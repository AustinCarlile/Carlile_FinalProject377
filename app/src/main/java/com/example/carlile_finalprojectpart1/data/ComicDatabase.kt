package com.example.carlile_finalprojectpart1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class ComicDatabase : RoomDatabase() {

    abstract fun comicDao(): ComicDao

    companion object {
        @Volatile
        private var INSTANCE: ComicDatabase? = null

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