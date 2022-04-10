package com.udemy.tmdbtest.ui.adapter.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udemy.tmdbtest.R
import com.udemy.tmdbtest.data.model.models.VideoModel


class VideosAdapter(
    private val videoList: List<VideoModel>, private val onVideoClicked: (VideoModel) -> Unit
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_video,parent,false)
        return VideoViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.render(videoList[position],onVideoClicked)
    }

    override fun getItemCount(): Int = videoList.size
}