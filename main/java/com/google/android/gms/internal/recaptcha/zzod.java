package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzod<V> {
    private final boolean zza;
    private final zzkj<zzop<? extends V>> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzod(boolean z, zzkj zzkjVar, zzob zzobVar) {
        this.zza = z;
        this.zzb = zzkjVar;
    }

    public final <C> zzop<C> zza(Callable<C> callable, Executor executor) {
        return new zznm(this.zzb, this.zza, executor, callable);
    }

    public final <C> zzop<C> zzb(zznf<C> zznfVar, Executor executor) {
        return new zznm(this.zzb, this.zza, executor, zznfVar);
    }
}
