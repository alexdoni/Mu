package com.appsflyer.internal;

/* loaded from: classes.dex */
public class AFe1mSDK {
    public final long AFKeystoreWrapper;

    public AFe1mSDK(long j) {
        this.AFKeystoreWrapper = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.AFKeystoreWrapper == ((AFe1mSDK) obj).AFKeystoreWrapper;
    }

    public int hashCode() {
        long j = this.AFKeystoreWrapper;
        return (int) (j ^ (j >>> 32));
    }
}
