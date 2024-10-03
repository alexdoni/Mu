package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.appsflyer.internal.AFe1fSDK;

/* loaded from: classes.dex */
public final class AFf1bSDK implements AFe1bSDK {
    private final AFd1sSDK AFInAppEventParameterName;
    private final Object AFInAppEventType = new Object();
    private final AFg1xSDK AFKeystoreWrapper;
    private final AFe1fSDK AFLogger;

    /* renamed from: d */
    private final AFf1aSDK f205d;

    /* renamed from: e */
    private AFh1eSDK f206e;
    private final AFe1wSDK registerClient;
    private AFf1gSDK unregisterClient;
    public final AFg1ySDK valueOf;
    private final AFf1eSDK values;

    @Override // com.appsflyer.internal.AFe1bSDK
    public final void values(AFe1eSDK<?> aFe1eSDK) {
    }

    public AFf1bSDK(AFf1eSDK aFf1eSDK, AFd1sSDK aFd1sSDK, AFg1xSDK aFg1xSDK, AFg1ySDK aFg1ySDK, AFe1wSDK aFe1wSDK, AFf1aSDK aFf1aSDK, AFe1fSDK aFe1fSDK) {
        this.values = aFf1eSDK;
        this.AFInAppEventParameterName = aFd1sSDK;
        this.AFKeystoreWrapper = aFg1xSDK;
        this.valueOf = aFg1ySDK;
        this.registerClient = aFe1wSDK;
        this.f205d = aFf1aSDK;
        this.AFLogger = aFe1fSDK;
        aFe1fSDK.valueOf.add(this);
    }

    public final void values(AFf1cSDK aFf1cSDK) {
        AFf1fSDK aFf1fSDK = new AFf1fSDK(this.values, this.AFInAppEventParameterName, this.AFKeystoreWrapper, this.valueOf, this.registerClient, this.f205d, "v1", aFf1cSDK);
        AFe1fSDK aFe1fSDK = this.AFLogger;
        aFe1fSDK.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1fSDK));
    }

    public final AFh1eSDK AFInAppEventParameterName() {
        AFh1eSDK aFh1eSDK;
        synchronized (this.AFInAppEventType) {
            aFh1eSDK = this.f206e;
            this.f206e = null;
        }
        return aFh1eSDK;
    }

    private void values(AFf1gSDK aFf1gSDK, AFf1cSDK aFf1cSDK) {
        synchronized (this.AFInAppEventType) {
            this.unregisterClient = aFf1gSDK;
        }
        if (aFf1cSDK != null) {
            aFf1cSDK.onRemoteConfigUpdateFinished(aFf1gSDK);
        }
    }

    @Override // com.appsflyer.internal.AFe1bSDK
    public final void AFInAppEventParameterName(AFe1eSDK<?> aFe1eSDK, AFe1dSDK aFe1dSDK) {
        if (aFe1eSDK instanceof AFf1fSDK) {
            AFf1fSDK aFf1fSDK = (AFf1fSDK) aFe1eSDK;
            AFf1gSDK aFf1gSDK = aFf1fSDK.f207d;
            if (aFf1gSDK == null) {
                AFLogger.INSTANCE.m103w(AFg1gSDK.REMOTE_CONTROL, "update RC returned null result, something went wrong!");
                aFf1gSDK = AFf1gSDK.FAILURE;
            }
            if (aFf1gSDK != AFf1gSDK.USE_CACHED) {
                AFh1eSDK aFh1eSDK = aFf1fSDK.unregisterClient;
                synchronized (this.AFInAppEventType) {
                    this.f206e = aFh1eSDK;
                }
            }
            values(aFf1gSDK, aFf1fSDK.f208e);
        }
    }

    @Override // com.appsflyer.internal.AFe1bSDK
    public final void AFKeystoreWrapper(AFe1eSDK<?> aFe1eSDK) {
        if (aFe1eSDK instanceof AFf1fSDK) {
            AFf1fSDK aFf1fSDK = (AFf1fSDK) aFe1eSDK;
            synchronized (this.AFInAppEventType) {
                this.f206e = null;
            }
            values(AFf1gSDK.FAILURE, aFf1fSDK.f208e);
        }
    }
}
