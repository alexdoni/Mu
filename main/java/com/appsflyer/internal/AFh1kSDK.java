package com.appsflyer.internal;

import com.appsflyer.AFInAppEventType;

/* loaded from: classes.dex */
public final class AFh1kSDK extends AFh1lSDK {
    public AFh1kSDK() {
        super(AFInAppEventType.PURCHASE, Boolean.TRUE);
    }

    @Override // com.appsflyer.internal.AFa1pSDK
    public final AFa1pSDK AFInAppEventParameterName(String str) {
        return super.AFInAppEventParameterName(AFKeystoreWrapper(str));
    }

    @Override // com.appsflyer.internal.AFa1pSDK
    public final AFf1zSDK AFKeystoreWrapper() {
        return AFf1zSDK.PURCHASE_VALIDATE;
    }
}
