package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_RollbackAccountModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_RollbackAccountModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnRollbackAccountListener;
import com.oversea.ab_firstarea.dpresenter.PresenterRollbackAccount;
import com.oversea.ab_firstarea.dview.Lxhw_RollbackAccountView;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_RollbackAccountPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_RollbackAccountModel, Lxhw_RollbackAccountView> implements OnRollbackAccountListener<BaseBean>, PresenterRollbackAccount {
    private Lxhw_RollbackAccountView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_RollbackAccountPresenterImpl(Activity activity, Lxhw_RollbackAccountView lxhw_RollbackAccountView) {
        super(activity, lxhw_RollbackAccountView);
        this.view = lxhw_RollbackAccountView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_RollbackAccountModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_RollbackAccountModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, BaseBean baseBean) {
        Lxhw_RollbackAccountView lxhw_RollbackAccountView = this.view;
        if (lxhw_RollbackAccountView != null) {
            lxhw_RollbackAccountView.onReqSuccess(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_RollbackAccountView lxhw_RollbackAccountView = this.view;
        if (lxhw_RollbackAccountView != null) {
            lxhw_RollbackAccountView.onReqFail("", baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterRollbackAccount
    public void rollback() {
        ((Lxhw_RollbackAccountModel) this.model).rollback(this);
    }
}
