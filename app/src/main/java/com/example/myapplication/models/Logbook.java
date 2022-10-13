package com.example.myapplication.models;

public class Logbook {
    String tanggal;
    int keterangan;

    public Logbook() {}

    public Logbook(String tanggal, int keterangan) {
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(int keterangan) {
        this.keterangan = keterangan;
    }
}
