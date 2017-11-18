package com.team_no_5.parkus.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Michal on 18.11.2017.
 */

public class DateTimeConverter {

    public static final String DATE_TIME_SERVER_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS";

    public static String calendarToString(Calendar calendar, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(calendar.getTime());
    }

    public static Calendar stringToCalendar(String string, String format) throws ParseException {
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        calendar.setTime(dateFormat.parse(string));

        return calendar;
    }
}
