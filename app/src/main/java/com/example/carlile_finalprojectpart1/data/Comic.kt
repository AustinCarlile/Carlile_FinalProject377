package com.example.carlile_finalprojectpart1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_comics")
data class Comic (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String,
    val favorite: Boolean
)