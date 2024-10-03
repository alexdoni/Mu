package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzqo extends zzrg<zzqo, zzqn> implements zzso {
    private static final zzqo zzb;
    private static volatile zzsu<zzqo> zze;
    private long zzf;
    private int zzg;

    static {
        zzqo zzqoVar = new zzqo();
        zzb = zzqoVar;
        zzrg.zzD(zzqo.class, zzqoVar);
    }

    private zzqo() {
    }

    public static zzqn zzf() {
        return zzb.zzu();
    }

    public final int zzd() {
        return this.zzg;
    }

    public final long zze() {
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
            return new zzsy(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzqo();
        }
        zzqm zzqmVar = null;
        if (i2 == 4) {
            return new zzqn(zzqmVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzqo> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzqo.class) {
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
