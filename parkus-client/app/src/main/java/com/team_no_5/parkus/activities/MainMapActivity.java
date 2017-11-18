package com.team_no_5.parkus.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team_no_5.parkus.R;
import com.team_no_5.parkus.Utilities.Locations;
import com.team_no_5.parkus.adapters.PointInfoAdapter;
import com.team_no_5.parkus.networking.ParkingPointsNetworking;
import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButtonAdd;

    private GoogleMap map;

    private List<ParkingPoint> parkingPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setInfoWindowAdapter(new PointInfoAdapter(this, getLayoutInflater()));

        map.setOnMarkerClickListener(marker -> {
            floatingActionButtonAdd.hide();

            return false;
        });

        map.setOnMapClickListener(latLng -> {
            floatingActionButtonAdd.show();
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);

        ParkingPointsNetworking parkingPointsNetworking = new ParkingPointsNetworking(this);
        parkingPointsNetworking.loadParkingPoints(
                () -> {
                    parkingPoints = parkingPointsNetworking.getParkingPoints();

                    for (ParkingPoint p : parkingPoints) {
                        LatLng coords = new LatLng(p.getLatitude(), p.getLongitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(coords)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_point)));
                        marker.setTag(p);
                        map.moveCamera(CameraUpdateFactory.newLatLng(coords));
                    }

                    return null;
                },
                () -> {
                    return null;
                });

    }

    @OnClick(R.id.floatingActionButton)
    void onFloatingActionButtonAddNewPointClick() {
        Intent intent = new Intent(this, AddNewParkingPointActivity.class);
        startActivity(intent);
    }
}
