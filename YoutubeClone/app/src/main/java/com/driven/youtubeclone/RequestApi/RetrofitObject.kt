package com.driven.youtubeclone.RequestApi

import com.driven.youtubeclone.Service.Youtube_Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private lateinit var BASE_URL:String

    init {
        BASE_URL = "https://www.googleapis.com/youtube/v3/"
    }

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api by lazy {
        retrofit.create(Youtube_Service::class.java)
    }
}