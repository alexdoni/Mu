package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.p008ld.sdk.core.LDUser;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.core.bean.LoginMode;
import com.p008ld.sdk.core.bean.PublicUserInfo;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.p009ui.zza.zzj;
import com.p008ld.sdk.p009ui.zzb.zzc;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzn;
import com.p008ld.sdk.util.zzt;
import java.util.List;

/* compiled from: AccountHistoryView.java */
/* loaded from: classes2.dex */
public class zza extends zzg {
    private TextView zzc;
    private ImageView zzd;
    private ListView zze;
    private Dialog zzf;

    public zza(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_login_history_account");
        zzb(activity, onClickListener);
    }

    private void zzb(Activity activity, View.OnClickListener onClickListener) {
        this.zzc = (TextView) zzt.zza(activity, "tv_other_login", this.zzb);
        this.zzd = (ImageView) zzt.zza(activity, "iv_contact_service", this.zzb);
        this.zze = (ListView) zzt.zza(activity, "account_list_view", this.zzb);
        this.zzd.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zza.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zza.this.zzb();
            }
        });
        this.zzc.setTag(11);
        this.zzc.setOnClickListener(onClickListener);
        zzc();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb() {
        zzc zzcVar = new zzc(this.zza, true);
        int top2 = this.zzd.getTop() - zzi.zza(this.zza, 30.0f);
        zzcVar.showAtLocation(this.zzd, 0, zzi.zza(this.zza, 220.0f), top2);
    }

    private void zzc() {
        final List<PublicUserInfo> zza = com.p008ld.sdk.zzb.zzc.zzc().zza();
        if (zza == null || zza.isEmpty()) {
            return;
        }
        this.zze.setAdapter((ListAdapter) new zzj(this.zza, zza));
        this.zze.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.ld.sdk.ui.zzc.zza.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                zza.this.zza((PublicUserInfo) zza.get(i));
            }
        });
    }

    public void zza(PublicUserInfo publicUserInfo) {
        LoginInfo loginInfo = new LoginInfo();
        this.zzf = zzn.zza((Context) this.zza, false);
        loginInfo.loginMode = LoginMode.AUTO;
        loginInfo.uid = publicUserInfo.ldUserId;
        loginInfo.token = publicUserInfo.ldUserToken;
        com.p008ld.sdk.core.zza.zza.zzf().zza(loginInfo, new LDQueryCallback<LDUser>() { // from class: com.ld.sdk.ui.zzc.zza.3
            @Override // com.p008ld.sdk.internal.LDCallback2
            /* renamed from: zza, reason: merged with bridge method [inline-methods] */
            public void done(LDUser lDUser, LDException lDException) {
                if (lDException == null) {
                    zza.this.zzb(true, lDUser.getSpaceUserId(), lDUser.getCpToken(), "");
                } else {
                    zza.this.zzb(false, "", "", lDException.toString());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(boolean z, String str, String str2, String str3) {
        this.zzf.dismiss();
        zza(z, str, str2, str3);
    }
}
