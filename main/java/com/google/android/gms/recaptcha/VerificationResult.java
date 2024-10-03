package com.google.android.gms.recaptcha;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class VerificationResult {
    public static VerificationResult zza(VerificationHandle verificationHandle, Status status) {
        Preconditions.checkArgument(status.getStatusCode() != 0);
        Preconditions.checkArgument(status.getStatusCode() != 36014);
        return new zzb(status, RecaptchaOptionalObject.ofNullable(verificationHandle), RecaptchaOptionalObject.ofNullable(null));
    }

    public static VerificationResult zzb(String str, Status status) {
        return new zzb(status, RecaptchaOptionalObject.ofNullable(null), RecaptchaOptionalObject.ofNullable(str));
    }

    public abstract Status getVerificationStatus();

    public abstract RecaptchaOptionalObject<String> recaptchaToken();

    public abstract RecaptchaOptionalObject<VerificationHandle> verificationHandle();
}
