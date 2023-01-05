package com.example.myapplication.retrofit;

import com.example.myapplication.datamodel.AmbilLogbookResponse;
import com.example.myapplication.datamodel.AmbilSemhasResponse;
import com.example.myapplication.datamodel.AmbilSidangResponse;
import com.example.myapplication.datamodel.LoginResponse;
import com.example.myapplication.datamodel.LogoutResponse;
import com.example.myapplication.datamodel.PembuktianRVResponse;
import com.example.myapplication.datamodel.ProfilResponse;
import com.example.myapplication.datamodel.RVResponse;
import com.example.myapplication.datamodel.UbahPassword;
import com.example.myapplication.datamodel.UpdateProfilResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface StoryClient {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("/api/me")
    Call<ProfilResponse> profill(@Header("Authorization") String token);


    @POST("/api/logout")
    Call<LogoutResponse> logout(@Header("Authorization") String token);

    @GET("/api/theses/309/seminars") // tampil di home
    Call<AmbilSemhasResponse> semhas(@Header("Authorization") String token);

    @GET("/api/list/trials") // tampil di home
    Call<RVResponse> rvdata(@Header("Authorization") String token);

    @GET("/api/theses/277/trials") // sidang list
    Call<AmbilSidangResponse> sidang(@Header("Authorization") String token);

    @GET("/api/theses/200/logbooks/399") // tes rv, karena list sidang = kosong
    Call<AmbilLogbookResponse> logbook(@Header("Authorization") String token);


    //pembuktian buat rv
    @GET("/api/theses/309/logbooks")
    Call<PembuktianRVResponse> rvtesdata(@Header("Authorization") String token);



    @FormUrlEncoded
    @POST("/api/password")
    Call<UbahPassword> UbahPassword(@Header("Authorization") String token, @Field("old_password") String old_password, @Field("new_password") String new_password, @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST("/api/me/update")
    Call<UpdateProfilResponse> UpdateProfilResponse(@Header("Authorization") String token, @Field("email") String email, @Field("name") String name);

}
