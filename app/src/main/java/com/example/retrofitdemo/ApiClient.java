package com.example.retrofitdemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://reqres.in/api/";
    private static ApiClient apiClient;
    private static Retrofit retrofit;

    public ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //to check whether the instance of class is created or not
    public static synchronized ApiClient getInstance(){
        if(apiClient==null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}
