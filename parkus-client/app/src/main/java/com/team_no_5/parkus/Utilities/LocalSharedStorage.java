package com.team_no_5.parkus.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import android.util.Base64;
import android.util.Log;

/**
 * Created by Michal on 19.11.2017.
 */

public class LocalSharedStorage {
    private static final String PREFERENCES_NAME = "MainSharedPreferences";
    public static final String AUTHORIZATION  = "authorization";

    public static String getUserAuthorizationData(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, 0);
        return settings.getString(AUTHORIZATION, null);
    }

    public static void saveUserAuthorizationData(Context context, String userName, String password) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, 0);

        String data = userName + ":" + password;
        String encodedData;
        try {
            encodedData = Base64Converter.encode(data);
        } catch (UnsupportedEncodingException e) {
            encodedData = "";
        }
        encodedData = "Basic " + encodedData;
        encodedData = encodedData.substring(0, encodedData.length() - 1);
        settings.edit().putString(AUTHORIZATION, encodedData).commit();
    }
}
