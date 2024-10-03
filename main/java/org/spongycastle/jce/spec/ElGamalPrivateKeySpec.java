package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ElGamalPrivateKeySpec extends ElGamalKeySpec {

    /* renamed from: x */
    private BigInteger f2599x;

    public ElGamalPrivateKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.f2599x = bigInteger;
    }

    public BigInteger getX() {
        return this.f2599x;
    }
}
