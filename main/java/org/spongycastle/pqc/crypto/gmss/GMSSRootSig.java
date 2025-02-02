package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class GMSSRootSig {
    private long big8;
    private int checksum;
    private int counter;
    private GMSSRandom gmssRandom;
    private byte[] hash;
    private int height;

    /* renamed from: ii */
    private int f2746ii;

    /* renamed from: k */
    private int f2747k;
    private int keysize;
    private int mdsize;
    private Digest messDigestOTS;
    private int messagesize;
    private byte[] privateKeyOTS;

    /* renamed from: r */
    private int f2748r;
    private byte[] seed;
    private byte[] sign;
    private int steps;
    private int test;
    private long test8;

    /* renamed from: w */
    private int f2749w;

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public GMSSRootSig(Digest digest, byte[][] bArr, int[] iArr) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.counter = iArr[0];
        this.test = iArr[1];
        this.f2746ii = iArr[2];
        this.f2748r = iArr[3];
        this.steps = iArr[4];
        this.keysize = iArr[5];
        this.height = iArr[6];
        this.f2749w = iArr[7];
        this.checksum = iArr[8];
        this.mdsize = this.messDigestOTS.getDigestSize();
        int i = this.f2749w;
        this.f2747k = (1 << i) - 1;
        this.messagesize = (int) Math.ceil((r10 << 3) / i);
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.hash = bArr[2];
        this.sign = bArr[3];
        byte[] bArr2 = bArr[4];
        this.test8 = ((bArr2[1] & 255) << 8) | (bArr2[0] & 255) | ((bArr2[2] & 255) << 16) | ((bArr2[3] & 255) << 24) | ((bArr2[4] & 255) << 32) | ((bArr2[5] & 255) << 40) | ((bArr2[6] & 255) << 48) | ((bArr2[7] & 255) << 56);
        this.big8 = (bArr2[8] & 255) | ((bArr2[9] & 255) << 8) | ((bArr2[10] & 255) << 16) | ((bArr2[11] & 255) << 24) | ((bArr2[12] & 255) << 32) | ((bArr2[13] & 255) << 40) | ((bArr2[14] & 255) << 48) | ((bArr2[15] & 255) << 56);
    }

    public GMSSRootSig(Digest digest, int i, int i2) {
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(digest);
        this.mdsize = this.messDigestOTS.getDigestSize();
        this.f2749w = i;
        this.height = i2;
        this.f2747k = (1 << i) - 1;
        this.messagesize = (int) Math.ceil((r3 << 3) / i);
    }

    public void initSign(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        this.hash = new byte[this.mdsize];
        this.messDigestOTS.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
        this.hash = bArr3;
        this.messDigestOTS.doFinal(bArr3, 0);
        int i3 = this.mdsize;
        byte[] bArr4 = new byte[i3];
        System.arraycopy(this.hash, 0, bArr4, 0, i3);
        int log = getLog((this.messagesize << this.f2749w) + 1);
        int i4 = this.f2749w;
        int i5 = 8;
        if (8 % i4 == 0) {
            int i6 = 8 / i4;
            i = 0;
            for (int i7 = 0; i7 < this.mdsize; i7++) {
                for (int i8 = 0; i8 < i6; i8++) {
                    byte b = bArr4[i7];
                    i += this.f2747k & b;
                    bArr4[i7] = (byte) (b >>> this.f2749w);
                }
            }
            int i9 = (this.messagesize << this.f2749w) - i;
            this.checksum = i9;
            int i10 = 0;
            while (i10 < log) {
                i += this.f2747k & i9;
                int i11 = this.f2749w;
                i9 >>>= i11;
                i10 += i11;
            }
        } else if (i4 < 8) {
            int i12 = this.mdsize / i4;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i13 < i12) {
                long j = 0;
                for (int i16 = 0; i16 < this.f2749w; i16++) {
                    j ^= (bArr4[i14] & 255) << (i16 << 3);
                    i14++;
                }
                int i17 = 0;
                while (i17 < i5) {
                    i15 += (int) (this.f2747k & j);
                    j >>>= this.f2749w;
                    i17++;
                    i12 = i12;
                    i5 = 8;
                }
                i13++;
                i5 = 8;
            }
            int i18 = this.mdsize % this.f2749w;
            long j2 = 0;
            for (int i19 = 0; i19 < i18; i19++) {
                j2 ^= (bArr4[i14] & 255) << (i19 << 3);
                i14++;
            }
            int i20 = i18 << 3;
            int i21 = 0;
            while (i21 < i20) {
                i15 += (int) (this.f2747k & j2);
                int i22 = this.f2749w;
                j2 >>>= i22;
                i21 += i22;
            }
            int i23 = (this.messagesize << this.f2749w) - i15;
            this.checksum = i23;
            int i24 = 0;
            i = i15;
            while (i24 < log) {
                i += this.f2747k & i23;
                int i25 = this.f2749w;
                i23 >>>= i25;
                i24 += i25;
            }
        } else if (i4 < 57) {
            int i26 = 0;
            int i27 = 0;
            while (true) {
                i2 = this.mdsize;
                int i28 = this.f2749w;
                if (i26 > (i2 << 3) - i28) {
                    break;
                }
                int i29 = i26 % 8;
                i26 += i28;
                int i30 = 0;
                long j3 = 0;
                for (int i31 = i26 >>> 3; i31 < ((i26 + 7) >>> 3); i31++) {
                    j3 ^= (bArr4[i31] & 255) << (i30 << 3);
                    i30++;
                }
                i27 = (int) (i27 + ((j3 >>> i29) & this.f2747k));
            }
            int i32 = i26 >>> 3;
            if (i32 < i2) {
                int i33 = i26 % 8;
                int i34 = 0;
                long j4 = 0;
                while (i32 < this.mdsize) {
                    j4 ^= (bArr4[i32] & 255) << (i34 << 3);
                    i34++;
                    i32++;
                }
                i27 = (int) (i27 + ((j4 >>> i33) & this.f2747k));
            }
            int i35 = (this.messagesize << this.f2749w) - i27;
            this.checksum = i35;
            int i36 = 0;
            i = i27;
            while (i36 < log) {
                i += this.f2747k & i35;
                int i37 = this.f2749w;
                i35 >>>= i37;
                i36 += i37;
            }
        } else {
            i = 0;
        }
        this.keysize = this.messagesize + ((int) Math.ceil(log / this.f2749w));
        this.steps = (int) Math.ceil((r2 + i) / (1 << this.height));
        int i38 = this.keysize;
        int i39 = this.mdsize;
        this.sign = new byte[i38 * i39];
        this.counter = 0;
        this.test = 0;
        this.f2746ii = 0;
        this.test8 = 0L;
        this.f2748r = 0;
        this.privateKeyOTS = new byte[i39];
        byte[] bArr5 = new byte[i39];
        this.seed = bArr5;
        System.arraycopy(bArr, 0, bArr5, 0, i39);
    }

    public boolean updateSign() {
        for (int i = 0; i < this.steps; i++) {
            if (this.counter < this.keysize) {
                oneStep();
            }
            if (this.counter == this.keysize) {
                return true;
            }
        }
        return false;
    }

    public byte[] getSig() {
        return this.sign;
    }

    private void oneStep() {
        int i = this.f2749w;
        if (8 % i == 0) {
            int i2 = this.test;
            if (i2 == 0) {
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
                int i3 = this.f2746ii;
                if (i3 < this.mdsize) {
                    byte[] bArr = this.hash;
                    byte b = bArr[i3];
                    this.test = this.f2747k & b;
                    bArr[i3] = (byte) (b >>> this.f2749w);
                } else {
                    int i4 = this.checksum;
                    this.test = this.f2747k & i4;
                    this.checksum = i4 >>> this.f2749w;
                }
            } else if (i2 > 0) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.privateKeyOTS;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr3;
                this.messDigestOTS.doFinal(bArr3, 0);
                this.test--;
            }
            if (this.test == 0) {
                byte[] bArr4 = this.privateKeyOTS;
                byte[] bArr5 = this.sign;
                int i5 = this.counter;
                int i6 = this.mdsize;
                System.arraycopy(bArr4, 0, bArr5, i5 * i6, i6);
                int i7 = this.counter + 1;
                this.counter = i7;
                if (i7 % (8 / this.f2749w) == 0) {
                    this.f2746ii++;
                    return;
                }
                return;
            }
            return;
        }
        if (i < 8) {
            int i8 = this.test;
            if (i8 == 0) {
                int i9 = this.counter;
                if (i9 % 8 == 0) {
                    int i10 = this.f2746ii;
                    int i11 = this.mdsize;
                    if (i10 < i11) {
                        this.big8 = 0L;
                        if (i9 < ((i11 / i) << 3)) {
                            for (int i12 = 0; i12 < this.f2749w; i12++) {
                                long j = this.big8;
                                byte[] bArr6 = this.hash;
                                int i13 = this.f2746ii;
                                this.big8 = j ^ ((bArr6[i13] & 255) << (i12 << 3));
                                this.f2746ii = i13 + 1;
                            }
                        } else {
                            for (int i14 = 0; i14 < this.mdsize % this.f2749w; i14++) {
                                long j2 = this.big8;
                                byte[] bArr7 = this.hash;
                                int i15 = this.f2746ii;
                                this.big8 = j2 ^ ((bArr7[i15] & 255) << (i14 << 3));
                                this.f2746ii = i15 + 1;
                            }
                        }
                    }
                }
                if (this.counter == this.messagesize) {
                    this.big8 = this.checksum;
                }
                this.test = (int) (this.big8 & this.f2747k);
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (i8 > 0) {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr8 = this.privateKeyOTS;
                digest2.update(bArr8, 0, bArr8.length);
                byte[] bArr9 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr9;
                this.messDigestOTS.doFinal(bArr9, 0);
                this.test--;
            }
            if (this.test == 0) {
                byte[] bArr10 = this.privateKeyOTS;
                byte[] bArr11 = this.sign;
                int i16 = this.counter;
                int i17 = this.mdsize;
                System.arraycopy(bArr10, 0, bArr11, i16 * i17, i17);
                this.big8 >>>= this.f2749w;
                this.counter++;
                return;
            }
            return;
        }
        if (i < 57) {
            long j3 = this.test8;
            if (j3 == 0) {
                this.big8 = 0L;
                this.f2746ii = 0;
                int i18 = this.f2748r;
                int i19 = i18 % 8;
                int i20 = i18 >>> 3;
                int i21 = this.mdsize;
                if (i20 < i21) {
                    if (i18 <= (i21 << 3) - i) {
                        int i22 = i18 + i;
                        this.f2748r = i22;
                        i21 = (i22 + 7) >>> 3;
                    } else {
                        this.f2748r = i18 + i;
                    }
                    while (i20 < i21) {
                        long j4 = this.big8;
                        int i23 = this.hash[i20] & 255;
                        int i24 = this.f2746ii;
                        this.big8 = j4 ^ (i23 << (i24 << 3));
                        this.f2746ii = i24 + 1;
                        i20++;
                    }
                    long j5 = this.big8 >>> i19;
                    this.big8 = j5;
                    this.test8 = j5 & this.f2747k;
                } else {
                    int i25 = this.checksum;
                    this.test8 = this.f2747k & i25;
                    this.checksum = i25 >>> i;
                }
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else if (j3 > 0) {
                Digest digest3 = this.messDigestOTS;
                byte[] bArr12 = this.privateKeyOTS;
                digest3.update(bArr12, 0, bArr12.length);
                byte[] bArr13 = new byte[this.messDigestOTS.getDigestSize()];
                this.privateKeyOTS = bArr13;
                this.messDigestOTS.doFinal(bArr13, 0);
                this.test8--;
            }
            if (this.test8 == 0) {
                byte[] bArr14 = this.privateKeyOTS;
                byte[] bArr15 = this.sign;
                int i26 = this.counter;
                int i27 = this.mdsize;
                System.arraycopy(bArr14, 0, bArr15, i26 * i27, i27);
                this.counter++;
            }
        }
    }

    public byte[][] getStatByte() {
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 5, this.mdsize);
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.hash;
        bArr[3] = this.sign;
        bArr[4] = getStatLong();
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.counter, this.test, this.f2746ii, this.f2748r, this.steps, this.keysize, this.height, this.f2749w, this.checksum};
    }

    public byte[] getStatLong() {
        long j = this.test8;
        long j2 = this.big8;
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 56) & 255), (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 32) & 255), (byte) ((j2 >> 40) & 255), (byte) ((j2 >> 48) & 255), (byte) ((j2 >> 56) & 255)};
    }

    public String toString() {
        String str = "" + this.big8 + "  ";
        int[] statInt = getStatInt();
        byte[][] statByte = getStatByte();
        for (int i = 0; i < 9; i++) {
            str = str + statInt[i] + " ";
        }
        for (int i2 = 0; i2 < 5; i2++) {
            str = str + new String(Hex.encode(statByte[i2])) + " ";
        }
        return str;
    }
}
