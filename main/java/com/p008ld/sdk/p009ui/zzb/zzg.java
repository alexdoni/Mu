package com.p008ld.sdk.p009ui.zzb;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;

/* compiled from: GooglePayConfirmDialog.java */
/* loaded from: classes2.dex */
public class zzg extends Dialog {
    public zzg(Context context, String str, String str2, String str3, View.OnClickListener onClickListener) {
        super(context, zzt.zzh(context, "KKKDialog"));
        View zza = zzt.zza(context, "ld_google_pay_confirm_dialog_layout", (ViewGroup) null);
        zza((TextView) zzt.zza(context, "order_details_tv", zza));
        ((TextView) zzt.zza(context, "name_tv", zza)).setText(str3);
        ((TextView) zzt.zza(context, "amount_tv", zza)).setText(str2);
        ((TextView) zzt.zza(context, "order_id_tv", zza)).setText(str);
        ((AnimationDrawable) ((ImageView) zzt.zza(context, "loading_img", zza)).getDrawable()).start();
        final LinearLayout linearLayout = (LinearLayout) zzt.zza(context, "loading_layout", zza);
        Button button = (Button) zzt.zza(context, "confirm_btn", zza);
        Button button2 = (Button) zzt.zza(context, "cancel_btn", zza);
        final Runnable runnable = new Runnable() { // from class: com.ld.sdk.ui.zzb.zzg.1
            @Override // java.lang.Runnable
            public void run() {
                if (zzg.this.isShowing()) {
                    LDUtil.toast(zzt.zza(zzg.this.getContext(), "ld_not_found_order_success_text"));
                    linearLayout.setVisibility(8);
                }
            }
        };
        button.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.ui.zzb.zzg.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                linearLayout.setVisibility(0);
                linearLayout.postDelayed(runnable, 5000L);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.ui.zzb.zzg.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                linearLayout.removeCallbacks(runnable);
            }
        });
        button2.setOnClickListener(onClickListener);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setContentView(zza, new LinearLayout.LayoutParams(-2, -2));
        show();
    }

    private void zza(TextView textView) {
        textView.getPaint().setShader(new LinearGradient(0.0f, 0.0f, 0.0f, textView.getPaint().getTextSize(), Color.parseColor("#FFA800"), Color.parseColor("#A66C26"), Shader.TileMode.CLAMP));
        textView.invalidate();
    }
}
