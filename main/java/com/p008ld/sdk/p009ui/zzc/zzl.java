package com.p008ld.sdk.p009ui.zzc;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.core.bean.InitResult;
import com.p008ld.sdk.core.bean.LoginInfo;
import com.p008ld.sdk.util.zzt;

/* compiled from: ScanCodeLoginView.java */
/* loaded from: classes2.dex */
public class zzl extends zzh {
    private View.OnClickListener zzc;
    private ImageView zzd;
    private View zze;
    private View zzf;
    private View zzg;
    private Handler zzh;
    private LoginInfo zzi;
    private int zzj;
    private boolean zzk;
    private final Runnable zzl;

    public zzl(Activity activity, View.OnClickListener onClickListener) {
        super(activity, "ld_scan_code_login");
        this.zzl = new Runnable() { // from class: com.ld.sdk.ui.zzc.zzl.2
            @Override // java.lang.Runnable
            public void run() {
            }
        };
        this.zzc = onClickListener;
        this.zzh = new Handler(Looper.getMainLooper());
        zzd();
    }

    private void zzd() {
        this.zzd = (ImageView) zzt.zza(this.zza, "login_qrcode", this.zzb);
        this.zze = zzt.zza(this.zza, "expire_layout", this.zzb);
        this.zzf = zzt.zza(this.zza, "scan_success_layout", this.zzb);
        this.zzg = zzt.zza(this.zza, "desc_layout", this.zzb);
        zzt.zza(this.zza, "expire_text", this.zzb).setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzc.zzl.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzl.this.zzb();
            }
        });
        TextView textView = (TextView) zzt.zza(this.zza, "switch_login", this.zzb);
        textView.setTag(11);
        textView.setOnClickListener(this.zzc);
        ((TextView) zzt.zza(this.zza, "ldAidlScan", this.zzb)).setText("使用  “雷电助手”  扫码登录");
        this.zzi = new LoginInfo();
        this.zzj = new InitResult().ldstoregameid;
    }

    @Override // com.p008ld.sdk.p009ui.zzc.zzh
    public void zzb() {
        this.zzk = true;
    }

    @Override // com.p008ld.sdk.p009ui.zzc.zzh
    public void zzc() {
        this.zzk = false;
    }
}
