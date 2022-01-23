package com.example.scipandroidworkshop2.data.remote

import com.example.scipandroidworkshop2.data.models.MoviesResponseModel
import com.example.scipandroidworkshop2.data.models.Results
import com.example.scipandroidworkshop2.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    companion object {
        var retrofitService: MovieService? = null
        fun getInstance(): MovieService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MovieService::class.java)
            }
            return retrofitService!!
        }

    }
}