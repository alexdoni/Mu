package com.appsflyer.internal;

import android.net.Uri;
import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFe1fSDK;

/* loaded from: classes.dex */
public final class AFf1qSDK extends AFe1eSDK<Boolean> {

    /* renamed from: d */
    private static volatile boolean f232d = false;
    private final AFe1fSDK AFLogger;

    /* renamed from: e */
    private final AFd1mSDK f233e;
    private final AFb1vSDK registerClient;
    private Boolean unregisterClient;

    @Override // com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 30000L;
    }

    public AFf1qSDK(AFd1mSDK aFd1mSDK) {
        super(AFf1zSDK.LOAD_CACHE, new AFf1zSDK[0], "LoadCachedRequests");
        this.registerClient = aFd1mSDK.afRDLog();
        this.AFLogger = aFd1mSDK.afInfoLog();
        this.f233e = aFd1mSDK;
    }

    public static boolean registerClient() {
        return f232d;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() throws Exception {
        for (AFb1qSDK aFb1qSDK : this.registerClient.AFInAppEventType()) {
            AFLogger aFLogger = AFLogger.INSTANCE;
            AFg1gSDK aFg1gSDK = AFg1gSDK.CACHE;
            StringBuilder sb = new StringBuilder("resending request: ");
            sb.append(aFb1qSDK.AFInAppEventType);
            aFLogger.m101i(aFg1gSDK, sb.toString());
            try {
                AFh1qSDK aFh1qSDK = new AFh1qSDK(values(aFb1qSDK), aFb1qSDK.AFInAppEventParameterName(), aFb1qSDK.AFInAppEventParameterName, aFb1qSDK.values);
                AFe1fSDK aFe1fSDK = this.AFLogger;
                aFe1fSDK.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(new AFf1kSDK(aFh1qSDK, this.f233e)));
            } catch (Exception e) {
                AFLogger.INSTANCE.m97e(AFg1gSDK.QUEUE, "Failed to resend cached request", e);
            }
        }
        this.unregisterClient = Boolean.TRUE;
        f232d = true;
        return AFe1dSDK.SUCCESS;
    }

    private static String values(AFb1qSDK aFb1qSDK) {
        long currentTimeMillis = System.currentTimeMillis();
        long parseLong = Long.parseLong(aFb1qSDK.AFInAppEventParameterName, 10);
        String str = aFb1qSDK.AFInAppEventType;
        try {
            return Uri.parse(str).buildUpon().appendQueryParameter("isCachedRequest", "true").appendQueryParameter("timeincache", String.valueOf((currentTimeMillis - parseLong) / 1000)).toString();
        } catch (Exception e) {
            AFLogger.afErrorLogForExcManagerOnly("Couldn't parse the uri", e);
            return str;
        }
    }
}
