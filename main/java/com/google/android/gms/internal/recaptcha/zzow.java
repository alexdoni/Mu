package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzow {
    public static zzoq zza(ExecutorService executorService) {
        zzoq zzosVar;
        if (executorService instanceof zzoq) {
            return (zzoq) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            zzosVar = new zzov((ScheduledExecutorService) executorService);
        } else {
            zzosVar = new zzos(executorService);
        }
        return zzosVar;
    }

    public static Executor zzb() {
        return zznn.INSTANCE;
    }

    public static Executor zzc(Executor executor) {
        return new zzoz(executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor zzd(Executor executor, zzms<?> zzmsVar) {
        executor.getClass();
        return executor == zznn.INSTANCE ? executor : new zzor(executor, zzmsVar);
    }
}
