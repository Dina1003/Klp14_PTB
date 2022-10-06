package com.example.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DetailSidang;
import com.example.myapplication.R;
import com.example.myapplication.models.jadwal;

import java.util.ArrayList;

public class jadwalAdapter extends RecyclerView.Adapter<jadwalAdapter.jadwalViewHolder> {

    //2. tambah properti dengan tipe interface dalam kelas adapter
    ItemJadwalClickListener listener;

    ArrayList<jadwal> listjadwal = new ArrayList<>();
    public jadwalAdapter(ArrayList<jadwal> listjadwal) {
        this.listjadwal = listjadwal;
    }


    public void setListjadwal(ArrayList<jadwal> listjadwal) {
        this.listjadwal = listjadwal;
        notifyDataSetChanged();
    }
//3. set object listener
    public void setListener(ItemJadwalClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public jadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home, parent,false);
        return new jadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull jadwalViewHolder holder, int position) {

        jadwal jadwal = listjadwal.get(position);
        holder.textnama.setText(jadwal.getNama());
        holder.textjadwaltype.setText(Integer.toString(jadwal.getTipejadwal()));
        if(jadwal.getTipejadwal() ==1 ){
            holder.textjadwaltype.setText("Sidang");
        }else{
            holder.textjadwaltype.setText("Seminar");
        }
        holder.texttanggal.setText(jadwal.getTanggal());
        holder.textwaktu.setText(jadwal.getWaktu());
        holder.texttempat.setText(jadwal.getTempat());

    }

    @Override
    public int getItemCount() {

        return listjadwal.size();
    }

    //1. buat interface dengan method onlick dalam adapter
    public interface ItemJadwalClickListener{
        void OnItemJadwalClick(jadwal jadwal);
    }

    //4. implementasi interface view.Onclicklistener pada View Holder
    public class jadwalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  textnama ,textwaktu, texttempat,texttanggal, textjadwaltype;

        public jadwalViewHolder(@NonNull View itemView) {
            super(itemView);
            textjadwaltype=itemView.findViewById(R.id.textjadwaltype);
            textnama = itemView.findViewById(R.id.namasidsem);
            texttanggal = itemView.findViewById(R.id. tanggalsidsem);
            textwaktu = itemView.findViewById(R.id.jamsidsem);
            texttempat = itemView.findViewById(R.id. tempatsidsem);

            //6 tambahkan object viewholder sebagai listener
            itemView.setOnClickListener(this);


        }

        //5. override method oncclik yang ada dalam interface
        @Override
        public void onClick(View view) {
            jadwal jadwal = listjadwal.get((getAdapterPosition()));
            listener.OnItemJadwalClick(jadwal);
        }
    }


}
