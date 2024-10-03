package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zznz<V> extends zznx<V> implements zzop<V> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zznx
    public /* bridge */ /* synthetic */ Future zzb() {
        throw null;
    }

    protected abstract zzop<? extends V> zzc();

    @Override // com.google.android.gms.internal.recaptcha.zzop
    public final void zzp(Runnable runnable, Executor executor) {
        zzc().zzp(runnable, executor);
    }
}
