package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {

    /* renamed from: dP */
    private BigInteger f2514dP;

    /* renamed from: dQ */
    private BigInteger f2515dQ;

    /* renamed from: e */
    private BigInteger f2516e;

    /* renamed from: p */
    private BigInteger f2517p;

    /* renamed from: q */
    private BigInteger f2518q;
    private BigInteger qInv;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.f2516e = bigInteger2;
        this.f2517p = bigInteger4;
        this.f2518q = bigInteger5;
        this.f2514dP = bigInteger6;
        this.f2515dQ = bigInteger7;
        this.qInv = bigInteger8;
    }

    public BigInteger getPublicExponent() {
        return this.f2516e;
    }

    public BigInteger getP() {
        return this.f2517p;
    }

    public BigInteger getQ() {
        return this.f2518q;
    }

    public BigInteger getDP() {
        return this.f2514dP;
    }

    public BigInteger getDQ() {
        return this.f2515dQ;
    }

    public BigInteger getQInv() {
        return this.qInv;
    }
}
