package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.datamodel.PendaftaranTAResponse;
import com.example.myapplication.retrofit.StoryClient;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormPendaftaranTActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //variabel pendaftaran
    EditText titleTA, abstrak, posisi;
    ImageButton daftar;
    String token,id_lecturer;
    private Spinner spinner;
    private static final String[] paths = {"Pilih ID Dosen", "2 : Husnil Kamil", "3 : Hasdi Putra","4 : Fajril Akbar",
                                            "5 : Surya Afnarius", "6 : Meza Silvana", "7 : Ricky Akbar", "8 : Haris Suryamen", "9 : Wahyudi"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pendaftaran_tactivity);

        SharedPreferences sharedPref = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        token = sharedPref.getString("TOKEN", "");

        titleTA = findViewById(R.id.judulTA);
        abstrak = findViewById(R.id.abstrak);

        posisi = findViewById(R.id.posisi);
        daftar = findViewById(R.id.daftar);

        spinner = (Spinner)findViewById(R.id.spinner_idlecturer);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FormPendaftaranTActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftarTA();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        switch (position) {
            case 1:
                this.id_lecturer="tidak ada";
                break;
            case 2:
                this.id_lecturer = "2";
                break;
            case 3:
                this.id_lecturer = "3";
                break;
            case 4:
                this.id_lecturer = "4";
                break;
            case 5:
                this.id_lecturer = "5";
                break;
            case 6:
                this.id_lecturer = "6";
                break;
            case 7:
                this.id_lecturer = "7";
                break;
            case 8:
                this.id_lecturer = "8";
                break;
            case 9:
                this.id_lecturer = "9";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void daftarTA() {
        String title = titleTA.getText().toString();
        String abstrakk = abstrak.getText().toString();
        String lecturer_id = id_lecturer.toString();
        String position = posisi.getText().toString();

        if (title.isEmpty()) {
            titleTA.setError("fill this field");
            titleTA.requestFocus();
            return;
        }
        if (abstrakk.isEmpty()) {
            abstrak.setError("fill this field");
            abstrak.requestFocus();
            return;
        }
//        if (lecturer_id==null) {
//            id_lecturer.setError("fill this field");
//            id_lecturer.requestFocus();
//            return;
//        }
        if (position.isEmpty()) {
            posisi.setError("fill this field");
            posisi.requestFocus();
            return;
        }


        String API_BASE_URL = "http://ptb-api.husnilkamil.my.id";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        StoryClient client = retrofit.create(StoryClient.class);

        Call<PendaftaranTAResponse> call = client.daftarTA("Bearer " + token, title, abstrakk, lecturer_id, position);
        call.enqueue(new Callback<PendaftaranTAResponse>() {
            @Override
            public void onResponse(Call<PendaftaranTAResponse> call, Response<PendaftaranTAResponse> response) {
                if (response.isSuccessful()) {
                    PendaftaranTAResponse ganti = response.body();
                    if (ganti != null) {
                        Toast.makeText(FormPendaftaranTActivity.this, ganti.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FormPendaftaranTActivity.this, HomeScreenActivity.class);
                        startActivity(intent);
                    }
                }  else {
                    Toast.makeText(FormPendaftaranTActivity.this, "Gagal mendaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PendaftaranTAResponse> call, Throwable t) {
                Toast.makeText(FormPendaftaranTActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }



}