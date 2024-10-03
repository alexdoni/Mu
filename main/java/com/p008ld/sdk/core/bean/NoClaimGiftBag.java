package com.p008ld.sdk.core.bean;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GiftPackageInfo.kt */
/* loaded from: classes2.dex */
public final class NoClaimGiftBag {
    private final List<GiftPackage> list;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NoClaimGiftBag copy$default(NoClaimGiftBag noClaimGiftBag, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = noClaimGiftBag.list;
        }
        return noClaimGiftBag.copy(list);
    }

    public final List<GiftPackage> component1() {
        return this.list;
    }

    public final NoClaimGiftBag copy(List<GiftPackage> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        return new NoClaimGiftBag(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof NoClaimGiftBag) && Intrinsics.areEqual(this.list, ((NoClaimGiftBag) obj).list);
    }

    public int hashCode() {
        return this.list.hashCode();
    }

    public String toString() {
        return "NoClaimGiftBag(list=" + this.list + ')';
    }

    public NoClaimGiftBag(List<GiftPackage> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.list = list;
    }

    public final List<GiftPackage> getList() {
        return this.list;
    }
}
