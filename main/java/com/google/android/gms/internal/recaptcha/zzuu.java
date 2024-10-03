package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzuu extends zzrg<zzuu, zzut> implements zzso {
    private static final zzuu zzb;
    private static volatile zzsu<zzuu> zze;
    private zzpy zzf = zzpy.zzb;
    private zzpy zzg = zzpy.zzb;

    static {
        zzuu zzuuVar = new zzuu();
        zzb = zzuuVar;
        zzrg.zzD(zzuu.class, zzuuVar);
    }

    private zzuu() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\n\u0002\n", new Object[]{"zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzuu();
        }
        zzuo zzuoVar = null;
        if (i2 == 4) {
            return new zzut(zzuoVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzuu> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzuu.class) {
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
