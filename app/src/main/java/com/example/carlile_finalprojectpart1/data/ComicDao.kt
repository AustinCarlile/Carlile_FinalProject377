package com.example.carlile_finalprojectpart1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

// Comic DAO interface
@Dao
interface ComicDao {

    // Insert a comic into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comic: Comic)

    // Get all favorite comics from the database
    @Query("SELECT * FROM favorite_comics WHERE favorite = true")
    fun getFavoriteComics(): LiveData<List<Comic>>

    // Updates a comic in the database
    @Update
    suspend fun updateComic(comic: Comic)

    // Delete a comic from the database
    @Delete
    suspend fun deleteComic(comic: Comic)
}