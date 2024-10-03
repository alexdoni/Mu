package com.p008ld.sdk.internal;

import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.zzd.zzc;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDCaptchaException.kt */
/* loaded from: classes2.dex */
public class LDCaptchaException extends LDException {
    public static final Companion Companion = new Companion(null);
    public static final long serialVersionUID = 1;
    private final CaptchaBean captchaBean;

    public final CaptchaBean getCaptchaBean() {
        return this.captchaBean;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LDCaptchaException(CaptchaBean captchaBean, String errorMessage) {
        super(errorMessage);
        Intrinsics.checkNotNullParameter(captchaBean, "captchaBean");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.captchaBean = captchaBean;
    }

    @Override // com.p008ld.sdk.internal.LDException
    public int getErrorCode() {
        return zzc.ERROR_CAPTCHA.zza();
    }

    @Override // com.p008ld.sdk.internal.LDException, java.lang.Throwable
    public String toString() {
        return " " + getMessage() + " : " + getErrorCode() + " , captchaBean = " + this.captchaBean;
    }

    /* compiled from: LDCaptchaException.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
