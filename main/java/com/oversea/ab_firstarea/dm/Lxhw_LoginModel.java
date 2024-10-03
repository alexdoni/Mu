package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnLoginListener;
import com.oversea.ab_firstarea.net.model.LoginBean;

/* loaded from: classes.dex */
public interface Lxhw_LoginModel {
    void guestLogin(OnLoginListener<LoginBean> onLoginListener);

    void login(boolean z, String str, String str2, OnLoginListener<LoginBean> onLoginListener);
}
