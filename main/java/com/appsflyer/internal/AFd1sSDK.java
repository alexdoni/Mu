package com.appsflyer.internal;

import android.content.pm.PackageItemInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import java.util.UUID;

/* loaded from: classes.dex */
public final class AFd1sSDK {
    private static String AFInAppEventType = "286";
    public final AFd1kSDK AFInAppEventParameterName;
    public final AFd1tSDK AFKeystoreWrapper;
    public final AFd1qSDK valueOf;
    private Bundle values = null;

    public static String AFInAppEventParameterName() {
        return "6.13.1";
    }

    public AFd1sSDK(AFd1kSDK aFd1kSDK, AFd1tSDK aFd1tSDK, AFd1qSDK aFd1qSDK) {
        this.AFInAppEventParameterName = aFd1kSDK;
        this.AFKeystoreWrapper = aFd1tSDK;
        this.valueOf = aFd1qSDK;
    }

    public static String AFKeystoreWrapper() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String values() {
        return UUID.randomUUID().toString();
    }

    public static String AFInAppEventType() {
        return AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
    }

    public final boolean unregisterClient() {
        return !this.valueOf.AFInAppEventType();
    }

    public final String AFInAppEventParameterName(String str) {
        Object obj;
        try {
            if (this.values == null) {
                this.values = ((PackageItemInfo) this.AFInAppEventParameterName.valueOf.getPackageManager().getApplicationInfo(this.AFInAppEventParameterName.valueOf.getPackageName(), 128)).metaData;
            }
            Bundle bundle = this.values;
            if (bundle == null || (obj = bundle.get(str)) == null) {
                return null;
            }
            return obj.toString();
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Could not load manifest metadata!");
            sb.append(th.getMessage());
            AFLogger.afErrorLog(sb.toString(), th);
            return null;
        }
    }

    public final boolean values(String str) {
        String AFInAppEventParameterName = AFInAppEventParameterName(str);
        if (AFInAppEventParameterName != null) {
            return Boolean.parseBoolean(AFInAppEventParameterName);
        }
        return false;
    }

    /* renamed from: e */
    public static String m83e() {
        StringBuilder sb = new StringBuilder("version: 6.13.1 (build ");
        sb.append(AFInAppEventType);
        sb.append(")");
        return sb.toString();
    }

    public final String AFLogger() {
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.CHANNEL);
        if (string == null) {
            string = AFInAppEventParameterName("CHANNEL");
        }
        if (string == null || !string.equals("")) {
            return string;
        }
        return null;
    }

    public final String valueOf() {
        AFh1xSDK aFh1xSDK = this.valueOf.f189e;
        AFa1bSDK aFa1bSDK = aFh1xSDK != null ? new AFa1bSDK(aFh1xSDK.AFInAppEventType, aFh1xSDK.unregisterClient) : null;
        if (aFa1bSDK != null) {
            return aFa1bSDK.AFInAppEventParameterName;
        }
        return null;
    }

    public final String AFInAppEventType(String str) {
        try {
            int identifier = this.AFInAppEventParameterName.valueOf.getResources().getIdentifier(str, TypedValues.Custom.S_STRING, this.AFInAppEventParameterName.valueOf.getPackageName());
            if (identifier != 0) {
                return this.AFInAppEventParameterName.valueOf.getString(identifier);
            }
            return null;
        } catch (Resources.NotFoundException e) {
            StringBuilder sb = new StringBuilder("Could not load string resource!");
            sb.append(e.getMessage());
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }
}
