package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDProductInfo {
    private final String appProductId;
    private final String price;
    private final String priceCent;
    private final String priceCurrency;
    private final String productId;
    private final String productName;

    public static /* synthetic */ LDProductInfo copy$default(LDProductInfo lDProductInfo, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDProductInfo.appProductId;
        }
        if ((i & 2) != 0) {
            str2 = lDProductInfo.productId;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = lDProductInfo.productName;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = lDProductInfo.price;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = lDProductInfo.priceCent;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = lDProductInfo.priceCurrency;
        }
        return lDProductInfo.copy(str, str7, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.appProductId;
    }

    public final String component2() {
        return this.productId;
    }

    public final String component3() {
        return this.productName;
    }

    public final String component4() {
        return this.price;
    }

    public final String component5() {
        return this.priceCent;
    }

    public final String component6() {
        return this.priceCurrency;
    }

    public final LDProductInfo copy(String appProductId, String productId, String productName, String price, String priceCent, String priceCurrency) {
        Intrinsics.checkNotNullParameter(appProductId, "appProductId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(priceCent, "priceCent");
        Intrinsics.checkNotNullParameter(priceCurrency, "priceCurrency");
        return new LDProductInfo(appProductId, productId, productName, price, priceCent, priceCurrency);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDProductInfo)) {
            return false;
        }
        LDProductInfo lDProductInfo = (LDProductInfo) obj;
        return Intrinsics.areEqual(this.appProductId, lDProductInfo.appProductId) && Intrinsics.areEqual(this.productId, lDProductInfo.productId) && Intrinsics.areEqual(this.productName, lDProductInfo.productName) && Intrinsics.areEqual(this.price, lDProductInfo.price) && Intrinsics.areEqual(this.priceCent, lDProductInfo.priceCent) && Intrinsics.areEqual(this.priceCurrency, lDProductInfo.priceCurrency);
    }

    public int hashCode() {
        return (((((((((this.appProductId.hashCode() * 31) + this.productId.hashCode()) * 31) + this.productName.hashCode()) * 31) + this.price.hashCode()) * 31) + this.priceCent.hashCode()) * 31) + this.priceCurrency.hashCode();
    }

    public String toString() {
        return "LDProductInfo(appProductId=" + this.appProductId + ", productId=" + this.productId + ", productName=" + this.productName + ", price=" + this.price + ", priceCent=" + this.priceCent + ", priceCurrency=" + this.priceCurrency + ')';
    }

    public LDProductInfo(String appProductId, String productId, String productName, String price, String priceCent, String priceCurrency) {
        Intrinsics.checkNotNullParameter(appProductId, "appProductId");
        Intrinsics.checkNotNullParameter(productId, "productId");
        Intrinsics.checkNotNullParameter(productName, "productName");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(priceCent, "priceCent");
        Intrinsics.checkNotNullParameter(priceCurrency, "priceCurrency");
        this.appProductId = appProductId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.priceCent = priceCent;
        this.priceCurrency = priceCurrency;
    }

    public final String getAppProductId() {
        return this.appProductId;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getPriceCent() {
        return this.priceCent;
    }

    public final String getPriceCurrency() {
        return this.priceCurrency;
    }
}
