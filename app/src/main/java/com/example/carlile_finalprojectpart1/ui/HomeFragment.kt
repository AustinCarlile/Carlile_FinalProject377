package com.example.carlile_finalprojectpart1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.ComicDatabaseInstance
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModel
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.launch

// This is the HomeFragment that displays the latest comic
class HomeFragment : Fragment(R.layout.fragment_home) {

    // Declare variables
    private lateinit var comicViewModel: ComicViewModel
    private lateinit var comicImageView: ImageView
    private lateinit var comicTitleTextView: TextView
    private lateinit var comicAltTextView: TextView

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the ComicViewModel
        val application = requireActivity().application
        val comicDatabase = ComicDatabaseInstance.getInstance(application)
        val comicDao = comicDatabase.comicDao()
        val comicRepository = ComicRepository(comicDao)
        val comicViewModelFactory = ComicViewModelFactory(comicRepository)

        comicViewModel = ViewModelProvider(this, comicViewModelFactory)[ComicViewModel::class.java]

        return view
    }

    // Load the latest comic when the view is created
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the variables
        comicImageView = view.findViewById(R.id.iv_comic_home)
        comicTitleTextView = view.findViewById(R.id.tv_comic_title_home)
        comicAltTextView = view.findViewById(R.id.tv_comic_alt_home)

        // Load latest comic when Home screen opens
        lifecycleScope.launch {
            val latestComic = comicViewModel.getLatestComicResponse()

            // Update the UI with the latest comic
            if (latestComic != null) {
                comicImageView.load(latestComic.img) {
                    placeholder(R.drawable.ic_launcher_foreground)
                    error(R.drawable.ic_no_comic_available)
                    crossfade(true)
                }
                comicTitleTextView.text = "Comic #${latestComic.num} – ${latestComic.title}"
                comicAltTextView.text = latestComic.alt
            } else { // else, latestComic is null
                comicTitleTextView.text = "No Comic Found"
                comicAltTextView.text = ""
            }
        }
    }
}
