package com.oversea.ab_firstplatform.imp;

import android.app.Activity;
import android.content.Context;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_User;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import java.util.Map;

/* loaded from: classes2.dex */
public class XSimpleDefaultUser implements Lxhw_User {
    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void deleteAccount(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void dianzan(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void downHead(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void extraFun() {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void fbShare(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void initApplication(Context context) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void initSDK() {
    }

    @Override // com.oversea.ab_firstplatform.plugin.XPlugin
    public boolean isSupportMethod(String str) {
        return false;
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void login(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void logout(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openActivityWithWeb(Activity activity, String str) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openDiscord(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openGooglePlayInappReview(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void openGpShop(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void pay(Activity activity, Lxhw_PayParams lxhw_PayParams) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void setGameLanguage(Activity activity, int i) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showAccountCenter(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showCustomerService(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showPriorityAD(Activity activity, String str) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showRewardedAd(Activity activity, AdCallback adCallback) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void showSurveyViewController() {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void submitExtraData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void switchLogin(Activity activity) {
    }

    @Override // com.oversea.ab_firstplatform.plugin.user.Lxhw_User
    public void uploadHead(Activity activity) {
    }
}
