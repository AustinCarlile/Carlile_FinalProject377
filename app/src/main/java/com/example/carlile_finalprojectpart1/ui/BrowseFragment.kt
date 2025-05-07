package com.example.carlile_finalprojectpart1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.Comic
import com.example.carlile_finalprojectpart1.network.toComic
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModel
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory
import kotlinx.coroutines.launch

// Fragment class that displays a RecyclerView of comics starting from the latest comic
class BrowseFragment : Fragment() {

    // Initialize the ComicViewModel using the ComicViewModelFactory
    private val viewModel: ComicViewModel by viewModels {
       val comicDao = com.example.carlile_finalprojectpart1
           .data.ComicDatabase.getDatabase(requireContext()).comicDao()
        ComicViewModelFactory(com.example.carlile_finalprojectpart1
            .repository.ComicRepository(comicDao))
    }

    // Declare the RecyclerView and adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BrowseAdapter

    // Create a list of comics
    private val comics = mutableListOf<Comic>()

    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_browse, container, false)

        // Initialize the RecyclerView and adapter
        recyclerView = view.findViewById(R.id.rv_browse)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = BrowseAdapter(comics) { comic ->
            viewModel.addFavoriteComic(comic)
            Toast.makeText(requireContext(), "Comic favorited!", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        return view
    }

    // Load latest comics
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadLatestComics()
    }

    @OptIn(UnstableApi::class)
    private fun loadLatestComics() {

        // Fetch the latest comic and add it to the list
        lifecycleScope.launch {
            val latestComicResponse = viewModel.getLatestComicResponse()
            if (latestComicResponse == null) {
                android.util.Log.e("BrowseFragment", "Failed to fetch latest comic.")
                return@launch
            }
            android.util.Log.d(
                "BrowseFragment",
                "Latest comic fetched: $latestComicResponse")

            val latestNum = latestComicResponse.num
            val startNum = (latestNum - 9).coerceAtLeast(1)

            // Fetch the comics from the start number to the latest number
            for (i in latestNum downTo startNum) {
                val comicResponse = viewModel.getComicResponseById(i)
                comicResponse?.let { response ->
                    val comic = response.toComic() // Convert ComicResponse to Comic
                    comics.add(comic)
                    adapter.notifyItemInserted(comics.size - 1)
                }
            }
        }
    }
}

