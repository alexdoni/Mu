package com.appsflyer;

import com.appsflyer.internal.AFb1mSDK;
import com.appsflyer.internal.AFd1mSDK;
import com.appsflyer.internal.AFd1sSDK;
import com.appsflyer.internal.AFe1fSDK;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import java.util.Map;

/* loaded from: classes.dex */
public final class PurchaseHandler {
    private final AFd1sSDK AFInAppEventParameterName;
    public final AFd1mSDK AFKeystoreWrapper;
    public final AFe1fSDK valueOf;

    /* loaded from: classes.dex */
    public interface PurchaseValidationCallback {
        void onFailure(Throwable th);

        void onResponse(ResponseNetwork<String> responseNetwork);
    }

    public PurchaseHandler(AFd1mSDK aFd1mSDK) {
        this.AFKeystoreWrapper = aFd1mSDK;
        this.AFInAppEventParameterName = aFd1mSDK.AFInAppEventType();
        this.valueOf = aFd1mSDK.afInfoLog();
    }

    public final boolean valueOf(Map<String, Object> map, PurchaseValidationCallback purchaseValidationCallback, String... strArr) {
        boolean AFInAppEventType = AFb1mSDK.AFInAppEventType(map, strArr, this.AFInAppEventParameterName);
        if (!AFInAppEventType && purchaseValidationCallback != null) {
            purchaseValidationCallback.onFailure(new IllegalArgumentException("Invalid Request Data"));
        }
        return AFInAppEventType;
    }
}
