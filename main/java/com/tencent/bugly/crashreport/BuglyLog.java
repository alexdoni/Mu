package com.tencent.bugly.crashreport;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.tencent.bugly.proguard.C2580ao;
import com.tencent.bugly.proguard.C2628p;
import com.xsdk.ab_firstbase.statisics.util.json.JsonSerializer;

/* compiled from: BUGLY */
/* loaded from: classes3.dex */
public class BuglyLog {
    /* renamed from: v */
    public static void m613v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = JsonSerializer.Null;
        }
        if (C2628p.f1469c) {
            Log.v(str, str2);
        }
        C2580ao.m798a(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, str, str2);
    }

    /* renamed from: d */
    public static void m609d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = JsonSerializer.Null;
        }
        if (C2628p.f1469c) {
            Log.d(str, str2);
        }
        C2580ao.m798a("D", str, str2);
    }

    /* renamed from: i */
    public static void m612i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = JsonSerializer.Null;
        }
        if (C2628p.f1469c) {
            Log.i(str, str2);
        }
        C2580ao.m798a("I", str, str2);
    }

    /* renamed from: w */
    public static void m614w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = JsonSerializer.Null;
        }
        if (C2628p.f1469c) {
            Log.w(str, str2);
        }
        C2580ao.m798a(ExifInterface.LONGITUDE_WEST, str, str2);
    }

    /* renamed from: e */
    public static void m610e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = JsonSerializer.Null;
        }
        if (C2628p.f1469c) {
            Log.e(str, str2);
        }
        C2580ao.m798a(ExifInterface.LONGITUDE_EAST, str, str2);
    }

    /* renamed from: e */
    public static void m611e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = JsonSerializer.Null;
        }
        if (C2628p.f1469c) {
            Log.e(str, str2, th);
        }
        C2580ao.m799a(ExifInterface.LONGITUDE_EAST, str, th);
    }

    public static void setCache(int i) {
        C2580ao.m796a(i);
    }
}
