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

    //(2) identifikasi listener
    ItemAgendaClickListener listener;

    //2. buat construktor sama setter
    public AgendaAdapter(ArrayList<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }

    //(3) buat construktor sama setter listener

    public AgendaAdapter(ItemAgendaClickListener listener) {
        this.listener = listener;
    }

    public AgendaAdapter(ArrayList<Agenda> listAgenda, ItemAgendaClickListener listener) {
        this.listAgenda = listAgenda;
        this.listener = listener;
    }

    public void setListener(ItemAgendaClickListener listener) {
        this.listener = listener;
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

    //untuk buat rv interaktif
    //(1)buat kelas interfase
    public  interface  ItemAgendaClickListener{
        void onItemAgendaClick(Agenda agenda);
    }

    public void setListAgenda(ArrayList<Agenda> listAgenda) {
        this.listAgenda = listAgenda;
    }

    //5. representasi data yang ada di item_kelas
    public class AgendaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //deklarasi item yang ada di layout item_kelas disini dulu
        public TextView textagenda;

        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            textagenda = itemView.findViewById(R.id.agenda);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Agenda agenda = listAgenda.get(getAdapterPosition());
            listener.onItemAgendaClick(agenda);
        }
    }
}
