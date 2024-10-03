package com.luck.picture.lib.utils;

import android.content.Context;
import com.luck.picture.lib.C2140R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DateUtils {

    /* renamed from: SF */
    private static final SimpleDateFormat f736SF = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long getCurrentTimeMillis() {
        String valueOf = ValueOf.toString(Long.valueOf(System.currentTimeMillis()));
        if (valueOf.length() > 10) {
            valueOf = valueOf.substring(0, 10);
        }
        return ValueOf.toLong(valueOf);
    }

    public static String getDataFormat(Context context, long j) {
        if (String.valueOf(j).length() <= 10) {
            j *= 1000;
        }
        if (isThisWeek(j)) {
            return context.getString(C2140R.string.ps_current_week);
        }
        if (isThisMonth(j)) {
            return context.getString(C2140R.string.ps_current_month);
        }
        return SDF.format(Long.valueOf(j));
    }

    public static String getYearDataFormat(long j) {
        if (String.valueOf(j).length() <= 10) {
            j *= 1000;
        }
        return SDF_YEAR.format(Long.valueOf(j));
    }

    private static boolean isThisWeek(long j) {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(3);
        calendar.setTime(new Date(j));
        return calendar.get(3) == i;
    }

    public static boolean isThisMonth(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = SDF;
        return simpleDateFormat.format(date).equals(simpleDateFormat.format(new Date()));
    }

    public static long millisecondToSecond(long j) {
        return (j / 1000) * 1000;
    }

    public static int dateDiffer(long j) {
        try {
            return (int) Math.abs(getCurrentTimeMillis() - j);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String formatDurationTime(long j) {
        String str = j < 0 ? "-" : "";
        long abs = Math.abs(j) / 1000;
        long j2 = abs % 60;
        long j3 = (abs / 60) % 60;
        long j4 = abs / 3600;
        return j4 > 0 ? String.format(Locale.getDefault(), "%s%d:%02d:%02d", str, Long.valueOf(j4), Long.valueOf(j3), Long.valueOf(j2)) : String.format(Locale.getDefault(), "%s%02d:%02d", str, Long.valueOf(j3), Long.valueOf(j2));
    }

    public static String getCreateFileName(String str) {
        return str + f736SF.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String getCreateFileName() {
        return f736SF.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String cdTime(long j, long j2) {
        long j3 = j2 - j;
        if (j3 > 1000) {
            return (j3 / 1000) + "秒";
        }
        return j3 + "毫秒";
    }
}
