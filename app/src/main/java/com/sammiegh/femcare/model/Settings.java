package com.sammiegh.femcare.model;

public class Settings {
    private int id;
    private String key;
    private int uid;
    private String value;

    public Settings() {
    }

    public Settings(int id2, int uid2, String key2, String value2) {
        this.id = id2;
        this.uid = uid2;
        this.key = key2;
        this.value = value2;
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

    public String getKey() {
        return this.key;
    }

    public void setKey(String key2) {
        this.key = key2;
    }

    public String getValueKey() {
        return this.value;
    }

    public void setValueKey(String value2) {
        this.value = value2;
    }

    public String toString() {
        return "Settings [id=" + this.id + ", uid=" + this.uid + ", key=" + this.key + ", value=" + this.value + "]";
    }
}
