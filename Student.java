package com.example.medicaldatabase;

public class Student {
    private int id;
    private String nume;
    private String prenume;
    public Student(int id, String nume, String prenume) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
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
    public int getId() {
        return this.id;
    }
    public String getNume() {
        return this.nume;
    }
    public String getPrenume() {
        return this.prenume;
    }
}