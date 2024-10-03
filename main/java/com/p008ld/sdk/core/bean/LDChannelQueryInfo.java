package com.p008ld.sdk.core.bean;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDChannelQueryInfo {
    private final String buyingExchangeRate;
    private final String countryCode;
    private final String countryName;
    private final String currencyLogo;
    private final String currencyUnit;
    private final String sellingExchangeRate;
    private final List<LDSupportPayChannel> supportPayChannels;

    public static /* synthetic */ LDChannelQueryInfo copy$default(LDChannelQueryInfo lDChannelQueryInfo, String str, String str2, String str3, String str4, String str5, String str6, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDChannelQueryInfo.countryCode;
        }
        if ((i & 2) != 0) {
            str2 = lDChannelQueryInfo.countryName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = lDChannelQueryInfo.currencyLogo;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = lDChannelQueryInfo.currencyUnit;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = lDChannelQueryInfo.buyingExchangeRate;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = lDChannelQueryInfo.sellingExchangeRate;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            list = lDChannelQueryInfo.supportPayChannels;
        }
        return lDChannelQueryInfo.copy(str, str7, str8, str9, str10, str11, list);
    }

    public final String component1() {
        return this.countryCode;
    }

    public final String component2() {
        return this.countryName;
    }

    public final String component3() {
        return this.currencyLogo;
    }

    public final String component4() {
        return this.currencyUnit;
    }

    public final String component5() {
        return this.buyingExchangeRate;
    }

    public final String component6() {
        return this.sellingExchangeRate;
    }

    public final List<LDSupportPayChannel> component7() {
        return this.supportPayChannels;
    }

    public final LDChannelQueryInfo copy(String countryCode, String countryName, String currencyLogo, String currencyUnit, String buyingExchangeRate, String sellingExchangeRate, List<LDSupportPayChannel> supportPayChannels) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(currencyLogo, "currencyLogo");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        Intrinsics.checkNotNullParameter(buyingExchangeRate, "buyingExchangeRate");
        Intrinsics.checkNotNullParameter(sellingExchangeRate, "sellingExchangeRate");
        Intrinsics.checkNotNullParameter(supportPayChannels, "supportPayChannels");
        return new LDChannelQueryInfo(countryCode, countryName, currencyLogo, currencyUnit, buyingExchangeRate, sellingExchangeRate, supportPayChannels);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDChannelQueryInfo)) {
            return false;
        }
        LDChannelQueryInfo lDChannelQueryInfo = (LDChannelQueryInfo) obj;
        return Intrinsics.areEqual(this.countryCode, lDChannelQueryInfo.countryCode) && Intrinsics.areEqual(this.countryName, lDChannelQueryInfo.countryName) && Intrinsics.areEqual(this.currencyLogo, lDChannelQueryInfo.currencyLogo) && Intrinsics.areEqual(this.currencyUnit, lDChannelQueryInfo.currencyUnit) && Intrinsics.areEqual(this.buyingExchangeRate, lDChannelQueryInfo.buyingExchangeRate) && Intrinsics.areEqual(this.sellingExchangeRate, lDChannelQueryInfo.sellingExchangeRate) && Intrinsics.areEqual(this.supportPayChannels, lDChannelQueryInfo.supportPayChannels);
    }

    public int hashCode() {
        return (((((((((((this.countryCode.hashCode() * 31) + this.countryName.hashCode()) * 31) + this.currencyLogo.hashCode()) * 31) + this.currencyUnit.hashCode()) * 31) + this.buyingExchangeRate.hashCode()) * 31) + this.sellingExchangeRate.hashCode()) * 31) + this.supportPayChannels.hashCode();
    }

    public String toString() {
        return "LDChannelQueryInfo(countryCode=" + this.countryCode + ", countryName=" + this.countryName + ", currencyLogo=" + this.currencyLogo + ", currencyUnit=" + this.currencyUnit + ", buyingExchangeRate=" + this.buyingExchangeRate + ", sellingExchangeRate=" + this.sellingExchangeRate + ", supportPayChannels=" + this.supportPayChannels + ')';
    }

    public LDChannelQueryInfo(String countryCode, String countryName, String currencyLogo, String currencyUnit, String buyingExchangeRate, String sellingExchangeRate, List<LDSupportPayChannel> supportPayChannels) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(currencyLogo, "currencyLogo");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        Intrinsics.checkNotNullParameter(buyingExchangeRate, "buyingExchangeRate");
        Intrinsics.checkNotNullParameter(sellingExchangeRate, "sellingExchangeRate");
        Intrinsics.checkNotNullParameter(supportPayChannels, "supportPayChannels");
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencyLogo = currencyLogo;
        this.currencyUnit = currencyUnit;
        this.buyingExchangeRate = buyingExchangeRate;
        this.sellingExchangeRate = sellingExchangeRate;
        this.supportPayChannels = supportPayChannels;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final String getCountryName() {
        return this.countryName;
    }

    public final String getCurrencyLogo() {
        return this.currencyLogo;
    }

    public final String getCurrencyUnit() {
        return this.currencyUnit;
    }

    public final String getBuyingExchangeRate() {
        return this.buyingExchangeRate;
    }

    public final String getSellingExchangeRate() {
        return this.sellingExchangeRate;
    }

    public final List<LDSupportPayChannel> getSupportPayChannels() {
        return this.supportPayChannels;
    }
}
