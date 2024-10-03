package com.appsflyer.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.text.AndroidCharacter;
import android.view.View;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.internal.components.network.http.ResponseNetwork;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* loaded from: classes.dex */
public final class AFf1iSDK extends AFf1hSDK {
    private static int $10 = 0;
    private static int $11 = 1;
    private static int afErrorLog = 0;
    private static int afRDLog = 1;
    private final AFd1sSDK afInfoLog;
    private final AFg1qSDK afWarnLog;
    private final String force;

    /* renamed from: w */
    private final AFd1kSDK f216w;
    private static char[] afDebugLog = {62958, 42166, 22397, 1594, 45256};
    private static long afVerboseLog = -7824471264982817596L;

    @Override // com.appsflyer.internal.AFf1hSDK
    protected final void AFInAppEventParameterName(AFa1pSDK aFa1pSDK) {
        int i = afErrorLog + 83;
        afRDLog = i % 128;
        int i2 = i % 2;
    }

    @Override // com.appsflyer.internal.AFf1hSDK
    protected final void AFKeystoreWrapper(AFa1pSDK aFa1pSDK) {
        int i = afErrorLog + 35;
        afRDLog = i % 128;
        int i2 = i % 2;
    }

    @Override // com.appsflyer.internal.AFf1hSDK
    protected final void valueOf(AFa1pSDK aFa1pSDK) {
        int i = afErrorLog + 51;
        afRDLog = i % 128;
        int i2 = i % 2;
    }

    public AFf1iSDK(String str, AFd1mSDK aFd1mSDK) {
        super(new AFg1nSDK(aFd1mSDK.mo80w().valueOf), aFd1mSDK, str);
        this.afInfoLog = aFd1mSDK.AFInAppEventType();
        this.f216w = aFd1mSDK.mo80w();
        this.force = str;
        this.afWarnLog = aFd1mSDK.unregisterClient();
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        int i = afErrorLog + 121;
        afRDLog = i % 128;
        int i2 = i % 2;
        super.AFKeystoreWrapper();
        ResponseNetwork responseNetwork = this.AFLogger;
        if ((responseNetwork != null ? '#' : 'J') != 'J' && responseNetwork.isSuccessful()) {
            force();
        }
        int i3 = afErrorLog + 15;
        afRDLog = i3 % 128;
        if ((i3 % 2 == 0 ? '1' : 'K') != 'K') {
            int i4 = 11 / 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.appsflyer.internal.AFf1hSDK
    public final void AFInAppEventType(AFa1pSDK aFa1pSDK) {
        int i = afRDLog + 3;
        afErrorLog = i % 128;
        if (i % 2 != 0) {
            super.AFInAppEventType(aFa1pSDK);
            Context context = this.f216w.valueOf;
            AFb1tSDK.valueOf();
            Object obj = null;
            obj.hashCode();
            throw null;
        }
        super.AFInAppEventType(aFa1pSDK);
        Context context2 = this.f216w.valueOf;
        AFb1tSDK valueOf = AFb1tSDK.valueOf();
        if (context2 == null) {
            throw new IllegalStateException("Context is not provided, can't send register request");
        }
        if (valueOf.AFInAppEventParameterName()) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            throw new IllegalStateException("CustomerUserId not set, register is not sent");
        }
        PackageManager packageManager = context2.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context2.getPackageName(), 0);
            aFa1pSDK.AFInAppEventType("app_version_code", Integer.toString(packageInfo.versionCode));
            aFa1pSDK.AFInAppEventType("app_version_name", packageInfo.versionName);
            aFa1pSDK.AFInAppEventType(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
            aFa1pSDK.AFInAppEventType("installDate", AFb1tSDK.valueOf(new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US), packageInfo.firstInstallTime));
        } catch (Throwable th) {
            AFLogger.afErrorLog("Exception while collecting application version info.", th);
        }
        this.afWarnLog.AFInAppEventParameterName(aFa1pSDK.valueOf());
        aFa1pSDK.valueOf().remove("ivc");
        String AFInAppEventType = AFb1tSDK.AFInAppEventType();
        if (AFInAppEventType != null) {
            aFa1pSDK.AFInAppEventType("appUserId", AFInAppEventType);
        }
        try {
            aFa1pSDK.AFInAppEventType("model", Build.MODEL);
            Object[] objArr = new Object[1];
            m89a(5 - Color.alpha(0), (char) ('0' - AndroidCharacter.getMirror('0')), View.combineMeasuredStates(0, 0), objArr);
            aFa1pSDK.AFInAppEventType(((String) objArr[0]).intern(), Build.BRAND);
        } catch (Throwable th2) {
            AFLogger.afErrorLog("Exception while collecting device brand and model.", th2);
        }
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            aFa1pSDK.AFInAppEventType(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
        }
        AFa1bSDK valueOf2 = AFb1rSDK.valueOf(context2.getContentResolver());
        if (valueOf2 != null) {
            int i2 = afRDLog + 25;
            afErrorLog = i2 % 128;
            int i3 = i2 % 2;
            aFa1pSDK.AFInAppEventType("amazon_aid", valueOf2.AFInAppEventParameterName);
            aFa1pSDK.AFInAppEventType("amazon_aid_limit", String.valueOf(valueOf2.AFKeystoreWrapper));
        }
        aFa1pSDK.AFInAppEventType("devkey", ((AFf1rSDK) this).registerClient.registerClient);
        aFa1pSDK.AFInAppEventType("uid", AFb1kSDK.AFInAppEventType(this.f216w, ((AFf1hSDK) this).f213i));
        aFa1pSDK.AFInAppEventType("af_gcm_token", this.force);
        aFa1pSDK.AFInAppEventType("launch_counter", Integer.toString(((AFf1hSDK) this).f213i.AFInAppEventParameterName("appsFlyerCount", 0)));
        aFa1pSDK.AFInAppEventType(ServerProtocol.DIALOG_PARAM_SDK_VERSION, Integer.toString(Build.VERSION.SDK_INT));
        String AFLogger = this.afInfoLog.AFLogger();
        if (!(AFLogger == null)) {
            aFa1pSDK.AFInAppEventType(AppsFlyerProperties.CHANNEL, AFLogger);
            int i4 = afRDLog + 103;
            afErrorLog = i4 % 128;
            int i5 = i4 % 2;
        }
        int i6 = afRDLog + 125;
        afErrorLog = i6 % 128;
        int i7 = i6 % 2;
    }

    @Override // com.appsflyer.internal.AFf1hSDK
    protected final void values(AFa1pSDK aFa1pSDK) {
        String valueOf;
        int i = afErrorLog + 91;
        afRDLog = i % 128;
        if ((i % 2 == 0 ? 'Z' : ';') != 'Z') {
            valueOf = this.afInfoLog.valueOf();
            if (!(valueOf != null)) {
                return;
            }
        } else {
            valueOf = this.afInfoLog.valueOf();
            int i2 = 1 / 0;
            if (valueOf == null) {
                return;
            }
        }
        int i3 = afErrorLog + 25;
        afRDLog = i3 % 128;
        int i4 = i3 % 2;
        aFa1pSDK.AFInAppEventType("advertiserId", valueOf);
    }

    @Override // com.appsflyer.internal.AFf1hSDK, com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        int i = afRDLog + 117;
        int i2 = i % 128;
        afErrorLog = i2;
        int i3 = i % 2;
        int i4 = i2 + 55;
        afRDLog = i4 % 128;
        if (i4 % 2 != 0) {
            return false;
        }
        throw null;
    }

    private void force() {
        int i = afRDLog + 105;
        afErrorLog = i % 128;
        int i2 = i % 2;
        ((AFf1hSDK) this).f213i.values("sentRegisterRequestToAF", true);
        AFLogger.afDebugLog("[register] Successfully registered for Uninstall Tracking");
        int i3 = afRDLog + 29;
        afErrorLog = i3 % 128;
        int i4 = i3 % 2;
    }

    /* renamed from: a */
    private static void m89a(int i, char c, int i2, Object[] objArr) {
        AFj1oSDK aFj1oSDK = new AFj1oSDK();
        long[] jArr = new long[i];
        aFj1oSDK.AFKeystoreWrapper = 0;
        while (true) {
            if ((aFj1oSDK.AFKeystoreWrapper < i ? (char) 25 : 'N') != 25) {
                break;
            }
            int i3 = $11 + 33;
            $10 = i3 % 128;
            int i4 = i3 % 2;
            jArr[aFj1oSDK.AFKeystoreWrapper] = (((char) (afDebugLog[i2 + aFj1oSDK.AFKeystoreWrapper] ^ 1056586240800585100L)) ^ (aFj1oSDK.AFKeystoreWrapper * (1056586240800585100L ^ afVerboseLog))) ^ c;
            aFj1oSDK.AFKeystoreWrapper++;
        }
        char[] cArr = new char[i];
        aFj1oSDK.AFKeystoreWrapper = 0;
        while (true) {
            if (aFj1oSDK.AFKeystoreWrapper >= i) {
                objArr[0] = new String(cArr);
                return;
            }
            cArr[aFj1oSDK.AFKeystoreWrapper] = (char) jArr[aFj1oSDK.AFKeystoreWrapper];
            aFj1oSDK.AFKeystoreWrapper++;
            int i5 = $10 + 51;
            $11 = i5 % 128;
            int i6 = i5 % 2;
        }
    }
}
