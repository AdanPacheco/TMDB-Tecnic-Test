package com.udemy.tmdbtest.data.model.models

enum class MovieType(val type: Int) {
    POPULAR(1),
    PLAYING_NOW(2),
    UPCOMING(3);

    companion object {
        fun get(value: Int) = values().find { it.type == value }
    }
}