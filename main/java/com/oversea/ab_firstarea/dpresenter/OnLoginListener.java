package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnLoginListener<L extends BaseBean> extends OnBaseListener<LoginBean> {
    void onReqGuestSuccess(LoginBean loginBean);
}
