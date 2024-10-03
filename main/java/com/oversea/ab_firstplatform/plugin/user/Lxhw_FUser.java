package com.oversea.ab_firstplatform.plugin.user;

import android.app.Activity;
import android.content.Context;
import com.oversea.ab_firstarea.plugin.ad.AdCallback;
import com.oversea.ab_firstplatform.imp.XSimpleDefaultUser;
import com.oversea.ab_firstplatform.init.Lxhw_ExitCallback;
import com.oversea.ab_firstplatform.init.Lxhw_TranslateCallback;
import com.oversea.ab_firstplatform.plugin.XPluginFactory;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes2.dex */
public class Lxhw_FUser {
    private static Lxhw_FUser mInstance;
    private Lxhw_User userPlugin;

    private Lxhw_FUser() {
    }

    public void init() {
        Lxhw_User lxhw_User = (Lxhw_User) XPluginFactory.getInstance().initPlugin(1);
        this.userPlugin = lxhw_User;
        if (lxhw_User == null) {
            LLog.v_noControl("XFUser init null");
            this.userPlugin = new XSimpleDefaultUser();
        }
    }

    public static Lxhw_FUser getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_FUser();
        }
        return mInstance;
    }

    public boolean isSupport(String str) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return false;
        }
        return lxhw_User.isSupportMethod(str);
    }

    public void initApplication(Context context) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.initApplication(context);
    }

    public void initSDK() {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.initSDK();
    }

    public void login(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.login(activity);
    }

    public void switchLogin(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.switchLogin(activity);
    }

    public void showAccountCenter(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.showAccountCenter(activity);
    }

    public void showCustomerService(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.showCustomerService(activity);
    }

    public void showRewardedAd(Activity activity, AdCallback adCallback) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.showRewardedAd(activity, adCallback);
    }

    public void logout(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.logout(activity);
    }

    public void pay(Activity activity, Lxhw_PayParams lxhw_PayParams) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.pay(activity, lxhw_PayParams);
    }

    public void submitExtraData(Activity activity, Lxhw_UserExtraData lxhw_UserExtraData) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.submitExtraData(activity, lxhw_UserExtraData);
    }

    public void onTrackEventAF(Context context, String str, Map<String, Object> map) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.onTrackEventAF(context, str, map);
    }

    public void openGpShop(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.openGpShop(activity);
    }

    public void openGooglePlayInappReview(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.openGooglePlayInappReview(activity);
    }

    public void dianzan(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.dianzan(activity);
    }

    public void fbShare(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.fbShare(activity);
    }

    public void showSurveyViewController() {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.showSurveyViewController();
    }

    public void getTranslateResult(String str, String str2, String str3, Lxhw_TranslateCallback lxhw_TranslateCallback) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.getTranslateResult(str, str2, str3, lxhw_TranslateCallback);
    }

    public void deleteAccount(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.deleteAccount(activity);
    }

    public void setGameLanguage(Activity activity, int i) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.setGameLanguage(activity, i);
    }

    public void uploadHead(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.uploadHead(activity);
    }

    public void downHead(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.downHead(activity);
    }

    public void exit(Activity activity, Lxhw_ExitCallback lxhw_ExitCallback) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.exit(activity, lxhw_ExitCallback);
    }

    public void openActivityWithWeb(Activity activity, String str) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.openActivityWithWeb(activity, str);
    }

    public void openDiscord(Activity activity) {
        Lxhw_User lxhw_User = this.userPlugin;
        if (lxhw_User == null) {
            return;
        }
        lxhw_User.openDiscord(activity);
    }
}
