package com.google.android.gms.internal.recaptcha;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzgn extends zzhd {
    private static final zzhd zza = new zzgn();

    private zzgn() {
    }

    public static /* synthetic */ zzhd zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzhd
    public final /* bridge */ /* synthetic */ zzhc zzb(zzfz zzfzVar, String str, Executor executor, zzed zzedVar, int i) {
        zzqr zza2;
        if (zzfzVar.zzg()) {
            zza2 = zzqr.zzb();
        } else {
            zza2 = zzqr.zza();
        }
        return new zzgo(str, zzof.zzf(zzfzVar.zza()), zzhj.zzd(zzfzVar.zzd(), zza2), executor, zzedVar, zzfzVar.zzb(), zzfzVar.zze() ? zzia.zzd() : zzia.zzc());
    }
}
