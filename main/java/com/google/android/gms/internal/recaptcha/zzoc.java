package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzoc<V> implements Runnable {
    final Future<V> zza;
    final zzoa<? super V> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzoc(Future<V> future, zzoa<? super V> zzoaVar) {
        this.zza = future;
        this.zzb = zzoaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Throwable zza;
        Future<V> future = this.zza;
        if (!(future instanceof zzpe) || (zza = zzpf.zza((zzpe) future)) == null) {
            try {
                this.zzb.zzb(zzof.zzl(this.zza));
                return;
            } catch (Error e) {
                e = e;
                this.zzb.zza(e);
                return;
            } catch (RuntimeException e2) {
                e = e2;
                this.zzb.zza(e);
                return;
            } catch (ExecutionException e3) {
                this.zzb.zza(e3.getCause());
                return;
            }
        }
        this.zzb.zza(zza);
    }

    public final String toString() {
        zzjg zza = zzjh.zza(this);
        zza.zza(this.zzb);
        return zza.toString();
    }
}
