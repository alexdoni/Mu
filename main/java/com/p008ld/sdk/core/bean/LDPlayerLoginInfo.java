package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLoginResult.kt */
/* loaded from: classes2.dex */
public final class LDPlayerLoginInfo {
    private String identityType;
    private String middlewareToken;
    private String middlewareUid;

    public static /* synthetic */ LDPlayerLoginInfo copy$default(LDPlayerLoginInfo lDPlayerLoginInfo, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDPlayerLoginInfo.middlewareUid;
        }
        if ((i & 2) != 0) {
            str2 = lDPlayerLoginInfo.middlewareToken;
        }
        if ((i & 4) != 0) {
            str3 = lDPlayerLoginInfo.identityType;
        }
        return lDPlayerLoginInfo.copy(str, str2, str3);
    }

    public final String component1() {
        return this.middlewareUid;
    }

    public final String component2() {
        return this.middlewareToken;
    }

    public final String component3() {
        return this.identityType;
    }

    public final LDPlayerLoginInfo copy(String middlewareUid, String middlewareToken, String identityType) {
        Intrinsics.checkNotNullParameter(middlewareUid, "middlewareUid");
        Intrinsics.checkNotNullParameter(middlewareToken, "middlewareToken");
        Intrinsics.checkNotNullParameter(identityType, "identityType");
        return new LDPlayerLoginInfo(middlewareUid, middlewareToken, identityType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDPlayerLoginInfo)) {
            return false;
        }
        LDPlayerLoginInfo lDPlayerLoginInfo = (LDPlayerLoginInfo) obj;
        return Intrinsics.areEqual(this.middlewareUid, lDPlayerLoginInfo.middlewareUid) && Intrinsics.areEqual(this.middlewareToken, lDPlayerLoginInfo.middlewareToken) && Intrinsics.areEqual(this.identityType, lDPlayerLoginInfo.identityType);
    }

    public int hashCode() {
        return (((this.middlewareUid.hashCode() * 31) + this.middlewareToken.hashCode()) * 31) + this.identityType.hashCode();
    }

    public String toString() {
        return "LDPlayerLoginInfo(middlewareUid=" + this.middlewareUid + ", middlewareToken=" + this.middlewareToken + ", identityType=" + this.identityType + ')';
    }

    public LDPlayerLoginInfo(String middlewareUid, String middlewareToken, String identityType) {
        Intrinsics.checkNotNullParameter(middlewareUid, "middlewareUid");
        Intrinsics.checkNotNullParameter(middlewareToken, "middlewareToken");
        Intrinsics.checkNotNullParameter(identityType, "identityType");
        this.middlewareUid = middlewareUid;
        this.middlewareToken = middlewareToken;
        this.identityType = identityType;
    }

    public final String getMiddlewareUid() {
        return this.middlewareUid;
    }

    public final void setMiddlewareUid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.middlewareUid = str;
    }

    public final String getMiddlewareToken() {
        return this.middlewareToken;
    }

    public final void setMiddlewareToken(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.middlewareToken = str;
    }

    public final String getIdentityType() {
        return this.identityType;
    }

    public final void setIdentityType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.identityType = str;
    }
}
