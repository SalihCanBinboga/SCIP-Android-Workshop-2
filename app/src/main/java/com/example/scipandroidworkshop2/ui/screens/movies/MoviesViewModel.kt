package com.example.scipandroidworkshop2.ui.screens.movies

import android.content.Context
import androidx.lifecycle.*
import com.example.scipandroidworkshop2.data.remote.MovieService
import com.example.scipandroidworkshop2.data.repositories.MoviesRepository
import com.example.scipandroidworkshop2.utils.Resource
import kotlinx.coroutines.Dispatchers

class MoviesViewModel : ViewModel() {
    private val moviesRepository: MoviesRepository by lazy {
        MoviesRepository(movieService = MovieService.getInstance())
    }

    fun getUpComingMovies(context: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = moviesRepository.getUpComingMovies(context = context)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}