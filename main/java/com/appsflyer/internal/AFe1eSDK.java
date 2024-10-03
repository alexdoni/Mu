package com.appsflyer.internal;

import android.net.TrafficStats;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class AFe1eSDK<Result> implements Comparable<AFe1eSDK<?>>, Callable<AFe1dSDK> {
    private static final AtomicInteger AFLogger = new AtomicInteger();
    public final AFf1zSDK AFInAppEventParameterName;
    public AFe1dSDK AFInAppEventType;
    public final Set<AFf1zSDK> AFKeystoreWrapper;

    /* renamed from: d */
    private long f192d;

    /* renamed from: e */
    private Throwable f193e;
    private final int registerClient;
    private final String unregisterClient;

    /* renamed from: v */
    private boolean f194v;
    public final Set<AFf1zSDK> valueOf;
    public volatile int values;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean AFInAppEventParameterName();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract long AFInAppEventType();

    protected void AFInAppEventType(Throwable th) {
    }

    protected void AFKeystoreWrapper() {
    }

    protected abstract AFe1dSDK values() throws Exception;

    public AFe1eSDK(AFf1zSDK aFf1zSDK, AFf1zSDK[] aFf1zSDKArr, String str) {
        HashSet hashSet = new HashSet();
        this.AFKeystoreWrapper = hashSet;
        this.valueOf = new HashSet();
        int incrementAndGet = AFLogger.incrementAndGet();
        this.registerClient = incrementAndGet;
        this.f194v = false;
        this.values = 0;
        this.AFInAppEventParameterName = aFf1zSDK;
        Collections.addAll(hashSet, aFf1zSDKArr);
        if (str != null) {
            this.unregisterClient = str;
        } else {
            this.unregisterClient = String.valueOf(incrementAndGet);
        }
    }

    public void valueOf() {
        this.f194v = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public final boolean m84d() {
        return this.f194v;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: AFLogger, reason: merged with bridge method [inline-methods] */
    public final AFe1dSDK call() throws Exception {
        TrafficStats.setThreadStatsTag("AppsFlyer".hashCode());
        this.AFInAppEventType = null;
        this.f193e = null;
        long currentTimeMillis = System.currentTimeMillis();
        this.values++;
        try {
            AFe1dSDK values = values();
            this.AFInAppEventType = values;
            return values;
        } finally {
        }
    }

    /* renamed from: e */
    public final Throwable m85e() {
        return this.f193e;
    }

    @Override // java.lang.Comparable
    /* renamed from: AFInAppEventParameterName, reason: merged with bridge method [inline-methods] */
    public final int compareTo(AFe1eSDK<?> aFe1eSDK) {
        int i = this.AFInAppEventParameterName.afLogForce - aFe1eSDK.AFInAppEventParameterName.afLogForce;
        if (i != 0) {
            return i;
        }
        if (this.unregisterClient.equals(aFe1eSDK.unregisterClient)) {
            return 0;
        }
        return this.registerClient - aFe1eSDK.registerClient;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AFe1eSDK aFe1eSDK = (AFe1eSDK) obj;
        if (this.AFInAppEventParameterName != aFe1eSDK.AFInAppEventParameterName) {
            return false;
        }
        return this.unregisterClient.equals(aFe1eSDK.unregisterClient);
    }

    public final int hashCode() {
        return (this.AFInAppEventParameterName.hashCode() * 31) + this.unregisterClient.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.AFInAppEventParameterName);
        sb.append("-");
        sb.append(this.unregisterClient);
        String obj = sb.toString();
        if (String.valueOf(this.registerClient).equals(this.unregisterClient)) {
            return obj;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(obj);
        sb2.append("-");
        sb2.append(this.registerClient);
        return sb2.toString();
    }
}
