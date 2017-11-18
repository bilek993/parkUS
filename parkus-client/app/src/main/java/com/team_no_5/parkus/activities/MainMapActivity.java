package com.team_no_5.parkus.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team_no_5.parkus.R;
import com.team_no_5.parkus.Utilities.AdvancedCallable;
import com.team_no_5.parkus.adapters.PointInfoAdapter;
import com.team_no_5.parkus.networking.ParkingPointsNetworking;
import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    private List<ParkingPoint> parkingPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setInfoWindowAdapter(new PointInfoAdapter(getLayoutInflater()));

        ParkingPointsNetworking parkingPointsNetworking = new ParkingPointsNetworking(this);
        parkingPointsNetworking.loadParkingPoints(
                () -> {
                    parkingPoints = parkingPointsNetworking.getParkingPoints();

                    for (ParkingPoint p : parkingPoints) {
                        LatLng coords = new LatLng(p.getLatitude(), p.getLongitude());
                        map.addMarker(new MarkerOptions().position(coords).title("Marker in Sydney"));
                        map.moveCamera(CameraUpdateFactory.newLatLng(coords));
                    }

                    return null;
                },
                () -> {
                    return null;
                });

    }
}
