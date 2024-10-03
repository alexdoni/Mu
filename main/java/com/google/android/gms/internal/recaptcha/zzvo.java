package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvo extends zzrg<zzvo, zzvn> implements zzso {
    private static final zzvo zzb;
    private static volatile zzsu<zzvo> zze;
    private int zzf;
    private zzvu zzh;
    private String zzg = "";
    private String zzi = "";

    static {
        zzvo zzvoVar = new zzvo();
        zzb = zzvoVar;
        zzrg.zzD(zzvo.class, zzvoVar);
    }

    private zzvo() {
    }

    public static zzvo zze() {
        return zzb;
    }

    public final zzvu zzf() {
        zzvu zzvuVar = this.zzh;
        return zzvuVar == null ? zzvu.zzi() : zzvuVar;
    }

    public final zzvw zzg() {
        zzvw zzb2 = zzvw.zzb(this.zzf);
        return zzb2 == null ? zzvw.UNRECOGNIZED : zzb2;
    }

    public final String zzi() {
        return this.zzg;
    }

    public final String zzj() {
        return this.zzi;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0004\u0000\u0000\u0001\u0005\u0004\u0000\u0000\u0000\u0001\f\u0002Ȉ\u0003\t\u0005Ȉ", new Object[]{"zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzvo();
        }
        zzvk zzvkVar = null;
        if (i2 == 4) {
            return new zzvn(zzvkVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvo> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvo.class) {
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
