package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import android.text.TextUtils;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.Lxhw_ForgetpwAccountModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwAccountModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnForgetpwAccountListener;
import com.oversea.ab_firstarea.dpresenter.PresenterForgetpwAccount;
import com.oversea.ab_firstarea.dview.Lxhw_ForgetpwAccountView;
import com.oversea.ab_firstarea.net.model.UserBindInfoBean;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwAccountPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_ForgetpwAccountModel, Lxhw_ForgetpwAccountView> implements OnForgetpwAccountListener<UserBindInfoBean>, PresenterForgetpwAccount {
    private Lxhw_ForgetpwAccountView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_ForgetpwAccountPresenterImpl(Activity activity, Lxhw_ForgetpwAccountView lxhw_ForgetpwAccountView) {
        super(activity, lxhw_ForgetpwAccountView);
        this.view = lxhw_ForgetpwAccountView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwAccountModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_ForgetpwAccountModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, UserBindInfoBean userBindInfoBean) {
        if (userBindInfoBean != null) {
            if (TextUtils.isEmpty(userBindInfoBean.getData().getEmail()) && TextUtils.isEmpty(userBindInfoBean.getData().getPhone())) {
                Lxhw_ForgetpwAccountView lxhw_ForgetpwAccountView = this.view;
                if (lxhw_ForgetpwAccountView != null) {
                    lxhw_ForgetpwAccountView.onReqSuccess(str, userBindInfoBean);
                    return;
                }
                return;
            }
            Lxhw_AreaPlatform.getInstance().setBindInfoBean(userBindInfoBean);
            Lxhw_DialogManage.getInstance().enterForgetPwChooseCenter(this.mActivity, userBindInfoBean.getData().getPlatform_account(), userBindInfoBean.getData().getEmail(), userBindInfoBean.getData().getPhone_prefix(), userBindInfoBean.getData().getPhone());
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_ForgetpwAccountView lxhw_ForgetpwAccountView = this.view;
        if (lxhw_ForgetpwAccountView != null) {
            lxhw_ForgetpwAccountView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterForgetpwAccount
    public void doForgetPwAccount(String str) {
        ((Lxhw_ForgetpwAccountModel) this.model).doForgetPwAccount(str, this);
    }
}
