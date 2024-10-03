package com.google.android.gms.internal.recaptcha;

import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
public final class zzts {
    private static final zzts zza = new zzts(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;
    private boolean zzf;

    private zzts() {
        this(0, new int[8], new Object[8], true);
    }

    private zzts(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = i;
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzf = z;
    }

    public static zzts zzc() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzts zzd(zzts zztsVar, zzts zztsVar2) {
        int i = zztsVar.zzb + zztsVar2.zzb;
        int[] copyOf = Arrays.copyOf(zztsVar.zzc, i);
        System.arraycopy(zztsVar2.zzc, 0, copyOf, zztsVar.zzb, zztsVar2.zzb);
        Object[] copyOf2 = Arrays.copyOf(zztsVar.zzd, i);
        System.arraycopy(zztsVar2.zzd, 0, copyOf2, zztsVar.zzb, zztsVar2.zzb);
        return new zzts(i, copyOf, copyOf2, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzts zze() {
        return new zzts(0, new int[8], new Object[8], true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzts)) {
            return false;
        }
        zzts zztsVar = (zzts) obj;
        int i = this.zzb;
        if (i == zztsVar.zzb) {
            int[] iArr = this.zzc;
            int[] iArr2 = zztsVar.zzc;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    Object[] objArr = this.zzd;
                    Object[] objArr2 = zztsVar.zzd;
                    int i3 = this.zzb;
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (objArr[i4].equals(objArr2[i4])) {
                        }
                    }
                    return true;
                }
                if (iArr[i2] != iArr2[i2]) {
                    break;
                }
                i2++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.zzb;
        int i2 = (i + 527) * 31;
        int[] iArr = this.zzc;
        int i3 = 17;
        int i4 = 17;
        for (int i5 = 0; i5 < i; i5++) {
            i4 = (i4 * 31) + iArr[i5];
        }
        int i6 = (i2 + i4) * 31;
        Object[] objArr = this.zzd;
        int i7 = this.zzb;
        for (int i8 = 0; i8 < i7; i8++) {
            i3 = (i3 * 31) + objArr[i8].hashCode();
        }
        return i6 + i3;
    }

    public final int zza() {
        int zzK;
        int zzL;
        int i;
        int i2 = this.zze;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzb; i4++) {
            int i5 = this.zzc[i4];
            int i6 = i5 >>> 3;
            int i7 = i5 & 7;
            if (i7 != 0) {
                if (i7 == 1) {
                    ((Long) this.zzd[i4]).longValue();
                    i = zzqj.zzK(i6 << 3) + 8;
                } else if (i7 == 2) {
                    zzpy zzpyVar = (zzpy) this.zzd[i4];
                    int zzK2 = zzqj.zzK(i6 << 3);
                    int zzd = zzpyVar.zzd();
                    i3 += zzK2 + zzqj.zzK(zzd) + zzd;
                } else if (i7 == 3) {
                    int zzJ = zzqj.zzJ(i6);
                    zzK = zzJ + zzJ;
                    zzL = ((zzts) this.zzd[i4]).zza();
                } else if (i7 == 5) {
                    ((Integer) this.zzd[i4]).intValue();
                    i = zzqj.zzK(i6 << 3) + 4;
                } else {
                    throw new IllegalStateException(zzrr.zza());
                }
                i3 += i;
            } else {
                long longValue = ((Long) this.zzd[i4]).longValue();
                zzK = zzqj.zzK(i6 << 3);
                zzL = zzqj.zzL(longValue);
            }
            i = zzK + zzL;
            i3 += i;
        }
        this.zze = i3;
        return i3;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzb; i3++) {
            int i4 = this.zzc[i3];
            zzpy zzpyVar = (zzpy) this.zzd[i3];
            int zzK = zzqj.zzK(8);
            int zzd = zzpyVar.zzd();
            i2 += zzK + zzK + zzqj.zzK(16) + zzqj.zzK(i4 >>> 3) + zzqj.zzK(24) + zzqj.zzK(zzd) + zzd;
        }
        this.zze = i2;
        return i2;
    }

    public final void zzf() {
        this.zzf = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < this.zzb; i2++) {
            zzsp.zzb(sb, i, String.valueOf(this.zzc[i2] >>> 3), this.zzd[i2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(int i, Object obj) {
        if (this.zzf) {
            int i2 = this.zzb;
            int[] iArr = this.zzc;
            if (i2 == iArr.length) {
                int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
                this.zzc = Arrays.copyOf(iArr, i3);
                this.zzd = Arrays.copyOf(this.zzd, i3);
            }
            int[] iArr2 = this.zzc;
            int i4 = this.zzb;
            iArr2[i4] = i;
            this.zzd[i4] = obj;
            this.zzb = i4 + 1;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzi(zzuj zzujVar) throws IOException {
        if (this.zzb != 0) {
            for (int i = 0; i < this.zzb; i++) {
                int i2 = this.zzc[i];
                Object obj = this.zzd[i];
                int i3 = i2 >>> 3;
                int i4 = i2 & 7;
                if (i4 == 0) {
                    zzujVar.zzt(i3, ((Long) obj).longValue());
                } else if (i4 == 1) {
                    zzujVar.zzm(i3, ((Long) obj).longValue());
                } else if (i4 == 2) {
                    zzujVar.zzd(i3, (zzpy) obj);
                } else if (i4 == 3) {
                    zzujVar.zzF(i3);
                    ((zzts) obj).zzi(zzujVar);
                    zzujVar.zzh(i3);
                } else if (i4 == 5) {
                    zzujVar.zzk(i3, ((Integer) obj).intValue());
                } else {
                    throw new RuntimeException(zzrr.zza());
                }
            }
        }
    }
}
