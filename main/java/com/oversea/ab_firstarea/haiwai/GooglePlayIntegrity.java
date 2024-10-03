package com.oversea.ab_firstarea.haiwai;

import android.text.TextUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.MD5;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GooglePlayIntegrity {
    private static GooglePlayIntegrity instance;
    private IntegrityManager integrityManager;
    private String nonce = "";

    public static GooglePlayIntegrity getInstance() {
        if (instance == null) {
            instance = new GooglePlayIntegrity();
        }
        return instance;
    }

    public void requestIntegrityToken(LoginBean loginBean) {
        this.integrityManager = IntegrityManagerFactory.create(Lxhw_XSDK.getInstance().getApplication());
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_START);
        if (TextUtils.isEmpty(loginBean.getData().getToken())) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKEN_FAIL, "sdk token Empty");
            return;
        }
        try {
            this.nonce = MD5.getMD5String(loginBean.getData().getToken());
            Task<IntegrityTokenResponse> requestIntegrityToken = this.integrityManager.requestIntegrityToken(IntegrityTokenRequest.builder().setNonce(this.nonce).build());
            if (requestIntegrityToken == null) {
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKEN_FAIL, "integrityTokenResponse null");
            } else {
                requestIntegrityToken.addOnSuccessListener(new OnSuccessListener<IntegrityTokenResponse>() { // from class: com.oversea.ab_firstarea.haiwai.GooglePlayIntegrity.1
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(IntegrityTokenResponse integrityTokenResponse) {
                        if (integrityTokenResponse != null && !TextUtils.isEmpty(integrityTokenResponse.token())) {
                            Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKEN_SUCCESS);
                            GooglePlayIntegrity.this.doGoogleIntegrity(integrityTokenResponse.token());
                        } else {
                            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKEN_FAIL, "onSuccess but token null ");
                        }
                    }
                });
                requestIntegrityToken.addOnFailureListener(new OnFailureListener() { // from class: com.oversea.ab_firstarea.haiwai.GooglePlayIntegrity.2
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public void onFailure(Exception exc) {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKEN_FAIL, exc.toString());
                    }
                });
            }
        } catch (Throwable th) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKEN_FAIL, th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doGoogleIntegrity(String str) {
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doGoogleIntegrity");
        createCommonParams.put("integrity_token", str);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.GOOGLE_INTEGRITY_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doGoogleIntegrity", createCommonParams, AreaBaseService.GOOGLE_INTEGRITY_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.haiwai.GooglePlayIntegrity.3
            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestCallBackListener(String str2) {
                try {
                    if (!TextUtils.isEmpty(str2)) {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (jSONObject.getInt("code") == 0) {
                            if (!jSONObject.isNull("data")) {
                                String str3 = jSONObject.get("data") + "";
                                JSONObject jSONObject2 = new JSONObject(str3);
                                if (!jSONObject2.isNull("verify_token") && jSONObject2.getInt("verify_token") == 1) {
                                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKENCHECK_SUCCESS, str3);
                                } else {
                                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKENCHECK_FAIL, str3);
                                }
                            } else {
                                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKENCHECK_FAIL, str2);
                            }
                        } else {
                            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKENCHECK_FAIL, str2);
                        }
                    }
                } catch (Throwable th) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKENCHECK_FAIL, str2 + "   " + th.toString());
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                if (baseBean == null) {
                    return;
                }
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_INTEGRITY_TOKENCHECK_FAIL, "httpRequestFail" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }
}
