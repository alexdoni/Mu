package com.appsflyer.internal;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class AFe1oSDK {
    final String AFInAppEventParameterName;
    public boolean AFInAppEventType;
    public boolean AFKeystoreWrapper;
    private boolean AFLogger;

    /* renamed from: d */
    private final boolean f199d;

    /* renamed from: e */
    private final byte[] f200e;
    private final boolean registerClient;
    public int unregisterClient;
    public final String valueOf;
    final Map<String, String> values;

    public AFe1oSDK(String str, byte[] bArr, String str2, Map<String, String> map, boolean z) {
        this(str, bArr, str2, map, z, (byte) 0);
    }

    private AFe1oSDK(String str, byte[] bArr, String str2, Map<String, String> map, boolean z, byte b) {
        this.AFLogger = true;
        this.AFKeystoreWrapper = false;
        this.AFInAppEventType = true;
        this.unregisterClient = -1;
        this.valueOf = str;
        this.f200e = bArr;
        this.AFInAppEventParameterName = str2;
        this.values = map;
        this.f199d = z;
        this.registerClient = true;
    }

    public AFe1oSDK(String str, String str2) {
        this(str, null, str2, new HashMap(), false);
    }

    public final byte[] values() {
        return this.f200e;
    }

    public final boolean AFInAppEventType() {
        return this.f199d;
    }

    public final boolean valueOf() {
        return this.AFLogger;
    }

    public final boolean AFInAppEventParameterName() {
        return this.AFKeystoreWrapper;
    }

    public final boolean AFKeystoreWrapper() {
        return this.registerClient;
    }

    public final boolean unregisterClient() {
        return this.AFInAppEventType;
    }
}
