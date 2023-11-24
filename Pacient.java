package com.example.medicaldatabase;

public class Pacient {
    private int id;
    private String idnp;
    private String nume;
    private String prenume;
    private String dataNasterii;
    private String sex;
    private String adresa;
    private String telefon;
    private String grupaDeSange;
    private String alergii;

    public Pacient(int id, String idnp, String nume, String prenume, String dataNasterii, String sex,
                   String adresa, String telefon, String grupaDeSange, String alergii) {
        this.id = id;
        this.idnp = idnp;
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.sex = sex;
        this.adresa = adresa;
        this.telefon = telefon;
        this.grupaDeSange = grupaDeSange;
        this.alergii = alergii;
    }

    public int getId() {
        return this.id;
    }

    public String getIdnp() {
        return this.idnp;
    }

    public String getNume() {
        return this.nume;
    }

    public String getPrenume() {
        return this.prenume;
    }

    public String getDataNasterii() {
        return this.dataNasterii;
    }

    public String getSex() {
        return this.sex;
    }

    public String getAdresa() {
        return this.adresa;
    }

    public String getTelefon() {
        return this.telefon;
    }

    public String getGrupaDeSange() {
        return this.grupaDeSange;
    }

    public String getAlergii() {
        return this.alergii;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setGrupaDeSange(String grupaDeSange) {
        this.grupaDeSange = grupaDeSange;
    }

    public void setAlergii(String alergii) {
        this.alergii = alergii;
    }
}
