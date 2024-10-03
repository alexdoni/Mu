package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import com.p008ld.sdk.core.bean.SendType;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.LoginActivity;
import com.p008ld.sdk.p009ui.zza.zzh;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.CountdownView;
import com.p008ld.sdk.widget.MyEditText;
import com.p008ld.sdk.zzb.zzc;
import java.util.List;

/* compiled from: ForgetPasswordView.java */
/* loaded from: classes2.dex */
public class zzk extends zzh {
    private MyEditText zzc;
    private MyEditText zzd;
    private MyEditText zze;
    private MyEditText zzf;
    private CountdownView zzg;
    private View zzh;
    private View zzi;
    private Button zzj;
    private boolean zzk;
    private ImageView zzl;
    private ImageView zzm;
    private ImageView zzn;
    private View zzo;
    private ListView zzp;
    private MyEditText.TextWatcherListener zzq;
    private TextView.OnEditorActionListener zzr;

    public zzk(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_login_forget_password");
        this.zzq = new MyEditText.TextWatcherListener() { // from class: com.ld.sdk.ui.zzc.zzk.12
            @Override // com.ld.sdk.widget.MyEditText.TextWatcherListener
            public void afterTextChanged(EditText editText, String str, int i) {
                if (zzk.this.zzo.getVisibility() == 0) {
                    zzk.this.zzo.setVisibility(8);
                    zzk.this.zza(true);
                }
                zzk.this.zzj.setEnabled(zzk.this.zzc.getTextLength() >= 5 && zzk.this.zzd.getTextLength() >= 6 && zzk.this.zzf.getTextLength() >= 4 && zzk.this.zze.getTextLength() >= 6);
                if (editText == zzk.this.zzd) {
                    zzk.this.zzl.setVisibility(i == 0 ? 8 : 0);
                }
                if (editText == zzk.this.zze) {
                    zzk.this.zzm.setVisibility(i != 0 ? 0 : 8);
                }
            }
        };
        this.zzr = new TextView.OnEditorActionListener() { // from class: com.ld.sdk.ui.zzc.zzk.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null || keyEvent.getKeyCode() != 66 || keyEvent.getAction() != 0 || !zzk.this.zzj.isEnabled()) {
                    return true;
                }
                zzk.this.zzb();
                return true;
            }
        };
        zzb(activity, onClickListener);
    }

    private void zzb(final Activity activity, final View.OnClickListener onClickListener) {
        this.zzc = (MyEditText) zzt.zza(activity, "mailbox_et", this.zzb);
        this.zzd = (MyEditText) zzt.zza(activity, "input_new_password", this.zzb);
        this.zze = (MyEditText) zzt.zza(activity, "once_more_input_new_password", this.zzb);
        this.zzf = (MyEditText) zzt.zza(activity, "mailbox_code_et", this.zzb);
        this.zzg = (CountdownView) zzt.zza(activity, "get_code_tv", this.zzb);
        Button button = (Button) zzt.zza(activity, "forget_phone_password", this.zzb);
        this.zzj = button;
        button.setTag(30);
        this.zzj.setOnClickListener(onClickListener);
        View zza = zzt.zza(activity, "back_login", this.zzb);
        this.zzh = zza;
        zza.setTag(5);
        this.zzh.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (zzk.this.zzg != null) {
                    zzk.this.zzg.stop();
                }
                onClickListener.onClick(view);
            }
        });
        View view = new View(activity);
        this.zzi = view;
        view.setTag(5);
        this.zzi.setOnClickListener(onClickListener);
        this.zzc.setOnEditorActionListener(this.zzr);
        this.zzd.setOnEditorActionListener(this.zzr);
        this.zzf.setOnEditorActionListener(this.zzr);
        this.zze.setOnEditorActionListener(this.zzr);
        this.zzc.setTextWatcherListener(this.zzq);
        this.zzd.setTextWatcherListener(this.zzq);
        this.zzf.setTextWatcherListener(this.zzq);
        this.zze.setTextWatcherListener(this.zzq);
        this.zzl = (ImageView) zzt.zza(activity, "see_pwd_img", this.zzb);
        this.zzm = (ImageView) zzt.zza(activity, "see_pwd_img1", this.zzb);
        this.zzl.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzk.this.zzd.seePwdClick(zzk.this.zzl, false);
            }
        });
        this.zzm.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzk.this.zze.seePwdClick(zzk.this.zzm, false);
            }
        });
        this.zzg.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzk.this.zza(activity);
            }
        });
        this.zzp = (ListView) zzt.zza(activity, "account_list_view", this.zzb);
        this.zzn = (ImageView) zzt.zza(activity, "account_select_img", this.zzb);
        this.zzo = zzt.zza(activity, "account_list_layout", this.zzb);
        zzt.zza(activity, "forget_password_layout", this.zzb).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                zzk.this.zza(true);
            }
        });
        zzc();
    }

    private void zzc() {
        final List<PublicUserInfo> zzb = zzc.zzc().zzb();
        if (zzb != null && !zzb.isEmpty()) {
            this.zzn.setVisibility(0);
            this.zzp.setAdapter((ListAdapter) new zzh(this.zza, zzb, true));
            this.zzp.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.9
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    zzk.this.zzc.setText(((PublicUserInfo) zzb.get(i)).username);
                }
            });
            this.zzo.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    zzk.this.zza(false);
                }
            });
            this.zzn.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzk.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    zzk.this.zza(false);
                }
            });
            return;
        }
        this.zzn.setVisibility(8);
    }

    public void zza(boolean z) {
        if (this.zzo.getVisibility() == 8 && !z) {
            this.zzo.setVisibility(0);
            this.zzn.setImageResource(zzt.zzd(this.zza, "ld_login_register_arrow_up"));
        } else {
            this.zzn.setImageResource(zzt.zzd(this.zza, "ld_login_register_arrow_down"));
            this.zzo.setVisibility(8);
        }
    }

    public void zza(Activity activity, boolean z) {
        this.zzk = z;
        if (z) {
            this.zzh.setTag(40);
            this.zzi.setTag(40);
        } else {
            this.zzh.setTag(5);
            this.zzi.setTag(5);
        }
        List<PublicUserInfo> zzb = zzc.zzc().zzb();
        if (zzb == null || zzb.isEmpty()) {
            return;
        }
        PublicUserInfo publicUserInfo = zzb.get(0);
        this.zzc.setText(publicUserInfo.username != null ? publicUserInfo.username : "");
    }

    public void zza(Activity activity) {
        String obj = this.zzc.getText().toString();
        if (!zzi.zzc(obj)) {
            LDUtil.toast(zzt.zza(activity, "ld_email_incorrect_format_text"));
        } else {
            final Dialog zza = zzn.zza((Context) activity, false);
            zza.zzf().zza(SendType.UPDATE_PASSWORD, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.zzc.zzk.3
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(String str, LDException lDException) {
                    if (lDException == null) {
                        zzk.this.zzg.start();
                    }
                    zza.dismiss();
                    LDUtil.toast(str);
                }
            });
        }
    }

    @Override // com.p008ld.sdk.p009ui.zzc.zzh
    public void zzb() {
        String obj = this.zzd.getText().toString();
        if (!this.zze.getText().toString().equals(obj)) {
            LDUtil.toast(zzt.zza(this.zza, "ld_password_inconsistency_text"));
            return;
        }
        String obj2 = this.zzc.getText().toString();
        String obj3 = this.zzf.getText().toString();
        final Dialog zza = zzn.zza((Context) this.zza, false);
        zza.zzf().zza(obj2, obj3, obj, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.zzc.zzk.4
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(String str, LDException lDException) {
                zza.dismiss();
                LDUtil.toast(str);
                if (lDException == null && zzk.this.zzi != null) {
                    zzk.this.zzi.performClick();
                }
                if (zzk.this.zzk) {
                    LoginActivity.resultToActivity(zzk.this.zza, lDException == null ? 1000 : lDException.getCode(), str);
                }
            }
        });
    }
}
