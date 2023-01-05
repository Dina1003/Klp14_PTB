package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.datamodel.LogbooksItem;
import com.example.myapplication.datamodel.RVResponse;
import com.example.myapplication.models.Sidang;

import java.util.ArrayList;
import java.util.List;

public class SidangAdapter extends RecyclerView.Adapter<SidangAdapter.SidangViewHolder> {

    //codingan Sidang RV baru ke api



    //1.deklarasi objek
    //private List<Object> seminars = new ArrayList<>();
    private List<LogbooksItem> itemList = new ArrayList<>();




    //2. buat konstruktor
    public SidangAdapter() {

    }
    /*public SidangAdapter(List<Object> seminars) {
        this.seminars = seminars;
    }*/

    public SidangAdapter(List<LogbooksItem> itemList){
        this.itemList = itemList;
    }


    //3. buat setter
    /*public void setSeminars(List<Object> seminars) {
        this.seminars = seminars;
    }*/

    public void setItemList(List<LogbooksItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }



    @NonNull
    //5. modif oncreatnya
    @Override
    public SidangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sidang, parent,false);
        return new SidangViewHolder(view);
    }

    @Override
    //6. Modif onbind
    public void onBindViewHolder(@NonNull SidangViewHolder holder, int position) {
        //ambil story yang mau ditampilkan

        //RVResponse rvResponse = (RVResponse) seminars.get(position);
        //holder.textwaktu.setText((CharSequence) rvResponse.getSeminars());

        //tes
        LogbooksItem logbooks = itemList.get(position);
        holder.textwaktu.setText(logbooks.getDate());

    }

    @Override
    //4. modif sizenya
    public int getItemCount() {
        //return seminars.size();
        //return listSidang.size();

        //tes
        return itemList.size();
    }

    public  interface ItemSidangCLickListener{
        //void onItemSidangClickListener(Sidang sidang);
    }

    /*public void setListSidang(ArrayList<Sidang> listSidang) {
        //this.listSidang = listSidang;
    }*/

    //7.  modif
    public class SidangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textwaktu;

        public SidangViewHolder(@NonNull View itemView) {
            super(itemView);

            textwaktu = itemView.findViewById(R.id.waktu);

            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*Sidang sidang = listSidang.get(getAdapterPosition());
            listener.onItemSidangClickListener(sidang);*/
        }
    }
}
