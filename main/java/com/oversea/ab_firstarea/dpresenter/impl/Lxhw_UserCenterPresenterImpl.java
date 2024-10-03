package com.oversea.ab_firstarea.dpresenter.impl;

import android.app.Activity;
import com.oversea.ab_firstarea.dm.Lxhw_UserCenterModel;
import com.oversea.ab_firstarea.dm.impl.Lxhw_UserCenterModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnUserCenterListener;
import com.oversea.ab_firstarea.dpresenter.PresenterUserCenter;
import com.oversea.ab_firstarea.dview.Lxhw_UserCenterView;
import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstplatform.model.BaseBean;

/* loaded from: classes.dex */
public class Lxhw_UserCenterPresenterImpl extends Lxhw_BasePresenterImpl<Lxhw_UserCenterModel, Lxhw_UserCenterView> implements OnUserCenterListener<UserInfoBean>, PresenterUserCenter {
    private String type;
    private Lxhw_UserCenterView view;

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void doRequestCom() {
    }

    public Lxhw_UserCenterPresenterImpl(Activity activity, Lxhw_UserCenterView lxhw_UserCenterView) {
        super(activity, lxhw_UserCenterView);
        this.type = "";
        this.view = lxhw_UserCenterView;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oversea.ab_firstarea.dm.impl.Lxhw_UserCenterModelImpl, M] */
    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void initModel() {
        this.model = new Lxhw_UserCenterModelImpl();
    }

    @Override // com.oversea.ab_firstarea.dpresenter.impl.Lxhw_BasePresenterImpl, com.oversea.ab_firstarea.dpresenter.PresenterBasse
    public void onDestroy() {
        this.view = null;
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqSuccess(String str, UserInfoBean userInfoBean) {
        Lxhw_UserCenterView lxhw_UserCenterView = this.view;
        if (lxhw_UserCenterView != null) {
            lxhw_UserCenterView.onReqSuccess(str, userInfoBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
    public void onReqFail(String str, BaseBean baseBean) {
        Lxhw_UserCenterView lxhw_UserCenterView = this.view;
        if (lxhw_UserCenterView != null) {
            lxhw_UserCenterView.onReqFail(str, baseBean);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterUserCenter
    public void doGetUserInfo() {
        this.type = "doGetUserInfo";
        ((Lxhw_UserCenterModel) this.model).doGetUserInfo(this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterUserCenter
    public void doUnbindPhoneSendCode(String str, String str2) {
        this.type = "doUnbindPhoneSendCode";
        ((Lxhw_UserCenterModel) this.model).doUnbindPhoneSendCode(str, str2, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.PresenterUserCenter
    public void doUnbindEmailSendCode(String str) {
        this.type = "doUnbindEmailSendCode";
        ((Lxhw_UserCenterModel) this.model).doUnbindEmailSendCode(str, this);
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnUserCenterListener
    public void onUnBindPhoneSuccess(String str) {
        Lxhw_UserCenterView lxhw_UserCenterView = this.view;
        if (lxhw_UserCenterView != null) {
            lxhw_UserCenterView.onUnBindPhoneSuccess(str);
        }
    }

    @Override // com.oversea.ab_firstarea.dpresenter.OnUserCenterListener
    public void onUnBindEmailSuccess(String str) {
        Lxhw_UserCenterView lxhw_UserCenterView = this.view;
        if (lxhw_UserCenterView != null) {
            lxhw_UserCenterView.onUnBindEmailSuccess(str);
        }
    }
}
