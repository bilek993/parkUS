package com.team_no_5.parkus.networking;

import android.content.Context;

import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Michal on 18.11.2017.
 */

public class ParkingPointsNetworking {
    private Context context;

    private List<ParkingPoint> parkingPoints;

    public ParkingPointsNetworking(Context context) {
        this.context = context;
    }

    public void loadParkingPoints(Callable<Void> onSuccess,
                                 Callable<Void> onFinish) {
        RestService restService = RestManager.getInstance();
        Call<List<ParkingPoint>> call = restService.parkingPoints();

        call.enqueue(new AdvancedCallback<List<ParkingPoint>>(context) {
            @Override
            public void onRetry() {
                loadParkingPoints(onSuccess, onFinish);
            }

            @Override
            public void onResponse(Call<List<ParkingPoint>> call, Response<List<ParkingPoint>> response) {
                super.onResponse(call, response);

                if (response.isSuccessful()) {
                    try {
                        parkingPoints = response.body();
                        onSuccess.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        onFinish.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ParkingPoint>> call, Throwable t) {
                super.onFailure(call, t);
                
                try {
                    onFinish.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public List<ParkingPoint> getParkingPoints() {
        return parkingPoints;
    }
}
