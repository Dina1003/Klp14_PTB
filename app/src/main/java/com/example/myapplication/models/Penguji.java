package com.example.myapplication.models;

public class Penguji {
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    String nama, nip;


    public Penguji() {

    }

    public Penguji(String nama, String nip) {
        this.nama = nama;
        this.nip = nip;
    }
}
