package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_RequestComAuto;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.AccountUpgradeBean;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_RequestComAutoModelImpl implements Lxhw_RequestComAuto {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_RequestComAuto
    public void autoLogin(String str, String str2) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("autoLogin");
        createCommonParams.put("login_log_id", Integer.valueOf(ManageBean.getInstance().getLoginBean().getData().getLogin_log_id()));
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.LOGIN_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("autoLogin", createCommonParams, AreaBaseService.LOGIN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_RequestComAutoModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str3);
                        if (loginBean.getCode() == 0) {
                            AreaSdk.getInstance().onAuthResult(loginBean);
                        } else {
                            LLog.v_noControl(loginBean.getMessage());
                            Lxhw_XSDK.getInstance().onAuthResult(-1);
                        }
                    }
                } catch (Throwable th) {
                    try {
                        th.printStackTrace();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl(baseBean.getCode() + " " + baseBean.getMessage());
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_RequestComAuto
    public void userBindInfo(String str) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("user_bind_info");
        createCommonParams.put("platform_account", str);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.USER_BIND_INFO_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("user_bind_info", createCommonParams, AreaBaseService.USER_BIND_INFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_RequestComAutoModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        AccountUpgradeBean accountUpgradeBean = (AccountUpgradeBean) JsonUtility.jsonToObject(AccountUpgradeBean.class, str2);
                        if (accountUpgradeBean.getCode() == 0) {
                            if (TextUtils.isEmpty(accountUpgradeBean.getData().getEmail()) && TextUtils.isEmpty(accountUpgradeBean.getData().getPhone())) {
                                LLog.v_noControl("no bind");
                            }
                            AreaSdk.getInstance().userBindResult();
                        } else {
                            LLog.v_noControl(accountUpgradeBean.getMessage());
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl(" bind " + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }
}
