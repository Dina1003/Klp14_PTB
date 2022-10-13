package com.example.myapplication.models;

import java.util.Date;

public class logbook {
    String gambar;
    String tanggal;
    String judulAgenda;
    int status;

    public logbook(){

    }

    public logbook(String gambar, String tanggal, String judulAgenda, int status) {
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.judulAgenda = judulAgenda;
        this.status = status;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudulAgenda() {
        return judulAgenda;
    }

    public void setJudulAgenda(String judulAgenda) {
        this.judulAgenda = judulAgenda;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

