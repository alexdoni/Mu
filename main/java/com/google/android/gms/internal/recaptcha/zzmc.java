package com.google.android.gms.internal.recaptcha;

import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzmc {
    final int zza;
    final int zzb;
    final int zzc;
    final int zzd;
    private final String zze;
    private final char[] zzf;
    private final byte[] zzg;

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzmc) {
            return Arrays.equals(this.zzf, ((zzmc) obj).zzf);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzf);
    }

    public final String toString() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char zza(int i) {
        return this.zzf[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final zzmc zzb() {
        boolean z;
        int i = 0;
        for (char c : this.zzf) {
            if (zziv.zza(c)) {
                char[] cArr = this.zzf;
                int length = cArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = false;
                        break;
                    }
                    char c2 = cArr[i2];
                    if (c2 >= 'a' && c2 <= 'z') {
                        z = true;
                        break;
                    }
                    i2++;
                }
                zzjn.zzj(!z, "Cannot call lowerCase() on a mixed-case alphabet");
                char[] cArr2 = new char[this.zzf.length];
                while (true) {
                    char[] cArr3 = this.zzf;
                    if (i < cArr3.length) {
                        char c3 = cArr3[i];
                        if (zziv.zza(c3)) {
                            c3 ^= 32;
                        }
                        cArr2[i] = (char) c3;
                        i++;
                    } else {
                        return new zzmc(String.valueOf(this.zze).concat(".lowerCase()"), cArr2);
                    }
                }
            }
        }
        return this;
    }

    public final boolean zzc(char c) {
        return c < 128 && this.zzg[c] != -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmc(String str, char[] cArr) {
        str.getClass();
        this.zze = str;
        cArr.getClass();
        this.zzf = cArr;
        try {
            int length = cArr.length;
            int zzb = zzmk.zzb(length, RoundingMode.UNNECESSARY);
            this.zzb = zzb;
            int min = Math.min(8, Integer.lowestOneBit(zzb));
            try {
                this.zzc = 8 / min;
                this.zzd = zzb / min;
                this.zza = length - 1;
                byte[] bArr = new byte[128];
                Arrays.fill(bArr, (byte) -1);
                int i = 0;
                while (true) {
                    boolean z = true;
                    if (i >= cArr.length) {
                        break;
                    }
                    char c = cArr[i];
                    zzjn.zzf(c < 128, "Non-ASCII character: %s", c);
                    if (bArr[c] != -1) {
                        z = false;
                    }
                    zzjn.zzf(z, "Duplicate character: %s", c);
                    bArr[c] = (byte) i;
                    i++;
                }
                this.zzg = bArr;
                boolean[] zArr = new boolean[this.zzc];
                for (int i2 = 0; i2 < this.zzd; i2++) {
                    zArr[zzmk.zza(i2 * 8, this.zzb, RoundingMode.CEILING)] = true;
                }
            } catch (ArithmeticException e) {
                String str2 = new String(cArr);
                throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e);
            }
        } catch (ArithmeticException e2) {
            int length2 = cArr.length;
            StringBuilder sb = new StringBuilder(35);
            sb.append("Illegal alphabet length ");
            sb.append(length2);
            throw new IllegalArgumentException(sb.toString(), e2);
        }
    }
}
