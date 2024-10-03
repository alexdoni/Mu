package com.appsflyer.internal;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFg1tSDK {
    final int AFInAppEventParameterName;
    final String AFInAppEventType;
    final int AFKeystoreWrapper;
    final int valueOf;
    final int values;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AFg1tSDK)) {
            return false;
        }
        AFg1tSDK aFg1tSDK = (AFg1tSDK) obj;
        return this.valueOf == aFg1tSDK.valueOf && this.AFKeystoreWrapper == aFg1tSDK.AFKeystoreWrapper && this.values == aFg1tSDK.values && this.AFInAppEventParameterName == aFg1tSDK.AFInAppEventParameterName && Intrinsics.areEqual(this.AFInAppEventType, aFg1tSDK.AFInAppEventType);
    }

    public final int hashCode() {
        return (((((((this.valueOf * 31) + this.AFKeystoreWrapper) * 31) + this.values) * 31) + this.AFInAppEventParameterName) * 31) + this.AFInAppEventType.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CmpTcfData(policyVersion=");
        sb.append(this.valueOf);
        sb.append(", gdprApplies=");
        sb.append(this.AFKeystoreWrapper);
        sb.append(", cmpSdkId=");
        sb.append(this.values);
        sb.append(", cmpSdkVersion=");
        sb.append(this.AFInAppEventParameterName);
        sb.append(", tcString=");
        sb.append(this.AFInAppEventType);
        sb.append(')');
        return sb.toString();
    }

    public AFg1tSDK(int i, int i2, int i3, int i4, String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.valueOf = i;
        this.AFKeystoreWrapper = i2;
        this.values = i3;
        this.AFInAppEventParameterName = i4;
        this.AFInAppEventType = str;
    }
}
