package com.oversea.ab_firstarea.p012f.p013a;

import android.app.Activity;
import android.content.Context;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* renamed from: com.oversea.ab_firstarea.f.a.a */
/* loaded from: classes.dex */
public class C2391a extends Lxhw_UserAdapter {
    @Override // com.oversea.ab_firstplatform.plugin.XPlugin
    public boolean isSupportMethod(String str) {
        return false;
    }

    public C2391a() {
        AreaSdk.getInstance().init();
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void initApplication(Context context) {
        AreaSdk.getInstance().initApplication(context);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void initSDK() {
        AreaSdk.getInstance().initSDK();
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void login(Activity activity) {
        AreaSdk.getInstance().login(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void logout(Activity activity) {
        AreaSdk.getInstance().logout(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showAccountCenter(Activity activity) {
        AreaSdk.getInstance().showAccountCenter(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showCustomerService(Activity activity) {
        AreaSdk.getInstance().showCustomerService(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void switchLogin(Activity activity) {
        AreaSdk.getInstance().switchLogin(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void pay(Activity activity, Lxhw_PayParams lxhw_PayParams) {
        AreaSdk.getInstance().pay(activity, lxhw_PayParams);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void submitExtraData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
        AreaSdk.getInstance().submitExtraData(activity, lxhw_UserExtraData);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        AreaSdk.getInstance().onTrackEventAF(context, str, map);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openGpShop(Activity activity) {
        AreaSdk.getInstance().openGpShop(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openGooglePlayInappReview(Activity activity) {
        AreaSdk.getInstance().openGooglePlayInappReview(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void dianzan(Activity activity) {
        AreaSdk.getInstance().dianzan(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void fbShare(Activity activity) {
        AreaSdk.getInstance().fbShare(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showPriorityAD(Activity activity, String str) {
        AreaSdk.getInstance().showPriorityAD(activity, str);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void setGameLanguage(Activity activity, int i) {
        AreaSdk.getInstance().setGameLanguage(activity, i);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void uploadHead(Activity activity) {
        AreaSdk.getInstance().uploadHead(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void downHead(Activity activity) {
        AreaSdk.getInstance().downHead(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        AreaSdk.getInstance().exit(activity, lxhw_ExitCallback);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void extraFun() {
        LLog.v_noControl("a extraFun");
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openActivityWithWeb(Activity activity, String str) {
        AreaSdk.getInstance().openActivityWithWeb(activity, str);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openDiscord(Activity activity) {
        AreaSdk.getInstance().openDiscord(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showSurveyViewController() {
        AreaSdk.getInstance().showSurveyViewController();
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback) {
        AreaSdk.getInstance().getTranslateResult(str, str2, str3, lxhw_TranslateCallback);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void deleteAccount(Activity activity) {
        AreaSdk.getInstance().deleteAccount(activity);
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_UserAdapter, com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showRewardedAd(Activity activity, AdCallback adCallback) {
        AreaSdk.getInstance().showRewardedAd(activity, adCallback);
    }
}
