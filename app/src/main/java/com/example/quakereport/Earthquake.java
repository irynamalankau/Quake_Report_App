package com.example.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTime;

    public Earthquake(double  magnitude, String location, long time) {
        mMagnitude = magnitude;
        mLocation = location;
        mTime = time;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTime;
    }

}


