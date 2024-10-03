package com.oversea.ab_firstarea.p012f.p013a;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.oversea.ab_firstarea.broadcast.TimeUpdateReceiver;
import com.oversea.ab_firstarea.callback.HeartArrivedCallback;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.floatView.FloatView;
import com.oversea.ab_firstarea.haiwai.FirebaseControl;
import com.oversea.ab_firstarea.haiwai.GoogleSafeControl;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.permission.ContextCompat;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstarea.utils.HeartPacketUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;

/* renamed from: com.oversea.ab_firstarea.f.a.c */
/* loaded from: classes.dex */
public abstract class AbstractC2392c {
    private int[] intervalDayArray = {2, 3, 5, 7, 10};
    private LoginBean loginBean;
    private TimeUpdateReceiver timeUpdateReceiver;

    protected abstract void deleteAccount(Activity activity);

    protected abstract void dianzan(Activity activity);

    protected abstract void downHead(Activity activity);

    protected abstract void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback);

    protected abstract void extraFun();

    protected abstract void fbShare(Activity activity);

    protected abstract void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback);

    protected abstract void init();

    protected abstract void initApplication(Context context);

    protected abstract void initSDK();

    protected abstract void login(Activity activity);

    protected abstract void logout(Activity activity);

    protected abstract void onTrackEventAF(Context context, String str, Map<String, Object> map);

    protected abstract void openActivityWithWeb(Activity activity, String str);

    protected abstract void openDiscord(Activity activity);

    protected abstract void openGooglePlayInappReview(Activity activity);

    protected abstract void openGpShop(Activity activity);

    protected abstract void pay(Activity activity, Lxhw_PayParams lxhw_PayParams);

    protected abstract void setGameLanguage(Activity activity, int i);

    protected abstract void showAccountCenter(Activity activity);

    protected abstract void showCustomerService(Activity activity);

    protected abstract void showPriorityAD(Activity activity, String str);

    protected abstract void showRewardedAd(Activity activity, AdCallback adCallback);

    protected abstract void showSurveyViewController();

    protected abstract void submitExtraData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData);

    protected abstract void switchLogin(Activity activity);

    protected abstract void uploadHead(Activity activity);

    public void initResult(boolean z) {
        if (z) {
            Lxhw_XSDK.getInstance().onResult(1, "init success");
        } else {
            Lxhw_XSDK.getInstance().onResult(2, "init fail");
        }
    }

    public void logoutResult() {
        Lxhw_XSDK.getInstance().onResult(4, "logout success");
        LLog.v_noControl("logoutResult");
    }

    public void switchAccountResult() {
        Lxhw_XSDK.getInstance().onResult(5, "switch success");
        LLog.v_noControl("switchAccountResult");
    }

    public void userBindResult() {
        Lxhw_XSDK.getInstance().onResult(6, "userbind");
    }

    public void onShareResult(boolean z) {
        Lxhw_XSDK.getInstance().onShareResult(z);
    }

    public void accountUpgradeResult() {
        Lxhw_XSDK.getInstance().onResult(8, "accountUpgrade");
    }

    public void giftGetResult(int i, String str) {
        Lxhw_XSDK.getInstance().onGiftGetResult(i, str);
    }

    public void onADResult(boolean z, String str) {
        Lxhw_XSDK.getInstance().onADResult(z, str);
    }

    public void onDianzanResult(boolean z) {
        Lxhw_XSDK.getInstance().onDianzanResult(z);
    }

    public void onPingFenResult(boolean z) {
        Lxhw_XSDK.getInstance().onPingFenResult(z);
    }

    public void onUploadHeadResult(boolean z, String str) {
        Lxhw_XSDK.getInstance().onUploadHeadResult(z, str);
    }

    public void onDownHeadResult(boolean z, String str) {
        Lxhw_XSDK.getInstance().onDownHeadResult(z, str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0202, code lost:
    
        if (r3.equals("1") == false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onAuthResult(com.oversea.ab_firstarea.net.model.LoginBean r7) {
        /*
            Method dump skipped, instructions count: 708
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oversea.ab_firstarea.p012f.p013a.AbstractC2392c.onAuthResult(com.oversea.ab_firstarea.net.model.LoginBean):void");
    }

    public void dealLoginSuccess(LoginBean loginBean) {
        if (loginBean.getData().getPlatform_uid() == 0 || TextUtils.isEmpty(loginBean.getData().getToken())) {
            LLog.v_noControl("onAuthResult uid or token null");
        }
        if (InitBean.getInstance().getGame_info() != null && InitBean.getInstance().getGame_info().getIs_show_first_open_time() == 1 && loginBean.getData() != null) {
            long game_first_open_time = InitBean.getInstance().getGame_info().getGame_first_open_time() - loginBean.getData().getLogin_time();
            if (InitBean.getInstance().getGame_info().getGame_first_open_time() > 0 && loginBean.getData().getLogin_time() > 0 && game_first_open_time > 0) {
                Bundle bundle = new Bundle();
                bundle.putLong("countdown", game_first_open_time);
                Lxhw_DialogManage.getInstance().showCountDownDialog(Lxhw_XSDK.getInstance().getContext(), bundle);
            } else {
                loginSuccess();
            }
        } else {
            loginSuccess();
        }
        if (!TextUtils.isEmpty(loginBean.getData().getAndroid_safety_switch_config())) {
            try {
                JSONObject jSONObject = new JSONObject(loginBean.getData().getAndroid_safety_switch_config());
                if (!jSONObject.isNull("google_play_integrity_switch")) {
                    Lxhw_AreaPlatform.getInstance().google_play_integrity_switch = jSONObject.getInt("google_play_integrity_switch");
                }
                if (!jSONObject.isNull("google_play_recaptcha_switch")) {
                    Lxhw_AreaPlatform.getInstance().google_play_recaptcha_switch = jSONObject.getInt("google_play_recaptcha_switch");
                }
                LLog.v_Control("onAuthResult integrity_switch=" + Lxhw_AreaPlatform.getInstance().google_play_integrity_switch + " recaptcha_switch=" + Lxhw_AreaPlatform.getInstance().google_play_recaptcha_switch);
                GoogleSafeControl.getInstance().checkSafe(loginBean);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.loginBean = loginBean;
        getRetention(loginBean.getData().getLogin_time() * 1000);
        this.timeUpdateReceiver = new TimeUpdateReceiver();
        registerUpdateTimeReceiver();
        if (ManageBean.getInstance().getaCloudStSBean().getData().getOss() == null) {
            Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().acloudsts();
        }
    }

    public void loginSuccess() {
        Lxhw_XSDK.getInstance().onAuthResult(0);
        if (Lxhw_AreaPlatform.getInstance().isFF()) {
            FloatView.getInstance().startFloatView(Lxhw_XSDK.getInstance().getContext());
            Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().fireBaseUpdateToken(FirebaseControl.getInstance().getMsgToken());
            Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().kfBaseInfo();
            Lxhw_AreaPlatform.getInstance().getManageSomeRequestModel().userinfo_vi();
            Lxhw_DialogManage.getInstance().showLoginSuccessTip();
        }
        if (InitBean.getInstance().getGame_info() != null) {
            final int login_duration_report_time = InitBean.getInstance().getGame_info().getLogin_duration_report_time();
            long j = login_duration_report_time;
            HeartPacketUtil.getInstance().scheduleRequest(j, j, new HeartArrivedCallback() { // from class: com.oversea.ab_firstarea.f.a.c.1
                @Override // com.oversea.ab_firstarea.callback.HeartArrivedCallback
                public void onHeartArrived(int i) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), "custom_sdk_login_duration", (i * login_duration_report_time) + "");
                }
            });
        }
    }

    private void askNotificationPermission(Activity activity) {
        if (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(activity, "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.POST_NOTIFICATIONS")) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.POST_NOTIFICATIONS"}, 10029);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.POST_NOTIFICATIONS"}, 10029);
        }
    }

    public static boolean useList(int[] iArr, int i) {
        Arrays.sort(iArr);
        return Arrays.binarySearch(iArr, i) >= 0;
    }

    public void getRetention(long j) {
        LoginBean loginBean = this.loginBean;
        if (loginBean == null || loginBean.getData() == null || this.loginBean.getData().getRegister_time() == 0 || this.loginBean.getData().getLogin_time() == 0) {
            return;
        }
        try {
            long register_time = this.loginBean.getData().getRegister_time() * 1000;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            calendar.setTimeInMillis(register_time);
            clearHourMinSecInfo(calendar);
            Long valueOf = Long.valueOf(calendar.getTimeInMillis());
            LLog.i_Control("getRetention 注册开始时间: " + valueOf);
            Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            calendar2.setTimeInMillis(j);
            clearHourMinSecInfo(calendar2);
            Long valueOf2 = Long.valueOf(calendar2.getTimeInMillis());
            LLog.i_Control("getRetention 登录开始时间: " + valueOf2);
            int longValue = ((int) ((valueOf2.longValue() - valueOf.longValue()) / 86400000)) + 1;
            LLog.i_Control("getRetention 留存: " + longValue);
            if (useList(this.intervalDayArray, longValue)) {
                String format = String.format("custom_sdk_play_%dretention", Integer.valueOf(longValue));
                if (!TextUtils.isEmpty(ImageUtils.getStringKeyForValue(Lxhw_XSDK.getInstance().getContext(), format))) {
                    LLog.i_Control("getRetention 已上报过" + longValue + "留，返回");
                    return;
                }
                uploadRetention(format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadRetention(final String str) {
        new Handler().postDelayed(new Runnable() { // from class: com.oversea.ab_firstarea.f.a.c.2
            @Override // java.lang.Runnable
            public void run() {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), str, "1");
                Activity context = Lxhw_XSDK.getInstance().getContext();
                String str2 = str;
                ImageUtils.setSharePreferences(context, str2, str2);
            }
        }, 2000L);
    }

    private void clearHourMinSecInfo(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }

    private void registerUpdateTimeReceiver() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.DATE_CHANGED");
            Lxhw_XSDK.getInstance().getContext().registerReceiver(this.timeUpdateReceiver, intentFilter);
        } catch (Exception unused) {
        }
    }

    public void unregisterUpdateTimeReceiver() {
        try {
            if (this.timeUpdateReceiver != null) {
                Lxhw_XSDK.getInstance().getContext().unregisterReceiver(this.timeUpdateReceiver);
            }
        } catch (Exception unused) {
        }
    }
}
