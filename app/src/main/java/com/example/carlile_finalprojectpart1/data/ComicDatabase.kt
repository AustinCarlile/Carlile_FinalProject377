package com.example.carlile_finalprojectpart1.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Comic::class], version = 1, exportSchema = false)
abstract class ComicDatabase : RoomDatabase() {

    abstract fun comicDao(): ComicDao

}