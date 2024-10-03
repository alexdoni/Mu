package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDLoginResult.kt */
/* loaded from: classes2.dex */
public final class LDUserInfo {
    private final String email;
    private final String headPortraitUrl;
    private final String nickname;
    private final Integer status;
    private final String uid;

    public LDUserInfo() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ LDUserInfo copy$default(LDUserInfo lDUserInfo, String str, String str2, String str3, Integer num, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDUserInfo.uid;
        }
        if ((i & 2) != 0) {
            str2 = lDUserInfo.headPortraitUrl;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = lDUserInfo.nickname;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            num = lDUserInfo.status;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            str4 = lDUserInfo.email;
        }
        return lDUserInfo.copy(str, str5, str6, num2, str4);
    }

    public final String component1() {
        return this.uid;
    }

    public final String component2() {
        return this.headPortraitUrl;
    }

    public final String component3() {
        return this.nickname;
    }

    public final Integer component4() {
        return this.status;
    }

    public final String component5() {
        return this.email;
    }

    public final LDUserInfo copy(String str, String str2, String str3, Integer num, String str4) {
        return new LDUserInfo(str, str2, str3, num, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDUserInfo)) {
            return false;
        }
        LDUserInfo lDUserInfo = (LDUserInfo) obj;
        return Intrinsics.areEqual(this.uid, lDUserInfo.uid) && Intrinsics.areEqual(this.headPortraitUrl, lDUserInfo.headPortraitUrl) && Intrinsics.areEqual(this.nickname, lDUserInfo.nickname) && Intrinsics.areEqual(this.status, lDUserInfo.status) && Intrinsics.areEqual(this.email, lDUserInfo.email);
    }

    public int hashCode() {
        String str = this.uid;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.headPortraitUrl;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.nickname;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.status;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.email;
        return hashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "LDUserInfo(uid=" + this.uid + ", headPortraitUrl=" + this.headPortraitUrl + ", nickname=" + this.nickname + ", status=" + this.status + ", email=" + this.email + ')';
    }

    public LDUserInfo(String str, String str2, String str3, Integer num, String str4) {
        this.uid = str;
        this.headPortraitUrl = str2;
        this.nickname = str3;
        this.status = num;
        this.email = str4;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getHeadPortraitUrl() {
        return this.headPortraitUrl;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public /* synthetic */ LDUserInfo(String str, String str2, String str3, Integer num, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? Integer.valueOf(LDUserStatus.NORMAL.getCode()) : num, (i & 16) != 0 ? "" : str4);
    }

    public final Integer getStatus() {
        return this.status;
    }

    public final String getEmail() {
        return this.email;
    }
}
