package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class DSAParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f2479g;

    /* renamed from: p */
    private BigInteger f2480p;

    /* renamed from: q */
    private BigInteger f2481q;
    private DSAValidationParameters validation;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2479g = bigInteger3;
        this.f2480p = bigInteger;
        this.f2481q = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.f2479g = bigInteger3;
        this.f2480p = bigInteger;
        this.f2481q = bigInteger2;
        this.validation = dSAValidationParameters;
    }

    public BigInteger getP() {
        return this.f2480p;
    }

    public BigInteger getQ() {
        return this.f2481q;
    }

    public BigInteger getG() {
        return this.f2479g;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.validation;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        DSAParameters dSAParameters = (DSAParameters) obj;
        return dSAParameters.getP().equals(this.f2480p) && dSAParameters.getQ().equals(this.f2481q) && dSAParameters.getG().equals(this.f2479g);
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
