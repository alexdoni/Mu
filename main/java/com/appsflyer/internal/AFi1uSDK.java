package com.appsflyer.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class AFi1uSDK extends AFh1dSDK {
    private String AFInAppEventParameterName;
    private Network values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AFi1uSDK(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "");
        this.AFInAppEventParameterName = "unknown";
        AFa1vSDK aFa1vSDK = new AFa1vSDK();
        ConnectivityManager connectivityManager = this.AFKeystoreWrapper;
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), aFa1vSDK);
        }
    }

    /* loaded from: classes.dex */
    public static final class AFa1vSDK extends ConnectivityManager.NetworkCallback {
        AFa1vSDK() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            Intrinsics.checkNotNullParameter(network, "");
            AFi1uSDK.this.values = network;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            Intrinsics.checkNotNullParameter(network, "");
            AFi1uSDK.this.values = network;
            AFi1uSDK.this.AFInAppEventParameterName = "NetworkLost";
        }
    }

    @Override // com.appsflyer.internal.AFh1dSDK
    protected final String values() {
        Network network = this.values;
        if (network != null) {
            ConnectivityManager connectivityManager = this.AFKeystoreWrapper;
            NetworkCapabilities networkCapabilities = connectivityManager != null ? connectivityManager.getNetworkCapabilities(network) : null;
            if (networkCapabilities != null && networkCapabilities != null) {
                if (networkCapabilities.hasTransport(1)) {
                    return "WIFI";
                }
                if (networkCapabilities.hasTransport(0)) {
                    return "MOBILE";
                }
            }
        }
        return "unknown";
    }

    @Override // com.appsflyer.internal.AFh1dSDK
    public final boolean valueOf() {
        Network network = this.values;
        if (network == null) {
            return false;
        }
        if (!(!Intrinsics.areEqual(this.AFInAppEventParameterName, "NetworkLost"))) {
            network = null;
        }
        if (network == null) {
            return false;
        }
        ConnectivityManager connectivityManager = this.AFKeystoreWrapper;
        NetworkCapabilities networkCapabilities = connectivityManager != null ? connectivityManager.getNetworkCapabilities(network) : null;
        if (networkCapabilities != null) {
            return AFInAppEventParameterName(networkCapabilities);
        }
        return false;
    }

    private static boolean AFInAppEventParameterName(NetworkCapabilities networkCapabilities) {
        return (networkCapabilities == null || !networkCapabilities.hasTransport(4) || networkCapabilities.hasCapability(15)) ? false : true;
    }
}
