package com.p008ld.sdk.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LogThreadManager.kt */
/* loaded from: classes2.dex */
public final class zzp {
    public static final zza zza = new zza(null);
    private static zzp zze = new zzp();
    private final Queue<Runnable> zzb;
    private final RejectedExecutionHandler zzc;
    private final ThreadPoolExecutor zzd;

    public /* synthetic */ zzp(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private zzp() {
        this.zzb = new LinkedList();
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() { // from class: com.ld.sdk.util.zzp$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.RejectedExecutionHandler
            public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                zzp.zza(zzp.this, runnable, threadPoolExecutor);
            }
        };
        this.zzc = rejectedExecutionHandler;
        this.zzd = new ThreadPoolExecutor(1, 1, 5000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(500), new ThreadFactory() { // from class: com.ld.sdk.util.zzp$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread zzb;
                zzb = zzp.zzb(runnable);
                return zzb;
            }
        }, rejectedExecutionHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zza(zzp this$0, Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.zzb.size() >= 200) {
            this$0.zzb.poll();
        }
        this$0.zzb.offer(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Thread zzb(Runnable runnable) {
        return new Thread(runnable, "ld-sdk-thread");
    }

    public final void zza(Runnable runnable) {
        if (runnable != null) {
            this.zzd.execute(runnable);
        }
    }

    /* compiled from: LogThreadManager.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        public final zzp zza() {
            if (zzp.zze == null) {
                zzp.zze = new zzp(null);
            }
            return zzp.zze;
        }
    }
}
