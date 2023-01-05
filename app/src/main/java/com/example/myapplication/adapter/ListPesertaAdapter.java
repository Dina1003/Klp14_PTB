package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.datamodel.AudiencesItem;

import java.util.ArrayList;
import java.util.List;

public class ListPesertaAdapter extends RecyclerView.Adapter<ListPesertaAdapter.ViewHolder> {




    private List<AudiencesItem> itemList = new ArrayList<>();

    public void setItemList(List<AudiencesItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.peserta_seminar,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AudiencesItem audiences = itemList.get(position);
        holder.namePeserta.setText(audiences.getName());
        holder.nimPeserta.setText(audiences.getNim());
        holder.phonePeserta.setText(audiences.getPhone());
        holder.department_idPeserta.setText(audiences.getNik());
        holder.textViewbirthplace.setText(audiences.getBirthplace());


        Glide.with(holder.imageViewphoto).load(audiences.getPhoto()).into(holder.imageViewphoto);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView namePeserta;
        public TextView nimPeserta;
        public TextView phonePeserta;
        public TextView department_idPeserta;
        public TextView textViewbirthplace;
        public ImageView imageViewphoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namePeserta = itemView.findViewById(R.id.namePeserta);
            nimPeserta = itemView.findViewById(R.id.nimPeserta);
            phonePeserta = itemView.findViewById(R.id.phonePeserta);
            department_idPeserta = itemView.findViewById(R.id.department_idPeserta);
            textViewbirthplace = itemView.findViewById(R.id.textViewbirthplace);
            imageViewphoto = itemView.findViewById(R.id.imageViewphoto);
        }
    }
}
