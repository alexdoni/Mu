package com.google.android.gms.internal.recaptcha;

import android.content.Context;
import java.util.concurrent.Executors;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzca {
    private static volatile zzca zza;
    private final zzgb zzb;

    private zzca(Context context) {
        zzgc zzgcVar = new zzgc();
        zzq.zza();
        zzgcVar.zzc(Executors.unconfigurableExecutorService(Executors.newCachedThreadPool()));
        zzgcVar.zzd(new zzed(zzkj.zzp(zzeh.zzb(context).zzb())));
        zzgcVar.zzb(zzgo.zza());
        this.zzb = zzgcVar.zza();
    }

    public static zzgb zza(Context context) {
        return zzb(context).zzb;
    }

    private static synchronized zzca zzb(Context context) {
        zzca zzcaVar;
        synchronized (zzca.class) {
            if (zza == null) {
                zza = new zzca(context.getApplicationContext());
            }
            zzcaVar = zza;
        }
        return zzcaVar;
    }
}
