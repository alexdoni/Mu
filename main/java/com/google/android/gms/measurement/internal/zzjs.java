package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzjs implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zziq zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjs(zziq zziqVar, AtomicReference atomicReference) {
        this.zzb = zziqVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Long.valueOf(this.zzb.zze().zzc(this.zzb.zzg().zzad(), zzbi.zzam)));
            } finally {
                this.zza.notify();
            }
        }
    }
}
