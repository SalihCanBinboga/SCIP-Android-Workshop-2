package com.example.scipandroidworkshop2.ui.screens.movie_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.scipandroidworkshop2.data.models.Results
import com.example.scipandroidworkshop2.databinding.FragmentMovieDetailBinding
import com.example.scipandroidworkshop2.utils.Status

class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    private val movieDetailViewModel: MovieDetailViewModel by activityViewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {
        movieDetailViewModel.getMovieDetail(movieId = args.movieId).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.movieProgressBar.visibility = View.GONE
                        resource.data?.let { movieResponse -> updateUI(movieResponse = movieResponse) }
                    }
                    Status.ERROR -> {
                        binding.movieProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.movieProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun updateUI(movieResponse: Results) {
        binding.apply {
            currentPointTextView.text = movieResponse.vote_average.toString()
            movieDateTextView.text = movieResponse.release_date
            movieTitleTextView.text = movieResponse.original_title
            movieDescriptionTextView.text = movieResponse.overview

            Glide.with(requireContext())
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${movieResponse.poster_path}")
                .into(movieBannerImageView)
        }

    }
}