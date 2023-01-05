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

import com.example.myapplication.adapter.SidangAdapter;
import com.example.myapplication.datamodel.LogbooksItem;
import com.example.myapplication.datamodel.PembuktianRVResponse;
import com.example.myapplication.datamodel.RVResponse;
import com.example.myapplication.models.Sidang;
import com.example.myapplication.retrofit.StoryClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sidang_Awal extends AppCompatActivity implements SidangAdapter.ItemSidangCLickListener{

    //bagian dina
    private RecyclerView rvSidang;
    private SidangAdapter adapter;
    String waktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidang_awal);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String token = sharedPref.getString("TOKEN", "");
        Log.d("ListLogbook-Debug", token);

        rvSidang = findViewById(R.id.rvSidang);
        rvSidang.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SidangAdapter();
        rvSidang.setAdapter(adapter);

        //minta data
        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<RVResponse> call = client.rvdata("Bearer " + token);
        call.enqueue(new Callback<RVResponse>() {
            @Override
            public void onResponse(Call<RVResponse> call, Response<RVResponse> response) {
                Log.d("ListLogbook-Debug", response.toString());
                RVResponse getLogbookResponse = response.body();
                if(getLogbookResponse != null){
                    List<Object> logbooks = getLogbookResponse.getSeminars();
                    Log.d("ListLogbook-Debug", String.valueOf(logbooks.size()));
                    adapter.setSeminars(logbooks);
                }
            }

            @Override
            public void onFailure(Call<RVResponse> call, Throwable t) {

            }
        });



            //tes rv 2
        /*Call<TesRVResponse> call = client.rvtesdata(token);
        call.enqueue(new Callback<TesRVResponse>() {
            @Override
            public void onResponse(Call<TesRVResponse> call, Response<TesRVResponse> response) {
                TesRVResponse tesRVResponse = response.body();
                if (tesRVResponse != null){
                    List<LogbooksItem> logbooks = tesRVResponse.getLogbooks();
                    adapter.setLogbooks(logbooks);
                }
            }

            @Override
            public void onFailure(Call<TesRVResponse> call, Throwable t) {

            }
        });*/



        /*SidangAdapter adapter = new SidangAdapter(getSidang());
        adapter.setListener(this);*/
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);

       // rvSidang.setLayoutManager(layoutManager);
        //rvSidang.setAdapter(adapter);
    }
    public ArrayList<Sidang> getSidang(){
        ArrayList<Sidang> listSidang = new ArrayList<>();

        listSidang.add(new Sidang(
           "Jum'at/ 01-08-2023"
        ));

        listSidang.add(new Sidang(
                "Kamis/ 10-08-2023"
        ));
        listSidang.add(new Sidang(
                "Selasa/ 20-08-2023"
        ));
        listSidang.add(new Sidang(
                "Senin/ 25-08-2023"
        ));
        listSidang.add(new Sidang(
                "Rabu/ 01-09-2023"
        ));
        listSidang.add(new Sidang(
                "Kamis/ 10-09-2023"
        ));

        listSidang.add(new Sidang(
                "Selasa/ 25-09-2023"
        ));

        listSidang.add(new Sidang(
                "Rabu/ 31-09-2023"
        ));
        listSidang.add(new Sidang(
                "Rabu/ 01-10-2023"
        ));
        listSidang.add(new Sidang(
                "Kamis/ 10-10-2023"
        ));
        listSidang.add(new Sidang(
                "Jum'at/ 21-10-2023"
        ));



        return listSidang;
    }


    public void form(View view) {
        Intent intent = new Intent(Sidang_Awal.this, Pengajuan_Sidang.class);
        startActivity(intent);
    }

    public void pindah(View view) {
        Intent intent = new Intent(Sidang_Awal.this, DetailSidang.class);
        startActivity(intent);
    }

    public void home(View view) {
        Intent intent = new Intent(Sidang_Awal.this, HomeScreenActivity.class);
        startActivity(intent);
    }

   // @Override
   // public void onItemSidangClickListener(Sidang sidang) {
       /* Intent intent = new Intent(this, DetailSidang.class);
        intent.putExtra("waktu", sidang.getWaktu());
        startActivity(intent);*/
    //}
}