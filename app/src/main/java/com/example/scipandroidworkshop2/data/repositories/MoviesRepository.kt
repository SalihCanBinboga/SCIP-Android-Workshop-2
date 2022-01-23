package com.example.scipandroidworkshop2.data.repositories

import com.example.scipandroidworkshop2.data.models.MoviesResponseModel
import com.example.scipandroidworkshop2.data.models.Results
import com.example.scipandroidworkshop2.data.remote.MovieService
import com.example.scipandroidworkshop2.utils.Constants

class MoviesRepository(
    private val movieService: MovieService
) {
    suspend fun getUpComingMovies(): MoviesResponseModel {
        return movieService.getUpComingMovies(apiKey = Constants.API_KEY)
    }

    suspend fun getMovie(movieID: String): Results {
        return movieService.getMovie(movieID = movieID, apiKey = Constants.API_KEY)
    }

}