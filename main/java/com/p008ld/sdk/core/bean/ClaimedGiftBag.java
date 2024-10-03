package com.p008ld.sdk.core.bean;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GiftPackageInfo.kt */
/* loaded from: classes2.dex */
public final class ClaimedGiftBag {
    private final List<GiftPackage> list;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ClaimedGiftBag copy$default(ClaimedGiftBag claimedGiftBag, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = claimedGiftBag.list;
        }
        return claimedGiftBag.copy(list);
    }

    public final List<GiftPackage> component1() {
        return this.list;
    }

    public final ClaimedGiftBag copy(List<GiftPackage> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        return new ClaimedGiftBag(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ClaimedGiftBag) && Intrinsics.areEqual(this.list, ((ClaimedGiftBag) obj).list);
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public String toString() {
        return "ClaimedGiftBag(list=" + this.list + ')';
    }

    public ClaimedGiftBag(List<GiftPackage> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    public final List<GiftPackage> getList() {
        return this.list;
    }
}
