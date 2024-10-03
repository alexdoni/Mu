package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zznx<V> extends zzka implements Future<V> {
    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return zzb().cancel(z);
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        return zzb().get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return zzb().isCancelled();
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return zzb().isDone();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzka
    protected /* bridge */ /* synthetic */ Object zza() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Future<? extends V> zzb();

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return zzb().get(j, timeUnit);
    }
}
