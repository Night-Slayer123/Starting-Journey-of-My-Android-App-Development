package com.example.apiintegration;

import static com.example.apiintegration.MainActivity.url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static RetrofitBuilder instance;
    ApiInterface api;
    RetrofitBuilder(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiInterface.class);
    }

    public static RetrofitBuilder getInstance(){

        if(instance==null){

            instance = new RetrofitBuilder();
        }

        return instance;
    }

    public ApiInterface getApi(){

        return api;
    }

}
