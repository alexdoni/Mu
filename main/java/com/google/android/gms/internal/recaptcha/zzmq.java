package com.google.android.gms.internal.recaptcha;

import java.lang.Throwable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzmq<V, X extends Throwable> extends zzmr<V, X, zzng<? super X, ? extends V>, zzop<? extends V>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmq(zzop<? extends V> zzopVar, Class<X> cls, zzng<? super X, ? extends V> zzngVar) {
        super(zzopVar, cls, zzngVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzmr
    final /* bridge */ /* synthetic */ Object zzd(Object obj, Throwable th) throws Exception {
        zzop zza = obj.zza(th);
        zzjn.zzd(zza, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", obj);
        return zza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzmr
    final /* bridge */ /* synthetic */ void zze(Object obj) {
        zzc(obj);
    }
}
