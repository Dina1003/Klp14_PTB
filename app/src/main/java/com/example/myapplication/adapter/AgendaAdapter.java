package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Agenda;

import java.util.ArrayList;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {
    //1.tempat simpan data
    ArrayList<Agenda> listAgenda = new ArrayList<>();

    //2. buat construktor sama setter
    public AgendaAdapter(ArrayList<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }

    //4. buat nempatin layout yang mau di tampilin
    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kelas, parent, false);
        return new AgendaViewHolder(view);
    }

    //6. configurasi view dari data yang kita punya
    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        //ambil data
        Agenda agenda = listAgenda.get(position);
        //tempatin data ke vh
        holder.textagenda.setText(agenda.getJadwal());

    }

    //3.mengembalikan jumlah data yang perlu ditampilkan dlm RV
    @Override
    public int getItemCount() {
        return  listAgenda.size();

    }

    public void setListAgenda(ArrayList<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }

    //5. representasi data yang ada di item_kelas
    public class AgendaViewHolder extends RecyclerView.ViewHolder {
        //deklarasi item yang ada di layout item_kelas disini dulu
        public TextView textagenda;

        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            textagenda = itemView.findViewById(R.id.agenda);

        }
    }
}
