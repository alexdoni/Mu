package com.google.android.gms.internal.recaptcha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zznq implements Runnable {
    final /* synthetic */ zzpd zza;
    final /* synthetic */ zzpa zzb;
    final /* synthetic */ zzop zzc;
    final /* synthetic */ zzop zzd;
    final /* synthetic */ zzns zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznq(zznu zznuVar, zzpd zzpdVar, zzpa zzpaVar, zzop zzopVar, zzop zzopVar2, zzns zznsVar) {
        this.zza = zzpdVar;
        this.zzb = zzpaVar;
        this.zzc = zzopVar;
        this.zzd = zzopVar2;
        this.zze = zznsVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zza.isDone()) {
            this.zzb.zzc(this.zzc);
        } else if (this.zzd.isCancelled() && this.zze.compareAndSet(zznr.NOT_RUN, zznr.CANCELLED)) {
            this.zza.cancel(false);
        }
    }
}
