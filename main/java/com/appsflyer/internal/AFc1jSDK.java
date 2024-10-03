package com.appsflyer.internal;

import com.appsflyer.deeplink.DeepLink;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFc1jSDK implements AFe1jSDK<AFc1qSDK> {
    @Override // com.appsflyer.internal.AFe1jSDK
    public final /* synthetic */ AFc1qSDK AFKeystoreWrapper(String str) {
        JSONObject optJSONObject;
        String str2 = str;
        DeepLink deepLink = null;
        if (!(str2 == null || str2.length() == 0)) {
            JSONObject jSONObject = new JSONObject(str);
            boolean optBoolean = jSONObject.optBoolean("found", false);
            boolean optBoolean2 = jSONObject.optBoolean("is_second_ping", true);
            if (optBoolean && (optJSONObject = jSONObject.optJSONObject("click_event")) != null) {
                deepLink = DeepLink.values(optJSONObject);
                deepLink.AFInAppEventType.put("is_deferred", true);
            }
            return new AFc1qSDK(optBoolean2, deepLink);
        }
        return new AFc1qSDK(false, null, 3, null);
    }
}
