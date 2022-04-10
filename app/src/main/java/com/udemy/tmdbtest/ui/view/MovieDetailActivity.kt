package com.udemy.tmdbtest.ui.view


import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.udemy.tmdbtest.data.model.models.MovieModel
import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.databinding.ActivityMovieDetailBinding
import com.udemy.tmdbtest.databinding.DialogVideoFragmentBinding
import com.udemy.tmdbtest.ui.adapter.video.VideosAdapter
import com.udemy.tmdbtest.ui.viewmodel.RecyclerVideosViewModel
import com.udemy.tmdbtest.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: RecyclerVideosViewModel
    private lateinit var bindingDialog: DialogVideoFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkExtras()
    }

    private fun checkExtras() {
        if (intent.hasExtra(Constants.MOVIE_DETAILS)) {
            viewModel = ViewModelProvider(this)[RecyclerVideosViewModel::class.java]
            val movieExtra = intent.getSerializableExtra(Constants.MOVIE_DETAILS) as MovieModel
            movieExtra.let { movie ->

                Glide.with(this).load(Constants.MOVIE_IMAGE_BASE_URL_1280 + movie.backdropPath).into(binding.movieBackdrop)

                Glide.with(this).load(Constants.MOVIE_IMAGE_BASE_URL_342 + movie.posterPath).transform(CenterCrop())
                    .into(binding.moviePoster)

                binding.movieTitle.text = movie.title
                binding.movieRating.rating = movie.rating / 2
                binding.movieReleaseDate.text = movie.releaseDate
                binding.movieOverview.text = movie.overview

                onCreateMovieVideosRecyclerViewAdapter(movie.movieId)

            }
        } else {
            finish()
        }
    }

    private fun onCreateMovieVideosRecyclerViewAdapter(movieId: Long) {

        viewModel.videoList.observe(this) { videos ->
            val videosAdapter = VideosAdapter(videos) { video -> onMovieClicked(video) }
            val videoLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvMovieVideos.layoutManager = videoLayoutManager
            binding.rvMovieVideos.adapter = videosAdapter

        }
        viewModel.fetchMovieVideos(movieId,Constants.isNetworkAvailable(this))
    }

    private fun onMovieClicked(video: VideoModel) {
        val dialog = Dialog(this)
        bindingDialog = DialogVideoFragmentBinding.inflate(layoutInflater)
        lifecycle.addObserver(bindingDialog.youtubePlayerView)
        dialog.setContentView(bindingDialog.root)
        dialog.setCancelable(true)
        setDialogWidth(dialog)

        bindingDialog.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(video.key, 0f)
            }
        })
    }

    private fun setDialogWidth(dialog: Dialog) {
        val width: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            (windowManager.currentWindowMetrics.bounds.width() * 0.90).toInt()
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            (displayMetrics.widthPixels * 0.90).toInt()
        }
        dialog.window?.attributes?.let { dialog.window?.setLayout(width, it.height) }
        dialog.show()
    }

}