package com.driven.youtubeclone.Service

import com.driven.youtubeclone.Model.Youtube_Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Youtube_Service {
    @GET("videos")
    fun getData(
        @Query("part") part: String,
        @Query("chart") chart:String,
        @Query("regionCode") region_code:String,
        @Query("maxResults") result:Int,
        @Query("key") key:String,
        @Query("nextPageToken")token:String
        ):Call<Youtube_Model>
}