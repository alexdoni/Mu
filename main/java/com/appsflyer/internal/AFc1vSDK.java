package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFc1vSDK {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject values(String str) {
        JSONObject values = AFa1qSDK.values(str);
        if (values != null) {
            try {
                if (values.has("ol_id")) {
                    String optString = values.optString("ol_scheme", null);
                    String optString2 = values.optString("ol_domain", null);
                    String optString3 = values.optString("ol_ver", null);
                    if (optString != null) {
                        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_SCHEME, optString);
                    }
                    if (optString2 != null) {
                        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_DOMAIN, optString2);
                    }
                    if (optString3 != null) {
                        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_VERSION, optString3);
                    }
                }
            } catch (Throwable th) {
                StringBuilder sb = new StringBuilder("Error in handleResponse: ");
                sb.append(th.getMessage());
                AFLogger.afErrorLogForExcManagerOnly(sb.toString(), th);
                AFb1tSDK.valueOf().values().mo78i().AFInAppEventType();
                AFb1tSDK.valueOf().values().mo78i().AFKeystoreWrapper();
            }
        }
        return values;
    }
}
