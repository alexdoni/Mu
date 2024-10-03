package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.Lxhw_ChangePsdDiaModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ChangePsdDiaModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnChangePsdDiaListener;
import com.oversea.ab_firstarea.dpresenter.PresenterChangePsdDia;
import com.oversea.ab_firstarea.dview.Lxhw_ChangePsdDiaView;
import com.oversea.ab_firstarea.net.model.ChangePsdDiaBean;
import com.oversea.ab_firstarea.net.model.PlatformLoginAP;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.LoginInfo;
import com.oversea.ab_firstplatform.model.LoginInfoManage;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;

/* loaded from: classes.dex */
public class Lxhw_ChangePsdDiaPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_ChangePsdDiaModel, Lxhw_ChangePsdDiaView> implements OnChangePsdDiaListener<ChangePsdDiaBean>, PresenterChangePsdDia {
    private String pwd;
    private Lxhw_ChangePsdDiaView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_ChangePsdDiaPresenterImpl(Activity activity, Lxhw_ChangePsdDiaView lxhw_ChangePsdDiaView) {
        super(activity, lxhw_ChangePsdDiaView);
        this.view = lxhw_ChangePsdDiaView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_ChangePsdDiaModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_ChangePsdDiaModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, ChangePsdDiaBean changePsdDiaBean) {
        Lxhw_ChangePsdDiaView lxhw_ChangePsdDiaView = this.view;
        if (lxhw_ChangePsdDiaView != null) {
            lxhw_ChangePsdDiaView.onReqSuccess("", changePsdDiaBean);
        }
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_ACCOUNT, changePsdDiaBean.getData().getPlatform_account());
        ImageUtils.setSharePreferences(this.mActivity, Constants.SDK_PASSWORD, this.pwd);
        PlatformLoginAP.getInstance().setAccountAndPw(changePsdDiaBean.getData().getPlatform_account(), this.pwd);
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setU(changePsdDiaBean.getData().getPlatform_account());
        loginInfo.setP(this.pwd);
        LoginInfoManage.getInstance().isVisitorAccountModify(this.mActivity, loginInfo);
        LoginInfoManage.getInstance().updataLoginInfos(this.mActivity, loginInfo);
        Lxhw_DialogManage.getInstance().removeFragment(this.mActivity, "UserCenterDialog");
        Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_ChangePsdDiaView lxhw_ChangePsdDiaView = this.view;
        if (lxhw_ChangePsdDiaView != null) {
            lxhw_ChangePsdDiaView.onReqFail("", baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterChangePsdDia
    public void doChangePassword(String str, String str2) {
        this.pwd = str2;
        ((Lxhw_ChangePsdDiaModel) this.model).doChangePassword(str, str2, this);
    }
}
