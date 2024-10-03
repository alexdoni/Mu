package com.luck.picture.lib.utils;

import android.os.SystemClock;

/* loaded from: classes2.dex */
public class DoubleUtils {
    private static final long TIME = 600;
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - lastClickTime < TIME) {
            return true;
        }
        lastClickTime = elapsedRealtime;
        return false;
    }
}
