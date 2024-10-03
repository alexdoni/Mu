package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzwc extends zzrg<zzwc, zzwb> implements zzso {
    private static final zzwc zzb;
    private static volatile zzsu<zzwc> zze;
    private int zzf;
    private zzwi zzg;
    private boolean zzj;
    private zzro<zzwe> zzh = zzz();
    private int zzi = 2;
    private zzro<zzpy> zzk = zzz();

    static {
        zzwc zzwcVar = new zzwc();
        zzb = zzwcVar;
        zzrg.zzD(zzwc.class, zzwcVar);
    }

    private zzwc() {
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0001\u0005\u0000\u0001\u0001\u0017\u0005\u0000\u0002\u0000\u0001ဉ\u0000\u0002\u001b\bဇ\u0002\t\u001c\u0017ဌ\u0001", new Object[]{"zzf", "zzg", "zzh", zzwe.class, "zzj", "zzk", "zzi", zzwa.zzb()});
        }
        if (i2 == 3) {
            return new zzwc();
        }
        if (i2 == 4) {
            return new zzwb(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzwc> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzwc.class) {
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
