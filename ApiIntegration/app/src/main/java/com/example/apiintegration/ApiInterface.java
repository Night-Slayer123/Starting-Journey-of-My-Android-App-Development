package com.example.apiintegration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/posts")
    Call<List<UserModel>> getUser();
}
