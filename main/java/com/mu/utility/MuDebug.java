package com.mu.utility;

import android.util.Log;

/* loaded from: classes2.dex */
public class MuDebug {
    private static String TAG = "MuLog";
    public static boolean isOpenLog = true;

    public static void Log(String str) {
        if (isOpenLog) {
            Log.e(TAG, str);
        }
    }

    public static void LogError(String str) {
        Log.e(TAG, str);
    }

    public static void LogWarning(String str, Throwable th) {
        Log.w(TAG, str, th);
    }

    public static void LogError(String str, Throwable th) {
        Log.e(TAG, str, th);
    }
}
