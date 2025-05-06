package com.example.carlile_finalprojectpart1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carlile_finalprojectpart1.data.Comic
import com.example.carlile_finalprojectpart1.network.ComicResponse
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import kotlinx.coroutines.launch

class ComicViewModel(private val comicRepository: ComicRepository) : ViewModel() {

    private val _latestComicLiveData = MutableLiveData<ComicResponse>()
    private val _specificComicLiveData = MutableLiveData<ComicResponse>()
    val latestComicLiveData: LiveData<ComicResponse> = _latestComicLiveData
    val specificComic: LiveData<ComicResponse> = _specificComicLiveData

    suspend fun getLatestComicResponse(): ComicResponse? {
        return try {
            comicRepository.getLatestComic()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getComicResponseById(id: Int): ComicResponse? {
        return try {
            comicRepository.getComicById(id)
        } catch (e: Exception) {
            null
        }
    }

    fun addFavoriteComic(comic: Comic) {
        viewModelScope.launch {
            comicRepository.insertComic(comic)
        }
    }

    fun removeFavoriteComic(comic: Comic) {
        viewModelScope.launch {
            comicRepository.deleteComic(comic)
        }
    }

    fun getFavoriteComics(): LiveData<List<Comic>> {
        return comicRepository.getFavoriteComics()
    }
}







