package com.p008ld.sdk.p009ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.facebook.share.internal.ShareConstants;
import com.linecorp.linesdk.BuildConfig;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.bean.LDLoginResult;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDCallback2;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.internal.LDThirdLoginCallback;
import com.p008ld.sdk.p009ui.zzb.zzo;
import com.p008ld.sdk.p009ui.zzc.zza;
import com.p008ld.sdk.p009ui.zzc.zzb;
import com.p008ld.sdk.p009ui.zzc.zzc;
import com.p008ld.sdk.p009ui.zzc.zzd;
import com.p008ld.sdk.p009ui.zzc.zze;
import com.p008ld.sdk.p009ui.zzc.zzf;
import com.p008ld.sdk.p009ui.zzc.zzg;
import com.p008ld.sdk.p009ui.zzc.zzh;
import com.p008ld.sdk.p009ui.zzc.zzi;
import com.p008ld.sdk.p009ui.zzc.zzj;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzs;
import com.p008ld.sdk.util.zzt;

/* loaded from: classes2.dex */
public class LoginActivity extends Activity implements View.OnClickListener {
    public static final int OPERATE_ACCOUNT_LOGIN = 5;
    public static final int OPERATE_ACCOUNT_REGISTER = 10;
    public static final int OPERATE_AUTO_LOGIN = 13;
    public static final int OPERATE_AUTO_LOGIN_FAIL = 115;
    public static final int OPERATE_BIND_GOOGLE = 10004;
    public static final int OPERATE_FORGET_PASSWORD = 30;
    public static final int OPERATE_GET_CODE = 12;
    public static final int OPERATE_GET_PHONE_STATE = 10002;
    public static final int OPERATE_JUMP_ACCOUNT_AGREEMENT = 33;
    public static final int OPERATE_JUMP_ACCOUNT_LOGIN = 11;
    public static final int OPERATE_JUMP_ACCOUNT_PRIVACY = 35;
    public static final int OPERATE_JUMP_FACEBOOK_LOGIN = 3;
    public static final int OPERATE_JUMP_FINISH_ACTIVITY = 40;
    public static final int OPERATE_JUMP_FORGET_PASSWORD = 4;
    public static final int OPERATE_JUMP_FORGET_PASSWORD_FROM_OUTSIDE = 41;
    public static final int OPERATE_JUMP_GOOGLE_LOGIN = 2;
    public static final int OPERATE_JUMP_LINE_LOGIN = 6;
    public static final int OPERATE_JUMP_MUST_BIND_EMAIL = 42;
    public static final int OPERATE_JUMP_REGISTER = 105;
    public static final int OPERATE_PHONE_AGREE_AGREEMENT = 111;
    public static final int OPERATE_PHONE_AGREE_AGREEMENT_PAGE = 110;
    public static final int OPERATE_PHONE_DISAGREE_AGREEMENT = 112;
    public static final int OPERATE_SHARE_DETAILED_LIST = 36;
    public static final int OPERATE_SWITCH_ACCOUNT = 1;
    public static final int OPERATE_UNKNOWN = -1;
    private static final String TAG_PAGE_ID = "pageId";
    private static final String TAG_URL = "url";
    private zza accountHistoryView;
    private zzb accountLoginView;
    private zzc accountRegisterView;
    private zzd agreementAgreeView;
    private zze agreementView;
    private zzf autoLoginView;
    private zzh bindEmailLoginView;
    private FrameLayout containerView;
    private zzg currentView;
    private zzi emailLoginView;
    private zzj forgetPasswordView;
    private CountDownTimer mCountDownTimer;
    private com.p008ld.sdk.p009ui.zzb.zzj mLoginDialog;
    private String mUrl;
    private zzg oldView;
    private boolean mJumpPage = false;
    private String stateCode = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(16777216, 16777216);
        this.containerView = new FrameLayout(this);
        setFinishOnTouchOutside(getPackageName().equals("com.android.flysilkworm"));
        LDSdk.trackEvent(LDTrackEvent.DISPLAY_LOGIN_PAGE, (LDException) null);
        if (jumpLauncherPageId()) {
            return;
        }
        this.mJumpPage = false;
        if (com.p008ld.sdk.core.zza.zza.zzf().zzc() != null && !com.p008ld.sdk.util.zzi.zzb(this)) {
            zzf zzfVar = new zzf(this, false, this);
            this.autoLoginView = zzfVar;
            replaceView(zzfVar);
        } else {
            zzb zzbVar = new zzb(this, this);
            this.accountLoginView = zzbVar;
            replaceView(zzbVar);
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 88) {
            zzs.zzb(this);
        }
    }

    public static boolean startLoginActivityByPageId(Activity activity, String str, int i) {
        try {
            Intent intent = new Intent(activity, (Class<?>) LoginActivity.class);
            intent.putExtra(TAG_PAGE_ID, i);
            intent.putExtra("url", str);
            activity.startActivityForResult(intent, i);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean startLoginActivityByPageId(Activity activity, int i) {
        return startLoginActivityByPageId(activity, "", i);
    }

    public static void resultToActivity(Activity activity, int i, String str) {
        Intent intent = new Intent();
        intent.putExtra("status", i);
        intent.putExtra("desc", str);
        activity.setResult(-1, intent);
        activity.finish();
    }

    private boolean jumpLauncherPageId() {
        if (getIntent() == null) {
            return false;
        }
        int intExtra = getIntent().getIntExtra(TAG_PAGE_ID, -1);
        this.mUrl = getIntent().getStringExtra("url");
        if (intExtra == -1) {
            return false;
        }
        if (intExtra == 10004) {
            thirdLogin(LoginMode.GOOGLE, 1002);
            return true;
        }
        if (intExtra == 10002) {
            zzs.zza(this);
            return true;
        }
        this.mJumpPage = true;
        View view = new View(this);
        view.setTag(Integer.valueOf(intExtra));
        onClick(view);
        return true;
    }

    private void replaceView(zzg zzgVar) {
        this.containerView.removeAllViews();
        this.containerView.addView(zzgVar.zzb);
        setContentView(this.containerView);
        this.containerView.requestFocus();
        this.currentView = zzgVar;
        if (zzgVar == this.autoLoginView) {
            this.containerView.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (isFinishing()) {
            return;
        }
        if (intValue == 0 && (this.currentView instanceof zze)) {
            finish();
        } else {
            intentPage(this, intValue, view);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x0019. Please report as an issue. */
    private void intentPage(Activity activity, int i, View view) {
        if (i == 30) {
            zzj zzjVar = this.forgetPasswordView;
            if (zzjVar != null) {
                zzjVar.zzb();
                return;
            }
            return;
        }
        int i2 = 0;
        if (i != 33) {
            if (i != 105) {
                if (i != 115) {
                    if (i != 35 && i != 36) {
                        switch (i) {
                            case 1:
                                if (this.accountHistoryView == null) {
                                    this.accountHistoryView = new zza(this, this);
                                }
                                replaceView(this.accountHistoryView);
                                return;
                            case 2:
                                onGoogleLogin();
                                return;
                            case 3:
                                onFaceBookLogin();
                                return;
                            case 4:
                                if (this.forgetPasswordView == null) {
                                    this.forgetPasswordView = new zzj(activity, this);
                                }
                                this.forgetPasswordView.zza(activity, false);
                                replaceView(this.forgetPasswordView);
                                return;
                            case 5:
                                if (this.emailLoginView == null) {
                                    this.emailLoginView = new zzi(this, this);
                                }
                                replaceView(this.emailLoginView);
                                return;
                            case 6:
                                onLineLogin();
                                return;
                            default:
                                switch (i) {
                                    case 10:
                                        break;
                                    case 11:
                                        break;
                                    case 12:
                                        if (view.getId() == zzt.zze(activity, "find_account_password_get_code")) {
                                            this.forgetPasswordView.zza(activity);
                                            return;
                                        }
                                        return;
                                    case 13:
                                        zzf zzfVar = this.autoLoginView;
                                        if (zzfVar == null) {
                                            this.autoLoginView = new zzf(this, true, this);
                                        } else {
                                            zzfVar.zza(false, true);
                                        }
                                        replaceView(this.autoLoginView);
                                        return;
                                    default:
                                        switch (i) {
                                            case 40:
                                                finish();
                                                return;
                                            case 41:
                                                if (this.forgetPasswordView == null) {
                                                    this.forgetPasswordView = new zzj(activity, this);
                                                }
                                                this.forgetPasswordView.zza(activity, true);
                                                replaceView(this.forgetPasswordView);
                                                return;
                                            case 42:
                                                if (this.bindEmailLoginView == null) {
                                                    this.bindEmailLoginView = new zzh(this, this);
                                                }
                                                replaceView(this.bindEmailLoginView);
                                                return;
                                            default:
                                                switch (i) {
                                                    case 110:
                                                        if (this.agreementAgreeView == null) {
                                                            this.agreementAgreeView = new zzd(this, this);
                                                        }
                                                        if (this.oldView == null) {
                                                            this.oldView = this.currentView;
                                                        }
                                                        replaceView(this.agreementAgreeView);
                                                        return;
                                                    case 111:
                                                    case 112:
                                                        if (i == 111) {
                                                            this.oldView.zza();
                                                        }
                                                        replaceView(this.oldView);
                                                        return;
                                                    default:
                                                        return;
                                                }
                                        }
                                }
                        }
                    }
                }
                if (this.accountLoginView == null) {
                    this.accountLoginView = new zzb(activity, this);
                }
                replaceView(this.accountLoginView);
                return;
            }
            if (this.accountRegisterView == null) {
                this.accountRegisterView = new zzc(activity, this);
            }
            replaceView(this.accountRegisterView);
            return;
        }
        if (this.agreementView == null) {
            this.agreementView = new zze(activity, this);
        }
        zzg zzgVar = this.currentView;
        if (zzgVar instanceof zzb) {
            i2 = 11;
        } else if (zzgVar instanceof zzc) {
            i2 = 10;
        } else if (zzgVar instanceof zzd) {
            i2 = 110;
        }
        this.agreementView.zza(activity, this, i2, i, this.mUrl);
        replaceView(this.agreementView);
    }

    private void onGoogleLogin() {
        thirdLogin(LoginMode.GOOGLE, 1001);
    }

    private void onLineLogin() {
        thirdLogin(LoginMode.LINE, 1003);
    }

    private void onFaceBookLogin() {
        thirdLogin(LoginMode.FACEBOOK, 1004);
    }

    private void thirdLogin(final LoginMode loginMode, final int i) {
        checkThirdAvailable(loginMode, new LDCallback2<Integer, String>() { // from class: com.ld.sdk.ui.LoginActivity.1
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Integer num, String str) {
                if (num.intValue() == 4) {
                    if (!com.p008ld.sdk.util.zzi.zza()) {
                        LoginActivity.this.thirdLoginByBrowser(loginMode);
                        return;
                    } else {
                        zzn.zza(LoginActivity.this, str);
                        return;
                    }
                }
                if (num.intValue() == 1 || num.intValue() == 3) {
                    LoginActivity.this.loginByStoreOrBrowser(loginMode, i);
                } else if (num.intValue() == 2) {
                    LoginActivity.this.thirdLoginByBrowser(loginMode);
                }
            }
        });
    }

    private void checkThirdAvailable(LoginMode loginMode, LDCallback2<Integer, String> lDCallback2) {
        if (loginMode != LoginMode.GOOGLE) {
            String str = loginMode == LoginMode.LINE ? BuildConfig.LINE_APP_PACKAGE_NAME : "com.facebook.katana";
            if (!com.p008ld.sdk.util.zzi.zzd(this, str)) {
                if (thirdPartyLogin(loginMode)) {
                    return;
                }
                lDCallback2.done(4, str);
                return;
            }
            lDCallback2.done(3, "");
            return;
        }
        if (LDUtil.isGooglePlayServicesAvailable(this)) {
            lDCallback2.done(1, "");
        } else {
            lDCallback2.done(2, "");
        }
    }

    private boolean thirdPartyLogin(final LoginMode loginMode) {
        String str;
        ConfigBean zze = com.p008ld.sdk.core.zza.zza.zzf().zze();
        if (loginMode == LoginMode.LINE) {
            str = zze.lineLoginUrl;
        } else {
            str = loginMode == LoginMode.FACEBOOK ? zze.facebookLoginUrl : null;
        }
        if (com.p008ld.sdk.util.zzi.zza((CharSequence) str)) {
            return false;
        }
        new zzo(this, loginMode, str, new LDThirdLoginCallback() { // from class: com.ld.sdk.ui.LoginActivity.2
            @Override // com.p008ld.sdk.internal.LDThirdLoginCallback
            public void onError(int i, Exception exc) {
                LDLog.m573e("LoginActivity -> ThirdLoginDialog onError:" + i + " , " + exc);
                LDSdk.trackLoginEvent(LDTrackEvent.LOGIN, new LDException(exc), loginMode.getValue());
            }

            @Override // com.p008ld.sdk.internal.LDThirdLoginCallback
            public void onSuccess(LoginMode loginMode2, LDLoginResult lDLoginResult) {
                LDLog.m573e("LoginActivity -> ThirdLoginDialog onSuccess:" + loginMode2 + " ," + lDLoginResult);
                LoginActivity.this.accountLoginView.zza(loginMode, lDLoginResult.getToken(), lDLoginResult.getUserName());
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginByStoreOrBrowser(LoginMode loginMode, int i) {
        try {
            thirdLoginByStore(loginMode, i);
        } catch (Exception e) {
            e.printStackTrace();
            thirdLoginByBrowser(loginMode);
        }
    }

    private void thirdLoginByStore(LoginMode loginMode, int i) {
        Intent intent = new Intent();
        intent.putExtra("packageName", getPackageName());
        intent.putExtra("loginMode", loginMode.getValue());
        intent.putExtra("requestCode", i);
        intent.setComponent(new ComponentName("com.android.ld.appstore", "com.android.ld.appstore.app.activity.ThirdLoginActivity"));
        startActivityForResult(intent, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean thirdLoginByBrowser(LoginMode loginMode) {
        ConfigBean zze = com.p008ld.sdk.core.zza.zza.zzf().zze();
        if (com.p008ld.sdk.util.zzi.zza((CharSequence) zze.thirdLoginH5Url)) {
            LDUtil.toast(zzt.zza(this, "ld_dialog_result_error_title"));
            return false;
        }
        this.stateCode = LDUtil.genUUID();
        com.p008ld.sdk.util.zzi.zza(this, com.p008ld.sdk.util.zzi.zzc, zze.thirdLoginH5Url + "?from=" + loginMode.getValue().toLowerCase() + "&extappid=" + LDSdk.getAppId() + "&state=" + this.stateCode);
        showLoadingDialog();
        if (this.mCountDownTimer == null) {
            this.mCountDownTimer = new CountDownTimer(300000L, 15000L) { // from class: com.ld.sdk.ui.LoginActivity.3
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    LoginActivity.this.checkLoginStatus();
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    LDLog.m573e("LoginActivity -> checkLoginStatus : onFinish");
                    LoginActivity.this.cancelLoadingDialog();
                }
            };
        }
        this.mCountDownTimer.start();
        return true;
    }

    private void showLoadingDialog() {
        com.p008ld.sdk.p009ui.zzb.zzj zzjVar = new com.p008ld.sdk.p009ui.zzb.zzj(this, true);
        this.mLoginDialog = zzjVar;
        zzjVar.setCancelable(false);
        this.mLoginDialog.setCanceledOnTouchOutside(false);
        this.mLoginDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.ui.LoginActivity.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (LoginActivity.this.mLoginDialog != null) {
                    LoginActivity.this.mLoginDialog.zzb();
                }
            }
        });
        this.mLoginDialog.zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.LoginActivity.5
            @Override // com.p008ld.sdk.internal.LDCallback1
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Boolean bool) {
                LoginActivity.this.cancelLoadingDialog();
            }
        });
        this.mLoginDialog.show();
        this.mLoginDialog.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelLoadingDialog() {
        this.stateCode = null;
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        com.p008ld.sdk.p009ui.zzb.zzj zzjVar = this.mLoginDialog;
        if (zzjVar == null || !zzjVar.isShowing()) {
            return;
        }
        this.mLoginDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLoginStatus() {
        if (com.p008ld.sdk.util.zzi.zza((CharSequence) this.stateCode)) {
            return;
        }
        LDLog.m573e("LoginActivity -> checkLoginStatus : stateCode = " + this.stateCode);
        com.p008ld.sdk.core.zza.zza.zzf().zzb(this.stateCode, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.ui.LoginActivity.6
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(LDUser lDUser, LDException lDException) {
                if (LoginActivity.this.isFinishing() || LoginActivity.this.isDestroyed()) {
                    return;
                }
                if (lDUser != null) {
                    LDLog.m573e("LoginActivity -> checkLoginStatus success : " + lDUser + " , " + LoginActivity.this.stateCode);
                    if (LoginActivity.this.mCountDownTimer != null) {
                        LoginActivity.this.mCountDownTimer.cancel();
                    }
                    LoginActivity.this.stateCode = null;
                    LoginActivity.this.accountLoginView.zza(lDUser, new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.LoginActivity.6.1
                        @Override // com.p008ld.sdk.internal.LDCallback1
                        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                        public void done(Boolean bool) {
                            if (LoginActivity.this.mLoginDialog == null || !LoginActivity.this.mLoginDialog.isShowing()) {
                                return;
                            }
                            LoginActivity.this.mLoginDialog.dismiss();
                        }
                    });
                    return;
                }
                LDLog.m573e("LoginActivity -> checkLoginStatus error :" + lDException + " , " + LoginActivity.this.stateCode);
            }
        });
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LDLog.m573e("LoginActivity -> onNewIntent :" + intent);
        handleIntentResult(intent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        LDLog.m573e("LoginActivity-> onActivityResult：" + i + " , " + i2 + " , " + intent);
        super.onActivityResult(i, i2, intent);
        handleIntentResult(intent);
    }

    private void handleSuccess(LoginMode loginMode, LDLoginResult lDLoginResult) {
        if (loginMode == LoginMode.GOOGLE && lDLoginResult.getRequestCode() == 1002) {
            Intent intent = new Intent();
            intent.putExtra("token", lDLoginResult.getToken());
            intent.putExtra("googleAccount", lDLoginResult.getUserName());
            intent.setAction("bind_google_action");
            sendBroadcast(intent);
            finish();
            return;
        }
        this.accountLoginView.zza(loginMode, lDLoginResult.getToken(), lDLoginResult.getUserName());
    }

    private void handleError(int i, Exception exc) {
        String value;
        if (i == 1001) {
            value = LoginMode.GOOGLE.getValue();
        } else if (i == 1004) {
            value = LoginMode.FACEBOOK.getValue();
        } else {
            value = i == 1003 ? LoginMode.LINE.getValue() : "";
        }
        if (!com.p008ld.sdk.util.zzi.zza((CharSequence) value)) {
            LDSdk.trackLoginEvent(LDTrackEvent.LOGIN, new LDException(exc), value);
        }
        LDUtil.toast(exc.toString());
        if (i == 1002) {
            Intent intent = new Intent();
            intent.putExtra("token", "");
            intent.putExtra("googleAccount", "");
            intent.setAction("bind_google_action");
            sendBroadcast(intent);
            finish();
        }
    }

    private void handleIntentResult(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            if (intent.hasExtra("result") && intent.hasExtra(ShareConstants.MEDIA_TYPE)) {
                LDLoginResult lDLoginResult = (LDLoginResult) intent.getParcelableExtra("result");
                String stringExtra = intent.getStringExtra(ShareConstants.MEDIA_TYPE);
                if (!com.p008ld.sdk.util.zzi.zza((CharSequence) stringExtra)) {
                    LoginMode valueOf = LoginMode.valueOf(stringExtra);
                    if (valueOf != null) {
                        LDLog.m573e("LoginActivity-> handleIntentResult：type = " + stringExtra + " , result = " + lDLoginResult);
                        handleSuccess(valueOf, lDLoginResult);
                    } else {
                        LDLog.m573e("LoginActivity-> handleIntentResult：mode is null");
                    }
                } else {
                    LDLog.m573e("LoginActivity-> handleIntentResult：type is null");
                }
            } else if (intent.hasExtra("error") && intent.hasExtra("requestCode")) {
                Exception exc = (Exception) intent.getSerializableExtra("error");
                int intExtra = intent.getIntExtra("requestCode", -1);
                LDLog.m573e("LoginActivity-> handleIntentResult：code = " + intExtra + " , e = " + exc);
                handleError(intExtra, exc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        cancelLoadingDialog();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        zzc zzcVar;
        zzi zziVar;
        if (i == 66) {
            zzg zzgVar = this.currentView;
            if ((zzgVar instanceof zzi) && (zziVar = this.emailLoginView) != null) {
                zziVar.zza(this);
            } else if ((zzgVar instanceof zzc) && (zzcVar = this.accountRegisterView) != null) {
                zzcVar.zza(this);
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        zzf zzfVar;
        zzg zzgVar = this.currentView;
        if (zzgVar != null && (zzfVar = this.autoLoginView) != null && zzgVar == zzfVar) {
            zzfVar.zzc();
            returnLoginView();
        }
        zzg zzgVar2 = this.currentView;
        if ((zzgVar2 instanceof zze) && this.mJumpPage) {
            super.onBackPressed();
            return;
        }
        if ((zzgVar2 instanceof zzb) && (com.p008ld.sdk.util.zzi.zzb(this) || LDSdk.getLoginViewBackPressEnable())) {
            super.onBackPressed();
            return;
        }
        zzg zzgVar3 = this.currentView;
        if (zzgVar3 == null || zzgVar3 == this.agreementView) {
            return;
        }
        returnLoginView();
    }

    private void returnLoginView() {
        if (this.accountLoginView == null) {
            this.accountLoginView = new zzb(this, this);
        }
        replaceView(this.accountLoginView);
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.fontScale != 1.0f) {
            getResources();
        }
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources.getConfiguration().fontScale != 1.0f) {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (com.p008ld.sdk.util.zzi.zzd()) {
                return true;
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
