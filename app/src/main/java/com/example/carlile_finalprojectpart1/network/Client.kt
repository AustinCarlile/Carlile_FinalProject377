package com.example.carlile_finalprojectpart1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Client object for interacting with www.xkcd.com
object Client {

    // URL string for connecting to the website
    private const val BASE_URL = "https://www.xkcd.com/"

    // Creates Retrofit for sending HTTP Requests to the website
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Creates an API from the Retrofit
    val apiService: Api = retrofit.create(Api::class.java)
}