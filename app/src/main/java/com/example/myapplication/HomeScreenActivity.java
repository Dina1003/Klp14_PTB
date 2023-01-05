package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.AgendaAdapter1;
import com.example.myapplication.datamodel.AmbilLogbookResponse;
import com.example.myapplication.datamodel.AmbilSemhasResponse;
import com.example.myapplication.datamodel.AmbilSidangResponse;
import com.example.myapplication.datamodel.LogoutResponse;
import com.example.myapplication.datamodel.ProfilResponse;
import com.example.myapplication.models.Agenda1;
import com.example.myapplication.retrofit.ApiClient;
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

    //bagian dina

    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    TextView username,tanggalsemhas, judulsemhas, dosen2, tanggalsidang, ruangan, judulsidang;
    TextView castName, textView8, textView7, textView9;
    String gettoken, token, detail;


    SharedPrefManager sharedPrefManager;
    ImageView logout_btn;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        SharedPreferences sharedPref = getSharedPreferences("prefs", MODE_PRIVATE);
        token = sharedPref.getString("TOKEN","");

        getnama();
        getDetailSemhas();
        getsidang();
        getlogbook();



        tanggalsemhas = findViewById(R.id.tanggalsemhas);
        castName= findViewById(R.id.tanggalsemhas);
        //getSeminarAt();

        //getUsername();

        sharedPrefManager = new SharedPrefManager(this);

        logout_btn = findViewById(R.id.iconlogout);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logout();
            }


        });

    }

    public void logout(){
        logout_btn = findViewById(R.id.iconlogout);

        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);
        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<LogoutResponse> call = client.logout("Bearer " + token );
        call.enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {

                if(response.isSuccessful()){
                    LogoutResponse logoutResponse = response.body();
                    if(logoutResponse !=null){
                        Toast.makeText(HomeScreenActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPref = getSharedPreferences("Prefs",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.clear();
                        editor.apply();
                        finish();
                        Intent out = new Intent(HomeScreenActivity.this, LoginActivity.class);
                        out.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(out);
                    } else {
                        Toast.makeText(HomeScreenActivity.this, "Gagal logout", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, "Terjadi error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //di Api juga error
    /*private void getUsername() {
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
    }*/

    public void getDetailSemhas(){
        tanggalsemhas = findViewById(R.id.tanggalsemhas);
        judulsemhas = findViewById(R.id.judulsemhas);
        dosen2 = findViewById(R.id.dosen2);

        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<AmbilSemhasResponse> call = client.semhas("Bearer "+token);
        call.enqueue(new Callback<AmbilSemhasResponse>() {
            @Override
            public void onResponse(Call<AmbilSemhasResponse> call, Response<AmbilSemhasResponse> response) {
                if(response.isSuccessful()){
                    if(!response.isSuccessful()){
                        tanggalsemhas.setText("04 Januari 2023");
                        return;
                    } else {
                        AmbilSemhasResponse ambilSemhasResponse = response.body();

                        String tanggal = ambilSemhasResponse.getSeminarAt();
                        Integer ruangan = ambilSemhasResponse.getRoomId();
                        String url = (String) ambilSemhasResponse.getOnlineUrl();

                        tanggalsemhas.setText("Date: "+tanggal);
                        judulsemhas.setText("Room: "+String.valueOf(ruangan));
                        dosen2.setText("Url: "+String.valueOf(url));
                    }
                }
            }

            @Override
            public void onFailure(Call<AmbilSemhasResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void getnama(){

        username = findViewById(R.id.username);

        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<ProfilResponse> call = client.profill("Bearer "+token);
        call.enqueue(new Callback<ProfilResponse>() {
            @Override
            public void onResponse(Call<ProfilResponse> call, Response<ProfilResponse> response) {
                if(response.isSuccessful()){
                    if(!response.isSuccessful()){
                        username.setText("nama anda");
                        return;
                    } else {
                        ProfilResponse profilResponse = response.body();

                        String nama = profilResponse.getName();

                        username.setText(nama);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfilResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getsidang(){

        tanggalsidang = findViewById(R.id.tanggalsidang);
        ruangan = findViewById(R.id.ruangan);
        judulsidang = findViewById(R.id.judulsidang);

        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<AmbilSidangResponse> call = client.sidang("Bearer "+token);
        call.enqueue(new Callback<AmbilSidangResponse>() {
            @Override
            public void onResponse(Call<AmbilSidangResponse> call, Response<AmbilSidangResponse> response) {
                if(response.isSuccessful()){
                    if(!response.isSuccessful()){
                        username.setText("nama anda");
                        return;
                    } else {
                        AmbilSidangResponse profilResponse = response.body();

                        String tanggal = profilResponse.getCreatedAt();
                        Integer room = (Integer) profilResponse.getRoomId();
                        String mulai = profilResponse.getStartAt();

                        tanggalsidang.setText("Date: "+tanggal);
                        ruangan.setText("Room: "+String.valueOf(room));
                        judulsidang.setText("Start: "+mulai);

                    }
                }
            }

            @Override
            public void onFailure(Call<AmbilSidangResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getlogbook(){

        textView8 = findViewById(R.id.textView8);
        textView7 = findViewById(R.id.textView7);
        textView9 = findViewById(R.id.textView9);

        SharedPreferences sharedPref = getSharedPreferences("Prefs", MODE_PRIVATE);

        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryClient client = retrofit.create(StoryClient.class);

        Call<AmbilLogbookResponse> call = client.logbook("Bearer "+token);
        call.enqueue(new Callback<AmbilLogbookResponse>() {
            @Override
            public void onResponse(Call<AmbilLogbookResponse> call, Response<AmbilLogbookResponse> response) {
                if(response.isSuccessful()){
                    if(!response.isSuccessful()){
                        username.setText("nama anda");
                        return;
                    } else {
                        AmbilLogbookResponse profilResponse = response.body();

                        String tanggal = profilResponse.getDate();
                        String desk = profilResponse.getProgress();
                        String gap = profilResponse.getProblem();

                        textView8.setText("Date: "+tanggal);
                        textView7.setText("Progress: "+desk);
                        textView9.setText("Gap: "+gap);

                    }
                }
            }

            @Override
            public void onFailure(Call<AmbilLogbookResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, "ERROR", Toast.LENGTH_SHORT).show();

            }
        });


    }

    /*private void getSeminarAt() {
        Config config = new Config();
        Call<SemhasResponse> call = config.configRetrofit().semhas(token);
        call.enqueue(new Callback<SemhasResponse>() {
            @Override
            public void onResponse(Call<SemhasResponse> call, Response<SemhasResponse> response) {
                if (!response.isSuccessful()) {
                    SemhasResponse user = response.body();
                    String jadwal = user.getSeminarAt();
                    castName.setText(jadwal);
                }
            }

            @Override
            public void onFailure(Call<SemhasResponse> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/



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

        Intent intent = new Intent(HomeScreenActivity.this, Semhas_Awal.class);
        startActivity(intent);
    }

//    public void sidang(View view) {
//        Intent intent = new Intent(HomeScreenActivity.this, Sidang_Awal.class);
//        startActivity(intent);
//    }

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
        Intent intent = new Intent(HomeScreenActivity.this, Semhas_Awal.class);
        startActivity(intent);
    }

    public void detailsidang(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, Sidang_Awal.class);
        startActivity(intent);
    }


    @Override
    public void onItemAgenda1Click(Agenda1 agenda1) {
        Intent detailIntent = new Intent(this, Logbook_Detail.class);
//        detailIntent.putExtra("tanggal", agenda1.getTanggal());
        startActivity(detailIntent);
    }


    public void selengkapnya(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, DetailSidang.class);
        startActivity(intent);
    }
}