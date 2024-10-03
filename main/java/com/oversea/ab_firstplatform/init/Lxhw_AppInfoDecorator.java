package com.oversea.ab_firstplatform.init;

import android.content.Context;
import android.text.TextUtils;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.MateUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;

/* loaded from: classes2.dex */
public class Lxhw_AppInfoDecorator {
    private static Lxhw_AppInfo appInfo;

    private Lxhw_AppInfoDecorator() {
    }

    public static void init(Context context) {
        if (appInfo == null) {
            synchronized (Lxhw_AppInfoDecorator.class) {
                Lxhw_AppInfo lxhw_AppInfo = new Lxhw_AppInfo();
                appInfo = lxhw_AppInfo;
                lxhw_AppInfo.setSecret_key_app(MateUtils.getStringFromMateData(context, ComConstants.SECRET_KEY_APP));
                appInfo.setGame_id(MateUtils.getIntFromMateData(context, ComConstants.GAME_ID));
                appInfo.setChannel_id(MateUtils.getIntFromMateData(context, ComConstants.CHANNEL_ID));
                appInfo.setPay_channel_id(MateUtils.getIntFromMateData(context, ComConstants.CHANNEL_PAY_ID));
                appInfo.setAppsf_dev_key(Util.getString(context, ComConstants.appsf_dev_key));
                appInfo.setFb_senderid(Util.getString(context, ComConstants.fb_senderid));
                appInfo.setFacebook_app_id(Util.getString(context, ComConstants.facebook_app_id));
                appInfo.setSpenvironmentType(MateUtils.getStringFromMateData(context, ComConstants.SPEnvironmentType));
                appInfo.setRec_type(MateUtils.getStringFromMateData(context, ComConstants.REC_TYPE));
                appInfo.setRecaptcha_key(Util.getString(context, ComConstants.recaptcha_key));
            }
        }
    }

    public static String getApp_id() {
        String packageName = Util.getPackageName(Lxhw_XSDK.getInstance().getApplication());
        return TextUtils.isEmpty(packageName) ? "" : packageName;
    }

    public static int getGame_id() {
        Lxhw_AppInfo lxhw_AppInfo = appInfo;
        if (lxhw_AppInfo == null) {
            return 0;
        }
        return lxhw_AppInfo.getGame_id();
    }

    public static int getChannel_id() {
        Lxhw_AppInfo lxhw_AppInfo = appInfo;
        return lxhw_AppInfo == null ? MateUtils.getIntFromMateData(ContextHolder.getContext(), ComConstants.CHANNEL_ID) : lxhw_AppInfo.getChannel_id();
    }

    public static int getChannelPay_id() {
        Lxhw_AppInfo lxhw_AppInfo = appInfo;
        return lxhw_AppInfo == null ? MateUtils.getIntFromMateData(ContextHolder.getContext(), ComConstants.CHANNEL_PAY_ID) : lxhw_AppInfo.getPay_channel_id();
    }

    public static String getSecret_key_app() {
        return appInfo.getSecret_key_app();
    }

    public static String getAppsf_dev_key() {
        String appsf_dev_key = appInfo.getAppsf_dev_key();
        return TextUtils.isEmpty(appsf_dev_key) ? "" : appsf_dev_key;
    }

    public static String getSPEnvironmentType() {
        return appInfo.getSpenvironmentType();
    }

    public static String getRECType() {
        return appInfo.getRec_type();
    }

    public static String getFb_senderid() {
        String fb_senderid = appInfo.getFb_senderid();
        return TextUtils.isEmpty(fb_senderid) ? "" : fb_senderid;
    }

    public static String getFacebook_App_Id() {
        String facebook_app_id = appInfo.getFacebook_app_id();
        return TextUtils.isEmpty(facebook_app_id) ? "" : facebook_app_id;
    }

    public static String getRecaptcha_key() {
        String recaptcha_key = appInfo.getRecaptcha_key();
        return TextUtils.isEmpty(recaptcha_key) ? "" : recaptcha_key;
    }
}
