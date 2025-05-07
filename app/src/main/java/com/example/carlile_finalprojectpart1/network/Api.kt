package com.example.carlile_finalprojectpart1.network


import retrofit2.http.GET
import retrofit2.http.Path

// API interface
interface Api {

    // Gets the latest comic from www.xkcd.com
    @GET("/info.0.json")
    suspend fun getLatestComic(): ComicResponse

    // Gets comic from www.xkcd.com by id
    @GET("/{id}/info.0.json")
    suspend fun getComicById(@Path("id") id: Int): ComicResponse

}