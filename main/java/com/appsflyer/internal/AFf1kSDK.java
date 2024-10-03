package com.appsflyer.internal;

import android.util.Base64;
import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes.dex */
public final class AFf1kSDK extends AFf1rSDK<String> {

    /* renamed from: e */
    private final AFh1qSDK f218e;

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AFe1uSDK<String> valueOf(String str) {
        String encodeToString = Base64.encodeToString(this.f218e.AFInAppEventType(), 2);
        AFLogger.afInfoLog("cached data: ".concat(String.valueOf(encodeToString)));
        ((AFf1rSDK) this).unregisterClient.valueOf(this.f218e.unregisterClient, encodeToString);
        return ((AFf1rSDK) this).f234d.valueOf(this.f218e);
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        AFf1zSDK aFf1zSDK;
        AFh1qSDK aFh1qSDK = this.f218e;
        if (aFh1qSDK.f275w != null) {
            aFf1zSDK = aFh1qSDK.f275w;
        } else {
            aFf1zSDK = AFf1zSDK.CACHED_EVENT;
        }
        return (aFf1zSDK == AFf1zSDK.ARS_VALIDATE && this.AFLogger != null && this.AFLogger.getStatusCode() == 424) || super.AFInAppEventParameterName();
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AppsFlyerRequestListener registerClient() {
        return this.f218e.AFInAppEventType;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AFf1kSDK(com.appsflyer.internal.AFh1qSDK r8, com.appsflyer.internal.AFd1mSDK r9) {
        /*
            r7 = this;
            com.appsflyer.internal.AFf1zSDK r0 = r8.f275w
            if (r0 == 0) goto L7
            com.appsflyer.internal.AFf1zSDK r0 = r8.f275w
            goto L9
        L7:
            com.appsflyer.internal.AFf1zSDK r0 = com.appsflyer.internal.AFf1zSDK.CACHED_EVENT
        L9:
            r2 = r0
            r0 = 1
            com.appsflyer.internal.AFf1zSDK[] r3 = new com.appsflyer.internal.AFf1zSDK[r0]
            r0 = 0
            com.appsflyer.internal.AFf1zSDK r1 = com.appsflyer.internal.AFf1zSDK.RC_CDN
            r3[r0] = r1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r8.AFInAppEventParameterName
            r0.append(r1)
            java.lang.String r1 = "-"
            r0.append(r1)
            java.lang.String r1 = values(r8)
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            java.lang.String r6 = r8.AFInAppEventParameterName
            r1 = r7
            r4 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r7.f218e = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1kSDK.<init>(com.appsflyer.internal.AFh1qSDK, com.appsflyer.internal.AFd1mSDK):void");
    }

    private static String values(AFh1qSDK aFh1qSDK) {
        try {
            return new URL(aFh1qSDK.unregisterClient).getHost();
        } catch (MalformedURLException unused) {
            return "";
        }
    }
}
