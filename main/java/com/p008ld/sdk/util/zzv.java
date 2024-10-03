package com.p008ld.sdk.util;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.p008ld.sdk.LDSdk;
import com.p008ld.sdk.core.bean.LDUploadAuth;
import com.p008ld.sdk.internal.LDException;
import com.p008ld.sdk.internal.LDQueryCallback;
import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: UploadUtils.kt */
/* loaded from: classes2.dex */
public final class zzv {
    public static final zza zza = new zza(null);
    private static zzv zze;
    private OSS zzb;
    private ClientConfiguration zzc = new ClientConfiguration();
    private zzr zzd = new zzr();

    public zzv() {
        this.zzc.setConnectionTimeout(30000);
        this.zzc.setSocketTimeout(30000);
        this.zzc.setMaxConcurrentRequest(5);
        this.zzc.setMaxErrorRetry(3);
    }

    /* compiled from: UploadUtils.kt */
    /* loaded from: classes2.dex */
    public static final class zza {
        public /* synthetic */ zza(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private zza() {
        }

        public final zzv zza() {
            zzv zzvVar = zzv.zze;
            if (zzvVar == null) {
                synchronized (this) {
                    zzvVar = zzv.zze;
                    if (zzvVar == null) {
                        zzvVar = new zzv();
                        zza zzaVar = zzv.zza;
                        zzv.zze = zzvVar;
                    }
                }
            }
            return zzvVar;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0061, code lost:
    
        if (r3.equals(".log") == false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String zza(java.io.File r10) {
        /*
            r9 = this;
            java.lang.String r0 = "file.name"
            long r1 = java.lang.System.currentTimeMillis()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r2 = r10.getName()     // Catch: java.lang.Throwable -> Lb3
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)     // Catch: java.lang.Throwable -> Lb3
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r4 = "."
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            int r2 = kotlin.text.StringsKt.lastIndexOf$default(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r3 = r10.getName()     // Catch: java.lang.Throwable -> Lb3
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r3 = r3.substring(r2)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r4 = "this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch: java.lang.Throwable -> Lb3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r4.<init>()     // Catch: java.lang.Throwable -> Lb3
            int r5 = r3.hashCode()     // Catch: java.lang.Throwable -> Lb3
            r6 = 1477718(0x168c56, float:2.070724E-39)
            if (r5 == r6) goto L5b
            r6 = 1485698(0x16ab82, float:2.081906E-39)
            if (r5 == r6) goto L52
            r6 = 46164218(0x2c068fa, float:2.8272118E-37)
            if (r5 == r6) goto L49
            goto L63
        L49:
            java.lang.String r5 = ".xlog"
            boolean r5 = r3.equals(r5)     // Catch: java.lang.Throwable -> Lb3
            if (r5 == 0) goto L63
            goto L7f
        L52:
            java.lang.String r5 = ".txt"
            boolean r5 = r3.equals(r5)     // Catch: java.lang.Throwable -> Lb3
            if (r5 != 0) goto L7f
            goto L63
        L5b:
            java.lang.String r5 = ".log"
            boolean r5 = r3.equals(r5)     // Catch: java.lang.Throwable -> Lb3
            if (r5 != 0) goto L7f
        L63:
            java.lang.String r0 = "IMG_"
            r4.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r0.<init>()     // Catch: java.lang.Throwable -> Lb3
            r0.append(r1)     // Catch: java.lang.Throwable -> Lb3
            r0.append(r3)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb3
            r4.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> Lb3
            goto Lae
        L7f:
            java.lang.String r5 = r10.getName()     // Catch: java.lang.Throwable -> Lb3
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)     // Catch: java.lang.Throwable -> Lb3
            r0 = 0
            java.lang.String r0 = r5.substring(r0, r2)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r2 = "this as java.lang.String…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)     // Catch: java.lang.Throwable -> Lb3
            r4.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = "_"
            r4.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r0.<init>()     // Catch: java.lang.Throwable -> Lb3
            r0.append(r1)     // Catch: java.lang.Throwable -> Lb3
            r0.append(r3)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb3
            r4.append(r0)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> Lb3
        Lae:
            java.lang.Object r0 = kotlin.Result.m1882constructorimpl(r0)     // Catch: java.lang.Throwable -> Lb3
            goto Lbe
        Lb3:
            r0 = move-exception
            kotlin.Result$Companion r2 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m1882constructorimpl(r0)
        Lbe:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r10 = r10.getName()
            r2.append(r10)
            r10 = 95
            r2.append(r10)
            r2.append(r1)
            java.lang.String r10 = r2.toString()
            boolean r1 = kotlin.Result.m1888isFailureimpl(r0)
            if (r1 == 0) goto Ldd
            r0 = r10
        Ldd:
            java.lang.String r0 = (java.lang.String) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p008ld.sdk.util.zzv.zza(java.io.File):java.lang.String");
    }

    public final void zza(LDUploadAuth auth, File localFile, LDQueryCallback<String> callback) {
        Intrinsics.checkNotNullParameter(auth, "auth");
        Intrinsics.checkNotNullParameter(localFile, "localFile");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            this.zzd.zza(auth.getStsAccessKeyId(), auth.getStsAccessKeySecret(), auth.getStsSecurityToken(), auth.getExpireAt());
            this.zzb = new OSSClient(LDSdk.getApp(), auth.getEndpoint(), this.zzd, this.zzc);
            String str = auth.getDir() + zza(localFile);
            PutObjectRequest putObjectRequest = new PutObjectRequest(auth.getBucketName(), str, localFile.getAbsolutePath());
            OSS oss = this.zzb;
            if (oss != null) {
                oss.asyncPutObject(putObjectRequest, new zzb(callback, auth, str));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LDLog.m573e("UploadUtils -> upLoadFile error：" + e);
            zzm.zza(new zzc(callback, e));
        }
    }

    /* compiled from: UploadUtils.kt */
    /* loaded from: classes2.dex */
    public static final class zzb implements OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
        final /* synthetic */ LDQueryCallback<String> zza;
        final /* synthetic */ LDUploadAuth zzb;
        final /* synthetic */ String zzc;

        zzb(LDQueryCallback<String> lDQueryCallback, LDUploadAuth lDUploadAuth, String str) {
            this.zza = lDQueryCallback;
            this.zzb = lDUploadAuth;
            this.zzc = str;
        }

        @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
            this.zza.done((LDQueryCallback<String>) (this.zzb.getCdnHost() + this.zzc), (LDException) null);
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0013, code lost:
        
            if (r2 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
        
            if (r2 == null) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x000b, code lost:
        
            r1 = r2;
         */
        @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
        /* renamed from: zza, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onFailure(com.alibaba.sdk.android.oss.model.PutObjectRequest r1, com.alibaba.sdk.android.oss.ClientException r2, com.alibaba.sdk.android.oss.ServiceException r3) {
            /*
                r0 = this;
                java.lang.String r1 = ""
                if (r2 == 0) goto Ld
                java.lang.String r2 = r2.getMessage()
                if (r2 != 0) goto Lb
                goto L18
            Lb:
                r1 = r2
                goto L18
            Ld:
                if (r3 == 0) goto L16
                java.lang.String r2 = r3.getMessage()
                if (r2 != 0) goto Lb
                goto L18
            L16:
                java.lang.String r1 = "upload error"
            L18:
                com.ld.sdk.internal.LDQueryCallback<java.lang.String> r2 = r0.zza
                com.ld.sdk.internal.LDException r3 = new com.ld.sdk.internal.LDException
                r3.<init>(r1)
                r1 = 0
                r2.done(r1, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ld.sdk.util.zzv.zzb.onFailure(com.alibaba.sdk.android.oss.model.PutObjectRequest, com.alibaba.sdk.android.oss.ClientException, com.alibaba.sdk.android.oss.ServiceException):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UploadUtils.kt */
    /* loaded from: classes2.dex */
    public static final class zzc extends Lambda implements Function0<Unit> {
        final /* synthetic */ LDQueryCallback<String> zza;
        final /* synthetic */ Exception zzb;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        zzc(LDQueryCallback<String> lDQueryCallback, Exception exc) {
            super(0);
            this.zza = lDQueryCallback;
            this.zzb = exc;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* synthetic */ Unit invoke() {
            zza();
            return Unit.INSTANCE;
        }

        public final void zza() {
            this.zza.done((LDQueryCallback<String>) null, new LDException(this.zzb));
        }
    }
}
