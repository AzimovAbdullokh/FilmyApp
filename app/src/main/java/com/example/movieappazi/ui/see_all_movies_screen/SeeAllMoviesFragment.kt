package com.example.movieappazi.ui.see_all_movies_screen

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.databinding.FragmentAllTopRatedMoviesBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.collectLatest

@Parcelize
enum class MovieType : Parcelable {
    POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING,
}

@AndroidEntryPoint
class SeeAllMoviesFragment : Fragment(), RvClickListener<MovieUi> {

    private val binding by lazy {
        FragmentAllTopRatedMoviesBinding.inflate(layoutInflater)
    }
    private val args by navArgs<SeeAllMoviesFragmentArgs>()
    private val ratingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val upcomingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val nowPlayingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val viewModel: SeeAllMoviesFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTypes()
    }

    private fun setupTypes() {
        when (args.type) {
            MovieType.TOP_RATED -> {
                binding.allTopratedRv.adapter = ratingAdapter
                observeRatingsMovie()
            }
            MovieType.UPCOMING -> {
                binding.allTopratedRv.adapter = upcomingAdapter
                observeUpcomingMovies()
            }
            MovieType.POPULAR -> {
                binding.allTopratedRv.adapter = popularAdapter
                observePopularMovies()
            }
            MovieType.NOW_PLAYING -> {
                binding.allTopratedRv.adapter = nowPlayingAdapter
                observeNowPlayingMovies()
            }
        }
    }

    private fun observeRatingsMovie() {
        lifecycleScope.launchWhenResumed {
            viewModel.allTopRatedMovies.collectLatest {
                ratingAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePopularMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allPopularMovies.collectLatest {
                popularAdapter.submitList(it.movies)
            }
        }
    }

    private fun observeUpcomingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allUpcomingMovies.collectLatest {
                upcomingAdapter.submitList(it.movies)
            }
        }
    }

    private fun observeNowPlayingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allNowPlayingMovies.collectLatest {
                nowPlayingAdapter.submitList(it.movies)
            }
        }
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        makeToast("${item.title} saved successfully", requireContext())
    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(SeeAllMoviesFragmentDirections.actionSeeAllMoviesFragmentToMovieDetailsFragment(
            item))
    }
}