package com.example.myapplication.retrofit;

import com.example.myapplication.datamodel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface StoryClient {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);
}
