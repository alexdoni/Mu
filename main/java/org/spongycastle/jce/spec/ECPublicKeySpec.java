package org.spongycastle.jce.spec;

import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECPublicKeySpec extends ECKeySpec {

    /* renamed from: q */
    private ECPoint f2596q;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        if (eCPoint.getCurve() != null) {
            this.f2596q = eCPoint.normalize();
        } else {
            this.f2596q = eCPoint;
        }
    }

    public ECPoint getQ() {
        return this.f2596q;
    }
}
