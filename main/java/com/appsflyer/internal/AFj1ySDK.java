package com.appsflyer.internal;

import android.content.Context;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class AFj1ySDK {
    public final WeakReference<Context> AFInAppEventType;
    public String AFKeystoreWrapper;

    public AFj1ySDK(Context context) {
        this.AFInAppEventType = new WeakReference<>(context);
    }
}
