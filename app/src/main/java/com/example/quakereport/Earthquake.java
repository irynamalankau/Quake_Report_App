package com.example.quakereport;

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private long mTime;

    public Earthquake(String  magnitude, String location, long time) {
        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTime;
    }

}


