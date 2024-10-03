package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_MobileConfirmModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_MobileConfirmModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnMobileConfirmListener;
import com.oversea.ab_firstarea.dpresenter.PresenterMobileConfirm;
import com.oversea.ab_firstarea.dview.Lxhw_MobileConfirmView;
import com.oversea.ab_firstarea.net.model.MobileConfirmBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_MobileConfirmPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_MobileConfirmModel, Lxhw_MobileConfirmView> implements OnMobileConfirmListener<MobileConfirmBean>, PresenterMobileConfirm {
    private Lxhw_MobileConfirmView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_MobileConfirmPresenterImpl(Activity activity, Lxhw_MobileConfirmView lxhw_MobileConfirmView) {
        super(activity, lxhw_MobileConfirmView);
        this.view = lxhw_MobileConfirmView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_MobileConfirmModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_MobileConfirmModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, MobileConfirmBean mobileConfirmBean) {
        Lxhw_MobileConfirmView lxhw_MobileConfirmView = this.view;
        if (lxhw_MobileConfirmView != null) {
            lxhw_MobileConfirmView.onReqSuccess(str, mobileConfirmBean.getMessage());
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_MobileConfirmView lxhw_MobileConfirmView = this.view;
        if (lxhw_MobileConfirmView != null) {
            lxhw_MobileConfirmView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterMobileConfirm
    public void doBindPhoneSendCode(String str, String str2) {
        ((Lxhw_MobileConfirmModel) this.model).doBindPhoneSendCode(str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterMobileConfirm
    public void doBindPhone(String str, String str2, String str3) {
        ((Lxhw_MobileConfirmModel) this.model).doBindPhone(str, str2, str3, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnMobileConfirmListener
    public void onReqCodeSuccess() {
        Lxhw_MobileConfirmView lxhw_MobileConfirmView = this.view;
        if (lxhw_MobileConfirmView != null) {
            lxhw_MobileConfirmView.onReqCodeSuccess();
        }
    }
}
