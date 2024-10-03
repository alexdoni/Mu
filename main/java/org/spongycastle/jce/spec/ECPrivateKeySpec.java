package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class ECPrivateKeySpec extends ECKeySpec {

    /* renamed from: d */
    private BigInteger f2595d;

    public ECPrivateKeySpec(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.f2595d = bigInteger;
    }

    public BigInteger getD() {
        return this.f2595d;
    }
}
