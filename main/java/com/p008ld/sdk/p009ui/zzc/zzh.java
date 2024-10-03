package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.SendType;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.CountdownView;
import com.p008ld.sdk.widget.MyEditText;

/* compiled from: BindEmailLoginView.java */
/* loaded from: classes2.dex */
public class zzh extends zzg implements View.OnClickListener {
    private MyEditText zzc;
    private MyEditText zzd;
    private MyEditText zze;
    private CountdownView zzf;
    private Button zzg;
    private ImageView zzh;
    private MyEditText.TextWatcherListener zzi;
    private TextView.OnEditorActionListener zzj;

    public zzh(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_login_bind_email");
        this.zzi = new MyEditText.TextWatcherListener() { // from class: com.ld.sdk.ui.zzc.zzh.1
            @Override // com.ld.sdk.widget.MyEditText.TextWatcherListener
            public void afterTextChanged(EditText editText, String str, int i) {
                zzh.this.zzg.setEnabled(zzh.this.zzc.getTextLength() >= 5 && zzh.this.zzd.getTextLength() >= 6 && zzh.this.zze.getTextLength() >= 4);
                zzh.this.zzh.setVisibility(i == 0 ? 8 : 0);
            }
        };
        this.zzj = new TextView.OnEditorActionListener() { // from class: com.ld.sdk.ui.zzc.zzh.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null || keyEvent.getKeyCode() != 66 || keyEvent.getAction() != 0 || !zzh.this.zzg.isEnabled()) {
                    return true;
                }
                zzh.this.zzc();
                return true;
            }
        };
        zzb(activity, onClickListener);
    }

    private void zzb(Activity activity, View.OnClickListener onClickListener) {
        this.zzc = (MyEditText) zzt.zza(activity, "mailbox_et", this.zzb);
        this.zzd = (MyEditText) zzt.zza(activity, "edit_pwd", this.zzb);
        this.zze = (MyEditText) zzt.zza(activity, "mailbox_code_et", this.zzb);
        this.zzf = (CountdownView) zzt.zza(activity, "get_code_tv", this.zzb);
        this.zzg = (Button) zzt.zza(activity, "btn_bind", this.zzb);
        this.zzh = (ImageView) zzt.zza(activity, "see_pwd_img", this.zzb);
        View zza2 = zzt.zza(activity, "iv_close", this.zzb);
        this.zzc.setOnEditorActionListener(this.zzj);
        this.zzd.setOnEditorActionListener(this.zzj);
        this.zze.setOnEditorActionListener(this.zzj);
        this.zzc.setTextWatcherListener(this.zzi);
        this.zzd.setTextWatcherListener(this.zzi);
        this.zze.setTextWatcherListener(this.zzi);
        this.zzg.setOnClickListener(this);
        zza2.setOnClickListener(this);
        this.zzh.setOnClickListener(this);
        this.zzf.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == zza("iv_close")) {
            this.zza.finish();
            return;
        }
        if (id == zza("see_pwd_img")) {
            this.zzd.seePwdClick(this.zzh, false);
        } else if (id == zza("get_code_tv")) {
            zzb();
        } else if (id == zza("btn_bind")) {
            zzc();
        }
    }

    public void zzb() {
        String obj = this.zzc.getText().toString();
        if (!zzi.zzc(obj)) {
            LDUtil.toast(zzt.zza(this.zza, "ld_email_incorrect_format_text"));
        } else {
            final Dialog zza2 = zzn.zza((Context) this.zza, false);
            com.p008ld.sdk.core.zza.zza.zzf().zza(SendType.BIND, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.zzc.zzh.3
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(String str, LDException lDException) {
                    if (lDException == null) {
                        zzh.this.zzf.start();
                    }
                    zza2.dismiss();
                    LDUtil.toast(str);
                }
            });
        }
    }

    public void zzc() {
        String obj = this.zzc.getText().toString();
        String obj2 = this.zzd.getText().toString();
        String obj3 = this.zze.getText().toString();
        final Dialog zza2 = zzn.zza((Context) this.zza, false);
        com.p008ld.sdk.core.zza.zza.zzf().zza(obj, obj3, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.zzc.zzh.4
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zza2.dismiss();
                LDUtil.toast(str);
                if (lDException == null) {
                    zzh.this.zza.finish();
                }
            }
        }, obj2);
    }

    /* compiled from: BaseStackView.java */
    /* loaded from: classes2.dex */
    static class zza extends UnderlineSpan {
        zza() {
        }

        @Override // android.text.style.UnderlineSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(Color.parseColor("#499DFF"));
            textPaint.setUnderlineText(false);
        }
    }
}
