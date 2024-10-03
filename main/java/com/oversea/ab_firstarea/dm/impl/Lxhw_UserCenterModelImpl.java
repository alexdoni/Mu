package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_UserCenterModel;
import com.oversea.ab_firstarea.dpresenter.OnUserCenterListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_UserCenterModelImpl implements Lxhw_UserCenterModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_UserCenterModel
    public void doGetUserInfo(final OnUserCenterListener<UserInfoBean> onUserCenterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doGetUserInfo");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.USERINFO_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doGetUserInfo", createCommonParams, AreaBaseService.USERINFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_UserCenterModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        UserInfoBean userInfoBean = (UserInfoBean) JsonUtility.jsonToObject(UserInfoBean.class, str);
                        if (userInfoBean.getCode() == 0) {
                            onUserCenterListener.onReqSuccess("", userInfoBean);
                        } else {
                            onUserCenterListener.onReqFail("", userInfoBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onUserCenterListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_UserCenterModel
    public void doUnbindPhoneSendCode(String str, String str2, final OnUserCenterListener<UserInfoBean> onUserCenterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doUnbindPhoneSendCode");
        createCommonParams.put("phone_prefix", str);
        createCommonParams.put("phone", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.UNBINDPHONESENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUnbindPhoneSendCode", createCommonParams, AreaBaseService.UNBINDPHONESENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_UserCenterModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        UserInfoBean userInfoBean = (UserInfoBean) JsonUtility.jsonToObject(UserInfoBean.class, str3);
                        if (userInfoBean.getCode() == 0) {
                            onUserCenterListener.onUnBindPhoneSuccess(userInfoBean.getMessage());
                        } else {
                            onUserCenterListener.onReqFail("", userInfoBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onUserCenterListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_UserCenterModel
    public void doUnbindEmailSendCode(String str, final OnUserCenterListener<UserInfoBean> onUserCenterListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doUnbindEmailSendCode");
        createCommonParams.put("email", str);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.UNBINDEMAILSENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUnbindEmailSendCode", createCommonParams, AreaBaseService.UNBINDEMAILSENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_UserCenterModelImpl.3
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        UserInfoBean userInfoBean = (UserInfoBean) JsonUtility.jsonToObject(UserInfoBean.class, str2);
                        if (userInfoBean.getCode() == 0) {
                            onUserCenterListener.onUnBindEmailSuccess(userInfoBean.getMessage());
                        } else {
                            onUserCenterListener.onReqFail("", userInfoBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onUserCenterListener.onReqFail("", baseBean);
            }
        });
    }
}
