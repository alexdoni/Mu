package org.spongycastle.crypto.prng.drbg;

import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class DualECPoints {
    private final int cofactor;

    /* renamed from: p */
    private final ECPoint f2529p;

    /* renamed from: q */
    private final ECPoint f2530q;
    private final int securityStrength;

    private static int log2(int i) {
        int i2 = 0;
        while (true) {
            i >>= 1;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public DualECPoints(int i, ECPoint eCPoint, ECPoint eCPoint2, int i2) {
        if (!eCPoint.getCurve().equals(eCPoint2.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.securityStrength = i;
        this.f2529p = eCPoint;
        this.f2530q = eCPoint2;
        this.cofactor = i2;
    }

    public int getSeedLen() {
        return this.f2529p.getCurve().getFieldSize();
    }

    public int getMaxOutlen() {
        return ((this.f2529p.getCurve().getFieldSize() - (log2(this.cofactor) + 13)) / 8) * 8;
    }

    public ECPoint getP() {
        return this.f2529p;
    }

    public ECPoint getQ() {
        return this.f2530q;
    }

    public int getSecurityStrength() {
        return this.securityStrength;
    }

    public int getCofactor() {
        return this.cofactor;
    }
}
