package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@21.5.0 */
/* loaded from: classes2.dex */
final class zzkp<T> implements zzlb<T> {
    private final zzkj zza;
    private final zzma<?, ?> zzb;
    private final boolean zzc;
    private final zzim<?> zzd;

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final int zza(T t) {
        zzma<?, ?> zzmaVar = this.zzb;
        int zzb = zzmaVar.zzb(zzmaVar.zzd(t)) + 0;
        return this.zzc ? zzb + this.zzd.zza(t).zza() : zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final int zzb(T t) {
        int hashCode = this.zzb.zzd(t).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza(t).hashCode() : hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzkp<T> zza(zzma<?, ?> zzmaVar, zzim<?> zzimVar, zzkj zzkjVar) {
        return new zzkp<>(zzmaVar, zzimVar, zzkjVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final T zza() {
        zzkj zzkjVar = this.zza;
        if (zzkjVar instanceof zzix) {
            return (T) ((zzix) zzkjVar).zzbz();
        }
        return (T) zzkjVar.zzcd().zzac();
    }

    private zzkp(zzma<?, ?> zzmaVar, zzim<?> zzimVar, zzkj zzkjVar) {
        this.zzb = zzmaVar;
        this.zzc = zzimVar.zza(zzkjVar);
        this.zzd = zzimVar;
        this.zza = zzkjVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final void zzc(T t) {
        this.zzb.zzf(t);
        this.zzd.zzc(t);
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final void zza(T t, T t2) {
        zzld.zza(this.zzb, t, t2);
        if (this.zzc) {
            zzld.zza(this.zzd, t, t2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0086 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[LOOP:0: B:2:0x000c->B:20:?, LOOP_END, SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzlb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r11, com.google.android.gms.internal.measurement.zzlc r12, com.google.android.gms.internal.measurement.zzik r13) throws java.io.IOException {
        /*
            r10 = this;
            com.google.android.gms.internal.measurement.zzma<?, ?> r0 = r10.zzb
            com.google.android.gms.internal.measurement.zzim<?> r1 = r10.zzd
            java.lang.Object r2 = r0.zzc(r11)
            com.google.android.gms.internal.measurement.zziq r3 = r1.zzb(r11)
        Lc:
            int r4 = r12.zzc()     // Catch: java.lang.Throwable -> L8f
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L19
            r0.zzb(r11, r2)
            return
        L19:
            int r4 = r12.zzd()     // Catch: java.lang.Throwable -> L8f
            r6 = 11
            if (r4 == r6) goto L3e
            r5 = r4 & 7
            r6 = 2
            if (r5 != r6) goto L39
            com.google.android.gms.internal.measurement.zzkj r5 = r10.zza     // Catch: java.lang.Throwable -> L8f
            int r4 = r4 >>> 3
            java.lang.Object r4 = r1.zza(r13, r5, r4)     // Catch: java.lang.Throwable -> L8f
            if (r4 == 0) goto L34
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L8f
            goto L83
        L34:
            boolean r4 = r0.zza(r2, r12)     // Catch: java.lang.Throwable -> L8f
            goto L84
        L39:
            boolean r4 = r12.zzt()     // Catch: java.lang.Throwable -> L8f
            goto L84
        L3e:
            r4 = 0
            r6 = 0
            r7 = r6
            r6 = r4
        L42:
            int r8 = r12.zzc()     // Catch: java.lang.Throwable -> L8f
            if (r8 == r5) goto L70
            int r8 = r12.zzd()     // Catch: java.lang.Throwable -> L8f
            r9 = 16
            if (r8 != r9) goto L5b
            int r7 = r12.zzj()     // Catch: java.lang.Throwable -> L8f
            com.google.android.gms.internal.measurement.zzkj r4 = r10.zza     // Catch: java.lang.Throwable -> L8f
            java.lang.Object r4 = r1.zza(r13, r4, r7)     // Catch: java.lang.Throwable -> L8f
            goto L42
        L5b:
            r9 = 26
            if (r8 != r9) goto L6a
            if (r4 == 0) goto L65
            r1.zza(r12, r4, r13, r3)     // Catch: java.lang.Throwable -> L8f
            goto L42
        L65:
            com.google.android.gms.internal.measurement.zzhm r6 = r12.zzp()     // Catch: java.lang.Throwable -> L8f
            goto L42
        L6a:
            boolean r8 = r12.zzt()     // Catch: java.lang.Throwable -> L8f
            if (r8 != 0) goto L42
        L70:
            int r5 = r12.zzd()     // Catch: java.lang.Throwable -> L8f
            r8 = 12
            if (r5 != r8) goto L8a
            if (r6 == 0) goto L83
            if (r4 == 0) goto L80
            r1.zza(r6, r4, r13, r3)     // Catch: java.lang.Throwable -> L8f
            goto L83
        L80:
            r0.zza(r2, r7, r6)     // Catch: java.lang.Throwable -> L8f
        L83:
            r4 = 1
        L84:
            if (r4 != 0) goto Lc
            r0.zzb(r11, r2)
            return
        L8a:
            com.google.android.gms.internal.measurement.zzji r12 = com.google.android.gms.internal.measurement.zzji.zzb()     // Catch: java.lang.Throwable -> L8f
            throw r12     // Catch: java.lang.Throwable -> L8f
        L8f:
            r12 = move-exception
            r0.zzb(r11, r2)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkp.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzlc, com.google.android.gms.internal.measurement.zzik):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0099 A[EDGE_INSN: B:24:0x0099->B:25:0x0099 BREAK  A[LOOP:1: B:10:0x0053->B:18:0x0053], SYNTHETIC] */
    @Override // com.google.android.gms.internal.measurement.zzlb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.measurement.zzhl r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.measurement.zzix r0 = (com.google.android.gms.internal.measurement.zzix) r0
            com.google.android.gms.internal.measurement.zzlz r1 = r0.zzb
            com.google.android.gms.internal.measurement.zzlz r2 = com.google.android.gms.internal.measurement.zzlz.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.measurement.zzlz r1 = com.google.android.gms.internal.measurement.zzlz.zzd()
            r0.zzb = r1
        L11:
            com.google.android.gms.internal.measurement.zzix$zzd r10 = (com.google.android.gms.internal.measurement.zzix.zzd) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L18:
            if (r12 >= r13) goto La4
            int r4 = com.google.android.gms.internal.measurement.zzhi.zzc(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L51
            r12 = r2 & 7
            if (r12 != r3) goto L4c
            com.google.android.gms.internal.measurement.zzim<?> r12 = r9.zzd
            com.google.android.gms.internal.measurement.zzik r0 = r14.zzd
            com.google.android.gms.internal.measurement.zzkj r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.measurement.zzix$zzf r0 = (com.google.android.gms.internal.measurement.zzix.zzf) r0
            if (r0 != 0) goto L43
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.measurement.zzhi.zza(r2, r3, r4, r5, r6, r7)
            goto L18
        L43:
            com.google.android.gms.internal.measurement.zzkx.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L4c:
            int r12 = com.google.android.gms.internal.measurement.zzhi.zza(r2, r11, r4, r13, r14)
            goto L18
        L51:
            r12 = 0
            r2 = r10
        L53:
            if (r4 >= r13) goto L99
            int r4 = com.google.android.gms.internal.measurement.zzhi.zzc(r11, r4, r14)
            int r5 = r14.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L7b
            r8 = 3
            if (r6 == r8) goto L65
            goto L90
        L65:
            if (r0 != 0) goto L72
            if (r7 != r3) goto L90
            int r4 = com.google.android.gms.internal.measurement.zzhi.zza(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.gms.internal.measurement.zzhm r2 = (com.google.android.gms.internal.measurement.zzhm) r2
            goto L53
        L72:
            com.google.android.gms.internal.measurement.zzkx.zza()
            java.lang.NoSuchMethodError r10 = new java.lang.NoSuchMethodError
            r10.<init>()
            throw r10
        L7b:
            if (r7 != 0) goto L90
            int r4 = com.google.android.gms.internal.measurement.zzhi.zzc(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.gms.internal.measurement.zzim<?> r0 = r9.zzd
            com.google.android.gms.internal.measurement.zzik r5 = r14.zzd
            com.google.android.gms.internal.measurement.zzkj r6 = r9.zza
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.measurement.zzix$zzf r0 = (com.google.android.gms.internal.measurement.zzix.zzf) r0
            goto L53
        L90:
            r6 = 12
            if (r5 == r6) goto L99
            int r4 = com.google.android.gms.internal.measurement.zzhi.zza(r5, r11, r4, r13, r14)
            goto L53
        L99:
            if (r2 == 0) goto La1
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zza(r12, r2)
        La1:
            r12 = r4
            goto L18
        La4:
            if (r12 != r13) goto La7
            return
        La7:
            com.google.android.gms.internal.measurement.zzji r10 = com.google.android.gms.internal.measurement.zzji.zzg()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkp.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzhl):void");
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final void zza(T t, zzmw zzmwVar) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd = this.zzd.zza(t).zzd();
        while (zzd.hasNext()) {
            Map.Entry<?, Object> next = zzd.next();
            zzis zzisVar = (zzis) next.getKey();
            if (zzisVar.zzc() != zzmx.MESSAGE || zzisVar.zze() || zzisVar.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (next instanceof zzjm) {
                zzmwVar.zza(zzisVar.zza(), (Object) ((zzjm) next).zza().zzc());
            } else {
                zzmwVar.zza(zzisVar.zza(), next.getValue());
            }
        }
        zzma<?, ?> zzmaVar = this.zzb;
        zzmaVar.zza((zzma<?, ?>) zzmaVar.zzd(t), zzmwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final boolean zzb(T t, T t2) {
        if (!this.zzb.zzd(t).equals(this.zzb.zzd(t2))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza(t).equals(this.zzd.zza(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzlb
    public final boolean zzd(T t) {
        return this.zzd.zza(t).zzg();
    }
}
