package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnForgetpwcodeListener<L extends BaseBean> extends OnBaseListener<BaseBean> {
    void jumpResetPassword();

    void onReqCodeSuccess();

    void onReqUnbindEmailSuccess();

    void onReqUnbindPhoneSuccess();
}
