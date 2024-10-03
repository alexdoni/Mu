package com.p008ld.sdk.zza;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.internal.LDCallback1;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.util.LDUtil;
import com.p008ld.sdk.util.zze;
import com.p008ld.sdk.util.zzm;
import com.p008ld.sdk.util.zzt;
import com.p008ld.sdk.widget.LDWebView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

/* compiled from: LDH5VerifyDialog.kt */
/* loaded from: classes2.dex */
public final class zza extends Dialog {
    private final Activity zza;
    private final CaptchaBean zzb;
    private final LDCallback1<String> zzc;
    private LDWebView zzd;
    private String zze;
    private Integer zzf;
    private Integer zzg;
    private String zzh;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zza(android.app.Activity r3, com.p008ld.sdk.bean.CaptchaBean r4, com.p008ld.sdk.internal.LDCallback1<java.lang.String> r5) {
        /*
            r2 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "bean"
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
            r3 = -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r2.zzf = r4
            r2.zzg = r4
            java.lang.String r4 = ""
            r2.zzh = r4
            r4 = 1
            r2.setCanceledOnTouchOutside(r4)
            r2.setCancelable(r4)
            android.view.Window r4 = r2.getWindow()
            r5 = 0
            if (r4 != 0) goto L3d
            goto L52
        L3d:
            android.view.Window r0 = r2.getWindow()
            if (r0 == 0) goto L4e
            android.view.WindowManager$LayoutParams r0 = r0.getAttributes()
            if (r0 == 0) goto L4e
            r0.width = r3
            r0.height = r3
            goto L4f
        L4e:
            r0 = r5
        L4f:
            r4.setAttributes(r0)
        L52:
            android.content.Context r3 = r2.getContext()
            java.lang.String r4 = "ld_dialog_h5_verify"
            android.view.View r3 = com.p008ld.sdk.util.zzt.zza(r3, r4, r5)
            java.lang.String r4 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r2.zza(r3)
            r2.setContentView(r3)
            com.ld.sdk.zza.zza$$ExternalSyntheticLambda0 r3 = new com.ld.sdk.zza.zza$$ExternalSyntheticLambda0
            r3.<init>()
            r2.setOnDismissListener(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.zza.zza.<init>(android.app.Activity, com.ld.sdk.bean.CaptchaBean, com.ld.sdk.internal.LDCallback1):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zza(zza this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LDCallback1<String> lDCallback1 = this$0.zzc;
        if (lDCallback1 != null) {
            lDCallback1.done(this$0.zzh);
        }
    }

    private final void zza(View view) {
        this.zze = this.zzb.getCaptchaData();
        this.zzf = Integer.valueOf(this.zzb.getWidth());
        this.zzg = Integer.valueOf(this.zzb.getHeight());
        if (this.zze == null) {
            return;
        }
        View zza = zzt.zza(getContext(), "ld_base_webView", view);
        Intrinsics.checkNotNull(zza, "null cannot be cast to non-null type com.ld.sdk.widget.LDWebView");
        LDWebView lDWebView = (LDWebView) zza;
        this.zzd = lDWebView;
        LDWebView lDWebView2 = null;
        if (lDWebView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView = null;
        }
        ViewGroup.LayoutParams layoutParams = lDWebView.getLayoutParams();
        layoutParams.width = LDUtil.px2dp(this.zzf != null ? r2.intValue() : 0);
        layoutParams.height = LDUtil.px2dp(this.zzg != null ? r2.intValue() : 0);
        LDWebView lDWebView3 = this.zzd;
        if (lDWebView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView3 = null;
        }
        lDWebView3.setLayoutParams(layoutParams);
        LDWebView lDWebView4 = this.zzd;
        if (lDWebView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView4 = null;
        }
        lDWebView4.setBackgroundColor(0);
        LDWebView lDWebView5 = this.zzd;
        if (lDWebView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView5 = null;
        }
        lDWebView5.setVerticalScrollBarEnabled(false);
        LDWebView lDWebView6 = this.zzd;
        if (lDWebView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView6 = null;
        }
        lDWebView6.setHorizontalScrollBarEnabled(false);
        LDWebView lDWebView7 = this.zzd;
        if (lDWebView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView7 = null;
        }
        lDWebView7.setInitialScale(100);
        LDWebView lDWebView8 = this.zzd;
        if (lDWebView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            lDWebView8 = null;
        }
        lDWebView8.addJavascriptInterface(this, "Android");
        StringBuilder sb = new StringBuilder();
        sb.append(this.zze);
        String str = this.zze;
        Intrinsics.checkNotNull(str);
        sb.append(StringsKt.indexOf$default((CharSequence) str, "?", 0, false, 6, (Object) null) > 0 ? "&" : "?");
        sb.append("screenWidth=");
        sb.append(zze.zzh());
        sb.append("&screenHeight=");
        sb.append(zze.zzi());
        sb.append("&webViewWidth=");
        sb.append(this.zzf);
        sb.append("&webViewHeight=");
        sb.append(this.zzg);
        String sb2 = sb.toString();
        LDLog.m573e("LDH5VerifyDialog -> url :" + sb2);
        LDWebView lDWebView9 = this.zzd;
        if (lDWebView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        } else {
            lDWebView2 = lDWebView9;
        }
        lDWebView2.loadUrl(sb2);
    }

    @JavascriptInterface
    public final void closeView() {
        LDLog.m573e("LDH5VerifyDialog -> closeView");
        dismiss();
    }

    @JavascriptInterface
    public final void closeAndTransmit(String answer) {
        Intrinsics.checkNotNullParameter(answer, "answer");
        this.zzh = answer;
        if (answer.length() == 0) {
            LDUtil.toast(zzt.zza(getContext(), "ld_dialog_tip"));
            return;
        }
        LDLog.m573e("LDH5VerifyDialog -> closeAndTransmit:" + this.zzh);
        dismiss();
    }

    /* compiled from: LDH5VerifyDialog.kt */
    /* renamed from: com.ld.sdk.zza.zza$zza, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    static final class C3262zza extends Lambda implements Function0<Unit> {
        C3262zza() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            LDWebView lDWebView = zza.this.zzd;
            LDWebView lDWebView2 = null;
            if (lDWebView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                lDWebView = null;
            }
            ViewGroup.LayoutParams layoutParams = lDWebView.getLayoutParams();
            layoutParams.width = LDUtil.px2dp(zza.this.zzf != null ? r3.intValue() : 0);
            layoutParams.height = LDUtil.px2dp(zza.this.zzg != null ? r3.intValue() : 0);
            LDWebView lDWebView3 = zza.this.zzd;
            if (lDWebView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            } else {
                lDWebView2 = lDWebView3;
            }
            lDWebView2.setLayoutParams(layoutParams);
        }
    }

    @JavascriptInterface
    public final void resizeView(int i, int i2) {
        try {
            LDLog.m573e("LDH5VerifyDialog -> resizeView:" + i + " , " + i2);
            zzm.zza(new C3262zza());
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.logThrowable2Local(e);
        }
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
