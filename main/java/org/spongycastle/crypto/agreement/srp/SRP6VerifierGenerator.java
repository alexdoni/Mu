package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes3.dex */
public class SRP6VerifierGenerator {

    /* renamed from: N */
    protected BigInteger f2120N;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f2121g;

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.f2120N = bigInteger;
        this.f2121g = bigInteger2;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.f2120N = sRP6GroupParameters.getN();
        this.f2121g = sRP6GroupParameters.getG();
        this.digest = digest;
    }

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f2121g.modPow(SRP6Util.calculateX(this.digest, this.f2120N, bArr, bArr2, bArr3), this.f2120N);
    }
}
