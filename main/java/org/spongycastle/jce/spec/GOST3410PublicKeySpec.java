package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes3.dex */
public class GOST3410PublicKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f2608a;

    /* renamed from: p */
    private BigInteger f2609p;

    /* renamed from: q */
    private BigInteger f2610q;

    /* renamed from: y */
    private BigInteger f2611y;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f2611y = bigInteger;
        this.f2609p = bigInteger2;
        this.f2610q = bigInteger3;
        this.f2608a = bigInteger4;
    }

    public BigInteger getY() {
        return this.f2611y;
    }

    public BigInteger getP() {
        return this.f2609p;
    }

    public BigInteger getQ() {
        return this.f2610q;
    }

    public BigInteger getA() {
        return this.f2608a;
    }
}
