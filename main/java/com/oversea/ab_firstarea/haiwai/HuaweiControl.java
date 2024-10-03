package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import android.content.Intent;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ThirdLoginModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnThirdLoginListener;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.login.huawei.HwLoginCallback;
import com.oversea.ab_firstarea.plugin.login.huawei.HwLoginLibHelper;
import com.oversea.ab_firstarea.plugin.login.huawei.HwLogoutCallback;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;

/* loaded from: classes.dex */
public class HuaweiControl {
    private static HuaweiControl mInstance;

    public static HuaweiControl getInstance() {
        if (mInstance == null) {
            synchronized (HuaweiControl.class) {
                if (mInstance == null) {
                    mInstance = new HuaweiControl();
                }
            }
        }
        return mInstance;
    }

    public void login(final Activity activity) {
        ComConstants.CTRL_TYPE = 34;
        HwLoginLibHelper.getInstance().login(activity, new HwLoginCallback() { // from class: com.oversea.ab_firstarea.haiwai.HuaweiControl.1
            @Override // com.oversea.ab_firstarea.plugin.login.huawei.HwLoginCallback
            public void loginSuccess(String str, String str2) {
                HuaweiControl.this.autoLogin(str, str2);
            }

            @Override // com.oversea.ab_firstarea.plugin.login.huawei.HwLoginCallback
            public void loginFailed(int i) {
                ToastUtils.toastShow(activity, "huawei login failed:" + i);
            }
        });
    }

    public void logout() {
        HwLoginLibHelper.getInstance().logout(new HwLogoutCallback() { // from class: com.oversea.ab_firstarea.haiwai.HuaweiControl.2
            @Override // com.oversea.ab_firstarea.plugin.login.huawei.HwLogoutCallback
            public void logoutFailed() {
            }

            @Override // com.oversea.ab_firstarea.plugin.login.huawei.HwLogoutCallback
            public void logoutSuccess() {
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        HwLoginLibHelper.getInstance().onActivityResult(i, i2, intent);
    }

    public void autoLogin(String str, String str2) {
        new Lxhw_ThirdLoginModelImpl().thirdLogin(8, "huawei", str, "", new OnThirdLoginListener<LoginBean>() { // from class: com.oversea.ab_firstarea.haiwai.HuaweiControl.3
            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqSuccess(String str3, LoginBean loginBean) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), loginBean.getMessage());
                LoadingDialog.cancelDialogForLoading();
                Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "LoginDialog");
                if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
                    Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "loginSelectDialog");
                }
                ImageUtils.setSharePreferences(Lxhw_XSDK.getInstance().getContext(), Constants.SDK_LOGIN_TYPE, Constants.SDK_LOGIN_TYPE_HUAWEI);
                AreaSdk.getInstance().onAuthResult(loginBean);
            }

            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqFail(String str3, BaseBean baseBean) {
                LoadingDialog.cancelDialogForLoading();
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
                Lxhw_Platform.getInstance().login(Lxhw_XSDK.getInstance().getContext());
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }
}
