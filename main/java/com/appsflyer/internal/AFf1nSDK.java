package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.appsflyer.internal.AFi1jSDK;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFf1nSDK extends AFf1rSDK<AFc1qSDK> {
    private int afDebugLog;
    private final CountDownLatch afErrorLog;
    private final AFd1qSDK afInfoLog;
    private final List<AFi1jSDK> afRDLog;
    private int afVerboseLog;
    private int afWarnLog;

    /* renamed from: e */
    private final AFc1kSDK f226e;
    private final AFd1sSDK force;

    /* renamed from: i */
    private final AFi1iSDK f227i;

    /* renamed from: v */
    private final AFg1cSDK f228v;

    /* renamed from: w */
    private final AFc1uSDK f229w;

    /* loaded from: classes.dex */
    public /* synthetic */ class AFa1ySDK {
        public static final /* synthetic */ int[] AFKeystoreWrapper;
        public static final /* synthetic */ int[] values;

        static {
            int[] iArr = new int[AFe1dSDK.values().length];
            iArr[AFe1dSDK.SUCCESS.ordinal()] = 1;
            iArr[AFe1dSDK.FAILURE.ordinal()] = 2;
            values = iArr;
            int[] iArr2 = new int[AFi1jSDK.AFa1tSDK.values().length];
            iArr2[AFi1jSDK.AFa1tSDK.FINISHED.ordinal()] = 1;
            iArr2[AFi1jSDK.AFa1tSDK.STARTED.ordinal()] = 2;
            AFKeystoreWrapper = iArr2;
        }
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    public final /* bridge */ /* synthetic */ AppsFlyerRequestListener registerClient() {
        return null;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    /* renamed from: v */
    protected final boolean mo91v() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFf1nSDK(AFc1kSDK aFc1kSDK, AFd1mSDK aFd1mSDK) {
        super(AFf1zSDK.DLSDK, new AFf1zSDK[]{AFf1zSDK.RC_CDN, AFf1zSDK.FETCH_ADVERTISING_ID}, aFd1mSDK, "DdlSdk");
        Intrinsics.checkNotNullParameter(aFc1kSDK, "");
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        this.f226e = aFc1kSDK;
        this.afErrorLog = new CountDownLatch(1);
        this.afRDLog = new ArrayList();
        AFd1sSDK AFInAppEventType = aFd1mSDK.AFInAppEventType();
        Intrinsics.checkNotNullExpressionValue(AFInAppEventType, "");
        this.force = AFInAppEventType;
        AFd1qSDK afErrorLogForExcManagerOnly = aFd1mSDK.afErrorLogForExcManagerOnly();
        Intrinsics.checkNotNullExpressionValue(afErrorLogForExcManagerOnly, "");
        this.afInfoLog = afErrorLogForExcManagerOnly;
        AFc1uSDK afWarnLog = aFd1mSDK.afWarnLog();
        Intrinsics.checkNotNullExpressionValue(afWarnLog, "");
        this.f229w = afWarnLog;
        AFg1cSDK AFLogger = aFd1mSDK.AFLogger();
        Intrinsics.checkNotNullExpressionValue(AFLogger, "");
        this.f228v = AFLogger;
        AFi1iSDK mo79v = aFd1mSDK.mo79v();
        Intrinsics.checkNotNullExpressionValue(mo79v, "");
        this.f227i = mo79v;
        AFi1jSDK[] AFKeystoreWrapper = mo79v.AFKeystoreWrapper();
        Intrinsics.checkNotNullExpressionValue(AFKeystoreWrapper, "");
        ArrayList arrayList = new ArrayList();
        for (AFi1jSDK aFi1jSDK : AFKeystoreWrapper) {
            AFi1jSDK aFi1jSDK2 = aFi1jSDK;
            if ((aFi1jSDK2 == null || aFi1jSDK2.f285d == AFi1jSDK.AFa1tSDK.NOT_STARTED) ? false : true) {
                arrayList.add(aFi1jSDK);
            }
        }
        ArrayList<AFi1jSDK> arrayList2 = arrayList;
        this.afWarnLog = arrayList2.size();
        for (final AFi1jSDK aFi1jSDK3 : arrayList2) {
            AFi1jSDK.AFa1tSDK aFa1tSDK = aFi1jSDK3.f285d;
            int i = aFa1tSDK == null ? -1 : AFa1ySDK.AFKeystoreWrapper[aFa1tSDK.ordinal()];
            if (i == 1) {
                AFLogger aFLogger = AFLogger.INSTANCE;
                AFg1gSDK aFg1gSDK = AFg1gSDK.DDL;
                StringBuilder sb = new StringBuilder();
                sb.append(aFi1jSDK3.valueOf.get("source"));
                sb.append(" referrer collected earlier");
                AFg1hSDK.d$default(aFLogger, aFg1gSDK, sb.toString(), false, 4, null);
                Intrinsics.checkNotNullExpressionValue(aFi1jSDK3, "");
                values(aFi1jSDK3);
            } else if (i == 2) {
                aFi1jSDK3.addObserver(new Observer() { // from class: com.appsflyer.internal.AFf1nSDK$$ExternalSyntheticLambda0
                    @Override // java.util.Observer
                    public final void update(Observable observable, Object obj) {
                        AFf1nSDK.values(AFi1jSDK.this, this, observable, obj);
                    }
                });
            }
        }
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() {
        AFe1dSDK aFe1dSDK = AFe1dSDK.FAILURE;
        try {
            AFe1dSDK values = super.values();
            Intrinsics.checkNotNullExpressionValue(values, "");
            try {
                AFg1cSDK aFg1cSDK = this.f228v;
                int i = this.afVerboseLog;
                if (i <= 0 || i > 2) {
                    AFLogger.afErrorLogForExcManagerOnly("Unexpected ddl requestCount - end", new IllegalStateException("Metrics: Unexpected ddl requestCount = ".concat(String.valueOf(i))));
                } else {
                    int i2 = i - 1;
                    aFg1cSDK.unregisterClient[i2] = System.currentTimeMillis();
                    if (aFg1cSDK.AFLogger[i2] != 0) {
                        aFg1cSDK.f256e[i2] = aFg1cSDK.unregisterClient[i2] - aFg1cSDK.AFLogger[i2];
                        aFg1cSDK.AFKeystoreWrapper.put("net", aFg1cSDK.f256e);
                        aFg1cSDK.AFInAppEventType.valueOf("ddl", new JSONObject(aFg1cSDK.AFKeystoreWrapper).toString());
                    } else {
                        StringBuilder sb = new StringBuilder("Metrics: ddlStart[");
                        sb.append(i2);
                        sb.append("] ts is missing");
                        AFLogger.afInfoLog(sb.toString());
                    }
                }
                int i3 = AFa1ySDK.values[values.ordinal()];
                if (i3 != 1) {
                    if (i3 != 2) {
                        return values;
                    }
                    AFLogger aFLogger = AFLogger.INSTANCE;
                    AFg1gSDK aFg1gSDK = AFg1gSDK.DDL;
                    StringBuilder sb2 = new StringBuilder("Error occurred. Server response code = ");
                    ResponseNetwork responseNetwork = this.AFLogger;
                    sb2.append(responseNetwork != null ? Integer.valueOf(responseNetwork.getStatusCode()) : null);
                    AFg1hSDK.d$default(aFLogger, aFg1gSDK, sb2.toString(), false, 4, null);
                    DeepLinkResult deepLinkResult = new DeepLinkResult(null, DeepLinkResult.Error.HTTP_STATUS_CODE);
                    this.f228v.valueOf(deepLinkResult, this.f229w.registerClient);
                    this.f229w.valueOf(deepLinkResult);
                    return values;
                }
                ResponseNetwork responseNetwork2 = this.AFLogger;
                Intrinsics.checkNotNull(responseNetwork2);
                Object body = responseNetwork2.getBody();
                Intrinsics.checkNotNullExpressionValue(body, "");
                AFc1qSDK aFc1qSDK = (AFc1qSDK) body;
                if (aFc1qSDK.values == null) {
                    if (this.afVerboseLog <= 1 && aFc1qSDK.getAFKeystoreWrapper() && m93w()) {
                        AFg1hSDK.d$default(AFLogger.INSTANCE, AFg1gSDK.DDL, "Waiting for referrers...", false, 4, null);
                        this.afErrorLog.await();
                        AFg1cSDK aFg1cSDK2 = this.f228v;
                        long currentTimeMillis = System.currentTimeMillis();
                        if (aFg1cSDK2.unregisterClient[0] != 0) {
                            aFg1cSDK2.AFKeystoreWrapper.put("rfr_wait", Long.valueOf(currentTimeMillis - aFg1cSDK2.unregisterClient[0]));
                            aFg1cSDK2.AFInAppEventType.valueOf("ddl", new JSONObject(aFg1cSDK2.AFKeystoreWrapper).toString());
                        } else {
                            AFLogger.afInfoLog("Metrics: ddlEnd[0] ts is missing");
                        }
                        if (this.afDebugLog != this.afWarnLog) {
                            return values();
                        }
                        DeepLinkResult deepLinkResult2 = new DeepLinkResult(null, null);
                        this.f228v.valueOf(deepLinkResult2, this.f229w.registerClient);
                        this.f229w.valueOf(deepLinkResult2);
                        return AFe1dSDK.SUCCESS;
                    }
                    DeepLinkResult deepLinkResult3 = new DeepLinkResult(null, null);
                    this.f228v.valueOf(deepLinkResult3, this.f229w.registerClient);
                    this.f229w.valueOf(deepLinkResult3);
                    return values;
                }
                DeepLinkResult deepLinkResult4 = new DeepLinkResult(aFc1qSDK.values, null);
                this.f228v.valueOf(deepLinkResult4, this.f229w.registerClient);
                this.f229w.valueOf(deepLinkResult4);
                return values;
            } catch (Exception e) {
                e = e;
                aFe1dSDK = values;
                Throwable cause = e.getCause();
                if (cause instanceof InterruptedException ? true : cause instanceof InterruptedIOException) {
                    AFLogger.afErrorLogForExcManagerOnly("[DDL] Timeout", new TimeoutException());
                    AFLogger aFLogger2 = AFLogger.INSTANCE;
                    AFg1gSDK aFg1gSDK2 = AFg1gSDK.DDL;
                    StringBuilder sb3 = new StringBuilder("Timeout, didn't manage to find deferred deeplink after ");
                    sb3.append(this.afVerboseLog);
                    sb3.append(" attempt(s) within ");
                    sb3.append(this.f229w.registerClient);
                    sb3.append(" milliseconds");
                    AFg1hSDK.d$default(aFLogger2, aFg1gSDK2, sb3.toString(), false, 4, null);
                    DeepLinkResult deepLinkResult5 = new DeepLinkResult(null, DeepLinkResult.Error.TIMEOUT);
                    this.f228v.valueOf(deepLinkResult5, this.f229w.registerClient);
                    this.f229w.valueOf(deepLinkResult5);
                    return AFe1dSDK.TIMEOUT;
                }
                if (cause instanceof IOException) {
                    AFg1hSDK.d$default(AFLogger.INSTANCE, AFg1gSDK.DDL, "Http Exception: the request was not sent to the server", false, 4, null);
                    DeepLinkResult deepLinkResult6 = new DeepLinkResult(null, DeepLinkResult.Error.NETWORK);
                    this.f228v.valueOf(deepLinkResult6, this.f229w.registerClient);
                    this.f229w.valueOf(deepLinkResult6);
                } else {
                    AFg1hSDK.d$default(AFLogger.INSTANCE, AFg1gSDK.DDL, "Unexpected Exception: ".concat(String.valueOf(e)), false, 4, null);
                    DeepLinkResult deepLinkResult7 = new DeepLinkResult(null, DeepLinkResult.Error.UNEXPECTED);
                    this.f228v.valueOf(deepLinkResult7, this.f229w.registerClient);
                    this.f229w.valueOf(deepLinkResult7);
                }
                return aFe1dSDK;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private final void values(AFi1jSDK aFi1jSDK) {
        if (AFInAppEventParameterName(aFi1jSDK)) {
            this.afRDLog.add(aFi1jSDK);
            this.afErrorLog.countDown();
            AFLogger aFLogger = AFLogger.INSTANCE;
            AFg1gSDK aFg1gSDK = AFg1gSDK.DDL;
            StringBuilder sb = new StringBuilder("Added non-organic ");
            sb.append(aFi1jSDK.getClass().getSimpleName());
            AFg1hSDK.d$default(aFLogger, aFg1gSDK, sb.toString(), false, 4, null);
            return;
        }
        int i = this.afDebugLog + 1;
        this.afDebugLog = i;
        if (i == this.afWarnLog) {
            this.afErrorLog.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void values(AFi1jSDK aFi1jSDK, AFf1nSDK aFf1nSDK, Observable observable, Object obj) {
        Intrinsics.checkNotNullParameter(aFf1nSDK, "");
        AFLogger aFLogger = AFLogger.INSTANCE;
        AFg1gSDK aFg1gSDK = AFg1gSDK.DDL;
        StringBuilder sb = new StringBuilder();
        sb.append(aFi1jSDK.valueOf.get("source"));
        sb.append(" referrer collected via observer");
        AFg1hSDK.d$default(aFLogger, aFg1gSDK, sb.toString(), false, 4, null);
        if (observable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.appsflyer.internal.referrer.Referrer");
        }
        aFf1nSDK.values((AFi1jSDK) observable);
    }

    /* renamed from: w */
    private final boolean m93w() {
        Object obj = this.f226e.valueOf().get("referrers");
        List list = obj instanceof List ? (List) obj : null;
        return (list != null ? list.size() : 0) < this.afWarnLog && !this.f226e.valueOf().containsKey("referrers");
    }

    private static boolean AFInAppEventParameterName(AFi1jSDK aFi1jSDK) {
        Object obj = aFi1jSDK.valueOf.get("click_ts");
        Long l = obj instanceof Long ? (Long) obj : null;
        if (l != null) {
            if (System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(l.longValue()) < TimeUnit.DAYS.toMillis(1L)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return this.f229w.registerClient;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0169 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0117 A[SYNTHETIC] */
    @Override // com.appsflyer.internal.AFf1rSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final com.appsflyer.internal.AFe1uSDK<com.appsflyer.internal.AFc1qSDK> valueOf(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 648
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFf1nSDK.valueOf(java.lang.String):com.appsflyer.internal.AFe1uSDK");
    }

    private static Map<String, String> AFKeystoreWrapper(AFa1bSDK aFa1bSDK) {
        String str;
        if (aFa1bSDK == null || (str = aFa1bSDK.AFInAppEventParameterName) == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(str, "");
        Boolean bool = aFa1bSDK.AFKeystoreWrapper;
        if (bool == null || !bool.booleanValue()) {
            return MapsKt.mapOf(TuplesKt.m1402to(ShareConstants.MEDIA_TYPE, "unhashed"), TuplesKt.m1402to("value", str));
        }
        return null;
    }
}
