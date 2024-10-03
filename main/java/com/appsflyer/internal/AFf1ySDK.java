package com.appsflyer.internal;

import java.util.TimerTask;

/* loaded from: classes.dex */
public final class AFf1ySDK extends TimerTask {
    private final Thread AFInAppEventParameterName;

    public AFf1ySDK(Thread thread) {
        this.AFInAppEventParameterName = thread;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        this.AFInAppEventParameterName.interrupt();
    }
}
