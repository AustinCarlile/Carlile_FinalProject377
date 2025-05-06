package com.example.carlile_finalprojectpart1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.ComicDatabaseInstance
import com.example.carlile_finalprojectpart1.network.ComicResponse
import com.example.carlile_finalprojectpart1.network.toComic
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModel
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.launch

// This is the SearchFragment that allows users to search for comics by number
class SearchFragment : Fragment() {

    // Declare variables
    private lateinit var comicViewModel: ComicViewModel
    private lateinit var searchEditText: EditText
    private lateinit var comicImageView: ImageView
    private lateinit var comicTitleTextView: TextView
    private lateinit var comicAltTextView: TextView
    private lateinit var favoriteButton: Button
    private lateinit var cardSearchResult: View
    private var latestSearchedComicResponse: ComicResponse? = null

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val application = requireActivity().application
        val comicDao = ComicDatabaseInstance.getInstance(application).comicDao()
        val comicRepository = ComicRepository(comicDao)
        val comicViewModelFactory = ComicViewModelFactory(comicRepository)

        comicViewModel = comicViewModelFactory.create(ComicViewModel::class.java)
        searchEditText = view.findViewById(R.id.et_search_query)
        comicImageView = view.findViewById(R.id.iv_comic)
        comicTitleTextView = view.findViewById(R.id.tv_comic_title)
        comicAltTextView = view.findViewById(R.id.tv_comic_alt)
        favoriteButton = view.findViewById(R.id.btn_favorite_item)
        cardSearchResult = view.findViewById(R.id.card_search_result)

        return view
    }

    // Load the latest comic when the view is created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Reset the comic views when the view is created
        resetComicViews()

        // Set up the search button click listener
        searchEditText.setOnEditorActionListener { _, actionId, event ->
            // If the user presses the search button or the enter key, perform the search
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch()
                true
            } else {
                false
            }
        }

        // Set up the favorite button click listener
        favoriteButton.setOnClickListener {
            latestSearchedComicResponse?.let {
                comicViewModel.addFavoriteComic(it.toComic())
                Toast.makeText(requireContext(), "Comic favorited!", Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(requireContext(), "Search a comic first!", Toast.LENGTH_SHORT).show()
        }
    }

    // Perform the search when the search button is clicked
    @SuppressLint("SetTextI18n")
    private fun performSearch() {
        val query = searchEditText.text.toString().trim()
        if (query.isNotEmpty()) {
            val comicNumber = query.toIntOrNull()

            // If the query is a valid number, perform the search
            if (comicNumber != null) {
                lifecycleScope.launch {
                    val comicResponse = comicViewModel.getComicResponseById(comicNumber)

                    // Update the UI with the search results
                    if (comicResponse != null) {
                        latestSearchedComicResponse = comicResponse
                        cardSearchResult.visibility = View.VISIBLE

                        // Load the comic image using Coil
                        comicImageView.load(comicResponse.img) {
                            placeholder(R.drawable.ic_launcher_foreground)
                            error(R.drawable.ic_no_comic_available)
                            crossfade(true)
                        }

                        // Update the comic title and alt text
                        comicTitleTextView.text = "Comic #${comicResponse.num} – ${comicResponse.title}"
                        comicAltTextView.text = comicResponse.alt
                    } else {
                        Toast.makeText(requireContext(), "Comic not found!", Toast.LENGTH_SHORT).show()
                        resetComicViews()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter a valid number!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Reset the comic views when the view is created
    private fun resetComicViews() {
        comicImageView.setImageDrawable(null)
        comicTitleTextView.text = ""
        comicAltTextView.text = ""
        latestSearchedComicResponse = null
        cardSearchResult.visibility = View.GONE
    }
}


