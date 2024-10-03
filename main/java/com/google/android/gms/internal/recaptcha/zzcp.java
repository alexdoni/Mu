package com.google.android.gms.internal.recaptcha;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzcp {
    private static final String zza = zzcn.zza(4);
    private static zzcp zzb;
    private final zzcq zzc;
    private final zzci zzd;
    private zzkn<zzvj> zze = zzkn.zzl();

    zzcp(zzcq zzcqVar, zzci zzciVar) {
        this.zzd = zzciVar;
        this.zzc = zzcqVar;
    }

    public static zzcp zzb() {
        if (zzb == null) {
            zzb = new zzcp(new zzcq(), new zzci());
        }
        return zzb;
    }

    public final zzcl zza(Context context) {
        zzkk zzkkVar = new zzkk();
        zzla<zzvj> it = this.zze.iterator();
        while (it.hasNext()) {
            zzvj next = it.next();
            try {
                zzkkVar.zza(Integer.valueOf(next.zza()), zzcq.zza(next, context, this.zzd));
            } catch (zzcj | zzcm e) {
                zzak.zza(zza, e);
            }
        }
        return zzcl.zzc(zzkkVar.zzb(), this.zzd.zze());
    }

    public final void zzc(zzck zzckVar) {
        this.zze = zzkn.zzk(zzckVar.zza());
        this.zzd.zzf(zzckVar.zzb());
    }
}
