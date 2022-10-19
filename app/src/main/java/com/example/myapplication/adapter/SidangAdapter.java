package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Sidang;

import java.util.ArrayList;

public class SidangAdapter extends RecyclerView.Adapter<SidangAdapter.SidangViewHolder> {

    ArrayList<Sidang> listSidang = new ArrayList<>();
    ItemSidangCLickListener listener;

    public SidangAdapter(ArrayList<Sidang> listSidang) {
        this.listSidang = listSidang;
    }
    public SidangAdapter(ArrayList<Sidang> listSidang, ItemSidangCLickListener listener) {
        this.listSidang = listSidang;
        this.listener = listener;
    }
    public void setListener(ItemSidangCLickListener listener) {
        this.listener = listener;
    }



    @NonNull
    @Override
    public SidangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sidang, parent, false);
        return new SidangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SidangViewHolder holder, int position) {
        Sidang sidang = listSidang.get(position);
        holder.textwaktu.setText(sidang.getWaktu());

    }

    @Override
    public int getItemCount() {
        return listSidang.size();
    }

    public  interface ItemSidangCLickListener{
        void onItemSidangClickListener(Sidang sidang);
    }

    public void setListSidang(ArrayList<Sidang> listSidang) {
        this.listSidang = listSidang;
    }

    public class SidangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textwaktu;
        public SidangViewHolder(@NonNull View itemView) {
            super(itemView);

            textwaktu = itemView.findViewById(R.id.waktu);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Sidang sidang = listSidang.get(getAdapterPosition());
            listener.onItemSidangClickListener(sidang);
        }
    }
}
