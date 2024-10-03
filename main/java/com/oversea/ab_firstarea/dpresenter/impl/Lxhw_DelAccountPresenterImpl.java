package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_DelAccountModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_DelAccountModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnDelAccountListener;
import com.oversea.ab_firstarea.dpresenter.PresenterDelAccount;
import com.oversea.ab_firstarea.dview.Lxhw_DelAccountView;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_DelAccountPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_DelAccountModel, Lxhw_DelAccountView> implements OnDelAccountListener<BaseBean>, PresenterDelAccount {
    private String pwd;
    private Lxhw_DelAccountView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_DelAccountPresenterImpl(Activity activity, Lxhw_DelAccountView lxhw_DelAccountView) {
        super(activity, lxhw_DelAccountView);
        this.view = lxhw_DelAccountView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_DelAccountModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_DelAccountModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, BaseBean baseBean) {
        Lxhw_DelAccountView lxhw_DelAccountView = this.view;
        if (lxhw_DelAccountView != null) {
            lxhw_DelAccountView.onReqSuccess(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_DelAccountView lxhw_DelAccountView = this.view;
        if (lxhw_DelAccountView != null) {
            lxhw_DelAccountView.onReqFail("", baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterDelAccount
    public void delAccount() {
        ((Lxhw_DelAccountModel) this.model).delAccount(this);
    }
}
