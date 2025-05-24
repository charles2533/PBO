package com.example.pertemuan_13;

public class Mahasiswa {
    private String nrp;
    private String nama;

    public Mahasiswa(String nrp, String nama) {
        this.nrp = nrp;
        this.nama = nama;
    }
    public String getNrp() {
        return nrp;
    }

    public String getNama() {
        return nama;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}


