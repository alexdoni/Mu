package com.p008ld.sdk.p009ui.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.zza.zza;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.LoginActivity;
import com.p008ld.sdk.p009ui.zzb.zzl;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.PictureHelper;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.CircleImageView;

/* loaded from: classes2.dex */
public class AccountManagerView extends BaseAccountView {
    private View zze;
    private TextView zzf;
    private View zzg;
    private TextView zzh;
    private ImageView zzi;
    private ImageView zzj;
    private ImageView zzk;
    private ImageView zzl;
    private TextView zzm;
    private Context zzn;
    private TextView zzo;
    private CircleImageView zzp;
    private TextView zzq;
    private TextView zzr;
    private BindGoogleBroadcastReceiver zzs;
    private int zzt;

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public String getTitle() {
        return "";
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public boolean zza() {
        return false;
    }

    public AccountManagerView(Activity activity, View.OnClickListener onClickListener) {
        super(activity);
        this.zzt = 0;
        this.zzn = activity;
        zza(activity, onClickListener);
    }

    @Override // com.p008ld.sdk.p009ui.account.BaseAccountView
    public void zzb() {
        zzc();
    }

    private void zzc() {
        zzd();
    }

    public void zza(final Activity activity, final View.OnClickListener onClickListener) {
        View zza = zzt.zza((Context) activity, "ld_account_manager_layout", (ViewGroup) this);
        View zza2 = zzt.zza(activity, "btn_edit", zza);
        zza2.setOnClickListener(onClickListener);
        zza2.setTag(1127);
        this.zzo = (TextView) zzt.zza(activity, "tv_username", zza);
        this.zzp = (CircleImageView) zzt.zza(activity, "iv_user_avatar", zza);
        this.zzj = (ImageView) zzt.zza(activity, "email_right_img", zza);
        this.zzi = (ImageView) zzt.zza(activity, "email_eye_img", zza);
        this.zzk = (ImageView) zzt.zza(activity, "google_account_eye_img", zza);
        this.zzl = (ImageView) zzt.zza(activity, "google_account_right_img", zza);
        this.zze = zzt.zza(activity, "email_layout", zza);
        this.zzf = (TextView) zzt.zza(activity, "email_status_tv", zza);
        this.zzg = zzt.zza(activity, "google_account_layout", zza);
        this.zzh = (TextView) zzt.zza(activity, "google_account_status_tv", zza);
        this.zzm = (TextView) zzt.zza(activity, "tv_ld_id", zza);
        TextView textView = (TextView) zzt.zza(activity, "tv_version", zza);
        this.zzq = textView;
        textView.setText("Version:" + LDSdk.getSdkVersion());
        this.zzr = (TextView) zzt.zza(activity, "tv_google_account", zza);
        this.zzm.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzi.zza((Context) activity, (CharSequence) AccountManagerView.this.zzm.getText().toString());
            }
        });
        View zza3 = zzt.zza(activity, "order_record_layout", zza);
        zza3.setTag(6000);
        zza3.setOnClickListener(onClickListener);
        zzd();
        View zza4 = zzt.zza(activity, "reset_password_layout", zza);
        zza4.setOnClickListener(onClickListener);
        zza4.setTag(5000);
        this.zze.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AccountManagerView.this.zzf.getText().toString().equals(zzt.zza(activity, "ld_unbound_text"))) {
                    View view2 = new View(AccountManagerView.this.zzn);
                    view2.setOnClickListener(onClickListener);
                    view2.setTag(Integer.valueOf(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE));
                    view2.performClick();
                }
            }
        });
        this.zzg.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AccountManagerView.this.zzh.getText().toString().equals(zzt.zza(AccountManagerView.this.zzn, "ld_unbound_text"))) {
                    try {
                        AccountManagerView.this.zzs = new BindGoogleBroadcastReceiver();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("bind_google_action");
                        AccountManagerView.this.zzn.registerReceiver(AccountManagerView.this.zzs, intentFilter);
                        Intent intent = new Intent(activity, (Class<?>) LoginActivity.class);
                        intent.putExtra("pageId", 10004);
                        activity.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.zzi.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AccountManagerView.this.zzi.getTag().equals("hide")) {
                    AccountManagerView.this.zzi.setTag("show");
                    AccountManagerView.this.zzf.setText((CharSequence) AccountManagerView.this.zzf.getTag());
                    AccountManagerView.this.zzi.setImageResource(zzt.zzd(activity, "ld_show_pwd_icon"));
                    return;
                }
                if (AccountManagerView.this.zzi.getTag().equals("show")) {
                    AccountManagerView.this.zzi.setTag("hide");
                    AccountManagerView.this.zzf.setText(zzi.zzb(AccountManagerView.this.zzf.getText().toString()));
                    AccountManagerView.this.zzi.setImageResource(zzt.zzd(activity, "ld_show_email_icon"));
                }
            }
        });
        this.zzk.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AccountManagerView.this.zzk.getTag().equals("hide")) {
                    AccountManagerView.this.zzk.setTag("show");
                    AccountManagerView.this.zzh.setText((CharSequence) AccountManagerView.this.zzh.getTag());
                    AccountManagerView.this.zzk.setImageResource(zzt.zzd(activity, "ld_show_pwd_icon"));
                    return;
                }
                if (AccountManagerView.this.zzk.getTag().equals("show")) {
                    AccountManagerView.this.zzk.setTag("hide");
                    AccountManagerView.this.zzh.setText(zzi.zzb(AccountManagerView.this.zzh.getText().toString()));
                    AccountManagerView.this.zzk.setImageResource(zzt.zzd(activity, "ld_show_email_icon"));
                }
            }
        });
        zzt.zza(activity, "exit_login_layout", zza).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new zzl(activity).zza(zzt.zza(activity, "ld_dialog_logout_title")).zzb(zzt.zza(activity, "ld_dialog_logout_msg")).zza(new LDCallback1<Boolean>() { // from class: com.ld.sdk.ui.account.AccountManagerView.6.1
                    @Override // com.p008ld.sdk.internal.LDCallback1
                    /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                    public void done(Boolean bool) {
                        if (bool.booleanValue()) {
                            zza.zzf().zza(true);
                        }
                    }
                }).show();
            }
        });
        this.zzr.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.account.AccountManagerView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountManagerView.this.zze();
            }
        });
        if (zzi.zza(activity) || this.zzc == null) {
            return;
        }
        this.zzc.setVisibility(0);
        this.zzc.setImageResource(zzt.zzd(activity, "ld_pay_close_img"));
        this.zzc.setTag(1123);
        this.zzc.setOnClickListener(onClickListener);
    }

    /* loaded from: classes2.dex */
    public class BindGoogleBroadcastReceiver extends BroadcastReceiver {
        public BindGoogleBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String stringExtra = intent.getStringExtra("token");
                String stringExtra2 = intent.getStringExtra("googleAccount");
                if (!zzi.zza((CharSequence) stringExtra)) {
                    zza.zzf().zza(LoginMode.GOOGLE, stringExtra, new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountManagerView.BindGoogleBroadcastReceiver.1
                        @Override // com.p008ld.sdk.internal.LDCallback2
                        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                        public void done(String str, LDException lDException) {
                            if (lDException == null) {
                                AccountManagerView.this.zzd();
                            }
                            LDUtil.toast(str);
                        }
                    }, stringExtra2);
                }
            }
            if (AccountManagerView.this.zzs != null) {
                AccountManagerView.this.zzn.unregisterReceiver(AccountManagerView.this.zzs);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzd() {
        LDUser zza = zza.zzf().zza();
        if (zza != null) {
            this.zzo.setText(zza.getNickname());
            PictureHelper.loadAvatar(zza.getHeadPortraitUrl(), this.zzp);
            this.zzm.setText("LD ID:" + zza.getSpaceUserId());
            if (zzi.zza((CharSequence) zza.getEmail()) || !zza.isBindEmail()) {
                this.zzf.setText(zzt.zza(this.zzn, "ld_unbound_text"));
            } else {
                this.zzj.setVisibility(4);
                this.zzi.setVisibility(0);
                this.zzi.setTag("hide");
                this.zzf.setText(zzi.zzb(zza.getEmail()));
                this.zzf.setTag(zza.getEmail());
            }
            if (zzi.zza((CharSequence) zza.getGoogleAccount())) {
                this.zzh.setText(zzt.zza(this.zzn, "ld_unbound_text"));
                return;
            }
            this.zzl.setVisibility(4);
            this.zzk.setVisibility(0);
            this.zzk.setTag("hide");
            this.zzh.setText(zzi.zzb(zza.getGoogleAccount()));
            this.zzh.setTag(zza.getGoogleAccount());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zze() {
        int i = this.zzt + 1;
        this.zzt = i;
        if (i == 5) {
            final Dialog zza = zzn.zza(this.zzn, false);
            zza.zzf().zza(new LDQueryCallback<String>() { // from class: com.ld.sdk.ui.account.AccountManagerView.8
                @Override // com.p008ld.sdk.internal.LDCallback2
                /* renamed from: zza, reason: merged with bridge method [inline-methods] */
                public void done(String str, LDException lDException) {
                    zza.dismiss();
                    AccountManagerView.this.zzt = 0;
                    if (lDException == null) {
                        LDLog.m573e("uploadLogFile: url = " + str);
                        LDUtil.toast(zzt.zza(AccountManagerView.this.zzn, "ld_log_upload_success_text"));
                        return;
                    }
                    LDLog.m573e(lDException.toString());
                }
            });
        }
    }
}
