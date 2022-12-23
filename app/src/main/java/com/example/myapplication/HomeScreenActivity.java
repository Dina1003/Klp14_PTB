package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.AgendaAdapter1;
import com.example.myapplication.datamodel.ProfilResponse;
import com.example.myapplication.models.Agenda1;
import com.example.myapplication.retrofit.StoryClient;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreenActivity extends AppCompatActivity implements AgendaAdapter1.itemAgenda1ClickListener{

    private SharedPreferences sharedPreferences;
    Context context;
    private  SharedPreferences.Editor editor;
    TextView username;
    String token;

    SharedPrefManager sharedPrefManager;
    ImageView logout_btn;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN","");

        sharedPrefManager = new SharedPrefManager(this);

        logout_btn = findViewById(R.id.iconlogout);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                out();
            }
        });
    }

    private void getUsername() {
        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);


        Call<ProfilResponse> call = client.profill("Bearer " + token );
        call.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                if (!response.isSuccessful()) {
                    username.setText("Nama Mahasiswa");
                } else {
                    ProfilResponse user = response.body();

//                    Log.d("YYPROFILEACTIVITY", "Hasilnya: " + response.body().getEmail());
                    String nama = user.getName();

                    TextView castName = findViewById(R.id.username);


                    castName.setText(nama);
                }
            }

            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void profil(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    //Log-Out
    private void out() {
        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /*public void logout(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }
*/
    public void semhas(View view) {

        Intent intent = new Intent(HomeScreenActivity.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void sidang(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Sidang_Awal.class);
        startActivity(intent);
    }

    public void detail(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Logbook_Detail.class);
        startActivity(intent);
    }


    public void logbook(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }

    public void image_detaillog(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }

    public void image_detailperjalanan(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, logbookList.class);
        startActivity(intent);
    }

    public void image_detailsemhas(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void detailsidang(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, DetailSidang.class);
        startActivity(intent);
    }


    @Override
    public void onItemAgenda1Click(Agenda1 agenda1) {
        Intent detailIntent = new Intent(this, Logbook_Detail.class);
//        detailIntent.putExtra("tanggal", agenda1.getTanggal());
        startActivity(detailIntent);
    }


}