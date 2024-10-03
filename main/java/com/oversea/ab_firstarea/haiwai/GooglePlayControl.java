package com.oversea.ab_firstarea.haiwai;

import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ThirdLoginModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnThirdLoginListener;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import com.xsdk.ab_firstbase.statisics.util.Util;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GooglePlayControl {
    public static GoogleSignInAccount account;
    private static GooglePlayControl mInstance;
    public int loginCount = 0;
    private String GAID = "";
    public String token = "";
    public String userid = "";

    private GooglePlayControl() {
    }

    public String getGAid() {
        if (!TextUtils.isEmpty(this.GAID)) {
            return this.GAID;
        }
        this.GAID = AdvertisingIdClient.doGoogleAdId();
        LLog.v_Control("GAID=" + this.GAID);
        return this.GAID;
    }

    public static GooglePlayControl getInstance() {
        if (mInstance == null) {
            mInstance = new GooglePlayControl();
        }
        return mInstance;
    }

    public void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            this.loginCount++;
            GoogleSignInAccount result = task.getResult(ApiException.class);
            account = result;
            if (result != null) {
                updateUI();
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put(ComConfig.PC_FF_GOOGLE_LOGIN_FAILED, "google login account == null");
                LoadingDialog.cancelDialogForLoading();
                Lxhw_AreaPlatform.getInstance().onTrackEventAF(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_LOGIN_FAILED, hashMap);
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), Lxhw_XSDK.getInstance().getContext().getString(Util.getIdByName(Lxhw_XSDK.getInstance().getContext(), TypedValues.Custom.S_STRING, "tw_loginfail")) + "account==null");
            }
        } catch (ApiException e) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(ComConfig.PC_FF_GOOGLE_LOGIN_FAILED, "status: " + e.getStatus().getStatusCode() + "," + e.getMessage());
            Lxhw_AreaPlatform.getInstance().onTrackEventAF(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_GOOGLE_LOGIN_FAILED, hashMap2);
            LoadingDialog.cancelDialogForLoading();
            if (e.getStatus().getStatusCode() == 8) {
                if (this.loginCount == 1) {
                    Lxhw_DialogManage.getInstance().showGoogleLogin(Lxhw_XSDK.getInstance().getContext());
                }
            } else {
                e.getStatus().getStatusCode();
            }
            e.printStackTrace();
        }
    }

    public void updateUI() {
        autoLogin(account.getId(), !TextUtils.isEmpty(account.getIdToken()) ? account.getIdToken() : "token");
    }

    public void autoLogin(String str, String str2) {
        this.token = str2;
        this.userid = str;
        new Lxhw_ThirdLoginModelImpl().thirdLogin(2, str2, str, "", new OnThirdLoginListener<LoginBean>() { // from class: com.oversea.ab_firstarea.haiwai.GooglePlayControl.1
            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqSuccess(String str3, LoginBean loginBean) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), loginBean.getMessage());
                LoadingDialog.cancelDialogForLoading();
                Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "LoginDialog");
                if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
                    Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "loginSelectDialog");
                }
                ImageUtils.setSharePreferences(Lxhw_XSDK.getInstance().getContext(), Constants.SDK_LOGIN_TYPE, "4");
                AreaSdk.getInstance().onAuthResult(loginBean);
            }

            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqFail(String str3, BaseBean baseBean) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
                LoadingDialog.cancelDialogForLoading();
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }
}
