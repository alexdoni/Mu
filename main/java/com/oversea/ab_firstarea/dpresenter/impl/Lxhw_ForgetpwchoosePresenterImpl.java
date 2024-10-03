package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_ForgetpwchooseModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwchooseModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnForgetpwchooseListener;
import com.oversea.ab_firstarea.dpresenter.PresenterForgetpwchoose;
import com.oversea.ab_firstarea.dview.Lxhw_ForgetpwchooseView;
import com.oversea.ab_firstarea.net.model.ForgetpwchooseBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwchoosePresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_ForgetpwchooseModel, Lxhw_ForgetpwchooseView> implements OnForgetpwchooseListener<ForgetpwchooseBean>, PresenterForgetpwchoose {
    private Lxhw_ForgetpwchooseView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_ForgetpwchoosePresenterImpl(Activity activity, Lxhw_ForgetpwchooseView lxhw_ForgetpwchooseView) {
        super(activity, lxhw_ForgetpwchooseView);
        this.view = lxhw_ForgetpwchooseView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwchooseModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_ForgetpwchooseModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, ForgetpwchooseBean forgetpwchooseBean) {
        Lxhw_ForgetpwchooseView lxhw_ForgetpwchooseView = this.view;
        if (lxhw_ForgetpwchooseView != null) {
            lxhw_ForgetpwchooseView.onReqSuccess(str, forgetpwchooseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_ForgetpwchooseView lxhw_ForgetpwchooseView = this.view;
        if (lxhw_ForgetpwchooseView != null) {
            lxhw_ForgetpwchooseView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwchoose
    public void changePasswordByPhoneSendCode(int i, String str, String str2, String str3) {
        ((Lxhw_ForgetpwchooseModel) this.model).changePasswordByPhoneSendCode(i, str, str2, str3, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwchoose
    public void changePasswordByEmailSendCode(int i, String str, String str2) {
        ((Lxhw_ForgetpwchooseModel) this.model).changePasswordByEmailSendCode(i, str, str2, this);
    }
}
