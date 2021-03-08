package com.example.retrofitdemo;

import com.example.retrofitdemo.Model.apiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    //define all api here with response call
    @GET("users?page=2")
    Call<apiResponse> getUserInfo();
}
