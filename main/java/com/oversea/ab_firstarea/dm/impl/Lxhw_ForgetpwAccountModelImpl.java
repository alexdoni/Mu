package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_ForgetpwAccountModel;
import com.oversea.ab_firstarea.dpresenter.OnForgetpwAccountListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.UserBindInfoBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwAccountModelImpl implements Lxhw_ForgetpwAccountModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwAccountModel
    public void doForgetPwAccount(String str, final OnForgetpwAccountListener<UserBindInfoBean> onForgetpwAccountListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("user_bind_info");
        createCommonParams.put("platform_account", str);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.USER_BIND_INFO_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("user_bind_info", createCommonParams, AreaBaseService.USER_BIND_INFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwAccountModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        UserBindInfoBean userBindInfoBean = (UserBindInfoBean) JsonUtility.jsonToObject(UserBindInfoBean.class, str2);
                        if (userBindInfoBean.getCode() == 0) {
                            onForgetpwAccountListener.onReqSuccess("", userBindInfoBean);
                        } else if (userBindInfoBean.getCode() == 2028) {
                            ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), userBindInfoBean.getMessage());
                        } else {
                            onForgetpwAccountListener.onReqFail("", userBindInfoBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwAccountListener.onReqFail("", baseBean);
            }
        });
    }
}
