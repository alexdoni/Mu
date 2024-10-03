package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.util.zzt;

/* compiled from: LDExitDialog.java */
/* loaded from: classes2.dex */
public class zzi extends Dialog {
    public TextView zza;
    public TextView zzb;
    private ImageView zzc;
    private Button zzd;
    private Button zze;
    private LDCallback1 zzf;

    public zzi(Context context) {
        super(context, zzt.zzh(context, "package_code_dialog_shadow"));
        zza(context);
    }

    public zzi zza(String str) {
        this.zzb.setText(str);
        return this;
    }

    public zzi zzb(String str) {
        this.zzd.setText(str);
        return this;
    }

    public zzi zzc(String str) {
        this.zze.setText(str);
        return this;
    }

    public zzi zza(LDCallback1<Boolean> lDCallback1) {
        this.zzf = lDCallback1;
        return this;
    }

    private void zza(Context context) {
        View zza = zzt.zza(context, "ld_dialog_common_layout", (ViewGroup) null);
        this.zza = (TextView) zzt.zza(context, "tv_title", zza);
        this.zzb = (TextView) zzt.zza(context, "tv_message", zza);
        ImageView imageView = (ImageView) zzt.zza(context, "iv_dialog_close", zza);
        this.zzc = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzi.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzi.this.dismiss();
                if (zzi.this.zzf != null) {
                    zzi.this.zzf.done(false);
                }
            }
        });
        Button button = (Button) zzt.zza(context, "btn_left", zza);
        this.zzd = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzi.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzi.this.dismiss();
                if (zzi.this.zzf != null) {
                    zzi.this.zzf.done(true);
                }
            }
        });
        Button button2 = (Button) zzt.zza(context, "btn_right", zza);
        this.zze = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzi.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzi.this.dismiss();
            }
        });
        setContentView(zza);
        setCanceledOnTouchOutside(false);
    }
}
