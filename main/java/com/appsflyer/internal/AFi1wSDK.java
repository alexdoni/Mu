package com.appsflyer.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.appsflyer.AFLogger;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFi1wSDK extends AFh1dSDK {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFi1wSDK(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "");
    }

    @Override // com.appsflyer.internal.AFh1dSDK
    public final boolean valueOf() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            Intrinsics.checkNotNullExpressionValue(networkInterfaces, "");
            ArrayList list = Collections.list(networkInterfaces);
            Intrinsics.checkNotNullExpressionValue(list, "");
            ArrayList<NetworkInterface> arrayList = list;
            if (!(arrayList instanceof Collection) || !arrayList.isEmpty()) {
                for (NetworkInterface networkInterface : arrayList) {
                    if (networkInterface.isUp() && Intrinsics.areEqual(networkInterface.getName(), "tun0")) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            AFLogger.afErrorLog("Failed collecting ivc data", e);
            return false;
        }
    }

    @Override // com.appsflyer.internal.AFh1dSDK
    protected final String values() {
        ConnectivityManager connectivityManager = this.AFKeystoreWrapper;
        if (connectivityManager != null) {
            if (AFInAppEventParameterName(connectivityManager.getNetworkInfo(1))) {
                return "WIFI";
            }
            if (AFInAppEventParameterName(connectivityManager.getNetworkInfo(0))) {
                return "MOBILE";
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                return type != 0 ? type != 1 ? "unknown" : "WIFI" : "MOBILE";
            }
        }
        return "unknown";
    }
}
