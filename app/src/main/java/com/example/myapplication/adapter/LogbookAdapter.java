package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.logbook;

import java.util.ArrayList;

public class LogbookAdapter extends RecyclerView.Adapter<LogbookAdapter.logbookVH> {

    ArrayList<logbook> listLogbook = new ArrayList<>() ;

    itemLogbookClickListener listener;

    public LogbookAdapter(ArrayList<logbook> listLogbook) {
        this.listLogbook = listLogbook;
    }
    public void setListener(itemLogbookClickListener listener) {
        this.listener = listener;
    }

    public void setListLogbook(ArrayList<logbook> listLogbook) {
        this.listLogbook = listLogbook;
    }

    @NonNull
    @Override
    public logbookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_logbook, parent, false);
        return new logbookVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull logbookVH holder, int position) {
        logbook logbook = listLogbook.get(position);
        holder.text_tanggal.setText(logbook.getTanggal());
        holder.text_judulKegiatan.setText(logbook.getJudulAgenda());
        holder.image_book.setImageResource(R.drawable.ic_book);
    }

    @Override
    public int getItemCount() {
        return listLogbook.size();
    }

    public interface itemLogbookClickListener{
        void onItemLogbookClick(logbook logbook);
    }

    public class logbookVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView image_book;
        public TextView text_tanggal,text_judulKegiatan;

        public logbookVH(@NonNull View itemView) {
            super(itemView);
            image_book = itemView.findViewById(R.id.image_book);
            text_tanggal = itemView.findViewById(R.id.text_tanggal);
            text_judulKegiatan = itemView.findViewById(R.id.text_judulKegiatan);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            logbook logbook = listLogbook.get(getAdapterPosition());
            listener.onItemLogbookClick(logbook);
        }
    }
}
