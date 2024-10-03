package com.xsdk.ab_firstbase.statisics.util;

import android.app.Application;
import android.content.Context;

/* loaded from: classes3.dex */
public class ContextHolder {
    private static Application applicationContext;

    public static void init(Application application) {
        applicationContext = application;
    }

    public static Context getContext() {
        if (applicationContext == null) {
            try {
                Application application = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
                if (application != null) {
                    applicationContext = application;
                    return application;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Application application2 = (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication", new Class[0]).invoke(null, null);
                if (application2 != null) {
                    applicationContext = application2;
                    return application2;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return applicationContext;
    }
}
