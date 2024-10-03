package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@5.2.1 */
/* loaded from: classes2.dex */
public final class zzfv extends zzcb implements zzdg {
    public static final /* synthetic */ int zzb = 0;
    private static final zzfv zzd;

    static {
        zzfv zzfvVar = new zzfv();
        zzd = zzfvVar;
        zzcb.zzo(zzfv.class, zzfvVar);
    }

    private zzfv() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.play_billing.zzcb
    public final Object zzt(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        zzft zzftVar = null;
        if (i2 == 2) {
            return zzl(zzd, "\u0001\u0000", null);
        }
        if (i2 == 3) {
            return new zzfv();
        }
        if (i2 == 4) {
            return new zzfu(zzftVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzd;
    }
}
