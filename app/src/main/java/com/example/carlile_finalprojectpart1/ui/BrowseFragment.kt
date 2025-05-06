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

class BrowseFragment : Fragment() {

    private val viewModel: ComicViewModel by viewModels {
       val comicDao = com.example.carlile_finalprojectpart1
           .data.ComicDatabase.getDatabase(requireContext()).comicDao()
        ComicViewModelFactory(com.example.carlile_finalprojectpart1
            .repository.ComicRepository(comicDao))
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BrowseAdapter

    private val comics = mutableListOf<Comic>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_browse, container, false)

        recyclerView = view.findViewById(R.id.rv_browse)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = BrowseAdapter(comics) { comic ->
            viewModel.addFavoriteComic(comic)
            Toast.makeText(requireContext(), "Comic favorited!", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadLatestComics()
    }

    @OptIn(UnstableApi::class)
    private fun loadLatestComics() {
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

