package com.ffandroid.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.oversea.ab_firstarea.net.model.ProductDetail;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FF_Platform {
    private static FF_Platform instance;

    public void showPriorityAD(Activity activity, String str) {
    }

    public static FF_Platform getInstance() {
        if (instance == null) {
            instance = new FF_Platform();
        }
        return instance;
    }

    public void setLogDeBug(boolean z) {
        Lxhw_Platform.getInstance().setLogDeBug(z);
    }

    public void initSdk(Activity activity, FF_SDKCallBack fF_SDKCallBack) {
        Lxhw_Platform.getInstance().initSdk(activity, fF_SDKCallBack);
    }

    public void addAccountUpgradeListener(FF_AccountUpgradeCallback fF_AccountUpgradeCallback) {
        Lxhw_Platform.getInstance().addAccountUpgradeListener(fF_AccountUpgradeCallback);
    }

    public void addDownHeadListener(FF_DownHeadCallback fF_DownHeadCallback) {
        Lxhw_Platform.getInstance().addDownHeadListener(fF_DownHeadCallback);
    }

    public void addUpLoadHeadListener(FF_UploadHeadCallback fF_UploadHeadCallback) {
        Lxhw_Platform.getInstance().addUpLoadHeadListener(fF_UploadHeadCallback);
    }

    public void addADListener(FF_ADCallback fF_ADCallback) {
        Lxhw_Platform.getInstance().addADListener(fF_ADCallback);
    }

    public void login(Activity activity) {
        Lxhw_Platform.getInstance().login(activity);
    }

    public void switchLogin(Activity activity) {
        Lxhw_Platform.getInstance().switchLogin(activity);
    }

    public void showAccountCenter(Activity activity) {
        Lxhw_Platform.getInstance().showAccountCenter(activity);
    }

    public void showCustomerService(Activity activity) {
        Lxhw_Platform.getInstance().showCustomerService(activity);
    }

    public void openGpShop(Activity activity) {
        Lxhw_Platform.getInstance().openGpShop(activity);
    }

    public void openGooglePlayInappReview(Activity activity) {
        Lxhw_Platform.getInstance().openGooglePlayInappReview(activity);
    }

    public void dianzan(Activity activity) {
        Lxhw_Platform.getInstance().dianzan(activity);
    }

    public void fbShare(Activity activity) {
        Lxhw_Platform.getInstance().fbShare(activity);
    }

    public void setGameLanguage(Activity activity, int i) {
        Lxhw_Platform.getInstance().setGameLanguage(activity, i);
    }

    public List<ProductDetail> getProductDetailList() {
        return Lxhw_Platform.getInstance().getProductDetailList();
    }

    public void uploadHead(Activity activity) {
        Lxhw_Platform.getInstance().uploadHead(activity);
    }

    public void downHead(Activity activity) {
        Lxhw_Platform.getInstance().downHead(activity);
    }

    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        Lxhw_Platform.getInstance().exit(activity, lxhw_ExitCallback);
    }

    public void submitExtendData(Activity activity, FF_UserExtraData fF_UserExtraData) {
        Lxhw_Platform.getInstance().submitExtendData(activity, fF_UserExtraData);
    }

    public void pay(Activity activity, FF_PayParams fF_PayParams) {
        Lxhw_Platform.getInstance().pay(activity, fF_PayParams);
    }

    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        Lxhw_Platform.getInstance().onTrackEventAF(context, str, map);
    }

    public void setScreenOrientation(int i) {
        Lxhw_Platform.getInstance().setScreenOrientation(i);
    }

    public void onStart(Activity activity) {
        Lxhw_Platform.getInstance().onStart(activity);
    }

    public void onRestart(Activity activity) {
        Lxhw_Platform.getInstance().onRestart(activity);
    }

    public void onPause(Activity activity) {
        Lxhw_Platform.getInstance().onPause(activity);
    }

    public void onResume(Activity activity) {
        Lxhw_Platform.getInstance().onResume(activity);
    }

    public void onNewIntent(Intent intent) {
        Lxhw_Platform.getInstance().onNewIntent(intent);
    }

    public void onStop(Activity activity) {
        Lxhw_Platform.getInstance().onStop(activity);
    }

    public void onDestroy(Activity activity) {
        Lxhw_Platform.getInstance().onDestroy(activity);
    }

    public void onConfigurationChanged(Configuration configuration) {
        Lxhw_Platform.getInstance().onConfigurationChanged(configuration);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Lxhw_Platform.getInstance().onSaveInstanceState(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Lxhw_Platform.getInstance().onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Lxhw_Platform.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onWindowFocusChanged(boolean z) {
        Lxhw_Platform.getInstance().onWindowFocusChanged(z);
    }

    public void showSurveyViewController() {
        Lxhw_Platform.getInstance().showSurveyViewController();
    }

    public void getTranslateResult(String str, String str2, String str3, FF_TranslateCallback fF_TranslateCallback) {
        Lxhw_Platform.getInstance().getTranslateResult(str, str2, str3, fF_TranslateCallback);
    }

    public void openWebView(Activity activity, String str, String str2) {
        Lxhw_Platform.getInstance().openWebView(activity, str, str2);
    }

    public void openDiscord(Activity activity) {
        Lxhw_Platform.getInstance().openDiscord(activity);
    }

    public static Context attachBaseContext(Context context, int i) {
        return MultiLanguageUtil.attachBaseContext(context, i);
    }

    public void showDeleteAccount(Activity activity) {
        Lxhw_Platform.getInstance().showDeleteAccount(activity);
    }
}
