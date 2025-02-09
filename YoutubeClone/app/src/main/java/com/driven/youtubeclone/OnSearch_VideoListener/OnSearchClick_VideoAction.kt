package com.driven.youtubeclone.OnSearch_VideoListener

import android.content.Context
import android.content.Intent
import com.driven.youtubeclone.DetailActivity
import com.driven.youtubeclone.OnClick_VideoListener.OnSearch_VideoListener
import com.driven.youtubeclone.SearchModel.Item

class OnSearchClick_VideoAction(private val context: Context) :OnSearch_VideoListener {
    override fun clicklistener(item: Item) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("Search",true)
        intent.putExtra("SVideotitle",item.snippet.title)
        intent.putExtra("SVideoviews","4886556")
        intent.putExtra("SVideoimage",item.snippet.thumbnails.high.url)
        intent.putExtra("SVideodescription",item.snippet.description)
        intent.putExtra("SVideochannel",item.snippet.channelTitle)
        intent.putExtra("SVideoDate",item.snippet.publishTime)
        intent.putExtra("SVideocomment","8495")
        context.startActivity(intent)
    }
}