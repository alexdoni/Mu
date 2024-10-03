package org.spongycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: Y1 */
    private static final int f2201Y1 = 1518500249;

    /* renamed from: Y2 */
    private static final int f2202Y2 = 1859775393;

    /* renamed from: Y3 */
    private static final int f2203Y3 = -1894007588;

    /* renamed from: Y4 */
    private static final int f2204Y4 = -899497514;

    /* renamed from: H1 */
    private int f2205H1;

    /* renamed from: H2 */
    private int f2206H2;

    /* renamed from: H3 */
    private int f2207H3;

    /* renamed from: H4 */
    private int f2208H4;

    /* renamed from: H5 */
    private int f2209H5;

    /* renamed from: X */
    private int[] f2210X;
    private int xOff;

    /* renamed from: f */
    private int m1501f(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: g */
    private int m1502g(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    /* renamed from: h */
    private int m1503h(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA1;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    public SHA1Digest() {
        this.f2210X = new int[80];
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.f2210X = new int[80];
        copyIn(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.f2210X = new int[80];
        this.f2205H1 = Pack.bigEndianToInt(bArr, 16);
        this.f2206H2 = Pack.bigEndianToInt(bArr, 20);
        this.f2207H3 = Pack.bigEndianToInt(bArr, 24);
        this.f2208H4 = Pack.bigEndianToInt(bArr, 28);
        this.f2209H5 = Pack.bigEndianToInt(bArr, 32);
        this.xOff = Pack.bigEndianToInt(bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            this.f2210X[i] = Pack.bigEndianToInt(bArr, (i * 4) + 40);
        }
    }

    private void copyIn(SHA1Digest sHA1Digest) {
        this.f2205H1 = sHA1Digest.f2205H1;
        this.f2206H2 = sHA1Digest.f2206H2;
        this.f2207H3 = sHA1Digest.f2207H3;
        this.f2208H4 = sHA1Digest.f2208H4;
        this.f2209H5 = sHA1Digest.f2209H5;
        int[] iArr = sHA1Digest.f2210X;
        System.arraycopy(iArr, 0, this.f2210X, 0, iArr.length);
        this.xOff = sHA1Digest.xOff;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int i2 = bArr[i] << Ascii.CAN;
        int i3 = i + 1;
        int i4 = i2 | ((bArr[i3] & 255) << 16);
        int i5 = i3 + 1;
        int i6 = (bArr[i5 + 1] & 255) | i4 | ((bArr[i5] & 255) << 8);
        int[] iArr = this.f2210X;
        int i7 = this.xOff;
        iArr[i7] = i6;
        int i8 = i7 + 1;
        this.xOff = i8;
        if (i8 == 16) {
            processBlock();
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f2210X;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.f2205H1, bArr, i);
        Pack.intToBigEndian(this.f2206H2, bArr, i + 4);
        Pack.intToBigEndian(this.f2207H3, bArr, i + 8);
        Pack.intToBigEndian(this.f2208H4, bArr, i + 12);
        Pack.intToBigEndian(this.f2209H5, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2205H1 = 1732584193;
        this.f2206H2 = -271733879;
        this.f2207H3 = -1732584194;
        this.f2208H4 = 271733878;
        this.f2209H5 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f2210X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        for (int i = 16; i < 80; i++) {
            int[] iArr = this.f2210X;
            int i2 = ((iArr[i - 3] ^ iArr[i - 8]) ^ iArr[i - 14]) ^ iArr[i - 16];
            iArr[i] = (i2 >>> 31) | (i2 << 1);
        }
        int i3 = this.f2205H1;
        int i4 = this.f2206H2;
        int i5 = this.f2207H3;
        int i6 = this.f2208H4;
        int i7 = this.f2209H5;
        int i8 = 0;
        int i9 = 0;
        while (i8 < 4) {
            int i10 = i9 + 1;
            int m1501f = i7 + ((i3 << 5) | (i3 >>> 27)) + m1501f(i4, i5, i6) + this.f2210X[i9] + f2201Y1;
            int i11 = (i4 >>> 2) | (i4 << 30);
            int i12 = i10 + 1;
            int m1501f2 = i6 + ((m1501f << 5) | (m1501f >>> 27)) + m1501f(i3, i11, i5) + this.f2210X[i10] + f2201Y1;
            int i13 = (i3 >>> 2) | (i3 << 30);
            int i14 = i12 + 1;
            int m1501f3 = i5 + ((m1501f2 << 5) | (m1501f2 >>> 27)) + m1501f(m1501f, i13, i11) + this.f2210X[i12] + f2201Y1;
            i7 = (m1501f >>> 2) | (m1501f << 30);
            int i15 = i14 + 1;
            i4 = i11 + ((m1501f3 << 5) | (m1501f3 >>> 27)) + m1501f(m1501f2, i7, i13) + this.f2210X[i14] + f2201Y1;
            i6 = (m1501f2 >>> 2) | (m1501f2 << 30);
            i3 = i13 + ((i4 << 5) | (i4 >>> 27)) + m1501f(m1501f3, i6, i7) + this.f2210X[i15] + f2201Y1;
            i5 = (m1501f3 >>> 2) | (m1501f3 << 30);
            i8++;
            i9 = i15 + 1;
        }
        int i16 = 0;
        while (i16 < 4) {
            int i17 = i9 + 1;
            int m1503h = i7 + ((i3 << 5) | (i3 >>> 27)) + m1503h(i4, i5, i6) + this.f2210X[i9] + f2202Y2;
            int i18 = (i4 >>> 2) | (i4 << 30);
            int i19 = i17 + 1;
            int m1503h2 = i6 + ((m1503h << 5) | (m1503h >>> 27)) + m1503h(i3, i18, i5) + this.f2210X[i17] + f2202Y2;
            int i20 = (i3 >>> 2) | (i3 << 30);
            int i21 = i19 + 1;
            int m1503h3 = i5 + ((m1503h2 << 5) | (m1503h2 >>> 27)) + m1503h(m1503h, i20, i18) + this.f2210X[i19] + f2202Y2;
            i7 = (m1503h >>> 2) | (m1503h << 30);
            int i22 = i21 + 1;
            i4 = i18 + ((m1503h3 << 5) | (m1503h3 >>> 27)) + m1503h(m1503h2, i7, i20) + this.f2210X[i21] + f2202Y2;
            i6 = (m1503h2 >>> 2) | (m1503h2 << 30);
            i3 = i20 + ((i4 << 5) | (i4 >>> 27)) + m1503h(m1503h3, i6, i7) + this.f2210X[i22] + f2202Y2;
            i5 = (m1503h3 >>> 2) | (m1503h3 << 30);
            i16++;
            i9 = i22 + 1;
        }
        int i23 = 0;
        while (i23 < 4) {
            int i24 = i9 + 1;
            int m1502g = i7 + ((i3 << 5) | (i3 >>> 27)) + m1502g(i4, i5, i6) + this.f2210X[i9] + f2203Y3;
            int i25 = (i4 >>> 2) | (i4 << 30);
            int i26 = i24 + 1;
            int m1502g2 = i6 + ((m1502g << 5) | (m1502g >>> 27)) + m1502g(i3, i25, i5) + this.f2210X[i24] + f2203Y3;
            int i27 = (i3 >>> 2) | (i3 << 30);
            int i28 = i26 + 1;
            int m1502g3 = i5 + ((m1502g2 << 5) | (m1502g2 >>> 27)) + m1502g(m1502g, i27, i25) + this.f2210X[i26] + f2203Y3;
            i7 = (m1502g >>> 2) | (m1502g << 30);
            int i29 = i28 + 1;
            i4 = i25 + ((m1502g3 << 5) | (m1502g3 >>> 27)) + m1502g(m1502g2, i7, i27) + this.f2210X[i28] + f2203Y3;
            i6 = (m1502g2 >>> 2) | (m1502g2 << 30);
            i3 = i27 + ((i4 << 5) | (i4 >>> 27)) + m1502g(m1502g3, i6, i7) + this.f2210X[i29] + f2203Y3;
            i5 = (m1502g3 >>> 2) | (m1502g3 << 30);
            i23++;
            i9 = i29 + 1;
        }
        int i30 = 0;
        while (i30 <= 3) {
            int i31 = i9 + 1;
            int m1503h4 = i7 + ((i3 << 5) | (i3 >>> 27)) + m1503h(i4, i5, i6) + this.f2210X[i9] + f2204Y4;
            int i32 = (i4 >>> 2) | (i4 << 30);
            int i33 = i31 + 1;
            int m1503h5 = i6 + ((m1503h4 << 5) | (m1503h4 >>> 27)) + m1503h(i3, i32, i5) + this.f2210X[i31] + f2204Y4;
            int i34 = (i3 >>> 2) | (i3 << 30);
            int i35 = i33 + 1;
            int m1503h6 = i5 + ((m1503h5 << 5) | (m1503h5 >>> 27)) + m1503h(m1503h4, i34, i32) + this.f2210X[i33] + f2204Y4;
            i7 = (m1503h4 >>> 2) | (m1503h4 << 30);
            int i36 = i35 + 1;
            i4 = i32 + ((m1503h6 << 5) | (m1503h6 >>> 27)) + m1503h(m1503h5, i7, i34) + this.f2210X[i35] + f2204Y4;
            i6 = (m1503h5 >>> 2) | (m1503h5 << 30);
            i3 = i34 + ((i4 << 5) | (i4 >>> 27)) + m1503h(m1503h6, i6, i7) + this.f2210X[i36] + f2204Y4;
            i5 = (m1503h6 >>> 2) | (m1503h6 << 30);
            i30++;
            i9 = i36 + 1;
        }
        this.f2205H1 += i3;
        this.f2206H2 += i4;
        this.f2207H3 += i5;
        this.f2208H4 += i6;
        this.f2209H5 += i7;
        this.xOff = 0;
        for (int i37 = 0; i37 < 16; i37++) {
            this.f2210X[i37] = 0;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn((GeneralDigest) sHA1Digest);
        copyIn(sHA1Digest);
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.xOff * 4) + 40];
        super.populateState(bArr);
        Pack.intToBigEndian(this.f2205H1, bArr, 16);
        Pack.intToBigEndian(this.f2206H2, bArr, 20);
        Pack.intToBigEndian(this.f2207H3, bArr, 24);
        Pack.intToBigEndian(this.f2208H4, bArr, 28);
        Pack.intToBigEndian(this.f2209H5, bArr, 32);
        Pack.intToBigEndian(this.xOff, bArr, 36);
        for (int i = 0; i != this.xOff; i++) {
            Pack.intToBigEndian(this.f2210X[i], bArr, (i * 4) + 40);
        }
        return bArr;
    }
}
