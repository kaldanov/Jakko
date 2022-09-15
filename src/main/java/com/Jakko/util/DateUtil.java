package com.Jakko.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat formatDateAndTimeSS = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static SimpleDateFormat formatDateAndTime   = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    private static SimpleDateFormat formatDate          = new SimpleDateFormat("dd.MM.yyyy");

    public  static String getDbMmYyyyHhMmSs(Date date) {
        formatDateAndTimeSS.setLenient(false);
        return formatDateAndTimeSS.format(date);
    }

    public  static String getDayDate(Date date) {
        formatDateAndTime.setLenient(false);
        return formatDate.format(date);
    }

    public  static String getString(Date date, String format) { return getResult(new SimpleDateFormat(format), date); }

    private static String getResult(SimpleDateFormat simpleDateFormat, Date date) {
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.format(date);
    }

    public  static String getTimeDate(Date date) { return getString(date, "HH:mm:ss"); }

    public  static String getDateAndTime(Date date) {
        if (date == null) return "";
        return getString(date, "dd.MM.yyyy HH:mm");
    }

    public  static Date   getHour(int hour) {
        Date date = new Date();
        if (date.getHours() >= hour) date.setDate(date.getDate() + 1);
        date.setHours(hour);
        date.setMinutes(0);
        date.setSeconds(1);
        return date;
    }
}
