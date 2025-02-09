package com.driven.youtubeclone.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.driven.youtubeclone.R

class Shorts_Adapter(private val context: Context,private val list:List<String>) : RecyclerView.Adapter<Shorts_Adapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Shorts_Adapter.viewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.shorts_layout,parent,false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Shorts_Adapter.viewHolder, position: Int) {

        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var webView: WebView
        lateinit var progressBar: ProgressBar

        init {
            webView = itemView.findViewById(R.id.videoView)
            progressBar = itemView.findViewById(R.id.progressBar3)
            val webSettings: WebSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.webViewClient = object :WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                }
            }
            WebView.setWebContentsDebuggingEnabled(true)
        }


        fun bind(videoUrl: String) {
            webView.loadUrl(videoUrl)
        }
    }
}