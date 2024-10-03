package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class ElGamalParameters implements CipherParameters {

    /* renamed from: g */
    private BigInteger f2489g;

    /* renamed from: l */
    private int f2490l;

    /* renamed from: p */
    private BigInteger f2491p;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f2489g = bigInteger2;
        this.f2491p = bigInteger;
        this.f2490l = i;
    }

    public BigInteger getP() {
        return this.f2491p;
    }

    public BigInteger getG() {
        return this.f2489g;
    }

    public int getL() {
        return this.f2490l;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalParameters)) {
            return false;
        }
        ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
        return elGamalParameters.getP().equals(this.f2491p) && elGamalParameters.getG().equals(this.f2489g) && elGamalParameters.getL() == this.f2490l;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.f2490l;
    }
}
