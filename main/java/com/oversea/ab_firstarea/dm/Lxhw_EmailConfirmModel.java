package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnEmailConfirmListener;
import com.oversea.ab_firstarea.net.model.EmailConfirmBean;

/* loaded from: classes.dex */
public interface Lxhw_EmailConfirmModel {
    void doBindEmail(String str, String str2, OnEmailConfirmListener<EmailConfirmBean> onEmailConfirmListener);

    void doBindEmailSendCode(String str, OnEmailConfirmListener<EmailConfirmBean> onEmailConfirmListener);
}
