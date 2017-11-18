package com.team_no_5.parkus.networking;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Michal on 18.11.2017.
 */

public class RestManager {
    private static String SERVER_ADDRESS = "http://192.168.43.199/";

    private static RestManager instance;
    private Retrofit retrofit;

    public RestManager() {
        retrofit = new Retrofit.Builder().baseUrl(SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                .build();
    }

    public static RestService getInstance() {
        if (instance == null)
            instance = new RestManager();

        return instance.retrofit.create(RestService.class);
    }
}
