package com.example.carlile_finalprojectpart1.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil3.load
import coil3.request.error
import coil3.request.placeholder
import com.bumptech.glide.Glide
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.ComicDatabaseInstance
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModel
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var comicViewModel: ComicViewModel
    private lateinit var comicImageView: ImageView
    private lateinit var comicTitleTextView: TextView
    private lateinit var comicAltTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireActivity().application
        val comicDatabase = ComicDatabaseInstance.getInstance(application)
        val comicDao = comicDatabase.comicDao()
        val comicRepository = ComicRepository(comicDao)
        val comicViewModelFactory = ComicViewModelFactory(comicRepository)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        comicViewModel = ViewModelProvider(
            this,
            comicViewModelFactory)[ComicViewModel::class.java]

        comicImageView = view.findViewById(R.id.iv_comic)
        comicTitleTextView = view.findViewById(R.id.tv_comic_title)
        comicAltTextView = view.findViewById(R.id.tv_comic_alt)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            comicViewModel.latestComicLiveData.observe(viewLifecycleOwner) { comic ->

                Glide.with(this@HomeFragment)
                    .load(comic.img)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(comicImageView)

                comicTitleTextView.text = "Comic #${comic.num} – ${comic.title}"
                comicAltTextView.text = comic.alt
            }
        }
    }
}