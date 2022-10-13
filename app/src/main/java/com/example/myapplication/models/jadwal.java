package com.example.myapplication.models;

public class jadwal {

    String nama;
    int tipejadwal;
    String tanggal;
    String waktu;
    String tempat;

    public jadwal(){}

    public jadwal(String nama, int tipejadwal, String waktu, String tempat, String tanggal) {
        this.nama = nama;
        this.tipejadwal = tipejadwal;
        this.waktu = waktu;
        this.tempat = tempat;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getTipejadwal() {
        return tipejadwal;
    }

    public void setTipejadwal(int tipejadwal) {
        this.tipejadwal = tipejadwal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
