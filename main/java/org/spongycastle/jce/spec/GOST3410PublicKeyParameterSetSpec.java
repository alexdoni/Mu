package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a */
    private BigInteger f2605a;

    /* renamed from: p */
    private BigInteger f2606p;

    /* renamed from: q */
    private BigInteger f2607q;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f2606p = bigInteger;
        this.f2607q = bigInteger2;
        this.f2605a = bigInteger3;
    }

    public BigInteger getP() {
        return this.f2606p;
    }

    public BigInteger getQ() {
        return this.f2607q;
    }

    public BigInteger getA() {
        return this.f2605a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PublicKeyParameterSetSpec)) {
            return false;
        }
        GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
        return this.f2605a.equals(gOST3410PublicKeyParameterSetSpec.f2605a) && this.f2606p.equals(gOST3410PublicKeyParameterSetSpec.f2606p) && this.f2607q.equals(gOST3410PublicKeyParameterSetSpec.f2607q);
    }

    public int hashCode() {
        return (this.f2605a.hashCode() ^ this.f2606p.hashCode()) ^ this.f2607q.hashCode();
    }
}
