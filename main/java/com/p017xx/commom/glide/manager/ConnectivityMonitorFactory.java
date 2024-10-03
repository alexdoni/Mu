package com.p017xx.commom.glide.manager;

import android.content.Context;
import com.p017xx.commom.glide.manager.ConnectivityMonitor;

/* loaded from: classes3.dex */
public interface ConnectivityMonitorFactory {
    ConnectivityMonitor build(Context context, ConnectivityMonitor.ConnectivityListener connectivityListener);
}
