package com.p008ld.sdk.p009ui.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.p008ld.sdk.core.bean.SendType;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.CountdownView;

/* loaded from: classes2.dex */
public class AccountBindEmailView extends BaseAccountView {
    private EditText zze;
    private EditText zzf;
    private CountdownView zzg;
    private Button zzh;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "ld_bind_email_text";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return true;
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public int getTitleBgColor() {
        return Color.parseColor("#FFFFFF");
    }

    public AccountBindEmailView(Activity activity, View.OnClickListener onClickListener) {
        super(activity);
        zzb(activity, onClickListener);
    }

    private void zzb(final Activity activity, final View.OnClickListener onClickListener) {
        View zza = zzt.zza((Context) activity, "ld_bind_email_layout", (ViewGroup) this);
        this.zze = (EditText) zzt.zza(activity, "email_et", zza);
        this.zzf = (EditText) zzt.zza(activity, "email_code_et", zza);
        this.zzh = (Button) zzt.zza(activity, "bind_btn", zza);
        this.zzg = (CountdownView) zzt.zza(activity, "email_get_code_tv", zza);
        this.zzh.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountBindEmailView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountBindEmailView.this.zza(activity, onClickListener);
            }
        });
        this.zzg.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountBindEmailView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountBindEmailView.this.zzc();
            }
        });
    }

    public void zza(final Activity activity, final View.OnClickListener onClickListener) {
        final Dialog zza = zzn.zza((Context) activity, false);
        zza.zzf().zza(this.zze.getText().toString(), this.zzf.getText().toString(), new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountBindEmailView.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zza.dismiss();
                if (lDException == null) {
                    View view = new View(activity);
                    view.setTag(13000);
                    onClickListener.onClick(view);
                }
                LDUtil.toast(str);
            }
        }, "12345678");
    }

    public void zzc() {
        String obj = this.zze.getText().toString();
        if (!zzi.zzc(obj)) {
            LDUtil.toast(zzt.zza(this.zza, "ld_email_incorrect_format_text"));
            return;
        }
        this.zzg.start();
        final Dialog zza = zzn.zza(this.zza, false);
        zza.zzf().zza(SendType.BIND, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountBindEmailView.4
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zza.dismiss();
                LDUtil.toast(str);
            }
        });
    }
}
