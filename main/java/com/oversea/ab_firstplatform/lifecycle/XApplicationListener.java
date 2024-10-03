package com.oversea.ab_firstplatform.lifecycle;

import android.content.Context;
import android.content.res.Configuration;

/* loaded from: classes2.dex */
public interface XApplicationListener {
    void onTwProxyAttachBaseContext(Context context);

    void onTwProxyConfigurationChanged(Configuration configuration);

    void onTwProxyCreate();
}
