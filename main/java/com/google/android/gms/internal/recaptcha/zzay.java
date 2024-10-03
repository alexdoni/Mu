package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzay extends zzu {
    final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzay(zzbc zzbcVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzu, com.google.android.gms.internal.recaptcha.zzac
    public final void zzc(Status status, zzx zzxVar) {
        TaskUtil.setResultOrApiException(status, zzxVar == null ? null : zzxVar.zza(), this.zza);
    }
}
