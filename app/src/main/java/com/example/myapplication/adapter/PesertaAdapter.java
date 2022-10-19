package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.PesertaSemhas;

import java.util.ArrayList;

public class PesertaAdapter extends RecyclerView.Adapter<PesertaAdapter.PesertaViewHolder> {

    ArrayList<PesertaSemhas> listPeserta = new ArrayList<>();

    //2. buat objek
    ItemPesertaClickListener listener;

    //3. setter dan construktor

    public PesertaAdapter(ArrayList<PesertaSemhas> listPeserta, ItemPesertaClickListener listener) {
        this.listPeserta = listPeserta;
        this.listener = listener;
    }

    public void setListener(ItemPesertaClickListener listener) {
        this.listener = listener;
    }

    public PesertaAdapter(ArrayList<PesertaSemhas> listPeserta) {
        this.listPeserta = listPeserta;
    }


    @NonNull
    @Override
    public PesertaAdapter.PesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_peserta, parent, false);
        return new PesertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PesertaAdapter.PesertaViewHolder holder, int position) {

        PesertaSemhas pesertaSemhas = listPeserta.get(position);
        holder.textpeserta.setText(pesertaSemhas.getPeserta());

    }

    @Override
    public int getItemCount() {
        return listPeserta.size();
    }

    public void setListPeserta(ArrayList<PesertaSemhas> listPeserta) {
        this.listPeserta = listPeserta;
    }

    //1. interface

    public interface ItemPesertaClickListener{
        void onItemPesertaClickListener(PesertaSemhas pesertaSemhas);
    }


    //4. implement oncliklistener
    public class PesertaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textpeserta;

        public PesertaViewHolder(@NonNull View itemView) {
            super(itemView);

            textpeserta = itemView.findViewById(R.id.peserta);

            //5. kasi tau siapa yang bisa di click
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //6. panggil metod
            PesertaSemhas pesertasemhas = listPeserta.get(getAdapterPosition());
            listener.onItemPesertaClickListener(pesertasemhas);
        }
    }
}
