package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zznu {
    private final AtomicReference<zzop<Void>> zza = new AtomicReference<>(zzok.zza);
    private zznt zzb = new zznt(null);

    private zznu() {
    }

    public static zznu zzc() {
        return new zznu();
    }

    public final <T> zzop<T> zzd(zznf<T> zznfVar, Executor executor) {
        executor.getClass();
        zzns zznsVar = new zzns(executor, this, null);
        zznp zznpVar = new zznp(this, zznsVar, zznfVar);
        zzpa zzd = zzpa.zzd();
        zzop<Void> andSet = this.zza.getAndSet(zzd);
        zzpd zzpdVar = new zzpd(zznpVar);
        andSet.zzp(zzpdVar, zznsVar);
        zzop<T> zzh = zzof.zzh(zzpdVar);
        zznq zznqVar = new zznq(this, zzpdVar, zzd, andSet, zzh, zznsVar);
        zzh.zzp(zznqVar, zznn.INSTANCE);
        zzpdVar.zzp(zznqVar, zznn.INSTANCE);
        return zzh;
    }
}
