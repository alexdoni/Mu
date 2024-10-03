package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzpd<V> extends zznv<V> implements RunnableFuture<V> {

    @CheckForNull
    private volatile zzoo<?> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzpd(zznf<V> zznfVar) {
        this.zza = new zzpb(this, zznfVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <V> zzpd<V> zzd(Runnable runnable, V v) {
        return new zzpd<>(Executors.callable(runnable, v));
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public final void run() {
        zzoo<?> zzooVar = this.zza;
        if (zzooVar != null) {
            zzooVar.run();
        }
        this.zza = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    @CheckForNull
    public final String zza() {
        zzoo<?> zzooVar = this.zza;
        if (zzooVar != null) {
            String valueOf = String.valueOf(zzooVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
            sb.append("task=[");
            sb.append(valueOf);
            sb.append("]");
            return sb.toString();
        }
        return super.zza();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzb() {
        zzoo<?> zzooVar;
        if (zzv() && (zzooVar = this.zza) != null) {
            zzooVar.zzh();
        }
        this.zza = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzpd(Callable<V> callable) {
        this.zza = new zzpc(this, callable);
    }
}
