package com.example.pinpals;

import android.util.Log;

public class PinPal {
    String time;
    int reg;
    double latitude, longitude;

    public PinPal() {
    }

    public PinPal(int reg,String time, double latitude, double longitude) {
        this.reg = reg;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public int getReg() {
        return reg;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        Log.i("Object PinPal", "The latest time is : " + time);
        return time;
    }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double[] getLocation() {
        double[] latlng = new double[2];
        latlng[0] = latitude;
        latlng[1] = longitude;
        return latlng;
    }
}
