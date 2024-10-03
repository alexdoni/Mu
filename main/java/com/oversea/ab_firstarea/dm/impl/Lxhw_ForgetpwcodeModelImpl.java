package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel;
import com.oversea.ab_firstarea.dpresenter.OnForgetpwcodeListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ForgetpwcodeModelImpl implements Lxhw_ForgetpwcodeModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void doChangePasswordByPhoneCheckCode(int i, String str, String str2, String str3, String str4, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doChangePasswordByPhoneCheckCode");
        httpParamsCommon.addParam("platform_uid", Integer.valueOf(i));
        httpParamsCommon.addParam("platform_account", str);
        httpParamsCommon.addParam("phone_prefix", str2);
        httpParamsCommon.addParam("phone", str3);
        httpParamsCommon.addParam("code", str4);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.CHANGEPASSWORDPHONECHECKCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("doChangePasswordByPhoneCheckCode", params, AreaBaseService.CHANGEPASSWORDPHONECHECKCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str5) {
                try {
                    if (!TextUtils.isEmpty(str5)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str5);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.jumpResetPassword();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void doChangePasswordByEmailCheckCode(int i, String str, String str2, String str3, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doChangePasswordByEmailCheckCode");
        httpParamsCommon.addParam("platform_uid", Integer.valueOf(i));
        httpParamsCommon.addParam("platform_account", str);
        httpParamsCommon.addParam("email", str2);
        httpParamsCommon.addParam("code", str3);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.CHANGEPASSWORDEMAILCHECKCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("doChangePasswordByEmailCheckCode", params, AreaBaseService.CHANGEPASSWORDEMAILCHECKCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    if (!TextUtils.isEmpty(str4)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str4);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.jumpResetPassword();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void changePasswordByPhoneSendCode(int i, String str, String str2, String str3, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("changePasswordByPhoneSendCode");
        httpParamsCommon.addParam("platform_uid", Integer.valueOf(i));
        httpParamsCommon.addParam("platform_account", str);
        httpParamsCommon.addParam("phone_prefix", str2);
        httpParamsCommon.addParam("phone", str3);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.CHANGEPASSWORDPHONESENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("changePasswordByPhoneSendCode", params, AreaBaseService.CHANGEPASSWORDPHONESENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.3
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    LLog.v_Control(str4);
                    if (!TextUtils.isEmpty(str4)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str4);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.onReqCodeSuccess();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void changePasswordByEmailSendCode(int i, String str, String str2, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("changePasswordByEmailSendCode");
        httpParamsCommon.addParam("platform_uid", Integer.valueOf(i));
        httpParamsCommon.addParam("platform_account", str);
        httpParamsCommon.addParam("email", str2);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.CHANGEPASSWORDEMAILSENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommon("changePasswordByEmailSendCode", params, AreaBaseService.CHANGEPASSWORDEMAILSENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.4
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    LLog.v_Control(str3);
                    if (!TextUtils.isEmpty(str3)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str3);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.onReqCodeSuccess();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void doUnbindPhoneSendCode(String str, String str2, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doUnbindPhoneSendCode");
        httpParamsCommon.addParam("phone_prefix", str);
        httpParamsCommon.addParam("phone", str2);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.UNBINDPHONESENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUnbindPhoneSendCode", params, AreaBaseService.UNBINDPHONESENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.5
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str3);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.onReqCodeSuccess();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void doUnbindEmailSendCode(String str, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doUnbindEmailSendCode");
        httpParamsCommon.addParam("email", str);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.UNBINDEMAILSENDCODE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUnbindEmailSendCode", params, AreaBaseService.UNBINDEMAILSENDCODE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.6
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str2);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.onReqCodeSuccess();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void doUnbindPhone(String str, String str2, String str3, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doUnbindPhone");
        httpParamsCommon.addParam("phone_prefix", str);
        httpParamsCommon.addParam("phone", str2);
        httpParamsCommon.addParam("code", str3);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.UNBINDPHONE_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUnbindPhone", params, AreaBaseService.UNBINDPHONE_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.7
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str4) {
                try {
                    if (!TextUtils.isEmpty(str4)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str4);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.onReqUnbindPhoneSuccess();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ForgetpwcodeModel
    public void doUnbindEmail(String str, String str2, final OnForgetpwcodeListener<BaseBean> onForgetpwcodeListener) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        httpParamsCommon.createCommonParams("doUnbindEmail");
        httpParamsCommon.addParam("email", str);
        httpParamsCommon.addParam("code", str2);
        Map<String, Object> params = httpParamsCommon.getParams();
        httpParamsCommon.addSign(params, AreaBaseService.UNBINDEMAIL_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doUnbindEmail", params, AreaBaseService.UNBINDEMAIL_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ForgetpwcodeModelImpl.8
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str3);
                        if (baseBean.getCode() == 0) {
                            onForgetpwcodeListener.onReqUnbindEmailSuccess();
                        } else {
                            onForgetpwcodeListener.onReqFail("", baseBean);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                onForgetpwcodeListener.onReqFail("", baseBean);
            }
        });
    }
}
