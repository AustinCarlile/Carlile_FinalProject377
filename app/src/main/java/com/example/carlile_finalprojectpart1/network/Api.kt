package com.example.carlile_finalprojectpart1.network


import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/info.0.json")
    suspend fun getLatestComic(): ComicResponse

    @GET("/{id}/info.0.json")
    suspend fun getComicById(@Path("id") id: Int): ComicResponse

}