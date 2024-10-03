package com.liulishuo.filedownloader.util;

import android.util.Log;

/* loaded from: classes2.dex */
public class FileDownloadLog {
    public static boolean NEED_LOG = false;
    private static final String TAG = "FileDownloader.";

    /* renamed from: e */
    public static void m593e(Object obj, Throwable th, String str, Object... objArr) {
        log(6, obj, th, str, objArr);
    }

    /* renamed from: e */
    public static void m592e(Object obj, String str, Object... objArr) {
        log(6, obj, str, objArr);
    }

    /* renamed from: i */
    public static void m594i(Object obj, String str, Object... objArr) {
        log(4, obj, str, objArr);
    }

    /* renamed from: d */
    public static void m591d(Object obj, String str, Object... objArr) {
        log(3, obj, str, objArr);
    }

    /* renamed from: w */
    public static void m596w(Object obj, String str, Object... objArr) {
        log(5, obj, str, objArr);
    }

    /* renamed from: v */
    public static void m595v(Object obj, String str, Object... objArr) {
        log(2, obj, str, objArr);
    }

    private static void log(int i, Object obj, String str, Object... objArr) {
        log(i, obj, null, str, objArr);
    }

    private static void log(int i, Object obj, Throwable th, String str, Object... objArr) {
        if ((i >= 5) || NEED_LOG) {
            Log.println(i, getTag(obj), FileDownloadUtils.formatString(str, objArr));
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private static String getTag(Object obj) {
        StringBuilder sb = new StringBuilder(TAG);
        sb.append((obj instanceof Class ? (Class) obj : obj.getClass()).getSimpleName());
        return sb.toString();
    }
}
