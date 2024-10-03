package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: pk */
    private CramerShoupPublicKeyParameters f2460pk;

    /* renamed from: x1 */
    private BigInteger f2461x1;

    /* renamed from: x2 */
    private BigInteger f2462x2;

    /* renamed from: y1 */
    private BigInteger f2463y1;

    /* renamed from: y2 */
    private BigInteger f2464y2;

    /* renamed from: z */
    private BigInteger f2465z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.f2461x1 = bigInteger;
        this.f2462x2 = bigInteger2;
        this.f2463y1 = bigInteger3;
        this.f2464y2 = bigInteger4;
        this.f2465z = bigInteger5;
    }

    public BigInteger getX1() {
        return this.f2461x1;
    }

    public BigInteger getX2() {
        return this.f2462x2;
    }

    public BigInteger getY1() {
        return this.f2463y1;
    }

    public BigInteger getY2() {
        return this.f2464y2;
    }

    public BigInteger getZ() {
        return this.f2465z;
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.f2460pk = cramerShoupPublicKeyParameters;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f2460pk;
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((((this.f2461x1.hashCode() ^ this.f2462x2.hashCode()) ^ this.f2463y1.hashCode()) ^ this.f2464y2.hashCode()) ^ this.f2465z.hashCode()) ^ super.hashCode();
    }

    @Override // org.spongycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPrivateKeyParameters)) {
            return false;
        }
        CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
        return cramerShoupPrivateKeyParameters.getX1().equals(this.f2461x1) && cramerShoupPrivateKeyParameters.getX2().equals(this.f2462x2) && cramerShoupPrivateKeyParameters.getY1().equals(this.f2463y1) && cramerShoupPrivateKeyParameters.getY2().equals(this.f2464y2) && cramerShoupPrivateKeyParameters.getZ().equals(this.f2465z) && super.equals(obj);
    }
}
