package com.appsflyer.internal;

/* loaded from: classes.dex */
public final class AFh1eSDK {
    public final String AFInAppEventParameterName;
    public final int AFInAppEventType;
    public final long AFKeystoreWrapper;
    public final Throwable AFLogger;

    /* renamed from: d */
    public final AFh1bSDK f274d;
    public final String unregisterClient;
    public final String valueOf;
    public final long values;

    public AFh1eSDK(String str, String str2, long j, long j2, int i, AFh1bSDK aFh1bSDK, String str3, Throwable th) {
        this.AFInAppEventParameterName = str;
        this.valueOf = str2;
        this.values = j;
        this.AFKeystoreWrapper = j2;
        this.AFInAppEventType = i;
        this.f274d = aFh1bSDK;
        this.unregisterClient = str3;
        this.AFLogger = th;
    }
}
