package com.avanipatel9.capstone_teampurple_bonvoyage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MapActivity extends BaseMapFragment implements GoogleMap.OnMarkerClickListener {


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}