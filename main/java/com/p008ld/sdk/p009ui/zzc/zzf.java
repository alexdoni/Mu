package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.AccountInfo;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.zzb.zzc;
import java.util.List;

/* compiled from: AutoLoginView.java */
/* loaded from: classes2.dex */
public class zzf extends zzg {
    private View.OnClickListener zzc;
    private boolean zzd;
    private LDUser zze;
    private Runnable zzf;
    private TextView zzg;
    private AnimationDrawable zzh;

    public zzf(Activity activity, boolean z, View.OnClickListener onClickListener) {
        super(activity, "ld_login_auto");
        this.zzc = onClickListener;
        zzb(activity, onClickListener);
        zza(this.zzd, z);
    }

    public void zza(boolean z, final boolean z2) {
        this.zzd = z;
        AccountInfo zzh = UserAccountMgr.zza().zzh();
        if (z2 && zzh != null) {
            this.zzg.setText(zzh.userName);
        } else {
            LDUser zza = zza.zzf().zza();
            this.zze = zza;
            if (zza == null) {
                zzb();
                return;
            } else if (zza.getLoginType().equals(LoginMode.USERNAME.getValue()) && !zzi.zza((CharSequence) this.zze.getEmail())) {
                this.zzg.setText(this.zze.getEmail());
            } else {
                this.zzg.setText(this.zze.getNickname());
            }
        }
        this.zzf = new Runnable() { // from class: com.ld.sdk.ui.zzc.zzf.1
            @Override // java.lang.Runnable
            public void run() {
                zzf.this.zza(z2);
            }
        };
        this.zzb.postDelayed(this.zzf, 1000L);
    }

    private void zzb(Activity activity, final View.OnClickListener onClickListener) {
        this.zzg = (TextView) zzt.zza(activity, "login_account_username", this.zzb);
        AnimationDrawable animationDrawable = (AnimationDrawable) ((ImageView) zzt.zza(activity, "kkk_loading_img", this.zzb)).getDrawable();
        this.zzh = animationDrawable;
        animationDrawable.start();
        TextView textView = (TextView) zzt.zza(activity, "login_change_account", this.zzb);
        textView.setTag(1);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzf.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzf.this.zzc();
                onClickListener.onClick(view);
            }
        });
        List<PublicUserInfo> zza = zzc.zzc().zza();
        textView.setVisibility(zza != null && zza.size() >= 2 ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(boolean z) {
        if (this.zzd) {
            return;
        }
        LoginInfo loginInfo = new LoginInfo();
        AccountInfo zzh = UserAccountMgr.zza().zzh();
        if (z && zzh != null) {
            this.zzg.setText(zzh.userName);
            loginInfo.loginMode = LoginMode.USERNAME;
            loginInfo.username = zzh.userName;
            loginInfo.password = zzh.password;
            UserAccountMgr.zza().zza((AccountInfo) null);
        } else {
            loginInfo.loginMode = LoginMode.AUTO;
            loginInfo.uid = this.zze.getUid();
            loginInfo.token = this.zze.getToken();
        }
        zza.zzf().zza(loginInfo, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.ui.zzc.zzf.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(LDUser lDUser, LDException lDException) {
                zzf.this.zzh.stop();
                if (lDException == null) {
                    if (zzf.this.zza(true, lDUser.getSpaceUserId(), lDUser.getCpToken(), "")) {
                        return;
                    }
                    zzf.this.zzb();
                } else {
                    zzf.this.zza(false, "", "", lDException.toString());
                    View view = new View(zzf.this.zza);
                    view.setTag(11);
                    view.setOnClickListener(zzf.this.zzc);
                    view.performClick();
                }
            }
        });
    }

    public void zzb() {
        this.zzd = true;
        View view = new View(this.zza);
        view.setTag(11);
        view.setOnClickListener(this.zzc);
        view.performClick();
    }

    public void zzc() {
        this.zzd = true;
        this.zzb.removeCallbacks(this.zzf);
    }
}
