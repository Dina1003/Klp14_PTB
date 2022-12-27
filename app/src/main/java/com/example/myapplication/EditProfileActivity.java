package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.datamodel.LoginResponse;
import com.example.myapplication.datamodel.UbahPassword;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText edittextpasslama, edittextpassbaru, konfirmasipass;
    ImageButton update;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");

        edittextpasslama = findViewById(R.id.edittextpasslama);
        edittextpassbaru = findViewById(R.id.edittextpassbaru);
        konfirmasipass = findViewById(R.id.konfirmasipass);
        update = findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ubahPass();
            }
        });
    }

    public void ubahPass() {


        String old_password = edittextpasslama.getText().toString();
        String new_password = edittextpassbaru.getText().toString();
        String confirm_password = konfirmasipass.getText().toString();

        if (old_password.isEmpty()) {
            edittextpasslama.setError("fill this field");
            edittextpasslama.requestFocus();
            return;
        }
        if (new_password.isEmpty()) {
            edittextpassbaru.setError("fill this field");
            edittextpassbaru.requestFocus();
            return;
        }
        if (confirm_password.isEmpty()) {
            konfirmasipass.setError("fill this field");
            konfirmasipass.requestFocus();
            return;
        }


        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);

        Call<UbahPassword> call = client.UbahPassword("Bearer " + token, old_password, new_password, confirm_password);

        call.enqueue(new Callback<UbahPassword>() {
            @Override
            public void onResponse(Call<UbahPassword> call, Response<UbahPassword> response) {
                // Toast.makeText(LoginActivity.this, "sedang login...", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    UbahPassword ganti = response.body();
                    if (ganti != null) {

                        Toast.makeText(EditProfileActivity.this, ganti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                        startActivity(mainIntent);
                    }
                } else {
                    Toast.makeText(EditProfileActivity.this, "Gagal ganti password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UbahPassword> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }

    public void pindah(View view) {
        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}