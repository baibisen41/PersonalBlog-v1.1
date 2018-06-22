package com.bbs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: baibisen
 * Date: 2018/5/20
 */
public class DateTimeUtils {

    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String TIME_PATTERN = "HH:mm:ss";
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String DATE_TIME_MIN_PATTERN = "yyyy-MM-dd HH:mm";
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
    private final static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_PATTERN);
    private final static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
    private final static SimpleDateFormat dateTimeMinFormat = new SimpleDateFormat(DATE_TIME_MIN_PATTERN);

    public static Date dateFormat(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static String dateFormat(Long currentTime) throws ParseException {
        return dateFormat.format(new Date(currentTime));
    }

    public static Date timeFormat(String date) throws ParseException {
        return timeFormat.parse(date);
    }

    public static String timeFormat(Long currentTime) {
        return timeFormat.format(new Date(currentTime));
    }

    public static Date dateTimeFormat(String date) throws ParseException {
        return dateTimeFormat.parse(date);
    }

    public static String dateTimeFormat(Long currentTime) {
        return dateTimeFormat.format(new Date(currentTime));
    }


    public static Date dateTimeMinFormat(String date) throws ParseException {
        return dateTimeMinFormat.parse(date);
    }

    public static String dateTimeMinFormat(Long currentTime) {
        return dateTimeMinFormat.format(new Date(currentTime));
    }
}
