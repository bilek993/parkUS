package com.team_no_5.parkus.adapters;

import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Michal on 18.11.2017.
 */

public class PointInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private View contentsView;

    public PointInfoAdapter(LayoutInflater inflater) {
        contentsView = null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return contentsView;
    }
}
