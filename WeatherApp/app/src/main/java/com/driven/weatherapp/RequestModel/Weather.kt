package com.driven.weatherapp.RequestModel

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)