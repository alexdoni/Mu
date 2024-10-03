package com.mu.utility;

import android.content.Context;
import android.os.Build;
import java.util.Locale;

/* loaded from: classes2.dex */
public class SystemUtil {
    public static String getSystemLanguage() {
        try {
            return Locale.getDefault().getLanguage();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getSystemVersion() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getSystemModel() {
        try {
            return Build.MODEL;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getDeviceBrand() {
        try {
            return Build.BRAND;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getIMEI(Context context) {
        return null;
    }
}
