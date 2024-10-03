package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerConversionListener;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFg1jSDK {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void AFInAppEventType(Map<String, Object> map) {
        AppsFlyerConversionListener appsFlyerConversionListener = AFb1tSDK.valueOf().AFInAppEventType;
        if (appsFlyerConversionListener != null) {
            StringBuilder sb = new StringBuilder("[GCD-A02] Calling onConversionDataSuccess with:\n");
            sb.append(map.toString());
            AFLogger.afDebugLog(sb.toString());
            appsFlyerConversionListener.onConversionDataSuccess(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void AFInAppEventParameterName(String str) {
        AppsFlyerConversionListener appsFlyerConversionListener = AFb1tSDK.valueOf().AFInAppEventType;
        if (appsFlyerConversionListener != null) {
            AFLogger.afDebugLog("[GCD-A02] Calling onConversionFailure with:\n".concat(String.valueOf(str)));
            appsFlyerConversionListener.onConversionDataFail(str);
        }
    }
}
