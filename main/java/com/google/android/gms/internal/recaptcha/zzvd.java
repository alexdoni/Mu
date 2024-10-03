package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvd extends zzrg<zzvd, zzvc> implements zzso {
    private static final zzvd zzb;
    private static volatile zzsu<zzvd> zze;
    private int zzg;
    private zzwg zzi;
    private String zzf = "";
    private String zzh = "";

    static {
        zzvd zzvdVar = new zzvd();
        zzb = zzvdVar;
        zzrg.zzD(zzvd.class, zzvdVar);
    }

    private zzvd() {
    }

    public static zzvd zze() {
        return zzb;
    }

    public final String zzf() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003Ȉ\u0004\t", new Object[]{"zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzvd();
        }
        zzuo zzuoVar = null;
        if (i2 == 4) {
            return new zzvc(zzuoVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvd> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvd.class) {
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
