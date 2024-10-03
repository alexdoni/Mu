package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_ResetPasswordModel;
import com.oversea.ab_firstarea.dpresenter.OnResetPasswordListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ResetPasswordModelImpl implements Lxhw_ResetPasswordModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ResetPasswordModel
    public void doChangePasswordByPhone(int i, String str, String str2, String str3, String str4, String str5, String str6, final OnResetPasswordListener<BaseBean> onResetPasswordListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("changePasswordByPhoneSendCode");
        httpParamsCommon.addParam("platform_uid", Integer.valueOf(i));
        httpParamsCommon.addParam("platform_account", str);
        httpParamsCommon.addParam("phone_prefix", str2);
        httpParamsCommon.addParam("phone", str3);
        httpParamsCommon.addParam("code", str4);
        httpParamsCommon.addParam("new_password", str5);
        httpParamsCommon.addParam("confirm_password", str6);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.CHANGEPASSWORDBYPHONE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("changePasswordByPhoneSendCode", params, AreaBaseService.CHANGEPASSWORDBYPHONE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ResetPasswordModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str7) {
                try {
                    if (!TextUtils.isEmpty(str7)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str7);
                        if (baseBean.getCode() == 0) {
                            onResetPasswordListener.onReqSuccess("", baseBean);
                        } else {
                            onResetPasswordListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onResetPasswordListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ResetPasswordModel
    public void doChangePasswordByEmail(int i, String str, String str2, String str3, String str4, String str5, final OnResetPasswordListener<BaseBean> onResetPasswordListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doChangePasswordByEmail");
        httpParamsCommon.addParam("platform_uid", Integer.valueOf(i));
        httpParamsCommon.addParam("platform_account", str);
        httpParamsCommon.addParam("email", str2);
        httpParamsCommon.addParam("code", str3);
        httpParamsCommon.addParam("new_password", str4);
        httpParamsCommon.addParam("confirm_password", str5);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.CHANGEPASSWORDBYEMAIL_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("doChangePasswordByEmail", params, AreaBaseService.CHANGEPASSWORDBYEMAIL_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ResetPasswordModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str6) {
                try {
                    if (!TextUtils.isEmpty(str6)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str6);
                        if (baseBean.getCode() == 0) {
                            onResetPasswordListener.onReqSuccess("", baseBean);
                        } else {
                            onResetPasswordListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onResetPasswordListener.onReqFail("", baseBean);
            }
        });
    }
}
