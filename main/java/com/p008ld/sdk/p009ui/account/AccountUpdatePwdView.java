package com.p008ld.sdk.p009ui.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.LDUser;
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

/* loaded from: classes2.dex */
public class AccountUpdatePwdView extends BaseAccountView {
    private MyEditText zze;
    private MyEditText zzf;
    private MyEditText zzg;
    private MyEditText zzh;
    private CountdownView zzi;
    private Activity zzj;
    private View zzk;
    private Button zzl;
    private ImageView zzm;
    private ImageView zzn;
    private View.OnClickListener zzo;
    private MyEditText.TextWatcherListener zzp;
    private TextView.OnEditorActionListener zzq;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "ld_modify_password_text";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return true;
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
    }

    public AccountUpdatePwdView(Activity activity, View.OnClickListener onClickListener) {
        super(activity);
        this.zzp = new MyEditText.TextWatcherListener() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.7
            @Override // com.ld.sdk.widget.MyEditText.TextWatcherListener
            public void afterTextChanged(EditText editText, String str, int i) {
                AccountUpdatePwdView.this.zzl.setEnabled(AccountUpdatePwdView.this.zze.getTextLength() >= 5 && AccountUpdatePwdView.this.zzf.getTextLength() >= 6 && AccountUpdatePwdView.this.zzh.getTextLength() >= 4 && AccountUpdatePwdView.this.zzg.getTextLength() >= 6);
            }
        };
        this.zzq = new TextView.OnEditorActionListener() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.8
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null || keyEvent.getKeyCode() != 66 || keyEvent.getAction() != 0) {
                    return true;
                }
                AccountUpdatePwdView.this.zzl.isEnabled();
                return true;
            }
        };
        this.zzj = activity;
        this.zzo = onClickListener;
        zza(activity);
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public int getTitleBgColor() {
        return Color.parseColor("#FFFFFF");
    }

    public void zza(Context context) {
        View zza = zzt.zza(context, "ld_account_update_pwd_layout", (ViewGroup) this);
        this.zze = (MyEditText) zzt.zza(context, "mailbox_et", zza);
        this.zzf = (MyEditText) zzt.zza(context, "input_new_password", zza);
        this.zzg = (MyEditText) zzt.zza(context, "once_more_input_new_password", zza);
        this.zzh = (MyEditText) zzt.zza(context, "mailbox_code_et", zza);
        this.zzi = (CountdownView) zzt.zza(context, "get_code_tv", zza);
        this.zzl = (Button) zzt.zza(context, "forget_phone_password", zza);
        this.zzi.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountUpdatePwdView.this.getEmailCode();
            }
        });
        this.zzl.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountUpdatePwdView.this.zzc();
            }
        });
        View view = new View(context);
        this.zzk = view;
        view.setTag(11);
        this.zzk.setOnClickListener(this.zzo);
        this.zze.setOnEditorActionListener(this.zzq);
        this.zzf.setOnEditorActionListener(this.zzq);
        this.zzh.setOnEditorActionListener(this.zzq);
        this.zzg.setOnEditorActionListener(this.zzq);
        this.zze.setTextWatcherListener(this.zzp);
        this.zzf.setTextWatcherListener(this.zzp);
        this.zzh.setTextWatcherListener(this.zzp);
        this.zzg.setTextWatcherListener(this.zzp);
        this.zzm = (ImageView) zzt.zza(context, "see_pwd_img", zza);
        this.zzn = (ImageView) zzt.zza(context, "see_pwd_img1", zza);
        this.zzm.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AccountUpdatePwdView.this.zzf.seePwdClick(AccountUpdatePwdView.this.zzm, false);
            }
        });
        this.zzn.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AccountUpdatePwdView.this.zzg.seePwdClick(AccountUpdatePwdView.this.zzn, false);
            }
        });
        LDUser zza2 = zza.zzf().zza();
        if (zza2 == null || zzi.zza((CharSequence) zza2.getEmail())) {
            return;
        }
        this.zze.setText(zza2.getEmail());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzc() {
        String obj = this.zzf.getText().toString();
        if (!this.zzg.getText().toString().equals(obj)) {
            LDUtil.toast(zzt.zza(this.zzj, "ld_password_inconsistency_text"));
            return;
        }
        String obj2 = this.zze.getText().toString();
        String obj3 = this.zzh.getText().toString();
        final Dialog zza = zzn.zza((Context) this.zzj, false);
        zza.zzf().zza(obj2, obj3, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.5
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zza.dismiss();
                if (lDException == null) {
                    AccountUpdatePwdView accountUpdatePwdView = AccountUpdatePwdView.this;
                    accountUpdatePwdView.zza(accountUpdatePwdView.zzo);
                    LDUtil.toast(zzt.zza(AccountUpdatePwdView.this.zzj, "ld_modify_password_success_text"));
                    return;
                }
                LDUtil.toast(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getEmailCode() {
        String obj = this.zze.getText().toString();
        if (!zzi.zzc(obj)) {
            LDUtil.toast(zzt.zza(this.zzj, "ld_email_incorrect_format_text"));
            return;
        }
        this.zzh.requestFocus();
        this.zzi.start();
        final Dialog zza = zzn.zza((Context) this.zzj, false);
        zza.zzf().zza(SendType.UPDATE_PASSWORD, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountUpdatePwdView.6
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zza.dismiss();
                LDUtil.toast(str);
            }
        });
    }
}
