package com.example.myapplication.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.datamodel.LogbooksItem;
import com.example.myapplication.models.logbook;

import java.util.ArrayList;
import java.util.List;

public class logbookAdapter extends RecyclerView.Adapter<logbookAdapter.logbookVH> {

    //  ArrayList<logbook> listLogbook = new ArrayList<>() ;
    private List<LogbooksItem> listLogbook = new ArrayList<>();
    public void setListLogbook(List<LogbooksItem> listLogbook) {
        this.listLogbook = listLogbook;
        notifyDataSetChanged();
    }

    public class logbookVH extends RecyclerView.ViewHolder {
        public ImageView image_book;
        public TextView label_supervised, label_problem, label_progres, text_supervised, text_progres, text_problem, text_tanggal;

        public logbookVH(View itemView) {
            super(itemView);
            image_book = itemView.findViewById(R.id.image_book);
            text_tanggal = itemView.findViewById(R.id.text_tanggal);
            text_progres = itemView.findViewById(R.id.text_progres);
            text_problem = itemView.findViewById(R.id.text_problem);
            text_supervised = itemView.findViewById(R.id.text_supervised);
            label_supervised = itemView.findViewById(R.id.label_supervised);
            label_progres = itemView.findViewById(R.id.label_progres);
            label_problem = itemView.findViewById(R.id.label_problem);


        }
    }

//    itemLogbookClickListener listener;
//
//    public logbookAdapter(ArrayList<logbook> listLogbook) {
//        this.listLogbook = listLogbook;
//    }
//    public void setListener(itemLogbookClickListener listener) {
//        this.listener = listener;
//    }
//
//    public void setListLogbook(ArrayList<logbook> listLogbook) {
//        this.listLogbook = listLogbook;
//    }

    @NonNull
    @Override
    public logbookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_logbook, parent, false);
        return new logbookVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull logbookVH holder, int position) {
        LogbooksItem listlb = listLogbook.get(position);
        holder.text_supervised.setText(String.valueOf(listlb.getSupervisorId()));
        holder.text_tanggal.setText(listlb.getDate());
        holder.text_problem.setText(listlb.getProblem());
        holder.text_progres.setText(listlb.getProgress());
        Glide.with(holder.itemView)
                .load(R.drawable.ic_book)
                .into(holder.image_book);
//        logbook logbook = listLogbook.get(position);
//        holder.text_tanggal.setText(logbook.getTanggal());
//        holder.text_jud ulKegiatan.setText(logbook.getJudulAgenda());
//        holder.image_book.setImageResource(R.drawable.ic_book);
    }

    @Override
    public int getItemCount() {
        return listLogbook.size();
    }


}
