package com.team_no_5.parkus.adapters;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.team_no_5.parkus.R;
import com.team_no_5.parkus.Utilities.AddressesHelper;
import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.List;

/**
 * Created by Michal on 18.11.2017.
 */

public class PointInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    private View contentsView;

    private TextView textViewPerson;
    private TextView textViewInfo1;
    private TextView textViewInfo2;
    private TextView textViewInfo3;
    private ImageView imageViewPhoto;

    private List<ParkingPoint> points;

    public PointInfoAdapter(Context context, LayoutInflater inflater) {
        this.context = context;

        contentsView = inflater.inflate(R.layout.dialog_map_details, null);

        textViewPerson = contentsView.findViewById(R.id.textViewPerson);
        textViewInfo1 = contentsView.findViewById(R.id.textViewInfo1);
        textViewInfo2 = contentsView.findViewById(R.id.textViewInfo2);
        textViewInfo3 = contentsView.findViewById(R.id.textViewInfo3);
        imageViewPhoto = contentsView.findViewById(R.id.imageViewPhoto);
    }

    public void setPointsList(List<ParkingPoint> points) {
        this.points = points;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ParkingPoint point = (ParkingPoint)marker.getTag();

        textViewPerson.setText(context.getString(R.string.parking_place));


        List<Address> addresses = AddressesHelper.getAddress(context,
                new LatLng(point.getLatitude(), point.getLongitude()));
        if (addresses.size() > 0) {
            textViewInfo1.setText(addresses.get(0).getAddressLine(0));
            textViewInfo2.setText(addresses.get(0).getAddressLine(1));
        }

        return contentsView;
    }
}
