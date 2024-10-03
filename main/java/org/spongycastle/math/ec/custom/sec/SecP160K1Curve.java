package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class SecP160K1Curve extends ECCurve.AbstractFp {
    private static final int SECP160K1_DEFAULT_COORDS = 2;

    /* renamed from: q */
    public static final BigInteger f2648q = SecP160R2Curve.f2655q;
    protected SecP160K1Point infinity;

    @Override // org.spongycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }

    public SecP160K1Curve() {
        super(f2648q);
        this.infinity = new SecP160K1Point(this, null, null);
        this.f2612a = fromBigInteger(ECConstants.ZERO);
        this.f2613b = fromBigInteger(BigInteger.valueOf(7L));
        this.order = new BigInteger(1, Hex.decode("0100000000000000000001B8FA16DFAB9ACA16B6B3"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.spongycastle.math.ec.ECCurve
    protected ECCurve cloneCurve() {
        return new SecP160K1Curve();
    }

    public BigInteger getQ() {
        return f2648q;
    }

    @Override // org.spongycastle.math.ec.ECCurve
    public int getFieldSize() {
        return f2648q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP160R2FieldElement(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP160K1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        return new SecP160K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
    }

    @Override // org.spongycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }
}
