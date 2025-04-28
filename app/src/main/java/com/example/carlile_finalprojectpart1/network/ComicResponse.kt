package com.example.carlile_finalprojectpart1.network

import com.example.carlile_finalprojectpart1.data.Comic


data class ComicResponse(
    val month: String,
    val num: Int,
    val link: String,
    val year: String,
    val news: String,
    val safe_title: String,
    val transcript: String,
    val alt: String,
    val img: String,
    val title: String,
    val day: String
)
fun ComicResponse.toComic(): Comic {
    return Comic(
        id = this.num,
        title = this.title,
        img = this.img,
        alt = this.alt,
        day = this.day,
        month = this.month,
        year = this.year,
        favorite = false
    )
}

