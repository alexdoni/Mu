package com.google.android.gms.internal.recaptcha;

import java.lang.Throwable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
abstract class zzmr<V, X extends Throwable, F, T> extends zznv<V> implements Runnable {

    @CheckForNull
    zzop<? extends V> zza;

    @CheckForNull
    Class<X> zzb;

    @CheckForNull
    F zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmr(zzop<? extends V> zzopVar, Class<X> cls, F f) {
        this.zza = zzopVar;
        this.zzb = cls;
        this.zzc = f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0091  */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Class<X extends java.lang.Throwable>, F] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r10 = this;
            com.google.android.gms.internal.recaptcha.zzop<? extends V> r0 = r10.zza
            java.lang.Class<X extends java.lang.Throwable> r1 = r10.zzb
            F r2 = r10.zzc
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Lc
            r5 = r3
            goto Ld
        Lc:
            r5 = r4
        Ld:
            if (r1 != 0) goto L11
            r6 = r3
            goto L12
        L11:
            r6 = r4
        L12:
            r5 = r5 | r6
            if (r2 != 0) goto L16
            goto L17
        L16:
            r3 = r4
        L17:
            r3 = r3 | r5
            if (r3 != 0) goto Lb5
            boolean r3 = r10.isCancelled()
            if (r3 == 0) goto L22
            goto Lb5
        L22:
            r3 = 0
            r10.zza = r3
            boolean r4 = r0 instanceof com.google.android.gms.internal.recaptcha.zzpe     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            if (r4 == 0) goto L31
            r4 = r0
            com.google.android.gms.internal.recaptcha.zzpe r4 = (com.google.android.gms.internal.recaptcha.zzpe) r4     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            java.lang.Throwable r4 = r4.zzn()     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L32
        L31:
            r4 = r3
        L32:
            if (r4 != 0) goto L3a
            java.lang.Object r5 = com.google.android.gms.internal.recaptcha.zzof.zzl(r0)     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L8b
        L39:
            r4 = move-exception
        L3a:
            r5 = r3
            goto L8b
        L3c:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L89
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.String r7 = java.lang.String.valueOf(r6)
            int r7 = r7.length()
            java.lang.String r8 = java.lang.String.valueOf(r4)
            int r8 = r8.length()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            int r7 = r7 + 35
            int r7 = r7 + r8
            r9.<init>(r7)
            java.lang.String r7 = "Future type "
            r9.append(r7)
            r9.append(r6)
            java.lang.String r6 = " threw "
            r9.append(r6)
            r9.append(r4)
            java.lang.String r4 = " without a cause"
            r9.append(r4)
            java.lang.String r4 = r9.toString()
            r5.<init>(r4)
        L89:
            r4 = r5
            goto L3a
        L8b:
            if (r4 != 0) goto L91
            r10.zzt(r5)
            return
        L91:
            boolean r1 = r1.isInstance(r4)
            if (r1 == 0) goto Lb2
            java.lang.Object r0 = r10.zzd(r2, r4)     // Catch: java.lang.Throwable -> La3
            r10.zzb = r3
            r10.zzc = r3
            r10.zze(r0)
            return
        La3:
            r0 = move-exception
            r10.zzu(r0)     // Catch: java.lang.Throwable -> Lac
            r10.zzb = r3
            r10.zzc = r3
            return
        Lac:
            r0 = move-exception
            r10.zzb = r3
            r10.zzc = r3
            throw r0
        Lb2:
            r10.zzc(r0)
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzmr.run():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    @CheckForNull
    public final String zza() {
        String str;
        zzop<? extends V> zzopVar = this.zza;
        Class<X> cls = this.zzb;
        F f = this.zzc;
        String zza = super.zza();
        if (zzopVar != null) {
            String valueOf = String.valueOf(zzopVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (cls == null || f == null) {
            if (zza == null) {
                return null;
            }
            String valueOf2 = String.valueOf(str);
            return zza.length() != 0 ? valueOf2.concat(zza) : new String(valueOf2);
        }
        String valueOf3 = String.valueOf(cls);
        String valueOf4 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 29 + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length());
        sb2.append(str);
        sb2.append("exceptionType=[");
        sb2.append(valueOf3);
        sb2.append("], fallback=[");
        sb2.append(valueOf4);
        sb2.append("]");
        return sb2.toString();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzb() {
        zzr(this.zza);
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    }

    abstract T zzd(F f, X x) throws Exception;

    abstract void zze(T t);
}
