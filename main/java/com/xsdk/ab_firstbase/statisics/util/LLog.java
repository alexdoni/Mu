package com.xsdk.ab_firstbase.statisics.util;

import android.util.Log;
import com.u2020.sdk.mc.Channel;
import com.u2020.sdk.mc.MCReader;

/* loaded from: classes3.dex */
public class LLog {
    private static boolean isOpenLog = false;
    private static String signLog;

    public static boolean isIsOpenLog() {
        return isOpenLog || "isOpenLog".equals(getSignLog());
    }

    public static void setIsOpenLog(boolean z) {
        isOpenLog = z;
    }

    private static String getSignLog() {
        Channel channel;
        String str = signLog;
        if (str != null) {
            return str;
        }
        if (ContextHolder.getContext() != null && (channel = MCReader.getChannel(ContextHolder.getContext())) != null && channel.getSiteID() != null) {
            signLog = String.valueOf(channel.getSiteID());
        }
        return signLog;
    }

    public static void v_noControl(String str) {
        Log.v("X_LOG", str);
    }

    public static void w_noControl(String str) {
        Log.w("X_LOG", str);
    }

    public static void e_noControl(String str) {
        Log.e("X_LOG", str);
    }

    public static void v_noControl(String str, String str2) {
        Log.v(str, str2);
    }

    public static void i_noControl(String str, String str2) {
        Log.i(str, str2);
    }

    public static void i_noControl(String str) {
        Log.i("X_LOG", str);
    }

    public static void w_noControl(String str, String str2) {
        Log.w(str, str2);
    }

    public static void e_noControl(String str, String str2) {
        Log.e(str, str2);
    }

    public static void v_Control(String str) {
        if (isIsOpenLog()) {
            Log.v("X_LOG", str);
        }
    }

    public static void i_Control(String str) {
        if (isIsOpenLog()) {
            Log.i("X_LOG", str);
        }
    }

    public static void i_Control(String str, String str2) {
        if (isIsOpenLog()) {
            Log.i(str, str2);
        }
    }

    public static void w_Control(String str) {
        if (isIsOpenLog()) {
            Log.w("X_LOG", str);
        }
    }

    public static void e_Control(String str) {
        if (isIsOpenLog()) {
            try {
                if (str.length() <= 3072) {
                    Log.e("X_LOG", str);
                    return;
                }
                while (str.length() > 3072) {
                    String substring = str.substring(0, 3072);
                    str = str.replace(substring, "");
                    Log.e("X_LOG", substring);
                }
                Log.e("X_LOG", str);
            } catch (Exception unused) {
                Log.e("X_LOG", str);
            }
        }
    }

    public static void v_Control(String str, String str2) {
        if (isIsOpenLog()) {
            Log.v(str, str2);
        }
    }

    public static void w_Control(String str, String str2) {
        if (isIsOpenLog()) {
            Log.w(str, str2);
        }
    }

    public static void e_Control(String str, String str2) {
        if (isIsOpenLog()) {
            Log.e(str, str2);
        }
    }
}
