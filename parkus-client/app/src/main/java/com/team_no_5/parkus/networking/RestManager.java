package com.team_no_5.parkus.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michal on 18.11.2017.
 */

public class RestManager {
    private static RestManager instance;
    private Retrofit retrofit;

    public RestManager() {
        retrofit = new Retrofit.Builder().baseUrl("").
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public static RestService getInstance() {
        if (instance == null)
            instance = new RestManager();

        return instance.retrofit.create(RestService.class);
    }
}
