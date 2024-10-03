package com.appsflyer.internal;

import com.appsflyer.internal.AFi1jSDK;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class AFi1tSDK extends AFi1jSDK {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AFi1tSDK(String str, String str2, Runnable runnable) {
        super(str, str2, runnable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void AFKeystoreWrapper(AFd1tSDK aFd1tSDK, AFc1bSDK<Map<String, Object>> aFc1bSDK) {
        AFb1tSDK.valueOf();
        if (AFb1tSDK.AFInAppEventType(aFd1tSDK, false) > 0 || !aFc1bSDK.AFKeystoreWrapper()) {
            return;
        }
        aFc1bSDK.AFKeystoreWrapper.AFInAppEventParameterName().execute(aFc1bSDK.valueOf);
        this.registerClient = System.currentTimeMillis();
        this.f285d = AFi1jSDK.AFa1tSDK.STARTED;
        addObserver(new AFi1jSDK.C07304());
    }
}
