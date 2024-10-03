package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzuq extends zzrg<zzuq, zzup> implements zzso {
    private static final zzuq zzb;
    private static volatile zzsu<zzuq> zze;
    private Object zzg;
    private boolean zzi;
    private zzwc zzj;
    private int zzf = 0;
    private zzpy zzh = zzpy.zzb;

    static {
        zzuq zzuqVar = new zzuq();
        zzb = zzuqVar;
        zzrg.zzD(zzuq.class, zzuqVar);
    }

    private zzuq() {
    }

    public static zzup zzd() {
        return zzb.zzu();
    }

    public static /* synthetic */ void zzf(zzuq zzuqVar, zzux zzuxVar) {
        zzuxVar.getClass();
        zzuqVar.zzg = zzuxVar;
        zzuqVar.zzf = 2;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȼ\u0000\u0002<\u0000\u0003\n\u0004\u0007\u0005\t", new Object[]{"zzg", "zzf", zzux.class, "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzuq();
        }
        if (i2 == 4) {
            return new zzup(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzuq> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzuq.class) {
                zzsuVar = zze;
                if (zzsuVar == null) {
                    zzsuVar = new zzrc<>(zzb);
                    zze = zzsuVar;
                }
            }
        }
        return zzsuVar;
    }
}
