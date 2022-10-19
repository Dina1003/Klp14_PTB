package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.adapter.PesertaAdapter;
import com.example.myapplication.models.PesertaSemhas;

import java.util.ArrayList;
//7. implementasi ke sini onclicknya
public class Peserta extends AppCompatActivity implements PesertaAdapter.ItemPesertaClickListener {

    private RecyclerView rvPeserta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta);

        rvPeserta = findViewById(R.id.rvPeserta);

        PesertaAdapter adapter = new PesertaAdapter(getPesertaSemhas());
        //8. beritahu listenerny siapa
        adapter.setListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        rvPeserta.setLayoutManager(layoutManager);
        rvPeserta.setAdapter(adapter);

    }

    public ArrayList<PesertaSemhas> getPesertaSemhas(){
        ArrayList<PesertaSemhas> listPeserta = new ArrayList<>();
        listPeserta.add(new PesertaSemhas(
                "budi"
        ));
        listPeserta.add(new PesertaSemhas(
                "laras"

        ));
        listPeserta.add(new PesertaSemhas(
                "Andre"
        ));
        listPeserta.add(new PesertaSemhas(
                "Kemala"
        ));
        listPeserta.add(new PesertaSemhas(
                "Bimbi"
        ));
        listPeserta.add(new PesertaSemhas(
                "Clara"
        ));
        listPeserta.add(new PesertaSemhas(
                "Intan"
        ));
        listPeserta.add(new PesertaSemhas(
                "Nayla"
        ));
        listPeserta.add(new PesertaSemhas(
                "Ridha"
        ));

        return listPeserta;
    }



    public void pindah(View view) {
        Intent intent = new Intent(Peserta.this, DetailSemHas.class);
        startActivity(intent);
    }

    public void input(View view) {
        Intent intent = new Intent(Peserta.this, InputPeserta.class);
        startActivity(intent);
    }

    public void deatil(View view) {
        Intent intent = new Intent(Peserta.this, DetailPeserta.class);
        startActivity(intent);
    }

    @Override
    public void onItemPesertaClickListener(PesertaSemhas pesertaSemhas) {

        //9. intentkan
        Intent intent = new Intent(this, DetailPeserta.class);
        intent.putExtra("peserta", pesertaSemhas.getPeserta());
        startActivity(intent);


    }
}