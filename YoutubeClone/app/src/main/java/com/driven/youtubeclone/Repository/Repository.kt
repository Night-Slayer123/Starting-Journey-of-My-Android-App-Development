package com.driven.youtubeclone.Repository

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.driven.youtubeclone.Adapter.RecyclerView_Adapter
import com.driven.youtubeclone.Model.Item
import com.driven.youtubeclone.Model.Youtube_Model
import com.driven.youtubeclone.OnCLickAction.Click_Action
import com.driven.youtubeclone.RequestApi.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun fetchHomeData(
            part:String,
            chart:String,
            regionCode:String,
            maxResults:Int,
            api_key:String,
            nextpageToken:String,
            onResult:(list:List<Item>)->Unit
            ,onError:(query:String?)->Unit){

        RetrofitObject.api.getData(part,chart,regionCode,maxResults,api_key,nextpageToken).enqueue(object :
            Callback<Youtube_Model?> {
            override fun onResponse(p0: Call<Youtube_Model?>, response: Response<Youtube_Model?>) {
                if(response.isSuccessful) {
                    onResult(response.body()?.items!!)
                }
            }

            override fun onFailure(p0: Call<Youtube_Model?>, p1: Throwable) {
               onError(p1.localizedMessage)
            }
        })
    }
}