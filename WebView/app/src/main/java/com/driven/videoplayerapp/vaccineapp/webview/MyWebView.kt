package com.driven.videoplayerapp.vaccineapp.webview

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class MyWebView :WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        var progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        progressBar?.visibility = View.VISIBLE
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        var progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
        progressBar?.visibility = View.GONE
        super.onPageFinished(view, url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }

}