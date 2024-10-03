package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.Lxhw_LoginModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_LoginModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnLoginListener;
import com.oversea.ab_firstarea.dpresenter.PresenterLogin;
import com.oversea.ab_firstarea.dview.Lxhw_LoginView;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.PlatformLoginAP;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.LoginInfo;
import com.oversea.ab_firstplatform.model.LoginInfoManage;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;

/* loaded from: classes.dex */
public class Lxhw_LoginPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_LoginModel, Lxhw_LoginView> implements OnLoginListener<LoginBean>, PresenterLogin {
    private String account;
    boolean isremember;
    private String pwd;
    private Lxhw_LoginView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_LoginPresenterImpl(Activity activity, Lxhw_LoginView lxhw_LoginView) {
        super(activity, lxhw_LoginView);
        this.account = "";
        this.pwd = "";
        this.isremember = false;
        this.view = lxhw_LoginView;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterLogin
    public void doLogin(boolean z, String str, String str2) {
        this.account = str;
        this.pwd = str2;
        this.isremember = z;
        ((Lxhw_LoginModel) this.model).login(z, str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterLogin
    public void jumpForgetPwdCenter() {
        Lxhw_DialogManage.getInstance().enterForgetPwAccountCenter(this.mActivity);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterLogin
    public void loginVisitor() {
        ((Lxhw_LoginModel) this.model).guestLogin(this);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_LoginModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_LoginModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, LoginBean loginBean) {
        Lxhw_LoginView lxhw_LoginView = this.view;
        if (lxhw_LoginView != null) {
            lxhw_LoginView.onReqSuccess(str, loginBean);
        }
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_ACCOUNT, this.account);
        PlatformLoginAP.getInstance().setAccountAndPw(loginBean.getData().getPlatform_account(), this.pwd);
        if (!this.isremember) {
            this.pwd = "";
            ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_PASSWORD, "");
        } else {
            ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_PASSWORD, this.pwd);
        }
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_LOGIN_TYPE, "1");
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_IS_REMEMBER_PASSWORD, this.isremember);
        AreaSdk.getInstance().onAuthResult(loginBean);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setU(this.account);
        loginInfo.setP(this.pwd);
        LoginInfoManage.getInstance().updataLoginInfos(this.mActivity, loginInfo);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnLoginListener
    public void onReqGuestSuccess(LoginBean loginBean) {
        Lxhw_LoginView lxhw_LoginView = this.view;
        if (lxhw_LoginView != null) {
            lxhw_LoginView.onReqSuccess("", loginBean);
        }
        if (loginBean.getData() != null && loginBean.getData().getAccount_type() == 1) {
            ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_LOGIN_TYPE, "1");
        } else {
            ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_LOGIN_TYPE, "2");
        }
        AreaSdk.getInstance().onAuthResult(loginBean);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_LoginView lxhw_LoginView = this.view;
        if (lxhw_LoginView != null) {
            lxhw_LoginView.onReqFail("", baseBean);
            Lxhw_XSDK.getInstance().onAuthResult(-1);
        }
    }
}
