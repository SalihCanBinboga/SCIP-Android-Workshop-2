package com.example.scipandroidworkshop2.data.repositories

import com.example.scipandroidworkshop2.data.models.MoviesResponseModel
import com.example.scipandroidworkshop2.data.models.Results

interface MoviesRepository {
    suspend fun getUpComingMovies(): MoviesResponseModel
    suspend fun getMovie(movieID: String): Results
}