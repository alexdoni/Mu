package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

/* loaded from: classes3.dex */
public class RIPEMD160Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: H0 */
    private int f2175H0;

    /* renamed from: H1 */
    private int f2176H1;

    /* renamed from: H2 */
    private int f2177H2;

    /* renamed from: H3 */
    private int f2178H3;

    /* renamed from: H4 */
    private int f2179H4;

    /* renamed from: X */
    private int[] f2180X;
    private int xOff;

    /* renamed from: RL */
    private int m1480RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: f1 */
    private int m1481f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m1482f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m1483f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m1484f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m1485f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD160";
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    public RIPEMD160Digest() {
        this.f2180X = new int[16];
        reset();
    }

    public RIPEMD160Digest(RIPEMD160Digest rIPEMD160Digest) {
        super(rIPEMD160Digest);
        this.f2180X = new int[16];
        copyIn(rIPEMD160Digest);
    }

    private void copyIn(RIPEMD160Digest rIPEMD160Digest) {
        super.copyIn((GeneralDigest) rIPEMD160Digest);
        this.f2175H0 = rIPEMD160Digest.f2175H0;
        this.f2176H1 = rIPEMD160Digest.f2176H1;
        this.f2177H2 = rIPEMD160Digest.f2177H2;
        this.f2178H3 = rIPEMD160Digest.f2178H3;
        this.f2179H4 = rIPEMD160Digest.f2179H4;
        int[] iArr = rIPEMD160Digest.f2180X;
        System.arraycopy(iArr, 0, this.f2180X, 0, iArr.length);
        this.xOff = rIPEMD160Digest.xOff;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f2180X;
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
        int[] iArr = this.f2180X;
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
        unpackWord(this.f2175H0, bArr, i);
        unpackWord(this.f2176H1, bArr, i + 4);
        unpackWord(this.f2177H2, bArr, i + 8);
        unpackWord(this.f2178H3, bArr, i + 12);
        unpackWord(this.f2179H4, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2175H0 = 1732584193;
        this.f2176H1 = -271733879;
        this.f2177H2 = -1732584194;
        this.f2178H3 = 271733878;
        this.f2179H4 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f2180X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.spongycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f2175H0;
        int i2 = this.f2176H1;
        int i3 = this.f2177H2;
        int i4 = this.f2178H3;
        int i5 = this.f2179H4;
        int m1480RL = m1480RL(m1481f1(i2, i3, i4) + i + this.f2180X[0], 11) + i5;
        int m1480RL2 = m1480RL(i3, 10);
        int m1480RL3 = m1480RL(m1481f1(m1480RL, i2, m1480RL2) + i5 + this.f2180X[1], 14) + i4;
        int m1480RL4 = m1480RL(i2, 10);
        int m1480RL5 = m1480RL(m1481f1(m1480RL3, m1480RL, m1480RL4) + i4 + this.f2180X[2], 15) + m1480RL2;
        int m1480RL6 = m1480RL(m1480RL, 10);
        int m1480RL7 = m1480RL(m1480RL2 + m1481f1(m1480RL5, m1480RL3, m1480RL6) + this.f2180X[3], 12) + m1480RL4;
        int m1480RL8 = m1480RL(m1480RL3, 10);
        int m1480RL9 = m1480RL(m1480RL4 + m1481f1(m1480RL7, m1480RL5, m1480RL8) + this.f2180X[4], 5) + m1480RL6;
        int m1480RL10 = m1480RL(m1480RL5, 10);
        int m1480RL11 = m1480RL(m1480RL6 + m1481f1(m1480RL9, m1480RL7, m1480RL10) + this.f2180X[5], 8) + m1480RL8;
        int m1480RL12 = m1480RL(m1480RL7, 10);
        int m1480RL13 = m1480RL(m1480RL8 + m1481f1(m1480RL11, m1480RL9, m1480RL12) + this.f2180X[6], 7) + m1480RL10;
        int m1480RL14 = m1480RL(m1480RL9, 10);
        int m1480RL15 = m1480RL(m1480RL10 + m1481f1(m1480RL13, m1480RL11, m1480RL14) + this.f2180X[7], 9) + m1480RL12;
        int m1480RL16 = m1480RL(m1480RL11, 10);
        int m1480RL17 = m1480RL(m1480RL12 + m1481f1(m1480RL15, m1480RL13, m1480RL16) + this.f2180X[8], 11) + m1480RL14;
        int m1480RL18 = m1480RL(m1480RL13, 10);
        int m1480RL19 = m1480RL(m1480RL14 + m1481f1(m1480RL17, m1480RL15, m1480RL18) + this.f2180X[9], 13) + m1480RL16;
        int m1480RL20 = m1480RL(m1480RL15, 10);
        int m1480RL21 = m1480RL(m1480RL16 + m1481f1(m1480RL19, m1480RL17, m1480RL20) + this.f2180X[10], 14) + m1480RL18;
        int m1480RL22 = m1480RL(m1480RL17, 10);
        int m1480RL23 = m1480RL(m1480RL18 + m1481f1(m1480RL21, m1480RL19, m1480RL22) + this.f2180X[11], 15) + m1480RL20;
        int m1480RL24 = m1480RL(m1480RL19, 10);
        int m1480RL25 = m1480RL(m1480RL20 + m1481f1(m1480RL23, m1480RL21, m1480RL24) + this.f2180X[12], 6) + m1480RL22;
        int m1480RL26 = m1480RL(m1480RL21, 10);
        int m1480RL27 = m1480RL(m1480RL22 + m1481f1(m1480RL25, m1480RL23, m1480RL26) + this.f2180X[13], 7) + m1480RL24;
        int m1480RL28 = m1480RL(m1480RL23, 10);
        int m1480RL29 = m1480RL(m1480RL24 + m1481f1(m1480RL27, m1480RL25, m1480RL28) + this.f2180X[14], 9) + m1480RL26;
        int m1480RL30 = m1480RL(m1480RL25, 10);
        int m1480RL31 = m1480RL(m1480RL26 + m1481f1(m1480RL29, m1480RL27, m1480RL30) + this.f2180X[15], 8) + m1480RL28;
        int m1480RL32 = m1480RL(m1480RL27, 10);
        int m1480RL33 = m1480RL(i + m1485f5(i2, i3, i4) + this.f2180X[5] + 1352829926, 8) + i5;
        int m1480RL34 = m1480RL(i3, 10);
        int m1480RL35 = m1480RL(i5 + m1485f5(m1480RL33, i2, m1480RL34) + this.f2180X[14] + 1352829926, 9) + i4;
        int m1480RL36 = m1480RL(i2, 10);
        int m1480RL37 = m1480RL(i4 + m1485f5(m1480RL35, m1480RL33, m1480RL36) + this.f2180X[7] + 1352829926, 9) + m1480RL34;
        int m1480RL38 = m1480RL(m1480RL33, 10);
        int m1480RL39 = m1480RL(m1480RL34 + m1485f5(m1480RL37, m1480RL35, m1480RL38) + this.f2180X[0] + 1352829926, 11) + m1480RL36;
        int m1480RL40 = m1480RL(m1480RL35, 10);
        int m1480RL41 = m1480RL(m1480RL36 + m1485f5(m1480RL39, m1480RL37, m1480RL40) + this.f2180X[9] + 1352829926, 13) + m1480RL38;
        int m1480RL42 = m1480RL(m1480RL37, 10);
        int m1480RL43 = m1480RL(m1480RL38 + m1485f5(m1480RL41, m1480RL39, m1480RL42) + this.f2180X[2] + 1352829926, 15) + m1480RL40;
        int m1480RL44 = m1480RL(m1480RL39, 10);
        int m1480RL45 = m1480RL(m1480RL40 + m1485f5(m1480RL43, m1480RL41, m1480RL44) + this.f2180X[11] + 1352829926, 15) + m1480RL42;
        int m1480RL46 = m1480RL(m1480RL41, 10);
        int m1480RL47 = m1480RL(m1480RL42 + m1485f5(m1480RL45, m1480RL43, m1480RL46) + this.f2180X[4] + 1352829926, 5) + m1480RL44;
        int m1480RL48 = m1480RL(m1480RL43, 10);
        int m1480RL49 = m1480RL(m1480RL44 + m1485f5(m1480RL47, m1480RL45, m1480RL48) + this.f2180X[13] + 1352829926, 7) + m1480RL46;
        int m1480RL50 = m1480RL(m1480RL45, 10);
        int m1480RL51 = m1480RL(m1480RL46 + m1485f5(m1480RL49, m1480RL47, m1480RL50) + this.f2180X[6] + 1352829926, 7) + m1480RL48;
        int m1480RL52 = m1480RL(m1480RL47, 10);
        int m1480RL53 = m1480RL(m1480RL48 + m1485f5(m1480RL51, m1480RL49, m1480RL52) + this.f2180X[15] + 1352829926, 8) + m1480RL50;
        int m1480RL54 = m1480RL(m1480RL49, 10);
        int m1480RL55 = m1480RL(m1480RL50 + m1485f5(m1480RL53, m1480RL51, m1480RL54) + this.f2180X[8] + 1352829926, 11) + m1480RL52;
        int m1480RL56 = m1480RL(m1480RL51, 10);
        int m1480RL57 = m1480RL(m1480RL52 + m1485f5(m1480RL55, m1480RL53, m1480RL56) + this.f2180X[1] + 1352829926, 14) + m1480RL54;
        int m1480RL58 = m1480RL(m1480RL53, 10);
        int m1480RL59 = m1480RL(m1480RL54 + m1485f5(m1480RL57, m1480RL55, m1480RL58) + this.f2180X[10] + 1352829926, 14) + m1480RL56;
        int m1480RL60 = m1480RL(m1480RL55, 10);
        int m1480RL61 = m1480RL(m1480RL56 + m1485f5(m1480RL59, m1480RL57, m1480RL60) + this.f2180X[3] + 1352829926, 12) + m1480RL58;
        int m1480RL62 = m1480RL(m1480RL57, 10);
        int m1480RL63 = m1480RL(m1480RL58 + m1485f5(m1480RL61, m1480RL59, m1480RL62) + this.f2180X[12] + 1352829926, 6) + m1480RL60;
        int m1480RL64 = m1480RL(m1480RL59, 10);
        int m1480RL65 = m1480RL(m1480RL28 + m1482f2(m1480RL31, m1480RL29, m1480RL32) + this.f2180X[7] + 1518500249, 7) + m1480RL30;
        int m1480RL66 = m1480RL(m1480RL29, 10);
        int m1480RL67 = m1480RL(m1480RL30 + m1482f2(m1480RL65, m1480RL31, m1480RL66) + this.f2180X[4] + 1518500249, 6) + m1480RL32;
        int m1480RL68 = m1480RL(m1480RL31, 10);
        int m1480RL69 = m1480RL(m1480RL32 + m1482f2(m1480RL67, m1480RL65, m1480RL68) + this.f2180X[13] + 1518500249, 8) + m1480RL66;
        int m1480RL70 = m1480RL(m1480RL65, 10);
        int m1480RL71 = m1480RL(m1480RL66 + m1482f2(m1480RL69, m1480RL67, m1480RL70) + this.f2180X[1] + 1518500249, 13) + m1480RL68;
        int m1480RL72 = m1480RL(m1480RL67, 10);
        int m1480RL73 = m1480RL(m1480RL68 + m1482f2(m1480RL71, m1480RL69, m1480RL72) + this.f2180X[10] + 1518500249, 11) + m1480RL70;
        int m1480RL74 = m1480RL(m1480RL69, 10);
        int m1480RL75 = m1480RL(m1480RL70 + m1482f2(m1480RL73, m1480RL71, m1480RL74) + this.f2180X[6] + 1518500249, 9) + m1480RL72;
        int m1480RL76 = m1480RL(m1480RL71, 10);
        int m1480RL77 = m1480RL(m1480RL72 + m1482f2(m1480RL75, m1480RL73, m1480RL76) + this.f2180X[15] + 1518500249, 7) + m1480RL74;
        int m1480RL78 = m1480RL(m1480RL73, 10);
        int m1480RL79 = m1480RL(m1480RL74 + m1482f2(m1480RL77, m1480RL75, m1480RL78) + this.f2180X[3] + 1518500249, 15) + m1480RL76;
        int m1480RL80 = m1480RL(m1480RL75, 10);
        int m1480RL81 = m1480RL(m1480RL76 + m1482f2(m1480RL79, m1480RL77, m1480RL80) + this.f2180X[12] + 1518500249, 7) + m1480RL78;
        int m1480RL82 = m1480RL(m1480RL77, 10);
        int m1480RL83 = m1480RL(m1480RL78 + m1482f2(m1480RL81, m1480RL79, m1480RL82) + this.f2180X[0] + 1518500249, 12) + m1480RL80;
        int m1480RL84 = m1480RL(m1480RL79, 10);
        int m1480RL85 = m1480RL(m1480RL80 + m1482f2(m1480RL83, m1480RL81, m1480RL84) + this.f2180X[9] + 1518500249, 15) + m1480RL82;
        int m1480RL86 = m1480RL(m1480RL81, 10);
        int m1480RL87 = m1480RL(m1480RL82 + m1482f2(m1480RL85, m1480RL83, m1480RL86) + this.f2180X[5] + 1518500249, 9) + m1480RL84;
        int m1480RL88 = m1480RL(m1480RL83, 10);
        int m1480RL89 = m1480RL(m1480RL84 + m1482f2(m1480RL87, m1480RL85, m1480RL88) + this.f2180X[2] + 1518500249, 11) + m1480RL86;
        int m1480RL90 = m1480RL(m1480RL85, 10);
        int m1480RL91 = m1480RL(m1480RL86 + m1482f2(m1480RL89, m1480RL87, m1480RL90) + this.f2180X[14] + 1518500249, 7) + m1480RL88;
        int m1480RL92 = m1480RL(m1480RL87, 10);
        int m1480RL93 = m1480RL(m1480RL88 + m1482f2(m1480RL91, m1480RL89, m1480RL92) + this.f2180X[11] + 1518500249, 13) + m1480RL90;
        int m1480RL94 = m1480RL(m1480RL89, 10);
        int m1480RL95 = m1480RL(m1480RL90 + m1482f2(m1480RL93, m1480RL91, m1480RL94) + this.f2180X[8] + 1518500249, 12) + m1480RL92;
        int m1480RL96 = m1480RL(m1480RL91, 10);
        int m1480RL97 = m1480RL(m1480RL60 + m1484f4(m1480RL63, m1480RL61, m1480RL64) + this.f2180X[6] + 1548603684, 9) + m1480RL62;
        int m1480RL98 = m1480RL(m1480RL61, 10);
        int m1480RL99 = m1480RL(m1480RL62 + m1484f4(m1480RL97, m1480RL63, m1480RL98) + this.f2180X[11] + 1548603684, 13) + m1480RL64;
        int m1480RL100 = m1480RL(m1480RL63, 10);
        int m1480RL101 = m1480RL(m1480RL64 + m1484f4(m1480RL99, m1480RL97, m1480RL100) + this.f2180X[3] + 1548603684, 15) + m1480RL98;
        int m1480RL102 = m1480RL(m1480RL97, 10);
        int m1480RL103 = m1480RL(m1480RL98 + m1484f4(m1480RL101, m1480RL99, m1480RL102) + this.f2180X[7] + 1548603684, 7) + m1480RL100;
        int m1480RL104 = m1480RL(m1480RL99, 10);
        int m1480RL105 = m1480RL(m1480RL100 + m1484f4(m1480RL103, m1480RL101, m1480RL104) + this.f2180X[0] + 1548603684, 12) + m1480RL102;
        int m1480RL106 = m1480RL(m1480RL101, 10);
        int m1480RL107 = m1480RL(m1480RL102 + m1484f4(m1480RL105, m1480RL103, m1480RL106) + this.f2180X[13] + 1548603684, 8) + m1480RL104;
        int m1480RL108 = m1480RL(m1480RL103, 10);
        int m1480RL109 = m1480RL(m1480RL104 + m1484f4(m1480RL107, m1480RL105, m1480RL108) + this.f2180X[5] + 1548603684, 9) + m1480RL106;
        int m1480RL110 = m1480RL(m1480RL105, 10);
        int m1480RL111 = m1480RL(m1480RL106 + m1484f4(m1480RL109, m1480RL107, m1480RL110) + this.f2180X[10] + 1548603684, 11) + m1480RL108;
        int m1480RL112 = m1480RL(m1480RL107, 10);
        int m1480RL113 = m1480RL(m1480RL108 + m1484f4(m1480RL111, m1480RL109, m1480RL112) + this.f2180X[14] + 1548603684, 7) + m1480RL110;
        int m1480RL114 = m1480RL(m1480RL109, 10);
        int m1480RL115 = m1480RL(m1480RL110 + m1484f4(m1480RL113, m1480RL111, m1480RL114) + this.f2180X[15] + 1548603684, 7) + m1480RL112;
        int m1480RL116 = m1480RL(m1480RL111, 10);
        int m1480RL117 = m1480RL(m1480RL112 + m1484f4(m1480RL115, m1480RL113, m1480RL116) + this.f2180X[8] + 1548603684, 12) + m1480RL114;
        int m1480RL118 = m1480RL(m1480RL113, 10);
        int m1480RL119 = m1480RL(m1480RL114 + m1484f4(m1480RL117, m1480RL115, m1480RL118) + this.f2180X[12] + 1548603684, 7) + m1480RL116;
        int m1480RL120 = m1480RL(m1480RL115, 10);
        int m1480RL121 = m1480RL(m1480RL116 + m1484f4(m1480RL119, m1480RL117, m1480RL120) + this.f2180X[4] + 1548603684, 6) + m1480RL118;
        int m1480RL122 = m1480RL(m1480RL117, 10);
        int m1480RL123 = m1480RL(m1480RL118 + m1484f4(m1480RL121, m1480RL119, m1480RL122) + this.f2180X[9] + 1548603684, 15) + m1480RL120;
        int m1480RL124 = m1480RL(m1480RL119, 10);
        int m1480RL125 = m1480RL(m1480RL120 + m1484f4(m1480RL123, m1480RL121, m1480RL124) + this.f2180X[1] + 1548603684, 13) + m1480RL122;
        int m1480RL126 = m1480RL(m1480RL121, 10);
        int m1480RL127 = m1480RL(m1480RL122 + m1484f4(m1480RL125, m1480RL123, m1480RL126) + this.f2180X[2] + 1548603684, 11) + m1480RL124;
        int m1480RL128 = m1480RL(m1480RL123, 10);
        int m1480RL129 = m1480RL(m1480RL92 + m1483f3(m1480RL95, m1480RL93, m1480RL96) + this.f2180X[3] + 1859775393, 11) + m1480RL94;
        int m1480RL130 = m1480RL(m1480RL93, 10);
        int m1480RL131 = m1480RL(m1480RL94 + m1483f3(m1480RL129, m1480RL95, m1480RL130) + this.f2180X[10] + 1859775393, 13) + m1480RL96;
        int m1480RL132 = m1480RL(m1480RL95, 10);
        int m1480RL133 = m1480RL(m1480RL96 + m1483f3(m1480RL131, m1480RL129, m1480RL132) + this.f2180X[14] + 1859775393, 6) + m1480RL130;
        int m1480RL134 = m1480RL(m1480RL129, 10);
        int m1480RL135 = m1480RL(m1480RL130 + m1483f3(m1480RL133, m1480RL131, m1480RL134) + this.f2180X[4] + 1859775393, 7) + m1480RL132;
        int m1480RL136 = m1480RL(m1480RL131, 10);
        int m1480RL137 = m1480RL(m1480RL132 + m1483f3(m1480RL135, m1480RL133, m1480RL136) + this.f2180X[9] + 1859775393, 14) + m1480RL134;
        int m1480RL138 = m1480RL(m1480RL133, 10);
        int m1480RL139 = m1480RL(m1480RL134 + m1483f3(m1480RL137, m1480RL135, m1480RL138) + this.f2180X[15] + 1859775393, 9) + m1480RL136;
        int m1480RL140 = m1480RL(m1480RL135, 10);
        int m1480RL141 = m1480RL(m1480RL136 + m1483f3(m1480RL139, m1480RL137, m1480RL140) + this.f2180X[8] + 1859775393, 13) + m1480RL138;
        int m1480RL142 = m1480RL(m1480RL137, 10);
        int m1480RL143 = m1480RL(m1480RL138 + m1483f3(m1480RL141, m1480RL139, m1480RL142) + this.f2180X[1] + 1859775393, 15) + m1480RL140;
        int m1480RL144 = m1480RL(m1480RL139, 10);
        int m1480RL145 = m1480RL(m1480RL140 + m1483f3(m1480RL143, m1480RL141, m1480RL144) + this.f2180X[2] + 1859775393, 14) + m1480RL142;
        int m1480RL146 = m1480RL(m1480RL141, 10);
        int m1480RL147 = m1480RL(m1480RL142 + m1483f3(m1480RL145, m1480RL143, m1480RL146) + this.f2180X[7] + 1859775393, 8) + m1480RL144;
        int m1480RL148 = m1480RL(m1480RL143, 10);
        int m1480RL149 = m1480RL(m1480RL144 + m1483f3(m1480RL147, m1480RL145, m1480RL148) + this.f2180X[0] + 1859775393, 13) + m1480RL146;
        int m1480RL150 = m1480RL(m1480RL145, 10);
        int m1480RL151 = m1480RL(m1480RL146 + m1483f3(m1480RL149, m1480RL147, m1480RL150) + this.f2180X[6] + 1859775393, 6) + m1480RL148;
        int m1480RL152 = m1480RL(m1480RL147, 10);
        int m1480RL153 = m1480RL(m1480RL148 + m1483f3(m1480RL151, m1480RL149, m1480RL152) + this.f2180X[13] + 1859775393, 5) + m1480RL150;
        int m1480RL154 = m1480RL(m1480RL149, 10);
        int m1480RL155 = m1480RL(m1480RL150 + m1483f3(m1480RL153, m1480RL151, m1480RL154) + this.f2180X[11] + 1859775393, 12) + m1480RL152;
        int m1480RL156 = m1480RL(m1480RL151, 10);
        int m1480RL157 = m1480RL(m1480RL152 + m1483f3(m1480RL155, m1480RL153, m1480RL156) + this.f2180X[5] + 1859775393, 7) + m1480RL154;
        int m1480RL158 = m1480RL(m1480RL153, 10);
        int m1480RL159 = m1480RL(m1480RL154 + m1483f3(m1480RL157, m1480RL155, m1480RL158) + this.f2180X[12] + 1859775393, 5) + m1480RL156;
        int m1480RL160 = m1480RL(m1480RL155, 10);
        int m1480RL161 = m1480RL(m1480RL124 + m1483f3(m1480RL127, m1480RL125, m1480RL128) + this.f2180X[15] + 1836072691, 9) + m1480RL126;
        int m1480RL162 = m1480RL(m1480RL125, 10);
        int m1480RL163 = m1480RL(m1480RL126 + m1483f3(m1480RL161, m1480RL127, m1480RL162) + this.f2180X[5] + 1836072691, 7) + m1480RL128;
        int m1480RL164 = m1480RL(m1480RL127, 10);
        int m1480RL165 = m1480RL(m1480RL128 + m1483f3(m1480RL163, m1480RL161, m1480RL164) + this.f2180X[1] + 1836072691, 15) + m1480RL162;
        int m1480RL166 = m1480RL(m1480RL161, 10);
        int m1480RL167 = m1480RL(m1480RL162 + m1483f3(m1480RL165, m1480RL163, m1480RL166) + this.f2180X[3] + 1836072691, 11) + m1480RL164;
        int m1480RL168 = m1480RL(m1480RL163, 10);
        int m1480RL169 = m1480RL(m1480RL164 + m1483f3(m1480RL167, m1480RL165, m1480RL168) + this.f2180X[7] + 1836072691, 8) + m1480RL166;
        int m1480RL170 = m1480RL(m1480RL165, 10);
        int m1480RL171 = m1480RL(m1480RL166 + m1483f3(m1480RL169, m1480RL167, m1480RL170) + this.f2180X[14] + 1836072691, 6) + m1480RL168;
        int m1480RL172 = m1480RL(m1480RL167, 10);
        int m1480RL173 = m1480RL(m1480RL168 + m1483f3(m1480RL171, m1480RL169, m1480RL172) + this.f2180X[6] + 1836072691, 6) + m1480RL170;
        int m1480RL174 = m1480RL(m1480RL169, 10);
        int m1480RL175 = m1480RL(m1480RL170 + m1483f3(m1480RL173, m1480RL171, m1480RL174) + this.f2180X[9] + 1836072691, 14) + m1480RL172;
        int m1480RL176 = m1480RL(m1480RL171, 10);
        int m1480RL177 = m1480RL(m1480RL172 + m1483f3(m1480RL175, m1480RL173, m1480RL176) + this.f2180X[11] + 1836072691, 12) + m1480RL174;
        int m1480RL178 = m1480RL(m1480RL173, 10);
        int m1480RL179 = m1480RL(m1480RL174 + m1483f3(m1480RL177, m1480RL175, m1480RL178) + this.f2180X[8] + 1836072691, 13) + m1480RL176;
        int m1480RL180 = m1480RL(m1480RL175, 10);
        int m1480RL181 = m1480RL(m1480RL176 + m1483f3(m1480RL179, m1480RL177, m1480RL180) + this.f2180X[12] + 1836072691, 5) + m1480RL178;
        int m1480RL182 = m1480RL(m1480RL177, 10);
        int m1480RL183 = m1480RL(m1480RL178 + m1483f3(m1480RL181, m1480RL179, m1480RL182) + this.f2180X[2] + 1836072691, 14) + m1480RL180;
        int m1480RL184 = m1480RL(m1480RL179, 10);
        int m1480RL185 = m1480RL(m1480RL180 + m1483f3(m1480RL183, m1480RL181, m1480RL184) + this.f2180X[10] + 1836072691, 13) + m1480RL182;
        int m1480RL186 = m1480RL(m1480RL181, 10);
        int m1480RL187 = m1480RL(m1480RL182 + m1483f3(m1480RL185, m1480RL183, m1480RL186) + this.f2180X[0] + 1836072691, 13) + m1480RL184;
        int m1480RL188 = m1480RL(m1480RL183, 10);
        int m1480RL189 = m1480RL(m1480RL184 + m1483f3(m1480RL187, m1480RL185, m1480RL188) + this.f2180X[4] + 1836072691, 7) + m1480RL186;
        int m1480RL190 = m1480RL(m1480RL185, 10);
        int m1480RL191 = m1480RL(m1480RL186 + m1483f3(m1480RL189, m1480RL187, m1480RL190) + this.f2180X[13] + 1836072691, 5) + m1480RL188;
        int m1480RL192 = m1480RL(m1480RL187, 10);
        int m1480RL193 = m1480RL(((m1480RL156 + m1484f4(m1480RL159, m1480RL157, m1480RL160)) + this.f2180X[1]) - 1894007588, 11) + m1480RL158;
        int m1480RL194 = m1480RL(m1480RL157, 10);
        int m1480RL195 = m1480RL(((m1480RL158 + m1484f4(m1480RL193, m1480RL159, m1480RL194)) + this.f2180X[9]) - 1894007588, 12) + m1480RL160;
        int m1480RL196 = m1480RL(m1480RL159, 10);
        int m1480RL197 = m1480RL(((m1480RL160 + m1484f4(m1480RL195, m1480RL193, m1480RL196)) + this.f2180X[11]) - 1894007588, 14) + m1480RL194;
        int m1480RL198 = m1480RL(m1480RL193, 10);
        int m1480RL199 = m1480RL(((m1480RL194 + m1484f4(m1480RL197, m1480RL195, m1480RL198)) + this.f2180X[10]) - 1894007588, 15) + m1480RL196;
        int m1480RL200 = m1480RL(m1480RL195, 10);
        int m1480RL201 = m1480RL(((m1480RL196 + m1484f4(m1480RL199, m1480RL197, m1480RL200)) + this.f2180X[0]) - 1894007588, 14) + m1480RL198;
        int m1480RL202 = m1480RL(m1480RL197, 10);
        int m1480RL203 = m1480RL(((m1480RL198 + m1484f4(m1480RL201, m1480RL199, m1480RL202)) + this.f2180X[8]) - 1894007588, 15) + m1480RL200;
        int m1480RL204 = m1480RL(m1480RL199, 10);
        int m1480RL205 = m1480RL(((m1480RL200 + m1484f4(m1480RL203, m1480RL201, m1480RL204)) + this.f2180X[12]) - 1894007588, 9) + m1480RL202;
        int m1480RL206 = m1480RL(m1480RL201, 10);
        int m1480RL207 = m1480RL(((m1480RL202 + m1484f4(m1480RL205, m1480RL203, m1480RL206)) + this.f2180X[4]) - 1894007588, 8) + m1480RL204;
        int m1480RL208 = m1480RL(m1480RL203, 10);
        int m1480RL209 = m1480RL(((m1480RL204 + m1484f4(m1480RL207, m1480RL205, m1480RL208)) + this.f2180X[13]) - 1894007588, 9) + m1480RL206;
        int m1480RL210 = m1480RL(m1480RL205, 10);
        int m1480RL211 = m1480RL(((m1480RL206 + m1484f4(m1480RL209, m1480RL207, m1480RL210)) + this.f2180X[3]) - 1894007588, 14) + m1480RL208;
        int m1480RL212 = m1480RL(m1480RL207, 10);
        int m1480RL213 = m1480RL(((m1480RL208 + m1484f4(m1480RL211, m1480RL209, m1480RL212)) + this.f2180X[7]) - 1894007588, 5) + m1480RL210;
        int m1480RL214 = m1480RL(m1480RL209, 10);
        int m1480RL215 = m1480RL(((m1480RL210 + m1484f4(m1480RL213, m1480RL211, m1480RL214)) + this.f2180X[15]) - 1894007588, 6) + m1480RL212;
        int m1480RL216 = m1480RL(m1480RL211, 10);
        int m1480RL217 = m1480RL(((m1480RL212 + m1484f4(m1480RL215, m1480RL213, m1480RL216)) + this.f2180X[14]) - 1894007588, 8) + m1480RL214;
        int m1480RL218 = m1480RL(m1480RL213, 10);
        int m1480RL219 = m1480RL(((m1480RL214 + m1484f4(m1480RL217, m1480RL215, m1480RL218)) + this.f2180X[5]) - 1894007588, 6) + m1480RL216;
        int m1480RL220 = m1480RL(m1480RL215, 10);
        int m1480RL221 = m1480RL(((m1480RL216 + m1484f4(m1480RL219, m1480RL217, m1480RL220)) + this.f2180X[6]) - 1894007588, 5) + m1480RL218;
        int m1480RL222 = m1480RL(m1480RL217, 10);
        int m1480RL223 = m1480RL(((m1480RL218 + m1484f4(m1480RL221, m1480RL219, m1480RL222)) + this.f2180X[2]) - 1894007588, 12) + m1480RL220;
        int m1480RL224 = m1480RL(m1480RL219, 10);
        int m1480RL225 = m1480RL(m1480RL188 + m1482f2(m1480RL191, m1480RL189, m1480RL192) + this.f2180X[8] + 2053994217, 15) + m1480RL190;
        int m1480RL226 = m1480RL(m1480RL189, 10);
        int m1480RL227 = m1480RL(m1480RL190 + m1482f2(m1480RL225, m1480RL191, m1480RL226) + this.f2180X[6] + 2053994217, 5) + m1480RL192;
        int m1480RL228 = m1480RL(m1480RL191, 10);
        int m1480RL229 = m1480RL(m1480RL192 + m1482f2(m1480RL227, m1480RL225, m1480RL228) + this.f2180X[4] + 2053994217, 8) + m1480RL226;
        int m1480RL230 = m1480RL(m1480RL225, 10);
        int m1480RL231 = m1480RL(m1480RL226 + m1482f2(m1480RL229, m1480RL227, m1480RL230) + this.f2180X[1] + 2053994217, 11) + m1480RL228;
        int m1480RL232 = m1480RL(m1480RL227, 10);
        int m1480RL233 = m1480RL(m1480RL228 + m1482f2(m1480RL231, m1480RL229, m1480RL232) + this.f2180X[3] + 2053994217, 14) + m1480RL230;
        int m1480RL234 = m1480RL(m1480RL229, 10);
        int m1480RL235 = m1480RL(m1480RL230 + m1482f2(m1480RL233, m1480RL231, m1480RL234) + this.f2180X[11] + 2053994217, 14) + m1480RL232;
        int m1480RL236 = m1480RL(m1480RL231, 10);
        int m1480RL237 = m1480RL(m1480RL232 + m1482f2(m1480RL235, m1480RL233, m1480RL236) + this.f2180X[15] + 2053994217, 6) + m1480RL234;
        int m1480RL238 = m1480RL(m1480RL233, 10);
        int m1480RL239 = m1480RL(m1480RL234 + m1482f2(m1480RL237, m1480RL235, m1480RL238) + this.f2180X[0] + 2053994217, 14) + m1480RL236;
        int m1480RL240 = m1480RL(m1480RL235, 10);
        int m1480RL241 = m1480RL(m1480RL236 + m1482f2(m1480RL239, m1480RL237, m1480RL240) + this.f2180X[5] + 2053994217, 6) + m1480RL238;
        int m1480RL242 = m1480RL(m1480RL237, 10);
        int m1480RL243 = m1480RL(m1480RL238 + m1482f2(m1480RL241, m1480RL239, m1480RL242) + this.f2180X[12] + 2053994217, 9) + m1480RL240;
        int m1480RL244 = m1480RL(m1480RL239, 10);
        int m1480RL245 = m1480RL(m1480RL240 + m1482f2(m1480RL243, m1480RL241, m1480RL244) + this.f2180X[2] + 2053994217, 12) + m1480RL242;
        int m1480RL246 = m1480RL(m1480RL241, 10);
        int m1480RL247 = m1480RL(m1480RL242 + m1482f2(m1480RL245, m1480RL243, m1480RL246) + this.f2180X[13] + 2053994217, 9) + m1480RL244;
        int m1480RL248 = m1480RL(m1480RL243, 10);
        int m1480RL249 = m1480RL(m1480RL244 + m1482f2(m1480RL247, m1480RL245, m1480RL248) + this.f2180X[9] + 2053994217, 12) + m1480RL246;
        int m1480RL250 = m1480RL(m1480RL245, 10);
        int m1480RL251 = m1480RL(m1480RL246 + m1482f2(m1480RL249, m1480RL247, m1480RL250) + this.f2180X[7] + 2053994217, 5) + m1480RL248;
        int m1480RL252 = m1480RL(m1480RL247, 10);
        int m1480RL253 = m1480RL(m1480RL248 + m1482f2(m1480RL251, m1480RL249, m1480RL252) + this.f2180X[10] + 2053994217, 15) + m1480RL250;
        int m1480RL254 = m1480RL(m1480RL249, 10);
        int m1480RL255 = m1480RL(m1480RL250 + m1482f2(m1480RL253, m1480RL251, m1480RL254) + this.f2180X[14] + 2053994217, 8) + m1480RL252;
        int m1480RL256 = m1480RL(m1480RL251, 10);
        int m1480RL257 = m1480RL(((m1480RL220 + m1485f5(m1480RL223, m1480RL221, m1480RL224)) + this.f2180X[4]) - 1454113458, 9) + m1480RL222;
        int m1480RL258 = m1480RL(m1480RL221, 10);
        int m1480RL259 = m1480RL(((m1480RL222 + m1485f5(m1480RL257, m1480RL223, m1480RL258)) + this.f2180X[0]) - 1454113458, 15) + m1480RL224;
        int m1480RL260 = m1480RL(m1480RL223, 10);
        int m1480RL261 = m1480RL(((m1480RL224 + m1485f5(m1480RL259, m1480RL257, m1480RL260)) + this.f2180X[5]) - 1454113458, 5) + m1480RL258;
        int m1480RL262 = m1480RL(m1480RL257, 10);
        int m1480RL263 = m1480RL(((m1480RL258 + m1485f5(m1480RL261, m1480RL259, m1480RL262)) + this.f2180X[9]) - 1454113458, 11) + m1480RL260;
        int m1480RL264 = m1480RL(m1480RL259, 10);
        int m1480RL265 = m1480RL(((m1480RL260 + m1485f5(m1480RL263, m1480RL261, m1480RL264)) + this.f2180X[7]) - 1454113458, 6) + m1480RL262;
        int m1480RL266 = m1480RL(m1480RL261, 10);
        int m1480RL267 = m1480RL(((m1480RL262 + m1485f5(m1480RL265, m1480RL263, m1480RL266)) + this.f2180X[12]) - 1454113458, 8) + m1480RL264;
        int m1480RL268 = m1480RL(m1480RL263, 10);
        int m1480RL269 = m1480RL(((m1480RL264 + m1485f5(m1480RL267, m1480RL265, m1480RL268)) + this.f2180X[2]) - 1454113458, 13) + m1480RL266;
        int m1480RL270 = m1480RL(m1480RL265, 10);
        int m1480RL271 = m1480RL(((m1480RL266 + m1485f5(m1480RL269, m1480RL267, m1480RL270)) + this.f2180X[10]) - 1454113458, 12) + m1480RL268;
        int m1480RL272 = m1480RL(m1480RL267, 10);
        int m1480RL273 = m1480RL(((m1480RL268 + m1485f5(m1480RL271, m1480RL269, m1480RL272)) + this.f2180X[14]) - 1454113458, 5) + m1480RL270;
        int m1480RL274 = m1480RL(m1480RL269, 10);
        int m1480RL275 = m1480RL(((m1480RL270 + m1485f5(m1480RL273, m1480RL271, m1480RL274)) + this.f2180X[1]) - 1454113458, 12) + m1480RL272;
        int m1480RL276 = m1480RL(m1480RL271, 10);
        int m1480RL277 = m1480RL(((m1480RL272 + m1485f5(m1480RL275, m1480RL273, m1480RL276)) + this.f2180X[3]) - 1454113458, 13) + m1480RL274;
        int m1480RL278 = m1480RL(m1480RL273, 10);
        int m1480RL279 = m1480RL(((m1480RL274 + m1485f5(m1480RL277, m1480RL275, m1480RL278)) + this.f2180X[8]) - 1454113458, 14) + m1480RL276;
        int m1480RL280 = m1480RL(m1480RL275, 10);
        int m1480RL281 = m1480RL(((m1480RL276 + m1485f5(m1480RL279, m1480RL277, m1480RL280)) + this.f2180X[11]) - 1454113458, 11) + m1480RL278;
        int m1480RL282 = m1480RL(m1480RL277, 10);
        int m1480RL283 = m1480RL(((m1480RL278 + m1485f5(m1480RL281, m1480RL279, m1480RL282)) + this.f2180X[6]) - 1454113458, 8) + m1480RL280;
        int m1480RL284 = m1480RL(m1480RL279, 10);
        int m1480RL285 = m1480RL(((m1480RL280 + m1485f5(m1480RL283, m1480RL281, m1480RL284)) + this.f2180X[15]) - 1454113458, 5) + m1480RL282;
        int m1480RL286 = m1480RL(m1480RL281, 10);
        int m1480RL287 = m1480RL(((m1480RL282 + m1485f5(m1480RL285, m1480RL283, m1480RL286)) + this.f2180X[13]) - 1454113458, 6) + m1480RL284;
        int m1480RL288 = m1480RL(m1480RL283, 10);
        int m1480RL289 = m1480RL(m1480RL252 + m1481f1(m1480RL255, m1480RL253, m1480RL256) + this.f2180X[12], 8) + m1480RL254;
        int m1480RL290 = m1480RL(m1480RL253, 10);
        int m1480RL291 = m1480RL(m1480RL254 + m1481f1(m1480RL289, m1480RL255, m1480RL290) + this.f2180X[15], 5) + m1480RL256;
        int m1480RL292 = m1480RL(m1480RL255, 10);
        int m1480RL293 = m1480RL(m1480RL256 + m1481f1(m1480RL291, m1480RL289, m1480RL292) + this.f2180X[10], 12) + m1480RL290;
        int m1480RL294 = m1480RL(m1480RL289, 10);
        int m1480RL295 = m1480RL(m1480RL290 + m1481f1(m1480RL293, m1480RL291, m1480RL294) + this.f2180X[4], 9) + m1480RL292;
        int m1480RL296 = m1480RL(m1480RL291, 10);
        int m1480RL297 = m1480RL(m1480RL292 + m1481f1(m1480RL295, m1480RL293, m1480RL296) + this.f2180X[1], 12) + m1480RL294;
        int m1480RL298 = m1480RL(m1480RL293, 10);
        int m1480RL299 = m1480RL(m1480RL294 + m1481f1(m1480RL297, m1480RL295, m1480RL298) + this.f2180X[5], 5) + m1480RL296;
        int m1480RL300 = m1480RL(m1480RL295, 10);
        int m1480RL301 = m1480RL(m1480RL296 + m1481f1(m1480RL299, m1480RL297, m1480RL300) + this.f2180X[8], 14) + m1480RL298;
        int m1480RL302 = m1480RL(m1480RL297, 10);
        int m1480RL303 = m1480RL(m1480RL298 + m1481f1(m1480RL301, m1480RL299, m1480RL302) + this.f2180X[7], 6) + m1480RL300;
        int m1480RL304 = m1480RL(m1480RL299, 10);
        int m1480RL305 = m1480RL(m1480RL300 + m1481f1(m1480RL303, m1480RL301, m1480RL304) + this.f2180X[6], 8) + m1480RL302;
        int m1480RL306 = m1480RL(m1480RL301, 10);
        int m1480RL307 = m1480RL(m1480RL302 + m1481f1(m1480RL305, m1480RL303, m1480RL306) + this.f2180X[2], 13) + m1480RL304;
        int m1480RL308 = m1480RL(m1480RL303, 10);
        int m1480RL309 = m1480RL(m1480RL304 + m1481f1(m1480RL307, m1480RL305, m1480RL308) + this.f2180X[13], 6) + m1480RL306;
        int m1480RL310 = m1480RL(m1480RL305, 10);
        int m1480RL311 = m1480RL(m1480RL306 + m1481f1(m1480RL309, m1480RL307, m1480RL310) + this.f2180X[14], 5) + m1480RL308;
        int m1480RL312 = m1480RL(m1480RL307, 10);
        int m1480RL313 = m1480RL(m1480RL308 + m1481f1(m1480RL311, m1480RL309, m1480RL312) + this.f2180X[0], 15) + m1480RL310;
        int m1480RL314 = m1480RL(m1480RL309, 10);
        int m1480RL315 = m1480RL(m1480RL310 + m1481f1(m1480RL313, m1480RL311, m1480RL314) + this.f2180X[3], 13) + m1480RL312;
        int m1480RL316 = m1480RL(m1480RL311, 10);
        int m1480RL317 = m1480RL(m1480RL312 + m1481f1(m1480RL315, m1480RL313, m1480RL316) + this.f2180X[9], 11) + m1480RL314;
        int m1480RL318 = m1480RL(m1480RL313, 10);
        int m1480RL319 = m1480RL(m1480RL314 + m1481f1(m1480RL317, m1480RL315, m1480RL318) + this.f2180X[11], 11) + m1480RL316;
        int m1480RL320 = m1480RL(m1480RL315, 10) + m1480RL285 + this.f2176H1;
        this.f2176H1 = this.f2177H2 + m1480RL288 + m1480RL318;
        this.f2177H2 = this.f2178H3 + m1480RL286 + m1480RL316;
        this.f2178H3 = this.f2179H4 + m1480RL284 + m1480RL319;
        this.f2179H4 = this.f2175H0 + m1480RL287 + m1480RL317;
        this.f2175H0 = m1480RL320;
        this.xOff = 0;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f2180X;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD160Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD160Digest) memoable);
    }
}
