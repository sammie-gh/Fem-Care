package com.sammiegh.femcare.model;

public class Notifications {
    private int custom;
    private String dateentry;
    private int frequency;
    private int id;
    private int idnotifications;
    private String name;
    private int timehour;
    private int timemin;
    private int type;
    private int uid;
    private int used;

    public Notifications(int id2, int idnotifications2, int type2, int uid2, String name2, int used2, int custom2, int frequency2, int timehour2, int timemin2, String dateentry2) {
        this.id = id2;
        this.idnotifications = idnotifications2;
        this.type = type2;
        this.uid = uid2;
        this.name = name2;
        this.used = used2;
        this.custom = custom2;
        this.frequency = frequency2;
        this.timehour = timehour2;
        this.timemin = timemin2;
        this.dateentry = dateentry2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id2) {
        this.id = id2;
    }

    public int getIdnotifications() {
        return this.idnotifications;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid2) {
        this.uid = uid2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public int getUsed() {
        return this.used;
    }

    public void setUsed(int used2) {
        this.used = used2;
    }

    public int getCustom() {
        return this.custom;
    }

    public void setCustom(int custom2) {
        this.custom = custom2;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public int getTimeHour() {
        return this.timehour;
    }

    public int getTimeMin() {
        return this.timemin;
    }

    public String getDateEntry() {
        return this.dateentry;
    }

    public String toString() {
        return "Pills [id=" + this.id + ", idnotifications=" + this.idnotifications + ", uid=" + this.uid + ", name=" + this.name + ", used=" + this.used + ", frequency=" + this.frequency + ", timehour=" + this.timehour + ", timemin=" + this.timemin + ", custom=" + this.custom + "]";
    }
}
