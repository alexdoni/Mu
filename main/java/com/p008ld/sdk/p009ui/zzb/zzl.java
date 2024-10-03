package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.util.zzt;

/* compiled from: LDTipsDialog.java */
/* loaded from: classes2.dex */
public class zzl extends Dialog {
    public TextView zza;
    public TextView zzb;
    private ImageView zzc;
    private Button zzd;
    private Button zze;
    private LDCallback1 zzf;
    private boolean zzg;

    public zzl(Context context) {
        super(context, zzt.zzh(context, "package_code_dialog_shadow"));
        this.zzg = false;
        zza(context);
    }

    public zzl zza(String str) {
        this.zza.setText(str);
        return this;
    }

    public zzl zzb(String str) {
        this.zzb.setText(str);
        return this;
    }

    public zzl zzc(String str) {
        this.zzd.setText(str);
        return this;
    }

    public zzl zza() {
        this.zze.setVisibility(8);
        return this;
    }

    public zzl zza(LDCallback1<Boolean> lDCallback1) {
        this.zzf = lDCallback1;
        return this;
    }

    private void zza(Context context) {
        View zza = zzt.zza(context, "ld_dialog_common_layout", (ViewGroup) null);
        this.zza = (TextView) zzt.zza(context, "tv_title", zza);
        this.zzb = (TextView) zzt.zza(context, "tv_message", zza);
        ImageView imageView = (ImageView) zzt.zza(context, "iv_dialog_close", zza);
        this.zzc = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzl.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzl.this.dismiss();
            }
        });
        Button button = (Button) zzt.zza(context, "btn_left", zza);
        this.zzd = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzl.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzl.this.zzg = true;
                zzl.this.dismiss();
            }
        });
        Button button2 = (Button) zzt.zza(context, "btn_right", zza);
        this.zze = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzl.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                zzl.this.dismiss();
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.ui.zzb.zzl.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (zzl.this.zzf != null) {
                    zzl.this.zzf.done(Boolean.valueOf(zzl.this.zzg));
                }
            }
        });
        setContentView(zza);
    }
}
