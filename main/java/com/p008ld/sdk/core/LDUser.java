package com.p008ld.sdk.core;

import com.p008ld.sdk.core.bean.LDThirdUserInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import java.util.List;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDUser.kt */
/* loaded from: classes2.dex */
public final class LDUser {
    public static final zza Companion = new zza(null);
    private int bindEmail;
    private String cpToken;
    private String email;
    private String googleAccount;
    private String headPortraitUrl;
    private String loginType;
    private Boolean mustBindEmail;
    private Boolean newUser;
    private String nickname;
    private final String shortToken;
    private String spaceUToken;
    private String spaceUserId;
    private List<LDThirdUserInfo> thirdUserInfos;
    private final String token;
    private final String uid;
    private String userName;

    public LDUser() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, 65535, null);
    }

    @JvmStatic
    public static final void logout(boolean z) {
        Companion.zza(z);
    }

    public final String component1() {
        return this.uid;
    }

    public final Boolean component10() {
        return this.newUser;
    }

    public final String component11() {
        return this.userName;
    }

    public final String component12() {
        return this.googleAccount;
    }

    public final String component13() {
        return this.spaceUserId;
    }

    public final String component14() {
        return this.spaceUToken;
    }

    public final String component15() {
        return this.cpToken;
    }

    public final int component16() {
        return this.bindEmail;
    }

    public final String component2() {
        return this.token;
    }

    public final String component3() {
        return this.shortToken;
    }

    public final String component4() {
        return this.loginType;
    }

    public final String component5() {
        return this.headPortraitUrl;
    }

    public final String component6() {
        return this.nickname;
    }

    public final List<LDThirdUserInfo> component7() {
        return this.thirdUserInfos;
    }

    public final String component8() {
        return this.email;
    }

    public final Boolean component9() {
        return this.mustBindEmail;
    }

    public final LDUser copy(String str, String str2, String str3, String str4, String str5, String str6, List<LDThirdUserInfo> list, String str7, Boolean bool, Boolean bool2, String str8, String str9, String str10, String str11, String str12, int i) {
        return new LDUser(str, str2, str3, str4, str5, str6, list, str7, bool, bool2, str8, str9, str10, str11, str12, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDUser)) {
            return false;
        }
        LDUser lDUser = (LDUser) obj;
        return Intrinsics.areEqual(this.uid, lDUser.uid) && Intrinsics.areEqual(this.token, lDUser.token) && Intrinsics.areEqual(this.shortToken, lDUser.shortToken) && Intrinsics.areEqual(this.loginType, lDUser.loginType) && Intrinsics.areEqual(this.headPortraitUrl, lDUser.headPortraitUrl) && Intrinsics.areEqual(this.nickname, lDUser.nickname) && Intrinsics.areEqual(this.thirdUserInfos, lDUser.thirdUserInfos) && Intrinsics.areEqual(this.email, lDUser.email) && Intrinsics.areEqual(this.mustBindEmail, lDUser.mustBindEmail) && Intrinsics.areEqual(this.newUser, lDUser.newUser) && Intrinsics.areEqual(this.userName, lDUser.userName) && Intrinsics.areEqual(this.googleAccount, lDUser.googleAccount) && Intrinsics.areEqual(this.spaceUserId, lDUser.spaceUserId) && Intrinsics.areEqual(this.spaceUToken, lDUser.spaceUToken) && Intrinsics.areEqual(this.cpToken, lDUser.cpToken) && this.bindEmail == lDUser.bindEmail;
    }

    public int hashCode() {
        String str = this.uid;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.token;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.shortToken;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.loginType;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.headPortraitUrl;
        int hashCode5 = (hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.nickname;
        int hashCode6 = (hashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<LDThirdUserInfo> list = this.thirdUserInfos;
        int hashCode7 = (hashCode6 + (list == null ? 0 : list.hashCode())) * 31;
        String str7 = this.email;
        int hashCode8 = (hashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Boolean bool = this.mustBindEmail;
        int hashCode9 = (hashCode8 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.newUser;
        int hashCode10 = (hashCode9 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        String str8 = this.userName;
        int hashCode11 = (hashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.googleAccount;
        int hashCode12 = (hashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.spaceUserId;
        int hashCode13 = (hashCode12 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.spaceUToken;
        int hashCode14 = (hashCode13 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.cpToken;
        return ((hashCode14 + (str12 != null ? str12.hashCode() : 0)) * 31) + this.bindEmail;
    }

    public String toString() {
        return "LDUser(uid=" + this.uid + ", token=" + this.token + ", shortToken=" + this.shortToken + ", loginType=" + this.loginType + ", headPortraitUrl=" + this.headPortraitUrl + ", nickname=" + this.nickname + ", thirdUserInfos=" + this.thirdUserInfos + ", email=" + this.email + ", mustBindEmail=" + this.mustBindEmail + ", newUser=" + this.newUser + ", userName=" + this.userName + ", googleAccount=" + this.googleAccount + ", spaceUserId=" + this.spaceUserId + ", spaceUToken=" + this.spaceUToken + ", cpToken=" + this.cpToken + ", bindEmail=" + this.bindEmail + ')';
    }

    public LDUser(String str, String str2, String str3, String str4, String str5, String str6, List<LDThirdUserInfo> list, String str7, Boolean bool, Boolean bool2, String str8, String str9, String str10, String str11, String str12, int i) {
        this.uid = str;
        this.token = str2;
        this.shortToken = str3;
        this.loginType = str4;
        this.headPortraitUrl = str5;
        this.nickname = str6;
        this.thirdUserInfos = list;
        this.email = str7;
        this.mustBindEmail = bool;
        this.newUser = bool2;
        this.userName = str8;
        this.googleAccount = str9;
        this.spaceUserId = str10;
        this.spaceUToken = str11;
        this.cpToken = str12;
        this.bindEmail = i;
    }

    public final String getUid() {
        return this.uid;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getShortToken() {
        return this.shortToken;
    }

    public /* synthetic */ LDUser(String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, Boolean bool, Boolean bool2, String str8, String str9, String str10, String str11, String str12, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) == 0 ? str3 : "", (i2 & 8) != 0 ? LoginMode.NONE.getValue() : str4, (i2 & 16) != 0 ? null : str5, (i2 & 32) != 0 ? null : str6, (i2 & 64) != 0 ? null : list, (i2 & 128) != 0 ? null : str7, (i2 & 256) != 0 ? null : bool, (i2 & 512) != 0 ? null : bool2, (i2 & 1024) != 0 ? null : str8, (i2 & 2048) != 0 ? null : str9, (i2 & 4096) != 0 ? null : str10, (i2 & 8192) != 0 ? null : str11, (i2 & 16384) != 0 ? null : str12, (i2 & 32768) != 0 ? 0 : i);
    }

    public final String getLoginType() {
        return this.loginType;
    }

    public final void setLoginType(String str) {
        this.loginType = str;
    }

    public final String getHeadPortraitUrl() {
        return this.headPortraitUrl;
    }

    public final void setHeadPortraitUrl(String str) {
        this.headPortraitUrl = str;
    }

    public final String getNickname() {
        return this.nickname;
    }

    public final void setNickname(String str) {
        this.nickname = str;
    }

    public final List<LDThirdUserInfo> getThirdUserInfos() {
        return this.thirdUserInfos;
    }

    public final void setThirdUserInfos(List<LDThirdUserInfo> list) {
        this.thirdUserInfos = list;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final Boolean getMustBindEmail() {
        return this.mustBindEmail;
    }

    public final void setMustBindEmail(Boolean bool) {
        this.mustBindEmail = bool;
    }

    public final Boolean getNewUser() {
        return this.newUser;
    }

    public final void setNewUser(Boolean bool) {
        this.newUser = bool;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final void setUserName(String str) {
        this.userName = str;
    }

    public final String getGoogleAccount() {
        return this.googleAccount;
    }

    public final void setGoogleAccount(String str) {
        this.googleAccount = str;
    }

    public final String getSpaceUserId() {
        return this.spaceUserId;
    }

    public final void setSpaceUserId(String str) {
        this.spaceUserId = str;
    }

    public final String getSpaceUToken() {
        return this.spaceUToken;
    }

    public final void setSpaceUToken(String str) {
        this.spaceUToken = str;
    }

    public final String getCpToken() {
        return this.cpToken;
    }

    public final void setCpToken(String str) {
        this.cpToken = str;
    }

    public final int getBindEmail() {
        return this.bindEmail;
    }

    public final void setBindEmail(int i) {
        this.bindEmail = i;
    }

    public final boolean isBindEmail() {
        return this.bindEmail == 1;
    }

    /* compiled from: LDUser.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        @JvmStatic
        public final void zza(boolean z) {
            com.p008ld.sdk.core.zza.zza.zza.zza().zza(z);
        }
    }
}
