package com.appsflyer.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.appsflyer.AFLogger;
import com.appsflyer.attribution.AppsFlyerRequestListener;

/* loaded from: classes.dex */
public final class AFf1tSDK extends AFf1rSDK<String> {
    private final AFd1sSDK afInfoLog;

    /* renamed from: e */
    private final String f241e;

    /* renamed from: i */
    private final AFj1ySDK f242i;

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final boolean AFInAppEventParameterName() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AppsFlyerRequestListener registerClient() {
        return null;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        return false;
    }

    public AFf1tSDK(AFd1mSDK aFd1mSDK, String str, AFj1ySDK aFj1ySDK) {
        super(AFf1zSDK.IMPRESSIONS, new AFf1zSDK[]{AFf1zSDK.RC_CDN, AFf1zSDK.FETCH_ADVERTISING_ID}, aFd1mSDK, str);
        this.f241e = str;
        this.f242i = aFj1ySDK;
        this.afInfoLog = aFd1mSDK.AFInAppEventType();
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AFe1uSDK<String> valueOf(String str) {
        String obj;
        AFe1wSDK aFe1wSDK = ((AFf1rSDK) this).f234d;
        String valueOf = this.afInfoLog.valueOf();
        if (AFc1rSDK.AFInAppEventType(valueOf)) {
            obj = this.f241e;
        } else {
            obj = Uri.parse(this.f241e).buildUpon().appendQueryParameter("advertising_id", valueOf).build().toString();
        }
        return aFe1wSDK.AFInAppEventType(obj);
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        super.AFKeystoreWrapper();
        AFe1hSDK<Result> aFe1hSDK = this.AFLogger;
        if (aFe1hSDK != 0) {
            int statusCode = aFe1hSDK.getStatusCode();
            if (statusCode == 200) {
                StringBuilder sb = new StringBuilder("Cross promotion impressions success: ");
                sb.append(this.f241e);
                AFLogger.afInfoLog(sb.toString(), false);
                return;
            }
            if (statusCode == 301 || statusCode == 302) {
                StringBuilder sb2 = new StringBuilder("Cross promotion redirection success: ");
                sb2.append(this.f241e);
                AFLogger.afInfoLog(sb2.toString(), false);
                String values = aFe1hSDK.values("Location");
                AFj1ySDK aFj1ySDK = this.f242i;
                if (aFj1ySDK == null || values == null) {
                    return;
                }
                aFj1ySDK.AFKeystoreWrapper = values;
                AFj1ySDK aFj1ySDK2 = this.f242i;
                Context context = aFj1ySDK2.AFInAppEventType.get();
                if (context != null) {
                    try {
                        if (aFj1ySDK2.AFKeystoreWrapper != null) {
                            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(aFj1ySDK2.AFKeystoreWrapper)).setFlags(268435456));
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        AFLogger.afErrorLog("Failed to open cross promotion url, does OS have browser installed?".concat(String.valueOf(e)), e);
                        return;
                    }
                }
                return;
            }
            StringBuilder sb3 = new StringBuilder("call to ");
            sb3.append(this.f241e);
            sb3.append(" failed: ");
            sb3.append(statusCode);
            AFLogger.afInfoLog(sb3.toString());
        }
    }
}
