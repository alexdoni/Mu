package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzan extends zzz {
    final /* synthetic */ TaskCompletionSource zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzan(zzaq zzaqVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzaa
    public final void zzb(Status status, boolean z) {
        if (status.isSuccess()) {
            this.zza.setResult(Boolean.valueOf(z));
        } else {
            this.zza.setException(new ApiException(status));
        }
    }
}
