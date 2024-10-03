package org.spongycastle.crypto.digests;

import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

/* loaded from: classes3.dex */
public class SHA384Digest extends LongDigest {
    private static final int DIGEST_LENGTH = 48;

    @Override // org.spongycastle.crypto.Digest
    public String getAlgorithmName() {
        return McElieceCCA2KeyGenParameterSpec.SHA384;
    }

    @Override // org.spongycastle.crypto.Digest
    public int getDigestSize() {
        return 48;
    }

    public SHA384Digest() {
    }

    public SHA384Digest(SHA384Digest sHA384Digest) {
        super(sHA384Digest);
    }

    public SHA384Digest(byte[] bArr) {
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
        reset();
        return 48;
    }

    @Override // org.spongycastle.crypto.digests.LongDigest, org.spongycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f2147H1 = -3766243637369397544L;
        this.f2148H2 = 7105036623409894663L;
        this.f2149H3 = -7973340178411365097L;
        this.f2150H4 = 1526699215303891257L;
        this.f2151H5 = 7436329637833083697L;
        this.f2152H6 = -8163818279084223215L;
        this.f2153H7 = -2662702644619276377L;
        this.f2154H8 = 5167115440072839076L;
    }

    @Override // org.spongycastle.util.Memoable
    public Memoable copy() {
        return new SHA384Digest(this);
    }

    @Override // org.spongycastle.util.Memoable
    public void reset(Memoable memoable) {
        super.copyIn((SHA384Digest) memoable);
    }

    @Override // org.spongycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[getEncodedStateSize()];
        super.populateState(bArr);
        return bArr;
    }
}
