package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.UserAccountMgr;
import com.p008ld.sdk.core.bean.AccountInfo;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyEditText;

/* compiled from: AgreementAgreeView.java */
/* loaded from: classes2.dex */
public class zzd extends zzg {
    public zzd(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_agreement_agree_layout");
        zzb(activity, onClickListener);
    }

    private void zzb(Activity activity, View.OnClickListener onClickListener) {
        zza((TextView) zzt.zza(activity, "tips_desc", this.zzb), onClickListener);
        Button button = (Button) zzt.zza(activity, "confirm_btn", this.zzb);
        Button button2 = (Button) zzt.zza(activity, "cancel_btn", this.zzb);
        ImageView imageView = (ImageView) zzt.zza(activity, "iv_close", this.zzb);
        button2.setOnClickListener(onClickListener);
        button2.setTag(112);
        button.setOnClickListener(onClickListener);
        button.setTag(111);
        imageView.setOnClickListener(onClickListener);
        imageView.setTag(112);
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$1 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC19511 implements View.OnClickListener {
        ViewOnClickListenerC19511() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            zzd.zza(zzd.this);
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$2 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC19522 implements View.OnClickListener {
        ViewOnClickListenerC19522() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            zzd zzdVar = zzd.this;
            zzdVar.zza(zzdVar.zza);
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$3 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC19533 implements View.OnClickListener {
        ViewOnClickListenerC19533() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (zzd.zzb(zzd.this) != null) {
                zzd.zzb(zzd.this).stop();
            }
            zzd.zzc(zzd.this).onClick(view);
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$4 */
    /* loaded from: classes2.dex */
    class ViewOnClickListenerC19544 implements View.OnClickListener {
        ViewOnClickListenerC19544() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            zzd.zze(zzd.this).seePwdClick(zzd.zzd(zzd.this), false);
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$5 */
    /* loaded from: classes2.dex */
    class C19555 implements LDQueryCallback<String> {
        final /* synthetic */ Dialog zza;

        C19555(Dialog dialog) {
            this.zza = dialog;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(String str, LDException lDException) {
            if (lDException == null) {
                zzd.zzb(zzd.this).start();
            }
            this.zza.dismiss();
            LDUtil.toast(str);
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$6 */
    /* loaded from: classes2.dex */
    class C19566 implements MyEditText.TextWatcherListener {
        C19566() {
        }

        @Override // com.ld.sdk.widget.MyEditText.TextWatcherListener
        public void afterTextChanged(EditText editText, String str, int i) {
            if (zzd.zzf(zzd.this).getTextLength() >= 6 && zzd.zzg(zzd.this).getTextLength() >= 4 && zzd.zze(zzd.this).getTextLength() >= 6) {
                zzd.zzh(zzd.this).setEnabled(true);
            } else {
                zzd.zzh(zzd.this).setEnabled(false);
            }
            if (editText == zzd.zze(zzd.this)) {
                zzd.zzd(zzd.this).setVisibility(i == 0 ? 8 : 0);
            }
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$7 */
    /* loaded from: classes2.dex */
    class C19577 implements TextView.OnEditorActionListener {
        C19577() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (keyEvent == null || keyEvent.getKeyCode() != 66 || zzd.this.zza == null || zzd.this.zza.isFinishing() || keyEvent.getAction() != 0 || !zzd.zzh(zzd.this).isEnabled()) {
                return true;
            }
            zzd zzdVar = zzd.this;
            zzdVar.zza(zzdVar.zza);
            return true;
        }
    }

    /* compiled from: AccountRegisterView.java */
    /* renamed from: com.ld.sdk.ui.zzc.zzd$8 */
    /* loaded from: classes2.dex */
    class C19588 implements LDQueryCallback<String> {
        final /* synthetic */ Dialog zza;
        final /* synthetic */ AccountInfo zzb;
        final /* synthetic */ Activity zzc;

        C19588(Dialog dialog, AccountInfo accountInfo, Activity activity) {
            this.zza = dialog;
            this.zzb = accountInfo;
            this.zzc = activity;
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(String str, LDException lDException) {
            zzd.zza(zzd.this, false);
            this.zza.dismiss();
            if (lDException == null) {
                UserAccountMgr.zza().zza(this.zzb);
                zzd zzdVar = zzd.this;
                zzdVar.zza(this.zzc, zzd.zzc(zzdVar));
                zzd.zzg(zzd.this).setText("");
                zzd.zze(zzd.this).setText("");
                zzd.zzf(zzd.this).setText("");
            }
            LDUtil.toast(str);
        }
    }
}
