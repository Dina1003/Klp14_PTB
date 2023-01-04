package com.example.myapplication.datamodel;

import java.util.List;

public class RVResponse{
    private List<Object> seminars;
    private int count;
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