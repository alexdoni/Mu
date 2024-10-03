package com.appsflyer.internal;

import android.os.Build;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.PurchaseHandler;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class AFf1sSDK extends AFf1rSDK<String> {
    private final String afDebugLog;
    private final String afErrorLog;
    private final AFf1zSDK afInfoLog;
    private final PurchaseHandler.PurchaseValidationCallback afVerboseLog;

    /* renamed from: e */
    private final AFd1sSDK f237e;
    private final AFd1tSDK force;

    /* renamed from: i */
    private final AFg1qSDK f238i;

    /* renamed from: v */
    private final Map<String, Object> f239v;

    /* renamed from: w */
    private final AFg1vSDK f240w;

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AppsFlyerRequestListener registerClient() {
        return null;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        return true;
    }

    public AFf1sSDK(AFf1zSDK aFf1zSDK, AFf1zSDK[] aFf1zSDKArr, AFd1mSDK aFd1mSDK, Map<String, Object> map, PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback) {
        super(aFf1zSDK, aFf1zSDKArr, aFd1mSDK, null);
        this.afInfoLog = aFf1zSDK;
        AFd1sSDK AFInAppEventType = aFd1mSDK.AFInAppEventType();
        this.f237e = AFInAppEventType;
        AFd1tSDK values = aFd1mSDK.values();
        this.force = values;
        AFg1qSDK unregisterClient = aFd1mSDK.unregisterClient();
        this.f238i = unregisterClient;
        AFg1vSDK onInstallConversionFailureNative = aFd1mSDK.onInstallConversionFailureNative();
        this.f240w = onInstallConversionFailureNative;
        String str = map.containsKey("billing_library_version") ? (String) map.remove("billing_library_version") : null;
        this.afDebugLog = str;
        String str2 = map.containsKey("connector_version") ? (String) map.remove("connector_version") : null;
        this.afErrorLog = str2;
        HashMap hashMap = new HashMap(new HashMap(map));
        hashMap.put("app_id", AFInAppEventType.AFInAppEventParameterName.valueOf.getPackageName());
        hashMap.put("event_timestamp", Long.valueOf(unregisterClient.values()));
        String AFInAppEventType2 = AFd1sSDK.AFInAppEventType();
        if (AFInAppEventType2 != null) {
            hashMap.put("cuid", AFInAppEventType2);
        }
        hashMap.put("app_version_name", AFb1uSDK.AFKeystoreWrapper(AFInAppEventType.AFInAppEventParameterName.valueOf, AFInAppEventType.AFInAppEventParameterName.valueOf.getPackageName()));
        HashMap hashMap2 = new HashMap();
        String valueOf = AFInAppEventType.valueOf();
        if (!AFc1rSDK.AFInAppEventType(valueOf)) {
            hashMap2.put("advertising_id", valueOf);
        }
        AFa1bSDK valueOf2 = AFb1rSDK.valueOf(AFInAppEventType.AFInAppEventParameterName.valueOf.getContentResolver());
        String str3 = valueOf2 != null ? valueOf2.AFInAppEventParameterName : null;
        if (!AFc1rSDK.AFInAppEventType(str3)) {
            hashMap2.put("oaid", str3);
        }
        AFa1bSDK valueOf3 = AFb1rSDK.valueOf(AFInAppEventType.AFInAppEventParameterName.valueOf.getContentResolver());
        String str4 = valueOf3 != null ? valueOf3.AFInAppEventParameterName : null;
        if (!AFc1rSDK.AFInAppEventType(str4)) {
            hashMap2.put("amazon_aid", str4);
        }
        if (!AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            String values2 = ((AFf1rSDK) this).registerClient.values(values);
            if (!AFc1rSDK.AFInAppEventType(values2)) {
                hashMap2.put("imei", values2);
            }
        }
        hashMap2.put("appsflyer_id", AFb1kSDK.AFInAppEventType(AFInAppEventType.AFInAppEventParameterName, AFInAppEventType.AFKeystoreWrapper));
        StringBuilder sb = new StringBuilder();
        sb.append(Build.VERSION.SDK_INT);
        hashMap2.put("os_version", sb.toString());
        hashMap2.put(ComConstants.sdk_version_code, "6.13.1");
        if (!AFc1rSDK.AFInAppEventType(str2)) {
            hashMap2.put("sdk_connector_version", str2);
        }
        hashMap.put("device_data", hashMap2);
        if (!AFc1rSDK.AFInAppEventType(str)) {
            hashMap.put("billing_lib_version", str);
        }
        onInstallConversionFailureNative.AFInAppEventParameterName(hashMap, aFf1zSDK);
        this.f239v = hashMap;
        this.afVerboseLog = purchaseValidationCallback;
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback;
        PurchaseHandler.PurchaseValidationCallback purchaseValidationCallback2;
        super.AFKeystoreWrapper();
        Throwable m85e = m85e();
        if (m85e != null && (purchaseValidationCallback2 = this.afVerboseLog) != null) {
            purchaseValidationCallback2.onFailure(m85e);
        }
        ResponseNetwork<String> responseNetwork = this.AFLogger;
        if (responseNetwork == null || (purchaseValidationCallback = this.afVerboseLog) == null) {
            return;
        }
        purchaseValidationCallback.onResponse(responseNetwork);
    }

    /* renamed from: w */
    public final String m94w() {
        return this.afDebugLog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Map<String, Object> force() {
        return this.f239v;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void AFInAppEventParameterName(String str) {
        String jSONObject = new JSONObject(this.f239v).toString();
        StringBuilder sb = new StringBuilder();
        sb.append(this);
        sb.append(": preparing data: ");
        sb.append(jSONObject);
        AFb1lSDK.values(sb.toString());
        ((AFf1rSDK) this).unregisterClient.valueOf(str, jSONObject);
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public boolean AFInAppEventParameterName() {
        if (this.AFLogger == null || this.AFLogger.getStatusCode() != 503) {
            return super.AFInAppEventParameterName();
        }
        return true;
    }
}
