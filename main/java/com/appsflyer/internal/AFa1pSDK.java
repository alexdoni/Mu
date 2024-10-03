package com.appsflyer.internal;

import android.net.Uri;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AFa1pSDK {
    public String AFInAppEventParameterName;
    public AppsFlyerRequestListener AFInAppEventType;
    public Map<String, Object> AFKeystoreWrapper;
    public String AFLogger;

    /* renamed from: d */
    public String f160d;

    /* renamed from: e */
    public String f161e;
    private final boolean force;
    public int registerClient;
    public String unregisterClient;

    /* renamed from: v */
    private byte[] f162v;
    protected final Map<String, Object> valueOf;
    public String values;

    public abstract AFf1zSDK AFKeystoreWrapper();

    /* renamed from: d */
    public boolean mo60d() {
        return true;
    }

    /* renamed from: e */
    public boolean mo61e() {
        return true;
    }

    public boolean registerClient() {
        return true;
    }

    public boolean unregisterClient() {
        return false;
    }

    public AFa1pSDK() {
        this(null, null, null);
    }

    public AFa1pSDK(String str, String str2, Boolean bool) {
        this.valueOf = new HashMap();
        this.AFLogger = str;
        this.unregisterClient = str2;
        this.force = bool != null ? bool.booleanValue() : true;
    }

    public AFa1pSDK AFInAppEventParameterName(String str) {
        this.unregisterClient = str;
        return this;
    }

    public final boolean values() {
        return this.AFLogger == null && this.AFInAppEventParameterName == null;
    }

    public final AFa1pSDK AFInAppEventParameterName(Map<String, ?> map) {
        synchronized (map) {
            this.valueOf.putAll(map);
        }
        return this;
    }

    public final AFa1pSDK AFInAppEventType(String str, Object obj) {
        synchronized (this.valueOf) {
            this.valueOf.put(str, obj);
        }
        return this;
    }

    public final Map<String, Object> valueOf() {
        return this.valueOf;
    }

    public final AFa1pSDK AFInAppEventType(int i) {
        this.registerClient = i;
        synchronized (this.valueOf) {
            if (this.valueOf.containsKey("counter")) {
                this.valueOf.put("counter", Integer.toString(i));
            }
            if (this.valueOf.containsKey("launch_counter")) {
                this.valueOf.put("launch_counter", Integer.toString(i));
            }
        }
        return this;
    }

    public final AFa1pSDK values(byte[] bArr) {
        this.f162v = bArr;
        return this;
    }

    public final byte[] AFInAppEventType() {
        return this.f162v;
    }

    public final boolean AFInAppEventParameterName() {
        return this.force;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String AFKeystoreWrapper(String str) {
        String AFLogger = AFb1tSDK.valueOf().values().AFInAppEventType().AFLogger();
        return AFLogger != null ? Uri.parse(str).buildUpon().appendQueryParameter(AppsFlyerProperties.CHANNEL, AFLogger).build().toString() : str;
    }

    public static boolean values(double d) {
        if (d < 0.0d || d >= 1.0d) {
            return false;
        }
        if (d == 0.0d) {
            return true;
        }
        int i = (int) (1.0d / d);
        int i2 = i + 1;
        if (i2 > 0) {
            return ((int) ((Math.random() * ((double) (i2 - 1))) + 1.0d)) != i;
        }
        throw new IllegalArgumentException("Unsupported max value");
    }
}
