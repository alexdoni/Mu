package com.google.android.gms.internal.measurement;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@21.5.0 */
/* loaded from: classes2.dex */
public final class zzdr implements ThreadFactory {
    private ThreadFactory zza = Executors.defaultThreadFactory();

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zza.newThread(runnable);
        newThread.setName("ScionFrontendApi");
        return newThread;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdr(zzdf zzdfVar) {
    }
}
