package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.recaptcha.Recaptcha;
import com.google.android.gms.recaptcha.RecaptchaAction;
import com.google.android.gms.recaptcha.RecaptchaActionType;
import com.google.android.gms.recaptcha.RecaptchaHandle;
import com.google.android.gms.recaptcha.RecaptchaResultData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.oversea.ab_firstarea.http.HttpParamsCommon;
import com.oversea.ab_firstarea.http.HttpRequestCallBack;
import com.oversea.ab_firstarea.http.HttpRequestCenter;
import com.oversea.ab_firstarea.net.service.AreaBaseService;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.init.Lxhw_AppInfoDecorator;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GoogleRecaptcha {
    private static GoogleRecaptcha instance;
    private RecaptchaHandle handle;
    final String TAG = "GoogleRecaptcha";
    private final int delayedTime = CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
    public final int TYPE_CREATE_NOSTART_HANDLE = 0;
    public final int TYPE_CREATE_HANDLE = 1;
    public final int TYPE_SUCCESS_HANDLE = 2;
    public final int TYPE_FAIL_HANDLE = 3;
    public final int TYPE_START_EXECUTE = 11;
    public final int TYPE_SUCCESS_EXECUTE = 12;
    public final int TYPE_FAIL_EXECUTE = 13;
    public int currentType = 0;
    private boolean isFirst = true;

    public static GoogleRecaptcha getInstance() {
        if (instance == null) {
            instance = new GoogleRecaptcha();
        }
        return instance;
    }

    public void dealRecaptcha(Activity activity) {
        LLog.v_Control("dealRecaptcha currentType=" + this.currentType + " isFirst=" + this.isFirst);
        int i = this.currentType;
        if (i == 0) {
            onCreate(activity);
            return;
        }
        if (this.isFirst && (i == 1 || i == 3)) {
            this.isFirst = false;
            onCreate(activity);
        } else if (i >= 11) {
            execute(activity);
        }
    }

    public void onCreate(final Activity activity) {
        this.currentType = 1;
        String recaptcha_key = Lxhw_AppInfoDecorator.getRecaptcha_key();
        if (activity == null || activity.isFinishing()) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_FAIL, "activity isEmpty");
            return;
        }
        if (TextUtils.isEmpty(recaptcha_key)) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_FAIL, "key isEmpty");
            return;
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_START);
        try {
            Recaptcha.getClient(activity).init(recaptcha_key).addOnSuccessListener(activity, new OnSuccessListener<RecaptchaHandle>() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.2
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(RecaptchaHandle recaptchaHandle) {
                    GoogleRecaptcha.this.currentType = 2;
                    GoogleRecaptcha.getInstance().handle = recaptchaHandle;
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_SUCCESS, "handle success");
                    GoogleRecaptcha.this.execute(activity);
                }
            }).addOnFailureListener(activity, new OnFailureListener() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    GoogleRecaptcha.this.currentType = 3;
                    if (exc instanceof ApiException) {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_FAIL, ((ApiException) exc).toString());
                    } else {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_FAIL, exc.toString());
                    }
                }
            });
        } catch (Throwable th) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_FAIL, th.toString());
        }
    }

    public void execute(Activity activity) {
        if (this.handle == null) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_INIT_FAIL, "execute HANDLE null currentType=" + this.currentType);
            return;
        }
        Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOEKN_START);
        this.currentType = 11;
        try {
            Recaptcha.getClient(activity).execute(this.handle, new RecaptchaAction(new RecaptchaActionType("login"))).addOnSuccessListener(activity, new OnSuccessListener<RecaptchaResultData>() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.4
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(RecaptchaResultData recaptchaResultData) {
                    String tokenResult = recaptchaResultData.getTokenResult();
                    GoogleRecaptcha.this.currentType = 12;
                    if (!tokenResult.isEmpty()) {
                        GoogleRecaptcha.this.doGoogleRecaptcha(tokenResult);
                        Lxhw_AreaPlatform.getInstance().onTrackEventConst(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKEN_SUCCESS);
                    } else {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKEN_FAIL, "onSuccess but token null");
                    }
                }
            }).addOnFailureListener(activity, new OnFailureListener() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.3
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    GoogleRecaptcha.this.currentType = 13;
                    if (exc instanceof ApiException) {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKEN_FAIL, ((ApiException) exc).getStatus().toString());
                    } else {
                        Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKEN_FAIL, exc.toString());
                    }
                }
            });
        } catch (Throwable th) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKEN_FAIL, th.toString());
        }
    }

    public void onDestroy(Activity activity) {
        if (this.handle == null) {
            Log.d("GoogleRecaptcha", "reCAPTCHA onDestroy handle null");
            return;
        }
        try {
            Recaptcha.getClient(activity).close(this.handle).addOnSuccessListener(activity, new OnSuccessListener<Boolean>() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.6
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(Boolean bool) {
                }
            }).addOnFailureListener(activity, new OnFailureListener() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.5
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(Exception exc) {
                    if (exc instanceof ApiException) {
                        Log.d("GoogleRecaptcha", "reCAPTCHA close onFailure: apiErrorStatus =" + ((ApiException) exc).getStatus().toString());
                        return;
                    }
                    Log.d("GoogleRecaptcha", "reCAPTCHA close onFailure: Handle other failures =" + exc.toString());
                }
            });
        } catch (Throwable th) {
            Log.e("GoogleRecaptcha", "reCAPTCHA onDestroy e:" + th.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doGoogleRecaptcha(String str) {
        String recaptcha_key = Lxhw_AppInfoDecorator.getRecaptcha_key();
        if (TextUtils.isEmpty(recaptcha_key)) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_FAIL, "recaptcha_key empty");
            return;
        }
        HttpParamsCommon httpParamsCommon = new HttpParamsCommon();
        Map<String, Object> createCommonParams = httpParamsCommon.createCommonParams("doGoogleRecaptcha");
        createCommonParams.put("recaptcha_token", str);
        createCommonParams.put("site_key", recaptcha_key);
        httpParamsCommon.addSign(createCommonParams, AreaBaseService.GOOGLE_RECAPTCHA_ROUTE);
        HttpRequestCenter.getInstance().doRequestCommonAddHead("doGoogleIntegrity", createCommonParams, AreaBaseService.GOOGLE_RECAPTCHA_URL, new HttpRequestCallBack() { // from class: com.oversea.ab_firstarea.haiwai.GoogleRecaptcha.7
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
                                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_SUCCESS, str3);
                                } else {
                                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_FAIL, str3);
                                }
                            } else {
                                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_FAIL, str2);
                            }
                        } else {
                            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_FAIL, str2);
                        }
                    }
                } catch (Throwable th) {
                    Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_FAIL, str2);
                    th.printStackTrace();
                }
            }

            @Override // com.oversea.ab_firstarea.http.HttpRequestCallBack
            public void httpRequestFail(BaseBean baseBean) {
                if (baseBean == null) {
                    return;
                }
                Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_RECAPTCHA_TOKENCHECK_FAIL, "httpRequestFail" + baseBean.getCode() + " " + baseBean.getMessage());
            }
        });
    }
}
