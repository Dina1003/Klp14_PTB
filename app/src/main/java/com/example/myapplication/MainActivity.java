package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void form(View view) {
        Intent intent = new Intent(MainActivity.this, FormPengajuan.class);
        startActivity(intent);
    }


    public void pindah(View view) {
        Intent intent = new Intent(MainActivity.this, DetailSemHas.class);
        startActivity(intent);
    }
}
