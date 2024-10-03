package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface Lxhw_ForgetpwcodeModel {
    void changePasswordByEmailSendCode(int i, String str, String str2, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void changePasswordByPhoneSendCode(int i, String str, String str2, String str3, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void doChangePasswordByEmailCheckCode(int i, String str, String str2, String str3, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void doChangePasswordByPhoneCheckCode(int i, String str, String str2, String str3, String str4, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void doUnbindEmail(String str, String str2, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void doUnbindEmailSendCode(String str, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void doUnbindPhone(String str, String str2, String str3, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);

    void doUnbindPhoneSendCode(String str, String str2, OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener);
}
