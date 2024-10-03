package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzmu<I, O> extends zzmw<I, O, zzng<? super I, ? extends O>, zzop<? extends O>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmu(zzop<? extends I> zzopVar, zzng<? super I, ? extends O> zzngVar) {
        super(zzopVar, zzngVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmw
    final /* bridge */ /* synthetic */ Object zzd(Object obj, Object obj2) throws Exception {
        zzng zzngVar = (zzng) obj;
        zzop<O> zza = zzngVar.zza(obj2);
        zzjn.zzd(zza, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzngVar);
        return zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzmw
    final /* bridge */ /* synthetic */ void zze(Object obj) {
        zzc((zzop) obj);
    }
}
