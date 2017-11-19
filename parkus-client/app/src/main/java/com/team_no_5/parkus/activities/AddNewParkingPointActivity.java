package com.team_no_5.parkus.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.team_no_5.parkus.R;
import com.team_no_5.parkus.Utilities.AddressesHelper;
import com.team_no_5.parkus.Utilities.PhotosHelper;
import com.team_no_5.parkus.networking.ParkingPointsNetworking;
import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewParkingPointActivity extends AppCompatActivity {

    @BindView(R.id.textViewInfo1)
    TextView textViewInfo1;
    @BindView(R.id.textViewInfo2)
    TextView textViewInfo2;
    @BindView(R.id.imageButtonAddPhoto)
    ImageButton imageButtonAddPhoto;

    private ParkingPoint parkingPoint = new ParkingPoint();
    private PhotosHelper photosHelper = new PhotosHelper();

    private final int TAKE_PHOTO_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_parking_point);

        ButterKnife.bind(this);

        getCurrentLoaction(this);
    }

    private void getCurrentLoaction(AppCompatActivity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);


        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                onChangedPosition(location.getLatitude(), location.getLongitude());

                locationManager.removeUpdates(this);
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
        };

        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private void onChangedPosition(double latitude, double longitude) {
        parkingPoint.setLatitude(latitude);
        parkingPoint.setLongitude(longitude);

        List<Address> addresses = AddressesHelper.getAddress(this,
                new LatLng(latitude, longitude));
        if (addresses.size() > 0) {
            textViewInfo1.setText(addresses.get(0).getAddressLine(0));
            textViewInfo2.setText(addresses.get(0).getAddressLine(1));
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            File photo = null;
            try {
                photo = photosHelper.createImageFile(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photo != null) {
                Uri uri = FileProvider.getUriForFile(this, "com.team_no_5.parkus.fileprovider", photo);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                startActivityForResult(intent, TAKE_PHOTO_REQUEST_CODE);
            }
        }
    }

    private void saveParkingPoint() {
        ParkingPointsNetworking parkingPointsNetworking = new ParkingPointsNetworking(this);
        parkingPointsNetworking.addParkingPoint(parkingPoint, () -> {
                    Intent resultIntent = new Intent();
                    setResult(RESULT_OK, resultIntent);

                    finish();
                    return null;
                },
                () -> {
                    return null;
                });
    }

    @OnClick(R.id.imageButtonAddPhoto)
    void onImageViewButtonAddPhotoClick() {
        takePhoto();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_new_parking_point, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_add_point) {
            saveParkingPoint();

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
            setPhoto();
        }
    }

    private void setPhoto() {
        int targetWidth = imageButtonAddPhoto.getWidth();
        int targetHeight = imageButtonAddPhoto.getHeight();

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photosHelper.getCurrentPhotoPath(), bitmapOptions);
        int photoWindth = bitmapOptions.outWidth;
        int photoHeight = bitmapOptions.outHeight;

        int scaleFactor = Math.min(photoWindth / targetWidth, photoHeight / targetHeight);

        bitmapOptions.inJustDecodeBounds = false;
        bitmapOptions.inSampleSize = scaleFactor;
        bitmapOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(photosHelper.getCurrentPhotoPath(), bitmapOptions);
        imageButtonAddPhoto.setImageBitmap(bitmap);
    }
}
