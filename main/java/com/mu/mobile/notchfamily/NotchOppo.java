package com.mu.mobile.notchfamily;

/* loaded from: classes2.dex */
public class NotchOppo extends NotchBase {
    public NotchOppo() {
        Init();
    }

    protected void Init() {
        this._hasNotch = this._context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        if (this._hasNotch) {
            this._notchHeight = 80;
        }
        this._isInit = true;
    }
}
