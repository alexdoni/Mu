package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a */
    private BigInteger f2494a;

    /* renamed from: p */
    private BigInteger f2495p;

    /* renamed from: q */
    private BigInteger f2496q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2495p = bigInteger;
        this.f2496q = bigInteger2;
        this.f2494a = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.f2494a = bigInteger3;
        this.f2495p = bigInteger;
        this.f2496q = bigInteger2;
        this.validation = gOST3410ValidationParameters;
    }

    public BigInteger getP() {
        return this.f2495p;
    }

    public BigInteger getQ() {
        return this.f2496q;
    }

    public BigInteger getA() {
        return this.f2494a;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f2495p.hashCode() ^ this.f2496q.hashCode()) ^ this.f2494a.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
        return gOST3410Parameters.getP().equals(this.f2495p) && gOST3410Parameters.getQ().equals(this.f2496q) && gOST3410Parameters.getA().equals(this.f2494a);
    }
}
