package com.google.android.gms.internal.recaptcha;

import android.os.Handler;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdw {
    private static Thread zza;
    private static volatile Handler zzb;

    public static Handler zza() {
        if (zzb == null) {
            zzb = new Handler(Looper.getMainLooper());
        }
        return zzb;
    }

    public static boolean zzb() {
        if (zza == null) {
            zza = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == zza;
    }
}
