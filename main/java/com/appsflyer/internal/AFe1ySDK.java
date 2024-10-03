package com.appsflyer.internal;

import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFe1ySDK {
    public static boolean values(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        int valueOf = AFc1tSDK.valueOf(str);
        int valueOf2 = AFc1tSDK.valueOf(str2);
        Pair<Integer, Integer> AFInAppEventType = AFe1zSDK.AFInAppEventType(str2);
        Pair<Integer, Integer> AFInAppEventParameterName = AFe1zSDK.AFInAppEventParameterName(str2);
        return (valueOf2 == -1 || AFInAppEventType != null) ? AFInAppEventParameterName != null ? AFInAppEventParameterName.getFirst().intValue() <= valueOf && valueOf <= AFInAppEventParameterName.getSecond().intValue() : AFInAppEventType != null && AFInAppEventType.getFirst().intValue() <= valueOf && valueOf <= AFInAppEventType.getSecond().intValue() : valueOf2 == valueOf;
    }
}
