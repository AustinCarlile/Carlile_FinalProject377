package com.example.carlile_finalprojectpart1.repository

import androidx.lifecycle.LiveData
import com.example.carlile_finalprojectpart1.data.Comic
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

    suspend fun insertComic(comic: Comic) {
        comicDao.insert(comic)
    }

    suspend fun deleteComic(comic: Comic) {
        comicDao.deleteComic(comic)
    }

    fun getFavoriteComics(): LiveData<List<Comic>> {
        return comicDao.getFavoriteComics()
    }
}