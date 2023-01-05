package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.datamodel.ExaminersItem;
import com.example.myapplication.datamodel.LogbooksItem;
import com.example.myapplication.datamodel.RVResponse;
import com.example.myapplication.models.Sidang;

import java.util.ArrayList;
import java.util.List;

public class PengujiAdapter extends RecyclerView.Adapter<PengujiAdapter.PengujiViewHolder> {

    //codingan Sidang RV baru ke api
    //bagian dina

    //1.deklarasi objek
    //private List<Object> seminars = new ArrayList<>();
    private List<ExaminersItem> itemList = new ArrayList<>();




    //2. buat konstruktor
    public PengujiAdapter() {

    }
    /*public SidangAdapter(List<Object> seminars) {
        this.seminars = seminars;
    }*/

    public PengujiAdapter(List<ExaminersItem> itemList){
        this.itemList = itemList;
    }


    //3. buat setter
    /*public void setSeminars(List<Object> seminars) {
        this.seminars = seminars;
    }*/

    public void setItemList(List<ExaminersItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }



    @NonNull
    //5. modif oncreatnya
    @Override
    public PengujiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sidang1, parent,false);
        return new PengujiViewHolder(view);
    }

    @Override
    //6. Modif onbind
    public void onBindViewHolder(@NonNull PengujiViewHolder holder, int position) {
        //ambil story yang mau ditampilkan

        //RVResponse rvResponse = (RVResponse) seminars.get(position);
        //holder.textwaktu.setText((CharSequence) rvResponse.getSeminars());

        //tes
        ExaminersItem logbooks = itemList.get(position);
        holder.nama.setText(logbooks.getName());
        holder.nip.setText(logbooks.getNip());

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
    public class PengujiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nama,nip;

        public PengujiViewHolder(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.nama);
            nip = itemView.findViewById(R.id.nip);

            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*Sidang sidang = listSidang.get(getAdapterPosition());
            listener.onItemSidangClickListener(sidang);*/
        }
    }
}
