package com.example.myapplication;

import com.example.myapplication.retrofit.StoryClient;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    private String API_BASE_URL = "http://ptb-api.husnilkamil.my.id/";


    public StoryClient configRetrofit(){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient route = retrofit.create(StoryClient.class);
        return route;
    }
}
