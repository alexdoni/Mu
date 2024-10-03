package com.google.firebase.crashlytics.internal;

import android.util.Log;

/* loaded from: classes2.dex */
public class Logger {
    private int logLevel = 4;
    private final String tag;
    public static final String TAG = "FirebaseCrashlytics";
    static final Logger DEFAULT_LOGGER = new Logger(TAG);

    public Logger(String str) {
        this.tag = str;
    }

    public static Logger getLogger() {
        return DEFAULT_LOGGER;
    }

    private boolean canLog(int i) {
        return this.logLevel <= i || Log.isLoggable(this.tag, i);
    }

    /* renamed from: d */
    public void m539d(String str, Throwable th) {
        if (canLog(3)) {
            Log.d(this.tag, str, th);
        }
    }

    /* renamed from: v */
    public void m545v(String str, Throwable th) {
        if (canLog(2)) {
            Log.v(this.tag, str, th);
        }
    }

    /* renamed from: i */
    public void m543i(String str, Throwable th) {
        if (canLog(4)) {
            Log.i(this.tag, str, th);
        }
    }

    /* renamed from: w */
    public void m547w(String str, Throwable th) {
        if (canLog(5)) {
            Log.w(this.tag, str, th);
        }
    }

    /* renamed from: e */
    public void m541e(String str, Throwable th) {
        if (canLog(6)) {
            Log.e(this.tag, str, th);
        }
    }

    /* renamed from: d */
    public void m538d(String str) {
        m539d(str, null);
    }

    /* renamed from: v */
    public void m544v(String str) {
        m545v(str, null);
    }

    /* renamed from: i */
    public void m542i(String str) {
        m543i(str, null);
    }

    /* renamed from: w */
    public void m546w(String str) {
        m547w(str, null);
    }

    /* renamed from: e */
    public void m540e(String str) {
        m541e(str, null);
    }

    public void log(int i, String str) {
        log(i, str, false);
    }

    public void log(int i, String str, boolean z) {
        if (z || canLog(i)) {
            Log.println(i, this.tag, str);
        }
    }
}
