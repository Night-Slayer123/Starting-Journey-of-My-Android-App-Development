package com.driven.youtubeclone.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.driven.youtubeclone.Model.Item
import com.driven.youtubeclone.Repository.Repository

class YoutubeViewModel(private val repository: Repository):ViewModel() {
    private val Api_Key = "AIzaSyDGe-JVkPJVaIJHnEwyphYleMhrcNK5Bv4"
    private val parts = "snippet,contentDetails,statistics"
    private val chart = "mostPopular"
    private val regionCode = "US"
    private val nextPageToken = "CDIQAA"
    private val maxResults = 50

    private val _data = MutableLiveData<List<Item>>()
    val data:LiveData<List<Item>> get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        repository.fetchHomeData(parts,chart,regionCode,maxResults,Api_Key,nextPageToken, onResult = {list -> _data.value=list }
            , onError = {throwable-> Log.e("Failure","FailedDue To:",throwable as Throwable)})
    }
}