package com.example.medicaldatabase;

public class Medic {
    private int id;
    private String nume;
    private String prenume;
    private String specializare;
    private String telefon;
    private String email;

    public Medic(int id, String nume, String prenume, String specializare, String telefon, String email) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.specializare = specializare;
        this.telefon = telefon;
        this.email = email;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public String getNume() {
        return this.nume;
    }

    public String getPrenume() {
        return this.prenume;
    }

    public String getSpecializare() {
        return this.specializare;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public String getEmail() {
        return this.email;
    }
}
