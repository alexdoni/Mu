package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_EmailConfirmModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_EmailConfirmModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnEmailConfirmListener;
import com.oversea.ab_firstarea.dpresenter.PresenterEmailConfirm;
import com.oversea.ab_firstarea.dview.Lxhw_EmailConfirmView;
import com.oversea.ab_firstarea.net.model.EmailConfirmBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_EmailConfirmPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_EmailConfirmModel, Lxhw_EmailConfirmView> implements OnEmailConfirmListener<EmailConfirmBean>, PresenterEmailConfirm {
    private String type;
    private Lxhw_EmailConfirmView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_EmailConfirmPresenterImpl(Activity activity, Lxhw_EmailConfirmView lxhw_EmailConfirmView) {
        super(activity, lxhw_EmailConfirmView);
        this.type = "";
        this.view = lxhw_EmailConfirmView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_EmailConfirmModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_EmailConfirmModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, EmailConfirmBean emailConfirmBean) {
        Lxhw_EmailConfirmView lxhw_EmailConfirmView = this.view;
        if (lxhw_EmailConfirmView != null) {
            lxhw_EmailConfirmView.onReqSuccess(str, "");
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_EmailConfirmView lxhw_EmailConfirmView = this.view;
        if (lxhw_EmailConfirmView != null) {
            lxhw_EmailConfirmView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterEmailConfirm
    public void doBindEmailSendCode(String str) {
        ((Lxhw_EmailConfirmModel) this.model).doBindEmailSendCode(str, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterEmailConfirm
    public void doBindEmail(String str, String str2) {
        ((Lxhw_EmailConfirmModel) this.model).doBindEmail(str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnEmailConfirmListener
    public void onReqCodeSuccess() {
        Lxhw_EmailConfirmView lxhw_EmailConfirmView = this.view;
        if (lxhw_EmailConfirmView != null) {
            lxhw_EmailConfirmView.onReqCodeSuccess();
        }
    }
}
