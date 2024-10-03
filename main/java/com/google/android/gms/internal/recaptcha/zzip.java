package com.google.android.gms.internal.recaptcha;

import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzip extends ThreadLocal<zzis> {
    @Override // java.lang.ThreadLocal
    protected final /* bridge */ /* synthetic */ zzis initialValue() {
        WeakHashMap weakHashMap;
        WeakHashMap weakHashMap2;
        zzis zzisVar = new zzis(zzdw.zzb());
        Thread currentThread = Thread.currentThread();
        weakHashMap = zziq.zzc;
        synchronized (weakHashMap) {
            weakHashMap2 = zziq.zzc;
            weakHashMap2.put(currentThread, zzisVar);
        }
        return zzisVar;
    }
}
