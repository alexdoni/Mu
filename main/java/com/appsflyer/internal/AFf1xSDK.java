package com.appsflyer.internal;

import com.appsflyer.PurchaseHandler;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFf1xSDK extends AFf1sSDK {
    public AFf1xSDK(Map<String, Object> map, PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback, AFd1mSDK aFd1mSDK) {
        super(AFf1zSDK.ARS_VALIDATE, new AFf1zSDK[]{AFf1zSDK.RC_CDN, AFf1zSDK.FETCH_ADVERTISING_ID}, aFd1mSDK, map, purchaseValidationCallback);
        this.valueOf.add(AFf1zSDK.CONVERSION);
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AFe1uSDK<String> valueOf(String str) {
        AFe1uSDK<String> AFInAppEventParameterName = ((AFf1rSDK) this).f234d.AFInAppEventParameterName(force(), str, m94w());
        if (AFInAppEventParameterName != null) {
            AFInAppEventParameterName(AFInAppEventParameterName.AFKeystoreWrapper.valueOf);
        }
        return AFInAppEventParameterName;
    }

    @Override // com.appsflyer.internal.AFf1sSDK, com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        if (this.AFLogger == null || this.AFLogger.getStatusCode() != 424) {
            return super.AFInAppEventParameterName();
        }
        return true;
    }
}
