package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.play:integrity@@1.0.1 */
/* loaded from: classes2.dex */
public abstract class zzj implements Runnable {
    private final TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj() {
        this.zza = null;
    }

    public zzj(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            zzb();
        } catch (Exception e) {
            zza(e);
        }
    }

    public void zza(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.zza;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }

    protected abstract void zzb();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final TaskCompletionSource zzc() {
        return this.zza;
    }
}
