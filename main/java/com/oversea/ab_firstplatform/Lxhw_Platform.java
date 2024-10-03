package com.oversea.ab_firstplatform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.oversea.ab_firstarea.net.model.ProductDetail;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ADCallback;
import com.oversea.ab_firstplatform.init.Lxhw_AccountUpgradeCallback;
import com.oversea.ab_firstplatform.init.Lxhw_BackServiceCallback;
import com.oversea.ab_firstplatform.init.Lxhw_DownHeadCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_GiftGetCallback;
import com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.init.Lxhw_UploadHeadCallback;
import com.oversea.ab_firstplatform.model.SkuProductListData;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.net.okhttp3.CallCode;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class Lxhw_Platform {
    private static Lxhw_Platform mInstance;
    public final String TAG = getClass().toString();
    public boolean isAPPinit = false;
    public Lxhw_UserExtraData userExtraData = new Lxhw_UserExtraData();
    public int fastClickTime = CallCode.HTTP_MULT_CHOICE;

    public String getSessionId(Context context) {
        return "";
    }

    public String getUid(Context context) {
        return "";
    }

    public void setJumpMainActivity(Intent intent) {
    }

    public static Lxhw_Platform getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_Platform();
        }
        return mInstance;
    }

    public void setLogDeBug(boolean z) {
        LLog.setIsOpenLog(z);
    }

    public void initSdk(Activity activity, Lxhw_SDKCallBack lxhw_SDKCallBack) {
        if (activity == null) {
            LLog.e_noControl("initSdk:activity is null");
        } else {
            Lxhw_ConnectSDK.getInstance().initSdk(activity, lxhw_SDKCallBack);
        }
    }

    public void addAccountUpgradeListener(Lxhw_AccountUpgradeCallback lxhw_AccountUpgradeCallback) {
        Lxhw_ConnectSDK.getInstance().addAccountUpgradeListener(lxhw_AccountUpgradeCallback);
    }

    public void addBackServiceListener(Lxhw_BackServiceCallback lxhw_BackServiceCallback) {
        Lxhw_ConnectSDK.getInstance().addBackServiceListener(lxhw_BackServiceCallback);
    }

    public void addDownHeadListener(Lxhw_DownHeadCallback lxhw_DownHeadCallback) {
        Lxhw_ConnectSDK.getInstance().addDownHeadListener(lxhw_DownHeadCallback);
    }

    public void addUpLoadHeadListener(Lxhw_UploadHeadCallback lxhw_UploadHeadCallback) {
        Lxhw_ConnectSDK.getInstance().addUpLoadHeadListener(lxhw_UploadHeadCallback);
    }

    public void addADListener(Lxhw_ADCallback lxhw_ADCallback) {
        Lxhw_ConnectSDK.getInstance().addADListener(lxhw_ADCallback);
    }

    public void addGiftGetListener(Lxhw_GiftGetCallback lxhw_GiftGetCallback) {
        Lxhw_ConnectSDK.getInstance().addGiftGetListener(lxhw_GiftGetCallback);
    }

    public void login(Activity activity) {
        if (activity == null) {
            LLog.e_noControl("login:activity is null");
        } else {
            Lxhw_ConnectSDK.getInstance().login(activity);
        }
    }

    public void switchLogin(Activity activity) {
        Lxhw_ConnectSDK.getInstance().switchLogin(activity);
    }

    public void showAccountCenter(Activity activity) {
        Lxhw_ConnectSDK.getInstance().showAccountCenter(activity);
    }

    public void showCustomerService(Activity activity) {
        Lxhw_ConnectSDK.getInstance().showCustomerService(activity);
    }

    public void showRewardedAd(Activity activity, AdCallback adCallback) {
        Lxhw_ConnectSDK.getInstance().showRewardedAd(activity, adCallback);
    }

    public void logOut(Activity activity) {
        if (activity == null) {
            LLog.e_noControl("login:activity is null");
        } else {
            Lxhw_ConnectSDK.getInstance().logOut(activity);
        }
    }

    public void openGpShop(Activity activity) {
        if (IsFastClickUtils.isFastClick(this.fastClickTime)) {
            Log.e(this.TAG, "多次点击，返回...................");
        } else {
            Lxhw_ConnectSDK.getInstance().openGpShop(activity);
        }
    }

    public void openGooglePlayInappReview(Activity activity) {
        Lxhw_ConnectSDK.getInstance().openGooglePlayInappReview(activity);
    }

    public void dianzan(Activity activity) {
        if (IsFastClickUtils.isFastClick(this.fastClickTime)) {
            Log.e(this.TAG, "多次点击，返回...................");
        } else {
            Lxhw_ConnectSDK.getInstance().dianzan(activity);
        }
    }

    public void fbShare(Activity activity) {
        if (IsFastClickUtils.isFastClick(this.fastClickTime)) {
            Log.e(this.TAG, "多次点击，返回...................");
        } else {
            Lxhw_ConnectSDK.getInstance().fbShare(activity);
        }
    }

    public void setGameLanguage(Activity activity, int i) {
        Lxhw_ConnectSDK.getInstance().setGameLanguage(activity, i);
    }

    public List<ProductDetail> getProductDetailList() {
        return SkuProductListData.getInstance().getProductDetailList();
    }

    public void uploadHead(Activity activity) {
        if (IsFastClickUtils.isFastClick(this.fastClickTime)) {
            Log.e(this.TAG, "多次点击，返回...................");
        } else {
            Lxhw_ConnectSDK.getInstance().uploadHead(activity);
        }
    }

    public void downHead(Activity activity) {
        Lxhw_ConnectSDK.getInstance().downHead(activity);
    }

    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        if (activity == null) {
            LLog.e_noControl("login:activity is null");
        } else {
            Lxhw_ConnectSDK.getInstance().exit(activity, lxhw_ExitCallback);
        }
    }

    public void submitExtendData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
        if (activity == null || lxhw_UserExtraData == null) {
            LLog.e_noControl("submit activity or extraData null");
            ToastUtils.toastLongShow(activity, "activity or extraData null");
            return;
        }
        Lxhw_ConnectSDK.getInstance().submitExtendData(activity, lxhw_UserExtraData);
        if (TextUtils.isEmpty(lxhw_UserExtraData.getRoleID())) {
            Log.e(this.TAG, "roleid empty");
        }
        if (TextUtils.isEmpty(lxhw_UserExtraData.getRoleName())) {
            Log.e(this.TAG, "roleName empty");
        }
        if (TextUtils.isEmpty(lxhw_UserExtraData.getServerId())) {
            Log.e(this.TAG, "serverId empty");
        }
        if (TextUtils.isEmpty(lxhw_UserExtraData.getServerName())) {
            Log.e(this.TAG, "serverName empty");
        }
    }

    public void openActivityWithWeb(Activity activity, String str) {
        if (IsFastClickUtils.isFastClick(this.fastClickTime)) {
            Log.e(this.TAG, "多次点击，返回...................");
        } else if (activity == null || TextUtils.isEmpty(str)) {
            LLog.e_noControl("openActivityWithWeb activity or url null");
            ToastUtils.toastLongShow(activity, "activity or url null");
        } else {
            Lxhw_ConnectSDK.getInstance().openActivityWithWeb(activity, str);
        }
    }

    public void openWebView(Activity activity, String str, String str2) {
        openActivityWithWeb(activity, str);
    }

    public void openDiscord(Activity activity) {
        Lxhw_ConnectSDK.getInstance().openDiscord(activity);
    }

    public void pay(Activity activity, Lxhw_PayParams lxhw_PayParams) {
        if (IsFastClickUtils.isFastClick(this.fastClickTime)) {
            Log.e(this.TAG, "多次点击，返回...................");
            return;
        }
        if (activity == null || lxhw_PayParams == null) {
            LLog.e_noControl("pay activity or params null");
            ToastUtils.toastLongShow(activity, "activity or extraData null");
            return;
        }
        if (TextUtils.isEmpty(lxhw_PayParams.getCpOrderID())) {
            ToastUtils.toastLongShow(activity, "cpOrderID empty");
            return;
        }
        if (TextUtils.isEmpty(lxhw_PayParams.getProductId())) {
            ToastUtils.toastLongShow(activity, "productId empty");
            return;
        }
        if (TextUtils.isEmpty(lxhw_PayParams.getRoleId())) {
            ToastUtils.toastLongShow(activity, "roleId empty");
            return;
        }
        if (TextUtils.isEmpty(lxhw_PayParams.getRoleName())) {
            ToastUtils.toastLongShow(activity, "roleName empty");
            return;
        }
        if (TextUtils.isEmpty(lxhw_PayParams.getServerId())) {
            ToastUtils.toastLongShow(activity, "serverId empty");
        } else if (TextUtils.isEmpty(lxhw_PayParams.getProduct_type())) {
            ToastUtils.toastLongShow(activity, "product_type empty");
        } else {
            Lxhw_ConnectSDK.getInstance().pay(activity, lxhw_PayParams);
        }
    }

    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        Lxhw_ConnectSDK.getInstance().onTrackEventAF(context, str, map);
    }

    public void setScreenOrientation(int i) {
        Lxhw_ConnectSDK.getInstance().setScreenOrientation(i);
    }

    public void onStart(Activity activity) {
        Lxhw_ConnectSDK.getInstance().onStart(activity);
    }

    public void onRestart(Activity activity) {
        Lxhw_ConnectSDK.getInstance().onRestart(activity);
    }

    public void onPause(Activity activity) {
        Lxhw_ConnectSDK.getInstance().onPause(activity);
    }

    public void onResume(Activity activity) {
        Lxhw_ConnectSDK.getInstance().onResume(activity);
    }

    public void onNewIntent(Intent intent) {
        Lxhw_ConnectSDK.getInstance().onNewIntent(intent);
    }

    public void onStop(Activity activity) {
        Lxhw_ConnectSDK.getInstance().onStop(activity);
    }

    public void onDestroy(Activity activity) {
        Lxhw_ConnectSDK.getInstance().onDestroy(activity);
    }

    public void onConfigurationChanged(Configuration configuration) {
        Lxhw_ConnectSDK.getInstance().onConfigurationChanged(configuration);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Lxhw_ConnectSDK.getInstance().onSaveInstanceState(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Lxhw_ConnectSDK.getInstance().onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Lxhw_ConnectSDK.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onWindowFocusChanged(boolean z) {
        Lxhw_ConnectSDK.getInstance().onWindowFocusChanged(z);
    }

    public void showSurveyViewController() {
        Lxhw_ConnectSDK.getInstance().showSurveyViewController();
    }

    public void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback) {
        Lxhw_ConnectSDK.getInstance().getTranslateResult(str, str2, str3, lxhw_TranslateCallback);
    }

    public void showDeleteAccount(Activity activity) {
        Lxhw_ConnectSDK.getInstance().deleteAccount(activity);
    }
}
