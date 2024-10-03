package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstarea.net.model.MobileConfirmBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnMobileConfirmListener<L extends BaseBean> extends OnBaseListener<MobileConfirmBean> {
    void onReqCodeSuccess();
}
