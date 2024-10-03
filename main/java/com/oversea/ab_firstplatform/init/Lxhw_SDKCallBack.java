package com.oversea.ab_firstplatform.init;

import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;

/* loaded from: classes2.dex */
public interface Lxhw_SDKCallBack {
    void onBindResult(boolean z);

    void onDianzanResult(boolean z);

    void onInitResult(boolean z);

    void onLoginFail();

    void onLoginResult(Lxhw_XUserInfo lxhw_XUserInfo);

    void onPayResult(int i, String str);

    void onPingFenResult(boolean z);

    void onShareResult(boolean z);

    void onSwitchAccount();
}
