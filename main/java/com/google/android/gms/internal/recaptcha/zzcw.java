package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaNetworkException;
import com.google.android.gms.tasks.OnFailureListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcw implements OnFailureListener {
    final /* synthetic */ zzcv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcw(zzcy zzcyVar, zzcv zzcvVar, byte[] bArr) {
        this.zza = zzcvVar;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        zzak.zza("RecaptchaCAOperation", exc);
        zzcy.zzf(this.zza, new Status(true != (exc instanceof RecaptchaNetworkException) ? 13 : 7, "Failed to challenge the account due to internal errors."));
    }
}
