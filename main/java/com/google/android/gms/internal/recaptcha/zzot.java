package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzot<V> extends zzny<V> implements ScheduledFuture<V>, zzop {
    private final ScheduledFuture<?> zza;

    public zzot(zzop<V> zzopVar, ScheduledFuture<?> scheduledFuture) {
        super(zzopVar);
        this.zza = scheduledFuture;
    }

    @Override // com.google.android.gms.internal.recaptcha.zznx, java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        boolean cancel = zzb().cancel(z);
        if (cancel) {
            this.zza.cancel(z);
        }
        return cancel;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Delayed delayed) {
        return this.zza.compareTo(delayed);
    }

    @Override // java.util.concurrent.Delayed
    public final long getDelay(TimeUnit timeUnit) {
        return this.zza.getDelay(timeUnit);
    }
}
