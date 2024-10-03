package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zznl<T> extends zzoo<T> {
    private final Executor zza;
    final /* synthetic */ zznm zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zznl(zznm zznmVar, Executor executor) {
        this.zzb = zznmVar;
        executor.getClass();
        this.zza = executor;
    }

    abstract void zzc(T t);

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final void zzd(Throwable th) {
        zznm.zzH(this.zzb, null);
        if (th instanceof ExecutionException) {
            this.zzb.zzu(((ExecutionException) th).getCause());
        } else if (th instanceof CancellationException) {
            this.zzb.cancel(false);
        } else {
            this.zzb.zzu(th);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final void zze(T t) {
        zznm.zzH(this.zzb, null);
        zzc((zznl<T>) t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf() {
        try {
            this.zza.execute(this);
        } catch (RejectedExecutionException e) {
            this.zzb.zzu(e);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final boolean zzg() {
        return this.zzb.isDone();
    }
}
