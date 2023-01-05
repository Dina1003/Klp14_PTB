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
import com.example.myapplication.datamodel.ListSeminarTAResponse;
import com.example.myapplication.datamodel.PesertaItem;
import com.example.myapplication.datamodel.SeminarsItem;

import java.util.ArrayList;
import java.util.List;

public class ListSeminarAdapter extends RecyclerView.Adapter<ListSeminarAdapter.ListSeminarViewholder> {

    private List<SeminarsItem> seminarsItems = new ArrayList<>();

    public void setSeminarsItems(List<SeminarsItem> seminarsItems) {
        this.seminarsItems = seminarsItems;
        notifyDataSetChanged();
    }

    ItemSeminarClickListener listener;

    public void setListener(ItemSeminarClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListSeminarAdapter.ListSeminarViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_seminar,parent,false);

        return new ListSeminarAdapter.ListSeminarViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSeminarAdapter.ListSeminarViewholder holder, int position) {

        SeminarsItem seminarsItem = seminarsItems.get(position);
        holder.keterangan.setText(seminarsItem.getSeminarAt());
        holder.tanggalselesai.setText(seminarsItem.getRegisteredAt());


    }

    @Override
    public int getItemCount() {
        return seminarsItems.size();
    }

    public interface ItemSeminarClickListener{
        void onItemSeminarClick(SeminarsItem seminarsItem);
    }


    public class ListSeminarViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView keterangan;
        public TextView tanggalselesai;




        public ListSeminarViewholder(@NonNull View itemView) {
            super(itemView);

            keterangan = itemView.findViewById(R.id.keterangan);
            tanggalselesai = itemView.findViewById(R.id.tanggalselesai);

            keterangan.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            SeminarsItem seminarsItem = seminarsItems.get(getAdapterPosition());
            listener.onItemSeminarClick(seminarsItem);
        }
    }
}