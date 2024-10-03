package com.ffandroid.sdk;

import android.text.TextUtils;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;

/* loaded from: classes.dex */
public class FF_XUserInfo extends Lxhw_XUserInfo {
    private int uid = 0;
    private String account = "";
    private int accountType = 0;
    private String token = "";

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public int getUid() {
        return this.uid;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public void setUid(int i) {
        this.uid = i;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public String getAccount() {
        return this.account;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public void setAccount(String str) {
        this.account = str;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public int getAccountType() {
        return this.accountType;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public void setAccountType(int i) {
        this.accountType = i;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public String getToken() {
        if (TextUtils.isEmpty(this.token)) {
            this.token = "";
        }
        return this.token;
    }

    @Override // com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo
    public void setToken(String str) {
        this.token = str;
    }
}
