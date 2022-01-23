package com.example.scipandroidworkshop2.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.scipandroidworkshop2.data.models.Results


@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Results>

    @Query("SELECT * FROM movies WHERE id=:movieId")
    suspend fun getMovie(movieId: Int): Results

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg movie: Results)
}