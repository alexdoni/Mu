package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class ECDomainParameters implements ECConstants {

    /* renamed from: G */
    private ECPoint f2484G;
    private ECCurve curve;

    /* renamed from: h */
    private BigInteger f2485h;

    /* renamed from: n */
    private BigInteger f2486n;
    private byte[] seed;

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f2484G = eCPoint.normalize();
        this.f2486n = bigInteger;
        this.f2485h = bigInteger2;
        this.seed = bArr;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f2484G;
    }

    public BigInteger getN() {
        return this.f2486n;
    }

    public BigInteger getH() {
        return this.f2485h;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ECDomainParameters)) {
            return false;
        }
        ECDomainParameters eCDomainParameters = (ECDomainParameters) obj;
        return this.curve.equals(eCDomainParameters.curve) && this.f2484G.equals(eCDomainParameters.f2484G) && this.f2486n.equals(eCDomainParameters.f2486n) && this.f2485h.equals(eCDomainParameters.f2485h);
    }

    public int hashCode() {
        return (((((this.curve.hashCode() * 37) ^ this.f2484G.hashCode()) * 37) ^ this.f2486n.hashCode()) * 37) ^ this.f2485h.hashCode();
    }
}
