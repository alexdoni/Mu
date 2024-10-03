package com.google.android.gms.internal.recaptcha;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public abstract class zzsa {
    private static final zzsa zza;
    private static final zzsa zzb;

    static {
        zzrx zzrxVar = null;
        zza = new zzry(zzrxVar);
        zzb = new zzrz(zzrxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzsa(zzrx zzrxVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzsa zzd() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzsa zze() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <L> void zzc(Object obj, Object obj2, long j);
}
