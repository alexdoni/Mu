package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_LoginModel;
import com.oversea.ab_firstarea.dpresenter.OnLoginListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_LoginModelImpl implements Lxhw_LoginModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_LoginModel
    public void login(boolean z, String str, String str2, final OnLoginListener<LoginBean> onLoginListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("login");
        createCommonParams.put("platform_account", str);
        createCommonParams.put("password", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.LOGIN_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("login", createCommonParams, AreaBaseService.LOGIN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_LoginModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str3);
                        if (loginBean.getCode() == 0) {
                            onLoginListener.onReqSuccess("", loginBean);
                        } else {
                            onLoginListener.onReqFail("", loginBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onLoginListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_LoginModel
    public void guestLogin(final OnLoginListener<LoginBean> onLoginListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("guestLogin");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.GUEST_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("guestLogin", createCommonParams, AreaBaseService.GUEST_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_LoginModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str);
                        if (loginBean.getCode() == 0) {
                            onLoginListener.onReqGuestSuccess(loginBean);
                        } else {
                            onLoginListener.onReqFail("", loginBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onLoginListener.onReqFail("", baseBean);
            }
        });
    }
}
