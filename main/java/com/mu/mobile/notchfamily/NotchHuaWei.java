package com.mu.mobile.notchfamily;

import android.util.Log;

/* loaded from: classes2.dex */
public class NotchHuaWei extends NotchBase {
    public NotchHuaWei() {
        Init();
    }

    protected void Init() {
        Class<?> loadClass;
        int[] iArr;
        try {
            loadClass = this._context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
        } catch (Exception e) {
            Log.e("Notch", e.toString());
        }
        if (loadClass == null) {
            this._notchHeight = 0;
            return;
        }
        this._hasNotch = ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
        if (this._hasNotch && (iArr = (int[]) loadClass.getMethod("getNotchSize", new Class[0]).invoke(loadClass, new Object[0])) != null && iArr.length == 2) {
            this._notchHeight = iArr[1];
        }
        this._isInit = true;
    }
}
