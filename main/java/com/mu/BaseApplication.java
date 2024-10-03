package com.mu;

import android.app.Application;
import com.mu.logtool.CrashHandler;

/* loaded from: classes2.dex */
public abstract class BaseApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
