package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.jadwalAdapter;
import com.example.myapplication.models.jadwal;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity implements jadwalAdapter.ItemJadwalClickListener{

    private RecyclerView rvsidsem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        rvsidsem =findViewById(R.id.rvsemsid);

        jadwalAdapter adapter = new jadwalAdapter(getjadwal());
        adapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);


        rvsidsem.setLayoutManager(layoutManager);
        rvsidsem.setAdapter(adapter);


    }

    public ArrayList<jadwal>getjadwal(){

        ArrayList<jadwal>listjadwal = new ArrayList<>();
        listjadwal.add(new jadwal(
                "Aulia Dwi Shaviqa Pohan",
                0,
                "11.00 WIB",
                "Ruang Sidang SI",
                "Senin, 23 Agustus 2023"

        ));
        listjadwal.add(new jadwal(
                "Aulia Dwi Shaviqa Pohan",
                1,
                "09.00 WIB",
                "Ruang Sidang SI",
                "Rabu, 01 Agustus 2023"

        ));
        return listjadwal;
    }



    public void profil(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void out(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void Logbook(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, HalamanLogbookActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnItemJadwalClick(jadwal jadwal) {
        Intent detailIntent =new Intent(this, DetailSidang.class );
        detailIntent.putExtra("tipe jadwal",jadwal.getTipejadwal());
        startActivity(detailIntent);
    }
}