package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzje implements Runnable {
    private final /* synthetic */ zziq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzje(zziq zziqVar) {
        this.zza = zziqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb.zza();
    }
}
