package com.oversea.ab_firstplatform.lifecycle;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import com.oversea.ab_firstarea.utils.ProcessUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;

/* loaded from: classes2.dex */
public class XApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.i("X_LOG", "application onAppCreate");
        if (ProcessUtil.isMainProcess(this)) {
            Lxhw_XSDK.getInstance().onAppCreate(this);
        }
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        if (ProcessUtil.isMainProcess(context)) {
            Log.i("X_LOG", "application attachBaseContext");
            Lxhw_XSDK.getInstance().onAppAttachBaseContext(context);
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (ProcessUtil.isMainProcess(this)) {
            Log.i("X_LOG", "application onConfigurationChanged");
            Lxhw_XSDK.getInstance().onAppConfigurationChanged(configuration);
        }
    }
}
