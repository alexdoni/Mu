package com.appsflyer.internal;

import android.content.Context;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.view.ViewConfiguration;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class AFg1xSDK {
    public final AFd1kSDK AFInAppEventType;
    Map<String, Object> AFKeystoreWrapper;
    public volatile String registerClient;
    public volatile String unregisterClient;
    public final AFg1zSDK valueOf;
    public long values;
    public boolean AFInAppEventParameterName = false;
    public volatile boolean AFLogger = false;

    public AFg1xSDK(AFd1kSDK aFd1kSDK, AFg1zSDK aFg1zSDK) {
        this.AFInAppEventType = aFd1kSDK;
        this.valueOf = aFg1zSDK;
    }

    public final boolean AFKeystoreWrapper() {
        return this.AFLogger;
    }

    public final String values(AFd1tSDK aFd1tSDK) {
        String str;
        boolean z = AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI, false);
        String AFKeystoreWrapper = aFd1tSDK.AFKeystoreWrapper("imeiCached", (String) null);
        if (!z || !AFc1rSDK.AFInAppEventType(this.unregisterClient)) {
            if (this.unregisterClient != null) {
                str = this.unregisterClient;
            }
            str = null;
        } else {
            Context context = this.AFInAppEventType.valueOf;
            if (context != null && AFKeystoreWrapper(context)) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    str = (String) telephonyManager.getClass().getMethod("getDeviceId", new Class[0]).invoke(telephonyManager, new Object[0]);
                } catch (InvocationTargetException e) {
                    if (AFKeystoreWrapper != null) {
                        AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(AFKeystoreWrapper)));
                    } else {
                        AFKeystoreWrapper = null;
                    }
                    StringBuilder sb = new StringBuilder("WARNING: Can't collect IMEI because of missing permissions: ");
                    sb.append(e.getMessage());
                    AFLogger.afErrorLog(sb.toString(), e);
                } catch (Exception e2) {
                    if (AFKeystoreWrapper != null) {
                        AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(AFKeystoreWrapper)));
                    } else {
                        AFKeystoreWrapper = null;
                    }
                    StringBuilder sb2 = new StringBuilder("WARNING: Can't collect IMEI: other reason: ");
                    sb2.append(e2.getMessage());
                    AFLogger.afErrorLog(sb2.toString(), e2);
                }
                if (str == null) {
                    if (AFKeystoreWrapper != null) {
                        AFLogger.afDebugLog("use cached IMEI: ".concat(String.valueOf(AFKeystoreWrapper)));
                    } else {
                        AFKeystoreWrapper = null;
                    }
                    str = AFKeystoreWrapper;
                }
            }
            str = null;
        }
        if (!AFc1rSDK.AFInAppEventType(str)) {
            aFd1tSDK.valueOf("imeiCached", str);
            return str;
        }
        AFLogger.afInfoLog("IMEI was not collected.");
        return null;
    }

    public final Map<String, Object> AFInAppEventType(Map<String, Object> map) {
        try {
            try {
                Object[] objArr = {map, this.AFInAppEventType.valueOf};
                Object obj = AFc1iSDK.afErrorLog.get(544149616);
                if (obj == null) {
                    obj = ((Class) AFc1iSDK.AFInAppEventParameterName((ViewConfiguration.getPressedStateDuration() >> 16) + 36, (char) (55716 - (ViewConfiguration.getTapTimeout() >> 16)), Color.green(0) + 124)).getDeclaredConstructor(Map.class, Context.class);
                    AFc1iSDK.afErrorLog.put(544149616, obj);
                }
                return (Map) ((Constructor) obj).newInstance(objArr);
            } catch (Throwable th) {
                Throwable cause = th.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw th;
            }
        } catch (Exception e) {
            AFLogger.afErrorLogForExcManagerOnly("AFCksmV3: reflection init failed", e);
            return new HashMap();
        }
    }

    public final Map<String, Object> values() {
        HashMap hashMap = new HashMap();
        if (AFInAppEventType()) {
            hashMap.put("lvl", this.AFKeystoreWrapper);
        } else if (this.AFInAppEventParameterName) {
            this.AFKeystoreWrapper = new HashMap();
            AFInAppEventParameterName();
            this.AFKeystoreWrapper.put("error", "pending LVL response");
            hashMap.put("lvl", this.AFKeystoreWrapper);
        }
        return hashMap;
    }

    private boolean AFInAppEventType() {
        Map<String, Object> map = this.AFKeystoreWrapper;
        return (map == null || map.isEmpty()) ? false : true;
    }

    public final boolean valueOf() {
        return this.AFInAppEventParameterName && !AFInAppEventType();
    }

    final void AFInAppEventParameterName() {
        this.AFKeystoreWrapper.put("ttr", Long.valueOf(System.currentTimeMillis() - this.values));
        this.AFKeystoreWrapper.put("lvl_timestamp", Long.valueOf(this.values));
    }

    private static boolean AFKeystoreWrapper(Context context) {
        if (!(AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false))) {
            AFb1tSDK.valueOf();
            if (AFb1tSDK.valueOf(context)) {
                return false;
            }
        }
        return true;
    }
}
