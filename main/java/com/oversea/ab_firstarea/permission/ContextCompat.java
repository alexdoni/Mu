package com.oversea.ab_firstarea.permission;

import android.content.Context;
import android.os.Process;

/* loaded from: classes.dex */
public class ContextCompat {
    public static int checkSelfPermission(Context context, String str) {
        if (str == null) {
            throw new IllegalArgumentException("permission is null");
        }
        try {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }
}
