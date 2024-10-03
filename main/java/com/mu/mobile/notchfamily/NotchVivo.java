package com.mu.mobile.notchfamily;

import android.util.Log;

/* loaded from: classes2.dex */
public class NotchVivo extends NotchBase {
    public static final int VIVO_FILLET = 8;
    public static final int VIVO_NOTCH = 32;

    public NotchVivo() {
        Init();
    }

    protected void Init() {
        Class<?> loadClass;
        try {
            loadClass = this._context.getClassLoader().loadClass("android.util.FtFeature");
        } catch (Exception e) {
            Log.e("Notch", e.toString());
        }
        if (loadClass == null) {
            this._notchHeight = 0;
            return;
        }
        this._hasNotch = ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
        if (this._hasNotch) {
            this._notchHeight = 32;
        }
        this._isInit = true;
    }
}
