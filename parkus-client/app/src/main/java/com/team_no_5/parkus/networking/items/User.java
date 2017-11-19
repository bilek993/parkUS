package com.team_no_5.parkus.networking.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michal on 19.11.2017.
 */

public class User {
    @SerializedName("id")
    @Expose(serialize = false)
    int id;
    @SerializedName("UserName")
    @Expose
    String username;
    @SerializedName("Name")
    @Expose
    String name;
    @SerializedName("Surname")
    @Expose
    String surname;
    @SerializedName("Password")
    @Expose
    String password;
    @SerializedName("Points")
    @Expose
    int points;

    public User(String username, String name, String surname, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
