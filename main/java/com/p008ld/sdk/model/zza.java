package com.p008ld.sdk.model;

import android.content.Context;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.bean.CaptchaBean;
import com.p008ld.sdk.internal.LDQueryCallback;
import com.p008ld.sdk.internal.LDValidate;
import com.p008ld.sdk.util.LDLog;
import com.p008ld.sdk.zzb.zzb;
import kotlin.ExceptionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LDBaseManager.kt */
/* loaded from: classes2.dex */
public final class zza {
    public static final C3253zza zza = new C3253zza(null);
    private static volatile zza zzd;
    private final zzb zzb;
    private String zzc;

    public zza(zzb mModel) {
        Intrinsics.checkNotNullParameter(mModel, "mModel");
        this.zzb = mModel;
        this.zzc = "";
    }

    /* compiled from: LDBaseManager.kt */
    /* renamed from: com.ld.sdk.model.zza$zza, reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static final class C3253zza {
        public /* synthetic */ C3253zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private C3253zza() {
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x004d A[Catch: all -> 0x0087, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x0025, B:9:0x0041, B:14:0x004d, B:16:0x005c, B:18:0x0064, B:21:0x006d, B:22:0x0076, B:24:0x0079, B:26:0x007f), top: B:2:0x0001 }] */
        @kotlin.jvm.JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final synchronized com.p008ld.sdk.model.zza zza() {
            /*
                r7 = this;
                monitor-enter(r7)
                com.ld.sdk.model.zza r0 = com.p008ld.sdk.model.zza.zzd()     // Catch: java.lang.Throwable -> L87
                if (r0 != 0) goto L79
                com.ld.sdk.model.zza r0 = new com.ld.sdk.model.zza     // Catch: java.lang.Throwable -> L87
                com.ld.sdk.model.zzb r1 = new com.ld.sdk.model.zzb     // Catch: java.lang.Throwable -> L87
                r1.<init>()     // Catch: java.lang.Throwable -> L87
                r0.<init>(r1)     // Catch: java.lang.Throwable -> L87
                android.content.Context r1 = com.p008ld.sdk.LDSdk.getApp()     // Catch: java.lang.Throwable -> L87
                com.ld.sdk.model.zzd r1 = com.p008ld.sdk.model.zza.zza(r0, r1)     // Catch: java.lang.Throwable -> L87
                java.io.File r1 = r1.zza()     // Catch: java.lang.Throwable -> L87
                boolean r2 = com.p008ld.sdk.util.zzj.zzd(r1)     // Catch: java.lang.Throwable -> L87
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L5c
                com.ld.sdk.internal.LDNative r2 = com.p008ld.sdk.internal.LDNative.INSTANCE     // Catch: java.lang.Throwable -> L87
                java.lang.String r5 = "KEY_LD_SID"
                java.lang.String r2 = r2.encrypt(r5)     // Catch: java.lang.Throwable -> L87
                r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                java.lang.String r1 = com.p008ld.sdk.util.zzf.zza(r1, r2, r5)     // Catch: java.lang.Throwable -> L87
                com.p008ld.sdk.model.zza.zza(r0, r1)     // Catch: java.lang.Throwable -> L87
                java.lang.String r1 = com.p008ld.sdk.model.zza.zzb(r0)     // Catch: java.lang.Throwable -> L87
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Throwable -> L87
                if (r1 == 0) goto L4a
                int r1 = r1.length()     // Catch: java.lang.Throwable -> L87
                if (r1 != 0) goto L48
                goto L4a
            L48:
                r1 = r3
                goto L4b
            L4a:
                r1 = r4
            L4b:
                if (r1 != 0) goto L5c
                com.ld.sdk.zzb.zzb$zza r1 = com.p008ld.sdk.zzb.zzb.zza     // Catch: java.lang.Throwable -> L87
                com.ld.sdk.zzb.zzb r1 = r1.zza()     // Catch: java.lang.Throwable -> L87
                java.lang.String r2 = "KEY_LD_SID"
                java.lang.String r5 = com.p008ld.sdk.model.zza.zzb(r0)     // Catch: java.lang.Throwable -> L87
                r1.zza(r2, r5)     // Catch: java.lang.Throwable -> L87
            L5c:
                java.lang.String r1 = com.p008ld.sdk.model.zza.zzb(r0)     // Catch: java.lang.Throwable -> L87
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.Throwable -> L87
                if (r1 == 0) goto L6a
                int r1 = r1.length()     // Catch: java.lang.Throwable -> L87
                if (r1 != 0) goto L6b
            L6a:
                r3 = r4
            L6b:
                if (r3 == 0) goto L76
                java.lang.String r1 = "KEY_LD_SID"
                java.lang.String r1 = com.p008ld.sdk.util.zzm.zzb(r1)     // Catch: java.lang.Throwable -> L87
                com.p008ld.sdk.model.zza.zza(r0, r1)     // Catch: java.lang.Throwable -> L87
            L76:
                com.p008ld.sdk.model.zza.zza(r0)     // Catch: java.lang.Throwable -> L87
            L79:
                com.ld.sdk.model.zza r0 = com.p008ld.sdk.model.zza.zzd()     // Catch: java.lang.Throwable -> L87
                if (r0 != 0) goto L85
                java.lang.String r0 = "instance"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)     // Catch: java.lang.Throwable -> L87
                r0 = 0
            L85:
                monitor-exit(r7)
                return r0
            L87:
                r0 = move-exception
                monitor-exit(r7)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.model.zza.C3253zza.zza():com.ld.sdk.model.zza");
        }
    }

    public final void zza() {
        LDLog.m573e("initSdk -> local sid = " + this.zzc);
        String str = this.zzc;
        if (str == null || str.length() == 0) {
            LDSdk.getExecutor().execute(new Runnable() { // from class: com.ld.sdk.model.zza$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    zza.zzc(zza.this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzc(zza this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.zzc = this$0.zzb();
    }

    public final String zzb() {
        String zzb = this.zzb.zzb();
        String str = zzb;
        if (str == null || str.length() == 0) {
            return "";
        }
        this.zzc = zzb;
        LDLog.m573e("initSdk -> save : " + zzb);
        try {
            zzb.zza.zza().zza("KEY_LD_SID", zzb);
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.log2Local("syncsGetSid fail: " + ExceptionsKt.stackTraceToString(e));
        }
        return zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final zzd zza(Context context) {
        Context appContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        return new zzd(appContext);
    }

    public final void zza(String captchaId, LDQueryCallback<CaptchaBean> callback) {
        Intrinsics.checkNotNullParameter(captchaId, "captchaId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (LDValidate.notNullOrEmpty(captchaId, "captchaId", callback)) {
            this.zzb.zza(captchaId, callback);
        }
    }
}
