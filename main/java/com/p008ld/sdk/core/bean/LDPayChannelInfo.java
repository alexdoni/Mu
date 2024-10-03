package com.p008ld.sdk.core.bean;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDPayBean.kt */
/* loaded from: classes2.dex */
public final class LDPayChannelInfo {
    private final String attach;
    private final String icon;
    private final String inAppPurchaseType;
    private boolean isSelected;
    private final String name;
    private final int osWeb;
    private final String payChannelCode;
    private final String payChannelType;
    private final List<LDSupportPayGrade> supportGrades;

    public final String component1() {
        return this.icon;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.payChannelType;
    }

    public final String component4() {
        return this.payChannelCode;
    }

    public final String component5() {
        return this.inAppPurchaseType;
    }

    public final String component6() {
        return this.attach;
    }

    public final int component7() {
        return this.osWeb;
    }

    public final List<LDSupportPayGrade> component8() {
        return this.supportGrades;
    }

    public final boolean component9() {
        return this.isSelected;
    }

    public final LDPayChannelInfo copy(String icon, String name, String payChannelType, String payChannelCode, String str, String str2, int i, List<LDSupportPayGrade> supportGrades, boolean z) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(payChannelType, "payChannelType");
        Intrinsics.checkNotNullParameter(payChannelCode, "payChannelCode");
        Intrinsics.checkNotNullParameter(supportGrades, "supportGrades");
        return new LDPayChannelInfo(icon, name, payChannelType, payChannelCode, str, str2, i, supportGrades, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LDPayChannelInfo)) {
            return false;
        }
        LDPayChannelInfo lDPayChannelInfo = (LDPayChannelInfo) obj;
        return Intrinsics.areEqual(this.icon, lDPayChannelInfo.icon) && Intrinsics.areEqual(this.name, lDPayChannelInfo.name) && Intrinsics.areEqual(this.payChannelType, lDPayChannelInfo.payChannelType) && Intrinsics.areEqual(this.payChannelCode, lDPayChannelInfo.payChannelCode) && Intrinsics.areEqual(this.inAppPurchaseType, lDPayChannelInfo.inAppPurchaseType) && Intrinsics.areEqual(this.attach, lDPayChannelInfo.attach) && this.osWeb == lDPayChannelInfo.osWeb && Intrinsics.areEqual(this.supportGrades, lDPayChannelInfo.supportGrades) && this.isSelected == lDPayChannelInfo.isSelected;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((((this.icon.hashCode() * 31) + this.name.hashCode()) * 31) + this.payChannelType.hashCode()) * 31) + this.payChannelCode.hashCode()) * 31;
        String str = this.inAppPurchaseType;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.attach;
        int hashCode3 = (((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.osWeb) * 31) + this.supportGrades.hashCode()) * 31;
        boolean z = this.isSelected;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "LDPayChannelInfo(icon=" + this.icon + ", name=" + this.name + ", payChannelType=" + this.payChannelType + ", payChannelCode=" + this.payChannelCode + ", inAppPurchaseType=" + this.inAppPurchaseType + ", attach=" + this.attach + ", osWeb=" + this.osWeb + ", supportGrades=" + this.supportGrades + ", isSelected=" + this.isSelected + ')';
    }

    public LDPayChannelInfo(String icon, String name, String payChannelType, String payChannelCode, String str, String str2, int i, List<LDSupportPayGrade> supportGrades, boolean z) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(payChannelType, "payChannelType");
        Intrinsics.checkNotNullParameter(payChannelCode, "payChannelCode");
        Intrinsics.checkNotNullParameter(supportGrades, "supportGrades");
        this.icon = icon;
        this.name = name;
        this.payChannelType = payChannelType;
        this.payChannelCode = payChannelCode;
        this.inAppPurchaseType = str;
        this.attach = str2;
        this.osWeb = i;
        this.supportGrades = supportGrades;
        this.isSelected = z;
    }

    public /* synthetic */ LDPayChannelInfo(String str, String str2, String str3, String str4, String str5, String str6, int i, List list, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, i, list, (i2 & 256) != 0 ? false : z);
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

    public final int getOsWeb() {
        return this.osWeb;
    }

    public final List<LDSupportPayGrade> getSupportGrades() {
        return this.supportGrades;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }
}
