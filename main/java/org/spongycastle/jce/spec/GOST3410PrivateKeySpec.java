package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/* loaded from: classes3.dex */
public class GOST3410PrivateKeySpec implements KeySpec {

    /* renamed from: a */
    private BigInteger f2601a;

    /* renamed from: p */
    private BigInteger f2602p;

    /* renamed from: q */
    private BigInteger f2603q;

    /* renamed from: x */
    private BigInteger f2604x;

    public GOST3410PrivateKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f2604x = bigInteger;
        this.f2602p = bigInteger2;
        this.f2603q = bigInteger3;
        this.f2601a = bigInteger4;
    }

    public BigInteger getX() {
        return this.f2604x;
    }

    public BigInteger getP() {
        return this.f2602p;
    }

    public BigInteger getQ() {
        return this.f2603q;
    }

    public BigInteger getA() {
        return this.f2601a;
    }
}
