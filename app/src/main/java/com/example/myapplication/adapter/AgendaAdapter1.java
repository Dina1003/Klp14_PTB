package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Agenda1;

import java.util.ArrayList;

public class AgendaAdapter1 extends RecyclerView.Adapter<AgendaAdapter1.Agenda1ViewHolder> {

    ArrayList<Agenda1> listAgenda = new ArrayList<>();



    itemAgenda1ClickListener listener;

    public AgendaAdapter1(ArrayList<Agenda1> listAgenda) {
        this.listAgenda = listAgenda;
    }
    public void setListener(itemAgenda1ClickListener listener) {
        this.listener = listener;
    }
    public AgendaAdapter1(ArrayList<Agenda1> listAgenda, itemAgenda1ClickListener listener) {
        this.listAgenda = listAgenda;
        this.listener = listener;
    }


    @NonNull
    @Override
    public Agenda1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kelas1, parent, false);
        return new Agenda1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Agenda1ViewHolder holder, int position) {
        Agenda1 agenda = listAgenda.get(position);
        holder.texttanggal_kegiatan.setText(agenda.getTanggal());
        holder.textkegiatan_bimbingan.setText(agenda.getKegiatan());

    }

    @Override
    public int getItemCount() {
        return listAgenda.size();
    }



    public void setListAgenda(ArrayList<Agenda1> listAgenda) {
        this.listAgenda = listAgenda;
    }

    public interface itemAgenda1ClickListener{
        void onItemAgenda1Click(Agenda1 agenda1);
    }

    public class Agenda1ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView texttanggal_kegiatan;
        public TextView textkegiatan_bimbingan;

        public Agenda1ViewHolder(@NonNull View itemView) {
            super(itemView);

            textkegiatan_bimbingan = itemView.findViewById(R.id.kegiatan_bimbingan);
            texttanggal_kegiatan = itemView.findViewById(R.id.tanggal_kegiatan);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Agenda1 agenda = listAgenda.get(getAdapterPosition());
            listener.onItemAgenda1Click(agenda);

        }
    }
}
