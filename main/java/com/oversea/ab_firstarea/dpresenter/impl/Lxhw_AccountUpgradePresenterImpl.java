package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_AccountUpgradeModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_AccountUpgradeModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnAccountUpgradeListener;
import com.oversea.ab_firstarea.dpresenter.PresenterAccountUpgrade;
import com.oversea.ab_firstarea.dview.Lxhw_AccountUpgradeView;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_AccountUpgradePresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_AccountUpgradeModel, Lxhw_AccountUpgradeView> implements OnAccountUpgradeListener<BaseBean>, PresenterAccountUpgrade {
    private Lxhw_AccountUpgradeView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_AccountUpgradePresenterImpl(Activity activity, Lxhw_AccountUpgradeView lxhw_AccountUpgradeView) {
        super(activity, lxhw_AccountUpgradeView);
        this.view = lxhw_AccountUpgradeView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_AccountUpgradeModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_AccountUpgradeModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, BaseBean baseBean) {
        Lxhw_AccountUpgradeView lxhw_AccountUpgradeView = this.view;
        if (lxhw_AccountUpgradeView != null) {
            lxhw_AccountUpgradeView.onReqSuccess(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_AccountUpgradeView lxhw_AccountUpgradeView = this.view;
        if (lxhw_AccountUpgradeView != null) {
            lxhw_AccountUpgradeView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterAccountUpgrade
    public void doUpgradeAccount(String str, String str2) {
        ((Lxhw_AccountUpgradeModel) this.model).doUpgradeAccount("", str, str2, this);
    }
}
