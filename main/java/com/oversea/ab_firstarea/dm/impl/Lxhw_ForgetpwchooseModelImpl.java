package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_ForgetpwchooseModel;
import com.oversea.ab_firstarea.dpresenter.OnForgetpwchooseListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.ForgetpwchooseBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwchooseModelImpl implements Lxhw_ForgetpwchooseModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwchooseModel
    public void changePasswordByPhoneSendCode(int i, String str, String str2, String str3, final OnForgetpwchooseListener<ForgetpwchooseBean> onForgetpwchooseListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("changePasswordByPhoneSendCode");
        createCommonParams.put("platform_uid", Integer.valueOf(i));
        createCommonParams.put("platform_account", str);
        createCommonParams.put("phone_prefix", str2);
        createCommonParams.put("phone", str3);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.CHANGEPASSWORDPHONESENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("changePasswordByPhoneSendCode", createCommonParams, AreaBaseService.CHANGEPASSWORDPHONESENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwchooseModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    if (!TextUtils.isEmpty(str4)) {
                        ForgetpwchooseBean forgetpwchooseBean = (ForgetpwchooseBean) JsonUtility.jsonToObject(ForgetpwchooseBean.class, str4);
                        if (forgetpwchooseBean.getCode() == 0) {
                            onForgetpwchooseListener.onReqSuccess("", forgetpwchooseBean);
                        } else {
                            onForgetpwchooseListener.onReqFail("", forgetpwchooseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwchooseListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwchooseModel
    public void changePasswordByEmailSendCode(int i, String str, String str2, final OnForgetpwchooseListener<ForgetpwchooseBean> onForgetpwchooseListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("changePasswordByEmailSendCode");
        createCommonParams.put("platform_uid", Integer.valueOf(i));
        createCommonParams.put("platform_account", str);
        createCommonParams.put("email", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.CHANGEPASSWORDEMAILSENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("changePasswordByEmailSendCode", createCommonParams, AreaBaseService.CHANGEPASSWORDEMAILSENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwchooseModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    LLog.v_Control(str3);
                    if (!TextUtils.isEmpty(str3)) {
                        ForgetpwchooseBean forgetpwchooseBean = (ForgetpwchooseBean) JsonUtility.jsonParseToObject(ForgetpwchooseBean.class, str3);
                        if (forgetpwchooseBean.getCode() == 0) {
                            onForgetpwchooseListener.onReqSuccess("", forgetpwchooseBean);
                        } else {
                            onForgetpwchooseListener.onReqFail("", forgetpwchooseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwchooseListener.onReqFail("", baseBean);
            }
        });
    }
}
