package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class CramerShoupParameters implements CipherParameters {

    /* renamed from: H */
    private Digest f2456H;

    /* renamed from: g1 */
    private BigInteger f2457g1;

    /* renamed from: g2 */
    private BigInteger f2458g2;

    /* renamed from: p */
    private BigInteger f2459p;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.f2459p = bigInteger;
        this.f2457g1 = bigInteger2;
        this.f2458g2 = bigInteger3;
        this.f2456H = digest;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAParameters)) {
            return false;
        }
        CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
        return cramerShoupParameters.getP().equals(this.f2459p) && cramerShoupParameters.getG1().equals(this.f2457g1) && cramerShoupParameters.getG2().equals(this.f2458g2);
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }

    public BigInteger getG1() {
        return this.f2457g1;
    }

    public BigInteger getG2() {
        return this.f2458g2;
    }

    public BigInteger getP() {
        return this.f2459p;
    }

    public Digest getH() {
        this.f2456H.reset();
        return this.f2456H;
    }
}
