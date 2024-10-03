package com.appsflyer.internal;

import android.os.Build;
import com.appsflyer.AFKeystoreWrapper;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import java.security.KeyStoreException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1394d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\u0007\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\u0007\u0010\nJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0004\b\u000b\u0010\n"}, m1395d2 = {"Lcom/appsflyer/internal/AFb1gSDK;", "", "Lcom/appsflyer/internal/AFd1kSDK;", "p0", "Lcom/appsflyer/internal/AFd1tSDK;", "p1", "", "AFKeystoreWrapper", "(Lcom/appsflyer/internal/AFd1kSDK;Lcom/appsflyer/internal/AFd1tSDK;)V", "", "()Ljava/lang/String;", "valueOf", "<init>", "()V"}, m1396k = 1, m1397mv = {1, 6, 0}, m1399xi = 48)
/* loaded from: classes.dex */
public final class AFb1gSDK {
    public static final AFb1gSDK INSTANCE = new AFb1gSDK();

    private AFb1gSDK() {
    }

    @JvmStatic
    public static final void AFKeystoreWrapper(AFd1kSDK p0, AFd1tSDK p1) {
        int i;
        Intrinsics.checkNotNullParameter(p0, "");
        Intrinsics.checkNotNullParameter(p1, "");
        AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
        if (AFb1uSDK.valueOf()) {
            AFLogger.afRDLog("OPPO device found");
            i = 23;
        } else {
            i = 18;
        }
        if (Build.VERSION.SDK_INT >= i && !appsFlyerProperties.getBoolean(AppsFlyerProperties.DISABLE_KEYSTORE, true)) {
            StringBuilder sb = new StringBuilder("OS SDK is=");
            sb.append(Build.VERSION.SDK_INT);
            sb.append("; use KeyStore");
            AFLogger.afRDLog(sb.toString());
            AFKeystoreWrapper aFKeystoreWrapper = new AFKeystoreWrapper(p0.valueOf);
            if (!aFKeystoreWrapper.valueOf()) {
                aFKeystoreWrapper.values = AFb1kSDK.AFInAppEventType(p0, p1);
                aFKeystoreWrapper.valueOf = 0;
                aFKeystoreWrapper.AFKeystoreWrapper(aFKeystoreWrapper.AFKeystoreWrapper());
            } else {
                String AFKeystoreWrapper = aFKeystoreWrapper.AFKeystoreWrapper();
                synchronized (aFKeystoreWrapper.AFKeystoreWrapper) {
                    aFKeystoreWrapper.valueOf++;
                    AFLogger.afInfoLog("Deleting key with alias: ".concat(String.valueOf(AFKeystoreWrapper)));
                    try {
                        synchronized (aFKeystoreWrapper.AFKeystoreWrapper) {
                            aFKeystoreWrapper.AFInAppEventParameterName.deleteEntry(AFKeystoreWrapper);
                        }
                    } catch (KeyStoreException e) {
                        StringBuilder sb2 = new StringBuilder("Exception ");
                        sb2.append(e.getMessage());
                        sb2.append(" occurred");
                        AFLogger.afErrorLog(sb2.toString(), e);
                    }
                }
                aFKeystoreWrapper.AFKeystoreWrapper(aFKeystoreWrapper.AFKeystoreWrapper());
            }
            appsFlyerProperties.set("KSAppsFlyerId", aFKeystoreWrapper.AFInAppEventType());
            appsFlyerProperties.set("KSAppsFlyerRICounter", String.valueOf(aFKeystoreWrapper.values()));
            return;
        }
        StringBuilder sb3 = new StringBuilder("OS SDK is=");
        sb3.append(Build.VERSION.SDK_INT);
        sb3.append("; no KeyStore usage");
        AFLogger.afRDLog(sb3.toString());
    }

    public static String AFKeystoreWrapper() {
        return AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
    }

    public static String valueOf() {
        return AppsFlyerProperties.getInstance().getString("KSAppsFlyerRICounter");
    }
}
