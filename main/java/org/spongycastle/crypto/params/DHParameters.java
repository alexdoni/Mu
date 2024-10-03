package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class DHParameters implements CipherParameters {
    private static final int DEFAULT_MINIMUM_LENGTH = 160;

    /* renamed from: g */
    private BigInteger f2469g;

    /* renamed from: j */
    private BigInteger f2470j;

    /* renamed from: l */
    private int f2471l;

    /* renamed from: m */
    private int f2472m;

    /* renamed from: p */
    private BigInteger f2473p;

    /* renamed from: q */
    private BigInteger f2474q;
    private DHValidationParameters validation;

    private static int getDefaultMParam(int i) {
        if (i != 0 && i < 160) {
            return i;
        }
        return 160;
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, null, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, 0);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i) {
        this(bigInteger, bigInteger2, bigInteger3, getDefaultMParam(i), i, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, i, i2, null, null);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        this(bigInteger, bigInteger2, bigInteger3, 160, 0, bigInteger4, dHValidationParameters);
    }

    public DHParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, int i2, BigInteger bigInteger4, DHValidationParameters dHValidationParameters) {
        if (i2 != 0) {
            if (i2 > bigInteger.bitLength()) {
                throw new IllegalArgumentException("when l value specified, it must satisfy 2^(l-1) <= p");
            }
            if (i2 < i) {
                throw new IllegalArgumentException("when l value specified, it may not be less than m value");
            }
        }
        this.f2469g = bigInteger2;
        this.f2473p = bigInteger;
        this.f2474q = bigInteger3;
        this.f2472m = i;
        this.f2471l = i2;
        this.f2470j = bigInteger4;
        this.validation = dHValidationParameters;
    }

    public BigInteger getP() {
        return this.f2473p;
    }

    public BigInteger getG() {
        return this.f2469g;
    }

    public BigInteger getQ() {
        return this.f2474q;
    }

    public BigInteger getJ() {
        return this.f2470j;
    }

    public int getM() {
        return this.f2472m;
    }

    public int getL() {
        return this.f2471l;
    }

    public DHValidationParameters getValidationParameters() {
        return this.validation;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHParameters)) {
            return false;
        }
        DHParameters dHParameters = (DHParameters) obj;
        if (getQ() != null) {
            if (!getQ().equals(dHParameters.getQ())) {
                return false;
            }
        } else if (dHParameters.getQ() != null) {
            return false;
        }
        return dHParameters.getP().equals(this.f2473p) && dHParameters.getG().equals(this.f2469g);
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) ^ (getQ() != null ? getQ().hashCode() : 0);
    }
}
