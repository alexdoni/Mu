package com.google.android.gms.internal.recaptcha;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzqr {
    static final zzqr zza = new zzqr(true);
    private static volatile boolean zzb = false;
    private static volatile zzqr zzc;
    private static volatile zzqr zzd;
    private final Map<zzqq, zzre<?, ?>> zze;

    zzqr() {
        this.zze = new HashMap();
    }

    public static zzqr zza() {
        zzqr zzqrVar = zzc;
        if (zzqrVar == null) {
            synchronized (zzqr.class) {
                zzqrVar = zzc;
                if (zzqrVar == null) {
                    zzqrVar = zza;
                    zzc = zzqrVar;
                }
            }
        }
        return zzqrVar;
    }

    public final <ContainingType extends zzsn> zzre<ContainingType, ?> zzc(ContainingType containingtype, int i) {
        return (zzre) this.zze.get(new zzqq(containingtype, i));
    }

    zzqr(boolean z) {
        this.zze = Collections.emptyMap();
    }

    public static zzqr zzb() {
        zzqr zzqrVar = zzd;
        if (zzqrVar != null) {
            return zzqrVar;
        }
        synchronized (zzqr.class) {
            zzqr zzqrVar2 = zzd;
            if (zzqrVar2 != null) {
                return zzqrVar2;
            }
            zzqr zzb2 = zzqz.zzb(zzqr.class);
            zzd = zzb2;
            return zzb2;
        }
    }
}
