package com.example.myapplication.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static  StoryClient service;
    public static StoryClient getService() {

        if (service == null) {
            String API_BASE_URL = "http://ptb-api.husnilkamil.my.id/";

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.client(httpClient.build()).build();

            service = retrofit.create(StoryClient.class);
        }
        return service;

    }

}
