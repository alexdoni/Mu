package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class SM3Digest extends GeneralDigest {
    private static final int BLOCK_SIZE = 16;
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: T */
    private static final int[] f2231T = new int[64];

    /* renamed from: V */
    private int[] f2232V;

    /* renamed from: W */
    private int[] f2233W;

    /* renamed from: W1 */
    private int[] f2234W1;
    private int[] inwords;
    private int xOff;

    private int FF0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int FF1(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    private int GG0(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int GG1(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: P0 */
    private int m1506P0(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    /* renamed from: P1 */
    private int m1507P1(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            f2231T[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            f2231T[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this.f2232V = new int[8];
        this.inwords = new int[16];
        this.f2233W = new int[68];
        this.f2234W1 = new int[64];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.f2232V = new int[8];
        this.inwords = new int[16];
        this.f2233W = new int[68];
        this.f2234W1 = new int[64];
        copyIn(sM3Digest);
    }

    private void copyIn(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.f2232V;
        int[] iArr2 = this.f2232V;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.inwords;
        int[] iArr4 = this.inwords;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.xOff = sM3Digest.xOff;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn((GeneralDigest) sM3Digest);
        copyIn(sM3Digest);
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] iArr = this.f2232V;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.xOff = 0;
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f2232V[0], bArr, i + 0);
        Pack.intToBigEndian(this.f2232V[1], bArr, i + 4);
        Pack.intToBigEndian(this.f2232V[2], bArr, i + 8);
        Pack.intToBigEndian(this.f2232V[3], bArr, i + 12);
        Pack.intToBigEndian(this.f2232V[4], bArr, i + 16);
        Pack.intToBigEndian(this.f2232V[5], bArr, i + 20);
        Pack.intToBigEndian(this.f2232V[6], bArr, i + 24);
        Pack.intToBigEndian(this.f2232V[7], bArr, i + 28);
        reset();
        return 32;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = (bArr[i] & 255) << 24;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
        int[] iArr = this.inwords;
        int i7 = this.xOff;
        iArr[i7] = i6;
        int i8 = i7 + 1;
        this.xOff = i8;
        if (i8 >= 16) {
            processBlock();
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        int i = this.xOff;
        if (i > 14) {
            this.inwords[i] = 0;
            this.xOff = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.xOff;
            if (i2 < 14) {
                this.inwords[i2] = 0;
                this.xOff = i2 + 1;
            } else {
                int[] iArr = this.inwords;
                int i3 = i2 + 1;
                iArr[i2] = (int) (j >>> 32);
                this.xOff = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int i = 0; i < 16; i++) {
            this.f2233W[i] = this.inwords[i];
        }
        for (int i2 = 16; i2 < 68; i2++) {
            int[] iArr = this.f2233W;
            int i3 = iArr[i2 - 3];
            int i4 = iArr[i2 - 13];
            iArr[i2] = (m1507P1(((i3 >>> 17) | (i3 << 15)) ^ (iArr[i2 - 16] ^ iArr[i2 - 9])) ^ ((i4 >>> 25) | (i4 << 7))) ^ this.f2233W[i2 - 6];
        }
        for (int i5 = 0; i5 < 64; i5++) {
            int[] iArr2 = this.f2234W1;
            int[] iArr3 = this.f2233W;
            iArr2[i5] = iArr3[i5 + 4] ^ iArr3[i5];
        }
        int[] iArr4 = this.f2232V;
        int i6 = iArr4[0];
        int i7 = iArr4[1];
        int i8 = iArr4[2];
        int i9 = iArr4[3];
        int i10 = iArr4[4];
        int i11 = iArr4[5];
        int i12 = iArr4[6];
        int i13 = iArr4[7];
        int i14 = 0;
        int i15 = i12;
        while (i14 < 16) {
            int i16 = (i6 << 12) | (i6 >>> 20);
            int i17 = i16 + i10 + f2231T[i14];
            int i18 = (i17 << 7) | (i17 >>> 25);
            int FF0 = FF0(i6, i7, i8) + i9 + (i18 ^ i16) + this.f2234W1[i14];
            int GG0 = GG0(i10, i11, i15) + i13 + i18 + this.f2233W[i14];
            int i19 = (i7 << 9) | (i7 >>> 23);
            i14++;
            int i20 = i10;
            i10 = m1506P0(GG0);
            i13 = i15;
            i15 = (i11 << 19) | (i11 >>> 13);
            i11 = i20;
            i7 = i6;
            i6 = FF0;
            i9 = i8;
            i8 = i19;
        }
        int i21 = i9;
        int i22 = i8;
        int i23 = i7;
        int i24 = i6;
        int i25 = 16;
        int i26 = i13;
        int i27 = i15;
        while (i25 < 64) {
            int i28 = (i24 << 12) | (i24 >>> 20);
            int i29 = i28 + i10 + f2231T[i25];
            int i30 = (i29 << 7) | (i29 >>> 25);
            int FF1 = FF1(i24, i23, i22) + i21 + (i30 ^ i28) + this.f2234W1[i25];
            int GG1 = GG1(i10, i11, i27) + i26 + i30 + this.f2233W[i25];
            int i31 = (i23 >>> 23) | (i23 << 9);
            int i32 = (i11 << 19) | (i11 >>> 13);
            i25++;
            i11 = i10;
            i10 = m1506P0(GG1);
            i26 = i27;
            i27 = i32;
            i23 = i24;
            i24 = FF1;
            i21 = i22;
            i22 = i31;
        }
        int[] iArr5 = this.f2232V;
        iArr5[0] = iArr5[0] ^ i24;
        iArr5[1] = iArr5[1] ^ i23;
        iArr5[2] = iArr5[2] ^ i22;
        iArr5[3] = iArr5[3] ^ i21;
        iArr5[4] = iArr5[4] ^ i10;
        iArr5[5] = iArr5[5] ^ i11;
        iArr5[6] = i27 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i26;
        this.xOff = 0;
    }
}
