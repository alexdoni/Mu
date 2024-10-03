package com.p008ld.sdk.util;

import android.util.Log;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.zza;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLog.kt */
/* loaded from: classes2.dex */
public final class LDLog {
    public static final LDLog INSTANCE = new LDLog();

    private LDLog() {
    }

    private final boolean isDebuggable() {
        return LDSdk.getDebugLogEnable();
    }

    @JvmStatic
    /* renamed from: d */
    public static final void m572d(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (INSTANCE.isDebuggable()) {
            Log.d(zza.zza.zza(), msg);
        }
    }

    @JvmStatic
    /* renamed from: e */
    public static final void m573e(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Log.e(zza.zza.zza(), msg);
        zzo zza = zzo.zza.zza();
        if (zza != null) {
            zza.zza(msg);
        }
    }

    @JvmStatic
    /* renamed from: i */
    public static final void m574i(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (INSTANCE.isDebuggable()) {
            Log.i(zza.zza.zza(), msg);
        }
    }

    @JvmStatic
    /* renamed from: v */
    public static final void m575v(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (INSTANCE.isDebuggable()) {
            Log.v(zza.zza.zza(), msg);
        }
    }

    @JvmStatic
    /* renamed from: w */
    public static final void m576w(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (INSTANCE.isDebuggable()) {
            Log.w(zza.zza.zza(), msg);
        }
    }

    @JvmStatic
    public static final void logThrowable2Local(Throwable th) {
        zzo zza = zzo.zza.zza();
        if (zza != null) {
            Intrinsics.checkNotNull(th);
            zza.zza(th);
        }
    }

    @JvmStatic
    public static final void log2Local(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        zzo zza = zzo.zza.zza();
        if (zza != null) {
            zza.zza(msg);
        }
    }
}
