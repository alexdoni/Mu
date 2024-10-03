package com.oversea.ab_firstarea.plugin.ad.max;

import android.app.Activity;
import com.oversea.ab_firstarea.plugin.ad.AbstractAdmobManager;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;

/* loaded from: classes.dex */
public class MaxLibHelper {
    private static AbstractAdmobManager mAbstractAdmobManager;
    private static volatile MaxLibHelper maxLibHelper;

    public static MaxLibHelper getInstance() {
        if (maxLibHelper == null) {
            synchronized (MaxLibHelper.class) {
                if (maxLibHelper == null) {
                    maxLibHelper = new MaxLibHelper();
                    initClass();
                }
            }
        }
        return maxLibHelper;
    }

    private static void initClass() {
        try {
            mAbstractAdmobManager = (AbstractAdmobManager) Class.forName("com.ff.sdk.max.AppLovinManager").newInstance();
        } catch (Exception unused) {
        }
    }

    public void init(Activity activity) {
        AbstractAdmobManager abstractAdmobManager = mAbstractAdmobManager;
        if (abstractAdmobManager != null) {
            abstractAdmobManager.init(activity);
        }
    }

    public void showRewardedAd(Activity activity, String str, AdCallback adCallback) {
        AbstractAdmobManager abstractAdmobManager = mAbstractAdmobManager;
        if (abstractAdmobManager != null) {
            abstractAdmobManager.showRewardedAd(activity, str, adCallback);
        }
    }

    public void openDebugView(Activity activity) {
        AbstractAdmobManager abstractAdmobManager = mAbstractAdmobManager;
        if (abstractAdmobManager != null) {
            abstractAdmobManager.openDebugView(activity);
        }
    }
}
