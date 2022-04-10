package com.udemy.tmdbtest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object Constants {

    const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "4ec54be4d102949bcf75da44ecabb2fc"
    const val MOVIE_IMAGE_BASE_URL_342 = "https://image.tmdb.org/t/p/w342"
    const val MOVIE_IMAGE_BASE_URL_1280 = "https://image.tmdb.org/t/p/w1280"
    const val MOVIE_DETAILS = "movie_details"
    const val VIDEO_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/"
    const val VIDEO_THUMBNAIL_EXT_URL = "/0.jpg"
    const val DATABASE_NAME = "tmdb.db"
    const val SHOW_VIDEOS_RV = "show_videos_rv"

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}