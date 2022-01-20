package com.theodore.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateFormatterUtils {
    public static final String LOCAL_DATE_FORMATTER = "yyyy-MM-dd";
    public static final String LOCAL_TIME_FORMATTER = "HH:mm:ss";
    public static final String LOCAL_TIME_FORMATTER_SHOT = "HH:mm";
    public static final String SIMPLATE_DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String SIMPLATE_DATE_FORMATTER_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static Date strConvertDate(String str) throws ParseException {
        return strConvertDate(str, LOCAL_DATE_FORMATTER);
    }

    public static Date strConvertDate(String str, String formatter) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatter);
        return strConvertDate(str, format);
    }

    public static Date strConvertDate(String str, SimpleDateFormat format) throws ParseException {
        return format.parse(str);
    }


    public static LocalTime strConvertLocalTime(String str) {
        return strConvertLocalTime(str, LOCAL_TIME_FORMATTER_SHOT);
    }

    public static LocalTime strConvertLocalTime(String str, String formatter) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern(formatter);
        return LocalTime.parse(str, parser);
    }

    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime date2LocalTime(Date date) {
        if(null == date) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }


    public static String convertStr(LocalTime localTime) {
        return convertStr(localTime, LOCAL_TIME_FORMATTER);
    }

    public static String convertStr(LocalTime localTime, String formatter) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(formatter);
        return convertStr(localTime, fmt);
    }

    public static String convertStr(LocalTime localTime, DateTimeFormatter formatter) {
        if (localTime == null)
            return null;
        return localTime.format(formatter);
    }

    public static String convertStr(LocalDate localDate) {
        return convertStr(localDate, LOCAL_DATE_FORMATTER);
    }

    public static String convertStr(LocalDate localDate, String formatter) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(formatter);
        return convertStr(localDate, fmt);
    }

    public static String convertStr(LocalDate localDate, DateTimeFormatter formatter) {
        if (localDate == null)
            return null;
        return localDate.format(formatter);
    }

    public static String dateConvertStr(Date date) {
        return dateConvertStr(date, SIMPLATE_DATE_FORMATTER);
    }

    public static String dateTimeConvertStr(Date date) {
        return dateConvertStr(date, SIMPLATE_DATE_FORMATTER_FULL);
    }

    public static String dateConvertStr(Date date, SimpleDateFormat formatter) {
        if (date == null)
            return null;
        return formatter.format(date);
    }

    public static String dateConvertStr(Date date, String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return dateConvertStr(date, sdf);
    }

    public static int dateToWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }
}
