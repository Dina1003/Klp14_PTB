package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.datamodel.LoginResponse;
import com.example.myapplication.dina.Constants1;
import com.example.myapplication.dina.AccessToken;
import com.example.myapplication.retrofit.StoryClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.FirebaseMessaging;

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
    String token;
    private static final String TAG = "Service-Debug";
    private static final String CHANNEL_ID= "Kelompok 14";
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //notifikasi firebase
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast

                        Log.d(TAG, token);
                        Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


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
                Toast.makeText(LoginActivity.this, "sedang login...", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    //logout

                    //Constants1.REFRESH_TOKEN = response.body().getRefreshToken();

                    //

                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {

                        Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();


                        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("TOKEN", loginResponse.getAuthorisation().getToken());
                        editor.apply();


                        Intent mainIntent = new Intent(LoginActivity.this, home_kosong.class);
                        startActivity(mainIntent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Username/password anda salah", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}