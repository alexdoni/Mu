package com.oversea.ab_firstplatform.plugin;

import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes2.dex */
public class XPluginFactory {
    private static XPluginFactory mInstance;
    private final String TAG = getClass().toString();

    public static XPluginFactory getInstance() {
        if (mInstance == null) {
            mInstance = new XPluginFactory();
        }
        return mInstance;
    }

    public Object initPlugin(int i) {
        Class<?> cls;
        try {
            if (i == 1) {
                try {
                    cls = Class.forName("com.oversea.ab_firstarea.f.a.a");
                } catch (ClassNotFoundException e) {
                    LLog.e_noControl("1fplugin init e" + e.toString());
                }
                return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            return cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
            LLog.e_noControl("2fplugin init e" + e2.toString());
            return null;
        }
        cls = null;
    }
}
