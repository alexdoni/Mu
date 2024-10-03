package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzwg extends zzrg<zzwg, zzwf> implements zzso {
    private static final zzwg zzb;
    private static volatile zzsu<zzwg> zze;
    private int zzf;
    private int zzi;
    private zzpy zzg = zzpy.zzb;
    private zzpy zzh = zzpy.zzb;
    private zzpy zzj = zzpy.zzb;

    static {
        zzwg zzwgVar = new zzwg();
        zzb = zzwgVar;
        zzrg.zzD(zzwg.class, zzwgVar);
    }

    private zzwg() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0000\u0000\u0001ည\u0000\u0003ည\u0001\u0004ဋ\u0002\u0005ည\u0003", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzwg();
        }
        zzvx zzvxVar = null;
        if (i2 == 4) {
            return new zzwf(zzvxVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzwg> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzwg.class) {
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
