package com.udemy.tmdbtest.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udemy.tmdbtest.databinding.ActivityMainBinding
import com.udemy.tmdbtest.ui.adapter.movie.MoviesAdapter
import com.udemy.tmdbtest.ui.viewmodel.RecyclerMoviesViewModel
import com.udemy.tmdbtest.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import android.view.View
import com.udemy.tmdbtest.data.model.models.MovieModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val moviesViewModel: RecyclerMoviesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularLinearLayoutManager: LinearLayoutManager

    private lateinit var nowPlayingMoviesAdapter: MoviesAdapter
    private lateinit var nowPlayingLinearLayoutManager: LinearLayoutManager

    private lateinit var upcomingMoviesAdapter: MoviesAdapter
    private lateinit var upcomingLinearLayoutManager: LinearLayoutManager

    private var pagePopularListIndex: Int = 1
    private var pageNowPlayingListIndex: Int = 1
    private var pageUpcomingListIndex: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onCreatePopularMoviesRecyclerViewAdapter()
        onCreateNowPlayingMoviesRecyclerViewAdapter()
        onCreateUpcomingMoviesRecyclerViewAdapter()
    }


    private fun onCreateNowPlayingMoviesRecyclerViewAdapter() {
        nowPlayingMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> onMovieClicked(movie) }
        nowPlayingLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvNowPlayingMovies.layoutManager = nowPlayingLinearLayoutManager
        binding.rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter

        moviesViewModel.nowPlayingMovieList.observe(this) { movies ->
            nowPlayingMoviesAdapter.appendMovies(movies)
            binding.progressPlayingNow.visibility = View.GONE
            addNowPlayingMoviesOnScrollLimit()
        }

        moviesViewModel.rvStatePlayingNow.observe(this) {
            binding.rvNowPlayingMovies.visibility = View.VISIBLE
        }

        moviesViewModel.fetchNowPlayingMoviesData(pageNowPlayingListIndex,Constants.isNetworkAvailable(this@MainActivity))
    }

    private fun onCreatePopularMoviesRecyclerViewAdapter() {
        popularMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> onMovieClicked(movie) }
        popularLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovies.layoutManager = popularLinearLayoutManager
        binding.rvPopularMovies.adapter = popularMoviesAdapter

        moviesViewModel.popularMovieList.observe(this) { movies ->
            popularMoviesAdapter.appendMovies(movies)
            binding.progressPopular.visibility = View.GONE
            addPopularMoviesOnScrollLimit()
        }


        moviesViewModel.rvStatePopular.observe(this) {
            binding.rvPopularMovies.visibility = View.VISIBLE
        }

        moviesViewModel.fetchPopularMoviesData(pagePopularListIndex, Constants.isNetworkAvailable(this))
    }

    private fun onCreateUpcomingMoviesRecyclerViewAdapter() {
        upcomingMoviesAdapter = MoviesAdapter(mutableListOf()) { movie -> onMovieClicked(movie) }
        upcomingLinearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingMovies.layoutManager = upcomingLinearLayoutManager
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter

        moviesViewModel.upcomingMovieList.observe(this) { movies ->
            upcomingMoviesAdapter.appendMovies(movies)
            binding.progressUpcoming.visibility = View.GONE
            addUpcomingMoviesOnScrollLimit()
        }

        moviesViewModel.rvStateUpcoming.observe(this) {
            binding.rvUpcomingMovies.visibility = View.VISIBLE
        }

        moviesViewModel.fetchUpcomingMoviesData(pageUpcomingListIndex,Constants.isNetworkAvailable(this@MainActivity))
    }

    private fun addPopularMoviesOnScrollLimit() {
        binding.rvPopularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalMovieCount = popularLinearLayoutManager.itemCount
                val lastVisible = popularLinearLayoutManager.findLastCompletelyVisibleItemPosition()


                if (lastVisible>=totalMovieCount-3) {
                    binding.rvPopularMovies.removeOnScrollListener(this)
                    pagePopularListIndex++
                    moviesViewModel.fetchPopularMoviesData(pagePopularListIndex,Constants.isNetworkAvailable(this@MainActivity))
                }
            }
        })
    }

    private fun addNowPlayingMoviesOnScrollLimit() {
        binding.rvNowPlayingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalMovieCount = popularLinearLayoutManager.itemCount
                val lastVisible = popularLinearLayoutManager.findLastCompletelyVisibleItemPosition()


                if (lastVisible>=totalMovieCount-3) {
                    binding.rvNowPlayingMovies.removeOnScrollListener(this)
                    pageNowPlayingListIndex++
                    moviesViewModel.fetchNowPlayingMoviesData(pageNowPlayingListIndex,Constants.isNetworkAvailable(this@MainActivity))
                }
            }
        })
    }

    private fun addUpcomingMoviesOnScrollLimit() {
        binding.rvUpcomingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalMovieCount = popularLinearLayoutManager.itemCount
                val lastVisible = popularLinearLayoutManager.findLastCompletelyVisibleItemPosition()


                if (lastVisible>=totalMovieCount-3) {
                    binding.rvUpcomingMovies.removeOnScrollListener(this)
                    pageUpcomingListIndex++
                    moviesViewModel.fetchUpcomingMoviesData(pageUpcomingListIndex,Constants.isNetworkAvailable(this@MainActivity))
                }
            }
        })
    }

    private fun onMovieClicked(movie: MovieModel) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Constants.MOVIE_DETAILS,movie)
        startActivity(intent)

    }
}