package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFc1eSDK implements AFc1oSDK {
    private final AFd1tSDK AFInAppEventParameterName;

    public AFc1eSDK(AFd1tSDK aFd1tSDK) {
        Intrinsics.checkNotNullParameter(aFd1tSDK, "");
        this.AFInAppEventParameterName = aFd1tSDK;
    }

    @Override // com.appsflyer.internal.AFc1oSDK
    public final Map<String, Object> AFInAppEventParameterName() {
        if (this.AFInAppEventParameterName.valueOf("deeplink_data")) {
            try {
                String AFKeystoreWrapper = this.AFInAppEventParameterName.AFKeystoreWrapper("deeplink_data", (String) null);
                return AFKeystoreWrapper == null ? MapsKt.emptyMap() : AFi1cSDK.AFInAppEventType(new JSONObject(AFKeystoreWrapper));
            } catch (Throwable th) {
                AFLogger.afErrorLog("Exception while parsing stored deeplink data", th, true, false);
            }
        }
        return MapsKt.emptyMap();
    }

    @Override // com.appsflyer.internal.AFc1oSDK
    public final void values() {
        this.AFInAppEventParameterName.AFInAppEventType("deeplink_data");
    }

    @Override // com.appsflyer.internal.AFc1oSDK
    public final void values(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        this.AFInAppEventParameterName.valueOf("deeplink_data", new JSONObject(map).toString());
    }
}
