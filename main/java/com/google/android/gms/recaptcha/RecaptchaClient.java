package com.google.android.gms.recaptcha;

import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public interface RecaptchaClient {
    Task<VerificationHandle> challengeAccount(RecaptchaHandle recaptchaHandle, String str);

    Task<Boolean> close(RecaptchaHandle recaptchaHandle);

    Task<RecaptchaResultData> execute(RecaptchaHandle recaptchaHandle, RecaptchaAction recaptchaAction);

    Task<RecaptchaHandle> init(String str);

    Task<VerificationResult> verifyAccount(String str, VerificationHandle verificationHandle);
}
