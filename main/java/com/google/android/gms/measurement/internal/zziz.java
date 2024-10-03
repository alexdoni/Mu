package com.google.android.gms.measurement.internal;

import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@21.5.0 */
/* loaded from: classes2.dex */
public final class zziz implements Executor {
    private final /* synthetic */ zziq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziz(zziq zziqVar) {
        this.zza = zziqVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.zza.zzl().zzb(runnable);
    }
}
