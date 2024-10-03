package org.spongycastle.crypto.params;

import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECPublicKeyParameters extends ECKeyParameters {

    /* renamed from: Q */
    private final ECPoint f2488Q;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.f2488Q = validate(eCPoint);
    }

    private ECPoint validate(ECPoint eCPoint) {
        if (eCPoint == null) {
            throw new IllegalArgumentException("point has null value");
        }
        if (eCPoint.isInfinity()) {
            throw new IllegalArgumentException("point at infinity");
        }
        ECPoint normalize = eCPoint.normalize();
        if (normalize.isValid()) {
            return normalize;
        }
        throw new IllegalArgumentException("point not on curve");
    }

    public ECPoint getQ() {
        return this.f2488Q;
    }
}
