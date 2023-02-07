package com.sammiegh.femcare.model;

public class Note {
    private String date;
    private int diastolic;
    private float fianchi;
    private int gommo;
    private float height;
    private int id;
    private int intimate;
    private String moods;
    private String mucus;
    private String notes;
    private int numorgasm;
    private int ovulationtest;
    private String pill;
    private int pressure;
    private String secretnotes;
    private float seno;
    private int sextimes;
    private String symptoms;
    private int systolic;
    private float temperature;
    private int testgravidanza;
    private int uid;
    private float vita;
    private float weight;

    public Note() {
    }

    public Note(int id2, int uid2, String date2, String notes2, String secretnotes2, String symptoms2, String mucus2, String moods2, int intimate2, int gommo2, int sextimes2, int numorgasm2, String pill2, int ovulationtest2, float temperature2, float weight2, float height2, float seno2, float vita2, float fianchi2, int systolic2, int diastolic2, int pressure2, int testgravidanza2) {
        this.id = id2;
        this.uid = uid2;
        this.date = date2;
        this.notes = notes2;
        this.secretnotes = secretnotes2;
        this.symptoms = symptoms2;
        this.mucus = mucus2;
        this.moods = moods2;
        this.intimate = intimate2;
        this.gommo = gommo2;
        this.sextimes = sextimes2;
        this.numorgasm = numorgasm2;
        this.pill = pill2;
        this.ovulationtest = ovulationtest2;
        this.temperature = temperature2;
        this.weight = weight2;
        this.height = height2;
        this.seno = seno2;
        this.vita = vita2;
        this.fianchi = fianchi2;
        this.systolic = systolic2;
        this.diastolic = diastolic2;
        this.pressure = pressure2;
        this.testgravidanza = testgravidanza2;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date2) {
        this.date = date2;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes2) {
        this.notes = notes2;
    }

    public String getSecretNotes() {
        return this.secretnotes;
    }

    public void setSecretNotes(String secretnotes2) {
        this.secretnotes = secretnotes2;
    }

    public String getSymptoms() {
        return this.symptoms;
    }

    public void setSymptoms(String symptoms2) {
        this.symptoms = symptoms2;
    }

    public String getMucus() {
        return this.mucus;
    }

    public void setMucus(String mucus2) {
        this.mucus = mucus2;
    }

    public String getMoods() {
        return this.moods;
    }

    public void setMoods(String moods2) {
        this.moods = moods2;
    }

    public int getIntimate() {
        return this.intimate;
    }

    public void setIntimate(int intimate2) {
        this.intimate = intimate2;
    }

    public int getGommo() {
        return this.gommo;
    }

    public void setGommo(int gommo2) {
        this.gommo = gommo2;
    }

    public int getSextimes() {
        return this.sextimes;
    }

    public void setSextimes(int sextimes2) {
        this.sextimes = sextimes2;
    }

    public int getNumorgasm() {
        return this.numorgasm;
    }

    public void setNumorgasm(int numorgasm2) {
        this.numorgasm = numorgasm2;
    }

    public String getPill() {
        return this.pill;
    }

    public void setPill(String pill2) {
        this.pill = pill2;
    }

    public int getOvulationtest() {
        return this.ovulationtest;
    }

    public void setOvulationtest(int ovulationtest2) {
        this.ovulationtest = ovulationtest2;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float temperature2) {
        this.temperature = temperature2;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight2) {
        this.weight = weight2;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height2) {
        this.height = height2;
    }

    public float getSeno() {
        return this.seno;
    }

    public void setSeno(float seno2) {
        this.seno = seno2;
    }

    public float getVita() {
        return this.vita;
    }

    public void setVita(float vita2) {
        this.vita = vita2;
    }

    public float getFianchi() {
        return this.fianchi;
    }

    public void setFianchi(float fianchi2) {
        this.fianchi = fianchi2;
    }

    public int getSystolic() {
        return this.systolic;
    }

    public void setSystolic(int systolic2) {
        this.systolic = systolic2;
    }

    public int getDiastolic() {
        return this.diastolic;
    }

    public void setDiastolic(int diastolic2) {
        this.diastolic = diastolic2;
    }

    public int getPressure() {
        return this.pressure;
    }

    public void setPressure(int pressure2) {
        this.pressure = pressure2;
    }

    public int getTestgravidanza() {
        return this.testgravidanza;
    }

    public void setTestgravidanza(int testgravidanza2) {
        this.testgravidanza = testgravidanza2;
    }

    public String toString() {
        return "Note [id=" + this.id + ", uid=" + this.uid + ", date=" + this.date + ", notes=" + this.notes + ", secretnotes=" + this.secretnotes + ", symptoms=" + this.symptoms + ", mucus=" + this.mucus + ", moods=" + this.moods + ", intimate=" + this.intimate + ", gommo=" + this.gommo + ", sextimes=" + this.sextimes + ", numorgasm=" + this.numorgasm + ", pill=" + this.pill + ", ovulationtest=" + this.ovulationtest + ", temperature=" + this.temperature + ", weight=" + this.weight + ", height=" + this.height + ", seno=" + this.seno + ", vita=" + this.vita + ", fianchi=" + this.fianchi + ", systolic=" + this.systolic + ", diastolic=" + this.diastolic + ", pressure=" + this.pressure + ", testgravidanza=" + this.testgravidanza + "]";
    }
}
