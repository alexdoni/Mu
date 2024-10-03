package com.p008ld.sdk.core.bean;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDExchangeInfo {
    private final Map<String, String> amounts;
    private final String buyingExchangeRate;
    private final String sellingExchangeRate;
    private final String toCurrencyLogo;
    private final String toCurrencyUnit;

    public static /* synthetic */ LDExchangeInfo copy$default(LDExchangeInfo lDExchangeInfo, String str, String str2, String str3, String str4, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDExchangeInfo.toCurrencyUnit;
        }
        if ((i & 2) != 0) {
            str2 = lDExchangeInfo.toCurrencyLogo;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = lDExchangeInfo.buyingExchangeRate;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = lDExchangeInfo.sellingExchangeRate;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            map = lDExchangeInfo.amounts;
        }
        return lDExchangeInfo.copy(str, str5, str6, str7, map);
    }

    public final String component1() {
        return this.toCurrencyUnit;
    }

    public final String component2() {
        return this.toCurrencyLogo;
    }

    public final String component3() {
        return this.buyingExchangeRate;
    }

    public final String component4() {
        return this.sellingExchangeRate;
    }

    public final Map<String, String> component5() {
        return this.amounts;
    }

    public final LDExchangeInfo copy(String toCurrencyUnit, String toCurrencyLogo, String buyingExchangeRate, String sellingExchangeRate, Map<String, String> amounts) {
        Intrinsics.checkNotNullParameter(toCurrencyUnit, "toCurrencyUnit");
        Intrinsics.checkNotNullParameter(toCurrencyLogo, "toCurrencyLogo");
        Intrinsics.checkNotNullParameter(buyingExchangeRate, "buyingExchangeRate");
        Intrinsics.checkNotNullParameter(sellingExchangeRate, "sellingExchangeRate");
        Intrinsics.checkNotNullParameter(amounts, "amounts");
        return new LDExchangeInfo(toCurrencyUnit, toCurrencyLogo, buyingExchangeRate, sellingExchangeRate, amounts);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDExchangeInfo)) {
            return false;
        }
        LDExchangeInfo lDExchangeInfo = (LDExchangeInfo) obj;
        return Intrinsics.areEqual(this.toCurrencyUnit, lDExchangeInfo.toCurrencyUnit) && Intrinsics.areEqual(this.toCurrencyLogo, lDExchangeInfo.toCurrencyLogo) && Intrinsics.areEqual(this.buyingExchangeRate, lDExchangeInfo.buyingExchangeRate) && Intrinsics.areEqual(this.sellingExchangeRate, lDExchangeInfo.sellingExchangeRate) && Intrinsics.areEqual(this.amounts, lDExchangeInfo.amounts);
    }

    public int hashCode() {
        return (((((((this.toCurrencyUnit.hashCode() * 31) + this.toCurrencyLogo.hashCode()) * 31) + this.buyingExchangeRate.hashCode()) * 31) + this.sellingExchangeRate.hashCode()) * 31) + this.amounts.hashCode();
    }

    public String toString() {
        return "LDExchangeInfo(toCurrencyUnit=" + this.toCurrencyUnit + ", toCurrencyLogo=" + this.toCurrencyLogo + ", buyingExchangeRate=" + this.buyingExchangeRate + ", sellingExchangeRate=" + this.sellingExchangeRate + ", amounts=" + this.amounts + ')';
    }

    public LDExchangeInfo(String toCurrencyUnit, String toCurrencyLogo, String buyingExchangeRate, String sellingExchangeRate, Map<String, String> amounts) {
        Intrinsics.checkNotNullParameter(toCurrencyUnit, "toCurrencyUnit");
        Intrinsics.checkNotNullParameter(toCurrencyLogo, "toCurrencyLogo");
        Intrinsics.checkNotNullParameter(buyingExchangeRate, "buyingExchangeRate");
        Intrinsics.checkNotNullParameter(sellingExchangeRate, "sellingExchangeRate");
        Intrinsics.checkNotNullParameter(amounts, "amounts");
        this.toCurrencyUnit = toCurrencyUnit;
        this.toCurrencyLogo = toCurrencyLogo;
        this.buyingExchangeRate = buyingExchangeRate;
        this.sellingExchangeRate = sellingExchangeRate;
        this.amounts = amounts;
    }

    public final String getToCurrencyUnit() {
        return this.toCurrencyUnit;
    }

    public final String getToCurrencyLogo() {
        return this.toCurrencyLogo;
    }

    public final String getBuyingExchangeRate() {
        return this.buyingExchangeRate;
    }

    public final String getSellingExchangeRate() {
        return this.sellingExchangeRate;
    }

    public final Map<String, String> getAmounts() {
        return this.amounts;
    }
}
