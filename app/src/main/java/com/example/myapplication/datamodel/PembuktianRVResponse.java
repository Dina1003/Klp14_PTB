package com.example.myapplication.datamodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PembuktianRVResponse{

    @SerializedName("count")
    private int count;

    @SerializedName("logbooks")
    private List<LogbooksItem> logbooks;

    @SerializedName("status")
    private String status;

    public int getCount(){
        return count;
    }

    public List<LogbooksItem> getLogbooks(){
        return logbooks;
    }

    public String getStatus(){
        return status;
    }
}