package com.lambdaschool.datapersistencesprintchallenge

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lambdaschool.datapersistencesprintchallenge.model.FavoriteMovie

@Dao
interface MovieDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: FavoriteMovie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(movie: FavoriteMovie)

    @Delete
    fun delete(movie: FavoriteMovie)

    @Query("SELECT * FROM movie_table")
    fun getAllMovies(): List<FavoriteMovie>


}