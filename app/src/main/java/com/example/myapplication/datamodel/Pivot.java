package com.example.myapplication.datamodel;

public class Pivot{
    private Object notes;
    private int reviewerId;
    private Object recomendation;
    private int id;
    private Object position;
    private int thesisSeminarId;

    public Object getNotes(){
        return notes;
    }

    public int getReviewerId(){
        return reviewerId;
    }

    public Object getRecomendation(){
        return recomendation;
    }

    public int getId(){
        return id;
    }

    public Object getPosition(){
        return position;
    }

    public int getThesisSeminarId(){
        return thesisSeminarId;
    }
}
