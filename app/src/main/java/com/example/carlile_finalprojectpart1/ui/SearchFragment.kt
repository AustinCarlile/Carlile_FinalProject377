package com.example.carlile_finalprojectpart1.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.ComicDatabaseInstance
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModel
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var comicViewModel: ComicViewModel
    private lateinit var searchEditText: EditText
    private lateinit var comicImageView: ImageView
    private lateinit var comicTitleTextView: TextView
    private lateinit var comicAltTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val application = requireActivity().application
        val comicDatabase = ComicDatabaseInstance.getInstance(application)
        val comicDao = comicDatabase.comicDao()
        val comicRepository = ComicRepository(comicDao)
        val comicViewModelFactory = ComicViewModelFactory(comicRepository)

        comicViewModel = comicViewModelFactory.create(ComicViewModel::class.java)

        searchEditText = view.findViewById(R.id.et_search_query)
        comicImageView = view.findViewById(R.id.iv_comic)
        comicTitleTextView = view.findViewById(R.id.tv_comic_title)
        comicAltTextView = view.findViewById(R.id.tv_comic_alt)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✨ Show initial welcome message
        resetComicViews()

        searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        val query = searchEditText.text.toString().trim()

        if (query.isNotEmpty()) {
            val comicNumber = query.toIntOrNull()

            if (comicNumber != null) {
                lifecycleScope.launch {
                    val comicResponse = comicViewModel.getComicResponseById(comicNumber)

                    if (comicResponse != null) {
                        comicImageView.load(comicResponse.img) {
                            placeholder(R.drawable.ic_launcher_foreground)
                            error(R.drawable.ic_no_comic_available)
                            crossfade(true)
                        }

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

    private fun resetComicViews() {
        comicImageView.setImageDrawable(null) // No image shown
        comicTitleTextView.text = "" // No title yet
        comicAltTextView.text = "Find your favorite XKCD comic here!" // Welcome message
    }

}
