package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzjo implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb = null;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zziq zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjo(zziq zziqVar, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zze = zziqVar;
        this.zza = atomicReference;
        this.zzc = str2;
        this.zzd = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zzu.zzr().zza(this.zza, (String) null, this.zzc, this.zzd);
    }
}
