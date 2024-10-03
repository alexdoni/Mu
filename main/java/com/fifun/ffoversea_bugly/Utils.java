package com.fifun.ffoversea_bugly;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.UUID;

/* loaded from: classes.dex */
public class Utils {
    public static final String SDK_ANDROIDID = "bugly_androidid";
    private static final String sharedpreferencespath = "buglysp";

    public static String getAndroidID(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused) {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String stringKeyForValue = getStringKeyForValue(context, SDK_ANDROIDID);
        if (TextUtils.isEmpty(stringKeyForValue)) {
            stringKeyForValue = "auto_" + getUUID();
            setSharePreferences(context, SDK_ANDROIDID, stringKeyForValue);
        }
        return stringKeyForValue;
    }

    public static String getStringKeyForValue(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            return context.getSharedPreferences(sharedpreferencespath, 0).getString(str, "");
        } catch (Throwable th) {
            th.toString();
            return "";
        }
    }

    public static String getUUID() {
        try {
            return UUID.randomUUID().toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void setSharePreferences(Context context, String str, String str2) {
        if (context == null) {
            return;
        }
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(sharedpreferencespath, 0).edit();
            edit.putString(str, str2);
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static String getPhoneModel() {
        String str = Build.MODEL;
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static String getStringFromMateData(Context context, String str) {
        Object obj;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            return (bundle == null || (obj = bundle.get(str)) == null) ? "" : String.valueOf(obj);
        } catch (Exception unused) {
            return "";
        }
    }
}
