package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzav implements Runnable {
    private final /* synthetic */ zzif zza;
    private final /* synthetic */ zzaw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzav(zzaw zzawVar, zzif zzifVar) {
        this.zzb = zzawVar;
        this.zza = zzifVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzd();
        if (zzae.zza()) {
            this.zza.zzl().zzb(this);
            return;
        }
        boolean zzc = this.zzb.zzc();
        this.zzb.zzd = 0L;
        if (zzc) {
            this.zzb.zzb();
        }
    }
}
