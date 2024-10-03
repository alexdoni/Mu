package com.oversea.ab_firstarea.dpresenter;

import com.oversea.ab_firstarea.net.model.IssueTypeListBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public interface OnKfOnlineCenterListener<L extends BaseBean, T extends BaseBean> extends OnBaseListener<IssueTypeListBean> {
    void onIssueListSuccess(L l);

    void onIssueListTypeSuccess(T t);
}
