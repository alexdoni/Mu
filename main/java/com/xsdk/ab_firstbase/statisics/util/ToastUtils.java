package com.xsdk.ab_firstbase.statisics.util;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/* loaded from: classes3.dex */
public class ToastUtils {
    public static void toastShow(Context context, String str) {
        if (context == null) {
            return;
        }
        if (TextUtils.isEmpty(str) || !str.startsWith("[1013] ")) {
            show(context, str, 0);
        }
    }

    public static void toastLongShow(Context context, String str) {
        show(context, str, 1);
    }

    private static void show(Context context, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper != Looper.getMainLooper() && myLooper == null) {
            Looper.prepare();
            myLooper = Looper.myLooper();
        }
        Toast.makeText(context, str + "", i).show();
        if (myLooper != Looper.getMainLooper()) {
            Looper.loop();
            myLooper.quit();
        }
    }
}
