package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.ConfigBean;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zzb.zzc;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;

/* compiled from: AccountLoginView.java */
/* loaded from: classes2.dex */
public class zzb extends zzg implements View.OnClickListener {
    private View.OnClickListener zzc;
    private View zzd;
    private ImageView zze;
    private CheckBox zzf;
    private LoginMode zzg;
    private LinearLayout zzh;
    private ImageView zzi;
    private ImageView zzj;
    private ImageView zzk;
    private boolean zzl;
    private Dialog zzm;

    public zzb(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_login_account");
        this.zzc = onClickListener;
        zzb(activity, onClickListener);
    }

    @Override // com.p008ld.sdk.p009ui.zzc.zzg
    public void zza() {
        super.zza();
        this.zzf.setChecked(true);
        zzb();
    }

    private void zzb() {
        if (this.zzg == LoginMode.GOOGLE) {
            zza(this.zza, this.zzc, 2);
            return;
        }
        if (this.zzg == LoginMode.FACEBOOK) {
            zza(this.zza, this.zzc, 3);
        } else if (this.zzg == LoginMode.LINE) {
            zza(this.zza, this.zzc, 6);
        } else if (this.zzg == LoginMode.USERNAME) {
            zza(this.zza, this.zzc, 5);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == zza("iv_contact_service")) {
            zzd();
            return;
        }
        if (id == zza("google_login_btn")) {
            this.zzg = LoginMode.GOOGLE;
        } else if (id == zza("facebook_login_btn")) {
            this.zzg = LoginMode.FACEBOOK;
        } else if (id == zza("line_login_btn")) {
            this.zzg = LoginMode.LINE;
        } else if (id == zza("email_login_btn")) {
            this.zzg = LoginMode.USERNAME;
        }
        zzc();
    }

    private void zzc() {
        if (this.zzf.isChecked()) {
            zzb();
        } else {
            zza(this.zza, this.zzc, 110);
        }
    }

    private void zzd() {
        new zzc(this.zza, false).showAsDropDown(this.zze);
    }

    private void zzb(Activity activity, View.OnClickListener onClickListener) {
        ImageView imageView = (ImageView) zzt.zza(activity, "iv_contact_service", this.zzb);
        this.zze = imageView;
        imageView.setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) zzt.zza(activity, "google_login_btn", this.zzb);
        this.zzh = linearLayout;
        linearLayout.setOnClickListener(this);
        ImageView imageView2 = (ImageView) zzt.zza(activity, "facebook_login_btn", this.zzb);
        this.zzk = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) zzt.zza(activity, "line_login_btn", this.zzb);
        this.zzi = imageView3;
        imageView3.setOnClickListener(this);
        ImageView imageView4 = (ImageView) zzt.zza(activity, "email_login_btn", this.zzb);
        this.zzj = imageView4;
        imageView4.setOnClickListener(this);
        View zza = zzt.zza(activity, "agreement_layout", this.zzb);
        this.zzd = zza;
        zza.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzb.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzb.this.zzf.setChecked(!zzb.this.zzf.isChecked());
            }
        });
        CheckBox checkBox = (CheckBox) zzt.zza(activity, "register_accept_agreement", this.zzb);
        this.zzf = checkBox;
        checkBox.setChecked(true);
        this.zzf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ld.sdk.ui.zzc.zzb.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            }
        });
        zza((TextView) zzt.zza(activity, "read_and_agree", this.zzb), onClickListener);
        ImageView imageView5 = (ImageView) zzt.zza(activity, "close_login", this.zzb);
        if (zzi.zzb(this.zza)) {
            this.zze.setVisibility(8);
            imageView5.setVisibility(0);
            imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzb.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    zzb.this.zza.finish();
                }
            });
        } else {
            this.zze.setVisibility(0);
            imageView5.setVisibility(8);
        }
        ConfigBean zze = zza.zzf().zze();
        if (zze == null || zze.allowLoginMethod == null) {
            return;
        }
        if (!zze.allowLoginMethod.contains("FACEBOOK")) {
            this.zzk.setVisibility(8);
        }
        if (!zze.allowLoginMethod.contains("LINE")) {
            this.zzi.setVisibility(8);
        }
        if (zze.allowLoginMethod.contains("EMAIL") || zze.allowLoginMethod.contains("USERNAME")) {
            return;
        }
        this.zzj.setVisibility(8);
    }

    public void zza(LoginMode loginMode, String str, String str2) {
        if (this.zzl) {
            return;
        }
        LoginInfo loginInfo = new LoginInfo();
        this.zzl = true;
        this.zzm = zzn.zza((Context) this.zza, false);
        loginInfo.loginMode = loginMode;
        loginInfo.auth = str;
        loginInfo.rememberPwd = true;
        LDLog.m573e("thirdLoginByToken-> type = " + loginMode.getValue() + ",token = " + str + ", userName = " + str2);
        zza.zzf().zza(loginInfo, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.ui.zzc.zzb.4
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(LDUser lDUser, LDException lDException) {
                if (zzb.this.zzm != null && zzb.this.zzm.isShowing()) {
                    zzb.this.zzm.dismiss();
                }
                zzb zzbVar = zzb.this;
                zzbVar.zzb(lDUser, lDException, zzbVar.zzc);
            }
        });
    }

    public void zza(LDUser lDUser, final LDCallback1<Boolean> lDCallback1) {
        if (this.zzl) {
            return;
        }
        LoginInfo loginInfo = new LoginInfo();
        this.zzl = true;
        loginInfo.loginMode = LoginMode.valueOf(lDUser.getLoginType());
        loginInfo.rememberPwd = true;
        zza.zzf().zza(loginInfo, lDUser, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.ui.zzc.zzb.5
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(LDUser lDUser2, LDException lDException) {
                LDCallback1 lDCallback12 = lDCallback1;
                if (lDCallback12 != null) {
                    lDCallback12.done(true);
                }
                zzb zzbVar = zzb.this;
                zzbVar.zzb(lDUser2, lDException, zzbVar.zzc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(LDUser lDUser, LDException lDException, View.OnClickListener onClickListener) {
        this.zzl = false;
        zza(lDUser, lDException, onClickListener);
    }
}
