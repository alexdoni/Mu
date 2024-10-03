package com.appsflyer.internal;

import com.appsflyer.PurchaseHandler;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFf1oSDK extends AFf1sSDK {
    public AFf1oSDK(Map<String, Object> map, PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback, AFd1mSDK aFd1mSDK) {
        super(AFf1zSDK.PURCHASE_VALIDATE, new AFf1zSDK[]{AFf1zSDK.RC_CDN, AFf1zSDK.FETCH_ADVERTISING_ID}, aFd1mSDK, map, purchaseValidationCallback);
        this.valueOf.add(AFf1zSDK.CONVERSION);
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AFe1uSDK<String> valueOf(String str) {
        AFe1uSDK<String> AFInAppEventType = ((AFf1rSDK) this).f234d.AFInAppEventType(force(), str, m94w());
        if (AFInAppEventType != null) {
            AFInAppEventParameterName(AFInAppEventType.AFKeystoreWrapper.valueOf);
        }
        return AFInAppEventType;
    }
}
