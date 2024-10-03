package com.mu.mobile.notchfamily;

import android.util.Log;

/* loaded from: classes2.dex */
public class NotchXiaoMi extends NotchBase {
    public NotchXiaoMi() {
        Init();
    }

    protected void Init() {
        String str;
        int identifier;
        try {
            Class<?> loadClass = this._context.getClassLoader().loadClass("android.os.SystemProperties");
            str = (String) loadClass.getMethod("get", String.class).invoke(loadClass, "ro.miui.notch");
        } catch (Exception e) {
            Log.e("Notch", e.toString());
        }
        if (str == null) {
            this._notchHeight = 0;
            return;
        }
        this._hasNotch = str.compareTo("1") == 0;
        if (this._hasNotch && (identifier = this._context.getResources().getIdentifier("notch_height", "dimen", "android")) > 0) {
            this._notchHeight = this._context.getResources().getDimensionPixelSize(identifier);
        }
        this._isInit = true;
    }
}
