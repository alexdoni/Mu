package com.xsdk.ab_firstbase.statisics.util;

import android.content.Intent;

/* loaded from: classes3.dex */
public class BaseUtils {
    private static BaseUtils mInstance;
    private Intent mIntent;
    private int screen_orientation = 0;

    public static BaseUtils getInstance() {
        if (mInstance == null) {
            mInstance = new BaseUtils();
        }
        return mInstance;
    }

    public int getScreen_orientation() {
        return this.screen_orientation;
    }

    public void setScreen_orientation(int i) {
        this.screen_orientation = i;
    }

    public Intent getmIntent() {
        return this.mIntent;
    }

    public void setmIntent(Intent intent) {
        if (intent != null) {
            this.mIntent = intent;
            LLog.v_noControl("setintent no null");
        } else {
            LLog.v_noControl("setintent  null");
        }
    }
}
