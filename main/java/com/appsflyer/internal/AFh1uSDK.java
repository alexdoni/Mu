package com.appsflyer.internal;

import android.app.Activity;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFh1uSDK {
    public final Intent AFKeystoreWrapper;
    public final String valueOf;
    public final String values;

    public AFh1uSDK(Activity activity, AFi1ySDK aFi1ySDK) {
        Intrinsics.checkNotNullParameter(activity, "");
        Intrinsics.checkNotNullParameter(aFi1ySDK, "");
        this.AFKeystoreWrapper = activity.getIntent();
        this.values = aFi1ySDK.AFInAppEventType(activity);
        this.valueOf = aFi1ySDK.AFInAppEventParameterName(activity);
    }
}
