package com.example.myapplication.models;

public class Agenda1 {
    String tanggal;
    String kegiatan;

    public  Agenda1(){

    }

    public Agenda1(String tanggal, String kegiatan) {
        this.tanggal = tanggal;
        this.kegiatan = kegiatan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }
}
