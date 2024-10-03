package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzjw implements Runnable {
    private final /* synthetic */ zzay zza;
    private final /* synthetic */ zziq zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjw(zziq zziqVar, zzay zzayVar) {
        this.zzb = zziqVar;
        this.zza = zzayVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzb.zzk().zza(this.zza)) {
            this.zzb.zzo().zza(false);
        } else {
            this.zzb.zzj().zzn().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(this.zza.zza()));
        }
    }
}
