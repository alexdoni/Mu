package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnRegisterListener;
import com.oversea.ab_firstarea.net.model.RegisterBean;

/* loaded from: classes.dex */
public interface Lxhw_RegisterModel {
    void autoLogin(String str, String str2, OnRegisterListener<RegisterBean> onRegisterListener);

    void register(String str, String str2, OnRegisterListener<RegisterBean> onRegisterListener);
}
