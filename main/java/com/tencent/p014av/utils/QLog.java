package com.tencent.p014av.utils;

import android.content.Context;
import android.util.Log;
import com.tencent.p014av.sdk.GMELibLoader;
import com.tencent.p014av.wrapper.GMEJavaInstance;

/* loaded from: classes3.dex */
public class QLog {
    public static final int DEBUG = 3;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int VERBOSE = 4;
    private static volatile boolean useAvsdkLogger = true;

    private static native void nativeWriteLog(int i, String str);

    private static native String natvieGetLogDir();

    /* renamed from: d */
    public static void m598d(String str, String str2) {
        if (useAvsdkLogger) {
            writeLog(3, str, str2);
        } else {
            Log.d(str, str2);
        }
    }

    /* renamed from: d */
    public static void m599d(String str, String str2, Exception exc) {
        if (useAvsdkLogger) {
            writeLog(3, str, str2, exc);
        } else {
            Log.d(str, str2, exc);
        }
    }

    /* renamed from: w */
    public static void m606w(String str, String str2) {
        if (useAvsdkLogger) {
            writeLog(1, str, str2);
        } else {
            Log.w(str, str2);
        }
    }

    /* renamed from: w */
    public static void m607w(String str, String str2, Exception exc) {
        if (useAvsdkLogger) {
            writeLog(1, str, str2, exc);
        } else {
            Log.w(str, str2, exc);
        }
    }

    /* renamed from: e */
    public static void m600e(String str, String str2) {
        if (useAvsdkLogger) {
            writeLog(1, str, str2);
        } else {
            Log.e(str, str2);
        }
    }

    /* renamed from: e */
    public static void m601e(String str, String str2, Exception exc) {
        if (useAvsdkLogger) {
            writeLog(1, str, str2, exc);
        } else {
            Log.e(str, str2, exc);
        }
    }

    /* renamed from: i */
    public static void m602i(String str, String str2) {
        if (useAvsdkLogger) {
            writeLog(2, str, str2);
        } else {
            Log.i(str, str2);
        }
    }

    /* renamed from: i */
    public static void m603i(String str, String str2, Exception exc) {
        if (useAvsdkLogger) {
            writeLog(2, str, str2, exc);
        } else {
            Log.i(str, str2, exc);
        }
    }

    /* renamed from: v */
    public static void m604v(String str, String str2) {
        if (useAvsdkLogger) {
            writeLog(4, str, str2);
        } else {
            Log.i(str, str2);
        }
    }

    /* renamed from: v */
    public static void m605v(String str, String str2, Exception exc) {
        if (useAvsdkLogger) {
            writeLog(4, str, str2, exc);
        } else {
            Log.i(str, str2, exc);
        }
    }

    private static void writeLog(int i, String str, String str2) {
        writeLog(i, str, str2, null);
    }

    private static void writeLog(int i, String str, String str2, Exception exc) {
        String format = String.format("[%s]%s", str, str2);
        if (exc != null) {
            format = format + ", " + Log.getStackTraceString(exc);
        }
        if (GMELibLoader.isLoadLibrary()) {
            nativeWriteLog(i, format);
            return;
        }
        Log.i(str, "so not load. " + format);
    }

    public static String getLogDir() {
        return natvieGetLogDir();
    }

    public static String getDefaultDir() {
        try {
            Context context = GMEJavaInstance.getmActivity();
            return context != null ? context.getExternalFilesDir(null).getAbsolutePath() : "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
