package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzju implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zziq zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzju(zziq zziqVar, AtomicReference atomicReference) {
        this.zzb = zziqVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Double.valueOf(this.zzb.zze().zza(this.zzb.zzg().zzad(), zzbi.zzao)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
