package com.tencent.bugly.proguard;

import android.util.Log;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;
import java.util.Locale;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.al */
/* loaded from: classes3.dex */
public final class C2577al {

    /* renamed from: a */
    public static String f1122a = "CrashReportInfo";

    /* renamed from: b */
    public static String f1123b = "CrashReport";

    /* renamed from: c */
    public static boolean f1124c = false;

    /* renamed from: a */
    private static boolean m778a(int i, String str, Object... objArr) {
        if (!f1124c) {
            return false;
        }
        if (str == null) {
            str = JsonSerializer.Null;
        } else if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i == 0) {
            Log.i(f1123b, str);
            return true;
        }
        if (i == 1) {
            Log.d(f1123b, str);
            return true;
        }
        if (i == 2) {
            Log.w(f1123b, str);
            return true;
        }
        if (i == 3) {
            Log.e(f1123b, str);
            return true;
        }
        if (i != 5) {
            return false;
        }
        Log.i(f1122a, str);
        return true;
    }

    /* renamed from: a */
    private static boolean m779a(int i, Throwable th) {
        if (f1124c) {
            return m778a(i, C2581ap.m822a(th), new Object[0]);
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m781a(String str, Object... objArr) {
        return m778a(0, str, objArr);
    }

    /* renamed from: a */
    public static boolean m780a(Class cls, String str, Object... objArr) {
        return m778a(0, String.format(Locale.US, "[%s] %s", cls.getSimpleName(), str), objArr);
    }

    /* renamed from: b */
    public static boolean m783b(String str, Object... objArr) {
        return m778a(5, str, objArr);
    }

    /* renamed from: c */
    public static boolean m785c(String str, Object... objArr) {
        return m778a(1, str, objArr);
    }

    /* renamed from: d */
    public static boolean m786d(String str, Object... objArr) {
        return m778a(2, str, objArr);
    }

    /* renamed from: a */
    public static boolean m782a(Throwable th) {
        return m779a(2, th);
    }

    /* renamed from: e */
    public static boolean m787e(String str, Object... objArr) {
        return m778a(3, str, objArr);
    }

    /* renamed from: b */
    public static boolean m784b(Throwable th) {
        return m779a(3, th);
    }
}
