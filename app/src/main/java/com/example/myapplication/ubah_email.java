package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.datamodel.UbahPassword;
import com.example.myapplication.datamodel.UpdateProfilResponse;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ubah_email extends AppCompatActivity {

    TextInputEditText edittextemaillama, edittextemailbaru;
    ImageButton updateemail;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_email2);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");

        edittextemaillama= findViewById(R.id.edittextemaillama);
        edittextemailbaru = findViewById(R.id.edittextemailbaru);
        updateemail = findViewById(R.id.updateemail);

        updateemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                update();
            }
        });
    }

    public void update() {


        String email = edittextemaillama.getText().toString();
        String name = edittextemailbaru.getText().toString();

        if (email.isEmpty()) {
            edittextemaillama.setError("fill this field");
            edittextemaillama.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            edittextemailbaru.setError("fill this field");
            edittextemailbaru.requestFocus();
            return;
        }


        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);

        Call<UpdateProfilResponse> call = client.UpdateProfilResponse("Bearer " + token,email, name );

        call.enqueue(new Callback<UpdateProfilResponse>() {
            @Override
            public void onResponse(Call<UpdateProfilResponse> call, Response<UpdateProfilResponse> response) {
                // Toast.makeText(LoginActivity.this, "sedang login...", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    UpdateProfilResponse ganti = response.body();
                    if (ganti != null) {

                        Toast.makeText(ubah_email.this, ganti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(ubah_email.this, ProfileActivity.class);
                        startActivity(mainIntent);
                    }
                } else {
                    Toast.makeText(ubah_email.this, "gagal update email", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateProfilResponse> call, Throwable t) {
                Toast.makeText(ubah_email.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    public void balik(View view) {
        Intent intent = new Intent(ubah_email.this, ProfileActivity.class);
        startActivity(intent);
    }
}