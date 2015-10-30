package com.mersocarlin.androidrest.domain.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {

    public static String dateToString(Date date) {
        if (date == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        return sdf.format(date);
    }

    public static Date stringToDate(String date) {
        if (date.equals("")) return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
