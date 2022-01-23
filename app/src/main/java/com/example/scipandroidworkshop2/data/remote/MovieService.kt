package com.example.scipandroidworkshop2.data.remote

import com.example.scipandroidworkshop2.data.models.MoviesResponseModel
import com.example.scipandroidworkshop2.data.models.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): MoviesResponseModel

    @GET("3/movie/{movieID}")
    suspend fun getMovie(
        @Path("movieID") movieID: String,
        @Query("api_key") apiKey: String
    ): Results

}