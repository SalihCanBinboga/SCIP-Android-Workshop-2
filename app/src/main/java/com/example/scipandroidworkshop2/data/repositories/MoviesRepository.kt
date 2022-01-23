package com.example.scipandroidworkshop2.data.repositories

import com.example.scipandroidworkshop2.data.remote.MovieService
import com.example.scipandroidworkshop2.utils.Constants

class MoviesRepository
    (
    private val movieService: MovieService
) {
    suspend fun getUpComingMovies() =
        movieService.getUpComingMovies(apiKey = Constants.API_KEY)

    suspend fun getMovie(movieID: String) =
        movieService.getMovie(movieID = movieID, apiKey = Constants.API_KEY)

}