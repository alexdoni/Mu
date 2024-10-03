package com.google.android.gms.internal.recaptcha;

import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzty extends zzua {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzty(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(zzk(obj, j));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(zzj(obj, j));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final void zzc(Object obj, long j, boolean z) {
        if (zzub.zzb) {
            zzub.zzD(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzub.zzE(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final void zzd(Object obj, long j, byte b) {
        if (zzub.zzb) {
            zzub.zzD(obj, j, b);
        } else {
            zzub.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final void zze(Object obj, long j, double d) {
        zzo(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final void zzf(Object obj, long j, float f) {
        zzn(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.recaptcha.zzua
    public final boolean zzg(Object obj, long j) {
        if (zzub.zzb) {
            return zzub.zzt(obj, j);
        }
        return zzub.zzu(obj, j);
    }
}
