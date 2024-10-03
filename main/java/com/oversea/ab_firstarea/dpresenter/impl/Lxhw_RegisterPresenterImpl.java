package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.Lxhw_RegisterModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_RegisterModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnRegisterListener;
import com.oversea.ab_firstarea.dpresenter.PresenterRegister;
import com.oversea.ab_firstarea.dview.Lxhw_RegisterView;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.PlatformLoginAP;
import com.oversea.ab_firstarea.net.model.RegisterBean;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.LoginInfo;
import com.oversea.ab_firstplatform.model.LoginInfoManage;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;

/* loaded from: classes.dex */
public class Lxhw_RegisterPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_RegisterModel, Lxhw_RegisterView> implements OnRegisterListener<RegisterBean>, PresenterRegister {
    private Lxhw_RegisterView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_RegisterPresenterImpl(Activity activity, Lxhw_RegisterView lxhw_RegisterView) {
        super(activity, lxhw_RegisterView);
        this.view = lxhw_RegisterView;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnRegisterListener
    public void onLoginSuccess(LoginBean loginBean) {
        this.view.onReqSuccess("", "");
        ImageUtils.setSharePreferences(Lxhw_XSDK.getInstance().getContext(), Constants.SDK_LOGIN_TYPE, "1");
        AreaSdk.getInstance().onAuthResult(loginBean);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterRegister
    public void doRegister(String str, String str2) {
        ((Lxhw_RegisterModel) this.model).register(str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterRegister
    public void jumpServerAgreement() {
        Lxhw_DialogManage.getInstance().showBigTextCommon(this.mActivity, "1");
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterRegister
    public void jumpPrivacyAgreement() {
        Lxhw_DialogManage.getInstance().showBigTextCommon(this.mActivity, "2");
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_RegisterModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_RegisterModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, RegisterBean registerBean) {
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_ACCOUNT, registerBean.getData().getPlatform_account());
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_PASSWORD, registerBean.getData().getPwd());
        PlatformLoginAP.getInstance().setAccountAndPw(registerBean.getData().getPlatform_account(), registerBean.getData().getPwd());
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setP(registerBean.getData().getPwd());
        loginInfo.setU(registerBean.getData().getPlatform_account());
        LoginInfoManage.getInstance().updataLoginInfos(this.mActivity, loginInfo);
        ((Lxhw_RegisterModel) this.model).autoLogin(registerBean.getData().getPlatform_account(), registerBean.getData().getPwd(), this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_RegisterView lxhw_RegisterView = this.view;
        if (lxhw_RegisterView != null) {
            lxhw_RegisterView.onReqFail(str, baseBean);
        }
    }
}
