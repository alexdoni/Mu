package com.appsflyer.internal;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public interface AFd1uSDK {
    AFa1zSDK values(Context context);

    /* loaded from: classes.dex */
    public static final class AFa1zSDK {
        public final float AFKeystoreWrapper;
        public final String values;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AFa1zSDK)) {
                return false;
            }
            AFa1zSDK aFa1zSDK = (AFa1zSDK) obj;
            return Intrinsics.areEqual((Object) Float.valueOf(this.AFKeystoreWrapper), (Object) Float.valueOf(aFa1zSDK.AFKeystoreWrapper)) && Intrinsics.areEqual(this.values, aFa1zSDK.values);
        }

        public final int hashCode() {
            int floatToIntBits = Float.floatToIntBits(this.AFKeystoreWrapper) * 31;
            String str = this.values;
            return floatToIntBits + (str == null ? 0 : str.hashCode());
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("BatteryData(level=");
            sb.append(this.AFKeystoreWrapper);
            sb.append(", charging=");
            sb.append(this.values);
            sb.append(')');
            return sb.toString();
        }

        public AFa1zSDK(float f, String str) {
            this.AFKeystoreWrapper = f;
            this.values = str;
        }
    }
}
