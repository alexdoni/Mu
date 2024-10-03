package com.google.android.gms.internal.recaptcha;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvf extends zzrg<zzvf, zzve> implements zzso {
    private static final zzvf zzb;
    private static volatile zzsu<zzvf> zze;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private zzro<zzpy> zzf = zzz();
    private zzpy zzk = zzpy.zzb;

    static {
        zzvf zzvfVar = new zzvf();
        zzb = zzvfVar;
        zzrg.zzD(zzvf.class, zzvfVar);
    }

    private zzvf() {
    }

    public static zzvf zzk() {
        return zzb;
    }

    public final int zzd() {
        return this.zzg;
    }

    public final int zze() {
        return this.zzh;
    }

    public final int zzf() {
        return this.zzi;
    }

    public final int zzg() {
        return this.zzj;
    }

    public final zzpy zzi() {
        return this.zzk;
    }

    public final List<zzpy> zzl() {
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
            return zzC(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0001\u0000\u0001\u001c\u0002\u0004\u0003\u0004\u0004\u0004\u0005\u0004\u0006\n", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        if (i2 == 3) {
            return new zzvf();
        }
        zzuo zzuoVar = null;
        if (i2 == 4) {
            return new zzve(zzuoVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvf> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvf.class) {
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
