package com.oversea.ab_firstplatform.init;

import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes2.dex */
public class Lxhw_AppInfo {
    private static Lxhw_AppInfo mInstance;
    private int channel_id;
    private int pay_channel_id;
    private int game_id = 0;
    private String secret_key_app = "";
    private String sdk_version_code = "";
    private String fb_senderid = "";
    private String appsf_dev_key = "";
    private String facebook_app_id = "";
    private String rec_type = "";
    private String spenvironmentType = "";
    private String recaptcha_key = "";
    private String mainActivity = "";

    public int getGame_id() {
        return this.game_id;
    }

    public void setGame_id(int i) {
        this.game_id = i;
    }

    public int getPay_channel_id() {
        return this.pay_channel_id;
    }

    public void setPay_channel_id(int i) {
        this.pay_channel_id = i;
    }

    public int getChannel_id() {
        return this.channel_id;
    }

    public void setChannel_id(int i) {
        this.channel_id = i;
    }

    public String getSecret_key_app() {
        return this.secret_key_app;
    }

    public void setSecret_key_app(String str) {
        this.secret_key_app = str;
    }

    public String getSdk_version_code() {
        return this.sdk_version_code;
    }

    public void setSdk_version_code(String str) {
        this.sdk_version_code = str;
    }

    public String getFb_senderid() {
        return this.fb_senderid;
    }

    public void setFb_senderid(String str) {
        this.fb_senderid = str;
    }

    public String getAppsf_dev_key() {
        return this.appsf_dev_key;
    }

    public void setAppsf_dev_key(String str) {
        this.appsf_dev_key = str;
    }

    public String getFacebook_app_id() {
        return this.facebook_app_id;
    }

    public void setFacebook_app_id(String str) {
        this.facebook_app_id = str;
    }

    public String getRec_type() {
        return this.rec_type;
    }

    public void setRec_type(String str) {
        this.rec_type = str;
    }

    public String getSpenvironmentType() {
        return this.spenvironmentType;
    }

    public void setSpenvironmentType(String str) {
        this.spenvironmentType = str;
    }

    public String getRecaptcha_key() {
        return this.recaptcha_key;
    }

    public void setRecaptcha_key(String str) {
        this.recaptcha_key = str;
    }

    public String getMainActivity() {
        return this.mainActivity;
    }

    public void setMainActivity(String str) {
        LLog.v_Control("cma =" + str);
        this.mainActivity = str;
    }
}
