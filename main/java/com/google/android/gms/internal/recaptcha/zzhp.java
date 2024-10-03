package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzhp<T> implements Runnable {
    private zznf<T> zza;
    private Executor zzb;

    @Override // java.lang.Runnable
    public final void run() {
        this.zza = null;
        this.zzb = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhp(zznf<T> zznfVar, Executor executor) {
        zznfVar.getClass();
        this.zza = zznfVar;
        executor.getClass();
        this.zzb = executor;
    }
}
