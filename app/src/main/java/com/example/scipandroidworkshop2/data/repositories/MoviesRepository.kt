package com.example.scipandroidworkshop2.data.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.scipandroidworkshop2.data.local.MovieDatabase
import com.example.scipandroidworkshop2.data.models.Results
import com.example.scipandroidworkshop2.data.remote.MovieService
import com.example.scipandroidworkshop2.utils.Constants

const val CACHE_UPDATE_PREF_KEY = "updated_time"

class MoviesRepository(
    private val movieService: MovieService,
) {
    suspend fun getUpComingMovies(context: Context): List<Results> {
        val localDatabase = Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "moviesdb"
        ).build()
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        val lastUpdateTime = preferences.getLong(CACHE_UPDATE_PREF_KEY, 0)
        val currentTime = System.currentTimeMillis()
        val diff = (currentTime - lastUpdateTime) / 1000


        return if (lastUpdateTime == 0L || diff > 30) {
            getMoviesRemote(localDatabase, preferences)
        } else {
            val localMovies = localDatabase.movieDao().getMovies()
            if (localMovies.isEmpty()) {
                getMoviesRemote(localDatabase, preferences)
            } else {
                localMovies
            }
        }


    }

    private suspend fun getMoviesRemote(
        localDatabase: MovieDatabase,
        preferences: SharedPreferences
    ): List<Results> {
        return movieService.getUpComingMovies(apiKey = Constants.API_KEY).also {
            localDatabase.movieDao().insertMovie(*it.results.toTypedArray())
            preferences.edit(commit = true) {
                putLong(CACHE_UPDATE_PREF_KEY, System.currentTimeMillis())
            }
        }.results
    }

    suspend fun getMovie(movieID: String): Results {
        return movieService.getMovie(movieID = movieID, apiKey = Constants.API_KEY)
    }

}