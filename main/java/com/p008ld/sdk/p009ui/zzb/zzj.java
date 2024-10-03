package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.util.zzt;

/* compiled from: LDLoadingDialog.java */
/* loaded from: classes2.dex */
public class zzj extends Dialog {
    private AnimationDrawable zza;
    private LDCallback1 zzb;

    public zzj(Context context, boolean z) {
        super(context);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setDimAmount(0.7f);
        requestWindowFeature(1);
        View zza = zzt.zza(context, "ld_loading", (ViewGroup) null);
        ImageView imageView = (ImageView) zzt.zza(context, "kkk_loading_img", zza);
        LinearLayout linearLayout = (LinearLayout) zzt.zza(context, "ll_loading_cancel", zza);
        if (z) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
        this.zza = (AnimationDrawable) imageView.getDrawable();
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzj.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (zzj.this.zzb != null) {
                    zzj.this.zzb.done(true);
                }
            }
        });
        setContentView(zza);
    }

    public zzj zza(LDCallback1<Boolean> lDCallback1) {
        this.zzb = lDCallback1;
        return this;
    }

    public void zza() {
        AnimationDrawable animationDrawable = this.zza;
        if (animationDrawable == null || animationDrawable.isRunning()) {
            return;
        }
        this.zza.start();
    }

    public void zzb() {
        AnimationDrawable animationDrawable = this.zza;
        if (animationDrawable == null || !animationDrawable.isRunning()) {
            return;
        }
        this.zza.stop();
    }
}
