package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFg1iSDK extends AFf1rSDK<Map<String, Object>> {

    /* renamed from: e */
    private static final List<String> f264e = Arrays.asList("googleplay", "playstore", "googleplaystore");
    private String afErrorLog;
    private Map<String, Object> afInfoLog;
    private final AFd1sSDK force;

    /* renamed from: i */
    private final AFd1tSDK f265i;

    /* renamed from: v */
    private final AFg1cSDK f266v;

    /* renamed from: w */
    private final AFe1wSDK f267w;

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    public final AppsFlyerRequestListener registerClient() {
        return null;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    public final boolean unregisterClient() {
        return false;
    }

    public AFg1iSDK(AFd1mSDK aFd1mSDK) {
        super(AFf1zSDK.GCDSDK, new AFf1zSDK[]{AFf1zSDK.RC_CDN}, aFd1mSDK, "GCD-FETCH");
        this.f267w = aFd1mSDK.AFKeystoreWrapper();
        this.f265i = aFd1mSDK.values();
        this.f266v = aFd1mSDK.AFLogger();
        this.force = aFd1mSDK.AFInAppEventType();
        this.valueOf.add(AFf1zSDK.CONVERSION);
        this.valueOf.add(AFf1zSDK.LAUNCH);
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        super.AFKeystoreWrapper();
        Map<String, Object> map = this.afInfoLog;
        String str = this.afErrorLog;
        if (map != null) {
            AFg1jSDK.AFInAppEventType(map);
        } else if (str != null && !str.isEmpty()) {
            AFg1jSDK.AFInAppEventParameterName(str);
        } else {
            AFg1jSDK.AFInAppEventParameterName("Unknown error");
        }
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    public final AFe1uSDK<Map<String, Object>> valueOf(String str) {
        String str2;
        String AFKeystoreWrapper = AFb1tSDK.AFKeystoreWrapper(this.f265i, this.force.AFLogger());
        if (AFKeystoreWrapper != null && !AFKeystoreWrapper.trim().isEmpty()) {
            if (!f264e.contains(AFKeystoreWrapper.toLowerCase(Locale.getDefault()))) {
                str2 = "-".concat(String.valueOf(AFKeystoreWrapper));
                AFe1uSDK<Map<String, Object>> AFInAppEventType = this.f267w.AFInAppEventType(str2, str);
                StringBuilder sb = new StringBuilder("[GCD-B01] URL: ");
                sb.append(AFInAppEventType.AFKeystoreWrapper.valueOf);
                AFb1lSDK.values(sb.toString());
                return AFInAppEventType;
            }
            AFLogger.afWarnLog(String.format("[GCD] AF detected using redundant Google-Play channel for attribution - %s. Using without channel postfix.", AFKeystoreWrapper));
        }
        str2 = "";
        AFe1uSDK<Map<String, Object>> AFInAppEventType2 = this.f267w.AFInAppEventType(str2, str);
        StringBuilder sb2 = new StringBuilder("[GCD-B01] URL: ");
        sb2.append(AFInAppEventType2.AFKeystoreWrapper.valueOf);
        AFb1lSDK.values(sb2.toString());
        return AFInAppEventType2;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007d A[Catch: all -> 0x0105, Exception -> 0x0107, AFe1aSDK -> 0x0136, TryCatch #3 {AFe1aSDK -> 0x0136, Exception -> 0x0107, blocks: (B:11:0x0025, B:17:0x002f, B:23:0x003f, B:30:0x0052, B:37:0x0069, B:39:0x007d, B:41:0x0097, B:43:0x009d, B:44:0x00a8, B:46:0x00ae, B:48:0x00b4, B:49:0x00ca, B:50:0x00db, B:52:0x00fa, B:53:0x00ff), top: B:10:0x0025, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ae A[Catch: all -> 0x0105, Exception -> 0x0107, AFe1aSDK -> 0x0136, TryCatch #3 {AFe1aSDK -> 0x0136, Exception -> 0x0107, blocks: (B:11:0x0025, B:17:0x002f, B:23:0x003f, B:30:0x0052, B:37:0x0069, B:39:0x007d, B:41:0x0097, B:43:0x009d, B:44:0x00a8, B:46:0x00ae, B:48:0x00b4, B:49:0x00ca, B:50:0x00db, B:52:0x00fa, B:53:0x00ff), top: B:10:0x0025, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fa A[Catch: all -> 0x0105, Exception -> 0x0107, AFe1aSDK -> 0x0136, TryCatch #3 {AFe1aSDK -> 0x0136, Exception -> 0x0107, blocks: (B:11:0x0025, B:17:0x002f, B:23:0x003f, B:30:0x0052, B:37:0x0069, B:39:0x007d, B:41:0x0097, B:43:0x009d, B:44:0x00a8, B:46:0x00ae, B:48:0x00b4, B:49:0x00ca, B:50:0x00db, B:52:0x00fa, B:53:0x00ff), top: B:10:0x0025, outer: #1 }] */
    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.appsflyer.internal.AFe1dSDK values() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFg1iSDK.values():com.appsflyer.internal.AFe1dSDK");
    }
}
