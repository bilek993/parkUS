package com.team_no_5.parkus.Utilities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Michal on 18.11.2017.
 */

public class Locations {
    public static final int ACCESS_FINE_LOCATION_REQUEST_CODE = 0;

    public static final double LOCATION_MARGIN = 0.00005;

    public static double calculateDifference(LatLng latLng1, LatLng latLng2) {
        return Math.sqrt((latLng1.latitude - latLng2.latitude) * (latLng1.latitude - latLng2.latitude) +
                (latLng1.longitude - latLng2.longitude) * (latLng1.longitude - latLng2.longitude));
    }

    public static boolean requestLocationPermission(AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    ACCESS_FINE_LOCATION_REQUEST_CODE);

            return false;
        }
    }
}
