package com.driven.youtubeclone.OnCLickAction

import android.content.Context
import android.content.Intent
import com.driven.youtubeclone.DetailActivity
import com.driven.youtubeclone.Model.Item
import com.driven.youtubeclone.OnClick_VideoListener.VideoClickListener

class Click_Action(private val context: Context) : VideoClickListener{
    override fun VideoListener(item: Item) {
        val intent = Intent(context,DetailActivity::class.java)
        intent.putExtra("Home",true)
        intent.putExtra("Videotitle",item.snippet.title)
        intent.putExtra("Videoviews",item.statistics.viewCount)
        intent.putExtra("Videoimage",item.snippet.thumbnails.standard.url)
        intent.putExtra("Videodescription",item.snippet.description)
        intent.putExtra("Videochannel",item.snippet.channelTitle)
        intent.putExtra("Videoduration",item.contentDetails.duration)
        intent.putExtra("Videocaption",item.contentDetails.caption)
        intent.putExtra("VideoDate",item.snippet.publishedAt)
        intent.putExtra("Videocomment",item.statistics.commentCount)
        context.startActivity(intent)

    }
}