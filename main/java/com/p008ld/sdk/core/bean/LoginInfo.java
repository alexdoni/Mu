package com.p008ld.sdk.core.bean;

/* loaded from: classes2.dex */
public class LoginInfo {
    public String auth;
    public LoginMode loginMode;
    public String password;
    public boolean rememberPwd = false;
    public String token;
    public String uid;
    public String username;

    public boolean isUserNameMode() {
        return this.loginMode == LoginMode.USERNAME;
    }
}
