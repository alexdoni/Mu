package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLoginResult.kt */
/* loaded from: classes2.dex */
public final class LDThirdUserInfo {
    private final String thirdEmail;
    private final String thirdType;

    public static /* synthetic */ LDThirdUserInfo copy$default(LDThirdUserInfo lDThirdUserInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDThirdUserInfo.thirdEmail;
        }
        if ((i & 2) != 0) {
            str2 = lDThirdUserInfo.thirdType;
        }
        return lDThirdUserInfo.copy(str, str2);
    }

    public final String component1() {
        return this.thirdEmail;
    }

    public final String component2() {
        return this.thirdType;
    }

    public final LDThirdUserInfo copy(String str, String str2) {
        return new LDThirdUserInfo(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDThirdUserInfo)) {
            return false;
        }
        LDThirdUserInfo lDThirdUserInfo = (LDThirdUserInfo) obj;
        return Intrinsics.areEqual(this.thirdEmail, lDThirdUserInfo.thirdEmail) && Intrinsics.areEqual(this.thirdType, lDThirdUserInfo.thirdType);
    }

    public int hashCode() {
        String str = this.thirdEmail;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.thirdType;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "LDThirdUserInfo(thirdEmail=" + this.thirdEmail + ", thirdType=" + this.thirdType + ')';
    }

    public LDThirdUserInfo(String str, String str2) {
        this.thirdEmail = str;
        this.thirdType = str2;
    }

    public final String getThirdEmail() {
        return this.thirdEmail;
    }

    public final String getThirdType() {
        return this.thirdType;
    }
}
