package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnUserCenterListener;
import com.oversea.ab_firstarea.net.model.UserInfoBean;

/* loaded from: classes.dex */
public interface Lxhw_UserCenterModel {
    void doGetUserInfo(OnUserCenterListener<UserInfoBean> onUserCenterListener);

    void doUnbindEmailSendCode(String str, OnUserCenterListener<UserInfoBean> onUserCenterListener);

    void doUnbindPhoneSendCode(String str, String str2, OnUserCenterListener<UserInfoBean> onUserCenterListener);
}
