package com.oversea.ab_firstarea.plugin.ad;

import android.app.Activity;

/* loaded from: classes.dex */
public class AdmobLibHelper {
    private static volatile AdmobLibHelper admobLibHelper;
    private static AbstractAdmobManager mAbstractAdmobManager;

    public static AdmobLibHelper getInstance() {
        if (admobLibHelper == null) {
            synchronized (AdmobLibHelper.class) {
                if (admobLibHelper == null) {
                    admobLibHelper = new AdmobLibHelper();
                    initClass();
                }
            }
        }
        return admobLibHelper;
    }

    private static void initClass() {
        try {
            mAbstractAdmobManager = (AbstractAdmobManager) Class.forName("com.ff.sdk.admob.AdmobManager").newInstance();
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
