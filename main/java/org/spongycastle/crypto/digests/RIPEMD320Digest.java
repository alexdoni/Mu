package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

/* loaded from: classes3.dex */
public class RIPEMD320Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;

    /* renamed from: H0 */
    private int f2190H0;

    /* renamed from: H1 */
    private int f2191H1;

    /* renamed from: H2 */
    private int f2192H2;

    /* renamed from: H3 */
    private int f2193H3;

    /* renamed from: H4 */
    private int f2194H4;

    /* renamed from: H5 */
    private int f2195H5;

    /* renamed from: H6 */
    private int f2196H6;

    /* renamed from: H7 */
    private int f2197H7;

    /* renamed from: H8 */
    private int f2198H8;

    /* renamed from: H9 */
    private int f2199H9;

    /* renamed from: X */
    private int[] f2200X;
    private int xOff;

    /* renamed from: RL */
    private int m1495RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: f1 */
    private int m1496f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m1497f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m1498f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m1499f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m1500f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 40;
    }

    public RIPEMD320Digest() {
        this.f2200X = new int[16];
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest);
        this.f2200X = new int[16];
        doCopy(rIPEMD320Digest);
    }

    private void doCopy(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.f2190H0 = rIPEMD320Digest.f2190H0;
        this.f2191H1 = rIPEMD320Digest.f2191H1;
        this.f2192H2 = rIPEMD320Digest.f2192H2;
        this.f2193H3 = rIPEMD320Digest.f2193H3;
        this.f2194H4 = rIPEMD320Digest.f2194H4;
        this.f2195H5 = rIPEMD320Digest.f2195H5;
        this.f2196H6 = rIPEMD320Digest.f2196H6;
        this.f2197H7 = rIPEMD320Digest.f2197H7;
        this.f2198H8 = rIPEMD320Digest.f2198H8;
        this.f2199H9 = rIPEMD320Digest.f2199H9;
        int[] iArr = rIPEMD320Digest.f2200X;
        System.arraycopy(iArr, 0, this.f2200X, 0, iArr.length);
        this.xOff = rIPEMD320Digest.xOff;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f2200X;
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
        int[] iArr = this.f2200X;
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
        unpackWord(this.f2190H0, bArr, i);
        unpackWord(this.f2191H1, bArr, i + 4);
        unpackWord(this.f2192H2, bArr, i + 8);
        unpackWord(this.f2193H3, bArr, i + 12);
        unpackWord(this.f2194H4, bArr, i + 16);
        unpackWord(this.f2195H5, bArr, i + 20);
        unpackWord(this.f2196H6, bArr, i + 24);
        unpackWord(this.f2197H7, bArr, i + 28);
        unpackWord(this.f2198H8, bArr, i + 32);
        unpackWord(this.f2199H9, bArr, i + 36);
        reset();
        return 40;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2190H0 = 1732584193;
        this.f2191H1 = -271733879;
        this.f2192H2 = -1732584194;
        this.f2193H3 = 271733878;
        this.f2194H4 = -1009589776;
        this.f2195H5 = 1985229328;
        this.f2196H6 = -19088744;
        this.f2197H7 = -1985229329;
        this.f2198H8 = 19088743;
        this.f2199H9 = 1009589775;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f2200X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f2190H0;
        int i2 = this.f2191H1;
        int i3 = this.f2192H2;
        int i4 = this.f2193H3;
        int i5 = this.f2194H4;
        int i6 = this.f2195H5;
        int i7 = this.f2196H6;
        int i8 = this.f2197H7;
        int i9 = this.f2198H8;
        int i10 = this.f2199H9;
        int m1495RL = m1495RL(i + m1496f1(i2, i3, i4) + this.f2200X[0], 11) + i5;
        int m1495RL2 = m1495RL(i3, 10);
        int m1495RL3 = m1495RL(i5 + m1496f1(m1495RL, i2, m1495RL2) + this.f2200X[1], 14) + i4;
        int m1495RL4 = m1495RL(i2, 10);
        int m1495RL5 = m1495RL(i4 + m1496f1(m1495RL3, m1495RL, m1495RL4) + this.f2200X[2], 15) + m1495RL2;
        int m1495RL6 = m1495RL(m1495RL, 10);
        int m1495RL7 = m1495RL(m1495RL2 + m1496f1(m1495RL5, m1495RL3, m1495RL6) + this.f2200X[3], 12) + m1495RL4;
        int m1495RL8 = m1495RL(m1495RL3, 10);
        int m1495RL9 = m1495RL(m1495RL4 + m1496f1(m1495RL7, m1495RL5, m1495RL8) + this.f2200X[4], 5) + m1495RL6;
        int m1495RL10 = m1495RL(m1495RL5, 10);
        int m1495RL11 = m1495RL(m1495RL6 + m1496f1(m1495RL9, m1495RL7, m1495RL10) + this.f2200X[5], 8) + m1495RL8;
        int m1495RL12 = m1495RL(m1495RL7, 10);
        int m1495RL13 = m1495RL(m1495RL8 + m1496f1(m1495RL11, m1495RL9, m1495RL12) + this.f2200X[6], 7) + m1495RL10;
        int m1495RL14 = m1495RL(m1495RL9, 10);
        int m1495RL15 = m1495RL(m1495RL10 + m1496f1(m1495RL13, m1495RL11, m1495RL14) + this.f2200X[7], 9) + m1495RL12;
        int m1495RL16 = m1495RL(m1495RL11, 10);
        int m1495RL17 = m1495RL(m1495RL12 + m1496f1(m1495RL15, m1495RL13, m1495RL16) + this.f2200X[8], 11) + m1495RL14;
        int m1495RL18 = m1495RL(m1495RL13, 10);
        int m1495RL19 = m1495RL(m1495RL14 + m1496f1(m1495RL17, m1495RL15, m1495RL18) + this.f2200X[9], 13) + m1495RL16;
        int m1495RL20 = m1495RL(m1495RL15, 10);
        int m1495RL21 = m1495RL(m1495RL16 + m1496f1(m1495RL19, m1495RL17, m1495RL20) + this.f2200X[10], 14) + m1495RL18;
        int m1495RL22 = m1495RL(m1495RL17, 10);
        int m1495RL23 = m1495RL(m1495RL18 + m1496f1(m1495RL21, m1495RL19, m1495RL22) + this.f2200X[11], 15) + m1495RL20;
        int m1495RL24 = m1495RL(m1495RL19, 10);
        int m1495RL25 = m1495RL(m1495RL20 + m1496f1(m1495RL23, m1495RL21, m1495RL24) + this.f2200X[12], 6) + m1495RL22;
        int m1495RL26 = m1495RL(m1495RL21, 10);
        int m1495RL27 = m1495RL(m1495RL22 + m1496f1(m1495RL25, m1495RL23, m1495RL26) + this.f2200X[13], 7) + m1495RL24;
        int m1495RL28 = m1495RL(m1495RL23, 10);
        int m1495RL29 = m1495RL(m1495RL24 + m1496f1(m1495RL27, m1495RL25, m1495RL28) + this.f2200X[14], 9) + m1495RL26;
        int m1495RL30 = m1495RL(m1495RL25, 10);
        int m1495RL31 = m1495RL(m1495RL26 + m1496f1(m1495RL29, m1495RL27, m1495RL30) + this.f2200X[15], 8) + m1495RL28;
        int m1495RL32 = m1495RL(m1495RL27, 10);
        int m1495RL33 = m1495RL(i6 + m1500f5(i7, i8, i9) + this.f2200X[5] + 1352829926, 8) + i10;
        int m1495RL34 = m1495RL(i8, 10);
        int m1495RL35 = m1495RL(i10 + m1500f5(m1495RL33, i7, m1495RL34) + this.f2200X[14] + 1352829926, 9) + i9;
        int m1495RL36 = m1495RL(i7, 10);
        int m1495RL37 = m1495RL(i9 + m1500f5(m1495RL35, m1495RL33, m1495RL36) + this.f2200X[7] + 1352829926, 9) + m1495RL34;
        int m1495RL38 = m1495RL(m1495RL33, 10);
        int m1495RL39 = m1495RL(m1495RL34 + m1500f5(m1495RL37, m1495RL35, m1495RL38) + this.f2200X[0] + 1352829926, 11) + m1495RL36;
        int m1495RL40 = m1495RL(m1495RL35, 10);
        int m1495RL41 = m1495RL(m1495RL36 + m1500f5(m1495RL39, m1495RL37, m1495RL40) + this.f2200X[9] + 1352829926, 13) + m1495RL38;
        int m1495RL42 = m1495RL(m1495RL37, 10);
        int m1495RL43 = m1495RL(m1495RL38 + m1500f5(m1495RL41, m1495RL39, m1495RL42) + this.f2200X[2] + 1352829926, 15) + m1495RL40;
        int m1495RL44 = m1495RL(m1495RL39, 10);
        int m1495RL45 = m1495RL(m1495RL40 + m1500f5(m1495RL43, m1495RL41, m1495RL44) + this.f2200X[11] + 1352829926, 15) + m1495RL42;
        int m1495RL46 = m1495RL(m1495RL41, 10);
        int m1495RL47 = m1495RL(m1495RL42 + m1500f5(m1495RL45, m1495RL43, m1495RL46) + this.f2200X[4] + 1352829926, 5) + m1495RL44;
        int m1495RL48 = m1495RL(m1495RL43, 10);
        int m1495RL49 = m1495RL(m1495RL44 + m1500f5(m1495RL47, m1495RL45, m1495RL48) + this.f2200X[13] + 1352829926, 7) + m1495RL46;
        int m1495RL50 = m1495RL(m1495RL45, 10);
        int m1495RL51 = m1495RL(m1495RL46 + m1500f5(m1495RL49, m1495RL47, m1495RL50) + this.f2200X[6] + 1352829926, 7) + m1495RL48;
        int m1495RL52 = m1495RL(m1495RL47, 10);
        int m1495RL53 = m1495RL(m1495RL48 + m1500f5(m1495RL51, m1495RL49, m1495RL52) + this.f2200X[15] + 1352829926, 8) + m1495RL50;
        int m1495RL54 = m1495RL(m1495RL49, 10);
        int m1495RL55 = m1495RL(m1495RL50 + m1500f5(m1495RL53, m1495RL51, m1495RL54) + this.f2200X[8] + 1352829926, 11) + m1495RL52;
        int m1495RL56 = m1495RL(m1495RL51, 10);
        int m1495RL57 = m1495RL(m1495RL52 + m1500f5(m1495RL55, m1495RL53, m1495RL56) + this.f2200X[1] + 1352829926, 14) + m1495RL54;
        int m1495RL58 = m1495RL(m1495RL53, 10);
        int m1495RL59 = m1495RL(m1495RL54 + m1500f5(m1495RL57, m1495RL55, m1495RL58) + this.f2200X[10] + 1352829926, 14) + m1495RL56;
        int m1495RL60 = m1495RL(m1495RL55, 10);
        int m1495RL61 = m1495RL(m1495RL56 + m1500f5(m1495RL59, m1495RL57, m1495RL60) + this.f2200X[3] + 1352829926, 12) + m1495RL58;
        int m1495RL62 = m1495RL(m1495RL57, 10);
        int m1495RL63 = m1495RL(m1495RL58 + m1500f5(m1495RL61, m1495RL59, m1495RL62) + this.f2200X[12] + 1352829926, 6) + m1495RL60;
        int m1495RL64 = m1495RL(m1495RL59, 10);
        int m1495RL65 = m1495RL(m1495RL28 + m1497f2(m1495RL63, m1495RL29, m1495RL32) + this.f2200X[7] + 1518500249, 7) + m1495RL30;
        int m1495RL66 = m1495RL(m1495RL29, 10);
        int m1495RL67 = m1495RL(m1495RL30 + m1497f2(m1495RL65, m1495RL63, m1495RL66) + this.f2200X[4] + 1518500249, 6) + m1495RL32;
        int m1495RL68 = m1495RL(m1495RL63, 10);
        int m1495RL69 = m1495RL(m1495RL32 + m1497f2(m1495RL67, m1495RL65, m1495RL68) + this.f2200X[13] + 1518500249, 8) + m1495RL66;
        int m1495RL70 = m1495RL(m1495RL65, 10);
        int m1495RL71 = m1495RL(m1495RL66 + m1497f2(m1495RL69, m1495RL67, m1495RL70) + this.f2200X[1] + 1518500249, 13) + m1495RL68;
        int m1495RL72 = m1495RL(m1495RL67, 10);
        int m1495RL73 = m1495RL(m1495RL68 + m1497f2(m1495RL71, m1495RL69, m1495RL72) + this.f2200X[10] + 1518500249, 11) + m1495RL70;
        int m1495RL74 = m1495RL(m1495RL69, 10);
        int m1495RL75 = m1495RL(m1495RL70 + m1497f2(m1495RL73, m1495RL71, m1495RL74) + this.f2200X[6] + 1518500249, 9) + m1495RL72;
        int m1495RL76 = m1495RL(m1495RL71, 10);
        int m1495RL77 = m1495RL(m1495RL72 + m1497f2(m1495RL75, m1495RL73, m1495RL76) + this.f2200X[15] + 1518500249, 7) + m1495RL74;
        int m1495RL78 = m1495RL(m1495RL73, 10);
        int m1495RL79 = m1495RL(m1495RL74 + m1497f2(m1495RL77, m1495RL75, m1495RL78) + this.f2200X[3] + 1518500249, 15) + m1495RL76;
        int m1495RL80 = m1495RL(m1495RL75, 10);
        int m1495RL81 = m1495RL(m1495RL76 + m1497f2(m1495RL79, m1495RL77, m1495RL80) + this.f2200X[12] + 1518500249, 7) + m1495RL78;
        int m1495RL82 = m1495RL(m1495RL77, 10);
        int m1495RL83 = m1495RL(m1495RL78 + m1497f2(m1495RL81, m1495RL79, m1495RL82) + this.f2200X[0] + 1518500249, 12) + m1495RL80;
        int m1495RL84 = m1495RL(m1495RL79, 10);
        int m1495RL85 = m1495RL(m1495RL80 + m1497f2(m1495RL83, m1495RL81, m1495RL84) + this.f2200X[9] + 1518500249, 15) + m1495RL82;
        int m1495RL86 = m1495RL(m1495RL81, 10);
        int m1495RL87 = m1495RL(m1495RL82 + m1497f2(m1495RL85, m1495RL83, m1495RL86) + this.f2200X[5] + 1518500249, 9) + m1495RL84;
        int m1495RL88 = m1495RL(m1495RL83, 10);
        int m1495RL89 = m1495RL(m1495RL84 + m1497f2(m1495RL87, m1495RL85, m1495RL88) + this.f2200X[2] + 1518500249, 11) + m1495RL86;
        int m1495RL90 = m1495RL(m1495RL85, 10);
        int m1495RL91 = m1495RL(m1495RL86 + m1497f2(m1495RL89, m1495RL87, m1495RL90) + this.f2200X[14] + 1518500249, 7) + m1495RL88;
        int m1495RL92 = m1495RL(m1495RL87, 10);
        int m1495RL93 = m1495RL(m1495RL88 + m1497f2(m1495RL91, m1495RL89, m1495RL92) + this.f2200X[11] + 1518500249, 13) + m1495RL90;
        int m1495RL94 = m1495RL(m1495RL89, 10);
        int m1495RL95 = m1495RL(m1495RL90 + m1497f2(m1495RL93, m1495RL91, m1495RL94) + this.f2200X[8] + 1518500249, 12) + m1495RL92;
        int m1495RL96 = m1495RL(m1495RL91, 10);
        int m1495RL97 = m1495RL(m1495RL60 + m1499f4(m1495RL31, m1495RL61, m1495RL64) + this.f2200X[6] + 1548603684, 9) + m1495RL62;
        int m1495RL98 = m1495RL(m1495RL61, 10);
        int m1495RL99 = m1495RL(m1495RL62 + m1499f4(m1495RL97, m1495RL31, m1495RL98) + this.f2200X[11] + 1548603684, 13) + m1495RL64;
        int m1495RL100 = m1495RL(m1495RL31, 10);
        int m1495RL101 = m1495RL(m1495RL64 + m1499f4(m1495RL99, m1495RL97, m1495RL100) + this.f2200X[3] + 1548603684, 15) + m1495RL98;
        int m1495RL102 = m1495RL(m1495RL97, 10);
        int m1495RL103 = m1495RL(m1495RL98 + m1499f4(m1495RL101, m1495RL99, m1495RL102) + this.f2200X[7] + 1548603684, 7) + m1495RL100;
        int m1495RL104 = m1495RL(m1495RL99, 10);
        int m1495RL105 = m1495RL(m1495RL100 + m1499f4(m1495RL103, m1495RL101, m1495RL104) + this.f2200X[0] + 1548603684, 12) + m1495RL102;
        int m1495RL106 = m1495RL(m1495RL101, 10);
        int m1495RL107 = m1495RL(m1495RL102 + m1499f4(m1495RL105, m1495RL103, m1495RL106) + this.f2200X[13] + 1548603684, 8) + m1495RL104;
        int m1495RL108 = m1495RL(m1495RL103, 10);
        int m1495RL109 = m1495RL(m1495RL104 + m1499f4(m1495RL107, m1495RL105, m1495RL108) + this.f2200X[5] + 1548603684, 9) + m1495RL106;
        int m1495RL110 = m1495RL(m1495RL105, 10);
        int m1495RL111 = m1495RL(m1495RL106 + m1499f4(m1495RL109, m1495RL107, m1495RL110) + this.f2200X[10] + 1548603684, 11) + m1495RL108;
        int m1495RL112 = m1495RL(m1495RL107, 10);
        int m1495RL113 = m1495RL(m1495RL108 + m1499f4(m1495RL111, m1495RL109, m1495RL112) + this.f2200X[14] + 1548603684, 7) + m1495RL110;
        int m1495RL114 = m1495RL(m1495RL109, 10);
        int m1495RL115 = m1495RL(m1495RL110 + m1499f4(m1495RL113, m1495RL111, m1495RL114) + this.f2200X[15] + 1548603684, 7) + m1495RL112;
        int m1495RL116 = m1495RL(m1495RL111, 10);
        int m1495RL117 = m1495RL(m1495RL112 + m1499f4(m1495RL115, m1495RL113, m1495RL116) + this.f2200X[8] + 1548603684, 12) + m1495RL114;
        int m1495RL118 = m1495RL(m1495RL113, 10);
        int m1495RL119 = m1495RL(m1495RL114 + m1499f4(m1495RL117, m1495RL115, m1495RL118) + this.f2200X[12] + 1548603684, 7) + m1495RL116;
        int m1495RL120 = m1495RL(m1495RL115, 10);
        int m1495RL121 = m1495RL(m1495RL116 + m1499f4(m1495RL119, m1495RL117, m1495RL120) + this.f2200X[4] + 1548603684, 6) + m1495RL118;
        int m1495RL122 = m1495RL(m1495RL117, 10);
        int m1495RL123 = m1495RL(m1495RL118 + m1499f4(m1495RL121, m1495RL119, m1495RL122) + this.f2200X[9] + 1548603684, 15) + m1495RL120;
        int m1495RL124 = m1495RL(m1495RL119, 10);
        int m1495RL125 = m1495RL(m1495RL120 + m1499f4(m1495RL123, m1495RL121, m1495RL124) + this.f2200X[1] + 1548603684, 13) + m1495RL122;
        int m1495RL126 = m1495RL(m1495RL121, 10);
        int m1495RL127 = m1495RL(m1495RL122 + m1499f4(m1495RL125, m1495RL123, m1495RL126) + this.f2200X[2] + 1548603684, 11) + m1495RL124;
        int m1495RL128 = m1495RL(m1495RL123, 10);
        int m1495RL129 = m1495RL(m1495RL92 + m1498f3(m1495RL95, m1495RL93, m1495RL128) + this.f2200X[3] + 1859775393, 11) + m1495RL94;
        int m1495RL130 = m1495RL(m1495RL93, 10);
        int m1495RL131 = m1495RL(m1495RL94 + m1498f3(m1495RL129, m1495RL95, m1495RL130) + this.f2200X[10] + 1859775393, 13) + m1495RL128;
        int m1495RL132 = m1495RL(m1495RL95, 10);
        int m1495RL133 = m1495RL(m1495RL128 + m1498f3(m1495RL131, m1495RL129, m1495RL132) + this.f2200X[14] + 1859775393, 6) + m1495RL130;
        int m1495RL134 = m1495RL(m1495RL129, 10);
        int m1495RL135 = m1495RL(m1495RL130 + m1498f3(m1495RL133, m1495RL131, m1495RL134) + this.f2200X[4] + 1859775393, 7) + m1495RL132;
        int m1495RL136 = m1495RL(m1495RL131, 10);
        int m1495RL137 = m1495RL(m1495RL132 + m1498f3(m1495RL135, m1495RL133, m1495RL136) + this.f2200X[9] + 1859775393, 14) + m1495RL134;
        int m1495RL138 = m1495RL(m1495RL133, 10);
        int m1495RL139 = m1495RL(m1495RL134 + m1498f3(m1495RL137, m1495RL135, m1495RL138) + this.f2200X[15] + 1859775393, 9) + m1495RL136;
        int m1495RL140 = m1495RL(m1495RL135, 10);
        int m1495RL141 = m1495RL(m1495RL136 + m1498f3(m1495RL139, m1495RL137, m1495RL140) + this.f2200X[8] + 1859775393, 13) + m1495RL138;
        int m1495RL142 = m1495RL(m1495RL137, 10);
        int m1495RL143 = m1495RL(m1495RL138 + m1498f3(m1495RL141, m1495RL139, m1495RL142) + this.f2200X[1] + 1859775393, 15) + m1495RL140;
        int m1495RL144 = m1495RL(m1495RL139, 10);
        int m1495RL145 = m1495RL(m1495RL140 + m1498f3(m1495RL143, m1495RL141, m1495RL144) + this.f2200X[2] + 1859775393, 14) + m1495RL142;
        int m1495RL146 = m1495RL(m1495RL141, 10);
        int m1495RL147 = m1495RL(m1495RL142 + m1498f3(m1495RL145, m1495RL143, m1495RL146) + this.f2200X[7] + 1859775393, 8) + m1495RL144;
        int m1495RL148 = m1495RL(m1495RL143, 10);
        int m1495RL149 = m1495RL(m1495RL144 + m1498f3(m1495RL147, m1495RL145, m1495RL148) + this.f2200X[0] + 1859775393, 13) + m1495RL146;
        int m1495RL150 = m1495RL(m1495RL145, 10);
        int m1495RL151 = m1495RL(m1495RL146 + m1498f3(m1495RL149, m1495RL147, m1495RL150) + this.f2200X[6] + 1859775393, 6) + m1495RL148;
        int m1495RL152 = m1495RL(m1495RL147, 10);
        int m1495RL153 = m1495RL(m1495RL148 + m1498f3(m1495RL151, m1495RL149, m1495RL152) + this.f2200X[13] + 1859775393, 5) + m1495RL150;
        int m1495RL154 = m1495RL(m1495RL149, 10);
        int m1495RL155 = m1495RL(m1495RL150 + m1498f3(m1495RL153, m1495RL151, m1495RL154) + this.f2200X[11] + 1859775393, 12) + m1495RL152;
        int m1495RL156 = m1495RL(m1495RL151, 10);
        int m1495RL157 = m1495RL(m1495RL152 + m1498f3(m1495RL155, m1495RL153, m1495RL156) + this.f2200X[5] + 1859775393, 7) + m1495RL154;
        int m1495RL158 = m1495RL(m1495RL153, 10);
        int m1495RL159 = m1495RL(m1495RL154 + m1498f3(m1495RL157, m1495RL155, m1495RL158) + this.f2200X[12] + 1859775393, 5) + m1495RL156;
        int m1495RL160 = m1495RL(m1495RL155, 10);
        int m1495RL161 = m1495RL(m1495RL124 + m1498f3(m1495RL127, m1495RL125, m1495RL96) + this.f2200X[15] + 1836072691, 9) + m1495RL126;
        int m1495RL162 = m1495RL(m1495RL125, 10);
        int m1495RL163 = m1495RL(m1495RL126 + m1498f3(m1495RL161, m1495RL127, m1495RL162) + this.f2200X[5] + 1836072691, 7) + m1495RL96;
        int m1495RL164 = m1495RL(m1495RL127, 10);
        int m1495RL165 = m1495RL(m1495RL96 + m1498f3(m1495RL163, m1495RL161, m1495RL164) + this.f2200X[1] + 1836072691, 15) + m1495RL162;
        int m1495RL166 = m1495RL(m1495RL161, 10);
        int m1495RL167 = m1495RL(m1495RL162 + m1498f3(m1495RL165, m1495RL163, m1495RL166) + this.f2200X[3] + 1836072691, 11) + m1495RL164;
        int m1495RL168 = m1495RL(m1495RL163, 10);
        int m1495RL169 = m1495RL(m1495RL164 + m1498f3(m1495RL167, m1495RL165, m1495RL168) + this.f2200X[7] + 1836072691, 8) + m1495RL166;
        int m1495RL170 = m1495RL(m1495RL165, 10);
        int m1495RL171 = m1495RL(m1495RL166 + m1498f3(m1495RL169, m1495RL167, m1495RL170) + this.f2200X[14] + 1836072691, 6) + m1495RL168;
        int m1495RL172 = m1495RL(m1495RL167, 10);
        int m1495RL173 = m1495RL(m1495RL168 + m1498f3(m1495RL171, m1495RL169, m1495RL172) + this.f2200X[6] + 1836072691, 6) + m1495RL170;
        int m1495RL174 = m1495RL(m1495RL169, 10);
        int m1495RL175 = m1495RL(m1495RL170 + m1498f3(m1495RL173, m1495RL171, m1495RL174) + this.f2200X[9] + 1836072691, 14) + m1495RL172;
        int m1495RL176 = m1495RL(m1495RL171, 10);
        int m1495RL177 = m1495RL(m1495RL172 + m1498f3(m1495RL175, m1495RL173, m1495RL176) + this.f2200X[11] + 1836072691, 12) + m1495RL174;
        int m1495RL178 = m1495RL(m1495RL173, 10);
        int m1495RL179 = m1495RL(m1495RL174 + m1498f3(m1495RL177, m1495RL175, m1495RL178) + this.f2200X[8] + 1836072691, 13) + m1495RL176;
        int m1495RL180 = m1495RL(m1495RL175, 10);
        int m1495RL181 = m1495RL(m1495RL176 + m1498f3(m1495RL179, m1495RL177, m1495RL180) + this.f2200X[12] + 1836072691, 5) + m1495RL178;
        int m1495RL182 = m1495RL(m1495RL177, 10);
        int m1495RL183 = m1495RL(m1495RL178 + m1498f3(m1495RL181, m1495RL179, m1495RL182) + this.f2200X[2] + 1836072691, 14) + m1495RL180;
        int m1495RL184 = m1495RL(m1495RL179, 10);
        int m1495RL185 = m1495RL(m1495RL180 + m1498f3(m1495RL183, m1495RL181, m1495RL184) + this.f2200X[10] + 1836072691, 13) + m1495RL182;
        int m1495RL186 = m1495RL(m1495RL181, 10);
        int m1495RL187 = m1495RL(m1495RL182 + m1498f3(m1495RL185, m1495RL183, m1495RL186) + this.f2200X[0] + 1836072691, 13) + m1495RL184;
        int m1495RL188 = m1495RL(m1495RL183, 10);
        int m1495RL189 = m1495RL(m1495RL184 + m1498f3(m1495RL187, m1495RL185, m1495RL188) + this.f2200X[4] + 1836072691, 7) + m1495RL186;
        int m1495RL190 = m1495RL(m1495RL185, 10);
        int m1495RL191 = m1495RL(m1495RL186 + m1498f3(m1495RL189, m1495RL187, m1495RL190) + this.f2200X[13] + 1836072691, 5) + m1495RL188;
        int m1495RL192 = m1495RL(m1495RL187, 10);
        int m1495RL193 = m1495RL(((m1495RL188 + m1499f4(m1495RL159, m1495RL157, m1495RL160)) + this.f2200X[1]) - 1894007588, 11) + m1495RL158;
        int m1495RL194 = m1495RL(m1495RL157, 10);
        int m1495RL195 = m1495RL(((m1495RL158 + m1499f4(m1495RL193, m1495RL159, m1495RL194)) + this.f2200X[9]) - 1894007588, 12) + m1495RL160;
        int m1495RL196 = m1495RL(m1495RL159, 10);
        int m1495RL197 = m1495RL(((m1495RL160 + m1499f4(m1495RL195, m1495RL193, m1495RL196)) + this.f2200X[11]) - 1894007588, 14) + m1495RL194;
        int m1495RL198 = m1495RL(m1495RL193, 10);
        int m1495RL199 = m1495RL(((m1495RL194 + m1499f4(m1495RL197, m1495RL195, m1495RL198)) + this.f2200X[10]) - 1894007588, 15) + m1495RL196;
        int m1495RL200 = m1495RL(m1495RL195, 10);
        int m1495RL201 = m1495RL(((m1495RL196 + m1499f4(m1495RL199, m1495RL197, m1495RL200)) + this.f2200X[0]) - 1894007588, 14) + m1495RL198;
        int m1495RL202 = m1495RL(m1495RL197, 10);
        int m1495RL203 = m1495RL(((m1495RL198 + m1499f4(m1495RL201, m1495RL199, m1495RL202)) + this.f2200X[8]) - 1894007588, 15) + m1495RL200;
        int m1495RL204 = m1495RL(m1495RL199, 10);
        int m1495RL205 = m1495RL(((m1495RL200 + m1499f4(m1495RL203, m1495RL201, m1495RL204)) + this.f2200X[12]) - 1894007588, 9) + m1495RL202;
        int m1495RL206 = m1495RL(m1495RL201, 10);
        int m1495RL207 = m1495RL(((m1495RL202 + m1499f4(m1495RL205, m1495RL203, m1495RL206)) + this.f2200X[4]) - 1894007588, 8) + m1495RL204;
        int m1495RL208 = m1495RL(m1495RL203, 10);
        int m1495RL209 = m1495RL(((m1495RL204 + m1499f4(m1495RL207, m1495RL205, m1495RL208)) + this.f2200X[13]) - 1894007588, 9) + m1495RL206;
        int m1495RL210 = m1495RL(m1495RL205, 10);
        int m1495RL211 = m1495RL(((m1495RL206 + m1499f4(m1495RL209, m1495RL207, m1495RL210)) + this.f2200X[3]) - 1894007588, 14) + m1495RL208;
        int m1495RL212 = m1495RL(m1495RL207, 10);
        int m1495RL213 = m1495RL(((m1495RL208 + m1499f4(m1495RL211, m1495RL209, m1495RL212)) + this.f2200X[7]) - 1894007588, 5) + m1495RL210;
        int m1495RL214 = m1495RL(m1495RL209, 10);
        int m1495RL215 = m1495RL(((m1495RL210 + m1499f4(m1495RL213, m1495RL211, m1495RL214)) + this.f2200X[15]) - 1894007588, 6) + m1495RL212;
        int m1495RL216 = m1495RL(m1495RL211, 10);
        int m1495RL217 = m1495RL(((m1495RL212 + m1499f4(m1495RL215, m1495RL213, m1495RL216)) + this.f2200X[14]) - 1894007588, 8) + m1495RL214;
        int m1495RL218 = m1495RL(m1495RL213, 10);
        int m1495RL219 = m1495RL(((m1495RL214 + m1499f4(m1495RL217, m1495RL215, m1495RL218)) + this.f2200X[5]) - 1894007588, 6) + m1495RL216;
        int m1495RL220 = m1495RL(m1495RL215, 10);
        int m1495RL221 = m1495RL(((m1495RL216 + m1499f4(m1495RL219, m1495RL217, m1495RL220)) + this.f2200X[6]) - 1894007588, 5) + m1495RL218;
        int m1495RL222 = m1495RL(m1495RL217, 10);
        int m1495RL223 = m1495RL(((m1495RL218 + m1499f4(m1495RL221, m1495RL219, m1495RL222)) + this.f2200X[2]) - 1894007588, 12) + m1495RL220;
        int m1495RL224 = m1495RL(m1495RL219, 10);
        int m1495RL225 = m1495RL(m1495RL156 + m1497f2(m1495RL191, m1495RL189, m1495RL192) + this.f2200X[8] + 2053994217, 15) + m1495RL190;
        int m1495RL226 = m1495RL(m1495RL189, 10);
        int m1495RL227 = m1495RL(m1495RL190 + m1497f2(m1495RL225, m1495RL191, m1495RL226) + this.f2200X[6] + 2053994217, 5) + m1495RL192;
        int m1495RL228 = m1495RL(m1495RL191, 10);
        int m1495RL229 = m1495RL(m1495RL192 + m1497f2(m1495RL227, m1495RL225, m1495RL228) + this.f2200X[4] + 2053994217, 8) + m1495RL226;
        int m1495RL230 = m1495RL(m1495RL225, 10);
        int m1495RL231 = m1495RL(m1495RL226 + m1497f2(m1495RL229, m1495RL227, m1495RL230) + this.f2200X[1] + 2053994217, 11) + m1495RL228;
        int m1495RL232 = m1495RL(m1495RL227, 10);
        int m1495RL233 = m1495RL(m1495RL228 + m1497f2(m1495RL231, m1495RL229, m1495RL232) + this.f2200X[3] + 2053994217, 14) + m1495RL230;
        int m1495RL234 = m1495RL(m1495RL229, 10);
        int m1495RL235 = m1495RL(m1495RL230 + m1497f2(m1495RL233, m1495RL231, m1495RL234) + this.f2200X[11] + 2053994217, 14) + m1495RL232;
        int m1495RL236 = m1495RL(m1495RL231, 10);
        int m1495RL237 = m1495RL(m1495RL232 + m1497f2(m1495RL235, m1495RL233, m1495RL236) + this.f2200X[15] + 2053994217, 6) + m1495RL234;
        int m1495RL238 = m1495RL(m1495RL233, 10);
        int m1495RL239 = m1495RL(m1495RL234 + m1497f2(m1495RL237, m1495RL235, m1495RL238) + this.f2200X[0] + 2053994217, 14) + m1495RL236;
        int m1495RL240 = m1495RL(m1495RL235, 10);
        int m1495RL241 = m1495RL(m1495RL236 + m1497f2(m1495RL239, m1495RL237, m1495RL240) + this.f2200X[5] + 2053994217, 6) + m1495RL238;
        int m1495RL242 = m1495RL(m1495RL237, 10);
        int m1495RL243 = m1495RL(m1495RL238 + m1497f2(m1495RL241, m1495RL239, m1495RL242) + this.f2200X[12] + 2053994217, 9) + m1495RL240;
        int m1495RL244 = m1495RL(m1495RL239, 10);
        int m1495RL245 = m1495RL(m1495RL240 + m1497f2(m1495RL243, m1495RL241, m1495RL244) + this.f2200X[2] + 2053994217, 12) + m1495RL242;
        int m1495RL246 = m1495RL(m1495RL241, 10);
        int m1495RL247 = m1495RL(m1495RL242 + m1497f2(m1495RL245, m1495RL243, m1495RL246) + this.f2200X[13] + 2053994217, 9) + m1495RL244;
        int m1495RL248 = m1495RL(m1495RL243, 10);
        int m1495RL249 = m1495RL(m1495RL244 + m1497f2(m1495RL247, m1495RL245, m1495RL248) + this.f2200X[9] + 2053994217, 12) + m1495RL246;
        int m1495RL250 = m1495RL(m1495RL245, 10);
        int m1495RL251 = m1495RL(m1495RL246 + m1497f2(m1495RL249, m1495RL247, m1495RL250) + this.f2200X[7] + 2053994217, 5) + m1495RL248;
        int m1495RL252 = m1495RL(m1495RL247, 10);
        int m1495RL253 = m1495RL(m1495RL248 + m1497f2(m1495RL251, m1495RL249, m1495RL252) + this.f2200X[10] + 2053994217, 15) + m1495RL250;
        int m1495RL254 = m1495RL(m1495RL249, 10);
        int m1495RL255 = m1495RL(m1495RL250 + m1497f2(m1495RL253, m1495RL251, m1495RL254) + this.f2200X[14] + 2053994217, 8) + m1495RL252;
        int m1495RL256 = m1495RL(m1495RL251, 10);
        int m1495RL257 = m1495RL(((m1495RL220 + m1500f5(m1495RL223, m1495RL253, m1495RL224)) + this.f2200X[4]) - 1454113458, 9) + m1495RL222;
        int m1495RL258 = m1495RL(m1495RL253, 10);
        int m1495RL259 = m1495RL(((m1495RL222 + m1500f5(m1495RL257, m1495RL223, m1495RL258)) + this.f2200X[0]) - 1454113458, 15) + m1495RL224;
        int m1495RL260 = m1495RL(m1495RL223, 10);
        int m1495RL261 = m1495RL(((m1495RL224 + m1500f5(m1495RL259, m1495RL257, m1495RL260)) + this.f2200X[5]) - 1454113458, 5) + m1495RL258;
        int m1495RL262 = m1495RL(m1495RL257, 10);
        int m1495RL263 = m1495RL(((m1495RL258 + m1500f5(m1495RL261, m1495RL259, m1495RL262)) + this.f2200X[9]) - 1454113458, 11) + m1495RL260;
        int m1495RL264 = m1495RL(m1495RL259, 10);
        int m1495RL265 = m1495RL(((m1495RL260 + m1500f5(m1495RL263, m1495RL261, m1495RL264)) + this.f2200X[7]) - 1454113458, 6) + m1495RL262;
        int m1495RL266 = m1495RL(m1495RL261, 10);
        int m1495RL267 = m1495RL(((m1495RL262 + m1500f5(m1495RL265, m1495RL263, m1495RL266)) + this.f2200X[12]) - 1454113458, 8) + m1495RL264;
        int m1495RL268 = m1495RL(m1495RL263, 10);
        int m1495RL269 = m1495RL(((m1495RL264 + m1500f5(m1495RL267, m1495RL265, m1495RL268)) + this.f2200X[2]) - 1454113458, 13) + m1495RL266;
        int m1495RL270 = m1495RL(m1495RL265, 10);
        int m1495RL271 = m1495RL(((m1495RL266 + m1500f5(m1495RL269, m1495RL267, m1495RL270)) + this.f2200X[10]) - 1454113458, 12) + m1495RL268;
        int m1495RL272 = m1495RL(m1495RL267, 10);
        int m1495RL273 = m1495RL(((m1495RL268 + m1500f5(m1495RL271, m1495RL269, m1495RL272)) + this.f2200X[14]) - 1454113458, 5) + m1495RL270;
        int m1495RL274 = m1495RL(m1495RL269, 10);
        int m1495RL275 = m1495RL(((m1495RL270 + m1500f5(m1495RL273, m1495RL271, m1495RL274)) + this.f2200X[1]) - 1454113458, 12) + m1495RL272;
        int m1495RL276 = m1495RL(m1495RL271, 10);
        int m1495RL277 = m1495RL(((m1495RL272 + m1500f5(m1495RL275, m1495RL273, m1495RL276)) + this.f2200X[3]) - 1454113458, 13) + m1495RL274;
        int m1495RL278 = m1495RL(m1495RL273, 10);
        int m1495RL279 = m1495RL(((m1495RL274 + m1500f5(m1495RL277, m1495RL275, m1495RL278)) + this.f2200X[8]) - 1454113458, 14) + m1495RL276;
        int m1495RL280 = m1495RL(m1495RL275, 10);
        int m1495RL281 = m1495RL(((m1495RL276 + m1500f5(m1495RL279, m1495RL277, m1495RL280)) + this.f2200X[11]) - 1454113458, 11) + m1495RL278;
        int m1495RL282 = m1495RL(m1495RL277, 10);
        int m1495RL283 = m1495RL(((m1495RL278 + m1500f5(m1495RL281, m1495RL279, m1495RL282)) + this.f2200X[6]) - 1454113458, 8) + m1495RL280;
        int m1495RL284 = m1495RL(m1495RL279, 10);
        int m1495RL285 = m1495RL(((m1495RL280 + m1500f5(m1495RL283, m1495RL281, m1495RL284)) + this.f2200X[15]) - 1454113458, 5) + m1495RL282;
        int m1495RL286 = m1495RL(m1495RL281, 10);
        int m1495RL287 = m1495RL(((m1495RL282 + m1500f5(m1495RL285, m1495RL283, m1495RL286)) + this.f2200X[13]) - 1454113458, 6) + m1495RL284;
        int m1495RL288 = m1495RL(m1495RL283, 10);
        int m1495RL289 = m1495RL(m1495RL252 + m1496f1(m1495RL255, m1495RL221, m1495RL256) + this.f2200X[12], 8) + m1495RL254;
        int m1495RL290 = m1495RL(m1495RL221, 10);
        int m1495RL291 = m1495RL(m1495RL254 + m1496f1(m1495RL289, m1495RL255, m1495RL290) + this.f2200X[15], 5) + m1495RL256;
        int m1495RL292 = m1495RL(m1495RL255, 10);
        int m1495RL293 = m1495RL(m1495RL256 + m1496f1(m1495RL291, m1495RL289, m1495RL292) + this.f2200X[10], 12) + m1495RL290;
        int m1495RL294 = m1495RL(m1495RL289, 10);
        int m1495RL295 = m1495RL(m1495RL290 + m1496f1(m1495RL293, m1495RL291, m1495RL294) + this.f2200X[4], 9) + m1495RL292;
        int m1495RL296 = m1495RL(m1495RL291, 10);
        int m1495RL297 = m1495RL(m1495RL292 + m1496f1(m1495RL295, m1495RL293, m1495RL296) + this.f2200X[1], 12) + m1495RL294;
        int m1495RL298 = m1495RL(m1495RL293, 10);
        int m1495RL299 = m1495RL(m1495RL294 + m1496f1(m1495RL297, m1495RL295, m1495RL298) + this.f2200X[5], 5) + m1495RL296;
        int m1495RL300 = m1495RL(m1495RL295, 10);
        int m1495RL301 = m1495RL(m1495RL296 + m1496f1(m1495RL299, m1495RL297, m1495RL300) + this.f2200X[8], 14) + m1495RL298;
        int m1495RL302 = m1495RL(m1495RL297, 10);
        int m1495RL303 = m1495RL(m1495RL298 + m1496f1(m1495RL301, m1495RL299, m1495RL302) + this.f2200X[7], 6) + m1495RL300;
        int m1495RL304 = m1495RL(m1495RL299, 10);
        int m1495RL305 = m1495RL(m1495RL300 + m1496f1(m1495RL303, m1495RL301, m1495RL304) + this.f2200X[6], 8) + m1495RL302;
        int m1495RL306 = m1495RL(m1495RL301, 10);
        int m1495RL307 = m1495RL(m1495RL302 + m1496f1(m1495RL305, m1495RL303, m1495RL306) + this.f2200X[2], 13) + m1495RL304;
        int m1495RL308 = m1495RL(m1495RL303, 10);
        int m1495RL309 = m1495RL(m1495RL304 + m1496f1(m1495RL307, m1495RL305, m1495RL308) + this.f2200X[13], 6) + m1495RL306;
        int m1495RL310 = m1495RL(m1495RL305, 10);
        int m1495RL311 = m1495RL(m1495RL306 + m1496f1(m1495RL309, m1495RL307, m1495RL310) + this.f2200X[14], 5) + m1495RL308;
        int m1495RL312 = m1495RL(m1495RL307, 10);
        int m1495RL313 = m1495RL(m1495RL308 + m1496f1(m1495RL311, m1495RL309, m1495RL312) + this.f2200X[0], 15) + m1495RL310;
        int m1495RL314 = m1495RL(m1495RL309, 10);
        int m1495RL315 = m1495RL(m1495RL310 + m1496f1(m1495RL313, m1495RL311, m1495RL314) + this.f2200X[3], 13) + m1495RL312;
        int m1495RL316 = m1495RL(m1495RL311, 10);
        int m1495RL317 = m1495RL(m1495RL312 + m1496f1(m1495RL315, m1495RL313, m1495RL316) + this.f2200X[9], 11) + m1495RL314;
        int m1495RL318 = m1495RL(m1495RL313, 10);
        int m1495RL319 = m1495RL(m1495RL314 + m1496f1(m1495RL317, m1495RL315, m1495RL318) + this.f2200X[11], 11) + m1495RL316;
        int m1495RL320 = m1495RL(m1495RL315, 10);
        this.f2190H0 += m1495RL284;
        this.f2191H1 += m1495RL287;
        this.f2192H2 += m1495RL285;
        this.f2193H3 += m1495RL288;
        this.f2194H4 += m1495RL318;
        this.f2195H5 += m1495RL316;
        this.f2196H6 += m1495RL319;
        this.f2197H7 += m1495RL317;
        this.f2198H8 += m1495RL320;
        this.f2199H9 += m1495RL286;
        this.xOff = 0;
        int i11 = 0;
        while (true) {
            int[] iArr = this.f2200X;
            if (i11 == iArr.length) {
                return;
            }
            iArr[i11] = 0;
            i11++;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        doCopy((RIPEMD320Digest) memoable);
    }
}
