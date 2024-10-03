package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnForgetpwchooseListener;
import com.oversea.ab_firstarea.net.model.ForgetpwchooseBean;

/* loaded from: classes.dex */
public interface Lxhw_ForgetpwchooseModel {
    void changePasswordByEmailSendCode(int i, String str, String str2, OnForgetpwchooseListener<ForgetpwchooseBean> onForgetpwchooseListener);

    void changePasswordByPhoneSendCode(int i, String str, String str2, String str3, OnForgetpwchooseListener<ForgetpwchooseBean> onForgetpwchooseListener);
}
