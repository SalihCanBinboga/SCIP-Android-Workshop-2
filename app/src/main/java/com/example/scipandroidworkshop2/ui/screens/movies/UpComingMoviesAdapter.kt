package com.example.scipandroidworkshop2.ui.screens.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scipandroidworkshop2.data.models.MoviesResponseModel
import com.example.scipandroidworkshop2.databinding.ItemUpComingListBinding

class UpComingMoviesAdapter(
    private var moviesModel: MoviesResponseModel?,
    private val mContext: Context
) : RecyclerView.Adapter<UpComingMoviesAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        moviesModel?.let { models ->
            val model = models.results[position]
            val view = holder.binding

            view.root.setOnClickListener {
                val directions =
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieId = model.id)
                it.findNavController().navigate(directions)
            }

            view.movieDateTextView.text = model.release_date
            view.movieDescriptionTextView.text = model.overview
            view.movieTitleTextView.text = model.original_title

            Glide.with(mContext)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${model.poster_path}")
                .into(view.movieImageView)
        }

    }

    fun updateMoviesModel(moviesModel: MoviesResponseModel) {
        this.moviesModel = moviesModel
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ItemUpComingListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUpComingListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = moviesModel?.results?.size ?: 0
}