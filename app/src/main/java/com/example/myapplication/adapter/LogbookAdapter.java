package com.example.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.Logbook;
import com.example.myapplication.models.jadwal;

import java.util.ArrayList;

public class LogbookAdapter extends RecyclerView.Adapter<LogbookAdapter.LogbookViewHolder>{

    ArrayList<Logbook> listLogbook = new ArrayList<>();
    public LogbookAdapter(ArrayList<Logbook> listlogbook) {
        this.listLogbook = listLogbook;
    }


    public void setListLogbook(ArrayList<Logbook> listLogbook) {
        this.listLogbook = listLogbook;
    }

    @NonNull
    @Override
    public LogbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.logbook, parent,false);
        return new LogbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogbookViewHolder holder, int position) {
        Logbook Logbook = listLogbook.get(position);
        holder.textketerangan.setText(Integer.toString(Logbook.getKeterangan()));
        if(Logbook.getKeterangan() ==1 ){
            holder.textketerangan.setText("Selesai");
        }else{
            holder.textketerangan.setText("Belum Selesai");
        }

    }

    @Override
    public int getItemCount() {

        return listLogbook.size();
    }

    public class LogbookViewHolder extends RecyclerView.ViewHolder {

        public TextView textketerangan, texttanggalselesai;
        public LogbookViewHolder(@NonNull View itemView) {
            super(itemView);
            textketerangan=itemView.findViewById(R.id.keterangan);
            texttanggalselesai = itemView.findViewById(R.id.tanggalselesai);
        }
    }
}
