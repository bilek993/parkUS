package com.team_no_5.parkus.Utilities;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by Michal on 19.11.2017.
 */

public class Base64Converter {
    public static String encode(String value) throws UnsupportedEncodingException {
        byte[] valueBytes = value.getBytes("UTF-8");
        byte[] result = Base64.encode(valueBytes, Base64.DEFAULT) ;

        return new String(result, "UTF-8");
    }
}
