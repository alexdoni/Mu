package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_ResetPasswordModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ResetPasswordModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnResetPasswordListener;
import com.oversea.ab_firstarea.dpresenter.PresenterResetPassword;
import com.oversea.ab_firstarea.dview.Lxhw_ResetPasswordView;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_ResetPasswordPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_ResetPasswordModel, Lxhw_ResetPasswordView> implements OnResetPasswordListener<BaseBean>, PresenterResetPassword {
    private Lxhw_ResetPasswordView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_ResetPasswordPresenterImpl(Activity activity, Lxhw_ResetPasswordView lxhw_ResetPasswordView) {
        super(activity, lxhw_ResetPasswordView);
        this.view = lxhw_ResetPasswordView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_ResetPasswordModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_ResetPasswordModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, BaseBean baseBean) {
        Lxhw_ResetPasswordView lxhw_ResetPasswordView = this.view;
        if (lxhw_ResetPasswordView != null) {
            lxhw_ResetPasswordView.onReqSuccess(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_ResetPasswordView lxhw_ResetPasswordView = this.view;
        if (lxhw_ResetPasswordView != null) {
            lxhw_ResetPasswordView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterResetPassword
    public void doChangePasswordByPhone(int i, String str, String str2, String str3, String str4, String str5, String str6) {
        ((Lxhw_ResetPasswordModel) this.model).doChangePasswordByPhone(i, str, str2, str3, str4, str5, str6, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterResetPassword
    public void doChangePasswordByEmail(int i, String str, String str2, String str3, String str4, String str5) {
        ((Lxhw_ResetPasswordModel) this.model).doChangePasswordByEmail(i, str, str2, str3, str4, str5, this);
    }
}
