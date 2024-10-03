package com.google.android.gms.internal.recaptcha;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzvs extends zzrg<zzvs, zzvr> implements zzso {
    private static final zzvs zzb;
    private static volatile zzsu<zzvs> zze;
    private int zzf;
    private zzvu zzh;
    private String zzg = "";
    private String zzi = "";
    private String zzj = "";

    static {
        zzvs zzvsVar = new zzvs();
        zzb = zzvsVar;
        zzrg.zzD(zzvs.class, zzvsVar);
    }

    private zzvs() {
    }

    public static zzvs zze() {
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
        return this.zzi;
    }

    public final String zzj() {
        return this.zzg;
    }

    public final String zzk() {
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.recaptcha.zzrg
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005\t", new Object[]{"zzf", "zzg", "zzi", "zzj", "zzh"});
        }
        if (i2 == 3) {
            return new zzvs();
        }
        zzvk zzvkVar = null;
        if (i2 == 4) {
            return new zzvr(zzvkVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        if (i2 != 6) {
            return null;
        }
        zzsu<zzvs> zzsuVar = zze;
        if (zzsuVar == null) {
            synchronized (zzvs.class) {
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
