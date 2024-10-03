package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.method.DigitsKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.muglobal.p011ld.BuildConfig;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zzj;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.MyEditText;
import com.p008ld.sdk.zzb.zzc;
import java.util.List;

/* compiled from: EmailLoginView.java */
/* loaded from: classes2.dex */
public class zzi extends zzg implements View.OnClickListener {
    private MyEditText zzc;
    private MyEditText zzd;
    private TextView zze;
    private Button zzf;
    private RelativeLayout zzg;
    private RelativeLayout zzh;
    private CheckBox zzi;
    private ImageView zzj;
    private ListView zzk;
    private View zzl;
    private TextView zzm;
    private boolean zzn;
    private boolean zzo;
    private ImageView zzp;
    private MyEditText.TextWatcherListener zzq;
    private TextView.OnEditorActionListener zzr;
    private boolean zzs;
    private Dialog zzt;
    private View.OnFocusChangeListener zzu;

    public zzi(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_login_email");
        this.zzq = new MyEditText.TextWatcherListener() { // from class: com.ld.sdk.ui.zzc.zzi.6
            @Override // com.ld.sdk.widget.MyEditText.TextWatcherListener
            public void afterTextChanged(EditText editText, String str, int i) {
                if (editText == zzi.this.zzc) {
                    if (zzi.this.zzo) {
                        return;
                    }
                    if (zzi.this.zzl.getVisibility() == 0) {
                        zzi.this.zzl.setVisibility(8);
                        zzi.this.zza(true);
                    }
                    if (str.equals("")) {
                        zzi.this.zzd.setText("");
                    }
                    zzi.this.zzf.setEnabled(i >= 4 && zzi.this.zzd.getText().toString().length() >= 6);
                    if (zzi.this.zzn) {
                        zzi.this.zzb();
                        return;
                    }
                    return;
                }
                if (editText != zzi.this.zzd || zzi.this.zzo) {
                    return;
                }
                if (zzi.this.zzn) {
                    zzi.this.zzn = false;
                    zzi.this.zzd.setText("");
                } else {
                    zzi.this.zzp.setVisibility(i != 0 ? 0 : 8);
                    zzi.this.zzf.setEnabled(i >= 6 && zzi.this.zzc.getText().toString().length() >= 6);
                }
            }
        };
        this.zzr = new TextView.OnEditorActionListener() { // from class: com.ld.sdk.ui.zzc.zzi.7
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent == null || keyEvent.getKeyCode() != 66 || zzi.this.zza == null || zzi.this.zza.isFinishing() || keyEvent.getAction() != 0) {
                    return false;
                }
                zzi zziVar = zzi.this;
                zziVar.zza(zziVar.zza);
                return false;
            }
        };
        this.zzu = new View.OnFocusChangeListener() { // from class: com.ld.sdk.ui.zzc.zzi.9
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    zzi.this.zzd();
                }
            }
        };
        zzb(activity, onClickListener);
    }

    @Override // com.p008ld.sdk.p009ui.zzc.zzg
    public void zza() {
        super.zza();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == zza("login_account_select_chick")) {
            zza(false);
            return;
        }
        if (id == zza("see_pwd_img")) {
            this.zzd.seePwdClick(this.zzp, false);
            zze();
        } else if (id == zza("rl_remember")) {
            this.zzi.setChecked(!r3.isChecked());
        }
    }

    private void zzb(final Activity activity, View.OnClickListener onClickListener) {
        this.zzd = (MyEditText) zzt.zza(activity, "login_account_password", this.zzb);
        MyEditText myEditText = (MyEditText) zzt.zza(activity, "login_account_username", this.zzb);
        this.zzc = myEditText;
        myEditText.setOnFocusChangeListener(this.zzu);
        TextView textView = (TextView) zzt.zza(activity, "login_account_forget_password", this.zzb);
        this.zze = textView;
        textView.setTag(4);
        this.zze.setOnClickListener(onClickListener);
        Button button = (Button) zzt.zza(activity, "login_account_login", this.zzb);
        this.zzf = button;
        button.setEnabled(false);
        this.zzm = (TextView) zzt.zza(activity, "register_view", this.zzb);
        this.zzk = (ListView) zzt.zza(activity, "account_list_view", this.zzb);
        this.zzl = zzt.zza(activity, "account_list_layout", this.zzb);
        this.zzg = (RelativeLayout) zzt.zza(activity, "login_account_select_chick", this.zzb);
        this.zzj = (ImageView) zzt.zza(activity, "login_account_select", this.zzb);
        this.zzg.setOnClickListener(this);
        ImageView imageView = (ImageView) zzt.zza(activity, "see_pwd_img", this.zzb);
        this.zzp = imageView;
        imageView.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) zzt.zza(activity, "rl_remember", this.zzb);
        this.zzh = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.zzi = (CheckBox) zzt.zza(activity, "cb_remember", this.zzb);
        zza(this.zze);
        zza(this.zzm);
        this.zzf.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzi.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzi.this.zza(activity);
            }
        });
        this.zzm.setTag(105);
        this.zzm.setOnClickListener(onClickListener);
        this.zzd.setOnEditorActionListener(this.zzr);
        this.zzc.setOnEditorActionListener(this.zzr);
        zzc();
        final List<PublicUserInfo> zzb = zzc.zzc().zzb();
        if (zzb != null && !zzb.isEmpty()) {
            PublicUserInfo publicUserInfo = zzb.get(0);
            this.zzc.setText(publicUserInfo.username);
            this.zzd.setText(publicUserInfo.password);
            if (!publicUserInfo.password.isEmpty()) {
                this.zzi.setChecked(true);
            }
            this.zzp.setVisibility(publicUserInfo.password.isEmpty() ? 8 : 0);
            this.zzf.setEnabled(true);
            zzd();
            this.zzg.setVisibility(0);
            zzj zzjVar = new zzj(this.zza, zzb, false);
            this.zzk.setAdapter((ListAdapter) zzjVar);
            this.zzk.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzc.zzi.2
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    PublicUserInfo publicUserInfo2 = (PublicUserInfo) zzb.get(i);
                    zzi.this.zza(publicUserInfo2.username, publicUserInfo2.password);
                }
            });
            this.zzl.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzi.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    zzi.this.zza(false);
                }
            });
            zzjVar.zza(new zzj.zza() { // from class: com.ld.sdk.ui.zzc.zzi.4
                @Override // com.ld.sdk.ui.zza.zzj.zza
                public void zza(String str) {
                    if (zzi.this.zzc.getText().toString().equals(str)) {
                        zzi.this.zzc.setText("");
                        zzi.this.zzb();
                    }
                }
            });
        } else {
            this.zzg.setVisibility(8);
        }
        this.zzc.setTextWatcherListener(this.zzq);
        this.zzd.setTextWatcherListener(this.zzq);
        zzt.zza(activity, "account_login_layout", this.zzb).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzi.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzi.this.zza(true);
            }
        });
        View zza = zzt.zza(activity, "back_login", this.zzb);
        zza.setTag(11);
        zza.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb() {
        this.zzn = false;
        this.zzd.setText("");
        this.zzc.setPadding((int) this.zza.getResources().getDimension(zzt.zzc(this.zza, "ld_dp_13")), 0, 0, 0);
        zza(true);
    }

    public void zza(boolean z) {
        if (this.zzl.getVisibility() == 8 && !z) {
            this.zzl.setVisibility(0);
            this.zzj.setImageResource(zzt.zzd(this.zza, "ld_login_register_arrow_up"));
        } else {
            this.zzj.setImageResource(zzt.zzd(this.zza, "ld_login_register_arrow_down"));
            this.zzl.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(String str, String str2) {
        this.zzo = true;
        this.zzc.setText(str);
        this.zzd.setText(str2);
        this.zzf.setEnabled(true);
        this.zzp.setVisibility(str2.isEmpty() ? 8 : 0);
        this.zzd.seePwdClick(this.zzp, true);
        this.zzc.requestFocus();
        zza(true);
        zzd();
        this.zzo = false;
    }

    public void zza(Activity activity) {
        if (this.zzs) {
            return;
        }
        LoginInfo loginInfo = new LoginInfo();
        String trim = this.zzc.getText().toString().trim();
        String obj = this.zzd.getText().toString();
        this.zzs = true;
        this.zzt = zzn.zza((Context) activity, false);
        loginInfo.username = trim;
        loginInfo.password = obj;
        loginInfo.loginMode = LoginMode.USERNAME;
        loginInfo.rememberPwd = this.zzi.isChecked();
        zza.zzf().zza(loginInfo, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.ui.zzc.zzi.8
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(LDUser lDUser, LDException lDException) {
                if (lDException == null) {
                    zzi.this.zzb(true, lDUser.getSpaceUserId(), lDUser.getCpToken(), "");
                } else {
                    zzi.this.zzb(false, "", "", lDException.toString());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(boolean z, String str, String str2, String str3) {
        MyEditText myEditText;
        this.zzt.dismiss();
        this.zzs = false;
        zza(z, str, str2, str3);
        if (z || (myEditText = this.zzd) == null || this.zzc == null || myEditText.hasFocus() || this.zzc.hasFocus()) {
            return;
        }
        this.zzf.setFocusable(true);
        this.zzf.setFocusableInTouchMode(true);
        this.zzf.requestFocus();
    }

    private void zzc() {
        this.zzc.setText("");
        this.zzd.setText("");
        this.zze.setVisibility(0);
        this.zzc.setKeyListener(DigitsKeyListener.getInstance("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_"));
        this.zzc.setInputType(1);
        this.zzd.setInputType(BuildConfig.VERSION_CODE);
        this.zzd.setTypeface(Typeface.DEFAULT);
        if (!this.zzc.isFocused()) {
            this.zzc.setFocusable(true);
            this.zzc.setFocusableInTouchMode(true);
            this.zzc.requestFocus();
            return;
        }
        zzd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzd() {
        try {
            if (this.zzc.getText().toString().length() > 1) {
                MyEditText myEditText = this.zzc;
                myEditText.setSelection(myEditText.getText().length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zze() {
        try {
            if (this.zzd.getText().toString().length() > 1) {
                MyEditText myEditText = this.zzd;
                myEditText.setSelection(myEditText.getText().length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
