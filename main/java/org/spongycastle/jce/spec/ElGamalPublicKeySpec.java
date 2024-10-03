package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ElGamalPublicKeySpec extends ElGamalKeySpec {

    /* renamed from: y */
    private BigInteger f2600y;

    public ElGamalPublicKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.f2600y = bigInteger;
    }

    public BigInteger getY() {
        return this.f2600y;
    }
}
