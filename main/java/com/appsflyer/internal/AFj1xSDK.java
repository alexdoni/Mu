package com.appsflyer.internal;

import com.appsflyer.AppsFlyerLib;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFj1xSDK implements AFj1wSDK {
    @Override // com.appsflyer.internal.AFj1wSDK
    public final String AFInAppEventType(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        String format = String.format(str, AppsFlyerLib.getInstance().getHostPrefix(), AFb1tSDK.valueOf().getHostName());
        Intrinsics.checkNotNullExpressionValue(format, "");
        return format;
    }
}
