package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnUserCenterListener<L extends BaseBean> extends OnBaseListener<UserInfoBean> {
    void onUnBindEmailSuccess(String str);

    void onUnBindPhoneSuccess(String str);
}
