package com.udemy.tmdbtest.ui.adapter.video

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.udemy.tmdbtest.data.model.models.VideoModel
import com.udemy.tmdbtest.databinding.ItemVideoBinding
import com.udemy.tmdbtest.utils.Constants

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemVideoBinding.bind(view)

    fun render(video: VideoModel, onVideoClicked: (VideoModel) -> Unit) {
        Glide.with(binding.root.context).load(Constants.VIDEO_THUMBNAIL_BASE_URL + video.key + Constants.VIDEO_THUMBNAIL_EXT_URL)
            .diskCacheStrategy(DiskCacheStrategy.DATA).transform(CenterCrop()).into(binding.videoThumbnail)
        itemView.setOnClickListener {
            onVideoClicked(video)
        }
    }

}
