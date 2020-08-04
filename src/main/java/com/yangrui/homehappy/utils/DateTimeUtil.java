package com.yangrui.homehappy.utils;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getNowDate() {
        return new Date();
    }

    public static String getNowDate8String() {
        return sdf8.format(new Date());
    }

    /**
     * 获取本周第一天
     *
     * @return
     */
    public static Date getStartDayOfWeek() {
        LocalDate now = LocalDate.now();
        LocalDate firstDay = now.with(DayOfWeek.MONDAY);
        return Date.from(firstDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static int getNowDayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    public static int getDateFar(String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.DAYS.between(now, localDate);
    }


    /**
     * 获取本周最后一天
     *
     * @return
     */
    public static Date getEndDayOfWeek() {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate now = LocalDate.now();
        LocalDate endDay = now.with(DayOfWeek.SUNDAY);
        return Date.from(endDay.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }


    /**
     * 描述 获取今天星期几
     *
     * @param
     * @return int
     */
    public static int getDayOfTheWeek() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.getDayOfWeek().getValue();
    }


    public static Date agoWeekByLocalDateTime(DayOfWeek weekIndex, int ago) {
        int i = ago * -7;
        LocalDate localDate = LocalDate.now().plusDays(i).with(weekIndex);
        Date from = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return from;
    }
}