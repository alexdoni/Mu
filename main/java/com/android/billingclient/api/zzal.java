package com.android.billingclient.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes.dex */
public final class zzal implements ThreadFactory {
    private final ThreadFactory zza = Executors.defaultThreadFactory();
    private final AtomicInteger zzb = new AtomicInteger(1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzal(BillingClientImpl billingClientImpl) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zza.newThread(runnable);
        newThread.setName("PlayBillingLibrary-" + this.zzb.getAndIncrement());
        return newThread;
    }
}
