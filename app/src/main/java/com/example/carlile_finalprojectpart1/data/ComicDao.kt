package com.example.carlile_finalprojectpart1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comic: Comic)

    @Query("SELECT * FROM favorite_comics WHERE favorite = true")
    fun getFavoriteComics(): LiveData<List<Comic>>

    @Update
    suspend fun updateComic(comic: Comic)

    @Delete
    suspend fun deleteComic(comic: Comic)
}