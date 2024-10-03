package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.internal.AFi1jSDK;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFf1jSDK extends AFf1hSDK {
    private final AppsFlyerProperties afDebugLog;
    private final AFf1bSDK afErrorLog;
    public Map<String, Object> afInfoLog;
    private final AFg1cSDK afRDLog;
    private final AFc1oSDK afWarnLog;
    private final AFi1iSDK force;

    /* renamed from: w */
    private final AFd1tSDK f217w;

    public AFf1jSDK(AFa1pSDK aFa1pSDK, AFd1mSDK aFd1mSDK) {
        super(aFa1pSDK, aFd1mSDK);
        this.force = aFd1mSDK.mo79v();
        this.f217w = aFd1mSDK.values();
        this.afRDLog = aFd1mSDK.AFLogger();
        this.afErrorLog = aFd1mSDK.mo76d();
        this.afDebugLog = AppsFlyerProperties.getInstance();
        this.afWarnLog = aFd1mSDK.AppsFlyer2dXConversionCallback();
        this.valueOf.add(AFf1zSDK.RESOLVE_ESP);
        this.valueOf.add(AFf1zSDK.DLSDK);
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        super.AFKeystoreWrapper();
        AFg1cSDK aFg1cSDK = this.afRDLog;
        int i = ((AFf1hSDK) this).f212e.registerClient;
        long currentTimeMillis = System.currentTimeMillis();
        if (i == 1) {
            if (aFg1cSDK.registerClient != 0) {
                aFg1cSDK.AFInAppEventParameterName.put("net", Long.valueOf(currentTimeMillis - aFg1cSDK.registerClient));
                aFg1cSDK.AFInAppEventType.valueOf("first_launch", new JSONObject(aFg1cSDK.AFInAppEventParameterName).toString());
                return;
            }
            AFLogger.afInfoLog("Metrics: launch start ts is missing");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.appsflyer.internal.AFf1hSDK
    public final void AFInAppEventType(AFa1pSDK aFa1pSDK) {
        super.AFInAppEventType(aFa1pSDK);
        int i = aFa1pSDK.registerClient;
        this.afRDLog.valueOf(i);
        Map map = (Map) aFa1pSDK.valueOf().get("meta");
        if (map == null) {
            map = new HashMap();
            aFa1pSDK.valueOf().put("meta", map);
        }
        if (!aFa1pSDK.valueOf().containsKey("af_deeplink")) {
            aFa1pSDK.AFInAppEventParameterName(this.afWarnLog.AFInAppEventParameterName());
        }
        AFh1eSDK AFInAppEventParameterName = this.afErrorLog.AFInAppEventParameterName();
        if (AFInAppEventParameterName != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("cdn_token", AFInAppEventParameterName.valueOf);
            if (AFInAppEventParameterName.AFInAppEventParameterName != null) {
                hashMap.put("c_ver", AFInAppEventParameterName.AFInAppEventParameterName);
            }
            if (AFInAppEventParameterName.values > 0) {
                hashMap.put("latency", Long.valueOf(AFInAppEventParameterName.values));
            }
            if (AFInAppEventParameterName.AFKeystoreWrapper > 0) {
                hashMap.put("delay", Long.valueOf(AFInAppEventParameterName.AFKeystoreWrapper));
            }
            if (AFInAppEventParameterName.AFInAppEventType > 0) {
                hashMap.put("res_code", Integer.valueOf(AFInAppEventParameterName.AFInAppEventType));
            }
            if (AFInAppEventParameterName.AFLogger != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(AFInAppEventParameterName.AFLogger.getClass().getSimpleName());
                sb.append(": ");
                sb.append(AFInAppEventParameterName.AFLogger.getMessage());
                hashMap.put("error", sb.toString());
            }
            if (AFInAppEventParameterName.f274d != null) {
                hashMap.put("sig", AFInAppEventParameterName.f274d.toString());
            }
            if (AFInAppEventParameterName.unregisterClient != null) {
                hashMap.put("cdn_cache_status", AFInAppEventParameterName.unregisterClient);
            }
            map.put("rc", hashMap);
        }
        ((AFf1hSDK) this).f214v.AFKeystoreWrapper(aFa1pSDK.valueOf());
        if (i == 1) {
            if (this.afDebugLog.getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false)) {
                aFa1pSDK.valueOf().put("wait_cid", Boolean.toString(true));
            }
            HashMap hashMap2 = new HashMap(this.afRDLog.AFKeystoreWrapper);
            this.afRDLog.AFInAppEventType.AFInAppEventType("ddl");
            if (!hashMap2.isEmpty()) {
                map.put("ddl", hashMap2);
            }
            HashMap hashMap3 = new HashMap(this.afRDLog.AFInAppEventParameterName);
            if (!hashMap3.isEmpty()) {
                map.put("first_launch", hashMap3);
            }
        } else if (i == 2) {
            HashMap hashMap4 = new HashMap(this.afRDLog.AFInAppEventParameterName);
            if (!hashMap4.isEmpty()) {
                map.put("first_launch", hashMap4);
            }
            this.afRDLog.AFInAppEventType.AFInAppEventType("first_launch");
        }
        if (map.isEmpty()) {
            aFa1pSDK.valueOf().remove("meta");
        }
        if (i <= 2) {
            ArrayList arrayList = new ArrayList();
            for (AFi1jSDK aFi1jSDK : this.force.AFKeystoreWrapper()) {
                boolean z = aFi1jSDK instanceof AFi1sSDK;
                int i2 = C07155.AFInAppEventParameterName[aFi1jSDK.f285d.ordinal()];
                if (i2 == 1) {
                    if (z) {
                        aFa1pSDK.AFInAppEventType("rfr", ((AFi1sSDK) aFi1jSDK).values);
                        this.f217w.values(AppsFlyerProperties.NEW_REFERRER_SENT, true);
                    }
                    arrayList.add(aFi1jSDK.valueOf);
                } else if (i2 == 2 && i == 2 && !z) {
                    HashMap hashMap5 = new HashMap();
                    hashMap5.put("source", aFi1jSDK.AFKeystoreWrapper);
                    hashMap5.put("response", "TIMEOUT");
                    hashMap5.put(ShareConstants.MEDIA_TYPE, aFi1jSDK.unregisterClient);
                    arrayList.add(hashMap5);
                }
            }
            if (!arrayList.isEmpty()) {
                aFa1pSDK.AFInAppEventType("referrers", arrayList);
            }
            Object obj = this.afInfoLog;
            if (obj != null) {
                aFa1pSDK.AFInAppEventType("fb_ddl", obj);
            }
        }
        ((AFf1hSDK) this).f214v.AFKeystoreWrapper(aFa1pSDK);
    }

    /* renamed from: com.appsflyer.internal.AFf1jSDK$5 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C07155 {
        static final /* synthetic */ int[] AFInAppEventParameterName;

        static {
            int[] iArr = new int[AFi1jSDK.AFa1tSDK.values().length];
            AFInAppEventParameterName = iArr;
            try {
                iArr[AFi1jSDK.AFa1tSDK.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                AFInAppEventParameterName[AFi1jSDK.AFa1tSDK.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
