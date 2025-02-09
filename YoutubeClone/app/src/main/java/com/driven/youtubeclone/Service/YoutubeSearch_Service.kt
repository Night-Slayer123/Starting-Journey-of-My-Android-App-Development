package com.driven.youtubeclone.Service

import com.driven.youtubeclone.SearchModel.ModelSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeSearch_Service {

    @GET("search")
    fun searchVideos(
        @Query("part") part: String,
        @Query("type") type:String,
        @Query("maxResults") results:Int,
        @Query("q") query: String,
        @Query("key") key: String
    ):Call<ModelSearch>
}