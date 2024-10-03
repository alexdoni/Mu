package com.oversea.ab_firstarea.dm.impl;

import android.text.TextUtils;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.ACloudStSBean;
import com.oversea.ab_firstarea.net.model.InitBean;
import com.oversea.ab_firstarea.net.model.KFBaseInfoBean;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.model.ManageBean;
import com.oversea.ab_firstarea.net.model.UserInfoBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstarea.utils.osshandle.OssHandel;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.AESUtil;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;

/* loaded from: classes.dex */
public class Lxhw_ManageSomeRequestModelImpl implements Lxhw_ManageSomeRequestModel {
    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void autoLogin(String str, String str2) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("autoLogin");
        createCommonParams.put("platform_account", str);
        createCommonParams.put("password", str2);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.LOGIN_ROUTE);
        Lxhw_DialogManage.getInstance().showDialog();
        HttpRequestCenter.getInstance().doRequestCommon("autoLogin", createCommonParams, AreaBaseService.LOGIN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.1
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str3) {
                try {
                    if (!TextUtils.isEmpty(str3)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str3);
                        if (loginBean.getCode() == 0) {
                            AreaSdk.getInstance().onAuthResult(loginBean);
                        } else {
                            Lxhw_DialogManage.getInstance().showLogin(Lxhw_XSDK.getInstance().getContext());
                            Lxhw_XSDK.getInstance().onAuthResult(-1);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl("autologin code " + baseBean.getCode() + " " + baseBean.getMessage());
                Lxhw_DialogManage.getInstance().showLogin(Lxhw_XSDK.getInstance().getContext());
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void logout() {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("logout");
        createCommonParams.put("login_log_id", Integer.valueOf(ManageBean.getInstance().getLoginBean().getData().getLogin_log_id()));
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.LOGOUT_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("logout", createCommonParams, AreaBaseService.LOGOUT_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.2
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    BaseBean baseBean = (BaseBean) JsonUtility.jsonToObject(BaseBean.class, str);
                    if (baseBean.getCode() == 0) {
                        Lxhw_AreaPlatform.getInstance().callbackSwitchAccount();
                    }
                    LLog.v_noControl("logout " + baseBean.getCode());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl("logout code" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void fireBaseUpdateToken(String str) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("fireBaseUpdateToken");
        createCommonParams.put("firebase_client_token", str);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.FIREBASEUPDATETOKEN_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("fireBaseUpdateToken", createCommonParams, AreaBaseService.FIREBASEUPDATETOKEN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.3
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl("firebase code" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void kfBaseInfo() {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("kfbaseInfo");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.CUSTOMERBASEINFO_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("kfBaseInfo", createCommonParams, AreaBaseService.CUSTOMERBASEINFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.4
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        KFBaseInfoBean kFBaseInfoBean = (KFBaseInfoBean) JsonUtility.jsonParseToObject(KFBaseInfoBean.class, str);
                        if (kFBaseInfoBean.getCode() == 0) {
                            ManageBean.getInstance().setKfBaseInfoBean(kFBaseInfoBean);
                        } else {
                            LLog.v_noControl("kfbase" + kFBaseInfoBean.getCode() + " " + kFBaseInfoBean.getMessage());
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl("kfbase" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void acloudsts() {
        try {
            HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
            Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("acloudsts");
            createCommonParams.put("safe_encrypt", 1);
            String str = AreaBaseService.ACLOUDSTS;
            httpParamsCommon.addSign(createCommonParams, AreaBaseService.ACLOUDSTS_ROUTE);
            HttpRequestCenter.getInstance().doRequestCommonAddHead("acloudsts", createCommonParams, str, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.5
                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestCallBackListener(String str2) {
                    try {
                        if (TextUtils.isEmpty(str2)) {
                            return;
                        }
                        ACloudStSBean aCloudStSBean = (ACloudStSBean) JsonUtility.jsonToObject(ACloudStSBean.class, str2);
                        if (aCloudStSBean.getCode() == 0) {
                            if (aCloudStSBean.getData().getCos() != null) {
                                ACloudStSBean.Oss oss = new ACloudStSBean.Oss();
                                oss.setBucket_domain(aCloudStSBean.getData().getCos().getBucket_domain());
                                oss.setEndpoint(aCloudStSBean.getData().getCos().getEndpoint());
                                oss.setBucket_name(aCloudStSBean.getData().getCos().getBucket_name());
                                oss.setRegion(aCloudStSBean.getData().getCos().getRegion());
                                oss.setImage_domain(aCloudStSBean.getData().getCos().getImage_domain());
                                aCloudStSBean.getData().setOss(oss);
                            }
                            if (TextUtils.isEmpty(aCloudStSBean.getData().getCredentials()) || aCloudStSBean.getData().getCredentials().length() <= 16) {
                                return;
                            }
                            String aesGcmDecrypt = AESUtil.aesGcmDecrypt(aCloudStSBean.getData().getCredentials(), Lxhw_AppInfoDecorator.getSecret_key_app().substring(0, 16));
                            LLog.i_Control("oss aesGcmDecrypt:" + aesGcmDecrypt);
                            ACloudStSBean.Aes123EcbCredentials aes123EcbCredentials = (ACloudStSBean.Aes123EcbCredentials) JsonUtility.jsonToObject(ACloudStSBean.Aes123EcbCredentials.class, aesGcmDecrypt);
                            if (aes123EcbCredentials != null) {
                                aCloudStSBean.setAes123EcbCredentials(aes123EcbCredentials);
                                ManageBean.getInstance().setaCloudStSBean(aCloudStSBean);
                                OssHandel.getInstance().init();
                                return;
                            }
                            return;
                        }
                        LLog.v_noControl("kfbase" + aCloudStSBean.getCode() + " " + aCloudStSBean.getMessage());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

                @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
                public void httpRequestFail(BaseBean baseBean) {
                    LLog.v_noControl("acloudsts" + baseBean.getCode() + " " + baseBean.getMessage());
                }
            });
        } catch (Exception unused) {
        }
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void userinfo_vi() {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doGetUserInfo");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.USERINFO_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doGetUserInfo", createCommonParams, AreaBaseService.USERINFO_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.6
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                long parseLong;
                try {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    UserInfoBean userInfoBean = (UserInfoBean) JsonUtility.jsonToObject(UserInfoBean.class, str);
                    if (userInfoBean.getCode() == 0) {
                        LLog.v_noControl("cert_switch=" + InitBean.getInstance().getGame_info().getCertification_switch() + " cert_status" + userInfoBean.getData().getInfo_certification_status());
                        if (InitBean.getInstance().getGame_info() == null || InitBean.getInstance().getGame_info().getCertification_switch() == 0 || userInfoBean.getData().getInfo_certification_status() != 0) {
                            return;
                        }
                        if (InitBean.getInstance().getGame_info().getCertification_switch() == 2) {
                            try {
                                if (TextUtils.isEmpty(ImageUtils.getStringKeyForValue(Lxhw_XSDK.getInstance().getContext(), "SDK_AUTH_TIME_SHOW_" + userInfoBean.getData().getPlatform_uid()))) {
                                    parseLong = 0;
                                } else {
                                    parseLong = Long.parseLong(ImageUtils.getStringKeyForValue(Lxhw_XSDK.getInstance().getContext(), "SDK_AUTH_TIME_SHOW_" + userInfoBean.getData().getPlatform_uid()));
                                }
                                if (System.currentTimeMillis() - parseLong <= 0) {
                                    return;
                                }
                            } catch (Exception unused) {
                            }
                        }
                        Lxhw_DialogManage.getInstance().showUpDataUserInfoVI(Lxhw_XSDK.getInstance().getContext());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.oversea.ab_firstarea.dm.Lxhw_ManageSomeRequestModel
    public void autoLoginWithToken(String str) {
        LoadingDialog.showDialogForLoading(Lxhw_XSDK.getInstance().getContext());
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("token_login");
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.TOKEN_LOGIN_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHeadWithLogin("tokenLogin", str, createCommonParams, AreaBaseService.TOKEN_LOGIN_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.dm.impl.Lxhw_ManageSomeRequestModelImpl.7
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    LoadingDialog.cancelDialogForLoading();
                    if (!TextUtils.isEmpty(str2)) {
                        LoginBean loginBean = (LoginBean) JsonUtility.jsonToObject(LoginBean.class, str2);
                        if (loginBean.getCode() == 0) {
                            AreaSdk.getInstance().onAuthResult(loginBean);
                        } else {
                            Lxhw_DialogManage.getInstance().showLoginV2(Lxhw_XSDK.getInstance().getContext());
                            Lxhw_XSDK.getInstance().onAuthResult(-1);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LoadingDialog.cancelDialogForLoading();
                LLog.v_noControl("autologin code " + baseBean.getCode() + " " + baseBean.getMessage());
                Lxhw_DialogManage.getInstance().showLoginV2(Lxhw_XSDK.getInstance().getContext());
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }
}
