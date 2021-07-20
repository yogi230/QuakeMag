package com.example.android.quakereport;

public class Item {
    private String mloc;
    private long milisec;
    private double mmag;
    private String murl;

    public Item(double mag, String loc, long date, String url) {
        mmag = mag;
        mloc = loc;
        milisec = date;
        murl = url;
    }

    public double getMagnitude() {
        return mmag;
    }

    public String getUrl() {
        return murl;
    }

    public String getLoc() {
        return mloc;
    }

    public long getDate() {
        return milisec;
    }
}
