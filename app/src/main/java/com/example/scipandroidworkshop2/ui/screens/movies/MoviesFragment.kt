package com.example.scipandroidworkshop2.ui.screens.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.scipandroidworkshop2.R
import com.example.scipandroidworkshop2.databinding.FragmentMovieDetailBinding
import com.example.scipandroidworkshop2.databinding.FragmentMoviesBinding
import com.example.scipandroidworkshop2.utils.Status


class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val homeViewModel: MoviesViewModel by viewModels()
    lateinit var upComingMoviesAdapter: UpComingMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupUI() {
        upComingMoviesAdapter = UpComingMoviesAdapter(null, requireContext())
        binding.upComingRecyclerView.apply {
            setHasFixedSize(true)
            adapter = upComingMoviesAdapter
        }
    }

    private fun setupObservers() {
        homeViewModel.getUpComingMovies(requireContext()).observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.moviesProgressBar.visibility = View.GONE
                        resource.data?.let { movieResponse ->
                            upComingMoviesAdapter.updateMoviesModel(moviesModel = movieResponse)
                        }
                    }
                    Status.ERROR -> {
                        binding.moviesProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.moviesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }


}