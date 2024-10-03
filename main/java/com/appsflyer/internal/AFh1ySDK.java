package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFh1ySDK implements AFg1fSDK {
    private AFg1bSDK AFInAppEventParameterName;
    private AFg1eSDK AFInAppEventType;
    private AFg1dSDK valueOf;
    private final AFd1mSDK values;

    public AFh1ySDK(AFd1mSDK aFd1mSDK) {
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        this.values = aFd1mSDK;
    }

    @Override // com.appsflyer.internal.AFg1fSDK
    public final void AFInAppEventParameterName() {
        AFg1eSDK aFg1eSDK = this.AFInAppEventType;
        if (aFg1eSDK != null) {
            AFLogger aFLogger = AFLogger.INSTANCE;
            AFg1hSDK.v$default(aFLogger, AFg1gSDK.EXCEPTION_MANAGER, "Releasing Exception Manager Client", false, 4, null);
            aFLogger.unregisterClient(aFg1eSDK);
            this.AFInAppEventType = null;
        }
    }

    @Override // com.appsflyer.internal.AFg1fSDK
    public final void values() {
        AFg1bSDK aFg1bSDK = this.AFInAppEventParameterName;
        if (aFg1bSDK != null) {
            AFLogger aFLogger = AFLogger.INSTANCE;
            AFg1hSDK.v$default(aFLogger, AFg1gSDK.RD, "Releasing Proxy Manager Client", false, 4, null);
            aFLogger.unregisterClient(aFg1bSDK);
            this.AFInAppEventParameterName = null;
        }
    }

    @Override // com.appsflyer.internal.AFg1fSDK
    public final void AFKeystoreWrapper() {
        AFg1dSDK aFg1dSDK = this.valueOf;
        if (aFg1dSDK != null) {
            AFLogger aFLogger = AFLogger.INSTANCE;
            AFg1hSDK.v$default(aFLogger, AFg1gSDK.RD, "Releasing Proxy Manager Client", false, 4, null);
            aFLogger.unregisterClient(aFg1dSDK);
            this.valueOf = null;
        }
    }

    @Override // com.appsflyer.internal.AFg1fSDK
    /* renamed from: d */
    public final void mo95d() {
        AFLogger aFLogger = AFLogger.INSTANCE;
        AFg1hSDK[] aFg1hSDKArr = new AFg1hSDK[1];
        if (this.valueOf == null) {
            this.valueOf = new AFg1dSDK();
        }
        AFg1dSDK aFg1dSDK = this.valueOf;
        Intrinsics.checkNotNull(aFg1dSDK);
        aFg1hSDKArr[0] = aFg1dSDK;
        aFLogger.registerClient(aFg1hSDKArr);
    }

    @Override // com.appsflyer.internal.AFg1fSDK
    public final void valueOf() {
        AFLogger aFLogger = AFLogger.INSTANCE;
        AFg1hSDK[] aFg1hSDKArr = new AFg1hSDK[1];
        if (this.AFInAppEventParameterName == null) {
            this.AFInAppEventParameterName = new AFg1bSDK(this.values);
        }
        AFg1bSDK aFg1bSDK = this.AFInAppEventParameterName;
        Intrinsics.checkNotNull(aFg1bSDK);
        aFg1hSDKArr[0] = aFg1bSDK;
        aFLogger.registerClient(aFg1hSDKArr);
    }

    @Override // com.appsflyer.internal.AFg1fSDK
    public final void AFInAppEventType() {
        AFLogger aFLogger = AFLogger.INSTANCE;
        AFg1hSDK[] aFg1hSDKArr = new AFg1hSDK[1];
        if (this.AFInAppEventType == null) {
            this.AFInAppEventType = new AFg1eSDK(this.values);
        }
        AFg1eSDK aFg1eSDK = this.AFInAppEventType;
        Intrinsics.checkNotNull(aFg1eSDK);
        aFg1hSDKArr[0] = aFg1eSDK;
        aFLogger.registerClient(aFg1hSDKArr);
    }
}
