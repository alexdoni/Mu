package com.oversea.ab_firstplatform.verify;

import android.text.TextUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;

/* loaded from: classes2.dex */
public class Lxhw_XUserInfo {
    public static Lxhw_XUserInfo mInstance;
    private String countryCode;
    private String domain;

    /* renamed from: en */
    private boolean f792en;
    private boolean event_status;
    private int gameUid;
    private int loginCount;
    private String recommend_server;
    private int sdkId;
    private int uid = 0;
    private String account = "";
    private int accountType = 0;
    private String token = "";

    public int getLoginCount() {
        return this.loginCount;
    }

    public void setLoginCount(int i) {
        this.loginCount = i;
    }

    public boolean isEvent_status() {
        return this.event_status;
    }

    public void setEvent_status(boolean z) {
        this.event_status = z;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getRecommend_server() {
        return this.recommend_server;
    }

    public void setRecommend_server(String str) {
        this.recommend_server = str;
    }

    public int getSdkId() {
        return this.sdkId;
    }

    public void setSdkId(int i) {
        this.sdkId = i;
    }

    public int getGameUid() {
        return this.gameUid;
    }

    public void setGameUid(int i) {
        this.gameUid = i;
    }

    public boolean isEn() {
        return this.f792en;
    }

    public void setEn(boolean z) {
        this.f792en = z;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public static Lxhw_XUserInfo getInstance() {
        if (mInstance == null) {
            mInstance = new Lxhw_XUserInfo();
        }
        return mInstance;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public int getAccountType() {
        return this.accountType;
    }

    public void setAccountType(int i) {
        this.accountType = i;
    }

    public String getToken() {
        if (TextUtils.isEmpty(this.token)) {
            this.token = "";
        }
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void cleanUserInfo() {
        LLog.v_noControl("cleanUserInfo");
        setUid(0);
        setSdkId(0);
        setGameUid(0);
        setToken("");
        setAccountType(0);
        setEn(false);
        setDomain("");
    }
}
