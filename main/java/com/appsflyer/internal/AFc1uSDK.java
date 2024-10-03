package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.deeplink.DeepLink;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.appsflyer.internal.AFe1fSDK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFc1uSDK {
    public DeepLinkListener AFInAppEventParameterName;
    public String AFInAppEventType;

    /* renamed from: d */
    public String[] f178d;
    public long registerClient;
    public final AFd1mSDK unregisterClient;
    public Intent valueOf;
    public Map<String, String> values;
    public List<List<String>> AFKeystoreWrapper = new ArrayList();

    /* renamed from: e */
    public final List<String> f179e = new ArrayList();

    public AFc1uSDK(AFd1mSDK aFd1mSDK) {
        this.unregisterClient = aFd1mSDK;
    }

    public final void AFKeystoreWrapper(AFc1pSDK aFc1pSDK, Intent intent, Context context) {
        AFd1lSDK aFd1lSDK = (AFd1lSDK) this.unregisterClient;
        if (context != null) {
            AFd1kSDK aFd1kSDK = aFd1lSDK.AFInAppEventParameterName;
            if (context != null) {
                aFd1kSDK.valueOf = context.getApplicationContext();
            }
        }
        if (!valueOf(intent, context, aFc1pSDK) && this.AFInAppEventParameterName != null && this.unregisterClient.AFInAppEventType().AFKeystoreWrapper.AFInAppEventParameterName("appsFlyerCount", 0) == 0 && !this.unregisterClient.values().values("ddl_sent")) {
            AFc1kSDK aFc1kSDK = new AFc1kSDK();
            AFe1fSDK afInfoLog = this.unregisterClient.afInfoLog();
            afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(new AFf1nSDK(aFc1kSDK, this.unregisterClient)));
        }
        this.unregisterClient.values().values("ddl_sent", true);
    }

    public final void valueOf(Context context, AFc1pSDK aFc1pSDK, Uri uri) {
        AFf1mSDK aFf1mSDK = new AFf1mSDK(context, aFc1pSDK, uri, this.f179e);
        AFe1fSDK afInfoLog = this.unregisterClient.afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1mSDK));
        this.valueOf = null;
    }

    private Uri values(Object obj, Iterator<String> it) {
        while (obj != JSONObject.NULL) {
            if (!it.hasNext()) {
                Uri parse = Uri.parse(obj.toString());
                if (parse == null || parse.getScheme() == null || parse.getHost() == null) {
                    return null;
                }
                return parse;
            }
            try {
                obj = new JSONObject(obj.toString()).get(it.next());
            } catch (JSONException e) {
                AFLogger.afErrorLogForExcManagerOnly("recursiveSearch error", e);
                return null;
            }
        }
        return null;
    }

    public final void valueOf(String str, DeepLinkResult.Error error) {
        if (this.AFInAppEventParameterName != null) {
            AFLogger.INSTANCE.m96d(AFg1gSDK.DDL, "Error occurred: ".concat(String.valueOf(str)));
            valueOf(new DeepLinkResult(null, error));
        } else {
            values(str);
        }
    }

    public final void AFInAppEventType(Map<String, String> map) {
        DeepLinkResult deepLinkResult;
        if (this.AFInAppEventParameterName != null) {
            try {
                try {
                    DeepLink values = DeepLink.values(map);
                    values.AFInAppEventType.put("is_deferred", false);
                    deepLinkResult = new DeepLinkResult(values, null);
                } catch (JSONException e) {
                    AFLogger.INSTANCE.m98e(AFg1gSDK.DDL, "Error occurred", e, true);
                    deepLinkResult = new DeepLinkResult(null, DeepLinkResult.Error.UNEXPECTED);
                }
                valueOf(deepLinkResult);
                return;
            } catch (Throwable th) {
                valueOf(new DeepLinkResult(null, null));
                throw th;
            }
        }
        AFInAppEventParameterName(map);
    }

    public final void valueOf(DeepLinkResult deepLinkResult) {
        if (this.AFInAppEventParameterName != null) {
            AFLogger aFLogger = AFLogger.INSTANCE;
            AFg1gSDK aFg1gSDK = AFg1gSDK.DDL;
            StringBuilder sb = new StringBuilder("Calling onDeepLinking with:\n");
            sb.append(deepLinkResult.toString());
            aFLogger.m96d(aFg1gSDK, sb.toString());
            try {
                this.AFInAppEventParameterName.onDeepLinking(deepLinkResult);
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
                return;
            }
        }
        AFLogger.INSTANCE.m96d(AFg1gSDK.DDL, "skipping, no callback registered");
    }

    private static void AFInAppEventParameterName(Map<String, String> map) {
        AppsFlyerConversionListener appsFlyerConversionListener = AFb1tSDK.valueOf().AFInAppEventType;
        if (appsFlyerConversionListener != null) {
            try {
                StringBuilder sb = new StringBuilder("Calling onAppOpenAttribution with:\n");
                sb.append(map.toString());
                AFLogger.afDebugLog(sb.toString());
                appsFlyerConversionListener.onAppOpenAttribution(map);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    private static void values(String str) {
        AppsFlyerConversionListener appsFlyerConversionListener = AFb1tSDK.valueOf().AFInAppEventType;
        if (appsFlyerConversionListener != null) {
            try {
                AFLogger.afDebugLog("Calling onAppOpenAttributionFailure with: ".concat(String.valueOf(str)));
                appsFlyerConversionListener.onAttributionFailure(str);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:42:0x0047->B:54:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean valueOf(android.content.Intent r9, android.content.Context r10, com.appsflyer.internal.AFc1pSDK r11) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFc1uSDK.valueOf(android.content.Intent, android.content.Context, com.appsflyer.internal.AFc1pSDK):boolean");
    }
}
