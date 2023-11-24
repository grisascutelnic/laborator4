package com.example.medicaldatabase;

public class Programari {
    private int idProgramare;
    private int idPacient;
    private int idMedic;
    private String dataOra;
    private String motiv;
    private String note;

    public Programari(int idProgramare, int idPacient, int idMedic, String dataOra, String motiv, String note) {
        this.idProgramare = idProgramare;
        this.idPacient = idPacient;
        this.idMedic = idMedic;
        this.dataOra = dataOra;
        this.motiv = motiv;
        this.note = note;
    }


    public void setIdProgramare(int idProgramare) {
        this.idProgramare = idProgramare;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public void setDataOra(String dataOra) {
        this.dataOra = dataOra;
    }

    public void setMotiv(String motiv) {
        this.motiv = motiv;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIdProgramare() {
        return this.idProgramare;
    }

    public int getIdPacient() {
        return this.idPacient;
    }

    public int getIdMedic() {
        return this.idMedic;
    }

    public String getDataOra() {
        return this.dataOra;
    }

    public String getMotiv() {
        return this.motiv;
    }

    public String getNote() {
        return this.note;
    }
}
