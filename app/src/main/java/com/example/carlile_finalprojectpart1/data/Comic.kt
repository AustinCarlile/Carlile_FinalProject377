package com.example.carlile_finalprojectpart1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Comic entity class
@Entity(tableName = "favorite_comics")
data class Comic (
    @PrimaryKey val id: Int,
    val title: String,
    val img: String,
    val alt: String,
    val year: String,
    val day: String,
    val month: String,
    val favorite: Boolean
)