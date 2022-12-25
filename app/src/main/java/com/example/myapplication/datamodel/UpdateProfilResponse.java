package com.example.myapplication.datamodel;

import com.google.gson.annotations.SerializedName;

public class UpdateProfilResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}
