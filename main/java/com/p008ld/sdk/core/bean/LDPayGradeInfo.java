package com.p008ld.sdk.core.bean;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDPayGradeInfo {
    private final String appProductId;
    private final String currencyOfPrice;
    private final String googleProductId;
    private final String price;
    private final String remarks;
    private final List<LDSupportPayChannel> supportPayChannels;

    public static /* synthetic */ LDPayGradeInfo copy$default(LDPayGradeInfo lDPayGradeInfo, String str, String str2, String str3, String str4, String str5, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDPayGradeInfo.appProductId;
        }
        if ((i & 2) != 0) {
            str2 = lDPayGradeInfo.googleProductId;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = lDPayGradeInfo.price;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = lDPayGradeInfo.currencyOfPrice;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = lDPayGradeInfo.remarks;
        }
        String str9 = str5;
        if ((i & 32) != 0) {
            list = lDPayGradeInfo.supportPayChannels;
        }
        return lDPayGradeInfo.copy(str, str6, str7, str8, str9, list);
    }

    public final String component1() {
        return this.appProductId;
    }

    public final String component2() {
        return this.googleProductId;
    }

    public final String component3() {
        return this.price;
    }

    public final String component4() {
        return this.currencyOfPrice;
    }

    public final String component5() {
        return this.remarks;
    }

    public final List<LDSupportPayChannel> component6() {
        return this.supportPayChannels;
    }

    public final LDPayGradeInfo copy(String str, String str2, String price, String currencyOfPrice, String str3, List<LDSupportPayChannel> supportPayChannels) {
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(currencyOfPrice, "currencyOfPrice");
        Intrinsics.checkNotNullParameter(supportPayChannels, "supportPayChannels");
        return new LDPayGradeInfo(str, str2, price, currencyOfPrice, str3, supportPayChannels);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDPayGradeInfo)) {
            return false;
        }
        LDPayGradeInfo lDPayGradeInfo = (LDPayGradeInfo) obj;
        return Intrinsics.areEqual(this.appProductId, lDPayGradeInfo.appProductId) && Intrinsics.areEqual(this.googleProductId, lDPayGradeInfo.googleProductId) && Intrinsics.areEqual(this.price, lDPayGradeInfo.price) && Intrinsics.areEqual(this.currencyOfPrice, lDPayGradeInfo.currencyOfPrice) && Intrinsics.areEqual(this.remarks, lDPayGradeInfo.remarks) && Intrinsics.areEqual(this.supportPayChannels, lDPayGradeInfo.supportPayChannels);
    }

    public int hashCode() {
        String str = this.appProductId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.googleProductId;
        int hashCode2 = (((((hashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + this.price.hashCode()) * 31) + this.currencyOfPrice.hashCode()) * 31;
        String str3 = this.remarks;
        return ((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.supportPayChannels.hashCode();
    }

    public String toString() {
        return "LDPayGradeInfo(appProductId=" + this.appProductId + ", googleProductId=" + this.googleProductId + ", price=" + this.price + ", currencyOfPrice=" + this.currencyOfPrice + ", remarks=" + this.remarks + ", supportPayChannels=" + this.supportPayChannels + ')';
    }

    public LDPayGradeInfo(String str, String str2, String price, String currencyOfPrice, String str3, List<LDSupportPayChannel> supportPayChannels) {
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(currencyOfPrice, "currencyOfPrice");
        Intrinsics.checkNotNullParameter(supportPayChannels, "supportPayChannels");
        this.appProductId = str;
        this.googleProductId = str2;
        this.price = price;
        this.currencyOfPrice = currencyOfPrice;
        this.remarks = str3;
        this.supportPayChannels = supportPayChannels;
    }

    public final String getAppProductId() {
        return this.appProductId;
    }

    public final String getGoogleProductId() {
        return this.googleProductId;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getCurrencyOfPrice() {
        return this.currencyOfPrice;
    }

    public final String getRemarks() {
        return this.remarks;
    }

    public final List<LDSupportPayChannel> getSupportPayChannels() {
        return this.supportPayChannels;
    }
}
