package com.oversea.ab_firstarea.haiwai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.applinks.AppLinkData;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.oversea.ab_firstarea.dialog.Lxhw_DialogManage;
import com.oversea.ab_firstarea.dm.impl.Lxhw_ThirdLoginModelImpl;
import com.oversea.ab_firstarea.dpresenter.OnThirdLoginListener;
import com.oversea.ab_firstarea.net.model.LoginBean;
import com.oversea.ab_firstarea.net.service.ComConfig;
import com.oversea.ab_firstarea.p012f.p013a.AreaSdk;
import com.oversea.ab_firstarea.p012f.sdk.Lxhw_AreaPlatform;
import com.oversea.ab_firstarea.utils.ComUtil;
import com.oversea.ab_firstplatform.Lxhw_Platform;
import com.oversea.ab_firstplatform.Lxhw_XSDK;
import com.oversea.ab_firstplatform.custom.CustProgressDialog;
import com.oversea.ab_firstplatform.model.BaseBean;
import com.oversea.ab_firstplatform.statistics.ComConstants;
import com.xsdk.ab_firstbase.loading.LoadingDialog;
import com.xsdk.ab_firstbase.statisics.util.Constants;
import com.xsdk.ab_firstbase.statisics.util.ImageUtils;
import com.xsdk.ab_firstbase.statisics.util.LLog;
import com.xsdk.ab_firstbase.statisics.util.ToastUtils;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class FaceBookControl {
    private static CallbackManager callbackManager;
    private static FaceBookControl mInstance;
    private Activity mActivity;
    private CustProgressDialog mProgressdialog;
    private String TAG = "FaceBookControl";
    public String token = "";
    public String token_for_business = "";
    public String userid = "";
    boolean isfirstFail = false;
    private boolean isAutoLoginCur = false;

    private void getTargetUrlFromInboundIntent(Activity activity) {
    }

    public void facebookLogout() {
    }

    public void loginFaceBookBtn() {
    }

    private FaceBookControl() {
    }

    public static FaceBookControl getInstance() {
        if (mInstance == null) {
            synchronized (FaceBookControl.class) {
                if (mInstance == null) {
                    mInstance = new FaceBookControl();
                }
            }
        }
        return mInstance;
    }

    public void initFaceBook(final Activity activity) {
        try {
            this.mActivity = activity;
            this.isfirstFail = false;
            Log.i(this.TAG, "init facebook");
            LLog.i_noControl("facebook sdk version: " + FacebookSdk.getSdkVersion());
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.1
                @Override // com.facebook.FacebookCallback
                public void onCancel() {
                    HashMap hashMap = new HashMap();
                    hashMap.put(ComConfig.PC_FF_FACEBOOK_LOGIN_CANCEL, "1");
                    Lxhw_AreaPlatform.getInstance().onTrackEventAF(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_FACEBOOK_LOGIN_CANCEL, hashMap);
                    LoadingDialog.cancelDialogForLoading();
                    Log.i(FaceBookControl.this.TAG, "onCancel facebook login");
                    CookieSyncManager.createInstance(activity);
                    CookieManager.getInstance().removeAllCookie();
                    CookieSyncManager.getInstance().sync();
                }

                @Override // com.facebook.FacebookCallback
                public void onError(FacebookException facebookException) {
                    Log.e(FaceBookControl.this.TAG, "FacebookException , arg0 : " + facebookException.getMessage().toString());
                    HashMap hashMap = new HashMap();
                    hashMap.put(ComConfig.PC_FF_FACEBOOK_LOGIN_FAILED, facebookException.getMessage());
                    Lxhw_AreaPlatform.getInstance().onTrackEventAF(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_FACEBOOK_LOGIN_FAILED, hashMap);
                    LoadingDialog.cancelDialogForLoading();
                    if (!FaceBookControl.this.isfirstFail) {
                        FaceBookControl.this.isfirstFail = true;
                        FaceBookControl.this.setBehavior();
                    } else {
                        ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), facebookException.getMessage().toString());
                    }
                    if (!(facebookException instanceof FacebookAuthorizationException) || AccessToken.getCurrentAccessToken() == null) {
                        return;
                    }
                    LoginManager.getInstance().logOut();
                }

                @Override // com.facebook.FacebookCallback
                public void onSuccess(LoginResult loginResult) {
                    ComConstants.CTRL_TYPE = 2;
                    if (loginResult != null && loginResult.getAccessToken() != null) {
                        FaceBookControl.this.getGraphuser(loginResult.getAccessToken());
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put(ComConfig.PC_FF_FACEBOOK_LOGIN_FAILED, "login result == null || access token == null");
                    Lxhw_AreaPlatform.getInstance().onTrackEventAF(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_FACEBOOK_LOGIN_FAILED, hashMap);
                    LoadingDialog.cancelDialogForLoading();
                    Log.e(FaceBookControl.this.TAG, "null=arg0||null!=arg0.getAccessToken()||null!=arg0.getAccessToken().getUserId()||null!=arg0.getAccessToken().getToken()");
                }
            });
            getFetchDeferredAppLinkData(activity);
        } catch (Throwable th) {
            Lxhw_AreaPlatform.getInstance().onTrackEventKeyValue(Lxhw_XSDK.getInstance().getContext(), ComConfig.PC_FF_FACEBOOK_LOGIN_ERROR, "fb login init throwable: " + th.toString());
            Log.d(this.TAG, "fb login init try catch Throwable: " + th.toString());
        }
    }

    private void getFetchDeferredAppLinkData(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            FacebookSdk.setAutoInitEnabled(true);
            FacebookSdk.fullyInitialize();
            AppLinkData.fetchDeferredAppLinkData(activity, new AppLinkData.CompletionHandler() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.2
                @Override // com.facebook.applinks.AppLinkData.CompletionHandler
                public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                    if (appLinkData == null) {
                        Log.i(FaceBookControl.this.TAG, "getFetchDeferredAppLinkData appLinkData= null");
                        if (ComConstants.CTRL_TYPE == 10 && FaceBookControl.this.isAutoLoginCur) {
                            Lxhw_Platform.getInstance().login(Lxhw_XSDK.getInstance().getContext());
                            return;
                        }
                        return;
                    }
                    Log.i(FaceBookControl.this.TAG, "getFetchDeferredAppLinkData appLinkData=" + appLinkData.toString());
                }
            });
        } catch (Throwable th) {
            Log.i(this.TAG, "getFetchDeferredAppLinkData e=" + th.toString());
        }
    }

    public static CallbackManager initCallbackManager(Activity activity) {
        if (!FacebookSdk.isInitialized()) {
            FacebookSdk.sdkInitialize(activity);
        }
        CallbackManager create = CallbackManager.Factory.create();
        callbackManager = create;
        return create;
    }

    public void setFacebookCallBack(int i, int i2, Intent intent) {
        callbackManager.onActivityResult(i, i2, intent);
    }

    public void loginFacebook(boolean z) {
        try {
            this.isAutoLoginCur = z;
            if (z) {
                ComConstants.CTRL_TYPE = 10;
                AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                if (currentAccessToken != null && !currentAccessToken.isExpired()) {
                    getGraphuser(currentAccessToken);
                } else {
                    LoginManager.getInstance().logInWithReadPermissions(this.mActivity, Arrays.asList("public_profile"));
                }
            } else {
                facebookLogout();
                ComConstants.CTRL_TYPE = 10;
                LoginManager.getInstance().logInWithReadPermissions(this.mActivity, Arrays.asList("public_profile"));
            }
        } catch (Exception e) {
            LoadingDialog.cancelDialogForLoading();
            LLog.v_noControl("loginFacebook " + e.toString());
        }
    }

    public void setBehavior() {
        loginFacebook(false);
    }

    public void getGraphuser(final AccessToken accessToken) {
        GraphRequest newMeRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.3
            @Override // com.facebook.GraphRequest.GraphJSONObjectCallback
            public void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                if (jSONObject != null) {
                    Log.i(FaceBookControl.this.TAG, "jsonobject" + jSONObject);
                    String optString = jSONObject.optString("token_for_business");
                    if (!TextUtils.isEmpty(optString)) {
                        FaceBookControl.this.autoLogin(accessToken.getUserId(), accessToken.getToken(), optString);
                        return;
                    }
                    LoadingDialog.cancelDialogForLoading();
                    return;
                }
                LoadingDialog.cancelDialogForLoading();
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, "token_for_business");
        newMeRequest.setParameters(bundle);
        newMeRequest.executeAsync();
    }

    public void getAppFriends() {
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/friends?fields=id,name,picture{url}", null, HttpMethod.GET, new GraphRequest.Callback() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.4
            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                Log.i(FaceBookControl.this.TAG, "response=" + graphResponse.toString());
            }
        }).executeAsync();
    }

    public void revokeAuth() {
        try {
            new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions", null, HttpMethod.DELETE, new GraphRequest.Callback() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.5
                @Override // com.facebook.GraphRequest.Callback
                public void onCompleted(GraphResponse graphResponse) {
                    if (graphResponse.getError() == null) {
                        Log.d("TAG", "Access token revoked...");
                        return;
                    }
                    Log.d("TAG", "Error revoking access token: " + graphResponse.getError().getErrorMessage());
                }
            }).executeAsync();
        } catch (Exception unused) {
        }
    }

    public void getTaggable_friends() {
        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/taggable_friends?fields=id,name,picture{url}", null, HttpMethod.GET, new GraphRequest.Callback() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.6
            @Override // com.facebook.GraphRequest.Callback
            public void onCompleted(GraphResponse graphResponse) {
                Log.i(FaceBookControl.this.TAG, "response=" + graphResponse.toString());
            }
        }).executeAsync();
    }

    public void updateUI(LoginResult loginResult) {
        if (loginResult != null && loginResult.getAccessToken() != null) {
            getGraphuser(loginResult.getAccessToken());
        } else {
            Log.e(this.TAG, "null=arg0||null!=arg0.getAccessToken()||null!=arg0.getAccessToken().getUserId()||null!=arg0.getAccessToken().getToken()");
        }
    }

    public void autoLogin(String str, String str2, String str3) {
        this.token = str2;
        this.token_for_business = str3;
        this.userid = str;
        new Lxhw_ThirdLoginModelImpl().thirdLogin(1, str2, str, str3, new OnThirdLoginListener<LoginBean>() { // from class: com.oversea.ab_firstarea.haiwai.FaceBookControl.7
            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqSuccess(String str4, LoginBean loginBean) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), loginBean.getMessage());
                LoadingDialog.cancelDialogForLoading();
                Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "LoginDialog");
                if (Lxhw_AreaPlatform.getInstance().isUseNewSdkLogin) {
                    Lxhw_DialogManage.getInstance().removeFragment(Lxhw_XSDK.getInstance().getContext(), "loginSelectDialog");
                }
                ImageUtils.setSharePreferences(FaceBookControl.this.mActivity, Constants.SDK_LOGIN_TYPE, "3");
                AreaSdk.getInstance().onAuthResult(loginBean);
            }

            @Override // com.oversea.ab_firstarea.dpresenter.OnBaseListener
            public void onReqFail(String str4, BaseBean baseBean) {
                ToastUtils.toastLongShow(Lxhw_XSDK.getInstance().getContext(), ComUtil.getBaseBeanTip(baseBean));
                LoadingDialog.cancelDialogForLoading();
                if (FaceBookControl.this.isAutoLoginCur) {
                    Lxhw_Platform.getInstance().login(Lxhw_XSDK.getInstance().getContext());
                }
                Lxhw_XSDK.getInstance().onAuthResult(-1);
            }
        });
    }
}
