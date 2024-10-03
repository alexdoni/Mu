package com.oversea.ab_firstplatform;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ADCallback;
import com.oversea.ab_firstplatform.init.Lxhw_AccountUpgradeCallback;
import com.oversea.ab_firstplatform.init.Lxhw_BackServiceCallback;
import com.oversea.ab_firstplatform.init.Lxhw_DownHeadCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_GiftGetCallback;
import com.oversea.ab_firstplatform.init.Lxhw_SDKCallBack;
import com.oversea.ab_firstplatform.init.Lxhw_SDKListener;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.init.Lxhw_UploadHeadCallback;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_FUser;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.BaseUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import java.util.Map;

/* loaded from: classes2.dex */
public class Lxhw_ConnectSDK {
    private static Lxhw_ConnectSDK mInstance;
    private static Lxhw_SDKCallBack mSDKCallBack;
    private Lxhw_AccountUpgradeCallback accountUpgradeCallback;
    private Lxhw_ADCallback adCallback;
    private Lxhw_BackServiceCallback backServiceCallback;
    private Lxhw_DownHeadCallback downHeadCallback;
    private Lxhw_GiftGetCallback giftGetCallback;
    private Activity mAcivity;
    private Lxhw_UploadHeadCallback uploadHeadCallback;
    private final String TAG = getClass().toString();
    private Lxhw_SDKListener mSDKListener = new Lxhw_SDKListener() { // from class: com.oversea.ab_firstplatform.Lxhw_ConnectSDK.1
        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onResult(final int i, final String str) {
            if (i == 1) {
                Lxhw_Platform.getInstance().isAPPinit = true;
                Lxhw_ConnectSDK.mSDKCallBack.onInitResult(true);
                return;
            }
            if (i == 2) {
                Lxhw_Platform.getInstance().isAPPinit = false;
                Lxhw_ConnectSDK.mSDKCallBack.onInitResult(false);
                return;
            }
            if (i == 5) {
                Lxhw_AreaPlatform.getInstance().cleanInfo();
                Lxhw_ConnectSDK.mSDKCallBack.onSwitchAccount();
                return;
            }
            if (i == 6) {
                Lxhw_ConnectSDK.mSDKCallBack.onBindResult(true);
                return;
            }
            if (i == 7) {
                Lxhw_ConnectSDK.mSDKCallBack.onBindResult(false);
            } else {
                if (i != 8) {
                    switch (i) {
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                            Lxhw_ConnectSDK.this.mAcivity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstplatform.Lxhw_ConnectSDK.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Lxhw_ConnectSDK.mSDKCallBack.onPayResult(i, str);
                                }
                            });
                            return;
                        default:
                            return;
                    }
                }
                Lxhw_ConnectSDK.this.callbackAccountUpgrade();
            }
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onShareResult(boolean z) {
            Lxhw_ConnectSDK.mSDKCallBack.onShareResult(z);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onUploadHeadResult(boolean z, String str) {
            Lxhw_ConnectSDK.this.callbackUploadHead(z, str);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onDianzanResult(boolean z) {
            Lxhw_ConnectSDK.mSDKCallBack.onDianzanResult(z);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onPingFenResult(boolean z) {
            Lxhw_ConnectSDK.mSDKCallBack.onPingFenResult(z);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onDownHeadResult(boolean z, String str) {
            Lxhw_ConnectSDK.this.callbackDownHead(z, str);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onADResult(boolean z, String str) {
            Lxhw_ConnectSDK.this.callbackAD(z, str);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onGiftGetResult(int i, String str) {
            Lxhw_ConnectSDK.this.callbackGiftGet(i, str);
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onAuthResult(int i) {
            if (i != 0) {
                Lxhw_ConnectSDK.mSDKCallBack.onLoginFail();
            } else {
                ComConstants.CTRL_TYPE = 15;
                Lxhw_ConnectSDK.mSDKCallBack.onLoginResult(Lxhw_XUserInfo.getInstance());
            }
        }

        @Override // com.oversea.ab_firstplatform.init.Lxhw_SDKListener
        public void onBackServiceResult(Lxhw_XUserInfo lxhw_XUserInfo) {
            if (Lxhw_XUserInfo.getInstance() != null) {
                Lxhw_ConnectSDK.this.callBackServiceCallback(lxhw_XUserInfo);
            }
        }
    };

    public static Lxhw_ConnectSDK getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_ConnectSDK();
        }
        return mInstance;
    }

    public Lxhw_SDKCallBack getSDKCallBack() {
        return mSDKCallBack;
    }

    public void initSdk(Activity activity, Lxhw_SDKCallBack lxhw_SDKCallBack) {
        LLog.v_noControl("X_LOG", "initSdk");
        this.mAcivity = activity;
        mSDKCallBack = lxhw_SDKCallBack;
        Lxhw_XSDK.getInstance().setSDKListener(this.mSDKListener);
        Lxhw_XSDK.getInstance().init(activity);
        Lxhw_FUser.getInstance().initSDK();
    }

    public void addAccountUpgradeListener(Lxhw_AccountUpgradeCallback lxhw_AccountUpgradeCallback) {
        this.accountUpgradeCallback = lxhw_AccountUpgradeCallback;
    }

    public void addGiftGetListener(Lxhw_GiftGetCallback lxhw_GiftGetCallback) {
        this.giftGetCallback = lxhw_GiftGetCallback;
    }

    public void addBackServiceListener(Lxhw_BackServiceCallback lxhw_BackServiceCallback) {
        this.backServiceCallback = lxhw_BackServiceCallback;
    }

    public void addDownHeadListener(Lxhw_DownHeadCallback lxhw_DownHeadCallback) {
        this.downHeadCallback = lxhw_DownHeadCallback;
    }

    public void addUpLoadHeadListener(Lxhw_UploadHeadCallback lxhw_UploadHeadCallback) {
        this.uploadHeadCallback = lxhw_UploadHeadCallback;
    }

    public void addADListener(Lxhw_ADCallback lxhw_ADCallback) {
        this.adCallback = lxhw_ADCallback;
    }

    public void callbackAccountUpgrade() {
        Lxhw_AccountUpgradeCallback lxhw_AccountUpgradeCallback = this.accountUpgradeCallback;
        if (lxhw_AccountUpgradeCallback != null) {
            lxhw_AccountUpgradeCallback.result();
        } else {
            LLog.v_noControl("accountUpgradeCallback null");
        }
    }

    public void callbackGiftGet(int i, String str) {
        Lxhw_GiftGetCallback lxhw_GiftGetCallback = this.giftGetCallback;
        if (lxhw_GiftGetCallback != null) {
            lxhw_GiftGetCallback.onResult(i, str);
        } else {
            LLog.v_noControl("callbackGiftGet null");
        }
    }

    public void callbackDownHead(boolean z, String str) {
        Lxhw_DownHeadCallback lxhw_DownHeadCallback = this.downHeadCallback;
        if (lxhw_DownHeadCallback != null) {
            lxhw_DownHeadCallback.result(z, str);
        } else {
            LLog.v_noControl("downHeadCallback null");
        }
    }

    public void callbackUploadHead(boolean z, String str) {
        Lxhw_UploadHeadCallback lxhw_UploadHeadCallback = this.uploadHeadCallback;
        if (lxhw_UploadHeadCallback != null) {
            lxhw_UploadHeadCallback.result(z, str);
        } else {
            LLog.v_noControl("uploadHeadCallback null");
        }
    }

    public void callbackAD(boolean z, String str) {
        Lxhw_ADCallback lxhw_ADCallback = this.adCallback;
        if (lxhw_ADCallback != null) {
            lxhw_ADCallback.result(z, str);
        } else {
            LLog.v_noControl("adCallback null");
        }
    }

    public void callBackServiceCallback(Lxhw_XUserInfo lxhw_XUserInfo) {
        Lxhw_BackServiceCallback lxhw_BackServiceCallback = this.backServiceCallback;
        if (lxhw_BackServiceCallback != null && lxhw_XUserInfo != null) {
            lxhw_BackServiceCallback.result(lxhw_XUserInfo);
        } else {
            LLog.v_noControl("backServiceCallback or userInfo null");
        }
    }

    public void login(Activity activity) {
        Lxhw_FUser.getInstance().login(activity);
    }

    public void switchLogin(Activity activity) {
        LLog.v_noControl("switchAccount");
        Lxhw_FUser.getInstance().switchLogin(activity);
    }

    public void showAccountCenter(Activity activity) {
        LLog.v_noControl("showAccountCenter");
        Lxhw_FUser.getInstance().showAccountCenter(activity);
    }

    public void showCustomerService(Activity activity) {
        LLog.v_noControl("showCustomerService");
        Lxhw_FUser.getInstance().showCustomerService(activity);
    }

    public void showRewardedAd(Activity activity, AdCallback adCallback) {
        LLog.v_noControl("showRewardedAd");
        Lxhw_FUser.getInstance().showRewardedAd(activity, adCallback);
    }

    public void logOut(Activity activity) {
        LLog.v_noControl("logout");
        Lxhw_FUser.getInstance().logout(activity);
    }

    public void openGpShop(Activity activity) {
        LLog.v_noControl("openGpShop");
        Lxhw_FUser.getInstance().openGpShop(activity);
    }

    public void openGooglePlayInappReview(Activity activity) {
        LLog.v_noControl("openGooglePlayInappReview");
        Lxhw_FUser.getInstance().openGooglePlayInappReview(activity);
    }

    public void dianzan(Activity activity) {
        LLog.v_noControl("dianzan");
        Lxhw_FUser.getInstance().dianzan(activity);
    }

    public void fbShare(Activity activity) {
        LLog.v_noControl("fbShare");
        Lxhw_FUser.getInstance().fbShare(activity);
    }

    public void setGameLanguage(Activity activity, int i) {
        Lxhw_FUser.getInstance().setGameLanguage(activity, i);
    }

    public void showSurveyViewController() {
        Lxhw_FUser.getInstance().showSurveyViewController();
    }

    public void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback) {
        Lxhw_FUser.getInstance().getTranslateResult(str, str2, str3, lxhw_TranslateCallback);
    }

    public void deleteAccount(Activity activity) {
        Lxhw_FUser.getInstance().deleteAccount(activity);
    }

    public void uploadHead(Activity activity) {
        Lxhw_FUser.getInstance().uploadHead(activity);
    }

    public void downHead(Activity activity) {
        Lxhw_FUser.getInstance().downHead(activity);
    }

    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        LLog.v_noControl("exit");
        Lxhw_FUser.getInstance().exit(activity, lxhw_ExitCallback);
    }

    public void pay(Activity activity, Lxhw_PayParams lxhw_PayParams) {
        Lxhw_FUser.getInstance().pay(activity, lxhw_PayParams);
    }

    public void submitExtendData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
        LLog.e_noControl("submit type=" + lxhw_UserExtraData.getDataType());
        Lxhw_FUser.getInstance().submitExtraData(activity, lxhw_UserExtraData);
    }

    public void openActivityWithWeb(Activity activity, String str) {
        LLog.v_noControl("openActivityWithWeb");
        Lxhw_FUser.getInstance().openActivityWithWeb(activity, str);
    }

    public void openDiscord(Activity activity) {
        LLog.i_noControl("openDiscord");
        Lxhw_FUser.getInstance().openDiscord(activity);
    }

    public void sedCheck(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        if (lxhw_UserExtraData != null) {
            String str8 = "uid:" + Lxhw_XUserInfo.getInstance().getSdkId() + "\n";
            if (TextUtils.isEmpty(lxhw_UserExtraData.getRoleID())) {
                LLog.v_noControl("submited roleid null");
                str = str8 + "\nroleID:null";
            } else {
                str = str8 + "\nroleID:" + lxhw_UserExtraData.getRoleID();
            }
            if (TextUtils.isEmpty(lxhw_UserExtraData.getRoleLevel())) {
                LLog.v_noControl("submited rolelevel null");
                str2 = str + "\nroleLevel:null";
            } else {
                str2 = str + "\nroleLevel:" + lxhw_UserExtraData.getRoleLevel();
            }
            if (TextUtils.isEmpty(lxhw_UserExtraData.getRoleName())) {
                LLog.v_noControl("submited rolename null");
                str3 = str2 + "\nrolename:null";
            } else {
                str3 = str2 + "\nrolename:" + lxhw_UserExtraData.getRoleName();
            }
            if (TextUtils.isEmpty(lxhw_UserExtraData.getServerId())) {
                LLog.v_noControl("submited serverid null");
                str4 = str3 + "\nserverid null";
            } else {
                str4 = str3 + "\nserverid:" + lxhw_UserExtraData.getServerId();
            }
            if (TextUtils.isEmpty(lxhw_UserExtraData.getServerName())) {
                LLog.v_noControl("submited servername null");
                str5 = str4 + "\nservername:null";
            } else {
                str5 = str4 + "\nservername:" + lxhw_UserExtraData.getServerName();
            }
            if (lxhw_UserExtraData.getVipLevel() <= 0) {
                LLog.v_noControl("submited viplevel <=0");
                str6 = str5 + "\nviplevel<=0";
            } else {
                str6 = str5 + "\nviplevel:" + lxhw_UserExtraData.getVipLevel();
            }
            if (TextUtils.isEmpty(lxhw_UserExtraData.getRemain_coin())) {
                LLog.v_noControl("submited remain_coin null");
                str7 = str6 + "\nremain_coin null";
            } else {
                str7 = str6 + "\nremain_coin:" + lxhw_UserExtraData.getRemain_coin();
            }
            ToastUtils.toastLongShow(activity, str7);
        }
    }

    public void setScreenOrientation(int i) {
        LLog.v_noControl("setScreenOrientation " + i);
        BaseUtils.getInstance().setScreen_orientation(i);
    }

    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        Lxhw_FUser.getInstance().onTrackEventAF(context, str, map);
    }

    public void onCreate(Bundle bundle) {
        LLog.v_noControl("onCreate");
        Lxhw_XSDK.getInstance().onCreate(bundle);
    }

    public void onStart(Activity activity) {
        LLog.v_noControl("onStart");
        Lxhw_XSDK.getInstance().onStart();
    }

    public void onRestart(Activity activity) {
        LLog.v_noControl("onRestart");
        Lxhw_XSDK.getInstance().onRestart();
    }

    public void onPause(Activity activity) {
        LLog.v_noControl("onPause");
        Lxhw_XSDK.getInstance().onPause();
    }

    public void onResume(Activity activity) {
        LLog.v_noControl("onResume");
        Lxhw_XSDK.getInstance().onResume();
    }

    public void onNewIntent(Intent intent) {
        LLog.v_noControl("onNewIntent");
        Lxhw_XSDK.getInstance().onNewIntent(intent);
    }

    public void onStop(Activity activity) {
        LLog.v_noControl("onStop");
        Lxhw_XSDK.getInstance().onStop();
    }

    public void onDestroy(Activity activity) {
        LLog.v_noControl("onDestroy");
        Lxhw_XSDK.getInstance().onDestroy();
    }

    public void onConfigurationChanged(Configuration configuration) {
        LLog.v_noControl("onConfigurationChanged");
        Lxhw_XSDK.getInstance().onConfigurationChanged(configuration);
    }

    public void onSaveInstanceState(Bundle bundle) {
        LLog.v_noControl("onSaveInstanceState");
        Lxhw_XSDK.getInstance().onSaveInstanceState(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        LLog.v_noControl("onActivityResult");
        Lxhw_XSDK.getInstance().onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        LLog.v_noControl("onRequestPermissionsResult");
        Lxhw_XSDK.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onWindowFocusChanged(boolean z) {
        LLog.v_noControl("onWindowFocusChanged");
        Lxhw_XSDK.getInstance().onWindowFocusChanged(z);
    }
}
