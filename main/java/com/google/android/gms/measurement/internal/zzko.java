package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzko implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzkh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzko(zzkh zzkhVar, long j) {
        this.zzb = zzkhVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzc().zza(this.zza);
        this.zzb.zza = null;
    }
}
