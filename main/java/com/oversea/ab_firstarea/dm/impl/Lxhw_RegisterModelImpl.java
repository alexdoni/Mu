package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_RegisterModel;
import com.oversea.ab_firstarea.dpresenter.OnRegisterListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.RegisterBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_RegisterModelImpl implements Lxhw_RegisterModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_RegisterModel
    public void register(String str, final String str2, final OnRegisterListener<RegisterBean> onRegisterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("register");
        createCommonParams.put("platform_account", str);
        createCommonParams.put("new_password", str2);
        createCommonParams.put("confirm_password", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.REGISTER_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("register", createCommonParams, AreaBaseService.REGISTER_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_RegisterModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                RegisterBean registerBean = (RegisterBean) JsonUtility.jsonToObject(RegisterBean.class, str3);
                if (registerBean.getCode() == 0) {
                    registerBean.getData().setPwd(str2);
                    onRegisterListener.onReqSuccess("register", registerBean);
                } else {
                    onRegisterListener.onReqFail("register", registerBean);
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onRegisterListener.onReqFail("register", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_RegisterModel
    public void autoLogin(String str, String str2, final OnRegisterListener<RegisterBean> onRegisterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("autoLogin");
        createCommonParams.put("platform_account", str);
        createCommonParams.put("password", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.LOGIN_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("autoLogin", createCommonParams, AreaBaseService.LOGIN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_RegisterModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    LLog.v_Control(str3);
                    if (!TextUtils.isEmpty(str3)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str3);
                        if (loginBean.getCode() == 0) {
                            onRegisterListener.onLoginSuccess(loginBean);
                        } else {
                            onRegisterListener.onReqFail("autoLogin", loginBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onRegisterListener.onReqFail("autoLogin", baseBean);
            }
        });
    }
}
