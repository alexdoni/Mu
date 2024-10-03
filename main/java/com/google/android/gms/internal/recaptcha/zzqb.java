package com.google.android.gms.internal.recaptcha;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-recaptcha@@16.0.1 */
/* loaded from: classes2.dex */
final class zzqb extends zzqc {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzqb(InputStream inputStream, int i, zzpz zzpzVar) {
        super(null);
        this.zzl = Integer.MAX_VALUE;
        zzrp.zzf(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    private final List<byte[]> zzI(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int min = Math.min(i, 4096);
            byte[] bArr = new byte[min];
            int i2 = 0;
            while (i2 < min) {
                int read = this.zze.read(bArr, i2, min - i2);
                if (read == -1) {
                    throw zzrr.zzj();
                }
                this.zzk += read;
                i2 += read;
            }
            i -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzJ() {
        int i = this.zzg + this.zzh;
        this.zzg = i;
        int i2 = this.zzk + i;
        int i3 = this.zzl;
        if (i2 <= i3) {
            this.zzh = 0;
            return;
        }
        int i4 = i2 - i3;
        this.zzh = i4;
        this.zzg = i - i4;
    }

    private final void zzK(int i) throws IOException {
        if (zzL(i)) {
            return;
        }
        if (i > (Integer.MAX_VALUE - this.zzk) - this.zzi) {
            throw zzrr.zzi();
        }
        throw zzrr.zzj();
    }

    private final boolean zzL(int i) throws IOException {
        int i2 = this.zzi;
        int i3 = this.zzg;
        if (i2 + i > i3) {
            int i4 = this.zzk;
            if (i > (Integer.MAX_VALUE - i4) - i2 || i4 + i2 + i > this.zzl) {
                return false;
            }
            if (i2 > 0) {
                if (i3 > i2) {
                    byte[] bArr = this.zzf;
                    System.arraycopy(bArr, i2, bArr, 0, i3 - i2);
                }
                i4 = this.zzk + i2;
                this.zzk = i4;
                i3 = this.zzg - i2;
                this.zzg = i3;
                this.zzi = 0;
            }
            try {
                int read = this.zze.read(this.zzf, i3, Math.min(4096 - i3, (Integer.MAX_VALUE - i4) - i3));
                if (read == 0 || read < -1 || read > 4096) {
                    String valueOf = String.valueOf(this.zze.getClass());
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 91);
                    sb.append(valueOf);
                    sb.append("#read(byte[]) returned invalid result: ");
                    sb.append(read);
                    sb.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(sb.toString());
                }
                if (read <= 0) {
                    return false;
                }
                this.zzg += read;
                zzJ();
                if (this.zzg >= i) {
                    return true;
                }
                return zzL(i);
            } catch (zzrr e) {
                e.zzk();
                throw e;
            }
        }
        StringBuilder sb2 = new StringBuilder(77);
        sb2.append("refillBuffer() called when ");
        sb2.append(i);
        sb2.append(" bytes were already available in buffer");
        throw new IllegalStateException(sb2.toString());
    }

    private final byte[] zzM(int i, boolean z) throws IOException {
        byte[] zzN = zzN(i);
        if (zzN != null) {
            return zzN;
        }
        int i2 = this.zzi;
        int i3 = this.zzg;
        int i4 = i3 - i2;
        this.zzk += i3;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzI = zzI(i - i4);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, i2, bArr, 0, i4);
        for (byte[] bArr2 : zzI) {
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i4, length);
            i4 += length;
        }
        return bArr;
    }

    private final byte[] zzN(int i) throws IOException {
        if (i == 0) {
            return zzrp.zzc;
        }
        if (i >= 0) {
            int i2 = this.zzk;
            int i3 = this.zzi;
            int i4 = i2 + i3 + i;
            if ((-2147483647) + i4 <= 0) {
                int i5 = this.zzl;
                if (i4 > i5) {
                    zzB((i5 - i2) - i3);
                    throw zzrr.zzj();
                }
                int i6 = this.zzg - i3;
                int i7 = i - i6;
                if (i7 >= 4096) {
                    try {
                        if (i7 > this.zze.available()) {
                            return null;
                        }
                    } catch (zzrr e) {
                        e.zzk();
                        throw e;
                    }
                }
                byte[] bArr = new byte[i];
                System.arraycopy(this.zzf, this.zzi, bArr, 0, i6);
                this.zzk += this.zzg;
                this.zzi = 0;
                this.zzg = 0;
                while (i6 < i) {
                    try {
                        int read = this.zze.read(bArr, i6, i - i6);
                        if (read == -1) {
                            throw zzrr.zzj();
                        }
                        this.zzk += read;
                        i6 += read;
                    } catch (zzrr e2) {
                        e2.zzk();
                        throw e2;
                    }
                }
                return bArr;
            }
            throw zzrr.zzi();
        }
        throw zzrr.zzf();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final void zzA(int i) {
        this.zzl = i;
        zzJ();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final boolean zzC() throws IOException {
        return this.zzi == this.zzg && !zzL(1);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        if (this.zzi == this.zzg) {
            zzK(1);
        }
        byte[] bArr = this.zzf;
        int i = this.zzi;
        this.zzi = i + 1;
        return bArr[i];
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzd() {
        return this.zzk + this.zzi;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zze(int i) throws zzrr {
        if (i >= 0) {
            int i2 = i + this.zzk + this.zzi;
            int i3 = this.zzl;
            if (i2 <= i3) {
                this.zzl = i2;
                zzJ();
                return i3;
            }
            throw zzrr.zzj();
        }
        throw zzrr.zzf();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzf() throws IOException {
        return zzj();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzg() throws IOException {
        return zzi();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzh() throws IOException {
        return zzj();
    }

    public final int zzi() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 4) {
            zzK(4);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzk() throws IOException {
        return zzi();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzl() throws IOException {
        return zzF(zzj());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzm() throws IOException {
        if (zzC()) {
            this.zzj = 0;
            return 0;
        }
        int zzj = zzj();
        this.zzj = zzj;
        if ((zzj >>> 3) != 0) {
            return zzj;
        }
        throw zzrr.zzc();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final int zzn() throws IOException {
        return zzj();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final long zzo() throws IOException {
        return zzq();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final long zzp() throws IOException {
        return zzr();
    }

    public final long zzq() throws IOException {
        int i = this.zzi;
        if (this.zzg - i < 8) {
            zzK(8);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 8;
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            j |= (r3 & Byte.MAX_VALUE) << i;
            if ((zza() & 128) == 0) {
                return j;
            }
        }
        throw zzrr.zze();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final long zzt() throws IOException {
        return zzq();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final long zzu() throws IOException {
        return zzG(zzr());
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final long zzv() throws IOException {
        return zzr();
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final zzpy zzw() throws IOException {
        int zzj = zzj();
        int i = this.zzg;
        int i2 = this.zzi;
        if (zzj <= i - i2 && zzj > 0) {
            zzpy zzo = zzpy.zzo(this.zzf, i2, zzj);
            this.zzi += zzj;
            return zzo;
        }
        if (zzj != 0) {
            byte[] zzN = zzN(zzj);
            if (zzN != null) {
                return zzpy.zzn(zzN);
            }
            int i3 = this.zzi;
            int i4 = this.zzg;
            int i5 = i4 - i3;
            this.zzk += i4;
            this.zzi = 0;
            this.zzg = 0;
            List<byte[]> zzI = zzI(zzj - i5);
            byte[] bArr = new byte[zzj];
            System.arraycopy(this.zzf, i3, bArr, 0, i5);
            for (byte[] bArr2 : zzI) {
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, i5, length);
                i5 += length;
            }
            return zzpy.zzq(bArr);
        }
        return zzpy.zzb;
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final String zzx() throws IOException {
        int zzj = zzj();
        if (zzj > 0) {
            int i = this.zzg;
            int i2 = this.zzi;
            if (zzj <= i - i2) {
                String str = new String(this.zzf, i2, zzj, zzrp.zza);
                this.zzi += zzj;
                return str;
            }
        }
        if (zzj == 0) {
            return "";
        }
        if (zzj <= this.zzg) {
            zzK(zzj);
            String str2 = new String(this.zzf, this.zzi, zzj, zzrp.zza);
            this.zzi += zzj;
            return str2;
        }
        return new String(zzM(zzj, false), zzrp.zza);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final String zzy() throws IOException {
        byte[] zzM;
        int zzj = zzj();
        int i = this.zzi;
        int i2 = this.zzg;
        if (zzj <= i2 - i && zzj > 0) {
            zzM = this.zzf;
            this.zzi = i + zzj;
        } else {
            if (zzj == 0) {
                return "";
            }
            i = 0;
            if (zzj <= i2) {
                zzK(zzj);
                zzM = this.zzf;
                this.zzi = zzj;
            } else {
                zzM = zzM(zzj, false);
            }
        }
        return zzug.zzd(zzM, i, zzj);
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final void zzz(int i) throws zzrr {
        if (this.zzj != i) {
            throw zzrr.zzb();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        if (r2[r3] >= 0) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzj() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 != r0) goto L7
            goto L6c
        L7:
            byte[] r2 = r5.zzf
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r5.zzi = r3
            return r0
        L12:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6c
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L23
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L69
        L23:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L30
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2e:
            r1 = r3
            goto L69
        L30:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3e
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L69
        L3e:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2e
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L69
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2e
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L69
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2e
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6c
        L69:
            r5.zzi = r1
            return r0
        L6c:
            long r0 = r5.zzs()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.recaptcha.zzqb.zzj():int");
    }

    @Override // com.google.android.gms.internal.recaptcha.zzqc
    public final boolean zzE(int i) throws IOException {
        int zzm;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzg - this.zzi < 10) {
                while (i3 < 10) {
                    if (zza() < 0) {
                        i3++;
                    }
                }
                throw zzrr.zze();
            }
            while (i3 < 10) {
                byte[] bArr = this.zzf;
                int i4 = this.zzi;
                this.zzi = i4 + 1;
                if (bArr[i4] < 0) {
                    i3++;
                }
            }
            throw zzrr.zze();
            return true;
        }
        if (i2 == 1) {
            zzB(8);
            return true;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    return false;
                }
                if (i2 == 5) {
                    zzB(4);
                    return true;
                }
                throw zzrr.zza();
            }
            do {
                zzm = zzm();
                if (zzm == 0) {
                    break;
                }
            } while (zzE(zzm));
            zzz(((i >>> 3) << 3) | 4);
            return true;
        }
        zzB(zzj());
        return true;
    }

    public final void zzB(int i) throws IOException {
        int i2 = this.zzg;
        int i3 = this.zzi;
        int i4 = i2 - i3;
        if (i <= i4 && i >= 0) {
            this.zzi = i3 + i;
            return;
        }
        if (i >= 0) {
            int i5 = this.zzk;
            int i6 = i5 + i3;
            int i7 = this.zzl;
            if (i6 + i > i7) {
                zzB((i7 - i5) - i3);
                throw zzrr.zzj();
            }
            this.zzk = i6;
            this.zzg = 0;
            this.zzi = 0;
            while (i4 < i) {
                try {
                    long j = i - i4;
                    try {
                        long skip = this.zze.skip(j);
                        if (skip < 0 || skip > j) {
                            String valueOf = String.valueOf(this.zze.getClass());
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 92);
                            sb.append(valueOf);
                            sb.append("#skip returned invalid result: ");
                            sb.append(skip);
                            sb.append("\nThe InputStream implementation is buggy.");
                            throw new IllegalStateException(sb.toString());
                        }
                        if (skip == 0) {
                            break;
                        } else {
                            i4 += (int) skip;
                        }
                    } catch (zzrr e) {
                        e.zzk();
                        throw e;
                    }
                } finally {
                    this.zzk += i4;
                    zzJ();
                }
            }
            if (i4 >= i) {
                return;
            }
            int i8 = this.zzg;
            int i9 = i8 - this.zzi;
            this.zzi = i8;
            zzK(1);
            while (true) {
                int i10 = i - i9;
                int i11 = this.zzg;
                if (i10 <= i11) {
                    this.zzi = i10;
                    return;
                } else {
                    i9 += i11;
                    this.zzi = i11;
                    zzK(1);
                }
            }
        } else {
            throw zzrr.zzf();
        }
    }

    public final long zzr() throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        int i;
        int i2 = this.zzi;
        int i3 = this.zzg;
        if (i3 != i2) {
            byte[] bArr = this.zzf;
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b >= 0) {
                this.zzi = i4;
                return b;
            }
            if (i3 - i4 >= 9) {
                int i5 = i4 + 1;
                int i6 = b ^ (bArr[i4] << 7);
                if (i6 >= 0) {
                    int i7 = i5 + 1;
                    int i8 = i6 ^ (bArr[i5] << Ascii.f556SO);
                    if (i8 >= 0) {
                        j = i8 ^ 16256;
                    } else {
                        i5 = i7 + 1;
                        int i9 = i8 ^ (bArr[i7] << Ascii.NAK);
                        if (i9 < 0) {
                            i = i9 ^ (-2080896);
                        } else {
                            i7 = i5 + 1;
                            long j5 = (bArr[i5] << 28) ^ i9;
                            if (j5 < 0) {
                                int i10 = i7 + 1;
                                long j6 = j5 ^ (bArr[i7] << 35);
                                if (j6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    i7 = i10 + 1;
                                    j5 = j6 ^ (bArr[i10] << 42);
                                    if (j5 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        i10 = i7 + 1;
                                        j6 = j5 ^ (bArr[i7] << 49);
                                        if (j6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i7 = i10 + 1;
                                            j = (j6 ^ (bArr[i10] << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                i10 = i7 + 1;
                                                if (bArr[i7] >= 0) {
                                                    j2 = j;
                                                    i5 = i10;
                                                    this.zzi = i5;
                                                    return j2;
                                                }
                                            }
                                        }
                                    }
                                }
                                j2 = j3 ^ j6;
                                i5 = i10;
                                this.zzi = i5;
                                return j2;
                            }
                            j4 = 266354560;
                            j = j5 ^ j4;
                        }
                    }
                    i5 = i7;
                    j2 = j;
                    this.zzi = i5;
                    return j2;
                }
                i = i6 ^ (-128);
                j2 = i;
                this.zzi = i5;
                return j2;
            }
        }
        return zzs();
    }
}
