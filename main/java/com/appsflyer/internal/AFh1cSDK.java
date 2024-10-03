package com.appsflyer.internal;

/* loaded from: classes.dex */
public final class AFh1cSDK {
    public final AFh1bSDK AFInAppEventParameterName;
    private boolean AFKeystoreWrapper;

    public AFh1cSDK(boolean z, AFh1bSDK aFh1bSDK) {
        this.AFKeystoreWrapper = z;
        this.AFInAppEventParameterName = aFh1bSDK;
    }

    public final boolean AFInAppEventType() {
        return this.AFKeystoreWrapper;
    }
}
