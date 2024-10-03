package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.util.Map;
import org.json.JSONException;

/* loaded from: classes.dex */
public final class AFg1mSDK extends AFe1eSDK<Void> {
    private final Throwable AFLogger;

    /* renamed from: e */
    private final AFd1tSDK f273e;
    private final AFe1hSDK<String> unregisterClient;

    @Override // com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 1000L;
    }

    public AFg1mSDK(AFf1jSDK aFf1jSDK, AFd1tSDK aFd1tSDK) {
        super(AFf1zSDK.GCDSDK, new AFf1zSDK[]{AFf1zSDK.RC_CDN}, "GCD-CHECK");
        this.AFLogger = aFf1jSDK.m85e();
        this.unregisterClient = aFf1jSDK.AFLogger;
        this.f273e = aFd1tSDK;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() throws Exception {
        AFLogger.afDebugLog("[GCD-A01] Loading conversion data. Counter: ".concat(String.valueOf(this.f273e.AFInAppEventParameterName("appsFlyerCount", 0))));
        long AFKeystoreWrapper = this.f273e.AFKeystoreWrapper("appsflyerConversionDataCacheExpiration", 0L);
        if (AFKeystoreWrapper != 0 && System.currentTimeMillis() - AFKeystoreWrapper > 5184000000L) {
            AFLogger.afDebugLog("[GCD-E02] Cached conversion data expired");
            this.f273e.values("sixtyDayConversionData", true);
            this.f273e.valueOf("attributionId", null);
            this.f273e.AFInAppEventParameterName("appsflyerConversionDataCacheExpiration", 0L);
        }
        Map<String, Object> registerClient = registerClient();
        if (registerClient != null) {
            try {
                if (!registerClient.containsKey("is_first_launch")) {
                    registerClient.put("is_first_launch", Boolean.FALSE);
                }
                AFg1jSDK.AFInAppEventType(registerClient);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder("[GCD] Error executing conversion data callback: ");
                sb.append(e.getLocalizedMessage());
                AFLogger.afErrorLog(sb.toString(), e);
            }
            return AFe1dSDK.SUCCESS;
        }
        try {
        } catch (Exception e2) {
            StringBuilder sb2 = new StringBuilder("[GCD] Error executing conversion data callback: ");
            sb2.append(e2.getLocalizedMessage());
            AFLogger.afErrorLog(sb2.toString(), e2);
        }
        if (this.AFLogger != null) {
            StringBuilder sb3 = new StringBuilder("Launch exception: ");
            sb3.append(this.AFLogger.getMessage());
            AFg1jSDK.AFInAppEventParameterName(sb3.toString());
            return AFe1dSDK.SUCCESS;
        }
        AFe1hSDK<String> aFe1hSDK = this.unregisterClient;
        if (aFe1hSDK != null && !aFe1hSDK.isSuccessful()) {
            StringBuilder sb4 = new StringBuilder("Launch status code: ");
            sb4.append(this.unregisterClient.getStatusCode());
            AFg1jSDK.AFInAppEventParameterName(sb4.toString());
            return AFe1dSDK.SUCCESS;
        }
        return AFe1dSDK.FAILURE;
    }

    private Map<String, Object> registerClient() {
        String AFKeystoreWrapper = this.f273e.AFKeystoreWrapper("attributionId", (String) null);
        if (AFKeystoreWrapper == null) {
            return null;
        }
        try {
            new AFe1tSDK();
            return AFe1tSDK.AFInAppEventType(AFKeystoreWrapper);
        } catch (JSONException e) {
            StringBuilder sb = new StringBuilder("[GCD] Failed to parse GCD response: ");
            sb.append(e.getMessage());
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }
}
