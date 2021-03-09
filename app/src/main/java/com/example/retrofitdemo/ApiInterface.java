package com.example.retrofitdemo;

import com.example.retrofitdemo.Model.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //define all api here with response call
    @GET("users")
    Call<Users> getUserInfo(@Query("page")int page_no);
}
