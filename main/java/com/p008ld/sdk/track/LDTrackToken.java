package com.p008ld.sdk.track;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDTrackToken.kt */
/* loaded from: classes2.dex */
public final class LDTrackToken {
    private String tokenCode;
    private String tokenName;

    public LDTrackToken() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ LDTrackToken copy$default(LDTrackToken lDTrackToken, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDTrackToken.tokenName;
        }
        if ((i & 2) != 0) {
            str2 = lDTrackToken.tokenCode;
        }
        return lDTrackToken.copy(str, str2);
    }

    public final String component1() {
        return this.tokenName;
    }

    public final String component2() {
        return this.tokenCode;
    }

    public final LDTrackToken copy(String tokenName, String tokenCode) {
        Intrinsics.checkNotNullParameter(tokenName, "tokenName");
        Intrinsics.checkNotNullParameter(tokenCode, "tokenCode");
        return new LDTrackToken(tokenName, tokenCode);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDTrackToken)) {
            return false;
        }
        LDTrackToken lDTrackToken = (LDTrackToken) obj;
        return Intrinsics.areEqual(this.tokenName, lDTrackToken.tokenName) && Intrinsics.areEqual(this.tokenCode, lDTrackToken.tokenCode);
    }

    public int hashCode() {
        return (this.tokenName.hashCode() * 31) + this.tokenCode.hashCode();
    }

    public String toString() {
        return "LDTrackToken(tokenName=" + this.tokenName + ", tokenCode=" + this.tokenCode + ')';
    }

    public LDTrackToken(String tokenName, String tokenCode) {
        Intrinsics.checkNotNullParameter(tokenName, "tokenName");
        Intrinsics.checkNotNullParameter(tokenCode, "tokenCode");
        this.tokenName = tokenName;
        this.tokenCode = tokenCode;
    }

    public /* synthetic */ LDTrackToken(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2);
    }

    public final String getTokenName() {
        return this.tokenName;
    }

    public final void setTokenName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tokenName = str;
    }

    public final String getTokenCode() {
        return this.tokenCode;
    }

    public final void setTokenCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tokenCode = str;
    }
}
