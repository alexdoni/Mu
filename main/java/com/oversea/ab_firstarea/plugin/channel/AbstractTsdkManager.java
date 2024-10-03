package com.oversea.ab_firstarea.plugin.channel;

import com.oversea.ab_firstarea.dm.impl.Lxhw_ThirdLoginModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnThirdLoginListener;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.plugin.pay.Lxhw_PayParams;
import com.oversea.ab_firstplatform.plugin.user.Lxhw_UserExtraData;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;

/* loaded from: classes.dex */
public abstract class AbstractTsdkManager {
    public abstract void exit();

    public abstract void init();

    public abstract void login();

    public abstract void logout();

    public abstract void pay(Lxhw_PayParams lxhw_PayParams);

    public abstract void submitGameInfo(Lxhw_UserExtraData lxhw_UserExtraData);

    /* JADX INFO: Access modifiers changed from: protected */
    public void initSuccess() {
        Lxhw_XSDK.getInstance().onResult(1, "init success");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void paySuccess() {
        Lxhw_XSDK.getInstance().onResult(16, "pay success");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void payFail() {
        Lxhw_XSDK.getInstance().onResult(17, "pay fail");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void payCancel() {
        Lxhw_XSDK.getInstance().onResult(18, "pay cancel");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logoutSuccess() {
        Lxhw_XSDK.getInstance().onResult(5, "switch success");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void loginService(int i, String str, String str2) {
        new Lxhw_ThirdLoginModelImpl().thirdLogin(i, str, str2, "", new OnThirdLoginListener<LoginBean>() { // from class: com.oversea.ab_firstarea.plugin.channel.AbstractTsdkManager.1
            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqSuccess(String str3, LoginBean loginBean) {
                AreaSdk.getInstance().onAuthResult(loginBean);
            }

            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqFail(String str3, BaseBean baseBean) {
                ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
            }
        });
    }
}
