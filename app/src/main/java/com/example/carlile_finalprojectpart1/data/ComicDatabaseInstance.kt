package com.example.carlile_finalprojectpart1.data

import android.content.Context
import androidx.room.Room

object ComicDatabaseInstance {

    private var databaseInstance: ComicDatabase? = null

    fun getInstance(context: Context): ComicDatabase {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(
                context.applicationContext,
                ComicDatabase::class.java,
                "comic_database"
            ).build()
        }
        return databaseInstance as ComicDatabase
    }
}