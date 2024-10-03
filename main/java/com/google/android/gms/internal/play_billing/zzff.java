package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzff extends zzcb implements zzdg {
    private static final zzff zzb;
    private int zzd;
    private int zze;

    static {
        zzff zzffVar = new zzff();
        zzb = zzffVar;
        zzcb.zzo(zzff.class, zzffVar);
    }

    private zzff() {
    }

    public static zzfe zzu() {
        return (zzfe) zzb.zze();
    }

    public static /* synthetic */ void zzw(zzff zzffVar, int i) {
        zzffVar.zze = i - 1;
        zzffVar.zzd |= 1;
    }

    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzt(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzl(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဌ\u0000", new Object[]{"zzd", "zze", zzfc.zza});
        }
        if (i2 == 3) {
            return new zzff();
        }
        if (i2 == 4) {
            return new zzfe(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
