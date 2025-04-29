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

    private val viewModel: ComicViewModel by viewModels {
        val comicDao = com.example.carlile_finalprojectpart1.data.ComicDatabase.getDatabase(requireContext()).comicDao()
        com.example.carlile_finalprojectpart1.viewmodel.ComicViewModelFactory(
            com.example.carlile_finalprojectpart1.repository.ComicRepository(comicDao)
        )
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoritesAdapter

    private val favorites = mutableListOf<Comic>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.rv_favorites)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoritesAdapter(favorites) { comic ->
            viewModel.removeFavoriteComic(comic)
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavoriteComics().observe(viewLifecycleOwner, Observer { favoriteList ->
            favorites.clear()
            favorites.addAll(favoriteList)
            adapter.notifyDataSetChanged()
        })
    }
}


