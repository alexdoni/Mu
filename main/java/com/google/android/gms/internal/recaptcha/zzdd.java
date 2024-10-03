package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaNetworkException;
import com.google.android.gms.tasks.OnFailureListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzdd implements OnFailureListener {
    final /* synthetic */ zzdc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdd(zzdf zzdfVar, zzdc zzdcVar, byte[] bArr) {
        this.zza = zzdcVar;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        zzdf.zzf(this.zza, new Status(true != (exc instanceof RecaptchaNetworkException) ? 13 : 7, "Failed to verify the account due to internal errors."));
    }
}
