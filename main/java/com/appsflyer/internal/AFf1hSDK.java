package com.appsflyer.internal;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AFf1hSDK extends AFf1rSDK<String> {
    private static final AFf1zSDK[] afErrorLog = {AFf1zSDK.DLSDK, AFf1zSDK.ONELINK, AFf1zSDK.REGISTER};
    private final AFe1gSDK afInfoLog;
    private final AFd1kSDK afVerboseLog;
    private final AFg1vSDK afWarnLog;

    /* renamed from: e */
    public final AFa1pSDK f212e;
    private final AFf1bSDK force;

    /* renamed from: i */
    protected final AFd1tSDK f213i;

    /* renamed from: v */
    protected final AFg1qSDK f214v;

    /* renamed from: w */
    private final AFd1sSDK f215w;

    @Override // com.appsflyer.internal.AFf1rSDK
    protected boolean unregisterClient() {
        return true;
    }

    public AFf1hSDK(AFa1pSDK aFa1pSDK, AFd1mSDK aFd1mSDK) {
        this(aFa1pSDK, aFd1mSDK, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFf1hSDK(AFa1pSDK aFa1pSDK, AFd1mSDK aFd1mSDK, String str) {
        super(aFa1pSDK.AFKeystoreWrapper(), new AFf1zSDK[]{AFf1zSDK.RC_CDN, AFf1zSDK.FETCH_ADVERTISING_ID}, aFd1mSDK, str);
        this.f212e = aFa1pSDK;
        this.afInfoLog = aFd1mSDK.afVerboseLog();
        this.f213i = aFd1mSDK.values();
        this.force = aFd1mSDK.mo76d();
        this.afVerboseLog = aFd1mSDK.mo80w();
        this.f215w = aFd1mSDK.AFInAppEventType();
        this.f214v = aFd1mSDK.unregisterClient();
        this.afWarnLog = aFd1mSDK.onInstallConversionFailureNative();
        for (AFf1zSDK aFf1zSDK : afErrorLog) {
            if (this.AFInAppEventParameterName == aFf1zSDK) {
                return;
            }
        }
        int i = this.f212e.registerClient;
        AFf1zSDK aFf1zSDK2 = this.AFInAppEventParameterName;
        if (i <= 0) {
            if (aFf1zSDK2 != AFf1zSDK.CONVERSION) {
                this.AFKeystoreWrapper.add(AFf1zSDK.CONVERSION);
                return;
            }
            return;
        }
        this.valueOf.add(AFf1zSDK.CONVERSION);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x010c  */
    @Override // com.appsflyer.internal.AFf1rSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final com.appsflyer.internal.AFe1uSDK<java.lang.String> valueOf(java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1hSDK.valueOf(java.lang.String):com.appsflyer.internal.AFe1uSDK");
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AppsFlyerRequestListener registerClient() {
        return this.f212e.AFInAppEventType;
    }

    protected void AFKeystoreWrapper(AFa1pSDK aFa1pSDK) {
        this.f214v.valueOf(aFa1pSDK.valueOf());
    }

    protected void AFInAppEventParameterName(AFa1pSDK aFa1pSDK) {
        this.f214v.AFInAppEventType(aFa1pSDK);
    }

    protected void valueOf(AFa1pSDK aFa1pSDK) {
        this.f214v.valueOf(aFa1pSDK);
    }

    /* renamed from: d */
    private static Map<String, Object> m88d(AFa1pSDK aFa1pSDK) {
        Map<String, Object> map = (Map) aFa1pSDK.valueOf().get("meta");
        if (map != null) {
            return map;
        }
        HashMap hashMap = new HashMap();
        aFa1pSDK.valueOf().put("meta", hashMap);
        return hashMap;
    }

    protected void values(AFa1pSDK aFa1pSDK) {
        this.f214v.values(aFa1pSDK);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void AFInAppEventType(AFa1pSDK aFa1pSDK) {
        AFe1cSDK aFe1cSDK;
        boolean z = true;
        try {
            AFKeystoreWrapper(aFa1pSDK);
            valueOf(aFa1pSDK);
            AFInAppEventParameterName(aFa1pSDK);
            values(aFa1pSDK);
        } catch (Throwable th) {
            AFLogger.afErrorLog("Error while collecting payload params", th, true, false);
        }
        if (aFa1pSDK.mo61e()) {
            aFa1pSDK.AFInAppEventParameterName(new AFd1rSDK(aFa1pSDK.valueOf(), ((AFf1rSDK) this).registerClient.AFInAppEventType.valueOf));
            aFa1pSDK.AFInAppEventParameterName((Map<String, ?>) ((AFf1rSDK) this).registerClient.AFInAppEventType(aFa1pSDK.valueOf()));
            if (this.f215w.values("com.appsflyer.security.enable")) {
                AFg1xSDK aFg1xSDK = ((AFf1rSDK) this).registerClient;
                try {
                    new AFb1sSDK(aFa1pSDK).afInfoLog();
                } catch (Exception e) {
                    AFLogger.afErrorLogForExcManagerOnly("native: reflection init failed", e);
                }
            }
        }
        if (aFa1pSDK.mo60d()) {
            aFa1pSDK.AFInAppEventParameterName((Map<String, ?>) ((AFf1rSDK) this).registerClient.values());
        }
        Set<AFf1zSDK> set = this.AFKeystoreWrapper;
        boolean z2 = set.contains(AFf1zSDK.LAUNCH) || set.contains(AFf1zSDK.CONVERSION);
        if (m84d() && z2) {
            aFa1pSDK.AFInAppEventType(this.f213i.AFInAppEventParameterName("appsFlyerCount", 0));
        }
        if (aFa1pSDK.unregisterClient()) {
            Map<String, Object> m88d = m88d(aFa1pSDK);
            AFe1gSDK aFe1gSDK = this.afInfoLog;
            String AFInAppEventType = aFe1gSDK.AFInAppEventType();
            String AFInAppEventParameterName = aFe1gSDK.AFInAppEventParameterName();
            if (AFe1gSDK.valueOf()) {
                aFe1cSDK = AFe1cSDK.DEFAULT;
            } else {
                aFe1cSDK = AFe1cSDK.API;
            }
            AFe1iSDK aFe1iSDK = new AFe1iSDK(AFInAppEventType, AFInAppEventParameterName, aFe1cSDK);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", aFe1iSDK.values);
            if (aFe1iSDK.AFInAppEventParameterName != AFe1cSDK.DEFAULT) {
                jSONObject.put(FirebaseAnalytics.Param.METHOD, aFe1iSDK.AFInAppEventParameterName.valueOf);
            }
            String str = aFe1iSDK.valueOf;
            if (str != null && !StringsKt.isBlank(str)) {
                z = false;
            }
            if (!z) {
                jSONObject.put(RequestParameters.PREFIX, aFe1iSDK.valueOf);
            }
            m88d.put("host", jSONObject);
        }
        if (this.f215w.values("AF_PREINSTALL_DISABLED")) {
            m88d(aFa1pSDK).put("preinstall_disabled", Boolean.TRUE);
        }
        this.afWarnLog.AFInAppEventParameterName(aFa1pSDK.valueOf(), aFa1pSDK.AFKeystoreWrapper());
    }
}
