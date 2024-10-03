package com.appsflyer.internal;

import com.appsflyer.AFLogger;

/* loaded from: classes.dex */
public abstract class AFi1rSDK extends AFi1jSDK {
    private AFd1sSDK values;

    public AFi1rSDK(String str, String str2, AFd1sSDK aFd1sSDK, Runnable runnable) {
        super(str, str2, runnable);
        this.values = aFd1sSDK;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean AFInAppEventType() {
        if (this.values.AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0) <= 0) {
            return true;
        }
        AFLogger.afRDLog("Install referrer will not load, the counter > 1, ");
        return false;
    }
}
