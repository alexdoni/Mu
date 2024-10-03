package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDSupportPayGrade {
    private final String additionalFee;
    private final String amount;
    private final String appProductId;
    private final String currencyOfPrice;
    private final String currencyUnit;
    private final String googleProductId;
    private final String gradeChannelTypeId;
    private final int osWeb;
    private final String price;
    private final String quantityOfGive;
    private final String quantityOfRewards;
    private final String remarks;
    private final String totalAmount;
    private final String totalQuantity;

    public final String component1() {
        return this.gradeChannelTypeId;
    }

    public final String component10() {
        return this.additionalFee;
    }

    public final String component11() {
        return this.quantityOfGive;
    }

    public final String component12() {
        return this.quantityOfRewards;
    }

    public final String component13() {
        return this.totalQuantity;
    }

    public final int component14() {
        return this.osWeb;
    }

    public final String component2() {
        return this.appProductId;
    }

    public final String component3() {
        return this.googleProductId;
    }

    public final String component4() {
        return this.remarks;
    }

    public final String component5() {
        return this.price;
    }

    public final String component6() {
        return this.currencyOfPrice;
    }

    public final String component7() {
        return this.amount;
    }

    public final String component8() {
        return this.totalAmount;
    }

    public final String component9() {
        return this.currencyUnit;
    }

    public final LDSupportPayGrade copy(String gradeChannelTypeId, String str, String str2, String str3, String price, String currencyOfPrice, String amount, String totalAmount, String currencyUnit, String str4, String str5, String str6, String str7, int i) {
        Intrinsics.checkNotNullParameter(gradeChannelTypeId, "gradeChannelTypeId");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(currencyOfPrice, "currencyOfPrice");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(totalAmount, "totalAmount");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        return new LDSupportPayGrade(gradeChannelTypeId, str, str2, str3, price, currencyOfPrice, amount, totalAmount, currencyUnit, str4, str5, str6, str7, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDSupportPayGrade)) {
            return false;
        }
        LDSupportPayGrade lDSupportPayGrade = (LDSupportPayGrade) obj;
        return Intrinsics.areEqual(this.gradeChannelTypeId, lDSupportPayGrade.gradeChannelTypeId) && Intrinsics.areEqual(this.appProductId, lDSupportPayGrade.appProductId) && Intrinsics.areEqual(this.googleProductId, lDSupportPayGrade.googleProductId) && Intrinsics.areEqual(this.remarks, lDSupportPayGrade.remarks) && Intrinsics.areEqual(this.price, lDSupportPayGrade.price) && Intrinsics.areEqual(this.currencyOfPrice, lDSupportPayGrade.currencyOfPrice) && Intrinsics.areEqual(this.amount, lDSupportPayGrade.amount) && Intrinsics.areEqual(this.totalAmount, lDSupportPayGrade.totalAmount) && Intrinsics.areEqual(this.currencyUnit, lDSupportPayGrade.currencyUnit) && Intrinsics.areEqual(this.additionalFee, lDSupportPayGrade.additionalFee) && Intrinsics.areEqual(this.quantityOfGive, lDSupportPayGrade.quantityOfGive) && Intrinsics.areEqual(this.quantityOfRewards, lDSupportPayGrade.quantityOfRewards) && Intrinsics.areEqual(this.totalQuantity, lDSupportPayGrade.totalQuantity) && this.osWeb == lDSupportPayGrade.osWeb;
    }

    public int hashCode() {
        int hashCode = this.gradeChannelTypeId.hashCode() * 31;
        String str = this.appProductId;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.googleProductId;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.remarks;
        int hashCode4 = (((((((((((hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.price.hashCode()) * 31) + this.currencyOfPrice.hashCode()) * 31) + this.amount.hashCode()) * 31) + this.totalAmount.hashCode()) * 31) + this.currencyUnit.hashCode()) * 31;
        String str4 = this.additionalFee;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.quantityOfGive;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.quantityOfRewards;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.totalQuantity;
        return ((hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31) + this.osWeb;
    }

    public String toString() {
        return "LDSupportPayGrade(gradeChannelTypeId=" + this.gradeChannelTypeId + ", appProductId=" + this.appProductId + ", googleProductId=" + this.googleProductId + ", remarks=" + this.remarks + ", price=" + this.price + ", currencyOfPrice=" + this.currencyOfPrice + ", amount=" + this.amount + ", totalAmount=" + this.totalAmount + ", currencyUnit=" + this.currencyUnit + ", additionalFee=" + this.additionalFee + ", quantityOfGive=" + this.quantityOfGive + ", quantityOfRewards=" + this.quantityOfRewards + ", totalQuantity=" + this.totalQuantity + ", osWeb=" + this.osWeb + ')';
    }

    public LDSupportPayGrade(String gradeChannelTypeId, String str, String str2, String str3, String price, String currencyOfPrice, String amount, String totalAmount, String currencyUnit, String str4, String str5, String str6, String str7, int i) {
        Intrinsics.checkNotNullParameter(gradeChannelTypeId, "gradeChannelTypeId");
        Intrinsics.checkNotNullParameter(price, "price");
        Intrinsics.checkNotNullParameter(currencyOfPrice, "currencyOfPrice");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(totalAmount, "totalAmount");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        this.gradeChannelTypeId = gradeChannelTypeId;
        this.appProductId = str;
        this.googleProductId = str2;
        this.remarks = str3;
        this.price = price;
        this.currencyOfPrice = currencyOfPrice;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.currencyUnit = currencyUnit;
        this.additionalFee = str4;
        this.quantityOfGive = str5;
        this.quantityOfRewards = str6;
        this.totalQuantity = str7;
        this.osWeb = i;
    }

    public final String getGradeChannelTypeId() {
        return this.gradeChannelTypeId;
    }

    public final String getAppProductId() {
        return this.appProductId;
    }

    public final String getGoogleProductId() {
        return this.googleProductId;
    }

    public final String getRemarks() {
        return this.remarks;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getCurrencyOfPrice() {
        return this.currencyOfPrice;
    }

    public final String getAmount() {
        return this.amount;
    }

    public final String getTotalAmount() {
        return this.totalAmount;
    }

    public final String getCurrencyUnit() {
        return this.currencyUnit;
    }

    public final String getAdditionalFee() {
        return this.additionalFee;
    }

    public final String getQuantityOfGive() {
        return this.quantityOfGive;
    }

    public final String getQuantityOfRewards() {
        return this.quantityOfRewards;
    }

    public final String getTotalQuantity() {
        return this.totalQuantity;
    }

    public final int getOsWeb() {
        return this.osWeb;
    }
}
