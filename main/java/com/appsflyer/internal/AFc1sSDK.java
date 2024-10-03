package com.appsflyer.internal;

import com.appsflyer.internal.AFe1fSDK;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFc1sSDK implements Runnable {
    private final AFa1pSDK AFInAppEventParameterName;
    private final Map<String, Object> AFInAppEventType;
    private final AFd1mSDK valueOf;

    public AFc1sSDK(AFd1mSDK aFd1mSDK, AFa1pSDK aFa1pSDK, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(aFd1mSDK, "");
        Intrinsics.checkNotNullParameter(aFa1pSDK, "");
        this.valueOf = aFd1mSDK;
        this.AFInAppEventParameterName = aFa1pSDK;
        this.AFInAppEventType = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AFf1jSDK aFf1hSDK;
        if (this.AFInAppEventParameterName.values()) {
            AFf1jSDK aFf1jSDK = new AFf1jSDK(this.AFInAppEventParameterName, this.valueOf);
            aFf1jSDK.afInfoLog = this.AFInAppEventType;
            aFf1hSDK = aFf1jSDK;
        } else {
            aFf1hSDK = new AFf1hSDK(this.AFInAppEventParameterName, this.valueOf);
        }
        AFe1fSDK afInfoLog = this.valueOf.afInfoLog();
        afInfoLog.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(aFf1hSDK));
        if (aFf1hSDK.AFInAppEventParameterName == AFf1zSDK.CONVERSION) {
            this.valueOf.afVerboseLog();
            if (AFe1gSDK.valueOf() && AFb1uSDK.AFKeystoreWrapper(this.valueOf.mo80w().valueOf)) {
                AFe1fSDK afInfoLog2 = this.valueOf.afInfoLog();
                afInfoLog2.AFKeystoreWrapper.execute(new AFe1fSDK.RunnableC07083(new AFf1pSDK(this.valueOf, "install")));
            }
        }
    }
}
