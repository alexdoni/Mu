package com.xsdk.ab_firstbase.statisics.util;

/* loaded from: classes3.dex */
public class IsFastClickUtils {
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;
    private static long lastClickTime2;

    public static boolean isFastClick(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < j;
        lastClickTime = currentTimeMillis;
        return z;
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime2 < 2000;
        lastClickTime2 = currentTimeMillis;
        if (z) {
            LLog.i_Control("FastClick!so return");
        }
        return z;
    }
}
