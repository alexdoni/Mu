package com.google.android.gms.internal.recaptcha;

import kotlin.UByte$$ExternalSyntheticBackport0;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzhq<T> extends zzms<T> {
    private zzhs<T> zza;
    private final int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhq(zzhs zzhsVar, int i, zzho zzhoVar) {
        this.zza = zzhsVar;
        this.zzb = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0006, code lost:
    
        r0 = com.google.android.gms.internal.recaptcha.zzhs.zza(r0).zza;
     */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zza() {
        /*
            r6 = this;
            com.google.android.gms.internal.recaptcha.zzhs<T> r0 = r6.zza
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            com.google.android.gms.internal.recaptcha.zzhp r0 = com.google.android.gms.internal.recaptcha.zzhs.zza(r0)
            com.google.android.gms.internal.recaptcha.zznf r0 = com.google.android.gms.internal.recaptcha.zzhp.zza(r0)
            if (r0 != 0) goto L11
            return r1
        L11:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r1 = r1.length()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r1 = r1 + 11
            r2.<init>(r1)
            java.lang.String r1 = "callable=["
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = "]"
            r2.append(r0)
            java.lang.String r1 = r2.toString()
            com.google.android.gms.internal.recaptcha.zzhs<T> r2 = r6.zza
            java.util.concurrent.atomic.AtomicReference r2 = com.google.android.gms.internal.recaptcha.zzhs.zze(r2)
            java.lang.Object r2 = r2.get()
            com.google.android.gms.internal.recaptcha.zzhr r2 = (com.google.android.gms.internal.recaptcha.zzhr) r2
            if (r2 == 0) goto L76
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r3 = r3 + 10
            int r3 = r3 + r4
            r5.<init>(r3)
            r5.append(r1)
            java.lang.String r1 = ", trial=["
            r5.append(r1)
            r5.append(r2)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            return r0
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzhq.zza():java.lang.String");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzms
    protected final void zzb() {
        zzhr zzhrVar;
        int i;
        zzhs<T> zzhsVar = this.zza;
        this.zza = null;
        if (zzhsVar == null || !zzhs.zzg(zzhsVar)) {
            return;
        }
        do {
            zzhrVar = (zzhr) zzhs.zze(zzhsVar).get();
            if (zzhrVar == null) {
                return;
            }
            i = zzhrVar.zza;
            if (i > this.zzb) {
                return;
            } else {
                zzhrVar.cancel(true);
            }
        } while (!UByte$$ExternalSyntheticBackport0.m1410m(zzhs.zze(zzhsVar), zzhrVar, null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.recaptcha.zzms
    public final boolean zzc(zzop<? extends T> zzopVar) {
        return super.zzc(zzopVar);
    }
}
