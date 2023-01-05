package com.example.myapplication.datamodel;

import com.google.gson.annotations.SerializedName;

public class PesertaItem{

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("student_id")
    private int studentId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("thesis_seminar_id")
    private int thesisSeminarId;

    public String getUpdatedAt(){
        return updatedAt;
    }

    public int getStudentId(){
        return studentId;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public int getId(){
        return id;
    }

    public int getThesisSeminarId(){
        return thesisSeminarId;
    }
}