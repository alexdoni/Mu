package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_KfOnlineCenterModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_KfOnlineCenterModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnKfOnlineCenterListener;
import com.oversea.ab_firstarea.dpresenter.PresenterKFOnlineCenter;
import com.oversea.ab_firstarea.dview.Lxhw_KFOnlineCenterView;
import com.oversea.ab_firstarea.net.model.IssueListBean;
import com.oversea.ab_firstarea.net.model.IssueTypeListBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_KfOnlineCenterPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_KfOnlineCenterModel, Lxhw_KFOnlineCenterView> implements OnKfOnlineCenterListener<IssueListBean, IssueTypeListBean>, PresenterKFOnlineCenter {
    private Lxhw_KFOnlineCenterView view;

    public Lxhw_KfOnlineCenterPresenterImpl(Activity activity, Lxhw_KFOnlineCenterView lxhw_KFOnlineCenterView) {
        super(activity, lxhw_KFOnlineCenterView);
        this.view = lxhw_KFOnlineCenterView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_KfOnlineCenterModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_KfOnlineCenterModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
        ((Lxhw_KfOnlineCenterModel) this.model).requestIssueTypeList(this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, IssueTypeListBean issueTypeListBean) {
        Lxhw_KFOnlineCenterView lxhw_KFOnlineCenterView = this.view;
        if (lxhw_KFOnlineCenterView != null) {
            lxhw_KFOnlineCenterView.onReqSuccess(str, issueTypeListBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_KFOnlineCenterView lxhw_KFOnlineCenterView = this.view;
        if (lxhw_KFOnlineCenterView != null) {
            lxhw_KFOnlineCenterView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnKfOnlineCenterListener
    public void onIssueListSuccess(IssueListBean issueListBean) {
        Lxhw_KFOnlineCenterView lxhw_KFOnlineCenterView = this.view;
        if (lxhw_KFOnlineCenterView != null) {
            lxhw_KFOnlineCenterView.onReqIssueListSuccess(issueListBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnKfOnlineCenterListener
    public void onIssueListTypeSuccess(IssueTypeListBean issueTypeListBean) {
        Lxhw_KFOnlineCenterView lxhw_KFOnlineCenterView = this.view;
        if (lxhw_KFOnlineCenterView != null) {
            lxhw_KFOnlineCenterView.onReqSuccess("", issueTypeListBean);
        }
        ManageBean.getInstance().setIssueTypeListBean(issueTypeListBean);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterKFOnlineCenter
    public void doIssueList() {
        ((Lxhw_KfOnlineCenterModel) this.model).requestIssueList(this);
    }
}
