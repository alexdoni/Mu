package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: GiftPackageInfo.kt */
/* loaded from: classes2.dex */
public final class ClaimGiftBagInfo {

    /* renamed from: package, reason: not valid java name */
    private final GiftPackage f2894package;

    public static /* synthetic */ ClaimGiftBagInfo copy$default(ClaimGiftBagInfo claimGiftBagInfo, GiftPackage giftPackage, int i, Object obj) {
        if ((i & 1) != 0) {
            giftPackage = claimGiftBagInfo.f2894package;
        }
        return claimGiftBagInfo.copy(giftPackage);
    }

    public final GiftPackage component1() {
        return this.f2894package;
    }

    public final ClaimGiftBagInfo copy(GiftPackage giftPackage) {
        Intrinsics.checkNotNullParameter(giftPackage, "package");
        return new ClaimGiftBagInfo(giftPackage);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ClaimGiftBagInfo) && Intrinsics.areEqual(this.f2894package, ((ClaimGiftBagInfo) obj).f2894package);
    }

    public int hashCode() {
        return this.f2894package.hashCode();
    }

    public String toString() {
        return "ClaimGiftBagInfo(package=" + this.f2894package + ')';
    }

    public ClaimGiftBagInfo(GiftPackage giftPackage) {
        Intrinsics.checkNotNullParameter(giftPackage, "package");
        this.f2894package = giftPackage;
    }

    public final GiftPackage getPackage() {
        return this.f2894package;
    }
}
