package com.p008ld.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.bean.GameRoleInfo;
import com.p008ld.sdk.core.bean.LDProductInfo;
import com.p008ld.sdk.core.bean.LDProductQueryParam;
import com.p008ld.sdk.core.bean.LdGamePayInfo;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.download.LDApkReceiver;
import com.p008ld.sdk.internal.LDCallback;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDExitCallback;
import com.p008ld.sdk.internal.LDLoginCallback;
import com.p008ld.sdk.internal.LDNotLoginException;
import com.p008ld.sdk.internal.LDPayCallback;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.internal.LDValidate;
import com.p008ld.sdk.p009ui.LoginActivity;
import com.p008ld.sdk.p009ui.floatview.FlyingBall;
import com.p008ld.sdk.p009ui.zzb.zzf;
import com.p008ld.sdk.p009ui.zzb.zzi;
import com.p008ld.sdk.p009ui.zzb.zzl;
import com.p008ld.sdk.p009ui.zzb.zzn;
import com.p008ld.sdk.track.LDTrackEvent;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;
import java.util.List;

/* compiled from: LDSdkHandler.java */
/* loaded from: classes2.dex */
public class zzb implements ISdkHandler {
    private zzf zza;
    private LDApkReceiver zzb;
    private zzn zzc;
    private zzi zzd;

    @Override // com.p008ld.sdk.ISdkHandler
    public void init(final Activity activity, final LDCallback<Boolean> lDCallback) {
        UserAccountMgr.zza().zza(activity);
        if (LDSdk.getFloatViewEnable()) {
            FlyingBall.getInstance().init(activity);
        }
        zza.zzf().zza(activity, new LDQueryCallback<ConfigBean>() { // from class: com.ld.sdk.zzb.1
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(ConfigBean configBean, LDException lDException) {
                Activity activity2;
                LDSdk.trackEvent(LDTrackEvent.INIT_CP_SDK, lDException);
                if (configBean != null) {
                    LDLog.m573e("LDSdkHandler -> init success ：" + LDUtil.toJson(configBean));
                    LDCallback lDCallback2 = lDCallback;
                    if (lDCallback2 != null) {
                        lDCallback2.done(true, null);
                    }
                    if (!configBean.hasNewVersion || (activity2 = activity) == null || activity2.isFinishing()) {
                        return;
                    }
                    zzb.this.zza(activity);
                    if (zzb.this.zza == null || !zzb.this.zza.isShowing()) {
                        zzb.this.zza = new zzf(activity, configBean);
                        zzb.this.zza.show();
                        return;
                    }
                    return;
                }
                LDLog.m573e("LDSdkHandler -> init error ：" + lDException);
                LDCallback lDCallback3 = lDCallback;
                if (lDCallback3 != null) {
                    lDCallback3.done(false, lDException);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(Activity activity) {
        this.zzb = new LDApkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        activity.registerReceiver(this.zzb, intentFilter);
    }

    private void zzb(Activity activity) {
        if (this.zzb == null || activity == null || activity.isFinishing()) {
            return;
        }
        activity.unregisterReceiver(this.zzb);
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void showLoginView(Activity activity, LDLoginCallback lDLoginCallback) {
        if (LDValidate.checkNullOrEmpty(zza.zzf().zze(), lDLoginCallback)) {
            UserAccountMgr.zza().zza(lDLoginCallback);
            if (LDSdk.getFloatViewEnable()) {
                showFloatWindow(activity, true);
            }
            try {
                activity.startActivity(new Intent(activity, (Class<?>) LoginActivity.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void autoLogin(final Activity activity, final LDLoginCallback lDLoginCallback) {
        LDUser zzc = zza.zzf().zzc();
        if (zzc != null) {
            UserAccountMgr.zza().zza(lDLoginCallback);
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.loginMode = LoginMode.AUTO;
            loginInfo.uid = zzc.getUid();
            loginInfo.token = zzc.getToken();
            zza.zzf().zza(loginInfo, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.zzb.2
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(LDUser lDUser, LDException lDException) {
                    if (lDUser != null) {
                        UserAccountMgr.zza().zza(true, lDUser.getSpaceUserId(), lDUser.getCpToken(), "");
                        return;
                    }
                    LDLog.m573e("LDSdkHandler -> autoLogin error ：" + lDException);
                    Activity activity2 = activity;
                    if (activity2 == null || activity2.isFinishing() || lDLoginCallback == null) {
                        return;
                    }
                    LDUtil.toast(lDException.toString());
                    zzb.this.showLoginView(activity, lDLoginCallback);
                }
            });
            return;
        }
        showLoginView(activity, lDLoginCallback);
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void showChargeView(Activity activity, LdGamePayInfo ldGamePayInfo, LDPayCallback lDPayCallback) {
        if (LDValidate.checkNullOrEmpty(activity, "context", lDPayCallback)) {
            if (zza.zzf().zza() == null) {
                lDPayCallback.onError(new LDNotLoginException("Not Logged In"));
                return;
            }
            if (ldGamePayInfo == null || ldGamePayInfo.checkError()) {
                String zza = zzt.zza(activity, "ld_pay_parameter_error_text");
                if (ldGamePayInfo == null) {
                    zza = zza + "：payInfo is null";
                } else if (TextUtils.isEmpty(ldGamePayInfo.cpOrderId)) {
                    zza = zza + "：cpOrderId is empty";
                } else if (TextUtils.isEmpty(ldGamePayInfo.cpUserId)) {
                    zza = zza + "：cpUserId is empty";
                } else if (TextUtils.isEmpty(ldGamePayInfo.productId)) {
                    zza = zza + "：productId is empty";
                } else if (TextUtils.isEmpty(ldGamePayInfo.tradeName)) {
                    zza = zza + "：tradeName is empty";
                } else if (TextUtils.isEmpty(ldGamePayInfo.currencyType)) {
                    zza = zza + "：currencyType is empty";
                } else if (ldGamePayInfo.commodityPrice == 0) {
                    zza = zza + "：commodityPrice must be greater than 0";
                }
                LDLog.m573e("LDSdkHandler -> showChargeView error ：" + zza);
                zza(activity, zza, lDPayCallback);
                return;
            }
            zzn zznVar = this.zzc;
            if (zznVar == null || !zznVar.isShowing()) {
                this.zzc = new zzn(activity, ldGamePayInfo, lDPayCallback);
            }
        }
    }

    private static void zza(Activity activity, final String str, final LDPayCallback lDPayCallback) {
        new zzl(activity).zza(zzt.zza(activity, "ld_dialog_common_notice")).zzb(str).zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.zzb.3
            @Override // com.p008ld.sdk.internal.LDCallback1
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(Boolean bool) {
                LDPayCallback lDPayCallback2;
                if (!bool.booleanValue() || (lDPayCallback2 = LDPayCallback.this) == null) {
                    return;
                }
                lDPayCallback2.onError(new LDException(str));
            }
        }).show();
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void showFloatWindow(final Activity activity, final boolean z) {
        com.p008ld.sdk.util.zza.zza(new Runnable() { // from class: com.ld.sdk.zzb.4
            @Override // java.lang.Runnable
            public void run() {
                if (z && zza.zzf().zza() != null) {
                    FlyingBall.getInstance().displayFull(activity);
                } else {
                    FlyingBall.getInstance().disappear();
                }
            }
        });
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void showUserCenterView(Activity activity, LDCallback<Boolean> lDCallback) {
        if (LDValidate.checkNullOrEmpty(activity, "context", lDCallback) && LDValidate.checkNullOrEmpty(zza.zzf().zza(), lDCallback) && !UserAccountMgr.zza().zzf()) {
            UserAccountMgr.zza().zza(0, false, null);
        }
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void showExitView(Activity activity, LDExitCallback lDExitCallback) {
        zza(activity, lDExitCallback);
    }

    private void zza(Activity activity, final LDExitCallback lDExitCallback) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        zzi zziVar = this.zzd;
        if (zziVar == null || !zziVar.isShowing()) {
            zzi zza = new zzi(activity).zza(zzt.zza(activity, "ld_do_you_want_to_exit_game_text")).zzb(zzt.zza(activity, "ld_exit_game_text")).zzc(zzt.zza(activity, "ld_continue_game_text")).zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.zzb.5
                @Override // com.p008ld.sdk.internal.LDCallback1
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(Boolean bool) {
                    LDExitCallback lDExitCallback2 = lDExitCallback;
                    if (lDExitCallback2 != null) {
                        lDExitCallback2.done(bool.booleanValue());
                    }
                }
            });
            this.zzd = zza;
            zza.show();
        }
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void enterGame(Context context, GameRoleInfo gameRoleInfo, LDCallback<Boolean> lDCallback) {
        if (LDValidate.checkNullOrEmpty(gameRoleInfo, "ldGameInfo", lDCallback) && LDValidate.checkNullOrEmpty(zza.zzf().zza(), lDCallback)) {
            zza.zzf().zza(gameRoleInfo, lDCallback);
        }
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void reportCpLogin(String str, LDCallback<Boolean> lDCallback) {
        if (LDValidate.checkNullOrEmpty(str, "userId", lDCallback)) {
            Bundle bundle = new Bundle();
            bundle.putString("userId", str);
            com.p008ld.sdk.zzd.zza.zza().zza(LDTrackEvent.CP_LOGIN_CP.getKey(), "", bundle, lDCallback);
        }
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void queryProducts(LDProductQueryParam lDProductQueryParam, LDQueryCallback<List<LDProductInfo>> lDQueryCallback) {
        zza.zzf().zza(lDProductQueryParam, lDQueryCallback);
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void jumpPage(Context context, String str, int i) {
        try {
            Intent intent = new Intent(context, (Class<?>) LoginActivity.class);
            intent.putExtra("pageId", i);
            intent.putExtra("url", str);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public void unInit(Activity activity) {
        UserAccountMgr.zza().zzb();
        if (LDSdk.getFloatViewEnable()) {
            showFloatWindow(activity, false);
            FlyingBall.getInstance().destroy();
        }
        zzb(activity);
    }

    @Override // com.p008ld.sdk.ISdkHandler
    public String getLoginInfo() {
        return zza.zzf().zzd();
    }
}
