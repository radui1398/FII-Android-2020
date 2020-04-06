package com.example.lab1;

import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import java.io.IOException;
import java.util.Locale;

public class MyLocationListener implements LocationListener {

    String latitude;
    String longitude;

    @Override
    public void onLocationChanged(Location loc) {

        latitude = "Latitude: " + loc.getLatitude();
        longitude = "Longitude: " + loc.getLongitude();

    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}