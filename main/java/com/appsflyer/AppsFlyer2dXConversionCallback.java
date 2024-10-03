package com.appsflyer;

import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.appsflyer.share.LinkGenerator;
import java.util.Map;

/* loaded from: classes.dex */
public class AppsFlyer2dXConversionCallback implements AppsFlyerConversionListener, DeepLinkListener, LinkGenerator.ResponseListener {
    public native void onAppOpenAttributionNative(Object obj);

    public native void onAttributionFailureNative(Object obj);

    public native void onDeepLinkingNative(DeepLinkResult deepLinkResult);

    public native void onInstallConversionDataLoadedNative(Object obj);

    public native void onInstallConversionFailureNative(Object obj);

    public native void onResponseErrorNative(String str);

    public native void onResponseNative(String str);

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onConversionDataSuccess(Map<String, Object> map) {
        onInstallConversionDataLoadedNative(map);
    }

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onConversionDataFail(String str) {
        valueOf("onAttributionFailure", str);
    }

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onAppOpenAttribution(Map<String, String> map) {
        onAppOpenAttributionNative(map);
    }

    @Override // com.appsflyer.AppsFlyerConversionListener
    public void onAttributionFailure(String str) {
        valueOf("onInstallConversionFailure", str);
    }

    @Override // com.appsflyer.deeplink.DeepLinkListener
    public void onDeepLinking(DeepLinkResult deepLinkResult) {
        onDeepLinkingNative(deepLinkResult);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003f A[Catch: JSONException -> 0x0043, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0043, blocks: (B:2:0x0000, B:13:0x003b, B:15:0x003f, B:17:0x0021, B:20:0x002b), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void valueOf(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L43
            r0.<init>()     // Catch: org.json.JSONException -> L43
            java.lang.String r1 = "status"
            java.lang.String r2 = "failure"
            r0.put(r1, r2)     // Catch: org.json.JSONException -> L43
            java.lang.String r1 = "data"
            r0.put(r1, r8)     // Catch: org.json.JSONException -> L43
            int r8 = r7.hashCode()     // Catch: org.json.JSONException -> L43
            r1 = -1390007222(0xffffffffad262c4a, float:-9.445842E-12)
            r2 = 1
            if (r8 == r1) goto L2b
            r1 = 1050716216(0x3ea0a838, float:0.3137834)
            if (r8 == r1) goto L21
            goto L35
        L21:
            java.lang.String r8 = "onInstallConversionFailure"
            boolean r7 = r7.equals(r8)     // Catch: org.json.JSONException -> L43
            if (r7 == 0) goto L35
            r7 = 0
            goto L36
        L2b:
            java.lang.String r8 = "onAttributionFailure"
            boolean r7 = r7.equals(r8)     // Catch: org.json.JSONException -> L43
            if (r7 == 0) goto L35
            r7 = r2
            goto L36
        L35:
            r7 = -1
        L36:
            if (r7 == 0) goto L3f
            if (r7 == r2) goto L3b
            goto L3e
        L3b:
            r6.onAttributionFailureNative(r0)     // Catch: org.json.JSONException -> L43
        L3e:
            return
        L3f:
            r6.onInstallConversionFailureNative(r0)     // Catch: org.json.JSONException -> L43
            return
        L43:
            r7 = move-exception
            r3 = r7
            com.appsflyer.AFLogger r0 = com.appsflyer.AFLogger.INSTANCE
            com.appsflyer.internal.AFg1gSDK r1 = com.appsflyer.internal.AFg1gSDK.OTHER
            java.lang.String r2 = "2dx error"
            r4 = 0
            r5 = 0
            r0.m99e(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyer2dXConversionCallback.valueOf(java.lang.String, java.lang.String):void");
    }

    @Override // com.appsflyer.share.LinkGenerator.ResponseListener
    public void onResponse(String str) {
        onResponseNative(str);
    }

    @Override // com.appsflyer.share.LinkGenerator.ResponseListener
    public void onResponseError(String str) {
        onResponseErrorNative(str);
    }
}
