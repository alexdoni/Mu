package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_EmailConfirmModel;
import com.oversea.ab_firstarea.dpresenter.OnEmailConfirmListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.EmailConfirmBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_EmailConfirmModelImpl implements Lxhw_EmailConfirmModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_EmailConfirmModel
    public void doBindEmailSendCode(String str, final OnEmailConfirmListener<EmailConfirmBean> onEmailConfirmListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doBindEmailSendCode");
        createCommonParams.put("email", str);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.BINDEMAILSENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doBindEmailSendCode", createCommonParams, AreaBaseService.BINDEMAILSENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_EmailConfirmModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        EmailConfirmBean emailConfirmBean = (EmailConfirmBean) JsonUtility.jsonToObject(EmailConfirmBean.class, str2);
                        if (emailConfirmBean.getCode() == 0) {
                            onEmailConfirmListener.onReqCodeSuccess();
                        } else {
                            onEmailConfirmListener.onReqFail("", emailConfirmBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onEmailConfirmListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_EmailConfirmModel
    public void doBindEmail(String str, String str2, final OnEmailConfirmListener<EmailConfirmBean> onEmailConfirmListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doBindEmail");
        createCommonParams.put("email", str);
        createCommonParams.put("code", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.BINDEMAIL_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doBindEmail", createCommonParams, AreaBaseService.BINDEMAIL_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_EmailConfirmModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        EmailConfirmBean emailConfirmBean = (EmailConfirmBean) JsonUtility.jsonToObject(EmailConfirmBean.class, str3);
                        if (emailConfirmBean.getCode() == 0) {
                            onEmailConfirmListener.onReqSuccess("", emailConfirmBean);
                        } else {
                            onEmailConfirmListener.onReqFail("", emailConfirmBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onEmailConfirmListener.onReqFail("", baseBean);
            }
        });
    }
}
