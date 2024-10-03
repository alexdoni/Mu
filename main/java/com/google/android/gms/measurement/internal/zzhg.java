package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzhg implements Runnable {
    private final /* synthetic */ zzio zza;
    private final /* synthetic */ zzhf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhg(zzhf zzhfVar, zzio zzioVar) {
        this.zzb = zzhfVar;
        this.zza = zzioVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzhf.zza(this.zzb, this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
