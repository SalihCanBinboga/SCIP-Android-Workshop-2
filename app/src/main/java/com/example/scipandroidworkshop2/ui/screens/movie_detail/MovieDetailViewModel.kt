package com.example.scipandroidworkshop2.ui.screens.movie_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.scipandroidworkshop2.data.models.Results
import com.example.scipandroidworkshop2.data.remote.MovieService
import com.example.scipandroidworkshop2.data.repositories.MoviesRepository
import com.example.scipandroidworkshop2.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel : ViewModel() {
    private val moviesRepository: MoviesRepository by lazy {
        MoviesRepository(movieService = MovieService.getInstance())
    }

    fun getMovieDetail(movieId: Int): LiveData<Resource<Results>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = moviesRepository.getMovie(
                            movieID = movieId.toString()
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            }
        }
    }
}