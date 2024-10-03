package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class SRP6GroupParameters {

    /* renamed from: N */
    private BigInteger f2519N;

    /* renamed from: g */
    private BigInteger f2520g;

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f2519N = bigInteger;
        this.f2520g = bigInteger2;
    }

    public BigInteger getG() {
        return this.f2520g;
    }

    public BigInteger getN() {
        return this.f2519N;
    }
}
