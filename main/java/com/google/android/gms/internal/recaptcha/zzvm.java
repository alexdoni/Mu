package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvm extends zzrg<zzvm, zzvl> implements zzso {
    private static final zzvm zzb;
    private static volatile zzsu<zzvm> zze;
    private String zzf = "";

    static {
        zzvm zzvmVar = new zzvm();
        zzb = zzvmVar;
        zzrg.zzD(zzvm.class, zzvmVar);
    }

    private zzvm() {
    }

    public static zzvl zzd() {
        return zzb.zzu();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzf"});
        }
        if (i2 == 3) {
            return new zzvm();
        }
        if (i2 == 4) {
            return new zzvl(null);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvm> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvm.class) {
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
