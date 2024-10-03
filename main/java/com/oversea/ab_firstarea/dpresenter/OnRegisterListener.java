package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.RegisterBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnRegisterListener<R extends BaseBean> extends OnBaseListener<RegisterBean> {
    void onLoginSuccess(LoginBean loginBean);
}
