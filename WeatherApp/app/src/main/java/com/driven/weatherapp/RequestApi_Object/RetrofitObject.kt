package com.driven.weatherapp.RequestApi_Object

import com.driven.weatherapp.RequestApi_Service.WeatherApi_Service
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val retrofit by lazy{
        Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api by lazy {
        retrofit.create(WeatherApi_Service::class.java)
    }
}