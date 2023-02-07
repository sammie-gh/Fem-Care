package com.sammiegh.femcare.model;

public class Period {
    private int completo;
    private String date;
    private int daysciclo;
    private int daysmestruazioni;
    private int daysovulation;
    private int id;
    private int pregnancy;
    private int type;
    private int uid;

    public Period() {
    }

    public Period(int id2, int uid2, int type2, String date2, int completo2, int daysmestruazioni2, int daysciclo2, int daysovulation2, int pregnancy2) {
        this.id = id2;
        this.uid = uid2;
        this.type = type2;
        this.date = date2;
        this.completo = completo2;
        this.daysmestruazioni = daysmestruazioni2;
        this.daysciclo = daysciclo2;
        this.daysovulation = daysovulation2;
        this.pregnancy = pregnancy2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid2) {
        this.uid = uid2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date2) {
        this.date = date2;
    }

    public int getCompleto() {
        return this.completo;
    }

    public void setCompleto(int completo2) {
        this.completo = completo2;
    }

    public int getDaysMestruazioni() {
        return this.daysmestruazioni;
    }

    public void setDaysMestruazioni(int daysmestruazioni2) {
        this.daysmestruazioni = daysmestruazioni2;
    }

    public int getDaysCiclo() {
        return this.daysciclo;
    }

    public void setDaysCiclo(int daysciclo2) {
        this.daysciclo = daysciclo2;
    }

    public int getDaysOvulation() {
        return this.daysovulation;
    }

    public void setDaysOvulation(int daysovulation2) {
        this.daysovulation = daysovulation2;
    }

    public int getPregnancy() {
        return this.pregnancy;
    }

    public void setPregnancy(int pregnancy2) {
        this.pregnancy = pregnancy2;
    }

    public String toString() {
        return "Period [id=" + this.id + ", uid=" + this.uid + ", type=" + this.type + ", date=" + this.date + ", completo=" + this.completo + ", daysmestruazioni=" + this.daysmestruazioni + ", daysciclo=" + this.daysciclo + ", daysovulation=" + this.daysovulation + ", pregnancy=" + this.pregnancy + "]";
    }
}
