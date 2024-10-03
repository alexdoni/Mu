package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.deeplink.DeepLinkResult;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class AFg1cSDK {
    public final Map<String, Object> AFInAppEventParameterName;
    public final AFd1tSDK AFInAppEventType;
    public final Map<String, Object> AFKeystoreWrapper;
    public final long[] AFLogger;

    /* renamed from: d */
    public long f255d;

    /* renamed from: e */
    public final long[] f256e;

    /* renamed from: i */
    public long f257i;
    public long registerClient;
    public final long[] unregisterClient;
    public long valueOf;
    public final Map<String, Object> values;

    /* renamed from: w */
    public long f258w;

    public AFg1cSDK(AFd1tSDK aFd1tSDK) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.AFInAppEventParameterName = concurrentHashMap;
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        this.AFKeystoreWrapper = concurrentHashMap2;
        ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap();
        this.values = concurrentHashMap3;
        this.valueOf = 0L;
        this.f255d = 0L;
        this.AFLogger = new long[2];
        this.unregisterClient = new long[2];
        this.f256e = new long[2];
        this.registerClient = 0L;
        this.f258w = 0L;
        this.AFInAppEventType = aFd1tSDK;
        concurrentHashMap.putAll(values("first_launch"));
        concurrentHashMap2.putAll(values("ddl"));
        concurrentHashMap3.putAll(values("gcd"));
        this.f257i = aFd1tSDK.AFKeystoreWrapper("prev_session_dur", 0L);
    }

    public final void AFKeystoreWrapper() {
        this.f255d = System.currentTimeMillis();
        if (values()) {
            long j = this.valueOf;
            if (j != 0) {
                this.AFInAppEventParameterName.put("init_to_fg", Long.valueOf(this.f255d - j));
                this.AFInAppEventType.valueOf("first_launch", new JSONObject(this.AFInAppEventParameterName).toString());
                return;
            }
            AFLogger.afInfoLog("Metrics: init ts is missing");
        }
    }

    public final void values(AFh1zSDK aFh1zSDK) {
        if (values()) {
            this.AFInAppEventParameterName.put("start_with", aFh1zSDK.toString());
            this.AFInAppEventType.valueOf("first_launch", new JSONObject(this.AFInAppEventParameterName).toString());
        }
    }

    public final void valueOf(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        this.registerClient = currentTimeMillis;
        if (i == 1) {
            long j = this.f255d;
            if (j != 0) {
                this.AFInAppEventParameterName.put("from_fg", Long.valueOf(currentTimeMillis - j));
                this.AFInAppEventType.valueOf("first_launch", new JSONObject(this.AFInAppEventParameterName).toString());
                return;
            }
            AFLogger.afInfoLog("Metrics: fg ts is missing");
        }
    }

    public final void valueOf(DeepLinkResult deepLinkResult, long j) {
        this.AFKeystoreWrapper.put("status", deepLinkResult.getStatus().toString());
        this.AFKeystoreWrapper.put("timeout_value", Long.valueOf(j));
        this.AFInAppEventType.valueOf("ddl", new JSONObject(this.AFKeystoreWrapper).toString());
    }

    public final void AFKeystoreWrapper(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f258w;
        if (j != 0) {
            this.values.put("net", Long.valueOf(currentTimeMillis - j));
        } else {
            AFLogger.afInfoLog("Metrics: gcdStart ts is missing");
        }
        this.values.put("retries", Integer.valueOf(i));
        this.AFInAppEventType.valueOf("gcd", new JSONObject(this.values).toString());
    }

    private Map<String, Object> values(String str) {
        Map<String, Object> emptyMap = Collections.emptyMap();
        String AFKeystoreWrapper = this.AFInAppEventType.AFKeystoreWrapper(str, (String) null);
        if (AFKeystoreWrapper == null) {
            return emptyMap;
        }
        try {
            return AFa1qSDK.values(new JSONObject(AFKeystoreWrapper));
        } catch (Exception e) {
            AFLogger.afErrorLog("Error while parsing cached json data", e, true);
            return emptyMap;
        }
    }

    public final boolean values() {
        return this.AFInAppEventType.AFInAppEventParameterName("appsFlyerCount", 0) == 0;
    }
}
