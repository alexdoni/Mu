package com.p008ld.sdk.bean;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDResult.kt */
/* loaded from: classes2.dex */
public final class CaptchaBean implements Serializable {
    private String captchaData;
    private final String captchaId;
    private final String captchaTips;
    private final int captchaType;
    private final int height;
    private final String requestId;
    private final int width;

    public static /* synthetic */ CaptchaBean copy$default(CaptchaBean captchaBean, int i, String str, String str2, String str3, String str4, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = captchaBean.captchaType;
        }
        if ((i4 & 2) != 0) {
            str = captchaBean.requestId;
        }
        String str5 = str;
        if ((i4 & 4) != 0) {
            str2 = captchaBean.captchaId;
        }
        String str6 = str2;
        if ((i4 & 8) != 0) {
            str3 = captchaBean.captchaTips;
        }
        String str7 = str3;
        if ((i4 & 16) != 0) {
            str4 = captchaBean.captchaData;
        }
        String str8 = str4;
        if ((i4 & 32) != 0) {
            i2 = captchaBean.width;
        }
        int i5 = i2;
        if ((i4 & 64) != 0) {
            i3 = captchaBean.height;
        }
        return captchaBean.copy(i, str5, str6, str7, str8, i5, i3);
    }

    public final int component1() {
        return this.captchaType;
    }

    public final String component2() {
        return this.requestId;
    }

    public final String component3() {
        return this.captchaId;
    }

    public final String component4() {
        return this.captchaTips;
    }

    public final String component5() {
        return this.captchaData;
    }

    public final int component6() {
        return this.width;
    }

    public final int component7() {
        return this.height;
    }

    public final CaptchaBean copy(int i, String requestId, String captchaId, String str, String captchaData, int i2, int i3) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(captchaId, "captchaId");
        Intrinsics.checkNotNullParameter(captchaData, "captchaData");
        return new CaptchaBean(i, requestId, captchaId, str, captchaData, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CaptchaBean)) {
            return false;
        }
        CaptchaBean captchaBean = (CaptchaBean) obj;
        return this.captchaType == captchaBean.captchaType && Intrinsics.areEqual(this.requestId, captchaBean.requestId) && Intrinsics.areEqual(this.captchaId, captchaBean.captchaId) && Intrinsics.areEqual(this.captchaTips, captchaBean.captchaTips) && Intrinsics.areEqual(this.captchaData, captchaBean.captchaData) && this.width == captchaBean.width && this.height == captchaBean.height;
    }

    public int hashCode() {
        int hashCode = ((((this.captchaType * 31) + this.requestId.hashCode()) * 31) + this.captchaId.hashCode()) * 31;
        String str = this.captchaTips;
        return ((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.captchaData.hashCode()) * 31) + this.width) * 31) + this.height;
    }

    public String toString() {
        return "CaptchaBean(captchaType=" + this.captchaType + ", requestId=" + this.requestId + ", captchaId=" + this.captchaId + ", captchaTips=" + this.captchaTips + ", captchaData=" + this.captchaData + ", width=" + this.width + ", height=" + this.height + ')';
    }

    public CaptchaBean(int i, String requestId, String captchaId, String str, String captchaData, int i2, int i3) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(captchaId, "captchaId");
        Intrinsics.checkNotNullParameter(captchaData, "captchaData");
        this.captchaType = i;
        this.requestId = requestId;
        this.captchaId = captchaId;
        this.captchaTips = str;
        this.captchaData = captchaData;
        this.width = i2;
        this.height = i3;
    }

    public final int getCaptchaType() {
        return this.captchaType;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final String getCaptchaId() {
        return this.captchaId;
    }

    public final String getCaptchaTips() {
        return this.captchaTips;
    }

    public final String getCaptchaData() {
        return this.captchaData;
    }

    public final void setCaptchaData(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.captchaData = str;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }
}
