package com.google.android.gms.internal.recaptcha;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzoz implements Executor {
    private static final Logger zza = Logger.getLogger(zzoz.class.getName());
    private final Executor zzb;
    private final Deque<Runnable> zzc = new ArrayDeque();
    private int zzf = 1;
    private long zzd = 0;
    private final zzoy zze = new zzoy(this, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzoz(Executor executor) {
        executor.getClass();
        this.zzb = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long zza(zzoz zzozVar) {
        long j = zzozVar.zzd;
        zzozVar.zzd = 1 + j;
        return j;
    }

    public final String toString() {
        int identityHashCode = System.identityHashCode(this);
        String valueOf = String.valueOf(this.zzb);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
        sb.append("SequentialExecutor@");
        sb.append(identityHashCode);
        sb.append("{");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.getClass();
        synchronized (this.zzc) {
            int i = this.zzf;
            if (i != 4 && i != 3) {
                long j = this.zzd;
                zzox zzoxVar = new zzox(this, runnable);
                this.zzc.add(zzoxVar);
                this.zzf = 2;
                try {
                    this.zzb.execute(this.zze);
                    if (this.zzf != 2) {
                        return;
                    }
                    synchronized (this.zzc) {
                        if (this.zzd == j && this.zzf == 2) {
                            this.zzf = 3;
                        }
                    }
                    return;
                } catch (Error | RuntimeException e) {
                    synchronized (this.zzc) {
                        int i2 = this.zzf;
                        boolean z = false;
                        if ((i2 == 1 || i2 == 2) && this.zzc.removeLastOccurrence(zzoxVar)) {
                            z = true;
                        }
                        if (!(e instanceof RejectedExecutionException) || z) {
                            throw e;
                        }
                    }
                    return;
                }
            }
            this.zzc.add(runnable);
        }
    }
}
