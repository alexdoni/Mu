package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.android.material.badge.BadgeDrawable;
import com.p008ld.sdk.util.zzi;
import com.p008ld.sdk.util.zzt;

/* compiled from: CouponTipsDialog.java */
/* loaded from: classes2.dex */
public class zzd extends Dialog {
    private View zza;
    private ImageView zzb;
    private WindowManager.LayoutParams zzc;

    public zzd(Context context) {
        super(context, zzt.zzh(context, "ld_coupon_tips_dialog"));
    }

    public void zza(int i, boolean z, int i2, int i3, int i4, int i5, int i6) {
        this.zza = zzt.zza(getContext(), "ld_coupon_tips_dialog_layout", (ViewGroup) null);
        this.zzb = (ImageView) zzt.zza(getContext(), "coupon_tips_img", this.zza);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        this.zzc = attributes;
        attributes.verticalMargin = 0.0f;
        this.zzc.flags = 8;
        this.zzc.height = this.zza.getMeasuredHeight();
        this.zzc.gravity = BadgeDrawable.TOP_START;
        window.setAttributes(this.zzc);
        zzb(i, z, i2, i3, i4, i5, i6);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        setContentView(this.zza, new LinearLayout.LayoutParams(-2, -2));
        show();
    }

    public void zzb(int i, final boolean z, final int i2, final int i3, final int i4, final int i5, final int i6) {
        int i7;
        final boolean zza = zzi.zza();
        this.zza.setVisibility(4);
        if (i == 4) {
            this.zzb.setImageResource(zzt.zzd(getContext(), "ld_coupon_tips_right_icon"));
            this.zzb.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.ld.sdk.ui.zzb.zzd.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    int i8;
                    WindowManager.LayoutParams layoutParams = zzd.this.zzc;
                    int i9 = i2;
                    if (z) {
                        int i10 = i4;
                        i8 = (i10 / 2) - ((((i10 / 2) / 2) / 2) / 2);
                    } else {
                        i8 = i4;
                    }
                    layoutParams.x = (i9 - i8) - zzd.this.zzb.getWidth();
                    zzd.this.zzc.y = i3 + ((i5 / 2) / 2);
                    if (!zza) {
                        zzd.this.zzc.x += (i4 / 2) + i6;
                        zzd.this.zzc.y += (z ? i6 / 2 : i6) / 2;
                    }
                    zzd.this.getWindow().setAttributes(zzd.this.zzc);
                    zzd.this.zza.postDelayed(new Runnable() { // from class: com.ld.sdk.ui.zzb.zzd.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            zzd.this.zza.setVisibility(0);
                        }
                    }, 200L);
                    zzd.this.zzb.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
            return;
        }
        if (i == 1 || i == 2) {
            this.zza.setVisibility(0);
            WindowManager.LayoutParams layoutParams = this.zzc;
            if (z) {
                int i8 = i5 / 2;
                i7 = i8 - (((i8 / 2) / 2) / 2);
            } else {
                i7 = i5;
            }
            layoutParams.y = i3 + i7;
            if (!zza) {
                this.zzc.y -= z ? i6 / 2 : i6;
            }
            if (i == 1) {
                this.zzc.x = i2 + i6;
                this.zzb.setImageResource(zzt.zzd(getContext(), "ld_coupon_tips_top_left_icon"));
                return;
            }
            if (zza) {
                int i9 = i4 / 2;
                this.zzc.x = (i2 - i9) - ((i9 / 2) / 2);
            } else {
                this.zzc.x = i2 - (z ? i6 * 2 : i6 * 3);
            }
            this.zzb.setImageResource(zzt.zzd(getContext(), "ld_coupon_tips_top_right_icon"));
            return;
        }
        this.zza.setVisibility(0);
        this.zzc.x = (z ? i4 / 2 : i4) + i2;
        this.zzc.y = i3 + ((i5 / 2) / 2);
        if (!zza) {
            this.zzc.x -= i6;
            this.zzc.y += i6 / 2;
        }
        this.zzb.setImageResource(zzt.zzd(getContext(), "ld_coupon_tips_left_icon"));
    }
}
