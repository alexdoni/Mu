package com.mu.mobile.notchfamily;

import android.app.Activity;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/* loaded from: classes2.dex */
public class NotchAndroidP extends NotchBase {
    public NotchAndroidP() {
        Init();
    }

    protected void Init() {
        getNotchInfo();
    }

    protected void getNotchInfo() {
        final View decorView = ((Activity) this._context).getWindow().getDecorView();
        decorView.post(new Runnable() { // from class: com.mu.mobile.notchfamily.NotchAndroidP.1
            @Override // java.lang.Runnable
            public void run() {
                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                if (displayCutout != null) {
                    NotchAndroidP.this._hasNotch = true;
                    NotchAndroidP.this._notchHeight = displayCutout.getSafeInsetTop();
                }
                NotchAndroidP.this._isInit = true;
            }
        });
    }

    public void DisplayFullScreen() {
        Window window = ((Activity) this._context).getWindow();
        window.addFlags(67108864);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        window.setAttributes(attributes);
    }
}
