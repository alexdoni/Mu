package com.unity3d.player;

import android.app.Activity;

/* loaded from: classes3.dex */
public class MultiWindowSupport {
    private static final String RESIZABLE_WINDOW = "unity.allow-resizable-window";

    public static boolean getAllowResizableWindow(Activity activity) {
        try {
            if (isInMultiWindowMode(activity)) {
                if (activity.getApplicationInfo().metaData.getBoolean(RESIZABLE_WINDOW)) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    static boolean isInMultiWindowMode(Activity activity) {
        if (C2715k.f1821c) {
            return activity.isInMultiWindowMode();
        }
        return false;
    }
}
