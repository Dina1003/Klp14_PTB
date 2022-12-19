package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.datamodel.LoginResponse;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edittextusername, editpassword;
    ImageButton login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekLogin();
            }
        });


    }

    public void login(View view) {
        Intent intent = new Intent(LoginActivity.this, home_kosong.class);
        startActivity(intent);
    }

    public void cekLogin() {
        edittextusername = findViewById(R.id.edittextusername);
        editpassword = findViewById(R.id.editpassword);
        login_btn = findViewById(R.id.login_btn);

        String username = edittextusername.getText().toString();
        String password = editpassword.getText().toString();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(new OkHttpClient.Builder().build())
                        .build();

                StoryClient client = retrofit.create(StoryClient.class);

                Call<LoginResponse> call = client.login(username, password);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse != null && Objects.equals(loginResponse.getStatus(), "success")) {
                                Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                                Intent Intent = new Intent(LoginActivity.this, home_kosong.class);
                                startActivity(Intent);
                            }
                        } else {
                            Log.e("LoginActivity", response.message());
                            Toast.makeText(LoginActivity.this, "Username/password anda salah", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Gagal login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}