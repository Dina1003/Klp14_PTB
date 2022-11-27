package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.example.myapplication.adapter.AgendaAdapter;
import com.example.myapplication.models.Agenda;

import java.util.ArrayList;


public class Semhas_Awal extends AppCompatActivity implements AgendaAdapter.ItemAgendaClickListener {
    //1.deklarasi
    private RecyclerView rvAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semhas_awal);
        //2.temukan id
        rvAgenda = findViewById(R.id.rvAgenda);

        //3. temukan layout & adapter
        AgendaAdapter adapter = new AgendaAdapter(getAgenda());
        adapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //4. configurasi layout & adapter
        rvAgenda.setLayoutManager(layoutManager);
        rvAgenda.setAdapter(adapter);

    }

    public  ArrayList<Agenda> getAgenda(){
        ArrayList<Agenda> listAgenda = new ArrayList<>();
        //5. buat data random
        listAgenda.add(new Agenda(
                "Selasa/ 12-10-2023"
        ));
        listAgenda.add(new Agenda(
                "Rabu/ 20-11-2023"
        ));
        listAgenda.add(new Agenda(
                "Kamis/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Senin/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Jum'at/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Rabu/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Selasa/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Jum'at/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Kamis/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Senin/ 18-12-2023"
        ));
        listAgenda.add(new Agenda(
                "Selasa/ 18-12-2023"
        ));



        return listAgenda;
    }

    public void form(View view) {
        Intent intent = new Intent(Semhas_Awal.this, FormPengajuan.class);
        startActivity(intent);
    }


    public void pindah(View view) {
        Intent intent = new Intent(Semhas_Awal.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void sidang(View view) {
        Intent intent = new Intent(Semhas_Awal.this, Sidang_Awal.class);
        startActivity(intent);
    }


    public void home(View view) {
        Intent intent = new Intent(Semhas_Awal.this, HomeScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemAgendaClick(Agenda agenda) {
        Intent detailIntent = new Intent(this, DetailSemHas.class);
        detailIntent.putExtra("Jadwal", agenda.getJadwal());
        startActivity(detailIntent);
//        Toast.makeText(this, "hahha"+ agenda.getJadwal(), Toast.LENGTH_SHORT).show();

    }

    //notifikasi
}
