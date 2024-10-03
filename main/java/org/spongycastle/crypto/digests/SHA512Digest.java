package org.spongycastle.crypto.digests;

import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class SHA512Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 64;

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA512;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 64;
    }

    public SHA512Digest() {
    }

    public SHA512Digest(SHA512Digest sHA512Digest) {
        super(sHA512Digest);
    }

    public SHA512Digest(byte[] bArr) {
        restoreState(bArr);
    }

    @Override // org.spongycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.longToBigEndian(this.f2147H1, bArr, i);
        Pack.longToBigEndian(this.f2148H2, bArr, i + 8);
        Pack.longToBigEndian(this.f2149H3, bArr, i + 16);
        Pack.longToBigEndian(this.f2150H4, bArr, i + 24);
        Pack.longToBigEndian(this.f2151H5, bArr, i + 32);
        Pack.longToBigEndian(this.f2152H6, bArr, i + 40);
        Pack.longToBigEndian(this.f2153H7, bArr, i + 48);
        Pack.longToBigEndian(this.f2154H8, bArr, i + 56);
        reset();
        return 64;
    }

    @Override // org.spongycastle.crypto.digests.LongDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2147H1 = 7640891576956012808L;
        this.f2148H2 = -4942790177534073029L;
        this.f2149H3 = 4354685564936845355L;
        this.f2150H4 = -6534734903238641935L;
        this.f2151H5 = 5840696475078001361L;
        this.f2152H6 = -7276294671716946913L;
        this.f2153H7 = 2270897969802886507L;
        this.f2154H8 = 6620516959819538809L;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SHA512Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((SHA512Digest) memoable);
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[getEncodedStateSize()];
        super.populateState(bArr);
        return bArr;
    }
}
