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
import android.widget.Toast;

import com.example.myapplication.adapter.logbookAdapter;
import com.example.myapplication.datamodel.ListLogbookResponse;
import com.example.myapplication.datamodel.LogbooksItem;
import com.example.myapplication.datamodel.PendaftaranTAResponse;
import com.example.myapplication.models.logbook;
import com.example.myapplication.retrofit.StoryClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class logbookList extends AppCompatActivity{

    private RecyclerView rvLogbook;
    private logbookAdapter adapter;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");

        rvLogbook = findViewById(R.id.rv_logbook);
        rvLogbook.setLayoutManager(new LinearLayoutManager(this));
        adapter = new logbookAdapter();
        rvLogbook.setAdapter(adapter);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);

        Call<ListLogbookResponse> call = client.listLogbook("Bearer " + token);
        call.enqueue(new Callback<ListLogbookResponse>() {
            @Override
            public void onResponse(Call<ListLogbookResponse> call, Response<ListLogbookResponse> response) {
                Log.d("logbook-debug", response.toString());
                ListLogbookResponse listlogbook = response.body();
                if (listlogbook != null){
                    List<LogbooksItem> logbooks = listlogbook.getLogbooks();
                    adapter.setListLogbook(logbooks);
                    Log.d("logbook-debug", String.valueOf(logbooks.size()));
                }
            }

            @Override
            public void onFailure(Call<ListLogbookResponse> call, Throwable t) {
                Toast.makeText(logbookList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




//    @Override
//    public void onItemLogbookClick(logbook logbook) {
//        Intent detailIntent = new Intent(this, Logbook_Detail.class);
////        detailIntent.putExtra("tanggal", agenda1.getTanggal());
//        startActivity(detailIntent);
//
//    }

    public void balik(View view) {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void add(View view) {
        Intent intent = new Intent(this, Logbook_Add.class);
        startActivity(intent);
    }

}