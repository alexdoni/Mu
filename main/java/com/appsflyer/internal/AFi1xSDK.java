package com.appsflyer.internal;

import android.app.Activity;
import android.net.Uri;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes.dex */
public final class AFi1xSDK implements AFi1ySDK {
    private String values;

    @Override // com.appsflyer.internal.AFi1ySDK
    public final void AFKeystoreWrapper(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "");
        String str = this.values;
        if (str == null || str.length() == 0) {
            this.values = values(activity);
        }
    }

    @Override // com.appsflyer.internal.AFi1ySDK
    public final String AFInAppEventParameterName(Activity activity) {
        String str = this.values;
        this.values = null;
        String str2 = str;
        return !(str2 == null || str2.length() == 0) ? str : values(activity);
    }

    private static String values(Activity activity) {
        Uri AFKeystoreWrapper = AFc1aSDK.AFKeystoreWrapper(activity != null ? activity.getIntent() : null);
        String obj = AFKeystoreWrapper != null ? AFKeystoreWrapper.toString() : null;
        if (obj == null) {
            obj = "";
        }
        if (valueOf(obj)) {
            return null;
        }
        return obj;
    }

    private static boolean valueOf(String str) {
        return StringsKt.startsWith$default(str, "android-app://", false, 2, (Object) null);
    }

    @Override // com.appsflyer.internal.AFi1ySDK
    public final String AFInAppEventType(Activity activity) {
        Uri referrer = (activity == null || activity.getIntent() == null) ? null : activity.getReferrer();
        String obj = referrer != null ? referrer.toString() : null;
        return obj == null ? "" : obj;
    }
}
