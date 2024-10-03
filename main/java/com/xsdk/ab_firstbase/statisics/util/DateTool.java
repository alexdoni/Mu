package com.xsdk.ab_firstbase.statisics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/* loaded from: classes3.dex */
public class DateTool {
    public static int yyMMddHHmmss = 13;
    public static int yyMMddHHmmssSSS = 16;
    public static int yy_MM_dd = 3;
    public static int yy_MM_dd_HH_mm_ss = 7;
    public static int yy_M_d = 4;
    public static int yy_M_d_H_m_s = 8;
    public static int yyyy = 9;
    public static int yyyyMM = 12;
    public static int yyyyMMdd = 11;
    public static int yyyyMMddHHmmss = 14;
    public static int yyyy_MM = 10;
    public static int yyyy_MM_dd = 1;
    public static int yyyy_MM_dd_HH_mm_ss = 5;
    public static int yyyy_MM_dd_HH_mm_ss_SSSZ = 15;
    public static int yyyy_M_d = 2;
    public static int yyyy_M_d_H_m_s = 6;

    public static String getStringDateFormat(String str, int i) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        switch (i) {
            case 1:
                simpleDateFormat.applyPattern("yyyy-MM-dd");
                break;
            case 2:
                simpleDateFormat.applyPattern("yyyy-M-d");
                break;
            case 3:
                simpleDateFormat.applyPattern("yy-MM-dd");
                break;
            case 4:
                simpleDateFormat.applyPattern("yy-M-d");
                break;
            case 5:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                break;
            case 6:
                simpleDateFormat.applyPattern("yyyy-M-d H:m:s");
                break;
            case 7:
                simpleDateFormat.applyPattern("yy-MM-dd HH:mm:ss");
                break;
            case 8:
                simpleDateFormat.applyPattern("yy-M-d H:m:s");
                break;
            case 9:
                simpleDateFormat.applyPattern("yyyy");
                break;
            case 10:
                simpleDateFormat.applyPattern("yyyy-MM");
                break;
            case 11:
                simpleDateFormat.applyPattern("yyyyMMdd");
                break;
            case 12:
                simpleDateFormat.applyPattern("yyyyMM");
                break;
            case 13:
                simpleDateFormat.applyPattern("yyMMddHHmmss");
                break;
            case 14:
                simpleDateFormat.applyPattern("yyyyMMddHHmmss");
                break;
            case 15:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSSZ");
                break;
            case 16:
                simpleDateFormat.applyPattern("yyMMddHHmmssSSS");
                break;
        }
        return simpleDateFormat.format(simpleDateFormat.parse(str));
    }

    public static String getStringDateFormat(Date date, int i) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        switch (i) {
            case 1:
                simpleDateFormat.applyPattern("yyyy-MM-dd");
                break;
            case 2:
                simpleDateFormat.applyPattern("yyyy-M-d");
                break;
            case 3:
                simpleDateFormat.applyPattern("yy-MM-dd");
                break;
            case 4:
                simpleDateFormat.applyPattern("yy-M-d");
                break;
            case 5:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                break;
            case 6:
                simpleDateFormat.applyPattern("yyyy-M-d H:m:s");
                break;
            case 7:
                simpleDateFormat.applyPattern("yy-MM-dd HH:mm:ss");
                break;
            case 8:
                simpleDateFormat.applyPattern("yy-M-d H:m:s");
                break;
            case 9:
                simpleDateFormat.applyPattern("yyyy");
                break;
            case 10:
                simpleDateFormat.applyPattern("yyyy-MM");
                break;
            case 11:
                simpleDateFormat.applyPattern("yyyyMMdd");
                break;
            case 12:
                simpleDateFormat.applyPattern("yyyyMM");
                break;
            case 13:
                simpleDateFormat.applyPattern("yyMMddHHmmss");
                break;
            case 14:
                simpleDateFormat.applyPattern("yyyyMMddHHmmss");
                break;
            case 15:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSSZ");
                break;
            case 16:
                simpleDateFormat.applyPattern("yyMMddHHmmssSSS");
                break;
        }
        return simpleDateFormat.format(date);
    }

    public static Date getDateFormat(String str, int i) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        switch (i) {
            case 1:
                simpleDateFormat.applyPattern("yyyy-MM-dd");
                break;
            case 2:
                simpleDateFormat.applyPattern("yyyy-M-d");
                break;
            case 3:
                simpleDateFormat.applyPattern("yy-MM-dd");
                break;
            case 4:
                simpleDateFormat.applyPattern("yy-M-d");
                break;
            case 5:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                break;
            case 6:
                simpleDateFormat.applyPattern("yyyy-M-d H:m:s");
                break;
            case 7:
                simpleDateFormat.applyPattern("yy-MM-dd HH:mm:ss");
                break;
            case 8:
                simpleDateFormat.applyPattern("yy-M-d H:m:s");
                break;
            case 9:
                simpleDateFormat.applyPattern("yyyy");
                break;
            case 10:
                simpleDateFormat.applyPattern("yyyy-MM");
                break;
            case 11:
                simpleDateFormat.applyPattern("yyyyMMdd");
                break;
            case 12:
                simpleDateFormat.applyPattern("yyyyMM");
                break;
            case 13:
                simpleDateFormat.applyPattern("yyMMddHHmmss");
                break;
            case 14:
                simpleDateFormat.applyPattern("yyyyMMddHHmmss");
                break;
            case 15:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSSZ");
                break;
            case 16:
                simpleDateFormat.applyPattern("yyMMddHHmmssSSS");
                break;
        }
        return simpleDateFormat.parse(str);
    }

    public static String getNowDate(int i) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        switch (i) {
            case 1:
                simpleDateFormat.applyPattern("yyyy-MM-dd");
                break;
            case 2:
                simpleDateFormat.applyPattern("yyyy-M-d");
                break;
            case 3:
                simpleDateFormat.applyPattern("yy-MM-dd");
                break;
            case 4:
                simpleDateFormat.applyPattern("yy-M-d");
                break;
            case 5:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
                break;
            case 6:
                simpleDateFormat.applyPattern("yyyy-M-d H:m:s");
                break;
            case 7:
                simpleDateFormat.applyPattern("yy-MM-dd HH:mm:ss");
                break;
            case 8:
                simpleDateFormat.applyPattern("yy-M-d H:m:s");
                break;
            case 9:
                simpleDateFormat.applyPattern("yyyy");
                break;
            case 10:
                simpleDateFormat.applyPattern("yyyy-MM");
                break;
            case 11:
                simpleDateFormat.applyPattern("yyyyMMdd");
                break;
            case 12:
                simpleDateFormat.applyPattern("yyyyMM");
                break;
            case 13:
                simpleDateFormat.applyPattern("yyMMddHHmmss");
                break;
            case 14:
                simpleDateFormat.applyPattern("yyyyMMddHHmmss");
                break;
            case 15:
                simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSSZ");
                break;
            case 16:
                simpleDateFormat.applyPattern("yyMMddHHmmssSSS");
                break;
        }
        return simpleDateFormat.format(new Date());
    }

    public static Date getNowDate() throws ParseException {
        return new Date();
    }

    public static Date getFristTimeOfDate(Date date) throws Exception {
        return getDateFormat(getStringDateFormat(date, yyyy_MM_dd) + " 00:00:00", yyyy_MM_dd_HH_mm_ss);
    }

    public static Date getLastTimeOfDate(Date date) throws Exception {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(5, 1);
        return getFristTimeOfDate(gregorianCalendar.getTime());
    }

    public static Date getNDayOfDate(Date date, Integer num) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(5, num.intValue());
        return gregorianCalendar.getTime();
    }

    public static Long getIntervalDays(String str, String str2) throws Exception {
        return getIntervalDays(getDateFormat(str, yy_MM_dd), getDateFormat(str2, yy_MM_dd));
    }

    public static Long getIntervalDays(Date date, Date date2) throws Exception {
        return Long.valueOf(((((date.getTime() - date2.getTime()) / 1000) / 60) / 60) / 24);
    }

    public static String getWeekDay(Date date) {
        Calendar.getInstance().setTime(date);
        return new String[]{"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"}[r0.get(7) - 1];
    }

    public static Integer getSecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return Integer.valueOf(calendar.get(13));
    }

    public static Date addDays(Date date, int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(5, i);
        return gregorianCalendar.getTime();
    }

    public static Date addWeeks(Date date, int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(3, i);
        return gregorianCalendar.getTime();
    }

    public static Date addMonths(Date date, int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(2, i);
        return gregorianCalendar.getTime();
    }

    public static Date addMinutes(Date date, int i) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(12, i);
        return gregorianCalendar.getTime();
    }
}
