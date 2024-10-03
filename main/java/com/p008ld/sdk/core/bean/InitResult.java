package com.p008ld.sdk.core.bean;

import java.util.List;

/* loaded from: classes2.dex */
public class InitResult {
    public int appcode;
    public String appdownloadurl;
    public String appupdatecontent;
    public String bbsUrl;
    public String email;
    public String feedbackUrl;

    /* renamed from: id */
    public int f682id;
    public int iscoupon;
    public int isldbit;
    public int isonlinenet;
    public int ispaylimit;
    public int ispayreport;
    public int isqrcode;
    public int isrealauth;
    public int isupdate;
    public int ldbitPay;
    public String ldqdownloadlink1;
    public String ldqdownloadlink2;
    public String ldqimgurl;
    public int ldstoregameid;
    public String ldyunDownloadUrl;
    public String ldyunMnqBgUrl;
    public String ldyunPhoneBgUrl;

    /* renamed from: qq */
    public String f683qq;
    public String qqurl;
    public int quickReg;
    public int screenshotTips;
    public SdkUpdateInfo sdkUpdateInfo;
    public int status;
    public String tabDesc;
    public String tabIcon;
    public List<TabInfo> tabList;
    public String tabName;
    public String tabUrl;
    public String tel;
    public String verifyimgUrl;
    public String wechat;
    public String wechatMnqUrl;
    public String wechatPhoneUrl;

    /* loaded from: classes2.dex */
    public static class SdkUpdateInfo {
        public static final int FORCE_UPDATE_TYPE = 0;
        public static final int SILENCE_UPDATE_TYPE = 1;
        public String createTime;
        public String downloadUrl;

        /* renamed from: id */
        public int f684id;
        public int status;
        public String updateContent;
        public String updateTime;
        public int updateType;
        public String version;
        public int versionCode;
    }

    /* loaded from: classes2.dex */
    public static class TabInfo {
        public String tabDesc;
        public String tabIcon;
        public String tabName;
        public String tabUrl;
    }
}
