package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes3.dex */
public class ECParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: G */
    private ECPoint f2592G;
    private ECCurve curve;

    /* renamed from: h */
    private BigInteger f2593h;

    /* renamed from: n */
    private BigInteger f2594n;
    private byte[] seed;

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this.curve = eCCurve;
        this.f2592G = eCPoint.normalize();
        this.f2594n = bigInteger;
        this.f2593h = BigInteger.valueOf(1L);
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this.curve = eCCurve;
        this.f2592G = eCPoint.normalize();
        this.f2594n = bigInteger;
        this.f2593h = bigInteger2;
        this.seed = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.curve = eCCurve;
        this.f2592G = eCPoint.normalize();
        this.f2594n = bigInteger;
        this.f2593h = bigInteger2;
        this.seed = bArr;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f2592G;
    }

    public BigInteger getN() {
        return this.f2594n;
    }

    public BigInteger getH() {
        return this.f2593h;
    }

    public byte[] getSeed() {
        return this.seed;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ECParameterSpec)) {
            return false;
        }
        ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
        return getCurve().equals(eCParameterSpec.getCurve()) && getG().equals(eCParameterSpec.getG());
    }

    public int hashCode() {
        return getCurve().hashCode() ^ getG().hashCode();
    }
}
