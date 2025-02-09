package com.driven.youtubeclone.RequestApi

import com.driven.youtubeclone.Service.YoutubeSearch_Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SearchRetrofit_Object {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://www.googleapis.com/youtube/v3/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api by lazy {
        retrofit.create(YoutubeSearch_Service::class.java)
    }
}