package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zznj extends zznl<zzop> {
    final /* synthetic */ zznm zza;
    private final zznf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zznj(zznm zznmVar, zznf zznfVar, Executor executor) {
        super(zznmVar, executor);
        this.zza = zznmVar;
        this.zzc = zznfVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final /* bridge */ /* synthetic */ Object zza() throws Exception {
        zzop zza = this.zzc.zza();
        zzjn.zzd(zza, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.zzc);
        return zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final String zzb() {
        return this.zzc.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zznl
    final /* bridge */ /* synthetic */ void zzc(zzop zzopVar) {
        this.zza.zzc(zzopVar);
    }
}
