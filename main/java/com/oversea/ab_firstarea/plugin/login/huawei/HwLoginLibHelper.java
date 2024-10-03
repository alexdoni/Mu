package com.oversea.ab_firstarea.plugin.login.huawei;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes.dex */
public class HwLoginLibHelper {
    private static volatile HwLoginLibHelper hwLoginLibHelper;
    private static AbstractHwLoginManager mAbstractHwLoginManager;

    public static HwLoginLibHelper getInstance() {
        if (hwLoginLibHelper == null) {
            synchronized (HwLoginLibHelper.class) {
                if (hwLoginLibHelper == null) {
                    hwLoginLibHelper = new HwLoginLibHelper();
                    initClass();
                }
            }
        }
        return hwLoginLibHelper;
    }

    private static void initClass() {
        try {
            mAbstractHwLoginManager = (AbstractHwLoginManager) Class.forName("com.ff.sdk.ffoversea_huaweilogin.HuaweiLoginManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void login(Activity activity, HwLoginCallback hwLoginCallback) {
        AbstractHwLoginManager abstractHwLoginManager = mAbstractHwLoginManager;
        if (abstractHwLoginManager != null) {
            abstractHwLoginManager.login(activity, hwLoginCallback);
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AbstractHwLoginManager abstractHwLoginManager = mAbstractHwLoginManager;
        if (abstractHwLoginManager != null) {
            abstractHwLoginManager.onActivityResult(i, i2, intent);
        }
    }

    public void logout(HwLogoutCallback hwLogoutCallback) {
        AbstractHwLoginManager abstractHwLoginManager = mAbstractHwLoginManager;
        if (abstractHwLoginManager != null) {
            abstractHwLoginManager.logout(hwLogoutCallback);
        }
    }
}
