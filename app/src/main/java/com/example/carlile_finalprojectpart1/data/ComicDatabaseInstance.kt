package com.example.carlile_finalprojectpart1.data

import android.content.Context
import androidx.room.Room

object ComicDatabaseInstance {

    // Singleton instance of the database
    private var databaseInstance: ComicDatabase? = null

    // Get the database instance
    fun getInstance(context: Context): ComicDatabase {
        // If the database instance is null, create a new instance
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