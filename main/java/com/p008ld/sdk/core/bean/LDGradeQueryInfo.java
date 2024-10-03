package com.p008ld.sdk.core.bean;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDGradeQueryInfo {
    private final String countryCode;
    private final String countryName;
    private final String currencyLogo;
    private final String currencyUnit;
    private final List<LDPayGradeInfo> grades;
    private final List<LDPayChannelInfo> payChannels;

    public static /* synthetic */ LDGradeQueryInfo copy$default(LDGradeQueryInfo lDGradeQueryInfo, String str, String str2, String str3, String str4, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lDGradeQueryInfo.countryCode;
        }
        if ((i & 2) != 0) {
            str2 = lDGradeQueryInfo.countryName;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            str3 = lDGradeQueryInfo.currencyLogo;
        }
        String str6 = str3;
        if ((i & 8) != 0) {
            str4 = lDGradeQueryInfo.currencyUnit;
        }
        String str7 = str4;
        if ((i & 16) != 0) {
            list = lDGradeQueryInfo.grades;
        }
        List list3 = list;
        if ((i & 32) != 0) {
            list2 = lDGradeQueryInfo.payChannels;
        }
        return lDGradeQueryInfo.copy(str, str5, str6, str7, list3, list2);
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

    public final List<LDPayGradeInfo> component5() {
        return this.grades;
    }

    public final List<LDPayChannelInfo> component6() {
        return this.payChannels;
    }

    public final LDGradeQueryInfo copy(String countryCode, String countryName, String currencyLogo, String currencyUnit, List<LDPayGradeInfo> grades, List<LDPayChannelInfo> payChannels) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(currencyLogo, "currencyLogo");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        Intrinsics.checkNotNullParameter(grades, "grades");
        Intrinsics.checkNotNullParameter(payChannels, "payChannels");
        return new LDGradeQueryInfo(countryCode, countryName, currencyLogo, currencyUnit, grades, payChannels);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDGradeQueryInfo)) {
            return false;
        }
        LDGradeQueryInfo lDGradeQueryInfo = (LDGradeQueryInfo) obj;
        return Intrinsics.areEqual(this.countryCode, lDGradeQueryInfo.countryCode) && Intrinsics.areEqual(this.countryName, lDGradeQueryInfo.countryName) && Intrinsics.areEqual(this.currencyLogo, lDGradeQueryInfo.currencyLogo) && Intrinsics.areEqual(this.currencyUnit, lDGradeQueryInfo.currencyUnit) && Intrinsics.areEqual(this.grades, lDGradeQueryInfo.grades) && Intrinsics.areEqual(this.payChannels, lDGradeQueryInfo.payChannels);
    }

    public int hashCode() {
        return (((((((((this.countryCode.hashCode() * 31) + this.countryName.hashCode()) * 31) + this.currencyLogo.hashCode()) * 31) + this.currencyUnit.hashCode()) * 31) + this.grades.hashCode()) * 31) + this.payChannels.hashCode();
    }

    public String toString() {
        return "LDGradeQueryInfo(countryCode=" + this.countryCode + ", countryName=" + this.countryName + ", currencyLogo=" + this.currencyLogo + ", currencyUnit=" + this.currencyUnit + ", grades=" + this.grades + ", payChannels=" + this.payChannels + ')';
    }

    public LDGradeQueryInfo(String countryCode, String countryName, String currencyLogo, String currencyUnit, List<LDPayGradeInfo> grades, List<LDPayChannelInfo> payChannels) {
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        Intrinsics.checkNotNullParameter(countryName, "countryName");
        Intrinsics.checkNotNullParameter(currencyLogo, "currencyLogo");
        Intrinsics.checkNotNullParameter(currencyUnit, "currencyUnit");
        Intrinsics.checkNotNullParameter(grades, "grades");
        Intrinsics.checkNotNullParameter(payChannels, "payChannels");
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.currencyLogo = currencyLogo;
        this.currencyUnit = currencyUnit;
        this.grades = grades;
        this.payChannels = payChannels;
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

    public final List<LDPayGradeInfo> getGrades() {
        return this.grades;
    }

    public final List<LDPayChannelInfo> getPayChannels() {
        return this.payChannels;
    }
}
