package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzov extends zzos implements ScheduledExecutorService, zzoq {
    final ScheduledExecutorService zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzov(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        scheduledExecutorService.getClass();
        this.zza = scheduledExecutorService;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        zzpd zzd = zzpd.zzd(runnable, null);
        return new zzot(zzd, this.zza.schedule(zzd, j, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzou zzouVar = new zzou(runnable);
        return new zzot(zzouVar, this.zza.scheduleAtFixedRate(zzouVar, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzou zzouVar = new zzou(runnable);
        return new zzot(zzouVar, this.zza.scheduleWithFixedDelay(zzouVar, j, j2, timeUnit));
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        zzpd zzpdVar = new zzpd(callable);
        return new zzot(zzpdVar, this.zza.schedule(zzpdVar, j, timeUnit));
    }
}
