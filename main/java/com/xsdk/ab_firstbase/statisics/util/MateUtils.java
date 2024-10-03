package com.xsdk.ab_firstbase.statisics.util;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/* loaded from: classes3.dex */
public class MateUtils {
    public static int getIntFromMateData(Context context, String str) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getInt(str, 0);
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getStringFromMateData(Context context, String str) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return "";
            }
            String str2 = bundle.get(str) + "";
            return TextUtils.isEmpty(str2) ? "" : str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean getBooleanFromMateData(Context context, String str) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getBoolean(str, false);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
