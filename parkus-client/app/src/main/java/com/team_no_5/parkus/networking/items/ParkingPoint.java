package com.team_no_5.parkus.networking.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.team_no_5.parkus.Utilities.DateTimeConverter;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by Michal on 18.11.2017.
 */

public class ParkingPoint {
    @SerializedName("Id")
    @Expose(serialize = false)
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
    @SerializedName("UserName")
    @Expose(serialize = false)
    private String userName;

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

    public Calendar getCreatedOn() throws ParseException {
        return DateTimeConverter.stringToCalendar(createdOn, DateTimeConverter.DATE_TIME_SERVER_FORMAT);
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = DateTimeConverter.calendarToString(createdOn, DateTimeConverter.DATE_TIME_SERVER_FORMAT);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
