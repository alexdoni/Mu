package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstarea.net.model.EmailConfirmBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnEmailConfirmListener<L extends BaseBean> extends OnBaseListener<EmailConfirmBean> {
    void onReqCodeSuccess();
}
