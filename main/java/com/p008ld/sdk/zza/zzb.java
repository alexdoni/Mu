package com.p008ld.sdk.zza;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zzt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDNormalVerifyDialog.kt */
/* loaded from: classes2.dex */
public final class zzb extends Dialog {
    private final Activity zza;
    private CaptchaBean zzb;
    private final LDCallback1<String> zzc;
    private ImageView zzd;
    private TextView zze;
    private TextView zzf;
    private TextView zzg;
    private RelativeLayout zzh;
    private EditText zzi;
    private ImageView zzj;
    private TextView zzk;
    private RelativeLayout zzl;
    private EditText zzm;
    private ImageView zzn;
    private TextView zzo;
    private boolean zzp;
    private String zzq;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzb(android.app.Activity r3, com.p008ld.sdk.bean.CaptchaBean r4, com.p008ld.sdk.internal.LDCallback1<java.lang.String> r5) {
        /*
            r2 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "mBean"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "mAnswerListener"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            r0 = r3
            android.content.Context r0 = (android.content.Context) r0
            java.lang.String r1 = "LD_DialogTheme"
            int r1 = com.p008ld.sdk.util.zzt.zzh(r0, r1)
            r2.<init>(r0, r1)
            r2.zza = r3
            r2.zzb = r4
            r2.zzc = r5
            java.lang.String r3 = ""
            r2.zzq = r3
            r3 = 1
            r2.setCanceledOnTouchOutside(r3)
            r2.setCancelable(r3)
            android.view.Window r3 = r2.getWindow()
            r4 = 0
            if (r3 != 0) goto L34
            goto L4e
        L34:
            android.view.Window r5 = r2.getWindow()
            if (r5 == 0) goto L4a
            android.view.WindowManager$LayoutParams r5 = r5.getAttributes()
            if (r5 == 0) goto L4a
            r0 = -1
            r5.width = r0
            r5.height = r0
            r0 = 17
            r5.gravity = r0
            goto L4b
        L4a:
            r5 = r4
        L4b:
            r3.setAttributes(r5)
        L4e:
            android.content.Context r3 = r2.getContext()
            java.lang.String r5 = "ld_dialog_normal_verify"
            android.view.View r3 = com.p008ld.sdk.util.zzt.zza(r3, r5, r4)
            java.lang.String r4 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r2.zza(r3)
            r2.setContentView(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.zza.zzb.<init>(android.app.Activity, com.ld.sdk.bean.CaptchaBean, com.ld.sdk.internal.LDCallback1):void");
    }

    public final void zza(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        View zza2 = zzt.zza(getContext(), "ld_base_iv_close", view);
        Intrinsics.checkNotNull(zza2, "null cannot be cast to non-null type android.widget.ImageView");
        this.zzd = (ImageView) zza2;
        View zza3 = zzt.zza(getContext(), "ld_base_btn_left", view);
        Intrinsics.checkNotNull(zza3, "null cannot be cast to non-null type android.widget.TextView");
        this.zze = (TextView) zza3;
        View zza4 = zzt.zza(getContext(), "ld_base_btn_right", view);
        Intrinsics.checkNotNull(zza4, "null cannot be cast to non-null type android.widget.TextView");
        this.zzf = (TextView) zza4;
        View zza5 = zzt.zza(getContext(), "ld_base_tv_tips", view);
        Intrinsics.checkNotNull(zza5, "null cannot be cast to non-null type android.widget.TextView");
        this.zzg = (TextView) zza5;
        View zza6 = zzt.zza(getContext(), "ld_base_rl_small", view);
        Intrinsics.checkNotNull(zza6, "null cannot be cast to non-null type android.widget.RelativeLayout");
        this.zzh = (RelativeLayout) zza6;
        View zza7 = zzt.zza(getContext(), "ld_base_et_small", view);
        Intrinsics.checkNotNull(zza7, "null cannot be cast to non-null type android.widget.EditText");
        this.zzi = (EditText) zza7;
        View zza8 = zzt.zza(getContext(), "ld_base_tv_small", view);
        Intrinsics.checkNotNull(zza8, "null cannot be cast to non-null type android.widget.TextView");
        this.zzk = (TextView) zza8;
        View zza9 = zzt.zza(getContext(), "ld_base_iv_small", view);
        Intrinsics.checkNotNull(zza9, "null cannot be cast to non-null type android.widget.ImageView");
        this.zzj = (ImageView) zza9;
        View zza10 = zzt.zza(getContext(), "ld_base_rl_large", view);
        Intrinsics.checkNotNull(zza10, "null cannot be cast to non-null type android.widget.RelativeLayout");
        this.zzl = (RelativeLayout) zza10;
        View zza11 = zzt.zza(getContext(), "ld_base_et_large", view);
        Intrinsics.checkNotNull(zza11, "null cannot be cast to non-null type android.widget.EditText");
        this.zzm = (EditText) zza11;
        View zza12 = zzt.zza(getContext(), "ld_base_tv_large", view);
        Intrinsics.checkNotNull(zza12, "null cannot be cast to non-null type android.widget.TextView");
        this.zzo = (TextView) zza12;
        View zza13 = zzt.zza(getContext(), "ld_base_iv_large", view);
        Intrinsics.checkNotNull(zza13, "null cannot be cast to non-null type android.widget.ImageView");
        this.zzn = (ImageView) zza13;
        zzb();
        TextView textView = this.zzk;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvSmall");
            textView = null;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.zza.zzb$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                zzb.zza(zzb.this, view2);
            }
        });
        TextView textView3 = this.zzo;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvLarge");
            textView3 = null;
        }
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.zza.zzb$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                zzb.zzb(zzb.this, view2);
            }
        });
        ImageView imageView = this.zzd;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivClose");
            imageView = null;
        }
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.zza.zzb$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                zzb.zzc(zzb.this, view2);
            }
        });
        TextView textView4 = this.zze;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnConfirm");
            textView4 = null;
        }
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.zza.zzb$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                zzb.zzd(zzb.this, view2);
            }
        });
        TextView textView5 = this.zzf;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnCancel");
        } else {
            textView2 = textView5;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ld.sdk.zza.zzb$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                zzb.zze(zzb.this, view2);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.ld.sdk.zza.zzb$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                zzb.zza(zzb.this, dialogInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zza(zzb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzb(zzb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.zza();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzc(zzb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzd(zzb this$0, View view) {
        String obj;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = null;
        if (this$0.zzp) {
            EditText editText2 = this$0.zzi;
            if (editText2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etSmall");
            } else {
                editText = editText2;
            }
            obj = editText.getText().toString();
        } else {
            EditText editText3 = this$0.zzm;
            if (editText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etLarge");
            } else {
                editText = editText3;
            }
            obj = editText.getText().toString();
        }
        this$0.zzq = obj;
        String str = obj;
        if (str == null || str.length() == 0) {
            LDUtil.toast(zzt.zza(this$0.getContext(), "ld_dialog_tip"));
        } else {
            this$0.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zze(zzb this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zza(zzb this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LDCallback1<String> lDCallback1 = this$0.zzc;
        if (lDCallback1 != null) {
            lDCallback1.done(this$0.zzq);
        }
    }

    /* compiled from: LDNormalVerifyDialog.kt */
    /* loaded from: classes2.dex */
    public static final class zza implements LDQueryCallback<CaptchaBean> {
        zza() {
        }

        @Override // com.p008ld.sdk.internal.LDCallback2
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void done(CaptchaBean captchaBean, LDException lDException) {
            if (lDException != null || captchaBean == null) {
                return;
            }
            zzb.this.zzb = captchaBean;
            zzb.this.zzb();
        }
    }

    private final void zza() {
        com.p008ld.sdk.model.zza.zza.zza().zza(this.zzb.getCaptchaId(), new zza());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb() {
        this.zzp = this.zzb.getWidth() < 200;
        RelativeLayout relativeLayout = this.zzh;
        ImageView imageView = null;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlSmall");
            relativeLayout = null;
        }
        relativeLayout.setVisibility(this.zzp ? 0 : 8);
        RelativeLayout relativeLayout2 = this.zzl;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rlLarge");
            relativeLayout2 = null;
        }
        relativeLayout2.setVisibility(this.zzp ? 8 : 0);
        Bitmap stringToBitmap = LDUtil.stringToBitmap(this.zzb.getCaptchaData());
        if (stringToBitmap == null) {
            return;
        }
        TextView textView = this.zzg;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tvTipsView");
            textView = null;
        }
        String captchaTips = this.zzb.getCaptchaTips();
        if (captchaTips == null) {
            captchaTips = "";
        }
        textView.setText(captchaTips);
        SpannableString spannableString = new SpannableString(zzt.zza(getContext(), "ld_dialog_hint"));
        spannableString.setSpan(new AbsoluteSizeSpan(this.zzp ? 10 : 12, true), 0, spannableString.length(), 33);
        Bitmap roundedCornerBitmap = LDUtil.getRoundedCornerBitmap(stringToBitmap);
        if (this.zzp) {
            EditText editText = this.zzi;
            if (editText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("etSmall");
                editText = null;
            }
            editText.setHint(spannableString);
            ImageView imageView2 = this.zzj;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ivSmall");
            } else {
                imageView = imageView2;
            }
            imageView.setImageBitmap(roundedCornerBitmap);
            return;
        }
        EditText editText2 = this.zzm;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etLarge");
            editText2 = null;
        }
        editText2.setHint(spannableString);
        ImageView imageView3 = this.zzn;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivLarge");
        } else {
            imageView = imageView3;
        }
        imageView.setImageBitmap(roundedCornerBitmap);
    }

    @Override // android.app.Dialog
    public void show() {
        Activity activity = this.zza;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        super.show();
    }
}
