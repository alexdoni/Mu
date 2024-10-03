package com.appsflyer.internal;

import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.appsflyer.internal.components.network.http.exceptions.ParsingException;
import com.appsflyer.share.LinkGenerator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public final class AFf1uSDK extends AFf1rSDK<String> {
    private final String afInfoLog;

    /* renamed from: e */
    private final String f243e;
    private final Map<String, String> force;

    /* renamed from: i */
    private final LinkGenerator f244i;

    /* renamed from: v */
    private final UUID f245v;

    /* renamed from: w */
    private final LinkGenerator.ResponseListener f246w;

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final long AFInAppEventType() {
        return 3000L;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AppsFlyerRequestListener registerClient() {
        return null;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final boolean unregisterClient() {
        return false;
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    /* renamed from: v */
    protected final boolean mo91v() {
        return false;
    }

    public AFf1uSDK(AFd1mSDK aFd1mSDK, UUID uuid, String str, Map<String, String> map, String str2, LinkGenerator.ResponseListener responseListener, LinkGenerator linkGenerator) {
        super(AFf1zSDK.ONELINK, new AFf1zSDK[]{AFf1zSDK.RC_CDN}, aFd1mSDK, uuid.toString());
        this.f245v = uuid;
        this.f243e = str;
        this.force = new HashMap(map);
        this.f246w = responseListener;
        this.afInfoLog = str2;
        this.f244i = linkGenerator;
    }

    @Override // com.appsflyer.internal.AFf1rSDK, com.appsflyer.internal.AFe1eSDK
    public final void AFKeystoreWrapper() {
        super.AFKeystoreWrapper();
        LinkGenerator.ResponseListener responseListener = this.f246w;
        if (responseListener != null) {
            if (this.AFInAppEventType == AFe1dSDK.SUCCESS && this.AFLogger != null) {
                responseListener.onResponse((String) this.AFLogger.getBody());
                return;
            }
            Throwable m85e = m85e();
            if (m85e instanceof ParsingException) {
                if (((ParsingException) m85e).getRawResponse().isSuccessful()) {
                    responseListener.onResponseError("Can't parse one link data");
                    return;
                } else {
                    responseListener.onResponse(this.f244i.generateLink());
                    return;
                }
            }
            responseListener.onResponse(this.f244i.generateLink());
        }
    }

    @Override // com.appsflyer.internal.AFf1rSDK
    protected final AFe1uSDK<String> valueOf(String str) {
        return ((AFf1rSDK) this).f234d.AFKeystoreWrapper(this.f243e, this.force, this.afInfoLog, this.f245v, str);
    }
}
