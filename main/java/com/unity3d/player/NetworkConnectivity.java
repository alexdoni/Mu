package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

/* loaded from: classes3.dex */
public class NetworkConnectivity extends Activity {

    /* renamed from: a */
    private final int f1652a = 0;

    /* renamed from: b */
    private final int f1653b = 1;

    /* renamed from: c */
    private final int f1654c = 2;

    /* renamed from: d */
    private int f1655d;

    /* renamed from: e */
    private ConnectivityManager f1656e;

    /* renamed from: f */
    private final ConnectivityManager.NetworkCallback f1657f;

    public NetworkConnectivity(Context context) {
        this.f1655d = 0;
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.unity3d.player.NetworkConnectivity.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onAvailable(Network network) {
                super.onAvailable(network);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                NetworkConnectivity networkConnectivity;
                int i;
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasTransport(0)) {
                    networkConnectivity = NetworkConnectivity.this;
                    i = 1;
                } else {
                    networkConnectivity = NetworkConnectivity.this;
                    i = 2;
                }
                networkConnectivity.f1655d = i;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onLost(Network network) {
                super.onLost(network);
                NetworkConnectivity.this.f1655d = 0;
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onUnavailable() {
                super.onUnavailable();
                NetworkConnectivity.this.f1655d = 0;
            }
        };
        this.f1657f = networkCallback;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.f1656e = connectivityManager;
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
        NetworkInfo activeNetworkInfo = this.f1656e.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        this.f1655d = activeNetworkInfo.getType() != 0 ? 2 : 1;
    }

    /* renamed from: a */
    public final int m1258a() {
        return this.f1655d;
    }

    /* renamed from: b */
    public final void m1259b() {
        this.f1656e.unregisterNetworkCallback(this.f1657f);
    }
}
