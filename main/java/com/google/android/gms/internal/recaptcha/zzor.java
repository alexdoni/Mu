package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzor implements Executor {
    final /* synthetic */ Executor zza;
    final /* synthetic */ zzms zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzor(Executor executor, zzms zzmsVar) {
        this.zza = executor;
        this.zzb = zzmsVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        try {
            this.zza.execute(runnable);
        } catch (RejectedExecutionException e) {
            this.zzb.zzu(e);
        }
    }
}
