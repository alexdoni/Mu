package com.xsdk.ab_firstbase.statisics.util;

import android.content.Context;
import androidx.core.app.ActivityCompat;

/* loaded from: classes3.dex */
public class PremissionBase {
    public static boolean lacksPermission(Context context, String str) {
        return ActivityCompat.checkSelfPermission(context, str) == -1;
    }
}
