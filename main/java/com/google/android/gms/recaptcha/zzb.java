package com.google.android.gms.recaptcha;

import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzb extends VerificationResult {
    private final Status zza;
    private final RecaptchaOptionalObject<VerificationHandle> zzb;
    private final RecaptchaOptionalObject<String> zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(Status status, RecaptchaOptionalObject<VerificationHandle> recaptchaOptionalObject, RecaptchaOptionalObject<String> recaptchaOptionalObject2) {
        if (status != null) {
            this.zza = status;
            this.zzb = recaptchaOptionalObject;
            this.zzc = recaptchaOptionalObject2;
            return;
        }
        throw new NullPointerException("Null getVerificationStatus");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof VerificationResult) {
            VerificationResult verificationResult = (VerificationResult) obj;
            if (this.zza.equals(verificationResult.getVerificationStatus()) && this.zzb.equals(verificationResult.verificationHandle()) && this.zzc.equals(verificationResult.recaptchaToken())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.recaptcha.VerificationResult
    public final Status getVerificationStatus() {
        return this.zza;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003) ^ this.zzc.hashCode();
    }

    @Override // com.google.android.gms.recaptcha.VerificationResult
    public final RecaptchaOptionalObject<String> recaptchaToken() {
        return this.zzc;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zza);
        String valueOf2 = String.valueOf(this.zzb);
        String valueOf3 = String.valueOf(this.zzc);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 80 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("VerificationResult{getVerificationStatus=");
        sb.append(valueOf);
        sb.append(", verificationHandle=");
        sb.append(valueOf2);
        sb.append(", recaptchaToken=");
        sb.append(valueOf3);
        sb.append("}");
        return sb.toString();
    }

    @Override // com.google.android.gms.recaptcha.VerificationResult
    public final RecaptchaOptionalObject<VerificationHandle> verificationHandle() {
        return this.zzb;
    }
}
