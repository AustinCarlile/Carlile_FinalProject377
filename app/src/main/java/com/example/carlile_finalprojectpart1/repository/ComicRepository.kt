package com.example.carlile_finalprojectpart1.repository

import androidx.lifecycle.LiveData
import com.example.carlile_finalprojectpart1.data.Comic
import com.example.carlile_finalprojectpart1.data.ComicDao
import com.example.carlile_finalprojectpart1.network.Client
import com.example.carlile_finalprojectpart1.network.ComicResponse

// Repository Class for interacting with the Database Access Object
class ComicRepository(private val comicDao: ComicDao) {

    // Gets the latest comic from www.xkcd.com
    suspend fun getLatestComic(): ComicResponse {
        return Client.apiService.getLatestComic()
    }

    // Gets a comic by ID from www.xkcd.com
    suspend fun getComicById(id: Int): ComicResponse {
        return Client.apiService.getComicById(id)
    }

    // Inserts a comic into the ComicDatabase
    suspend fun insertComic(comic: Comic) {
        comicDao.insert(comic)
    }

    // Deletes a comic from the ComicDatabase
    suspend fun deleteComic(comic: Comic) {
        comicDao.deleteComic(comic)
    }

    // Retrieves the list of favorited Comics from the ComicDatabase
    fun getFavoriteComics(): LiveData<List<Comic>> {
        return comicDao.getFavoriteComics()
    }
}