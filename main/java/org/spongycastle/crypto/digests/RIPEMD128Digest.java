package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

/* loaded from: classes3.dex */
public class RIPEMD128Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: H0 */
    private int f2170H0;

    /* renamed from: H1 */
    private int f2171H1;

    /* renamed from: H2 */
    private int f2172H2;

    /* renamed from: H3 */
    private int f2173H3;

    /* renamed from: X */
    private int[] f2174X;
    private int xOff;

    /* renamed from: RL */
    private int m1475RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: f1 */
    private int m1476f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m1477f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m1478f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m1479f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    public RIPEMD128Digest() {
        this.f2174X = new int[16];
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest);
        this.f2174X = new int[16];
        copyIn(rIPEMD128Digest);
    }

    private void copyIn(RIPEMD128Digest rIPEMD128Digest) {
        super.copyIn((GeneralDigest) rIPEMD128Digest);
        this.f2170H0 = rIPEMD128Digest.f2170H0;
        this.f2171H1 = rIPEMD128Digest.f2171H1;
        this.f2172H2 = rIPEMD128Digest.f2172H2;
        this.f2173H3 = rIPEMD128Digest.f2173H3;
        int[] iArr = rIPEMD128Digest.f2174X;
        System.arraycopy(iArr, 0, this.f2174X, 0, iArr.length);
        this.xOff = rIPEMD128Digest.xOff;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f2174X;
        int i2 = this.xOff;
        int i3 = i2 + 1;
        this.xOff = i3;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (i3 == 16) {
            processBlock();
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f2174X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    private void unpackWord(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        unpackWord(this.f2170H0, bArr, i);
        unpackWord(this.f2171H1, bArr, i + 4);
        unpackWord(this.f2172H2, bArr, i + 8);
        unpackWord(this.f2173H3, bArr, i + 12);
        reset();
        return 16;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2170H0 = 1732584193;
        this.f2171H1 = -271733879;
        this.f2172H2 = -1732584194;
        this.f2173H3 = 271733878;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f2174X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    /* renamed from: F1 */
    private int m1471F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1476f1(i2, i3, i4) + i5, i6);
    }

    /* renamed from: F2 */
    private int m1472F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1477f2(i2, i3, i4) + i5 + 1518500249, i6);
    }

    /* renamed from: F3 */
    private int m1473F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1478f3(i2, i3, i4) + i5 + 1859775393, i6);
    }

    /* renamed from: F4 */
    private int m1474F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(((i + m1479f4(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1476f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1477f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1478f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1475RL(i + m1479f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f2170H0;
        int i2 = this.f2171H1;
        int i3 = this.f2172H2;
        int i4 = this.f2173H3;
        int m1471F1 = m1471F1(i, i2, i3, i4, this.f2174X[0], 11);
        int m1471F12 = m1471F1(i4, m1471F1, i2, i3, this.f2174X[1], 14);
        int m1471F13 = m1471F1(i3, m1471F12, m1471F1, i2, this.f2174X[2], 15);
        int m1471F14 = m1471F1(i2, m1471F13, m1471F12, m1471F1, this.f2174X[3], 12);
        int m1471F15 = m1471F1(m1471F1, m1471F14, m1471F13, m1471F12, this.f2174X[4], 5);
        int m1471F16 = m1471F1(m1471F12, m1471F15, m1471F14, m1471F13, this.f2174X[5], 8);
        int m1471F17 = m1471F1(m1471F13, m1471F16, m1471F15, m1471F14, this.f2174X[6], 7);
        int m1471F18 = m1471F1(m1471F14, m1471F17, m1471F16, m1471F15, this.f2174X[7], 9);
        int m1471F19 = m1471F1(m1471F15, m1471F18, m1471F17, m1471F16, this.f2174X[8], 11);
        int m1471F110 = m1471F1(m1471F16, m1471F19, m1471F18, m1471F17, this.f2174X[9], 13);
        int m1471F111 = m1471F1(m1471F17, m1471F110, m1471F19, m1471F18, this.f2174X[10], 14);
        int m1471F112 = m1471F1(m1471F18, m1471F111, m1471F110, m1471F19, this.f2174X[11], 15);
        int m1471F113 = m1471F1(m1471F19, m1471F112, m1471F111, m1471F110, this.f2174X[12], 6);
        int m1471F114 = m1471F1(m1471F110, m1471F113, m1471F112, m1471F111, this.f2174X[13], 7);
        int m1471F115 = m1471F1(m1471F111, m1471F114, m1471F113, m1471F112, this.f2174X[14], 9);
        int m1471F116 = m1471F1(m1471F112, m1471F115, m1471F114, m1471F113, this.f2174X[15], 8);
        int m1472F2 = m1472F2(m1471F113, m1471F116, m1471F115, m1471F114, this.f2174X[7], 7);
        int m1472F22 = m1472F2(m1471F114, m1472F2, m1471F116, m1471F115, this.f2174X[4], 6);
        int m1472F23 = m1472F2(m1471F115, m1472F22, m1472F2, m1471F116, this.f2174X[13], 8);
        int m1472F24 = m1472F2(m1471F116, m1472F23, m1472F22, m1472F2, this.f2174X[1], 13);
        int m1472F25 = m1472F2(m1472F2, m1472F24, m1472F23, m1472F22, this.f2174X[10], 11);
        int m1472F26 = m1472F2(m1472F22, m1472F25, m1472F24, m1472F23, this.f2174X[6], 9);
        int m1472F27 = m1472F2(m1472F23, m1472F26, m1472F25, m1472F24, this.f2174X[15], 7);
        int m1472F28 = m1472F2(m1472F24, m1472F27, m1472F26, m1472F25, this.f2174X[3], 15);
        int m1472F29 = m1472F2(m1472F25, m1472F28, m1472F27, m1472F26, this.f2174X[12], 7);
        int m1472F210 = m1472F2(m1472F26, m1472F29, m1472F28, m1472F27, this.f2174X[0], 12);
        int m1472F211 = m1472F2(m1472F27, m1472F210, m1472F29, m1472F28, this.f2174X[9], 15);
        int m1472F212 = m1472F2(m1472F28, m1472F211, m1472F210, m1472F29, this.f2174X[5], 9);
        int m1472F213 = m1472F2(m1472F29, m1472F212, m1472F211, m1472F210, this.f2174X[2], 11);
        int m1472F214 = m1472F2(m1472F210, m1472F213, m1472F212, m1472F211, this.f2174X[14], 7);
        int m1472F215 = m1472F2(m1472F211, m1472F214, m1472F213, m1472F212, this.f2174X[11], 13);
        int m1472F216 = m1472F2(m1472F212, m1472F215, m1472F214, m1472F213, this.f2174X[8], 12);
        int m1473F3 = m1473F3(m1472F213, m1472F216, m1472F215, m1472F214, this.f2174X[3], 11);
        int m1473F32 = m1473F3(m1472F214, m1473F3, m1472F216, m1472F215, this.f2174X[10], 13);
        int m1473F33 = m1473F3(m1472F215, m1473F32, m1473F3, m1472F216, this.f2174X[14], 6);
        int m1473F34 = m1473F3(m1472F216, m1473F33, m1473F32, m1473F3, this.f2174X[4], 7);
        int m1473F35 = m1473F3(m1473F3, m1473F34, m1473F33, m1473F32, this.f2174X[9], 14);
        int m1473F36 = m1473F3(m1473F32, m1473F35, m1473F34, m1473F33, this.f2174X[15], 9);
        int m1473F37 = m1473F3(m1473F33, m1473F36, m1473F35, m1473F34, this.f2174X[8], 13);
        int m1473F38 = m1473F3(m1473F34, m1473F37, m1473F36, m1473F35, this.f2174X[1], 15);
        int m1473F39 = m1473F3(m1473F35, m1473F38, m1473F37, m1473F36, this.f2174X[2], 14);
        int m1473F310 = m1473F3(m1473F36, m1473F39, m1473F38, m1473F37, this.f2174X[7], 8);
        int m1473F311 = m1473F3(m1473F37, m1473F310, m1473F39, m1473F38, this.f2174X[0], 13);
        int m1473F312 = m1473F3(m1473F38, m1473F311, m1473F310, m1473F39, this.f2174X[6], 6);
        int m1473F313 = m1473F3(m1473F39, m1473F312, m1473F311, m1473F310, this.f2174X[13], 5);
        int m1473F314 = m1473F3(m1473F310, m1473F313, m1473F312, m1473F311, this.f2174X[11], 12);
        int m1473F315 = m1473F3(m1473F311, m1473F314, m1473F313, m1473F312, this.f2174X[5], 7);
        int m1473F316 = m1473F3(m1473F312, m1473F315, m1473F314, m1473F313, this.f2174X[12], 5);
        int m1474F4 = m1474F4(m1473F313, m1473F316, m1473F315, m1473F314, this.f2174X[1], 11);
        int m1474F42 = m1474F4(m1473F314, m1474F4, m1473F316, m1473F315, this.f2174X[9], 12);
        int m1474F43 = m1474F4(m1473F315, m1474F42, m1474F4, m1473F316, this.f2174X[11], 14);
        int m1474F44 = m1474F4(m1473F316, m1474F43, m1474F42, m1474F4, this.f2174X[10], 15);
        int m1474F45 = m1474F4(m1474F4, m1474F44, m1474F43, m1474F42, this.f2174X[0], 14);
        int m1474F46 = m1474F4(m1474F42, m1474F45, m1474F44, m1474F43, this.f2174X[8], 15);
        int m1474F47 = m1474F4(m1474F43, m1474F46, m1474F45, m1474F44, this.f2174X[12], 9);
        int m1474F48 = m1474F4(m1474F44, m1474F47, m1474F46, m1474F45, this.f2174X[4], 8);
        int m1474F49 = m1474F4(m1474F45, m1474F48, m1474F47, m1474F46, this.f2174X[13], 9);
        int m1474F410 = m1474F4(m1474F46, m1474F49, m1474F48, m1474F47, this.f2174X[3], 14);
        int m1474F411 = m1474F4(m1474F47, m1474F410, m1474F49, m1474F48, this.f2174X[7], 5);
        int m1474F412 = m1474F4(m1474F48, m1474F411, m1474F410, m1474F49, this.f2174X[15], 6);
        int m1474F413 = m1474F4(m1474F49, m1474F412, m1474F411, m1474F410, this.f2174X[14], 8);
        int m1474F414 = m1474F4(m1474F410, m1474F413, m1474F412, m1474F411, this.f2174X[5], 6);
        int m1474F415 = m1474F4(m1474F411, m1474F414, m1474F413, m1474F412, this.f2174X[6], 5);
        int m1474F416 = m1474F4(m1474F412, m1474F415, m1474F414, m1474F413, this.f2174X[2], 12);
        int FF4 = FF4(i, i2, i3, i4, this.f2174X[5], 8);
        int FF42 = FF4(i4, FF4, i2, i3, this.f2174X[14], 9);
        int FF43 = FF4(i3, FF42, FF4, i2, this.f2174X[7], 9);
        int FF44 = FF4(i2, FF43, FF42, FF4, this.f2174X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f2174X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f2174X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f2174X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f2174X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f2174X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f2174X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f2174X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f2174X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f2174X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f2174X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f2174X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f2174X[12], 6);
        int FF3 = FF3(FF413, FF416, FF415, FF414, this.f2174X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f2174X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f2174X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f2174X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f2174X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f2174X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f2174X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f2174X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f2174X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f2174X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f2174X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f2174X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f2174X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f2174X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f2174X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f2174X[2], 11);
        int FF2 = FF2(FF313, FF316, FF315, FF314, this.f2174X[15], 9);
        int FF22 = FF2(FF314, FF2, FF316, FF315, this.f2174X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, FF316, this.f2174X[1], 15);
        int FF24 = FF2(FF316, FF23, FF22, FF2, this.f2174X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f2174X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f2174X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f2174X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f2174X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f2174X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f2174X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f2174X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f2174X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f2174X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f2174X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f2174X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f2174X[13], 5);
        int FF1 = FF1(FF213, FF216, FF215, FF214, this.f2174X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, FF215, this.f2174X[6], 5);
        int FF13 = FF1(FF215, FF12, FF1, FF216, this.f2174X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f2174X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f2174X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f2174X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f2174X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f2174X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f2174X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f2174X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f2174X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f2174X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f2174X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f2174X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f2174X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f2174X[14], 8);
        int i5 = FF114 + m1474F415 + this.f2171H1;
        this.f2171H1 = this.f2172H2 + m1474F414 + FF113;
        this.f2172H2 = this.f2173H3 + m1474F413 + FF116;
        this.f2173H3 = this.f2170H0 + m1474F416 + FF115;
        this.f2170H0 = i5;
        this.xOff = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f2174X;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD128Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD128Digest) memoable);
    }
}
