package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzns extends AtomicReference<zznr> implements Executor, Runnable {

    @CheckForNull
    zznu zza;

    @CheckForNull
    Executor zzb;

    @CheckForNull
    Runnable zzc;

    @CheckForNull
    Thread zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzns(Executor executor, zznu zznuVar, zzno zznoVar) {
        super(zznr.NOT_RUN);
        this.zzb = executor;
        this.zza = zznuVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (get() == zznr.CANCELLED) {
            this.zzb = null;
            this.zza = null;
            return;
        }
        this.zzd = Thread.currentThread();
        try {
            zznu zznuVar = this.zza;
            zznuVar.getClass();
            zznt zza = zznu.zza(zznuVar);
            if (zza.zza == this.zzd) {
                this.zza = null;
                zzjn.zzi(zza.zzb == null);
                zza.zzb = runnable;
                Executor executor = this.zzb;
                executor.getClass();
                zza.zzc = executor;
                this.zzb = null;
            } else {
                Executor executor2 = this.zzb;
                executor2.getClass();
                this.zzb = null;
                this.zzc = runnable;
                executor2.execute(this);
            }
        } finally {
            this.zzd = null;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Thread currentThread = Thread.currentThread();
        if (currentThread != this.zzd) {
            Runnable runnable = this.zzc;
            runnable.getClass();
            this.zzc = null;
            runnable.run();
            return;
        }
        zznt zzntVar = new zznt(null);
        zzntVar.zza = currentThread;
        zznu zznuVar = this.zza;
        zznuVar.getClass();
        zznu.zzb(zznuVar, zzntVar);
        this.zza = null;
        try {
            Runnable runnable2 = this.zzc;
            runnable2.getClass();
            this.zzc = null;
            runnable2.run();
            while (true) {
                Runnable runnable3 = zzntVar.zzb;
                boolean z = true;
                boolean z2 = runnable3 != null;
                Executor executor = zzntVar.zzc;
                if (executor == null) {
                    z = false;
                }
                if (!z || !z2) {
                    return;
                }
                zzntVar.zzb = null;
                zzntVar.zzc = null;
                executor.execute(runnable3);
            }
        } finally {
            zzntVar.zza = null;
        }
    }
}
