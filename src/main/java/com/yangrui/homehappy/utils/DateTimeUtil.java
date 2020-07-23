package com.yangrui.homehappy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getNowDate(){
        return new Date();
    }

    public static String getNowDate8String(){
        return sdf8.format(new Date());
    }
}
