package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.VerificationHandle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzao extends zzcv {
    final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzao(zzaq zzaqVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzcv
    public final void zza(Status status, VerificationHandle verificationHandle) {
        if (verificationHandle == null) {
            this.zza.setException(new ApiException(status));
        } else {
            this.zza.setResult(verificationHandle);
        }
    }
}
