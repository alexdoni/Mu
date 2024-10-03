package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

/* loaded from: classes3.dex */
public class RIPEMD256Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 32;

    /* renamed from: H0 */
    private int f2181H0;

    /* renamed from: H1 */
    private int f2182H1;

    /* renamed from: H2 */
    private int f2183H2;

    /* renamed from: H3 */
    private int f2184H3;

    /* renamed from: H4 */
    private int f2185H4;

    /* renamed from: H5 */
    private int f2186H5;

    /* renamed from: H6 */
    private int f2187H6;

    /* renamed from: H7 */
    private int f2188H7;

    /* renamed from: X */
    private int[] f2189X;
    private int xOff;

    /* renamed from: RL */
    private int m1490RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: f1 */
    private int m1491f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m1492f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m1493f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m1494f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD256";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    public RIPEMD256Digest() {
        this.f2189X = new int[16];
        reset();
    }

    public RIPEMD256Digest(RIPEMD256Digest rIPEMD256Digest) {
        super(rIPEMD256Digest);
        this.f2189X = new int[16];
        copyIn(rIPEMD256Digest);
    }

    private void copyIn(RIPEMD256Digest rIPEMD256Digest) {
        super.copyIn((GeneralDigest) rIPEMD256Digest);
        this.f2181H0 = rIPEMD256Digest.f2181H0;
        this.f2182H1 = rIPEMD256Digest.f2182H1;
        this.f2183H2 = rIPEMD256Digest.f2183H2;
        this.f2184H3 = rIPEMD256Digest.f2184H3;
        this.f2185H4 = rIPEMD256Digest.f2185H4;
        this.f2186H5 = rIPEMD256Digest.f2186H5;
        this.f2187H6 = rIPEMD256Digest.f2187H6;
        this.f2188H7 = rIPEMD256Digest.f2188H7;
        int[] iArr = rIPEMD256Digest.f2189X;
        System.arraycopy(iArr, 0, this.f2189X, 0, iArr.length);
        this.xOff = rIPEMD256Digest.xOff;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f2189X;
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
        int[] iArr = this.f2189X;
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
        unpackWord(this.f2181H0, bArr, i);
        unpackWord(this.f2182H1, bArr, i + 4);
        unpackWord(this.f2183H2, bArr, i + 8);
        unpackWord(this.f2184H3, bArr, i + 12);
        unpackWord(this.f2185H4, bArr, i + 16);
        unpackWord(this.f2186H5, bArr, i + 20);
        unpackWord(this.f2187H6, bArr, i + 24);
        unpackWord(this.f2188H7, bArr, i + 28);
        reset();
        return 32;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2181H0 = 1732584193;
        this.f2182H1 = -271733879;
        this.f2183H2 = -1732584194;
        this.f2184H3 = 271733878;
        this.f2185H4 = 1985229328;
        this.f2186H5 = -19088744;
        this.f2187H6 = -1985229329;
        this.f2188H7 = 19088743;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f2189X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    /* renamed from: F1 */
    private int m1486F1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1491f1(i2, i3, i4) + i5, i6);
    }

    /* renamed from: F2 */
    private int m1487F2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1492f2(i2, i3, i4) + i5 + 1518500249, i6);
    }

    /* renamed from: F3 */
    private int m1488F3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1493f3(i2, i3, i4) + i5 + 1859775393, i6);
    }

    /* renamed from: F4 */
    private int m1489F4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(((i + m1494f4(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    private int FF1(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1491f1(i2, i3, i4) + i5, i6);
    }

    private int FF2(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1492f2(i2, i3, i4) + i5 + 1836072691, i6);
    }

    private int FF3(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1493f3(i2, i3, i4) + i5 + 1548603684, i6);
    }

    private int FF4(int i, int i2, int i3, int i4, int i5, int i6) {
        return m1490RL(i + m1494f4(i2, i3, i4) + i5 + 1352829926, i6);
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f2181H0;
        int i2 = this.f2182H1;
        int i3 = this.f2183H2;
        int i4 = this.f2184H3;
        int i5 = this.f2185H4;
        int i6 = this.f2186H5;
        int i7 = this.f2187H6;
        int i8 = this.f2188H7;
        int m1486F1 = m1486F1(i, i2, i3, i4, this.f2189X[0], 11);
        int m1486F12 = m1486F1(i4, m1486F1, i2, i3, this.f2189X[1], 14);
        int m1486F13 = m1486F1(i3, m1486F12, m1486F1, i2, this.f2189X[2], 15);
        int m1486F14 = m1486F1(i2, m1486F13, m1486F12, m1486F1, this.f2189X[3], 12);
        int m1486F15 = m1486F1(m1486F1, m1486F14, m1486F13, m1486F12, this.f2189X[4], 5);
        int m1486F16 = m1486F1(m1486F12, m1486F15, m1486F14, m1486F13, this.f2189X[5], 8);
        int m1486F17 = m1486F1(m1486F13, m1486F16, m1486F15, m1486F14, this.f2189X[6], 7);
        int m1486F18 = m1486F1(m1486F14, m1486F17, m1486F16, m1486F15, this.f2189X[7], 9);
        int m1486F19 = m1486F1(m1486F15, m1486F18, m1486F17, m1486F16, this.f2189X[8], 11);
        int m1486F110 = m1486F1(m1486F16, m1486F19, m1486F18, m1486F17, this.f2189X[9], 13);
        int m1486F111 = m1486F1(m1486F17, m1486F110, m1486F19, m1486F18, this.f2189X[10], 14);
        int m1486F112 = m1486F1(m1486F18, m1486F111, m1486F110, m1486F19, this.f2189X[11], 15);
        int m1486F113 = m1486F1(m1486F19, m1486F112, m1486F111, m1486F110, this.f2189X[12], 6);
        int m1486F114 = m1486F1(m1486F110, m1486F113, m1486F112, m1486F111, this.f2189X[13], 7);
        int m1486F115 = m1486F1(m1486F111, m1486F114, m1486F113, m1486F112, this.f2189X[14], 9);
        int m1486F116 = m1486F1(m1486F112, m1486F115, m1486F114, m1486F113, this.f2189X[15], 8);
        int FF4 = FF4(i5, i6, i7, i8, this.f2189X[5], 8);
        int FF42 = FF4(i8, FF4, i6, i7, this.f2189X[14], 9);
        int FF43 = FF4(i7, FF42, FF4, i6, this.f2189X[7], 9);
        int FF44 = FF4(i6, FF43, FF42, FF4, this.f2189X[0], 11);
        int FF45 = FF4(FF4, FF44, FF43, FF42, this.f2189X[9], 13);
        int FF46 = FF4(FF42, FF45, FF44, FF43, this.f2189X[2], 15);
        int FF47 = FF4(FF43, FF46, FF45, FF44, this.f2189X[11], 15);
        int FF48 = FF4(FF44, FF47, FF46, FF45, this.f2189X[4], 5);
        int FF49 = FF4(FF45, FF48, FF47, FF46, this.f2189X[13], 7);
        int FF410 = FF4(FF46, FF49, FF48, FF47, this.f2189X[6], 7);
        int FF411 = FF4(FF47, FF410, FF49, FF48, this.f2189X[15], 8);
        int FF412 = FF4(FF48, FF411, FF410, FF49, this.f2189X[8], 11);
        int FF413 = FF4(FF49, FF412, FF411, FF410, this.f2189X[1], 14);
        int FF414 = FF4(FF410, FF413, FF412, FF411, this.f2189X[10], 14);
        int FF415 = FF4(FF411, FF414, FF413, FF412, this.f2189X[3], 12);
        int FF416 = FF4(FF412, FF415, FF414, FF413, this.f2189X[12], 6);
        int m1487F2 = m1487F2(FF413, m1486F116, m1486F115, m1486F114, this.f2189X[7], 7);
        int m1487F22 = m1487F2(m1486F114, m1487F2, m1486F116, m1486F115, this.f2189X[4], 6);
        int m1487F23 = m1487F2(m1486F115, m1487F22, m1487F2, m1486F116, this.f2189X[13], 8);
        int m1487F24 = m1487F2(m1486F116, m1487F23, m1487F22, m1487F2, this.f2189X[1], 13);
        int m1487F25 = m1487F2(m1487F2, m1487F24, m1487F23, m1487F22, this.f2189X[10], 11);
        int m1487F26 = m1487F2(m1487F22, m1487F25, m1487F24, m1487F23, this.f2189X[6], 9);
        int m1487F27 = m1487F2(m1487F23, m1487F26, m1487F25, m1487F24, this.f2189X[15], 7);
        int m1487F28 = m1487F2(m1487F24, m1487F27, m1487F26, m1487F25, this.f2189X[3], 15);
        int m1487F29 = m1487F2(m1487F25, m1487F28, m1487F27, m1487F26, this.f2189X[12], 7);
        int m1487F210 = m1487F2(m1487F26, m1487F29, m1487F28, m1487F27, this.f2189X[0], 12);
        int m1487F211 = m1487F2(m1487F27, m1487F210, m1487F29, m1487F28, this.f2189X[9], 15);
        int m1487F212 = m1487F2(m1487F28, m1487F211, m1487F210, m1487F29, this.f2189X[5], 9);
        int m1487F213 = m1487F2(m1487F29, m1487F212, m1487F211, m1487F210, this.f2189X[2], 11);
        int m1487F214 = m1487F2(m1487F210, m1487F213, m1487F212, m1487F211, this.f2189X[14], 7);
        int m1487F215 = m1487F2(m1487F211, m1487F214, m1487F213, m1487F212, this.f2189X[11], 13);
        int m1487F216 = m1487F2(m1487F212, m1487F215, m1487F214, m1487F213, this.f2189X[8], 12);
        int FF3 = FF3(m1486F113, FF416, FF415, FF414, this.f2189X[6], 9);
        int FF32 = FF3(FF414, FF3, FF416, FF415, this.f2189X[11], 13);
        int FF33 = FF3(FF415, FF32, FF3, FF416, this.f2189X[3], 15);
        int FF34 = FF3(FF416, FF33, FF32, FF3, this.f2189X[7], 7);
        int FF35 = FF3(FF3, FF34, FF33, FF32, this.f2189X[0], 12);
        int FF36 = FF3(FF32, FF35, FF34, FF33, this.f2189X[13], 8);
        int FF37 = FF3(FF33, FF36, FF35, FF34, this.f2189X[5], 9);
        int FF38 = FF3(FF34, FF37, FF36, FF35, this.f2189X[10], 11);
        int FF39 = FF3(FF35, FF38, FF37, FF36, this.f2189X[14], 7);
        int FF310 = FF3(FF36, FF39, FF38, FF37, this.f2189X[15], 7);
        int FF311 = FF3(FF37, FF310, FF39, FF38, this.f2189X[8], 12);
        int FF312 = FF3(FF38, FF311, FF310, FF39, this.f2189X[12], 7);
        int FF313 = FF3(FF39, FF312, FF311, FF310, this.f2189X[4], 6);
        int FF314 = FF3(FF310, FF313, FF312, FF311, this.f2189X[9], 15);
        int FF315 = FF3(FF311, FF314, FF313, FF312, this.f2189X[1], 13);
        int FF316 = FF3(FF312, FF315, FF314, FF313, this.f2189X[2], 11);
        int m1488F3 = m1488F3(m1487F213, FF316, m1487F215, m1487F214, this.f2189X[3], 11);
        int m1488F32 = m1488F3(m1487F214, m1488F3, FF316, m1487F215, this.f2189X[10], 13);
        int m1488F33 = m1488F3(m1487F215, m1488F32, m1488F3, FF316, this.f2189X[14], 6);
        int m1488F34 = m1488F3(FF316, m1488F33, m1488F32, m1488F3, this.f2189X[4], 7);
        int m1488F35 = m1488F3(m1488F3, m1488F34, m1488F33, m1488F32, this.f2189X[9], 14);
        int m1488F36 = m1488F3(m1488F32, m1488F35, m1488F34, m1488F33, this.f2189X[15], 9);
        int m1488F37 = m1488F3(m1488F33, m1488F36, m1488F35, m1488F34, this.f2189X[8], 13);
        int m1488F38 = m1488F3(m1488F34, m1488F37, m1488F36, m1488F35, this.f2189X[1], 15);
        int m1488F39 = m1488F3(m1488F35, m1488F38, m1488F37, m1488F36, this.f2189X[2], 14);
        int m1488F310 = m1488F3(m1488F36, m1488F39, m1488F38, m1488F37, this.f2189X[7], 8);
        int m1488F311 = m1488F3(m1488F37, m1488F310, m1488F39, m1488F38, this.f2189X[0], 13);
        int m1488F312 = m1488F3(m1488F38, m1488F311, m1488F310, m1488F39, this.f2189X[6], 6);
        int m1488F313 = m1488F3(m1488F39, m1488F312, m1488F311, m1488F310, this.f2189X[13], 5);
        int m1488F314 = m1488F3(m1488F310, m1488F313, m1488F312, m1488F311, this.f2189X[11], 12);
        int m1488F315 = m1488F3(m1488F311, m1488F314, m1488F313, m1488F312, this.f2189X[5], 7);
        int m1488F316 = m1488F3(m1488F312, m1488F315, m1488F314, m1488F313, this.f2189X[12], 5);
        int FF2 = FF2(FF313, m1487F216, FF315, FF314, this.f2189X[15], 9);
        int FF22 = FF2(FF314, FF2, m1487F216, FF315, this.f2189X[5], 7);
        int FF23 = FF2(FF315, FF22, FF2, m1487F216, this.f2189X[1], 15);
        int FF24 = FF2(m1487F216, FF23, FF22, FF2, this.f2189X[3], 11);
        int FF25 = FF2(FF2, FF24, FF23, FF22, this.f2189X[7], 8);
        int FF26 = FF2(FF22, FF25, FF24, FF23, this.f2189X[14], 6);
        int FF27 = FF2(FF23, FF26, FF25, FF24, this.f2189X[6], 6);
        int FF28 = FF2(FF24, FF27, FF26, FF25, this.f2189X[9], 14);
        int FF29 = FF2(FF25, FF28, FF27, FF26, this.f2189X[11], 12);
        int FF210 = FF2(FF26, FF29, FF28, FF27, this.f2189X[8], 13);
        int FF211 = FF2(FF27, FF210, FF29, FF28, this.f2189X[12], 5);
        int FF212 = FF2(FF28, FF211, FF210, FF29, this.f2189X[2], 14);
        int FF213 = FF2(FF29, FF212, FF211, FF210, this.f2189X[10], 13);
        int FF214 = FF2(FF210, FF213, FF212, FF211, this.f2189X[0], 13);
        int FF215 = FF2(FF211, FF214, FF213, FF212, this.f2189X[4], 7);
        int FF216 = FF2(FF212, FF215, FF214, FF213, this.f2189X[13], 5);
        int m1489F4 = m1489F4(m1488F313, m1488F316, FF215, m1488F314, this.f2189X[1], 11);
        int m1489F42 = m1489F4(m1488F314, m1489F4, m1488F316, FF215, this.f2189X[9], 12);
        int m1489F43 = m1489F4(FF215, m1489F42, m1489F4, m1488F316, this.f2189X[11], 14);
        int m1489F44 = m1489F4(m1488F316, m1489F43, m1489F42, m1489F4, this.f2189X[10], 15);
        int m1489F45 = m1489F4(m1489F4, m1489F44, m1489F43, m1489F42, this.f2189X[0], 14);
        int m1489F46 = m1489F4(m1489F42, m1489F45, m1489F44, m1489F43, this.f2189X[8], 15);
        int m1489F47 = m1489F4(m1489F43, m1489F46, m1489F45, m1489F44, this.f2189X[12], 9);
        int m1489F48 = m1489F4(m1489F44, m1489F47, m1489F46, m1489F45, this.f2189X[4], 8);
        int m1489F49 = m1489F4(m1489F45, m1489F48, m1489F47, m1489F46, this.f2189X[13], 9);
        int m1489F410 = m1489F4(m1489F46, m1489F49, m1489F48, m1489F47, this.f2189X[3], 14);
        int m1489F411 = m1489F4(m1489F47, m1489F410, m1489F49, m1489F48, this.f2189X[7], 5);
        int m1489F412 = m1489F4(m1489F48, m1489F411, m1489F410, m1489F49, this.f2189X[15], 6);
        int m1489F413 = m1489F4(m1489F49, m1489F412, m1489F411, m1489F410, this.f2189X[14], 8);
        int m1489F414 = m1489F4(m1489F410, m1489F413, m1489F412, m1489F411, this.f2189X[5], 6);
        int m1489F415 = m1489F4(m1489F411, m1489F414, m1489F413, m1489F412, this.f2189X[6], 5);
        int m1489F416 = m1489F4(m1489F412, m1489F415, m1489F414, m1489F413, this.f2189X[2], 12);
        int FF1 = FF1(FF213, FF216, m1488F315, FF214, this.f2189X[8], 15);
        int FF12 = FF1(FF214, FF1, FF216, m1488F315, this.f2189X[6], 5);
        int FF13 = FF1(m1488F315, FF12, FF1, FF216, this.f2189X[4], 8);
        int FF14 = FF1(FF216, FF13, FF12, FF1, this.f2189X[1], 11);
        int FF15 = FF1(FF1, FF14, FF13, FF12, this.f2189X[3], 14);
        int FF16 = FF1(FF12, FF15, FF14, FF13, this.f2189X[11], 14);
        int FF17 = FF1(FF13, FF16, FF15, FF14, this.f2189X[15], 6);
        int FF18 = FF1(FF14, FF17, FF16, FF15, this.f2189X[0], 14);
        int FF19 = FF1(FF15, FF18, FF17, FF16, this.f2189X[5], 6);
        int FF110 = FF1(FF16, FF19, FF18, FF17, this.f2189X[12], 9);
        int FF111 = FF1(FF17, FF110, FF19, FF18, this.f2189X[2], 12);
        int FF112 = FF1(FF18, FF111, FF110, FF19, this.f2189X[13], 9);
        int FF113 = FF1(FF19, FF112, FF111, FF110, this.f2189X[9], 12);
        int FF114 = FF1(FF110, FF113, FF112, FF111, this.f2189X[7], 5);
        int FF115 = FF1(FF111, FF114, FF113, FF112, this.f2189X[10], 15);
        int FF116 = FF1(FF112, FF115, FF114, FF113, this.f2189X[14], 8);
        this.f2181H0 += m1489F413;
        this.f2182H1 += m1489F416;
        this.f2183H2 += m1489F415;
        this.f2184H3 += FF114;
        this.f2185H4 += FF113;
        this.f2186H5 += FF116;
        this.f2187H6 += FF115;
        this.f2188H7 += m1489F414;
        this.xOff = 0;
        int i9 = 0;
        while (true) {
            int[] iArr = this.f2189X;
            if (i9 == iArr.length) {
                return;
            }
            iArr[i9] = 0;
            i9++;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD256Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD256Digest) memoable);
    }
}
