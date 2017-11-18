package com.team_no_5.parkus.networking.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michal on 18.11.2017.
 */

public class ParkingPoint {
    @SerializedName("Id")
    @Expose
    private int id;
    @SerializedName("Longitude")
    @Expose
    private double longitude;
    @SerializedName("Latitude")
    @Expose
    private double latitude;
    @SerializedName("Photo")
    @Expose
    private byte[] photo;
    @SerializedName("CreatedOn")
    @Expose
    private String createdOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
