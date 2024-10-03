package com.luck.picture.lib.utils;

import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;

/* loaded from: classes2.dex */
public class ValueOf {
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: to */
    public static <T> T m597to(Object obj, T t) {
        return obj == 0 ? t : obj;
    }

    public static String toString(Object obj) {
        try {
            return obj.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static double toDouble(Object obj) {
        return toDouble(obj, 0);
    }

    public static double toDouble(Object obj, int i) {
        if (obj == null) {
            return i;
        }
        try {
            return Double.parseDouble(obj.toString().trim());
        } catch (Exception unused) {
            return i;
        }
    }

    public static long toLong(Object obj, long j) {
        long parseLong;
        if (obj == null) {
            return j;
        }
        try {
            String trim = obj.toString().trim();
            if (trim.contains(".")) {
                parseLong = Long.parseLong(trim.substring(0, trim.lastIndexOf(".")));
            } else {
                parseLong = Long.parseLong(trim);
            }
            return parseLong;
        } catch (Exception unused) {
            return j;
        }
    }

    public static long toLong(Object obj) {
        return toLong(obj, 0L);
    }

    public static float toFloat(Object obj, long j) {
        if (obj == null) {
            return (float) j;
        }
        try {
            return Float.parseFloat(obj.toString().trim());
        } catch (Exception unused) {
            return (float) j;
        }
    }

    public static float toFloat(Object obj) {
        return toFloat(obj, 0L);
    }

    public static int toInt(Object obj, int i) {
        int parseInt;
        if (obj == null) {
            return i;
        }
        try {
            String trim = obj.toString().trim();
            if (trim.contains(".")) {
                parseInt = Integer.parseInt(trim.substring(0, trim.lastIndexOf(".")));
            } else {
                parseInt = Integer.parseInt(trim);
            }
            return parseInt;
        } catch (Exception unused) {
            return i;
        }
    }

    public static int toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static boolean toBoolean(Object obj) {
        return toBoolean(obj, false);
    }

    public static boolean toBoolean(Object obj, boolean z) {
        if (obj == null) {
            return false;
        }
        try {
            return !JsonSerializer.False.equals(obj.toString().trim().trim());
        } catch (Exception unused) {
            return z;
        }
    }
}
