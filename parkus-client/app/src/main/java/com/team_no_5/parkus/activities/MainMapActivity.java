package com.team_no_5.parkus.activities;

import android.location.Location;
import android.location.LocationListener;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team_no_5.parkus.R;
import com.team_no_5.parkus.Utilities.AdvancedCallable;
import com.team_no_5.parkus.Utilities.Locations;
import com.team_no_5.parkus.adapters.PointInfoAdapter;
import com.team_no_5.parkus.networking.ParkingPointsNetworking;
import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;

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

        ParkingPointsNetworking parkingPointsNetworking = new ParkingPointsNetworking(this);
        parkingPointsNetworking.loadParkingPoints(
                () -> {
                    parkingPoints = parkingPointsNetworking.getParkingPoints();

                    Locations.getCurrentLoaction(this, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            ParkingPoint p = new ParkingPoint();
                            p.setLatitude(location.getLatitude());
                            p.setLongitude(location.getLatitude());

                            parkingPoints.add(p);

                            LatLng coords = new LatLng(p.getLatitude(), p.getLongitude());
                            Marker marker = map.addMarker(new MarkerOptions().position(coords));
                            marker.setTag(p);
                        }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }
                    });

                    for (ParkingPoint p : parkingPoints) {
                        LatLng coords = new LatLng(p.getLatitude(), p.getLongitude());
                        Marker marker = map.addMarker(new MarkerOptions().position(coords));
                        marker.setTag(p);
                        map.moveCamera(CameraUpdateFactory.newLatLng(coords));
                    }

                    return null;
                },
                () -> {
                    return null;
                });

    }
}
