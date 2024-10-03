package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.AccountInfo;
import com.p008ld.sdk.core.bean.SendType;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.CountdownView;
import com.p008ld.sdk.widget.MyEditText;

/* compiled from: AccountRegisterView.java */
/* loaded from: classes2.dex */
public class zzc extends zzg {
    private MyEditText zzc;
    private MyEditText zzd;
    private MyEditText zze;
    private View.OnClickListener zzf;
    private Button zzg;
    private ImageView zzh;
    private CountdownView zzi;
    private MyEditText.TextWatcherListener zzj;
    private TextView.OnEditorActionListener zzk;
    private boolean zzl;

    public zzc(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_login_register_account");
        this.zzj = new MyEditText.TextWatcherListener() { // from class: com.ld.sdk.ui.zzc.zzc.6
            @Override // com.ld.sdk.widget.MyEditText.TextWatcherListener
            public void afterTextChanged(EditText editText, String str, int i) {
                if (zzc.this.zzd.getTextLength() < 6 || zzc.this.zzc.getTextLength() < 4 || zzc.this.zze.getTextLength() < 6) {
                    zzc.this.zzg.setEnabled(false);
                } else {
                    zzc.this.zzg.setEnabled(true);
                }
                if (editText == zzc.this.zze) {
                    zzc.this.zzh.setVisibility(i == 0 ? 8 : 0);
                }
            }
        };
        this.zzk = new TextView.OnEditorActionListener() { // from class: com.ld.sdk.ui.zzc.zzc.7
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null || keyEvent.getKeyCode() != 66 || zzc.this.zza == null || zzc.this.zza.isFinishing() || keyEvent.getAction() != 0 || !zzc.this.zzg.isEnabled()) {
                    return true;
                }
                zzc zzcVar = zzc.this;
                zzcVar.zza(zzcVar.zza);
                return true;
            }
        };
        this.zzf = onClickListener;
        zzb(activity);
    }

    @Override // com.p008ld.sdk.p009ui.zzc.zzg
    public void zza() {
        super.zza();
    }

    private void zzb(Activity activity) {
        this.zzc = (MyEditText) zzt.zza(activity, "register_email_et", this.zzb);
        this.zzd = (MyEditText) zzt.zza(activity, "email_code_et", this.zzb);
        this.zze = (MyEditText) zzt.zza(activity, "password_et", this.zzb);
        CountdownView countdownView = (CountdownView) zzt.zza(activity, "reg_get_code", this.zzb);
        this.zzi = countdownView;
        countdownView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzc.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzc.this.zzb();
            }
        });
        Button button = (Button) zzt.zza(activity, "register_account_register", this.zzb);
        this.zzg = button;
        button.setEnabled(false);
        this.zzg.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzc.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzc zzcVar = zzc.this;
                zzcVar.zza(zzcVar.zza);
            }
        });
        View zza = zzt.zza(activity, "back_login", this.zzb);
        zza.setTag(5);
        zza.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzc.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (zzc.this.zzi != null) {
                    zzc.this.zzi.stop();
                }
                zzc.this.zzf.onClick(view);
            }
        });
        this.zzc.setOnEditorActionListener(this.zzk);
        this.zzd.setOnEditorActionListener(this.zzk);
        this.zze.setOnEditorActionListener(this.zzk);
        this.zzc.setTextWatcherListener(this.zzj);
        this.zzd.setTextWatcherListener(this.zzj);
        this.zze.setTextWatcherListener(this.zzj);
        ImageView imageView = (ImageView) zzt.zza(activity, "see_pwd_img1", this.zzb);
        this.zzh = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzc.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzc.this.zze.seePwdClick(zzc.this.zzh, false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb() {
        String obj = this.zzc.getText().toString();
        if (!zzi.zzc(obj)) {
            LDUtil.toast(zzt.zza(this.zza, "ld_email_incorrect_format_text"));
        } else {
            final Dialog zza = zzn.zza((Context) this.zza, false);
            zza.zzf().zza(SendType.REGISTER, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.zzc.zzc.5
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(String str, LDException lDException) {
                    if (lDException == null) {
                        zzc.this.zzi.start();
                    }
                    zza.dismiss();
                    LDUtil.toast(str);
                }
            });
        }
    }

    public void zza(final Activity activity) {
        if (this.zzl) {
            return;
        }
        this.zzl = true;
        final AccountInfo accountInfo = new AccountInfo();
        accountInfo.userName = this.zzc.getText().toString();
        accountInfo.password = this.zze.getText().toString();
        accountInfo.auth = this.zzd.getText().toString();
        final Dialog zza = zzn.zza((Context) activity, false);
        zza.zzf().zza(accountInfo, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.zzc.zzc.8
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zzc.this.zzl = false;
                zza.dismiss();
                if (lDException == null) {
                    UserAccountMgr.zza().zza(accountInfo);
                    zzc zzcVar = zzc.this;
                    zzcVar.zza(activity, zzcVar.zzf);
                    zzc.this.zzc.setText("");
                    zzc.this.zze.setText("");
                    zzc.this.zzd.setText("");
                }
                LDUtil.toast(str);
            }
        });
    }
}
