package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c */
    private BigInteger f2466c;

    /* renamed from: d */
    private BigInteger f2467d;

    /* renamed from: h */
    private BigInteger f2468h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f2466c = bigInteger;
        this.f2467d = bigInteger2;
        this.f2468h = bigInteger3;
    }

    public BigInteger getC() {
        return this.f2466c;
    }

    public BigInteger getD() {
        return this.f2467d;
    }

    public BigInteger getH() {
        return this.f2468h;
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f2466c.hashCode() ^ this.f2467d.hashCode()) ^ this.f2468h.hashCode()) ^ super.hashCode();
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPublicKeyParameters)) {
            return false;
        }
        CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
        return cramerShoupPublicKeyParameters.getC().equals(this.f2466c) && cramerShoupPublicKeyParameters.getD().equals(this.f2467d) && cramerShoupPublicKeyParameters.getH().equals(this.f2468h) && super.equals(obj);
    }
}
