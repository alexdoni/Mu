package com.appsflyer.internal;

/* loaded from: classes.dex */
public final class AFh1sSDK extends AFa1pSDK {
    @Override // com.appsflyer.internal.AFa1pSDK
    public final boolean unregisterClient() {
        return true;
    }

    @Override // com.appsflyer.internal.AFa1pSDK
    public final AFf1zSDK AFKeystoreWrapper() {
        if (this.registerClient == 1) {
            return AFf1zSDK.CONVERSION;
        }
        return AFf1zSDK.LAUNCH;
    }
}
