package com.ffandroid.sdk;

/* loaded from: classes.dex */
public class FF_ThirdLoginInfo {
    private String access_token;
    private String fb_token_for_business;
    private String openid;
    private int third_type;

    public int getThird_type() {
        return this.third_type;
    }

    public void setThird_type(int i) {
        this.third_type = i;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setAccess_token(String str) {
        this.access_token = str;
    }

    public String getOpenid() {
        return this.openid;
    }

    public void setOpenid(String str) {
        this.openid = str;
    }

    public String getFb_token_for_business() {
        return this.fb_token_for_business;
    }

    public void setFb_token_for_business(String str) {
        this.fb_token_for_business = str;
    }

    public String infoTostring() {
        return getThird_type() + " " + getOpenid() + " " + getAccess_token() + " " + getFb_token_for_business();
    }
}
