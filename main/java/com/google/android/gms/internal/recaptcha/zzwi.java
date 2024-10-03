package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzwi extends zzrg<zzwi, zzwh> implements zzso {
    private static final zzwi zzb;
    private static volatile zzsu<zzwi> zze;
    private int zzf;
    private String zzg = "";
    private String zzh = "";

    static {
        zzwi zzwiVar = new zzwi();
        zzb = zzwiVar;
        zzrg.zzD(zzwi.class, zzwiVar);
    }

    private zzwi() {
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzwi();
        }
        if (i2 == 4) {
            return new zzwh(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzwi> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzwi.class) {
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
