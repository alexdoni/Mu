package com.oversea.ab_firstarea.net.model;

/* loaded from: classes.dex */
public class PlatformLoginAP {
    public static PlatformLoginAP info;
    private String account;

    /* renamed from: pw */
    private String f785pw;

    public static PlatformLoginAP getInstance() {
        if (info == null) {
            info = new PlatformLoginAP();
        }
        return info;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String str) {
        this.account = str;
    }

    public String getPw() {
        return this.f785pw;
    }

    public void setPw(String str) {
        this.f785pw = str;
    }

    public void setAccountAndPw(String str, String str2) {
        this.account = str;
        this.f785pw = str2;
    }
}
