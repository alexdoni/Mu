package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvh extends zzrg<zzvh, zzvg> implements zzso {
    private static final zzvh zzb;
    private static volatile zzsu<zzvh> zze;
    private int zzg;
    private int zzh;
    private zzpy zzf = zzpy.zzb;
    private zzpy zzi = zzpy.zzb;

    static {
        zzvh zzvhVar = new zzvh();
        zzb = zzvhVar;
        zzrg.zzD(zzvh.class, zzvhVar);
    }

    private zzvh() {
    }

    public static zzvg zzd() {
        return zzb.zzu();
    }

    public static zzvh zzf() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\n\u0002\u0004\u0003\u0004\u0004\n", new Object[]{"zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzvh();
        }
        zzuo zzuoVar = null;
        if (i2 == 4) {
            return new zzvg(zzuoVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvh> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvh.class) {
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
