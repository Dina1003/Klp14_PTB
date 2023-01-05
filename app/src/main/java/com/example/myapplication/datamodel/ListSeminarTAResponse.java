package com.example.myapplication.datamodel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListSeminarTAResponse{

    @SerializedName("seminars")
    private List<Object> seminars;

    @SerializedName("count")
    private int count;

    @SerializedName("status")
    private String status;

    public List<Object> getSeminars(){
        return seminars;
    }

    public int getCount(){
        return count;
    }

    public String getStatus(){
        return status;
    }
}