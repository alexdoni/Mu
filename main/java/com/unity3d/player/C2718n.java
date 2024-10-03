package com.unity3d.player;

import android.os.Build;
import java.lang.Thread;

/* renamed from: com.unity3d.player.n */
/* loaded from: classes3.dex */
final class C2718n implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private volatile Thread.UncaughtExceptionHandler f1843a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized boolean m1351a() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == this) {
            return false;
        }
        this.f1843a = defaultUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(this);
        return true;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        try {
            Error error = new Error(String.format("FATAL EXCEPTION [%s]\n", thread.getName()) + String.format("Unity version     : %s\n", "2019.4.34f1") + String.format("Device model      : %s %s\n", Build.MANUFACTURER, Build.MODEL) + String.format("Device fingerprint: %s\n", Build.FINGERPRINT));
            error.setStackTrace(new StackTraceElement[0]);
            error.initCause(th);
            this.f1843a.uncaughtException(thread, error);
        } catch (Throwable unused) {
            this.f1843a.uncaughtException(thread, th);
        }
    }
}
