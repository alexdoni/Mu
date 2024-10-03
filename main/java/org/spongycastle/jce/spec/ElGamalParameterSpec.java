package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes3.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: g */
    private BigInteger f2597g;

    /* renamed from: p */
    private BigInteger f2598p;

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.f2598p = bigInteger;
        this.f2597g = bigInteger2;
    }

    public BigInteger getP() {
        return this.f2598p;
    }

    public BigInteger getG() {
        return this.f2597g;
    }
}
