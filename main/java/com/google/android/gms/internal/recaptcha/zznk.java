package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zznk extends zznl {
    final /* synthetic */ zznm zza;
    private final Callable zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zznk(zznm zznmVar, Callable callable, Executor executor) {
        super(zznmVar, executor);
        this.zza = zznmVar;
        callable.getClass();
        this.zzc = callable;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final Object zza() throws Exception {
        return this.zzc.call();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzoo
    final String zzb() {
        return this.zzc.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zznl
    final void zzc(Object obj) {
        this.zza.zzt(obj);
    }
}
