package com.google.android.gms.internal.recaptcha;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzuv extends zzrg<zzuv, zzus> implements zzso {
    private static final zzrm<Integer, zzvj> zzb = new zzur();
    private static final zzuv zze;
    private static volatile zzsu<zzuv> zzf;
    private zzuu zzh;
    private int zzk;
    private zzvf zzm;
    private zzwg zzn;
    private String zzg = "";
    private zzro<String> zzi = zzrg.zzz();
    private zzrl zzj = zzy();
    private String zzl = "";

    static {
        zzuv zzuvVar = new zzuv();
        zze = zzuvVar;
        zzrg.zzD(zzuv.class, zzuvVar);
    }

    private zzuv() {
    }

    public static zzuv zze() {
        return zze;
    }

    public final zzvf zzf() {
        zzvf zzvfVar = this.zzm;
        return zzvfVar == null ? zzvf.zzk() : zzvfVar;
    }

    public final String zzg() {
        return this.zzg;
    }

    public final String zzi() {
        return this.zzl;
    }

    public final List<String> zzj() {
        return this.zzi;
    }

    public final List<zzvj> zzk() {
        return new zzrn(this.zzj, zzb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zze, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0002\u0000\u0001Ȉ\u0002\t\u0003Ț\u0004,\u0005\f\u0006Ȉ\u0007\t\b\t", new Object[]{"zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn"});
        }
        if (i2 == 3) {
            return new zzuv();
        }
        zzuo zzuoVar = null;
        if (i2 == 4) {
            return new zzus(zzuoVar);
        }
        if (i2 == 5) {
            return zze;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzuv> zzsuVar = zzf;
        if (zzsuVar == null) {
            synchronized (zzuv.class) {
                zzsuVar = zzf;
                if (zzsuVar == null) {
                    zzsuVar = new zzrc<>(zze);
                    zzf = zzsuVar;
                }
            }
        }
        return zzsuVar;
    }
}
