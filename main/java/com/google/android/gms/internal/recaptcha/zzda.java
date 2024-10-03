package com.google.android.gms.internal.recaptcha;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.recaptcha.RecaptchaStatusCodes;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzda {
    static final zzkl<zzvw, Status> zza;

    static {
        zzkk zzkkVar = new zzkk();
        zzkkVar.zza(zzvw.NO_ERROR, new Status(0));
        zzkkVar.zza(zzvw.CHALLENGE_EXPIRED, new Status(RecaptchaStatusCodes.RECAPTCHA_2FA_CHALLENGE_EXPIRED, "2FA challenge has expired."));
        zzkkVar.zza(zzvw.INVALID_REQUEST_TOKEN, new Status(RecaptchaStatusCodes.RECAPTCHA_2FA_INVALID_REQUEST_TOKEN, "Invalid request token."));
        zzkkVar.zza(zzvw.INVALID_PIN, new Status(RecaptchaStatusCodes.RECAPTCHA_2FA_INVALID_PIN, "Invalid pin format."));
        zzkkVar.zza(zzvw.PIN_MISMATCH, new Status(RecaptchaStatusCodes.RECAPTCHA_2FA_PIN_MISMATCH, "Incorrect pin."));
        zzkkVar.zza(zzvw.ATTEMPTS_EXHAUSTED, new Status(RecaptchaStatusCodes.RECAPTCHA_2FA_ATTEMPTS_EXHAUSTED, "All verification attempts are exhausted."));
        zzkkVar.zza(zzvw.ABORTED, new Status(RecaptchaStatusCodes.RECAPTCHA_2FA_ABORTED, "Operation was aborted, please use the token with the enterprise server to get more information."));
        zza = zzkkVar.zzb();
    }

    public static Status zza(zzvw zzvwVar) {
        zzkl<zzvw, Status> zzklVar = zza;
        if (zzklVar.containsKey(zzvwVar)) {
            return zzklVar.get(zzvwVar);
        }
        return new Status(13, "Internal Error.");
    }
}
