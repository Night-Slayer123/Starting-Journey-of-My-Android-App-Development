package com.driven.weatherapp.RequestApi_Service

import com.driven.weatherapp.RequestModel.Weather_Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi_Service {

    @GET("weather")
    fun Weather_Data(@Query("q") country:String,@Query("app_id") api_key:String): Call<Weather_Model>
}