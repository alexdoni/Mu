package com.oversea.ab_firstarea.p012f.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.oversea.ab_firstarea.activity.Lxhw_ActActivity;
import com.oversea.ab_firstarea.camearAndphoto.Lxhw_CamearPhotoControl;
import com.oversea.ab_firstarea.channel.ProjectType;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_DataReportModelImpl;
import com.oversea.ab_firstarea.dm.impl.Lxhw_LoginModelImpl;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnLoginListener;
import com.oversea.ab_firstarea.dpresenter.PresenterRequestComAuto;
import com.oversea.ab_firstarea.dpresenter.impl.Lxhw_RequestComAutoPresenterImpl;
import com.oversea.ab_firstarea.eventSyn.MockEventReport;
import com.oversea.ab_firstarea.floatView.FloatView;
import com.oversea.ab_firstarea.haiwai.AppsFlyerControl;
import com.oversea.ab_firstarea.haiwai.FaceBookControl;
import com.oversea.ab_firstarea.haiwai.FaceBookGameRequestDialog;
import com.oversea.ab_firstarea.haiwai.FaceBookShareControl;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.haiwai.GooglePlayControl;
import com.oversea.ab_firstarea.haiwai.HuaweiControl;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.model.TranslateBean;
import com.oversea.ab_firstarea.net.model.UserBindInfoBean;
import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.orderSyn.GooglePreRegistrationSupplementOrder;
import com.oversea.ab_firstarea.orderSyn.PreRegistrationOrderInfo;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstarea.plugin.ad.AdmobLibHelper;
import com.oversea.ab_firstarea.plugin.ad.max.MaxLibHelper;
import com.oversea.ab_firstarea.plugin.channel.Tsdk;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstarea.utils.HeartPacketUtil;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.TSBean;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.IsFastClickUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.Lxhw_RechargeType;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import com.xsdk.ab_firstbase.statisics.util.languagelib.MultiLanguageUtil;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Lxhw_AreaPlatform {
    private static Lxhw_AreaPlatform instance;
    public Activity activity;
    private UserBindInfoBean bindInfoBean;
    public int google_play_integrity_switch;
    public int google_play_recaptcha_switch;
    public boolean isUseNewSdkLogin;
    private Lxhw_ManageSomeRequestModel manageSomeRequestModel;
    private PresenterRequestComAuto presenterRequestComAuto;
    private UserInfoBean userInfoBean;
    private Lxhw_PayParams xPayParams;
    private final String TAG = getClass().toString();
    private boolean isFirstLogin = true;
    public String tempLoginClickType = "0";
    public boolean iTestPay = false;
    public boolean iTestPay_fahuo = false;
    public boolean iTestSDKDemo = false;
    public boolean iOpenUpdate = false;
    public boolean iShowViInfo = false;
    public boolean is_first_start = true;

    public void logout(Activity activity) {
    }

    public static Lxhw_AreaPlatform getInstance() {
        if (instance == null) {
            instance = new Lxhw_AreaPlatform();
        }
        return instance;
    }

    public void onCreate(Activity activity) {
        this.activity = activity;
    }

    public void setProjectType(String str) {
        ProjectType.setProjectType(str);
    }

    public void setRecType(String str) {
        LLog.v_noControl("REC_TYPE = " + str);
        Lxhw_RechargeType.setType(str);
    }

    public PresenterRequestComAuto getRequestComAuto() {
        if (this.presenterRequestComAuto == null) {
            this.presenterRequestComAuto = new Lxhw_RequestComAutoPresenterImpl();
        }
        return this.presenterRequestComAuto;
    }

    public Lxhw_ManageSomeRequestModel getManageSomeRequestModel() {
        if (this.manageSomeRequestModel == null) {
            this.manageSomeRequestModel = new Lxhw_ManageSomeRequestModelImpl();
        }
        return this.manageSomeRequestModel;
    }

    public UserBindInfoBean getBindInfoBean() {
        return this.bindInfoBean;
    }

    public void setBindInfoBean(UserBindInfoBean userBindInfoBean) {
        this.bindInfoBean = userBindInfoBean;
    }

    public UserInfoBean getUserInfoBean() {
        return this.userInfoBean;
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("init");
        createCommonParams.put("scene_type", "2");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.INIT_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("init", createCommonParams, AreaBaseService.INIT_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:77:0x044a A[Catch: all -> 0x0473, JSONException -> 0x0479, TryCatch #3 {all -> 0x0473, blocks: (B:11:0x0056, B:13:0x005c, B:15:0x006e, B:16:0x0093, B:18:0x0099, B:21:0x00c1, B:23:0x00fc, B:25:0x0123, B:27:0x0131, B:28:0x014d, B:29:0x015e, B:31:0x016d, B:32:0x0173, B:34:0x0182, B:35:0x0195, B:37:0x019b, B:38:0x01ae, B:40:0x01b4, B:43:0x01d4, B:46:0x01d7, B:48:0x01df, B:50:0x01fc, B:52:0x020e, B:53:0x024b, B:55:0x0255, B:57:0x0267, B:58:0x02a4, B:60:0x02ac, B:61:0x02dc, B:63:0x02e4, B:64:0x02f5, B:66:0x02fd, B:67:0x030e, B:70:0x0325, B:72:0x033a, B:73:0x033d, B:75:0x0428, B:77:0x044a, B:79:0x0452, B:80:0x046a, B:84:0x034b, B:86:0x035b, B:88:0x036b, B:90:0x0387, B:92:0x0397, B:93:0x039c, B:95:0x03af, B:96:0x03fa, B:98:0x040a, B:99:0x040e, B:101:0x0425, B:107:0x0080, B:110:0x0090, B:114:0x0465, B:117:0x0485, B:119:0x048f), top: B:9:0x0054 }] */
            /* JADX WARN: Type inference failed for: r4v17, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r4v18 */
            /* JADX WARN: Type inference failed for: r4v19 */
            /* JADX WARN: Type inference failed for: r4v20 */
            /* JADX WARN: Type inference failed for: r4v27 */
            /* JADX WARN: Type inference failed for: r4v28 */
            /* JADX WARN: Type inference failed for: r4v29 */
            /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void httpRequestCallBackListener(java.lang.String r22) {
                /*
                    Method dump skipped, instructions count: 1356
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform.C23971.httpRequestCallBackListener(java.lang.String):void");
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                AreaSdk.getInstance().initResult(false);
                LLog.v_noControl("init fail code=" + baseBean.getCode() + " " + baseBean.getMessage());
                Lxhw_AreaPlatform.this.onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_SDK_INIT_ERROR, baseBean.getCode() + ", " + baseBean.getMessage());
                if (Lxhw_AreaPlatform.this.activity == null || Lxhw_AreaPlatform.this.activity.isFinishing()) {
                    return;
                }
                ToastUtils.toastShow(Lxhw_AreaPlatform.this.activity, "init fail: " + ComUtil.getBaseBeanTip(baseBean));
            }
        });
    }

    public void initSDK() {
        LLog.v_noControl("area init=" + Lxhw_Platform.getInstance().isAPPinit);
        if (Lxhw_Platform.getInstance().isAPPinit) {
            return;
        }
        onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_SDK_INIT_START, "1");
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isExam() {
        try {
            int parseInt = (InitBean.getInstance().getGame_info() == null || TextUtils.isEmpty(InitBean.getInstance().getGame_info().getAndroid_online_version())) ? 0 : Integer.parseInt(InitBean.getInstance().getGame_info().getAndroid_online_version());
            if (parseInt != 0) {
                return Util.getVersionCode(ContextHolder.getContext()) - parseInt > 0;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdSDK() {
        if (InitBean.getInstance().getGame_info() == null || InitBean.getInstance().getGame_info().getIs_use_ad_realization() != 1 || TextUtils.isEmpty(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform()) || TextUtils.isEmpty(InitBean.getInstance().getGame_info().getAndroid_video_advert_id())) {
            return;
        }
        if ("MAX".equals(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform())) {
            MaxLibHelper.getInstance().init(Lxhw_XSDK.getInstance().getContext());
        } else if ("Admob".equals(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform())) {
            AdmobLibHelper.getInstance().init(Lxhw_XSDK.getInstance().getContext());
        }
    }

    private boolean isForcedUpdate(Activity activity) {
        try {
            this.iOpenUpdate = true;
            if (InitBean.getInstance().getGame_info() != null && !TextUtils.isEmpty(InitBean.getInstance().getGame_info().getAndroid_forced_update_version())) {
                int intValue = Integer.valueOf(InitBean.getInstance().getGame_info().getAndroid_forced_update_version()).intValue();
                int versionCode = Util.getVersionCode(activity);
                int android_update_switch = InitBean.getInstance().getGame_info().getAndroid_update_switch();
                LLog.i_Control("isForcedUpdate = serverDownVersion" + intValue + " sysVersion=" + Util.getVersionCode(activity) + " updateSwitch=" + android_update_switch);
                if (versionCode < intValue && ((android_update_switch == 1 || android_update_switch == 2) && !TextUtils.isEmpty(Util.getGoogleShopUrl(activity)))) {
                    Lxhw_DialogManage.getInstance().enterAppUpdateCenter(activity);
                    return true;
                }
            } else {
                LLog.v_noControl("isForcedUpdate= getAndroid_forced_update_version null");
            }
            return false;
        } catch (Throwable th) {
            LLog.e_noControl("isForcedUpdate=" + th.toString());
            return false;
        }
    }

    private void autoGuestLogin() {
        onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_AUTO_GUEST_LOGIN, "1");
        new Lxhw_LoginModelImpl().guestLogin(new OnLoginListener<LoginBean>() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.2
            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqSuccess(String str, LoginBean loginBean) {
            }

            @Override // com.oversea.ab_firstarea.dpresenter.OnLoginListener
            public void onReqGuestSuccess(LoginBean loginBean) {
                ImageUtils.setSharePreferences(Lxhw_XSDK.getInstance().getContext(), Constants.SDK_LOGIN_TYPE, "2");
                AreaSdk.getInstance().onAuthResult(loginBean);
            }

            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqFail(String str, BaseBean baseBean) {
                ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
                Lxhw_DialogManage.getInstance().showLoginV2(Lxhw_XSDK.getInstance().getContext());
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }

    public void login(final Activity activity) {
        LLog.v_noControl("area login=" + Lxhw_Platform.getInstance().isAPPinit);
        if ((this.iOpenUpdate || !isForcedUpdate(activity)) && activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.3
                @Override // java.lang.Runnable
                public void run() {
                    if (!Lxhw_Platform.getInstance().isAPPinit) {
                        Activity activity2 = activity;
                        ToastUtils.toastShow(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_init_tip")));
                        Lxhw_AreaPlatform.this.init();
                        return;
                    }
                    if (Lxhw_AreaPlatform.this.isFF()) {
                        Lxhw_AreaPlatform.this.ffLogin(activity);
                    } else {
                        Tsdk.getInstance().login();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ffLogin(Activity activity) {
        Lxhw_DialogManage.getInstance().removeLoginSuccessTip();
        FloatView.getInstance().onDestroyFloatView();
        if (!this.isFirstLogin) {
            Lxhw_DialogManage.getInstance().showLoginV2(activity);
            return;
        }
        this.isFirstLogin = false;
        String stringKeyForValue = ImageUtils.getStringKeyForValue(activity, Constants.SDK_USER_TOKEN);
        if (TextUtils.isEmpty(stringKeyForValue)) {
            if (this.is_first_start && InitBean.getInstance().getGame_info() != null && InitBean.getInstance().getGame_info().getIs_auto_guest_login() == 1) {
                autoGuestLogin();
                return;
            } else {
                Lxhw_DialogManage.getInstance().showLoginV2(activity);
                return;
            }
        }
        getManageSomeRequestModel().autoLoginWithToken(stringKeyForValue);
    }

    public void switchLogin(Activity activity) {
        if (!isFF()) {
            Tsdk.getInstance().logout();
        } else if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.4
                @Override // java.lang.Runnable
                public void run() {
                    Lxhw_AreaPlatform.this.callbackSwitchAccount();
                }
            });
        }
    }

    public void callbackSwitchAccount() {
        if (PayLibHelper.getInstance().isHuawei()) {
            HuaweiControl.getInstance().logout();
        }
        AreaSdk.getInstance().switchAccountResult();
    }

    public void dealErrorRequest(String str) {
        getInstance().callbackSwitchAccount();
    }

    public void cleanInfo() {
        Lxhw_XUserInfo.getInstance().cleanUserInfo();
        Lxhw_Platform.getInstance().userExtraData = new Lxhw_UserExtraData();
        FloatView.getInstance().onDestroyFloatView();
        Lxhw_DialogManage.getInstance().removeLoginSuccessTip();
        Lxhw_DialogManage.getInstance().removeAllFragment(Lxhw_XSDK.getInstance().getContext());
        getInstance().google_play_integrity_switch = 0;
        getInstance().google_play_recaptcha_switch = 0;
        ImageUtils.remove(Lxhw_XSDK.getInstance().getContext(), Constants.SDK_USER_TOKEN);
        AreaSdk.getInstance().unregisterUpdateTimeReceiver();
        HeartPacketUtil.getInstance().stop();
    }

    public void showAccountCenter(final Activity activity) {
        if (!isFF() || activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.5
            @Override // java.lang.Runnable
            public void run() {
                if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
                    Lxhw_DialogManage.getInstance().showUserCenter(activity);
                    return;
                }
                Activity activity2 = activity;
                Toast.makeText(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_please_login")), 0).show();
            }
        });
    }

    public void showCustomerService(final Activity activity) {
        if (!isFF() || activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.6
            @Override // java.lang.Runnable
            public void run() {
                if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
                    Lxhw_DialogManage.getInstance().showCustomerService(activity);
                } else {
                    Toast.makeText(activity, Lxhw_XSDK.getInstance().getContext().getString(Util.getIdByName(Lxhw_XSDK.getInstance().getContext(), TypedValues.Custom.S_STRING, "tw_please_login")), 0).show();
                }
            }
        });
    }

    public void submitExtendData(Activity activity, final Lxhw_UserExtraData lxhw_UserExtraData) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.7
                @Override // java.lang.Runnable
                public void run() {
                    if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
                        Lxhw_Platform.getInstance().userExtraData = lxhw_UserExtraData;
                        if (!Lxhw_AreaPlatform.this.isFF()) {
                            Tsdk.getInstance().submitGameInfo(lxhw_UserExtraData);
                        }
                        if (ManageBean.getInstance().getLoginBean() != null && ManageBean.getInstance().getLoginBean().getData() != null) {
                            if (lxhw_UserExtraData.getDataType() == 3) {
                                if (!GooglePreRegistrationSupplementOrder.getInstance().isOrderExist()) {
                                    GooglePreRegistrationSupplementOrder.getInstance().createOrder(lxhw_UserExtraData.getServerId(), lxhw_UserExtraData.getRoleID(), lxhw_UserExtraData.getServerName(), lxhw_UserExtraData.getRoleName());
                                }
                                PreRegistrationOrderInfo preRegistrationOrderInfo = GooglePreRegistrationSupplementOrder.getInstance().getPreRegistrationOrderInfo();
                                if (preRegistrationOrderInfo != null && !TextUtils.isEmpty(preRegistrationOrderInfo.getSignature()) && preRegistrationOrderInfo.getReceiveStatus() == 0) {
                                    GooglePreRegistrationSupplementOrder.getInstance().preRegRewardNotify(preRegistrationOrderInfo.getSignedData(), preRegistrationOrderInfo.getSignature(), preRegistrationOrderInfo.getPurchaseToken(), preRegistrationOrderInfo.getRoleId(), preRegistrationOrderInfo.getRoleName(), preRegistrationOrderInfo.getServerId(), preRegistrationOrderInfo.getServerName());
                                }
                                PayLibHelper.getInstance().queryPurchases();
                                if (PayLibHelper.getInstance().getProductListData() == null) {
                                    HttpRequestCenter.getInstance().requestProductIdList();
                                } else {
                                    PayLibHelper.getInstance().getProductListInfo();
                                }
                            }
                            new Lxhw_DataReportModelImpl().report(ManageBean.getInstance().getLoginBean().getData().getLogin_log_id(), lxhw_UserExtraData);
                            return;
                        }
                        LLog.v_noControl("submitExtendData loginbean null");
                        return;
                    }
                    LLog.v_noControl("submitExtendData submitExtendData uid null");
                }
            });
        }
    }

    public void deleteAccount(Activity activity) {
        if (!isFF() || InitBean.getInstance().getGame_info() == null) {
            return;
        }
        if (InitBean.getInstance().getGame_info().getAndroid_delete_account_switch() == 1 && Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
            Lxhw_DialogManage.getInstance().showDelAccountDialog(activity, null);
        } else {
            LLog.i_noControl("delete_account_switch != 0 or uid is null");
        }
    }

    public void showRewardedAd(Activity activity, AdCallback adCallback) {
        if (Lxhw_XUserInfo.getInstance().getSdkId() == 0) {
            LLog.v_noControl("showRewardedAd uid =0");
        } else {
            showAd(activity, adCallback);
        }
    }

    private void showAd(Activity activity, AdCallback adCallback) {
        if (InitBean.getInstance().getGame_info() == null || InitBean.getInstance().getGame_info().getIs_use_ad_realization() != 1) {
            return;
        }
        String android_video_advert_id = InitBean.getInstance().getGame_info().getAndroid_video_advert_id();
        if (TextUtils.isEmpty(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform()) || TextUtils.isEmpty(android_video_advert_id)) {
            return;
        }
        if ("MAX".equals(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform())) {
            MaxLibHelper.getInstance().showRewardedAd(activity, android_video_advert_id, adCallback);
        } else if ("Admob".equals(InitBean.getInstance().getGame_info().getAndroid_video_advert_platform())) {
            AdmobLibHelper.getInstance().showRewardedAd(activity, android_video_advert_id, adCallback);
        }
    }

    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        if (map == null) {
            map = new ArrayMap<>();
        }
        if (MockEventReport.getInstance().CheckEventReportIntervalTime(str)) {
            LLog.v_noControl(this.TAG, "onTrackPollEvent map=" + map.toString());
            AppsFlyerControl.getInstance().onTrackEvent(context, str, map);
            Bundle mapToBundle = Util.mapToBundle(map);
            if (mapToBundle != null) {
                FirebaseControl.getInstance().setLogEvent(str, mapToBundle);
            }
            MockEventReport.getInstance().onTrackEvent(context, str, map);
        }
    }

    public void onTrackEventConst(Context context, String str) {
        if (context == null) {
            context = ContextHolder.getContext();
        }
        LLog.v_noControl("onTrackEventConst " + str);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(str, "1");
        AppsFlyerControl.getInstance().onTrackEvent(context, str, arrayMap);
        Bundle mapToBundle = Util.mapToBundle(arrayMap);
        if (mapToBundle != null) {
            FirebaseControl.getInstance().setLogEvent(str, mapToBundle);
        }
        MockEventReport.getInstance().onTrackEvent(context, str, arrayMap);
    }

    public void onTrackEventKeyValue(Context context, String str, String str2) {
        LLog.v_noControl("onTrackEventKeyValue   eventName=" + str + " eventValue=" + str2);
        if (context == null) {
            LLog.v_noControl("onTrackEventKeyValue context null");
            return;
        }
        if (MockEventReport.getInstance().CheckEventReportIntervalTime(str) && !TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put(str, str2);
            AppsFlyerControl.getInstance().onTrackEvent(context, str, arrayMap);
            Bundle mapToBundle = Util.mapToBundle(arrayMap);
            if (mapToBundle != null) {
                FirebaseControl.getInstance().setLogEvent(str, mapToBundle);
            }
            MockEventReport.getInstance().onTrackEvent(context, str, arrayMap);
        }
    }

    public void onTrackEventJsonObject(Context context, String str, String str2, int i, String str3) {
        if (MockEventReport.getInstance().CheckEventReportIntervalTime(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(ShareConstants.MEDIA_TYPE, str2);
                jSONObject.put("code", i + "");
                jSONObject.put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, str3);
                jSONObject.put("os", "2");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(jSONObject.getString("code"))) {
                return;
            }
            if (TextUtils.isEmpty(jSONObject.getString(ShareConstants.MEDIA_TYPE))) {
                return;
            }
            ArrayMap arrayMap = new ArrayMap();
            arrayMap.put(str, jSONObject.toString());
            AppsFlyerControl.getInstance().onTrackEvent(context, str, arrayMap);
            Bundle mapToBundle = Util.mapToBundle(arrayMap);
            if (mapToBundle != null) {
                FirebaseControl.getInstance().setLogEvent(str, mapToBundle);
            }
            MockEventReport.getInstance().onTrackEvent(context, str, arrayMap);
        }
    }

    public void openGpShop(final Activity activity) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.8
                @Override // java.lang.Runnable
                public void run() {
                    Lxhw_DialogManage.getInstance().openGpShop(activity);
                }
            });
        }
    }

    public void openGooglePlayInappReview(Activity activity) {
        if (!isFF() || activity == null) {
            return;
        }
        AreaSdk.getInstance().onPingFenResult(true);
        PayLibHelper.getInstance().launchReviewFlow(activity);
        getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_APP_COMMENT);
    }

    public void dianzan(final Activity activity) {
        if (!isFF() || activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.9
            @Override // java.lang.Runnable
            public void run() {
                if (!TextUtils.isEmpty(InitBean.getInstance().getGame_info().getFb_fans_url())) {
                    AreaSdk.getInstance().onDianzanResult(true);
                    Lxhw_DialogManage.getInstance().jumpBrowserUrl(activity, InitBean.getInstance().getGame_info().getFb_fans_url());
                } else {
                    LLog.i_Control("fhwf_dz : url null");
                    Activity activity2 = activity;
                    ToastUtils.toastShow(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "like_link_not_set")));
                }
            }
        });
    }

    public void fbShare(final Activity activity) {
        if (!isFF() || activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.10
            @Override // java.lang.Runnable
            public void run() {
                if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
                    if (!TextUtils.isEmpty(InitBean.getInstance().getGame_info().getFb_share_url())) {
                        LLog.i_Control("sharelink start");
                        FaceBookShareControl.getInstance().sharelink(activity, InitBean.getInstance().getGame_info().getFb_share_url(), "", "");
                        return;
                    } else {
                        LLog.i_Control("fhwf_sfblink : url null");
                        Activity activity2 = activity;
                        ToastUtils.toastShow(activity, activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "sharing_link_notset")));
                        return;
                    }
                }
                Activity activity3 = activity;
                ToastUtils.toastShow(activity, activity3.getString(Util.getIdByName(activity3, TypedValues.Custom.S_STRING, "sharing_link_notset")));
            }
        });
    }

    public void pay(final Activity activity, final Lxhw_PayParams lxhw_PayParams) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.11
                @Override // java.lang.Runnable
                public void run() {
                    if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
                        DealPay.getInstance().init(activity);
                        if (ComConstants.GETORDER_STATU != 3) {
                            ComConstants.GETORDER_STATU = 3;
                            LLog.i_Control("productid=" + lxhw_PayParams.getProductId() + " storeProductid=" + lxhw_PayParams.getStoreProductId());
                            StringBuilder sb = new StringBuilder("cpod=");
                            sb.append(lxhw_PayParams.getCpOrderID());
                            LLog.i_Control(sb.toString());
                            DealPay.getInstance().payInit(lxhw_PayParams);
                            return;
                        }
                        Activity activity2 = activity;
                        String string = activity2.getString(Util.getIdByName(activity2, TypedValues.Custom.S_STRING, "tw_donot_order"));
                        ToastUtils.toastShow(activity, string);
                        Lxhw_XSDK.getInstance().onResult(18, string);
                        return;
                    }
                    Activity activity3 = activity;
                    ToastUtils.toastShow(activity, activity3.getString(Util.getIdByName(activity3, TypedValues.Custom.S_STRING, "tw_please_login")));
                    Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
                }
            });
        }
    }

    public void setGameLanguage(Activity activity, int i) {
        Lxhw_DialogManage.getInstance().setGameLanguage(activity, i);
    }

    public void uploadHead(Activity activity) {
        Lxhw_DialogManage.getInstance().uploadHead(activity);
    }

    public void downHead(Activity activity) {
        Lxhw_DialogManage.getInstance().downHead(activity);
    }

    public void sdkExit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        if (isFF()) {
            lxhw_ExitCallback.exitResult();
        } else {
            Tsdk.getInstance().exit();
        }
    }

    public void showSurveyViewController() {
        if (isFF()) {
            if (TextUtils.isEmpty(Lxhw_XUserInfo.getInstance().getToken()) || TextUtils.isEmpty(Lxhw_Platform.getInstance().userExtraData.getServerId()) || TextUtils.isEmpty(Lxhw_Platform.getInstance().userExtraData.getRoleID())) {
                LLog.e_Control("showSurveyViewController token or serverid or roleid null");
                return;
            }
            if (IsFastClickUtils.isFastClick(Lxhw_Platform.getInstance().fastClickTime)) {
                LLog.e_Control("showSurveyViewController多次点击，返回...................");
                return;
            }
            HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
            Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doGetUserInfo");
            httpParamsCommon.addSign(createCommonParams, AreaBaseService.USERINFO_ROUTE);
            HttpRequestCenter.getInstance().doRequestCommonAddHead("doGetUserInfo", createCommonParams, AreaBaseService.USERINFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.12
                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestCallBackListener(String str) {
                    try {
                        if (!TextUtils.isEmpty(str)) {
                            UserInfoBean userInfoBean = (UserInfoBean) JsonUtility.jsonParseToObject(UserInfoBean.class, str);
                            if (userInfoBean.getCode() == 0) {
                                String str2 = AreaBaseService.SURVEYURL + "?" + ("game_id=" + Lxhw_AppInfoDecorator.getGame_id() + "&server_id=" + Util.urlEncoded(Lxhw_Platform.getInstance().userExtraData.getServerId()) + "&server_name=" + Util.urlEncoded(Lxhw_Platform.getInstance().userExtraData.getServerName()) + "&role_id=" + Util.urlEncoded(Lxhw_Platform.getInstance().userExtraData.getRoleID()) + "&role_name=" + Util.urlEncoded(Lxhw_Platform.getInstance().userExtraData.getRoleName()) + "&role_level=" + Util.urlEncoded(Lxhw_Platform.getInstance().userExtraData.getRoleLevel()) + "&token=" + Util.urlEncoded(Lxhw_XUserInfo.getInstance().getToken()) + "&system_area_code=" + Util.getCountry() + "&system_lang_code=" + MultiLanguageUtil.getInstance().getCurrentSystemLauguage() + "&os=2");
                                LLog.i_Control("showSurveyViewController url=" + str2);
                                Lxhw_DialogManage.getInstance().showWebFullScreenCommon(Lxhw_XSDK.getInstance().getContext(), str2, "");
                            } else {
                                LLog.i_Control("showSurveyViewController code=" + userInfoBean.getCode() + " msg=" + userInfoBean.getMessage());
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestFail(BaseBean baseBean) {
                    LLog.i_Control("showSurveyViewController httpRequestFail code=" + baseBean.getCode() + " msg=" + baseBean.getMessage());
                }
            });
        }
    }

    public void getTranslateResult(final String str, final String str2, final String str3, final Lxhw_TranslateCallback lxhw_TranslateCallback) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("translate");
        createCommonParams.put("text", str);
        createCommonParams.put("target_lang", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.TRANSLATE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("translate", createCommonParams, AreaBaseService.TRANSLATEURL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.13
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                LLog.v_noControl(str + " tl=" + str2 + " " + str4);
                try {
                    if (new JSONObject(str4).isNull("code")) {
                        return;
                    }
                    TranslateBean translateBean = (TranslateBean) JsonUtility.jsonToObject(TranslateBean.class, str4);
                    if (translateBean != null && translateBean.getCode() == 0) {
                        TSBean tSBean = new TSBean();
                        tSBean.setResult(true);
                        tSBean.setSourceText(str);
                        tSBean.setExtInfo(str3);
                        if (translateBean.getData() != null) {
                            tSBean.setTranslatedText(translateBean.getData().getTranslated_text());
                        }
                        lxhw_TranslateCallback.result(tSBean);
                        return;
                    }
                    TSBean tSBean2 = new TSBean();
                    tSBean2.setResult(false);
                    tSBean2.setSourceText(str);
                    tSBean2.setTranslatedText("");
                    tSBean2.setExtInfo(str3);
                    lxhw_TranslateCallback.result(tSBean2);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        LLog.i_Control("fhwf_OnActivityResult");
        LLog.i_Control("CTRL_TYPE : " + ComConstants.CTRL_TYPE);
        if (ComConstants.CTRL_TYPE == 1) {
            return;
        }
        if (ComConstants.CTRL_TYPE == 11 || i == 9001) {
            GooglePlayControl.getInstance().handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
            return;
        }
        if (ComConstants.CTRL_TYPE == 10) {
            FaceBookControl.getInstance().setFacebookCallBack(i, i2, intent);
            return;
        }
        if (ComConstants.CTRL_TYPE == 21 || ComConstants.CTRL_TYPE == 22 || ComConstants.CTRL_TYPE == 23) {
            Lxhw_CamearPhotoControl.getInstance().onActivityResult(i, i2, intent);
            return;
        }
        if (ComConstants.CTRL_TYPE == 30) {
            if (FaceBookGameRequestDialog.getInstance().callbackManager != null) {
                FaceBookGameRequestDialog.getInstance().callbackManager.onActivityResult(i, i2, intent);
            }
        } else if (ComConstants.CTRL_TYPE == 31) {
            if (FaceBookShareControl.getInstance().callbackManager != null) {
                FaceBookShareControl.getInstance().callbackManager.onActivityResult(i, i2, intent);
            }
        } else if (ComConstants.CTRL_TYPE == 32 || ComConstants.CTRL_TYPE == 33 || ComConstants.CTRL_TYPE == 35) {
            PayLibHelper.getInstance().onActivityResult(i, i2, intent);
        } else if (i == 34) {
            HuaweiControl.getInstance().onActivityResult(i, i2, intent);
        }
    }

    public void onResume() {
        LLog.i_Control("fhwf_onResume");
        if (Lxhw_XUserInfo.getInstance().getSdkId() != 0) {
            try {
                new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.f.sdk.Lxhw_AreaPlatform.14
                    @Override // java.lang.Runnable
                    public void run() {
                        PayLibHelper.getInstance().queryPurchases();
                    }
                }, 3000L);
            } catch (Exception e) {
                LLog.e_Control(e.getMessage());
            }
        }
    }

    public void onDestroy() {
        PayLibHelper.getInstance().onDestroy();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Lxhw_CamearPhotoControl.getInstance().onRequestPermissionsResult(i, strArr, iArr);
    }

    public void openActivityWithWeb(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) Lxhw_ActActivity.class);
        intent.putExtra("mUrl", str);
        activity.startActivity(intent);
    }

    public void openDiscord(Activity activity) {
        if (isFF()) {
            if (InitBean.getInstance().getGame_info() != null && !TextUtils.isEmpty(InitBean.getInstance().getGame_info().getDiscord_invite_url())) {
                try {
                    Uri parse = Uri.parse(InitBean.getInstance().getGame_info().getDiscord_invite_url());
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(parse);
                    activity.startActivity(intent);
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            LLog.e_noControl("discord url is empty!");
        }
    }

    public boolean isFF() {
        return Lxhw_AppInfoDecorator.getChannel_id() == 0 || InitBean.getInstance().getAndroid_third_login_switch_config().getStore_new().getSwitchA() == 0;
    }
}
