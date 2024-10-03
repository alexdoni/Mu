package com.oversea.ab_firstarea.haiwai;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerConsent;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.Map;

/* loaded from: classes.dex */
public class AppsFlyerControl {
    private static AppsFlyerControl mInstance = null;
    public static String track_appsflyer = "";

    public static AppsFlyerControl getInstance() {
        if (mInstance == null) {
            mInstance = new AppsFlyerControl();
        }
        return mInstance;
    }

    public void init(Application application, Context context) {
        AppsFlyerConversionListener appsFlyerConversionListener = new AppsFlyerConversionListener() { // from class: com.oversea.ab_firstarea.haiwai.AppsFlyerControl.1
            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onConversionDataSuccess(Map<String, Object> map) {
                for (String str : map.keySet()) {
                    LLog.i_noControl("AppsFlyerLib attribute: " + str + " = " + map.get(str));
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onConversionDataFail(String str) {
                LLog.e_noControl("AppsFlyerLib error getting conversion data: " + str);
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAppOpenAttribution(Map<String, String> map) {
                for (String str : map.keySet()) {
                    LLog.i_noControl("AppsFlyerLib attribute: " + str + " = " + map.get(str));
                }
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public void onAttributionFailure(String str) {
                LLog.e_noControl("AppsFlyerLib error onAttributionFailure : " + str);
            }
        };
        AppsFlyerLib.getInstance().setDebugLog(false);
        AppsFlyerLib.getInstance().init(Lxhw_AppInfoDecorator.getAppsf_dev_key(), appsFlyerConversionListener, application);
        AppsFlyerLib.getInstance().setAndroidIdData(Util.getAndroidID(application));
        LLog.i_noControl("AppsFlyerLib version : " + AppsFlyerLib.getInstance().getSdkVersion());
    }

    public void updateServerUninstallToken(String str) {
        AppsFlyerLib.getInstance().updateServerUninstallToken(Lxhw_XSDK.getInstance().getApplication(), str);
    }

    public String getTrack_appsflyerId(Context context) {
        if (TextUtils.isEmpty(track_appsflyer) && AppsFlyerLib.getInstance() != null) {
            setTrack_appsflyerUid(context);
        }
        if (TextUtils.isEmpty(track_appsflyer)) {
            track_appsflyer = "";
        }
        return track_appsflyer;
    }

    public void setTrack_appsflyerUid(Context context) {
        track_appsflyer = AppsFlyerLib.getInstance().getAppsFlyerUID(context);
        AppsFlyerLib.getInstance().setCustomerUserId(track_appsflyer);
    }

    public void onTrackEvent(Context context, String str, Map<String, Object> map) {
        AppsFlyerLib.getInstance().logEvent(context, str, map);
    }

    public void setConsentAndStart(Context context, boolean z, boolean z2, boolean z3) {
        AppsFlyerConsent forNonGDPRUser;
        if (z) {
            forNonGDPRUser = AppsFlyerConsent.forGDPRUser(z2, z3);
        } else {
            forNonGDPRUser = AppsFlyerConsent.forNonGDPRUser();
        }
        AppsFlyerLib.getInstance().setConsentData(forNonGDPRUser);
        AppsFlyerLib.getInstance().start(context);
    }
}
