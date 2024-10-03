package com.appsflyer.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.appsflyer.internal.AFd1xSDK;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFd1vSDK implements AFd1xSDK {
    private final ScheduledExecutorService AFInAppEventParameterName;
    private final AFi1ySDK AFInAppEventType;
    private final Executor AFKeystoreWrapper;
    private AFd1wSDK valueOf;
    private final AFc1uSDK values;

    public AFd1vSDK(Executor executor, ScheduledExecutorService scheduledExecutorService, AFc1uSDK aFc1uSDK, AFi1ySDK aFi1ySDK) {
        Intrinsics.checkNotNullParameter(executor, "");
        Intrinsics.checkNotNullParameter(scheduledExecutorService, "");
        Intrinsics.checkNotNullParameter(aFc1uSDK, "");
        Intrinsics.checkNotNullParameter(aFi1ySDK, "");
        this.AFKeystoreWrapper = executor;
        this.AFInAppEventParameterName = scheduledExecutorService;
        this.values = aFc1uSDK;
        this.AFInAppEventType = aFi1ySDK;
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void values(Context context, AFd1xSDK.AFa1vSDK aFa1vSDK) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(aFa1vSDK, "");
        Intrinsics.checkNotNullParameter(context, "");
        if (this.valueOf != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Application");
            }
            ((Application) applicationContext).unregisterActivityLifecycleCallbacks(this.valueOf);
        }
        this.valueOf = null;
        AFd1wSDK aFd1wSDK = new AFd1wSDK(this.AFKeystoreWrapper, this.AFInAppEventParameterName, this.values, this.AFInAppEventType, aFa1vSDK);
        this.valueOf = aFd1wSDK;
        if (context instanceof Activity) {
            aFd1wSDK.onActivityResumed((Activity) context);
        }
        Application valueOf = AFb1uSDK.valueOf(context);
        if (valueOf != null) {
            valueOf.registerActivityLifecycleCallbacks(this.valueOf);
        }
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final boolean AFKeystoreWrapper() {
        return this.valueOf != null;
    }

    @Override // com.appsflyer.internal.AFd1xSDK
    public final void valueOf() {
        AFd1xSDK.AFa1vSDK aFa1vSDK;
        AFd1wSDK aFd1wSDK = this.valueOf;
        if (aFd1wSDK == null || (aFa1vSDK = aFd1wSDK.values) == null) {
            return;
        }
        aFa1vSDK.valueOf();
    }
}
