package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: GiftPackageInfo.kt */
/* loaded from: classes2.dex */
public final class GiftPackage {
    private final String appDownloadUrl;
    private final int appPackageId;
    private final String banner;
    private boolean claimMark;
    private final int commonType;
    private final String expireDate;
    private final int gameId;
    private final String gameName;
    private final String gameSltUrl;
    private final int giftStatus;

    /* renamed from: id */
    private final int f681id;
    private final String jumpUrl;
    private final int limitType;
    private String packageCode;
    private final String packageContent;
    private final String packageDesc;
    private final String packageFunction;
    private final String packageName;
    private final String packageSltUrl;
    private final int platform;
    private final int point;
    private final int status;
    private final int totalAmount;
    private final int unusedAmount;
    private final int usedAmount;

    public final int component1() {
        return this.f681id;
    }

    public final String component10() {
        return this.packageFunction;
    }

    public final boolean component11() {
        return this.claimMark;
    }

    public final String component12() {
        return this.packageCode;
    }

    public final String component13() {
        return this.expireDate;
    }

    public final int component14() {
        return this.status;
    }

    public final String component15() {
        return this.jumpUrl;
    }

    public final int component16() {
        return this.limitType;
    }

    public final int component17() {
        return this.commonType;
    }

    public final int component18() {
        return this.point;
    }

    public final int component19() {
        return this.unusedAmount;
    }

    public final int component2() {
        return this.gameId;
    }

    public final int component20() {
        return this.usedAmount;
    }

    public final int component21() {
        return this.totalAmount;
    }

    public final int component22() {
        return this.giftStatus;
    }

    public final int component23() {
        return this.appPackageId;
    }

    public final String component24() {
        return this.appDownloadUrl;
    }

    public final String component25() {
        return this.gameSltUrl;
    }

    public final String component3() {
        return this.gameName;
    }

    public final int component4() {
        return this.platform;
    }

    public final String component5() {
        return this.packageSltUrl;
    }

    public final String component6() {
        return this.banner;
    }

    public final String component7() {
        return this.packageName;
    }

    public final String component8() {
        return this.packageDesc;
    }

    public final String component9() {
        return this.packageContent;
    }

    public final GiftPackage copy(int i, int i2, String gameName, int i3, String packageSltUrl, String banner, String packageName, String packageDesc, String packageContent, String packageFunction, boolean z, String packageCode, String expireDate, int i4, String jumpUrl, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, String appDownloadUrl, String gameSltUrl) {
        Intrinsics.checkNotNullParameter(gameName, "gameName");
        Intrinsics.checkNotNullParameter(packageSltUrl, "packageSltUrl");
        Intrinsics.checkNotNullParameter(banner, "banner");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(packageDesc, "packageDesc");
        Intrinsics.checkNotNullParameter(packageContent, "packageContent");
        Intrinsics.checkNotNullParameter(packageFunction, "packageFunction");
        Intrinsics.checkNotNullParameter(packageCode, "packageCode");
        Intrinsics.checkNotNullParameter(expireDate, "expireDate");
        Intrinsics.checkNotNullParameter(jumpUrl, "jumpUrl");
        Intrinsics.checkNotNullParameter(appDownloadUrl, "appDownloadUrl");
        Intrinsics.checkNotNullParameter(gameSltUrl, "gameSltUrl");
        return new GiftPackage(i, i2, gameName, i3, packageSltUrl, banner, packageName, packageDesc, packageContent, packageFunction, z, packageCode, expireDate, i4, jumpUrl, i5, i6, i7, i8, i9, i10, i11, i12, appDownloadUrl, gameSltUrl);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GiftPackage)) {
            return false;
        }
        GiftPackage giftPackage = (GiftPackage) obj;
        return this.f681id == giftPackage.f681id && this.gameId == giftPackage.gameId && Intrinsics.areEqual(this.gameName, giftPackage.gameName) && this.platform == giftPackage.platform && Intrinsics.areEqual(this.packageSltUrl, giftPackage.packageSltUrl) && Intrinsics.areEqual(this.banner, giftPackage.banner) && Intrinsics.areEqual(this.packageName, giftPackage.packageName) && Intrinsics.areEqual(this.packageDesc, giftPackage.packageDesc) && Intrinsics.areEqual(this.packageContent, giftPackage.packageContent) && Intrinsics.areEqual(this.packageFunction, giftPackage.packageFunction) && this.claimMark == giftPackage.claimMark && Intrinsics.areEqual(this.packageCode, giftPackage.packageCode) && Intrinsics.areEqual(this.expireDate, giftPackage.expireDate) && this.status == giftPackage.status && Intrinsics.areEqual(this.jumpUrl, giftPackage.jumpUrl) && this.limitType == giftPackage.limitType && this.commonType == giftPackage.commonType && this.point == giftPackage.point && this.unusedAmount == giftPackage.unusedAmount && this.usedAmount == giftPackage.usedAmount && this.totalAmount == giftPackage.totalAmount && this.giftStatus == giftPackage.giftStatus && this.appPackageId == giftPackage.appPackageId && Intrinsics.areEqual(this.appDownloadUrl, giftPackage.appDownloadUrl) && Intrinsics.areEqual(this.gameSltUrl, giftPackage.gameSltUrl);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((((((((((((((this.f681id * 31) + this.gameId) * 31) + this.gameName.hashCode()) * 31) + this.platform) * 31) + this.packageSltUrl.hashCode()) * 31) + this.banner.hashCode()) * 31) + this.packageName.hashCode()) * 31) + this.packageDesc.hashCode()) * 31) + this.packageContent.hashCode()) * 31) + this.packageFunction.hashCode()) * 31;
        boolean z = this.claimMark;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((((((((((((((((((((((((((((hashCode + i) * 31) + this.packageCode.hashCode()) * 31) + this.expireDate.hashCode()) * 31) + this.status) * 31) + this.jumpUrl.hashCode()) * 31) + this.limitType) * 31) + this.commonType) * 31) + this.point) * 31) + this.unusedAmount) * 31) + this.usedAmount) * 31) + this.totalAmount) * 31) + this.giftStatus) * 31) + this.appPackageId) * 31) + this.appDownloadUrl.hashCode()) * 31) + this.gameSltUrl.hashCode();
    }

    public String toString() {
        return "GiftPackage(id=" + this.f681id + ", gameId=" + this.gameId + ", gameName=" + this.gameName + ", platform=" + this.platform + ", packageSltUrl=" + this.packageSltUrl + ", banner=" + this.banner + ", packageName=" + this.packageName + ", packageDesc=" + this.packageDesc + ", packageContent=" + this.packageContent + ", packageFunction=" + this.packageFunction + ", claimMark=" + this.claimMark + ", packageCode=" + this.packageCode + ", expireDate=" + this.expireDate + ", status=" + this.status + ", jumpUrl=" + this.jumpUrl + ", limitType=" + this.limitType + ", commonType=" + this.commonType + ", point=" + this.point + ", unusedAmount=" + this.unusedAmount + ", usedAmount=" + this.usedAmount + ", totalAmount=" + this.totalAmount + ", giftStatus=" + this.giftStatus + ", appPackageId=" + this.appPackageId + ", appDownloadUrl=" + this.appDownloadUrl + ", gameSltUrl=" + this.gameSltUrl + ')';
    }

    public GiftPackage(int i, int i2, String gameName, int i3, String packageSltUrl, String banner, String packageName, String packageDesc, String packageContent, String packageFunction, boolean z, String packageCode, String expireDate, int i4, String jumpUrl, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, String appDownloadUrl, String gameSltUrl) {
        Intrinsics.checkNotNullParameter(gameName, "gameName");
        Intrinsics.checkNotNullParameter(packageSltUrl, "packageSltUrl");
        Intrinsics.checkNotNullParameter(banner, "banner");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(packageDesc, "packageDesc");
        Intrinsics.checkNotNullParameter(packageContent, "packageContent");
        Intrinsics.checkNotNullParameter(packageFunction, "packageFunction");
        Intrinsics.checkNotNullParameter(packageCode, "packageCode");
        Intrinsics.checkNotNullParameter(expireDate, "expireDate");
        Intrinsics.checkNotNullParameter(jumpUrl, "jumpUrl");
        Intrinsics.checkNotNullParameter(appDownloadUrl, "appDownloadUrl");
        Intrinsics.checkNotNullParameter(gameSltUrl, "gameSltUrl");
        this.f681id = i;
        this.gameId = i2;
        this.gameName = gameName;
        this.platform = i3;
        this.packageSltUrl = packageSltUrl;
        this.banner = banner;
        this.packageName = packageName;
        this.packageDesc = packageDesc;
        this.packageContent = packageContent;
        this.packageFunction = packageFunction;
        this.claimMark = z;
        this.packageCode = packageCode;
        this.expireDate = expireDate;
        this.status = i4;
        this.jumpUrl = jumpUrl;
        this.limitType = i5;
        this.commonType = i6;
        this.point = i7;
        this.unusedAmount = i8;
        this.usedAmount = i9;
        this.totalAmount = i10;
        this.giftStatus = i11;
        this.appPackageId = i12;
        this.appDownloadUrl = appDownloadUrl;
        this.gameSltUrl = gameSltUrl;
    }

    public final int getId() {
        return this.f681id;
    }

    public final int getGameId() {
        return this.gameId;
    }

    public final String getGameName() {
        return this.gameName;
    }

    public final int getPlatform() {
        return this.platform;
    }

    public final String getPackageSltUrl() {
        return this.packageSltUrl;
    }

    public final String getBanner() {
        return this.banner;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getPackageDesc() {
        return this.packageDesc;
    }

    public final String getPackageContent() {
        return this.packageContent;
    }

    public final String getPackageFunction() {
        return this.packageFunction;
    }

    public final boolean getClaimMark() {
        return this.claimMark;
    }

    public final void setClaimMark(boolean z) {
        this.claimMark = z;
    }

    public final String getPackageCode() {
        return this.packageCode;
    }

    public final void setPackageCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.packageCode = str;
    }

    public final String getExpireDate() {
        return this.expireDate;
    }

    public final int getStatus() {
        return this.status;
    }

    public final String getJumpUrl() {
        return this.jumpUrl;
    }

    public final int getLimitType() {
        return this.limitType;
    }

    public final int getCommonType() {
        return this.commonType;
    }

    public final int getPoint() {
        return this.point;
    }

    public final int getUnusedAmount() {
        return this.unusedAmount;
    }

    public final int getUsedAmount() {
        return this.usedAmount;
    }

    public final int getTotalAmount() {
        return this.totalAmount;
    }

    public final int getGiftStatus() {
        return this.giftStatus;
    }

    public final int getAppPackageId() {
        return this.appPackageId;
    }

    public final String getAppDownloadUrl() {
        return this.appDownloadUrl;
    }

    public final String getGameSltUrl() {
        return this.gameSltUrl;
    }
}
