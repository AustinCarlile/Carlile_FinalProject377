package com.example.carlile_finalprojectpart1.repository

import com.example.carlile_finalprojectpart1.data.ComicDao
import com.example.carlile_finalprojectpart1.network.Client
import com.example.carlile_finalprojectpart1.network.ComicResponse

class ComicRepository(private val comicDao: ComicDao) {

    suspend fun getLatestComic(): ComicResponse {
        return Client.apiService.getLatestComic()
    }

    suspend fun getComicById(id: Int): ComicResponse {
        return Client.apiService.getComicById(id)
    }

}