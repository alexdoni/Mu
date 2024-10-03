package com.oversea.ab_firstplatform.plugin.user;

import android.app.Activity;
import android.content.Context;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.plugin.XPlugin;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import java.util.Map;

/* loaded from: classes2.dex */
public interface Lxhw_User extends XPlugin {
    public static final int PLUGIN_TYPE = 1;

    void deleteAccount(Activity activity);

    void dianzan(Activity activity);

    void downHead(Activity activity);

    void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback);

    void extraFun();

    void fbShare(Activity activity);

    void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback);

    void initApplication(Context context);

    void initSDK();

    void login(Activity activity);

    void logout(Activity activity);

    void onTrackEventAF(Context context, String str, Map<String, Object> map);

    void openActivityWithWeb(Activity activity, String str);

    void openDiscord(Activity activity);

    void openGooglePlayInappReview(Activity activity);

    void openGpShop(Activity activity);

    void pay(Activity activity, Lxhw_PayParams lxhw_PayParams);

    void setGameLanguage(Activity activity, int i);

    void showAccountCenter(Activity activity);

    void showCustomerService(Activity activity);

    void showPriorityAD(Activity activity, String str);

    void showRewardedAd(Activity activity, AdCallback adCallback);

    void showSurveyViewController();

    void submitExtraData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData);

    void switchLogin(Activity activity);

    void uploadHead(Activity activity);
}
