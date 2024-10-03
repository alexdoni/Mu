package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzpb extends zzoo<zzop> {
    final /* synthetic */ zzpd zza;
    private final zznf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzpb(zzpd zzpdVar, zznf zznfVar) {
        this.zza = zzpdVar;
        zznfVar.getClass();
        this.zzb = zznfVar;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final /* bridge */ /* synthetic */ zzop zza() throws Exception {
        zzop zza = this.zzb.zza();
        zzjn.zzd(zza, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.zzb);
        return zza;
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
    final /* bridge */ /* synthetic */ void zze(zzop zzopVar) {
        this.zza.zzc(zzopVar);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final boolean zzg() {
        return this.zza.isDone();
    }
}
