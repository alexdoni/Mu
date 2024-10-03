package com.appsflyer.internal;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFe1kSDK {
    final String AFInAppEventParameterName;
    final String values;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AFe1kSDK)) {
            return false;
        }
        AFe1kSDK aFe1kSDK = (AFe1kSDK) obj;
        return Intrinsics.areEqual(this.values, aFe1kSDK.values) && Intrinsics.areEqual(this.AFInAppEventParameterName, aFe1kSDK.AFInAppEventParameterName);
    }

    public final int hashCode() {
        return (this.values.hashCode() * 31) + this.AFInAppEventParameterName.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("HostConfig(prefix=");
        sb.append(this.values);
        sb.append(", host=");
        sb.append(this.AFInAppEventParameterName);
        sb.append(')');
        return sb.toString();
    }

    public AFe1kSDK(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        this.values = str;
        this.AFInAppEventParameterName = str2;
    }
}
