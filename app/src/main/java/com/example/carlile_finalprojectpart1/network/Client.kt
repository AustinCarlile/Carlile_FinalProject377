package com.example.carlile_finalprojectpart1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    private const val BASE_URL = "https://www.xkcd.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: Api = retrofit.create(Api::class.java)
}