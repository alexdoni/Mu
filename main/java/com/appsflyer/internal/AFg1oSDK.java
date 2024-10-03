package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.FirebaseMessagingServiceListener;
import com.appsflyer.internal.AFe1fSDK;

/* loaded from: classes.dex */
public final class AFg1oSDK {
    public static String AFInAppEventParameterName;
    public final AFd1tSDK values;

    static {
        StringBuilder sb = new StringBuilder("https://%sregister.%s/api/v");
        sb.append(AFb1tSDK.valueOf);
        AFInAppEventParameterName = sb.toString();
    }

    public AFg1oSDK(Context context) {
        this.values = AFb1tSDK.valueOf().AFKeystoreWrapper(context);
    }

    public static boolean valueOf(Context context) {
        if (AppsFlyerLib.getInstance().isStopped()) {
            return false;
        }
        try {
            Class.forName("com.google.firebase.messaging.FirebaseMessagingService");
        } catch (ClassNotFoundException unused) {
        } catch (Throwable th) {
            AFLogger.INSTANCE.m97e(AFg1gSDK.UNINSTALL, "An error occurred while trying to verify manifest declarations: ", th);
        }
        return AFb1uSDK.valueOf(context, new Intent("com.google.firebase.MESSAGING_EVENT", null, context, FirebaseMessagingServiceListener.class));
    }

    public static boolean values(AFd1tSDK aFd1tSDK) {
        return aFd1tSDK.values("sentRegisterRequestToAF");
    }

    public static void AFInAppEventParameterName(String str) {
        AFd1mSDK values = AFb1tSDK.valueOf().values();
        AFf1iSDK aFf1iSDK = new AFf1iSDK(str, values);
        AFe1fSDK afInfoLog = values.afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1iSDK));
    }

    public final AFg1rSDK AFInAppEventParameterName() {
        String string;
        String string2;
        String AFKeystoreWrapper = this.values.AFKeystoreWrapper("afUninstallToken", (String) null);
        long AFKeystoreWrapper2 = this.values.AFKeystoreWrapper("afUninstallToken_received_time", 0L);
        boolean values = this.values.values("afUninstallToken_queued");
        this.values.values("afUninstallToken_queued", false);
        if (AFKeystoreWrapper == null && (string2 = AppsFlyerProperties.getInstance().getString("afUninstallToken")) != null) {
            AFKeystoreWrapper = string2.split(",")[r0.length - 1];
        }
        if (AFKeystoreWrapper2 == 0 && (string = AppsFlyerProperties.getInstance().getString("afUninstallToken")) != null) {
            String[] split = string.split(",");
            if (split.length >= 2) {
                try {
                    AFKeystoreWrapper2 = Long.parseLong(split[split.length - 2]);
                } catch (NumberFormatException unused) {
                }
            }
        }
        if (AFKeystoreWrapper != null) {
            return new AFg1rSDK(AFKeystoreWrapper, AFKeystoreWrapper2, values);
        }
        return null;
    }
}
