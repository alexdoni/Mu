package com.p008ld.sdk.core.bean;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDSupportPayChannel {
    private final String additionalFee;
    private final String amount;
    private final String attach;
    private final String gradeChannelTypeId;
    private final String icon;
    private final String inAppPurchaseType;
    private final String name;
    private final int osWeb;
    private final String payChannelCode;
    private final String payChannelType;
    private final String quantityOfGive;
    private final String quantityOfRewards;
    private final String totalAmount;
    private final String totalQuantity;

    public final String component1() {
        return this.gradeChannelTypeId;
    }

    public final String component10() {
        return this.totalAmount;
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
        return this.icon;
    }

    public final String component3() {
        return this.name;
    }

    public final String component4() {
        return this.payChannelType;
    }

    public final String component5() {
        return this.payChannelCode;
    }

    public final String component6() {
        return this.inAppPurchaseType;
    }

    public final String component7() {
        return this.attach;
    }

    public final String component8() {
        return this.additionalFee;
    }

    public final String component9() {
        return this.amount;
    }

    public final LDSupportPayChannel copy(String gradeChannelTypeId, String icon, String name, String payChannelType, String payChannelCode, String str, String str2, String str3, String amount, String totalAmount, String str4, String str5, String str6, int i) {
        Intrinsics.checkNotNullParameter(gradeChannelTypeId, "gradeChannelTypeId");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(payChannelType, "payChannelType");
        Intrinsics.checkNotNullParameter(payChannelCode, "payChannelCode");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(totalAmount, "totalAmount");
        return new LDSupportPayChannel(gradeChannelTypeId, icon, name, payChannelType, payChannelCode, str, str2, str3, amount, totalAmount, str4, str5, str6, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDSupportPayChannel)) {
            return false;
        }
        LDSupportPayChannel lDSupportPayChannel = (LDSupportPayChannel) obj;
        return Intrinsics.areEqual(this.gradeChannelTypeId, lDSupportPayChannel.gradeChannelTypeId) && Intrinsics.areEqual(this.icon, lDSupportPayChannel.icon) && Intrinsics.areEqual(this.name, lDSupportPayChannel.name) && Intrinsics.areEqual(this.payChannelType, lDSupportPayChannel.payChannelType) && Intrinsics.areEqual(this.payChannelCode, lDSupportPayChannel.payChannelCode) && Intrinsics.areEqual(this.inAppPurchaseType, lDSupportPayChannel.inAppPurchaseType) && Intrinsics.areEqual(this.attach, lDSupportPayChannel.attach) && Intrinsics.areEqual(this.additionalFee, lDSupportPayChannel.additionalFee) && Intrinsics.areEqual(this.amount, lDSupportPayChannel.amount) && Intrinsics.areEqual(this.totalAmount, lDSupportPayChannel.totalAmount) && Intrinsics.areEqual(this.quantityOfGive, lDSupportPayChannel.quantityOfGive) && Intrinsics.areEqual(this.quantityOfRewards, lDSupportPayChannel.quantityOfRewards) && Intrinsics.areEqual(this.totalQuantity, lDSupportPayChannel.totalQuantity) && this.osWeb == lDSupportPayChannel.osWeb;
    }

    public int hashCode() {
        int hashCode = ((((((((this.gradeChannelTypeId.hashCode() * 31) + this.icon.hashCode()) * 31) + this.name.hashCode()) * 31) + this.payChannelType.hashCode()) * 31) + this.payChannelCode.hashCode()) * 31;
        String str = this.inAppPurchaseType;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.attach;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.additionalFee;
        int hashCode4 = (((((hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.amount.hashCode()) * 31) + this.totalAmount.hashCode()) * 31;
        String str4 = this.quantityOfGive;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.quantityOfRewards;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.totalQuantity;
        return ((hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.osWeb;
    }

    public String toString() {
        return "LDSupportPayChannel(gradeChannelTypeId=" + this.gradeChannelTypeId + ", icon=" + this.icon + ", name=" + this.name + ", payChannelType=" + this.payChannelType + ", payChannelCode=" + this.payChannelCode + ", inAppPurchaseType=" + this.inAppPurchaseType + ", attach=" + this.attach + ", additionalFee=" + this.additionalFee + ", amount=" + this.amount + ", totalAmount=" + this.totalAmount + ", quantityOfGive=" + this.quantityOfGive + ", quantityOfRewards=" + this.quantityOfRewards + ", totalQuantity=" + this.totalQuantity + ", osWeb=" + this.osWeb + ')';
    }

    public LDSupportPayChannel(String gradeChannelTypeId, String icon, String name, String payChannelType, String payChannelCode, String str, String str2, String str3, String amount, String totalAmount, String str4, String str5, String str6, int i) {
        Intrinsics.checkNotNullParameter(gradeChannelTypeId, "gradeChannelTypeId");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(payChannelType, "payChannelType");
        Intrinsics.checkNotNullParameter(payChannelCode, "payChannelCode");
        Intrinsics.checkNotNullParameter(amount, "amount");
        Intrinsics.checkNotNullParameter(totalAmount, "totalAmount");
        this.gradeChannelTypeId = gradeChannelTypeId;
        this.icon = icon;
        this.name = name;
        this.payChannelType = payChannelType;
        this.payChannelCode = payChannelCode;
        this.inAppPurchaseType = str;
        this.attach = str2;
        this.additionalFee = str3;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.quantityOfGive = str4;
        this.quantityOfRewards = str5;
        this.totalQuantity = str6;
        this.osWeb = i;
    }

    public final String getGradeChannelTypeId() {
        return this.gradeChannelTypeId;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPayChannelType() {
        return this.payChannelType;
    }

    public final String getPayChannelCode() {
        return this.payChannelCode;
    }

    public final String getInAppPurchaseType() {
        return this.inAppPurchaseType;
    }

    public final String getAttach() {
        return this.attach;
    }

    public final String getAdditionalFee() {
        return this.additionalFee;
    }

    public final String getAmount() {
        return this.amount;
    }

    public final String getTotalAmount() {
        return this.totalAmount;
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
