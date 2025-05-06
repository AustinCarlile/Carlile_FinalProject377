package com.example.carlile_finalprojectpart1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.carlile_finalprojectpart1.repository.ComicRepository

@Suppress("UNCHECKED_CAST")
class ComicViewModelFactory(private val comicRepository: ComicRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
            return ComicViewModel(comicRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}