package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: GiftPackageInfo.kt */
/* loaded from: classes2.dex */
public final class GiftBagInfo {
    private final ClaimedGiftBag claimed;
    private final NoClaimGiftBag notClaimed;

    public static /* synthetic */ GiftBagInfo copy$default(GiftBagInfo giftBagInfo, ClaimedGiftBag claimedGiftBag, NoClaimGiftBag noClaimGiftBag, int i, Object obj) {
        if ((i & 1) != 0) {
            claimedGiftBag = giftBagInfo.claimed;
        }
        if ((i & 2) != 0) {
            noClaimGiftBag = giftBagInfo.notClaimed;
        }
        return giftBagInfo.copy(claimedGiftBag, noClaimGiftBag);
    }

    public final ClaimedGiftBag component1() {
        return this.claimed;
    }

    public final NoClaimGiftBag component2() {
        return this.notClaimed;
    }

    public final GiftBagInfo copy(ClaimedGiftBag claimed, NoClaimGiftBag notClaimed) {
        Intrinsics.checkNotNullParameter(claimed, "claimed");
        Intrinsics.checkNotNullParameter(notClaimed, "notClaimed");
        return new GiftBagInfo(claimed, notClaimed);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GiftBagInfo)) {
            return false;
        }
        GiftBagInfo giftBagInfo = (GiftBagInfo) obj;
        return Intrinsics.areEqual(this.claimed, giftBagInfo.claimed) && Intrinsics.areEqual(this.notClaimed, giftBagInfo.notClaimed);
    }

    public int hashCode() {
        return (this.claimed.hashCode() * 31) + this.notClaimed.hashCode();
    }

    public String toString() {
        return "GiftBagInfo(claimed=" + this.claimed + ", notClaimed=" + this.notClaimed + ')';
    }

    public GiftBagInfo(ClaimedGiftBag claimed, NoClaimGiftBag notClaimed) {
        Intrinsics.checkNotNullParameter(claimed, "claimed");
        Intrinsics.checkNotNullParameter(notClaimed, "notClaimed");
        this.claimed = claimed;
        this.notClaimed = notClaimed;
    }

    public final ClaimedGiftBag getClaimed() {
        return this.claimed;
    }

    public final NoClaimGiftBag getNotClaimed() {
        return this.notClaimed;
    }
}
