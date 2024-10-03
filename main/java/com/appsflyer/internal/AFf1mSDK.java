package com.appsflyer.internal;

import android.content.Context;
import android.net.Uri;
import com.appsflyer.AFLogger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class AFf1mSDK extends AFe1eSDK<Map<String, Object>> {
    private static final int registerClient = (int) TimeUnit.SECONDS.toMillis(2);
    private final Uri AFLogger;

    /* renamed from: d */
    private Map<String, Object> f223d;

    /* renamed from: e */
    private final Context f224e;

    /* renamed from: i */
    private final List<String> f225i;
    private final AFc1pSDK unregisterClient;

    @Override // com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 60000L;
    }

    public AFf1mSDK(Context context, AFc1pSDK aFc1pSDK, Uri uri, List<String> list) {
        super(AFf1zSDK.RESOLVE_ESP, new AFf1zSDK[]{AFf1zSDK.RC_CDN}, "ResolveEsp");
        this.f224e = context;
        this.unregisterClient = aFc1pSDK;
        this.AFLogger = uri;
        this.f225i = list;
    }

    @Override // com.appsflyer.internal.AFe1eSDK
    public final AFe1dSDK values() throws Exception {
        Integer num = null;
        if (!AFKeystoreWrapper(this.AFLogger.toString())) {
            AFb1tSDK.valueOf().values(this.f224e, this.unregisterClient, this.AFLogger, null);
            return AFe1dSDK.SUCCESS;
        }
        long currentTimeMillis = System.currentTimeMillis();
        String obj = this.AFLogger.toString();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        String str = null;
        while (i < 5) {
            Map<String, Object> values = values(Uri.parse(obj));
            String str2 = (String) values.get("res");
            Integer num2 = (Integer) values.get("status");
            String str3 = (String) values.get("error");
            if (str2 == null || !AFKeystoreWrapper(str2)) {
                str = str3;
                obj = str2;
                num = num2;
                break;
            }
            if (i < 4) {
                arrayList.add(str2);
            }
            i++;
            str = str3;
            obj = str2;
            num = num2;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("res", obj != null ? obj : "");
        hashMap.put("status", Integer.valueOf(num != null ? num.intValue() : -1));
        if (str != null) {
            hashMap.put("error", str);
        }
        if (!arrayList.isEmpty()) {
            hashMap.put("redirects", arrayList);
        }
        hashMap.put("latency", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        synchronized (this.unregisterClient) {
            this.unregisterClient.AFKeystoreWrapper("af_deeplink_r", hashMap);
            this.unregisterClient.AFKeystoreWrapper("af_deeplink", this.AFLogger.toString());
        }
        AFb1tSDK.valueOf().values(this.f224e, this.unregisterClient, obj != null ? Uri.parse(obj) : this.AFLogger, this.AFLogger);
        this.f223d = hashMap;
        return AFe1dSDK.SUCCESS;
    }

    private static Map<String, Object> values(Uri uri) {
        HashMap hashMap = new HashMap();
        try {
            StringBuilder sb = new StringBuilder("ESP deeplink resolving is started: ");
            sb.append(uri.toString());
            AFLogger.afDebugLog(sb.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(uri.toString()).openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            int i = registerClient;
            httpURLConnection.setReadTimeout(i);
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setRequestProperty("User-agent", "Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 5 Build/M4B30Z)");
            httpURLConnection.setRequestProperty("af-esp", "6.13.1");
            int responseCode = httpURLConnection.getResponseCode();
            hashMap.put("status", Integer.valueOf(responseCode));
            if (300 <= responseCode && responseCode <= 305) {
                hashMap.put("res", httpURLConnection.getHeaderField("Location"));
            }
            httpURLConnection.disconnect();
            AFLogger.afDebugLog("ESP deeplink resolving is finished");
        } catch (Throwable th) {
            hashMap.put("error", th.getLocalizedMessage());
            AFLogger.afErrorLog(th.getMessage(), th);
        }
        return hashMap;
    }

    private boolean AFKeystoreWrapper(String str) {
        if (str.contains("af_tranid=")) {
            return false;
        }
        StringBuilder sb = new StringBuilder("Validate if link ");
        sb.append(str);
        sb.append(" belongs to ESP domains: ");
        sb.append(this.f225i);
        AFLogger.afRDLog(sb.toString());
        try {
            return this.f225i.contains(new URL(str).getHost());
        } catch (MalformedURLException e) {
            AFLogger.afErrorLogForExcManagerOnly("MalformedURLException ESP link", e);
            return false;
        }
    }
}
