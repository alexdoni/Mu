package com.appsflyer.internal;

import android.content.Context;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerInAppPurchaseValidatorListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.internal.AFe1fSDK;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFa1cSDK implements Runnable {
    final String AFInAppEventParameterName;
    final String AFInAppEventType;
    final String AFKeystoreWrapper;
    private final Map<String, String> AFLogger;

    /* renamed from: d */
    private final String f157d;

    /* renamed from: e */
    private final AFd1sSDK f158e;
    private final String unregisterClient;
    private final String valueOf;
    private final WeakReference<Context> values;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AFa1cSDK(Context context, String str, AFd1sSDK aFd1sSDK, String str2, String str3, String str4, String str5, String str6, Map<String, String> map) {
        this.values = new WeakReference<>(context);
        this.valueOf = str;
        this.unregisterClient = str2;
        this.AFInAppEventParameterName = str4;
        this.AFInAppEventType = str5;
        this.AFKeystoreWrapper = str6;
        this.AFLogger = map;
        this.f157d = str3;
        this.f158e = aFd1sSDK;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.valueOf;
        if (str == null || str.length() == 0 || AppsFlyerLib.getInstance().isStopped()) {
            return;
        }
        try {
            Context context = this.values.get();
            if (context == null) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("public-key", this.unregisterClient);
            hashMap.put("sig-data", this.AFInAppEventParameterName);
            hashMap.put("signature", this.f157d);
            Object hashMap2 = new HashMap(hashMap);
            Object obj = this.AFLogger;
            String AFKeystoreWrapper = AFb1tSDK.valueOf().values().values().AFKeystoreWrapper("referrer", "");
            AFh1kSDK aFh1kSDK = new AFh1kSDK();
            aFh1kSDK.f160d = AFKeystoreWrapper;
            AFh1kSDK aFh1kSDK2 = aFh1kSDK;
            AFb1tSDK valueOf = AFb1tSDK.valueOf();
            Map<String, Object> valueOf2 = valueOf.valueOf(aFh1kSDK);
            valueOf2.put(FirebaseAnalytics.Param.PRICE, this.AFInAppEventType);
            valueOf2.put(FirebaseAnalytics.Param.CURRENCY, this.AFKeystoreWrapper);
            valueOf2.put("receipt_data", hashMap2);
            if (obj != null) {
                valueOf2.put("extra_prms", obj);
            }
            valueOf2.putAll(valueOf.values().force().values());
            aFh1kSDK.AFInAppEventParameterName((Map<String, ?>) valueOf2);
            aFh1kSDK.AFInAppEventParameterName(new AFi1eSDK(this.f158e).values(aFh1kSDK));
            AFInAppEventType(context, aFh1kSDK);
            hashMap.put("dev_key", this.valueOf);
            hashMap.put("app_id", context.getPackageName());
            hashMap.put("uid", AppsFlyerLib.getInstance().getAppsFlyerUID(context));
            AFh1xSDK aFh1xSDK = AFb1tSDK.valueOf().values().AFInAppEventType().valueOf.f189e;
            AFa1bSDK aFa1bSDK = aFh1xSDK != null ? new AFa1bSDK(aFh1xSDK.AFInAppEventType, aFh1xSDK.unregisterClient) : null;
            String str2 = aFa1bSDK != null ? aFa1bSDK.AFInAppEventParameterName : null;
            if (str2 != null) {
                hashMap.put("advertiserId", str2);
            }
            AFh1jSDK aFh1jSDK = (AFh1jSDK) new AFh1jSDK().AFInAppEventParameterName(hashMap);
            aFh1jSDK.AFInAppEventParameterName(new AFi1eSDK(this.f158e).values(aFh1jSDK));
            final AFf1hSDK AFInAppEventType = AFInAppEventType(context, aFh1jSDK);
            aFh1jSDK.AFInAppEventType = new AppsFlyerRequestListener() { // from class: com.appsflyer.internal.AFa1cSDK.1
                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public final void onSuccess() {
                    try {
                        JSONObject jSONObject = new JSONObject((String) AFInAppEventType.AFLogger.getBody());
                        AFLogger.afInfoLog("Validate response ok: ".concat(String.valueOf(jSONObject)));
                        AFa1cSDK.AFInAppEventParameterName(jSONObject.optBoolean("result"), AFa1cSDK.this.AFInAppEventParameterName, AFa1cSDK.this.AFInAppEventType, AFa1cSDK.this.AFKeystoreWrapper, jSONObject.toString());
                    } catch (Exception e) {
                        AFLogger.afErrorLog("Failed Validate request: ".concat(String.valueOf(e)), e);
                        AFa1cSDK.AFInAppEventParameterName(false, AFa1cSDK.this.AFInAppEventParameterName, AFa1cSDK.this.AFInAppEventType, AFa1cSDK.this.AFKeystoreWrapper, e.getMessage());
                    }
                }

                @Override // com.appsflyer.attribution.AppsFlyerRequestListener
                public final void onError(int i, String str3) {
                    ResponseNetwork responseNetwork;
                    if (i == 50 && (responseNetwork = AFInAppEventType.AFLogger) != null) {
                        str3 = responseNetwork.toString();
                    }
                    AFa1cSDK.AFInAppEventParameterName(false, AFa1cSDK.this.AFInAppEventParameterName, AFa1cSDK.this.AFInAppEventType, AFa1cSDK.this.AFKeystoreWrapper, str3);
                }
            };
        } catch (Throwable th) {
            if (AFb1tSDK.AFKeystoreWrapper != null) {
                AFLogger.afErrorLog("Failed Validate request + ex", th);
                AFInAppEventParameterName(false, this.AFInAppEventParameterName, this.AFInAppEventType, this.AFKeystoreWrapper, th.getMessage());
            }
            AFLogger.afErrorLog(th.getMessage(), th);
        }
    }

    private static AFf1hSDK AFInAppEventType(Context context, AFh1lSDK aFh1lSDK) {
        AFb1tSDK.valueOf().AFInAppEventParameterName(context);
        AFd1mSDK values = AFb1tSDK.valueOf().values();
        aFh1lSDK.AFInAppEventType(values.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0));
        AFf1hSDK aFf1hSDK = new AFf1hSDK(aFh1lSDK, values);
        AFe1fSDK afInfoLog = values.afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1hSDK));
        return aFf1hSDK;
    }

    static void AFInAppEventParameterName(boolean z, String str, String str2, String str3, String str4) {
        if (AFb1tSDK.AFKeystoreWrapper != null) {
            StringBuilder sb = new StringBuilder("Validate callback parameters: ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            sb.append(" ");
            sb.append(str3);
            AFLogger.afDebugLog(sb.toString());
            if (z) {
                AFLogger.afDebugLog("Validate in app purchase success: ".concat(String.valueOf(str4)));
                AFb1tSDK.AFKeystoreWrapper.onValidateInApp();
                return;
            }
            AFLogger.afDebugLog("Validate in app purchase failed: ".concat(String.valueOf(str4)));
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = AFb1tSDK.AFKeystoreWrapper;
            if (str4 == null) {
                str4 = "Failed validating";
            }
            appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure(str4);
        }
    }
}
