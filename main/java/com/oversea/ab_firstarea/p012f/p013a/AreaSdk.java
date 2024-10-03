package com.oversea.ab_firstarea.p012f.p013a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.oversea.ab_firstarea.haiwai.AppsFlyerControl;
import com.oversea.ab_firstarea.haiwai.FaceBookControl;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.haiwai.GooglePlayControl;
import com.oversea.ab_firstarea.haiwai.LoginGooglePlay;
import com.oversea.ab_firstarea.net.domainN.DomainManager;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.lifecycle.XActivityCallback;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import java.util.Map;

/* loaded from: classes.dex */
public class AreaSdk extends AbstractC2392c {
    private static AreaSdk mInstance;
    boolean iDealFirst = false;

    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    protected void extraFun() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void showPriorityAD(Activity activity, String str) {
    }

    public static AreaSdk getInstance() {
        if (mInstance == null) {
            mInstance = new AreaSdk();
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void init() {
        Lxhw_XSDK.getInstance().setActivityCallback(new XActivityCallback() { // from class: com.oversea.ab_firstarea.f.a.AreaSdk.1
            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onBackPressed() {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onConfigurationChanged(Configuration configuration) {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onCreate(Bundle bundle) {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onNewIntent(Intent intent) {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onPause() {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onRestart() {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onSaveInstanceState(Bundle bundle) {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onStart() {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onStop() {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onWindowFocusChanged(boolean z) {
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onActivityResult(int i, int i2, Intent intent) {
                Lxhw_AreaPlatform.getInstance().onActivityResult(i, i2, intent);
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onResume() {
                Lxhw_AreaPlatform.getInstance().onResume();
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onDestroy() {
                Lxhw_AreaPlatform.getInstance().onDestroy();
            }

            @Override // com.oversea.ab_firstplatform.lifecycle.XActivityCallback
            public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                Lxhw_AreaPlatform.getInstance().onRequestPermissionsResult(i, strArr, iArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void initApplication(Context context) {
        FirebaseControl.getInstance().initFireBase(context);
        AppsFlyerControl.getInstance().init(Lxhw_XSDK.getInstance().getApplication(), context);
        PayLibHelper.getInstance().loadHuaweiAGCConfig(context);
        if (ImageUtils.getStringKeyForBoolValue(context, Constants.SDK_IS_FIRST_START).booleanValue()) {
            Lxhw_AreaPlatform.getInstance().is_first_start = false;
        } else {
            ImageUtils.setSharePreferences(context, Constants.SDK_IS_FIRST_START, true);
        }
        GooglePlayControl.getInstance().getGAid();
        Lxhw_AreaPlatform.getInstance().setProjectType(Lxhw_AppInfoDecorator.getSPEnvironmentType());
        Lxhw_AreaPlatform.getInstance().setRecType(Lxhw_AppInfoDecorator.getRECType());
        DomainManager.getInstance().init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void initSDK() {
        if (!this.iDealFirst) {
            this.iDealFirst = true;
            FaceBookControl.getInstance().initFaceBook(Lxhw_XSDK.getInstance().getContext());
            LoginGooglePlay.getInstent().init(Lxhw_XSDK.getInstance().getContext());
            PayLibHelper.getInstance().init(Lxhw_XSDK.getInstance().getContext());
            Lxhw_AreaPlatform.getInstance().onCreate(Lxhw_XSDK.getInstance().getContext());
        }
        Lxhw_AreaPlatform.getInstance().initSDK();
        FirebaseControl.getInstance().getMsgToken();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void login(Activity activity) {
        Lxhw_AreaPlatform.getInstance().login(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void switchLogin(Activity activity) {
        Lxhw_AreaPlatform.getInstance().switchLogin(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void showAccountCenter(Activity activity) {
        Lxhw_AreaPlatform.getInstance().showAccountCenter(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void showCustomerService(Activity activity) {
        Lxhw_AreaPlatform.getInstance().showCustomerService(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void logout(Activity activity) {
        Lxhw_AreaPlatform.getInstance().logout(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void showSurveyViewController() {
        Lxhw_AreaPlatform.getInstance().showSurveyViewController();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback) {
        Lxhw_AreaPlatform.getInstance().getTranslateResult(str, str2, str3, lxhw_TranslateCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        Lxhw_AreaPlatform.getInstance().onTrackEventAF(context, str, map);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void openGpShop(Activity activity) {
        Lxhw_AreaPlatform.getInstance().openGpShop(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void openGooglePlayInappReview(Activity activity) {
        Lxhw_AreaPlatform.getInstance().openGooglePlayInappReview(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void dianzan(Activity activity) {
        Lxhw_AreaPlatform.getInstance().dianzan(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void fbShare(Activity activity) {
        Lxhw_AreaPlatform.getInstance().fbShare(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void setGameLanguage(Activity activity, int i) {
        Lxhw_AreaPlatform.getInstance().setGameLanguage(activity, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void uploadHead(Activity activity) {
        Lxhw_AreaPlatform.getInstance().uploadHead(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void downHead(Activity activity) {
        Lxhw_AreaPlatform.getInstance().downHead(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        Lxhw_AreaPlatform.getInstance().sdkExit(activity, lxhw_ExitCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void pay(Activity activity, Lxhw_PayParams lxhw_PayParams) {
        Lxhw_AreaPlatform.getInstance().pay(activity, lxhw_PayParams);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void submitExtraData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
        Lxhw_AreaPlatform.getInstance().submitExtendData(activity, lxhw_UserExtraData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void deleteAccount(Activity activity) {
        Lxhw_AreaPlatform.getInstance().deleteAccount(activity);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void showRewardedAd(Activity activity, AdCallback adCallback) {
        Lxhw_AreaPlatform.getInstance().showRewardedAd(activity, adCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void openActivityWithWeb(Activity activity, String str) {
        Lxhw_AreaPlatform.getInstance().openActivityWithWeb(activity, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c
    public void openDiscord(Activity activity) {
        Lxhw_AreaPlatform.getInstance().openDiscord(activity);
    }
}
