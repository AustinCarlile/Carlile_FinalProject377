package com.example.carlile_finalprojectpart1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carlile_finalprojectpart1.R
import com.example.carlile_finalprojectpart1.data.Comic
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModel
import com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory

class FavoritesFragment : Fragment() {
    // Initialize the ComicViewModel using the ComicViewModelFactory
    private val viewModel: ComicViewModel by viewModels {
        val comicDao = com.example.carlile_finalprojectpart1.data.ComicDatabase.getDatabase(requireContext()).comicDao()
        com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory(
            com.example.carlile_finalprojectpart1.repository.ComicRepository(comicDao)
        )
    }
    // Declare the RecyclerView and adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoritesAdapter
    // Create a list of favorites
    private val favorites = mutableListOf<Comic>()
    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        // Initialize the RecyclerView and adapter
        recyclerView = view.findViewById(R.id.rv_favorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoritesAdapter(favorites) { comic ->
            viewModel.removeFavoriteComic(comic)
        }
        recyclerView.adapter = adapter

        return view
    }
    // Observe the favorite list and update the adapter when it changes
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe the favorite list and update the adapter when it changes
        viewModel.getFavoriteComics().observe(viewLifecycleOwner, Observer { favoriteList ->
            favorites.clear()
            favorites.addAll(favoriteList)
            adapter.notifyDataSetChanged()
        })
    }
}


