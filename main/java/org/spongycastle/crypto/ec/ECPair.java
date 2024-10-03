package org.spongycastle.crypto.ec;

import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECPair {

    /* renamed from: x */
    private final ECPoint f2254x;

    /* renamed from: y */
    private final ECPoint f2255y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f2254x = eCPoint;
        this.f2255y = eCPoint2;
    }

    public ECPoint getX() {
        return this.f2254x;
    }

    public ECPoint getY() {
        return this.f2255y;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public int hashCode() {
        return this.f2254x.hashCode() + (this.f2255y.hashCode() * 37);
    }
}
