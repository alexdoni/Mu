package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnResetPasswordListener;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface Lxhw_ResetPasswordModel {
    void doChangePasswordByEmail(int i, String str, String str2, String str3, String str4, String str5, OnResetPasswordListener<BaseBean> onResetPasswordListener);

    void doChangePasswordByPhone(int i, String str, String str2, String str3, String str4, String str5, String str6, OnResetPasswordListener<BaseBean> onResetPasswordListener);
}
