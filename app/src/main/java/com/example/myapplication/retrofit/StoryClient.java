package com.example.myapplication.retrofit;

import com.example.myapplication.datamodel.DetailSemhasResponse;
import com.example.myapplication.datamodel.ListPesertaResponse;
import com.example.myapplication.datamodel.ListSemhasResponse;
import com.example.myapplication.datamodel.ListSeminarTAResponse;
import com.example.myapplication.datamodel.LoginResponse;
import com.example.myapplication.datamodel.LogoutResponse;
import com.example.myapplication.datamodel.ProfilResponse;
import com.example.myapplication.datamodel.UbahPassword;
import com.example.myapplication.datamodel.UpdateProfilResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StoryClient {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("/api/me")
    Call<ProfilResponse> profill(@Header("Authorization") String token);


    @POST("/api/logout")
    Call<LogoutResponse> logout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("/api/password")
    Call<UbahPassword> UbahPassword(@Header("Authorization") String token, @Field("old_password") String old_password, @Field("new_password") String new_password, @Field("confirm_password") String confirm_password);

    @FormUrlEncoded
    @POST("/api/me/update")
    Call<UpdateProfilResponse> UpdateProfilResponse(@Header("Authorization") String token, @Field("email") String email, @Field("name") String name);


    @GET("/api/thesis/seminars/322/audiences")
    Call<ListPesertaResponse> getAudiens(@Header("Authorization") String token);

    @GET("/api/admin/thesis/seminar-submissions")
    Call<ListSemhasResponse> getSeminar(@Header("Authorization") String token);

    @GET("/api/theses/309/seminars")
    Call<DetailSemhasResponse> detailseminar(@Header("Authorization") String token);

//    @GET("/api/theses/{id}")
//    Call<DetailSemhasResponse> theses(
//            @Path("id") Integer idTheses,
//
////    Call<DetailSemhasResponse> theses(
////            @Path("id") Integer idTheses,
////            @Header("Authorization") String token);
////    Call<DetailSemhasResponse> theses(
////            @Path("id") Integer idTheses,
////            @Header("Authorization") String token);


}
