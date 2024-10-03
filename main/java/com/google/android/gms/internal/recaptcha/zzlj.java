package com.google.android.gms.internal.recaptcha;

import org.spongycastle.asn1.cmc.BodyPartID;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzlj extends zzli {
    static final zzln zza = new zzlj();

    zzlj() {
    }

    private static long zzf(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    private static void zzg(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long zzb = zzlv.zzb(bArr, i);
        long zzb2 = zzlv.zzb(bArr, i + 8);
        long zzb3 = zzlv.zzb(bArr, i + 16);
        long zzb4 = zzlv.zzb(bArr, i + 24);
        long j3 = j + zzb;
        long rotateRight = Long.rotateRight(j2 + j3 + zzb4, 21);
        long j4 = zzb2 + j3 + zzb3;
        long rotateRight2 = Long.rotateRight(j4, 44);
        jArr[0] = j4 + zzb4;
        jArr[1] = rotateRight + rotateRight2 + j3;
    }

    public final String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    @Override // com.google.android.gms.internal.recaptcha.zzle
    public final zzlm zzb(byte[] bArr, int i, int i2) {
        long j;
        long zzb;
        long rotateRight;
        int i3;
        zzjn.zzh(0, i2, bArr.length);
        int i4 = 37;
        long j2 = -7286425919675154353L;
        if (i2 <= 32) {
            if (i2 > 16) {
                long j3 = (i2 + i2) - 7286425919675154353L;
                long zzb2 = zzlv.zzb(bArr, 0) * (-5435081209227447693L);
                long zzb3 = zzlv.zzb(bArr, 8);
                long zzb4 = zzlv.zzb(bArr, i2 - 8) * j3;
                j2 = zzf(Long.rotateRight(zzb2 + zzb3, 43) + Long.rotateRight(zzb4, 30) + (zzlv.zzb(bArr, i2 - 16) * (-7286425919675154353L)), zzb2 + Long.rotateRight(zzb3 - 7286425919675154353L, 18) + zzb4, j3);
            } else if (i2 >= 8) {
                long j4 = (i2 + i2) - 7286425919675154353L;
                long zzb5 = zzlv.zzb(bArr, 0) - 7286425919675154353L;
                long zzb6 = zzlv.zzb(bArr, i2 - 8);
                j2 = zzf((Long.rotateRight(zzb6, 37) * j4) + zzb5, (Long.rotateRight(zzb5, 25) + zzb6) * j4, j4);
            } else if (i2 >= 4) {
                j2 = zzf(i2 + ((zzlv.zza(bArr, 0) & BodyPartID.bodyIdMax) << 3), zzlv.zza(bArr, i2 - 4) & BodyPartID.bodyIdMax, (i2 + i2) - 7286425919675154353L);
            } else if (i2 > 0) {
                long j5 = (((bArr[0] & 255) + ((bArr[i2 >> 1] & 255) << 8)) * (-7286425919675154353L)) ^ ((i2 + ((bArr[i2 - 1] & 255) << 2)) * (-4348849565147123417L));
                j2 = (-7286425919675154353L) * (j5 ^ (j5 >>> 47));
            }
        } else if (i2 <= 64) {
            long j6 = (i2 + i2) - 7286425919675154353L;
            long zzb7 = zzlv.zzb(bArr, 0) * (-7286425919675154353L);
            long zzb8 = zzlv.zzb(bArr, 8);
            long zzb9 = zzlv.zzb(bArr, i2 - 8) * j6;
            long rotateRight2 = Long.rotateRight(zzb7 + zzb8, 43) + Long.rotateRight(zzb9, 30) + (zzlv.zzb(bArr, i2 - 16) * (-7286425919675154353L));
            long rotateRight3 = Long.rotateRight(zzb8 - 7286425919675154353L, 18);
            long zzb10 = zzlv.zzb(bArr, 16) * j6;
            long zzb11 = zzlv.zzb(bArr, 24);
            long zzb12 = (zzlv.zzb(bArr, i2 - 32) + rotateRight2) * j6;
            j2 = zzf(Long.rotateRight(zzb10 + zzb11, 43) + Long.rotateRight(zzb12, 30) + ((zzf(rotateRight2, rotateRight3 + zzb7 + zzb9, j6) + zzlv.zzb(bArr, i2 - 24)) * j6), zzb10 + Long.rotateRight(zzb11 + zzb7, 18) + zzb12, j6);
        } else {
            long[] jArr = new long[2];
            long[] jArr2 = new long[2];
            long zzb13 = zzlv.zzb(bArr, 0) + 95310865018149119L;
            int i5 = i2 - 1;
            int i6 = (i5 >> 6) * 64;
            int i7 = i5 & 63;
            int i8 = (i6 + i7) - 63;
            long j7 = 2480279821605975764L;
            long j8 = 1390051526045402406L;
            int i9 = 0;
            while (true) {
                long rotateRight4 = Long.rotateRight(zzb13 + j7 + jArr[0] + zzlv.zzb(bArr, i9 + 8), i4);
                long rotateRight5 = Long.rotateRight(j7 + jArr[1] + zzlv.zzb(bArr, i9 + 48), 42);
                j = (rotateRight4 * (-5435081209227447693L)) ^ jArr2[1];
                zzb = (rotateRight5 * (-5435081209227447693L)) + jArr[0] + zzlv.zzb(bArr, i9 + 40);
                rotateRight = Long.rotateRight(j8 + jArr2[0], 33) * (-5435081209227447693L);
                i3 = i7;
                zzg(bArr, i9, jArr[1] * (-5435081209227447693L), j + jArr2[0], jArr);
                zzg(bArr, i9 + 32, rotateRight + jArr2[1], zzb + zzlv.zzb(bArr, i9 + 16), jArr2);
                int i10 = i9 + 64;
                if (i10 == i6) {
                    break;
                }
                i9 = i10;
                i7 = i3;
                zzb13 = rotateRight;
                j8 = j;
                j7 = zzb;
                i4 = 37;
            }
            long j9 = j & 255;
            long j10 = (-5435081209227447693L) + j9 + j9;
            long j11 = jArr2[0] + i3;
            long j12 = jArr[0] + j11;
            jArr[0] = j12;
            jArr2[0] = j11 + j12;
            long rotateRight6 = Long.rotateRight(rotateRight + zzb + j12 + zzlv.zzb(bArr, i8 + 8), 37);
            long rotateRight7 = Long.rotateRight(zzb + jArr[1] + zzlv.zzb(bArr, i8 + 48), 42);
            long j13 = (rotateRight6 * j10) ^ (jArr2[1] * 9);
            long zzb14 = (rotateRight7 * j10) + (jArr[0] * 9) + zzlv.zzb(bArr, i8 + 40);
            long rotateRight8 = Long.rotateRight(j + jArr2[0], 33) * j10;
            zzg(bArr, i8, jArr[1] * j10, j13 + jArr2[0], jArr);
            zzg(bArr, i8 + 32, rotateRight8 + jArr2[1], zzb14 + zzlv.zzb(bArr, i8 + 16), jArr2);
            j2 = zzf(zzf(jArr[0], jArr2[0], j10) + ((zzb14 ^ (zzb14 >>> 47)) * (-4348849565147123417L)) + j13, zzf(jArr[1], jArr2[1], j10) + rotateRight8, j10);
        }
        return zzlm.zzh(j2);
    }
}
