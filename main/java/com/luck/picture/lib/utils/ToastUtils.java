package com.luck.picture.lib.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.luck.picture.lib.app.PictureAppMaster;
import com.luck.picture.lib.thread.PictureThreadUtils;

/* loaded from: classes2.dex */
public class ToastUtils {
    private static final long TIME = 1000;
    private static long lastClickTime;
    private static String mLastText;

    public static void showToast(final Context context, final String str) {
        if (isFastDoubleClick() && TextUtils.equals(str, mLastText)) {
            return;
        }
        Context appContext = PictureAppMaster.getInstance().getAppContext();
        if (appContext == null) {
            appContext = context.getApplicationContext();
        }
        if (PictureThreadUtils.isInUiThread()) {
            Toast.makeText(appContext, str, 0).show();
            mLastText = str;
        } else {
            PictureThreadUtils.runOnUiThread(new Runnable() { // from class: com.luck.picture.lib.utils.ToastUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    Context appContext2 = PictureAppMaster.getInstance().getAppContext();
                    if (appContext2 == null) {
                        appContext2 = context.getApplicationContext();
                    }
                    Toast.makeText(appContext2, str, 0).show();
                    String unused = ToastUtils.mLastText = str;
                }
            });
        }
    }

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastClickTime < TIME) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }
}
