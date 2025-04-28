package com.example.carlile_finalprojectpart1.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carlile_finalprojectpart1.network.ComicResponse
import com.example.carlile_finalprojectpart1.repository.ComicRepository
import kotlinx.coroutines.launch

class ComicViewModel(private val comicRepository: ComicRepository) : ViewModel() {

    private val _latestComicLiveData = MutableLiveData<ComicResponse>()
    val latestComicLiveData: LiveData<ComicResponse> = _latestComicLiveData

    fun getLatestComic() {
        viewModelScope.launch {
            try {
                _latestComicLiveData.value = comicRepository.getLatestComic()
            } catch (exception: Exception) {
                Log.e("API Error", "Error fetching latest comic: ${exception.message}")
            }
        }
    }
}