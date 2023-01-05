package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.adapter.ListPesertaAdapter;
import com.example.myapplication.datamodel.AudiencesItem;
import com.example.myapplication.datamodel.ListPesertaResponse;
import com.example.myapplication.retrofit.StoryClient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListPesertaSeminarHasilActivity extends AppCompatActivity {

    private RecyclerView rvListPeserta;
    private ListPesertaAdapter adapter;
//    String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_peserta_seminar_hasil);



        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String token = sharedPref.getString("TOKEN", "");
        Log.d("list",token.toString());
;
        rvListPeserta = findViewById(R.id.rv_listPesertaseminar);
        rvListPeserta.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ListPesertaAdapter();
        rvListPeserta.setAdapter(adapter);

        //Minta Data Ke server
        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);

        

        Call<ListPesertaResponse> call = client.getAudiens("Bearer " + token);

        call.enqueue(new Callback<ListPesertaResponse>() {
            @Override
            public void onResponse(Call<ListPesertaResponse> call, Response<ListPesertaResponse> response) {

                Log.d("ListLogbook-Debug", response.toString());
                ListPesertaResponse listPesertaResponse = response.body();
                if(listPesertaResponse != null ){

                    List<AudiencesItem> audiences = listPesertaResponse.getAudiences();
                    Log.d("ListLogbook-Debug", audiences.toString());
                    adapter.setItemList(audiences);
                }

            }

            @Override
            public void onFailure(Call<ListPesertaResponse> call, Throwable t) {

            }
        });

    }

    public void maju(View view) {
        Intent intent = new Intent(ListPesertaSeminarHasilActivity.this, ListSeminarTugasAkhirActivity.class);
        startActivity(intent);
    }













    }
