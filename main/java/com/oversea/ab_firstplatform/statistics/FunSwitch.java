package com.oversea.ab_firstplatform.statistics;

/* loaded from: classes2.dex */
public class FunSwitch {
    private static FunSwitch mInstance;
    private boolean isOpenFloatView;

    public static FunSwitch getInstance() {
        if (mInstance == null) {
            mInstance = new FunSwitch();
        }
        return mInstance;
    }

    public boolean isOpenFloatView() {
        return this.isOpenFloatView;
    }

    public void setOpenFloatView(int i) {
        if (i == 1) {
            this.isOpenFloatView = true;
        } else {
            this.isOpenFloatView = false;
        }
    }
}
