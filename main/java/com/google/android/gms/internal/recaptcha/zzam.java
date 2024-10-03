package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzam extends zzu {
    final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzam(zzaq zzaqVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzu, com.google.android.gms.internal.recaptcha.zzac
    public final void zzc(Status status, zzx zzxVar) {
        if (zzxVar == null) {
            this.zza.setException(new ApiException(status));
        } else {
            this.zza.setResult(zzxVar.zza());
        }
    }
}
