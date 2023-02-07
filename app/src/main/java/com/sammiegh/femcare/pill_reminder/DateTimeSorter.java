package com.sammiegh.femcare.pill_reminder;

public class DateTimeSorter {
    public String mDateTime;
    public int mIndex;

    public DateTimeSorter(int i, String str) {
        this.mIndex = i;
        this.mDateTime = str;
    }

    public DateTimeSorter() {
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setIndex(int i) {
        this.mIndex = i;
    }

    public String getDateTime() {
        return this.mDateTime;
    }

    public void setDateTime(String str) {
        this.mDateTime = str;
    }
}
