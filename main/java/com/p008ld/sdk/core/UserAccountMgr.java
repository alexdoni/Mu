package com.p008ld.sdk.core;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.p008ld.sdk.LDSdkManager;
import com.p008ld.sdk.core.bean.AccountInfo;
import com.p008ld.sdk.core.bean.GiftBagInfo;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDLoginCallback;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.floatview.FlyingBall;
import com.p008ld.sdk.p009ui.zza;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.zzi;

/* loaded from: classes2.dex */
public class UserAccountMgr {
    private static UserAccountMgr zza;
    private Activity zzb;
    private OutLoginReceiver zzc;
    private LDLoginCallback zzd;
    private boolean zze;
    private zza zzf;
    private AccountInfo zzg;

    public static synchronized UserAccountMgr zza() {
        UserAccountMgr userAccountMgr;
        synchronized (UserAccountMgr.class) {
            if (zza == null) {
                zza = new UserAccountMgr();
            }
            userAccountMgr = zza;
        }
        return userAccountMgr;
    }

    public void zza(Activity activity) {
        this.zzb = activity;
        zza((Context) activity);
    }

    private void zza(Context context) {
        this.zzc = new OutLoginReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ld.outlogin.action");
        if (Build.VERSION.SDK_INT >= 33) {
            context.registerReceiver(this.zzc, intentFilter, 4);
        } else {
            context.registerReceiver(this.zzc, intentFilter);
        }
    }

    public void zzb() {
        Activity activity;
        if (this.zzc == null || (activity = this.zzb) == null || activity.isFinishing()) {
            return;
        }
        this.zzb.unregisterReceiver(this.zzc);
    }

    /* loaded from: classes2.dex */
    public class OutLoginReceiver extends BroadcastReceiver {
        public OutLoginReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            UserAccountMgr.this.zzc();
        }
    }

    public void zzc() {
        if (this.zzd != null) {
            LDLog.m573e("UserAccountMgr -> receiver OutLoginReceiver");
            zzg();
            com.p008ld.sdk.util.zza.zza(new Runnable() { // from class: com.ld.sdk.core.UserAccountMgr.1
                @Override // java.lang.Runnable
                public void run() {
                    FlyingBall.getInstance().disappear();
                }
            }, 300L);
            this.zze = false;
            this.zzd.onLogout();
        }
    }

    public void zza(LDLoginCallback lDLoginCallback) {
        this.zzd = lDLoginCallback;
    }

    public void zza(boolean z, String str, String str2, String str3) {
        LDLoginCallback lDLoginCallback = this.zzd;
        if (lDLoginCallback != null) {
            if (z) {
                com.p008ld.sdk.util.zza.zza(new Runnable() { // from class: com.ld.sdk.core.UserAccountMgr.2
                    @Override // java.lang.Runnable
                    public void run() {
                        UserAccountMgr.this.zzi();
                    }

                    /* renamed from: com.ld.sdk.core.UserAccountMgr$2$1, reason: invalid class name */
                    /* loaded from: classes2.dex */
                    class AnonymousClass1 implements DialogInterface.OnDismissListener {
                        AnonymousClass1() {
                        }

                        @Override // android.content.DialogInterface.OnDismissListener
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (RunnableC18022.this.zzb) {
                                LDSdkManager.getInstance().showFloatView(RunnableC18022.this.zzd.zzf);
                            }
                            if (RunnableC18022.this.zzc != null) {
                                RunnableC18022.this.zzc.done(true);
                            }
                        }
                    }
                }, 2000L);
                this.zzd.onSuccess(str, str2);
            } else {
                lDLoginCallback.onError(str3);
            }
        }
    }

    public boolean zzd() {
        return this.zze;
    }

    public void zze() {
        this.zze = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzi() {
        com.p008ld.sdk.core.zza.zza.zzf().zzd(new LDQueryCallback<GiftBagInfo>() { // from class: com.ld.sdk.core.UserAccountMgr.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(GiftBagInfo giftBagInfo, LDException lDException) {
                if (giftBagInfo != null) {
                    UserAccountMgr.this.zze = zzi.zza(giftBagInfo.getNotClaimed().getList(), "gameGiftSet") > 0;
                } else if (lDException != null) {
                    LDLog.m573e("UserAccountMgr -> checkNewGiftPackage error ：" + lDException);
                }
            }
        });
    }

    public boolean zzf() {
        zza zzaVar = this.zzf;
        return zzaVar != null && zzaVar.isShowing();
    }

    public void zza(final int i, final boolean z, final LDCallback1 lDCallback1) {
        com.p008ld.sdk.util.zza.zza(new Runnable() { // from class: com.ld.sdk.core.UserAccountMgr.4
            @Override // java.lang.Runnable
            public void run() {
                if (UserAccountMgr.this.zzf()) {
                    UserAccountMgr.this.zzf.zza(i);
                } else {
                    UserAccountMgr.this.zzf = new zza(UserAccountMgr.this.zzb, i);
                }
                if (z) {
                    LDSdkManager.getInstance().hideFlowView(UserAccountMgr.this.zzb);
                }
                UserAccountMgr.this.zzf.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.core.UserAccountMgr.4.1
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (z) {
                            LDSdkManager.getInstance().showFloatView(UserAccountMgr.this.zzb);
                        }
                        if (lDCallback1 != null) {
                            lDCallback1.done(true);
                        }
                    }
                });
            }
        });
    }

    public void zzg() {
        zza zzaVar = this.zzf;
        if (zzaVar == null || !zzaVar.isShowing()) {
            return;
        }
        this.zzf.dismiss();
    }

    public void zza(AccountInfo accountInfo) {
        this.zzg = accountInfo;
    }

    public AccountInfo zzh() {
        return this.zzg;
    }
}
