package com.sammiegh.femcare.pill_reminder;

public class Reminder {
    private String mActive;
    private String mDate;
    private int mID;
    private String mRepeat;
    private String mRepeatNo;
    private String mRepeatType;
    private String mTime;
    private String mTitle;

    public Reminder(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mID = i;
        this.mTitle = str;
        this.mDate = str2;
        this.mTime = str3;
        this.mRepeat = str4;
        this.mRepeatNo = str5;
        this.mRepeatType = str6;
        this.mActive = str7;
    }

    public Reminder(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mTitle = str;
        this.mDate = str2;
        this.mTime = str3;
        this.mRepeat = str4;
        this.mRepeatNo = str5;
        this.mRepeatType = str6;
        this.mActive = str7;
    }

    public Reminder() {
    }

    public int getID() {
        return this.mID;
    }

    public void setID(int i) {
        this.mID = i;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getDate() {
        return this.mDate;
    }

    public void setDate(String str) {
        this.mDate = str;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setTime(String str) {
        this.mTime = str;
    }

    public String getRepeatType() {
        return this.mRepeatType;
    }

    public void setRepeatType(String str) {
        this.mRepeatType = str;
    }

    public String getRepeatNo() {
        return this.mRepeatNo;
    }

    public void setRepeatNo(String str) {
        this.mRepeatNo = str;
    }

    public String getRepeat() {
        return this.mRepeat;
    }

    public void setRepeat(String str) {
        this.mRepeat = str;
    }

    public String getActive() {
        return this.mActive;
    }

    public void setActive(String str) {
        this.mActive = str;
    }
}
