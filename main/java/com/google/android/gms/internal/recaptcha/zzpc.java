package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzpc extends zzoo {
    final /* synthetic */ zzpd zza;
    private final Callable zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzpc(zzpd zzpdVar, Callable callable) {
        this.zza = zzpdVar;
        callable.getClass();
        this.zzb = callable;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final Object zza() throws Exception {
        return this.zzb.call();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final String zzb() {
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final void zzd(Throwable th) {
        this.zza.zzu(th);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final void zze(Object obj) {
        this.zza.zzt(obj);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final boolean zzg() {
        return this.zza.isDone();
    }
}
