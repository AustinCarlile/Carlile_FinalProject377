package com.example.carlile_finalprojectpart1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Comic database class
@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class ComicDatabase : RoomDatabase() {

    // Get the comic DAO
    abstract fun comicDao(): ComicDao

    // Companion object to get the database
    companion object {
        @Volatile
        // Singleton instance of the database
        private var INSTANCE: ComicDatabase? = null

        // Get the database
        fun getDatabase(context: Context): ComicDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ComicDatabase::class.java,
                    "comic_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}