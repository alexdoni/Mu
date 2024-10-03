package com.oversea.ab_firstarea.http;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.share.internal.ShareConstants;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.net.domainN.DomainBean;
import com.oversea.ab_firstarea.net.domainN.DomainManager;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.plugin.pay.PayLibHelper;
import com.oversea.ab_firstarea.utils.json.JsonUtility;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.model.GoogleProductListData;
import com.oversea.ab_firstplatform.verify.Lxhw_XUserInfo;
import com.xsdk.ab_firstbase.net.http.CommomCallBack;
import com.xsdk.ab_firstbase.net.http.HttpRequest;
import com.xsdk.ab_firstbase.net.http.HttpUtils;
import com.xsdk.ab_firstbase.statisics.util.AESUtil;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ContextHolder;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.JudgeJson;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.io.IOException;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HttpRequestCenter {
    private static HttpRequestCenter instance;

    public static HttpRequestCenter getInstance() {
        if (instance == null) {
            instance = new HttpRequestCenter();
        }
        return instance;
    }

    public void doRequestCommon(final String str, Map<String, Object> map, String str2, final HttpRequestCallBack httpRequestCallBack) {
        if (!Util.isNetworkAvailable(Lxhw_XSDK.getInstance().getApplication())) {
            String string = Lxhw_XSDK.getInstance().getApplication().getApplicationContext().getString(Util.getIdByName(Lxhw_XSDK.getInstance().getApplication().getApplicationContext(), TypedValues.Custom.S_STRING, "tw_network_error"));
            if (Lxhw_XSDK.getInstance().getContext() == null || TextUtils.isEmpty(string)) {
                return;
            }
            ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), string);
            return;
        }
        HttpUtils.getInstance().post().url(str2).addMapParams(map).build().execute(new CommomCallBack() { // from class: com.oversea.ab_firstarea.http.HttpRequestCenter.1
            @Override // com.xsdk.ab_firstbase.net.http.CommomCallBack
            public void onSucceed(String str3, String str4, HttpRequest httpRequest) {
                Lxhw_DialogManage.getInstance().cancelDialog();
                if (str3 != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str3);
                        if (!jSONObject.isNull("data")) {
                            if (JudgeJson.isJson(jSONObject.getString("data"))) {
                                httpRequestCallBack.httpRequestCallBackListener(str3);
                            } else {
                                String aesGcmDecrypt = AESUtil.aesGcmDecrypt(jSONObject.getString("data"), Lxhw_AppInfoDecorator.getSecret_key_app().substring(0, 16));
                                LLog.e_Control("result-" + str4 + "\n" + aesGcmDecrypt);
                                if (!TextUtils.isEmpty(aesGcmDecrypt)) {
                                    if (JudgeJson.isJsonArray(aesGcmDecrypt)) {
                                        jSONObject.put("data", new JSONArray(aesGcmDecrypt));
                                    } else if (JudgeJson.isJsonObject(aesGcmDecrypt)) {
                                        jSONObject.put("data", new JSONObject(aesGcmDecrypt));
                                    } else {
                                        jSONObject.put("data", aesGcmDecrypt);
                                    }
                                    httpRequestCallBack.httpRequestCallBackListener(jSONObject.toString());
                                } else {
                                    httpRequestCallBack.httpRequestCallBackListener(str3);
                                }
                            }
                        } else {
                            httpRequestCallBack.httpRequestCallBackListener(str3);
                        }
                        HttpRequestCenter.this.dealErrorRequest(str3, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.xsdk.ab_firstbase.net.http.CommomCallBack
            public void onFailure(int i, String str3) {
                Lxhw_DialogManage.getInstance().cancelDialog();
                BaseBean baseBean = new BaseBean();
                baseBean.setCode(i);
                baseBean.setMessage(str3);
                httpRequestCallBack.httpRequestFail(baseBean);
            }
        });
    }

    public void doRequestCommonAddHead(final String str, Map<String, Object> map, String str2, final HttpRequestCallBack httpRequestCallBack) {
        if (!Util.isNetworkAvailable(Lxhw_XSDK.getInstance().getApplication())) {
            String string = Lxhw_XSDK.getInstance().getApplication().getApplicationContext().getString(Util.getIdByName(Lxhw_XSDK.getInstance().getApplication().getApplicationContext(), TypedValues.Custom.S_STRING, "tw_network_error"));
            if (Lxhw_XSDK.getInstance().getContext() == null || TextUtils.isEmpty(string)) {
                return;
            }
            ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), string);
            return;
        }
        String stringKeyForValue = ImageUtils.getStringKeyForValue(Lxhw_XSDK.getInstance().getContext(), Constants.SDK_USER_TOKEN);
        if (!TextUtils.isEmpty(Lxhw_XUserInfo.getInstance().getToken())) {
            stringKeyForValue = Lxhw_XUserInfo.getInstance().getToken();
        }
        if (TextUtils.isEmpty(stringKeyForValue)) {
            LLog.v_noControl("doRequestCommonAddHead token为空");
            return;
        }
        LLog.e_Control("token: " + stringKeyForValue);
        HttpUtils.getInstance().post().url(str2).addMapParams(map).addHeader("Authorization", "Bearer " + stringKeyForValue).build().execute(new CommomCallBack() { // from class: com.oversea.ab_firstarea.http.HttpRequestCenter.2
            @Override // com.xsdk.ab_firstbase.net.http.CommomCallBack
            public void onSucceed(String str3, String str4, HttpRequest httpRequest) {
                if (!AreaBaseService.PAYORDERCREATE.equals(str4) && !AreaBaseService.PAYINIT.equals(str4)) {
                    Lxhw_DialogManage.getInstance().cancelDialog();
                }
                if (str3 != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str3);
                        if (!jSONObject.isNull("data")) {
                            if (JudgeJson.isJson(jSONObject.getString("data"))) {
                                httpRequestCallBack.httpRequestCallBackListener(str3);
                            } else {
                                String aesGcmDecrypt = AESUtil.aesGcmDecrypt(jSONObject.getString("data"), Lxhw_AppInfoDecorator.getSecret_key_app().substring(0, 16));
                                LLog.e_Control("result-" + str4 + "\n" + aesGcmDecrypt);
                                if (!TextUtils.isEmpty(aesGcmDecrypt)) {
                                    if (JudgeJson.isJsonArray(aesGcmDecrypt)) {
                                        jSONObject.put("data", new JSONArray(aesGcmDecrypt));
                                    } else if (JudgeJson.isJsonObject(aesGcmDecrypt)) {
                                        jSONObject.put("data", new JSONObject(aesGcmDecrypt));
                                    } else {
                                        jSONObject.put("data", aesGcmDecrypt);
                                    }
                                    httpRequestCallBack.httpRequestCallBackListener(jSONObject.toString());
                                } else {
                                    httpRequestCallBack.httpRequestCallBackListener(str3);
                                }
                            }
                        } else {
                            httpRequestCallBack.httpRequestCallBackListener(str3);
                        }
                        HttpRequestCenter.this.dealErrorRequest(str3, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.xsdk.ab_firstbase.net.http.CommomCallBack
            public void onFailure(int i, String str3) {
                Lxhw_DialogManage.getInstance().cancelDialog();
                BaseBean baseBean = new BaseBean();
                baseBean.setCode(i);
                baseBean.setMessage(str3);
                httpRequestCallBack.httpRequestFail(baseBean);
            }
        });
    }

    public void doRequestCommonAddHeadWithLogin(final String str, String str2, Map<String, Object> map, String str3, final HttpRequestCallBack httpRequestCallBack) {
        if (!Util.isNetworkAvailable(ContextHolder.getContext())) {
            String string = ContextHolder.getContext().getString(Util.getIdByName(ContextHolder.getContext(), TypedValues.Custom.S_STRING, "tw_network_error"));
            if (TextUtils.isEmpty(string)) {
                return;
            }
            ToastUtils.toastLongShow(ContextHolder.getContext(), string);
            return;
        }
        HttpUtils.getInstance().post().url(str3).addMapParams(map).addHeader("Authorization", "Bearer " + str2).build().execute(new CommomCallBack() { // from class: com.oversea.ab_firstarea.http.HttpRequestCenter.3
            @Override // com.xsdk.ab_firstbase.net.http.CommomCallBack
            public void onSucceed(String str4, String str5, HttpRequest httpRequest) {
                if (!AreaBaseService.PAYORDERCREATE.equals(str5) && !AreaBaseService.PAYINIT.equals(str5)) {
                    Lxhw_DialogManage.getInstance().cancelDialog();
                }
                if (str4 != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str4);
                        if (!jSONObject.isNull("data")) {
                            if (JudgeJson.isJson(jSONObject.getString("data"))) {
                                httpRequestCallBack.httpRequestCallBackListener(str4);
                            } else {
                                String aesGcmDecrypt = AESUtil.aesGcmDecrypt(jSONObject.getString("data"), Lxhw_AppInfoDecorator.getSecret_key_app().substring(0, 16));
                                LLog.e_Control("result-" + str5 + "\n" + aesGcmDecrypt);
                                if (!TextUtils.isEmpty(aesGcmDecrypt)) {
                                    if (JudgeJson.isJsonArray(aesGcmDecrypt)) {
                                        jSONObject.put("data", new JSONArray(aesGcmDecrypt));
                                    } else if (JudgeJson.isJsonObject(aesGcmDecrypt)) {
                                        jSONObject.put("data", new JSONObject(aesGcmDecrypt));
                                    } else {
                                        jSONObject.put("data", aesGcmDecrypt);
                                    }
                                    httpRequestCallBack.httpRequestCallBackListener(jSONObject.toString());
                                } else {
                                    httpRequestCallBack.httpRequestCallBackListener(str4);
                                }
                            }
                        } else {
                            httpRequestCallBack.httpRequestCallBackListener(str4);
                        }
                        HttpRequestCenter.this.dealErrorRequest(str4, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.xsdk.ab_firstbase.net.http.CommomCallBack
            public void onFailure(int i, String str4) {
                Lxhw_DialogManage.getInstance().cancelDialog();
                BaseBean baseBean = new BaseBean();
                baseBean.setCode(i);
                baseBean.setMessage(str4);
                httpRequestCallBack.httpRequestFail(baseBean);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dealErrorRequest(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.isNull("code")) {
                return;
            }
            String string = jSONObject.isNull(ShareConstants.WEB_DIALOG_PARAM_MESSAGE) ? "" : jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            if (jSONObject.getInt("code") != 1004 && jSONObject.getInt("code") != 1006 && jSONObject.getInt("code") != 1007 && jSONObject.getInt("code") != 1009 && jSONObject.getInt("code") != 1010 && jSONObject.getInt("code") != 1015) {
                if (jSONObject.getInt("code") == 1013) {
                    String string2 = jSONObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    if (TextUtils.isEmpty(string2)) {
                        return;
                    }
                    Lxhw_DialogManage.getInstance().enterServerErrorTip(Lxhw_XSDK.getInstance().getContext(), string2);
                    return;
                }
                if (jSONObject.getInt("code") == 2001) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_REGISTER_ERROR, "2001");
                    return;
                }
                if (jSONObject.getInt("code") == 2002) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_LOGIN_ERROR, "2002");
                    return;
                }
                if (jSONObject.getInt("code") == 50000) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.CUSTOM_SDK_IP_BLOCKED, "");
                    Lxhw_DialogManage.getInstance().showTipDialog(Lxhw_XSDK.getInstance().getContext(), string);
                    return;
                } else {
                    if (jSONObject.getInt("code") == 2050) {
                        Bundle bundle = new Bundle();
                        bundle.putString("tip", string);
                        Lxhw_DialogManage.getInstance().showRollbackAccountDialog(Lxhw_XSDK.getInstance().getContext(), bundle);
                        return;
                    }
                    return;
                }
            }
            if (!TextUtils.isEmpty(string)) {
                ToastUtils.toastShow(Lxhw_XSDK.getInstance().getContext(), string);
            }
            if (!"login".equals(str2) && !"autoLogin".equals(str2) && !"guestLogin".equals(str2) && !"thirdLogin".equals(str2) && !"tokenLogin".equals(str2)) {
                LLog.v_noControl("dealErrorRequest  result=" + str + " " + str2);
                Lxhw_AreaPlatform.getInstance().dealErrorRequest(str2);
            }
        } catch (Exception unused) {
        }
    }

    public void requestProductIdList() {
        LLog.v_noControl("requestProductIdList");
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("product_list");
        createCommonParams.put("channel_parent_id", Integer.valueOf(Lxhw_AppInfoDecorator.getChannelPay_id() != 0 ? Lxhw_AppInfoDecorator.getChannelPay_id() : 2));
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.PRODUCTID_LIST_ROUTE);
        getInstance().doRequestCommon("product_list", createCommonParams, AreaBaseService.PRODUCTID_LIST, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.http.HttpRequestCenter.4
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.isNull("code") || jSONObject.getInt("code") != 0) {
                        return;
                    }
                    PayLibHelper.getInstance().setProductListData((GoogleProductListData) JsonUtility.jsonToObject(GoogleProductListData.class, str));
                    PayLibHelper.getInstance().getProductListInfo();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                LLog.v_noControl("product_list fail code=" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }

    public void getDomainUrl() {
        new Thread(new Runnable() { // from class: com.oversea.ab_firstarea.http.HttpRequestCenter.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Response execute = new OkHttpClient().newCall(new Request.Builder().url("https://d1pg7ocnnnc6u9.cloudfront.net/domain_delivery/domain_json.txt").build()).execute();
                    if (execute.isSuccessful()) {
                        String string = execute.body().string();
                        LLog.i_Control("getDomainUrl:" + string);
                        DomainBean domainBean = (DomainBean) JsonUtility.jsonToObject(DomainBean.class, string);
                        if (domainBean != null && domainBean.getApi_sdk() != null) {
                            ImageUtils.setSharePreferences(ContextHolder.getContext(), Constants.SDK_DOWNLOAD_BACKDOMAIN, string);
                            DomainManager.getInstance().setBackDomainFromServer();
                            LLog.i_Control("getDomainUrl:" + domainBean.toString());
                        }
                    } else {
                        LLog.e_Control("getDomainUrl:" + execute.code() + execute.message());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
