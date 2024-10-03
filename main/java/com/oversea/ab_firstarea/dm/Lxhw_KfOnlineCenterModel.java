package com.oversea.ab_firstarea.dm;

import com.oversea.ab_firstarea.dpresenter.OnKfOnlineCenterListener;
import com.oversea.ab_firstarea.net.model.IssueListBean;
import com.oversea.ab_firstarea.net.model.IssueTypeListBean;

/* loaded from: classes.dex */
public interface Lxhw_KfOnlineCenterModel {
    void requestIssueList(OnKfOnlineCenterListener<IssueListBean, IssueTypeListBean> onKfOnlineCenterListener);

    void requestIssueTypeList(OnKfOnlineCenterListener<IssueListBean, IssueTypeListBean> onKfOnlineCenterListener);
}
