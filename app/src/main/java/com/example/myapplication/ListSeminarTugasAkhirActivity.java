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

import com.example.myapplication.adapter.ListSeminarAdapter;
import com.example.myapplication.datamodel.ListSemhasResponse;
import com.example.myapplication.datamodel.SeminarsItem;
import com.example.myapplication.retrofit.StoryClient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListSeminarTugasAkhirActivity extends AppCompatActivity {

    RecyclerView rvseminar;
    ListSeminarAdapter listSeminarAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_seminar_tugas_akhir);


        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String token = sharedPref.getString("TOKEN", "");
        Log.d("list",token.toString());

        rvseminar = findViewById(R.id.rv_list_seminar);
        rvseminar.setLayoutManager(new LinearLayoutManager(this));

        listSeminarAdapter = new ListSeminarAdapter();
        rvseminar.setAdapter(listSeminarAdapter);

        //Minta Data Ke server
        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);



        Call<ListSemhasResponse> call = client.getSeminar("Bearer " + token);

        call.enqueue(new Callback<ListSemhasResponse>() {
            @Override
            public void onResponse(Call<ListSemhasResponse> call, Response<ListSemhasResponse> response) {

                Log.d("ListLogbook-Debug", response.toString());
                ListSemhasResponse listSemhasResponse = response.body();
                if(listSemhasResponse != null ){

                    List<SeminarsItem> seminarsItem = listSemhasResponse.getSeminars();
                    Log.d("ListLogbook-Debug", seminarsItem.toString());
                    listSeminarAdapter.setSeminarsItems(seminarsItem);
                }

            }

            @Override
            public void onFailure(Call<ListSemhasResponse> call, Throwable t) {

            }
        });




    }

//    @Override
//    public void onItemSeminarClick(SeminarsItem seminarsItem) {
//        Intent detailIntent = new Intent(this, DetailSeminarHasilActivity.class);
//        detailIntent.putExtra("Tempat", seminarsItem.getSeminarAt());
//        detailIntent.putExtra("Dibuat", seminarsItem.getCreatedAt());
//        detailIntent.putExtra("File", seminarsItem.getFileReport());
//        detailIntent.putExtra("Terdaftar", seminarsItem.getRegisteredAt());
//
//        startActivity(detailIntent);
//    }

    public void detail (View view)
    {
        Intent intent = new Intent(ListSeminarTugasAkhirActivity.this, DetailSeminarHasilActivity.class);
        startActivity(intent);
    }

    public void Back(View view) {
        Intent intent = new Intent(ListSeminarTugasAkhirActivity.this, ListPesertaSeminarHasilActivity.class);
        startActivity(intent);
    }
}