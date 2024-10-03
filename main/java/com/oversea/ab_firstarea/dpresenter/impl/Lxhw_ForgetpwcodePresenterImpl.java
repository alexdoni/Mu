package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener;
import com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode;
import com.oversea.ab_firstarea.dview.Lxhw_ForgetpwcodeView;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwcodePresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_ForgetpwcodeModel, Lxhw_ForgetpwcodeView> implements OnForgetpwcodeListener<BaseBean>, PresenterForgetpwcode {
    private Lxhw_ForgetpwcodeView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_ForgetpwcodePresenterImpl(Activity activity, Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView) {
        super(activity, lxhw_ForgetpwcodeView);
        this.view = lxhw_ForgetpwcodeView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_ForgetpwcodeModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, BaseBean baseBean) {
        Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView = this.view;
        if (lxhw_ForgetpwcodeView != null) {
            lxhw_ForgetpwcodeView.onReqSuccess(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView = this.view;
        if (lxhw_ForgetpwcodeView != null) {
            lxhw_ForgetpwcodeView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void doChangePasswordByPhoneCheckCode(int i, String str, String str2, String str3, String str4) {
        ((Lxhw_ForgetpwcodeModel) this.model).doChangePasswordByPhoneCheckCode(i, str, str2, str3, str4, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void doChangePasswordByEmailCheckCode(int i, String str, String str2, String str3) {
        ((Lxhw_ForgetpwcodeModel) this.model).doChangePasswordByEmailCheckCode(i, str, str2, str3, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void changePasswordByPhoneSendCode(int i, String str, String str2, String str3) {
        ((Lxhw_ForgetpwcodeModel) this.model).changePasswordByPhoneSendCode(i, str, str2, str3, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void changePasswordByEmailSendCode(int i, String str, String str2) {
        ((Lxhw_ForgetpwcodeModel) this.model).changePasswordByEmailSendCode(i, str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void doUnbindPhoneSendCode(String str, String str2) {
        ((Lxhw_ForgetpwcodeModel) this.model).doUnbindPhoneSendCode(str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void doUnbindEmailSendCode(String str) {
        ((Lxhw_ForgetpwcodeModel) this.model).doUnbindEmailSendCode(str, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void doUnbindPhone(String str, String str2, String str3) {
        ((Lxhw_ForgetpwcodeModel) this.model).doUnbindPhone(str, str2, str3, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwcode
    public void doUnbindEmail(String str, String str2) {
        ((Lxhw_ForgetpwcodeModel) this.model).doUnbindEmail(str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener
    public void onReqCodeSuccess() {
        Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView = this.view;
        if (lxhw_ForgetpwcodeView != null) {
            lxhw_ForgetpwcodeView.onReqCodeSuccess();
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener
    public void jumpResetPassword() {
        Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView = this.view;
        if (lxhw_ForgetpwcodeView != null) {
            lxhw_ForgetpwcodeView.jumpResetPassword();
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener
    public void onReqUnbindPhoneSuccess() {
        Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView = this.view;
        if (lxhw_ForgetpwcodeView != null) {
            lxhw_ForgetpwcodeView.onReqUnbindPhoneSuccess();
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener
    public void onReqUnbindEmailSuccess() {
        Lxhw_ForgetpwcodeView lxhw_ForgetpwcodeView = this.view;
        if (lxhw_ForgetpwcodeView != null) {
            lxhw_ForgetpwcodeView.onReqUnbindEmailSuccess();
        }
    }
}
