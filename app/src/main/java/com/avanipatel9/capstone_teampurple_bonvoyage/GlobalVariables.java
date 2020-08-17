package com.avanipatel9.capstone_teampurple_bonvoyage;

import android.app.Application;

public class GlobalVariables extends Application {
    private String phoneNumber;
    private String tripid;
    public String getData(){
        return phoneNumber;
    }

    public void setData(String phNumber){
        phoneNumber = phNumber;
    }

    public String getTripid(){
        return tripid;
    }

    public void setTripid(String trip){
        tripid = trip;
    }
}
