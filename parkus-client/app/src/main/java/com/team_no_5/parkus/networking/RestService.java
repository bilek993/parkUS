package com.team_no_5.parkus.networking;

import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Michal on 18.11.2017.
 */

public interface RestService {
    @Headers({
            "Content-Type: application/json"
    })
    @GET("api/ParkingPoints")
    Call<List<ParkingPoint>> parkingPoints();
}
