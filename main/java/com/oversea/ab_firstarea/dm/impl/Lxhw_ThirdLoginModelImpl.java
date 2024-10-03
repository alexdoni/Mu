package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_ThirdLoginModel;
import com.oversea.ab_firstarea.dpresenter.OnThirdLoginListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ThirdLoginModelImpl implements Lxhw_ThirdLoginModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ThirdLoginModel
    public void thirdLogin(int i, String str, String str2, String str3, final OnThirdLoginListener<LoginBean> onThirdLoginListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("thirdLogin");
        createCommonParams.put("third_type", Integer.valueOf(i));
        createCommonParams.put("access_token", str);
        if (!TextUtils.isEmpty(str2)) {
            createCommonParams.put("openid", str2);
        } else {
            createCommonParams.put("openid", "");
        }
        createCommonParams.put("fb_token_for_business", str3);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.THIRD_LOGIN_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("thirdLogin", createCommonParams, AreaBaseService.THIRD_LOGIN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ThirdLoginModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    if (!TextUtils.isEmpty(str4)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str4);
                        if (loginBean.getCode() == 0) {
                            onThirdLoginListener.onReqSuccess("", loginBean);
                        } else {
                            onThirdLoginListener.onReqFail("", loginBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onThirdLoginListener.onReqFail("", baseBean);
            }
        });
    }
}
