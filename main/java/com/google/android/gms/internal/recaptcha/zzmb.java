package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzmb extends zzrg<zzmb, zzma> implements zzso {
    private static final zzmb zzb;
    private static volatile zzsu<zzmb> zze;
    private int zzf;
    private String zzg = "";

    static {
        zzmb zzmbVar = new zzmb();
        zzb = zzmbVar;
        zzrg.zzD(zzmb.class, zzmbVar);
    }

    private zzmb() {
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0001\u0001\u0000\u0001\u0007\u0007\u0001\u0000\u0000\u0000\u0007ဈ\u0000", new Object[]{"zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzmb();
        }
        if (i2 == 4) {
            return new zzma(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzmb> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzmb.class) {
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
