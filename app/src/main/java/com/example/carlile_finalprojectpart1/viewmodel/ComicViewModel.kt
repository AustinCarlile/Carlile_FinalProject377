package com.example.carlile_finalprojectpart1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carlile_finalprojectpart1.data.Comic
import com.example.carlile_finalprojectpart1.network.ComicResponse
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import kotlinx.coroutines.launch

// ViewModel class for retrieving Comics using the ComicRepository
class ComicViewModel(private val comicRepository: ComicRepository) : ViewModel() {

    // Gets the latest comic from the ComicRepository,
    // handles Exception if no ComicResponse is returned
    suspend fun getLatestComicResponse(): ComicResponse? {
        return try {
            comicRepository.getLatestComic()
        } catch (e: Exception) {
            null
        }
    }

    // Gets a comic by ID from the ComicRepository,
    // handles Exception if no ComicResponse is returned
    suspend fun getComicResponseById(id: Int): ComicResponse? {
        return try {
            comicRepository.getComicById(id)
        } catch (e: Exception) {
            null
        }
    }

    // Adds a comic to favorites in the ComicDatabase
    fun addFavoriteComic(comic: Comic) {
        viewModelScope.launch {
            comicRepository.insertComic(comic)
        }
    }

    // Removes a comic from favorites in the ComicDatabase
    fun removeFavoriteComic(comic: Comic) {
        viewModelScope.launch {
            comicRepository.deleteComic(comic)
        }
    }

    // Gets list of all favorite comics from ComicDatabase
    fun getFavoriteComics(): LiveData<List<Comic>> {
        return comicRepository.getFavoriteComics()
    }
}







