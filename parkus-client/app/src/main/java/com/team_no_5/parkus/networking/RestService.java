package com.team_no_5.parkus.networking;

import com.team_no_5.parkus.networking.items.ParkingPoint;
import com.team_no_5.parkus.networking.items.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Michal on 18.11.2017.
 */

public interface RestService {
    @Headers({
            "Content-Type: application/json"

    })
    @GET("api/ParkingPoints")
    Call<List<ParkingPoint>> parkingPoints(@Header("Authorization") String authorization);

    @Headers({
            "Content-Type: application/json"
    })
    @PUT("api/ParkingPoints")
    Call<Void> addParkingPoint(@Header("Authorization") String authorization, @Body ParkingPoint parkingPoint);

    @Headers({
            "Content-Type: application/json"
    })
    @PUT("api/Users")
    Call<Boolean> addNewUser(@Body User user);

    @POST("api/Users")
    Call<Boolean> login(@Body User user);
}
