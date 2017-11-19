package com.team_no_5.parkus.networking;

import android.content.Context;

import com.team_no_5.parkus.Utilities.LocalSharedStorage;
import com.team_no_5.parkus.networking.items.ParkingPoint;

import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Michal on 19.11.2017.
 */

public class PointsNetworking {
    private Context context;

    private int pointsNumber;

    public PointsNetworking(Context context) {
        this.context = context;
    }

    public void loadUserPoints(Callable<Void> onSuccess,
                                  Callable<Void> onFinish) {
        String authorizationHeader = LocalSharedStorage.getUserAuthorizationData(context);

        RestService restService = RestManager.getInstance();
        Call<Integer> call = restService.userPoints(authorizationHeader);

        call.enqueue(new AdvancedCallback<Integer>(context) {
            @Override
            public void onRetry() {
                loadUserPoints(onSuccess, onFinish);
            }

            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                super.onResponse(call, response);

                if (response.isSuccessful()) {
                    try {
                        pointsNumber = response.body();
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
            public void onFailure(Call<Integer> call, Throwable t) {
                super.onFailure(call, t);

                try {
                    onFinish.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getPointsNumber() {
        return pointsNumber;
    }
}
