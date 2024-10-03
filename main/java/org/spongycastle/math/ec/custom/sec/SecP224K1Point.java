package org.spongycastle.math.ec.custom.sec;

import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat224;

/* loaded from: classes3.dex */
public class SecP224K1Point extends ECPoint.AbstractFp {
    public SecP224K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SecP224K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP224K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    @Override // org.spongycastle.math.ec.ECPoint
    protected ECPoint detach() {
        return new SecP224K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP224K1FieldElement secP224K1FieldElement = (SecP224K1FieldElement) this.f2627x;
        SecP224K1FieldElement secP224K1FieldElement2 = (SecP224K1FieldElement) this.f2628y;
        SecP224K1FieldElement secP224K1FieldElement3 = (SecP224K1FieldElement) eCPoint.getXCoord();
        SecP224K1FieldElement secP224K1FieldElement4 = (SecP224K1FieldElement) eCPoint.getYCoord();
        SecP224K1FieldElement secP224K1FieldElement5 = (SecP224K1FieldElement) this.f2629zs[0];
        SecP224K1FieldElement secP224K1FieldElement6 = (SecP224K1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat224.createExt();
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        int[] create3 = Nat224.create();
        boolean isOne = secP224K1FieldElement5.isOne();
        if (isOne) {
            iArr = secP224K1FieldElement3.f2675x;
            iArr2 = secP224K1FieldElement4.f2675x;
        } else {
            SecP224K1Field.square(secP224K1FieldElement5.f2675x, create2);
            SecP224K1Field.multiply(create2, secP224K1FieldElement3.f2675x, create);
            SecP224K1Field.multiply(create2, secP224K1FieldElement5.f2675x, create2);
            SecP224K1Field.multiply(create2, secP224K1FieldElement4.f2675x, create2);
            iArr = create;
            iArr2 = create2;
        }
        boolean isOne2 = secP224K1FieldElement6.isOne();
        if (isOne2) {
            iArr3 = secP224K1FieldElement.f2675x;
            iArr4 = secP224K1FieldElement2.f2675x;
        } else {
            SecP224K1Field.square(secP224K1FieldElement6.f2675x, create3);
            SecP224K1Field.multiply(create3, secP224K1FieldElement.f2675x, createExt);
            SecP224K1Field.multiply(create3, secP224K1FieldElement6.f2675x, create3);
            SecP224K1Field.multiply(create3, secP224K1FieldElement2.f2675x, create3);
            iArr3 = createExt;
            iArr4 = create3;
        }
        int[] create4 = Nat224.create();
        SecP224K1Field.subtract(iArr3, iArr, create4);
        SecP224K1Field.subtract(iArr4, iArr2, create);
        if (Nat224.isZero(create4)) {
            if (Nat224.isZero(create)) {
                return twice();
            }
            return curve.getInfinity();
        }
        SecP224K1Field.square(create4, create2);
        int[] create5 = Nat224.create();
        SecP224K1Field.multiply(create2, create4, create5);
        SecP224K1Field.multiply(create2, iArr3, create2);
        SecP224K1Field.negate(create5, create5);
        Nat224.mul(iArr4, create5, createExt);
        SecP224K1Field.reduce32(Nat224.addBothTo(create2, create2, create5), create5);
        SecP224K1FieldElement secP224K1FieldElement7 = new SecP224K1FieldElement(create3);
        SecP224K1Field.square(create, secP224K1FieldElement7.f2675x);
        SecP224K1Field.subtract(secP224K1FieldElement7.f2675x, create5, secP224K1FieldElement7.f2675x);
        SecP224K1FieldElement secP224K1FieldElement8 = new SecP224K1FieldElement(create5);
        SecP224K1Field.subtract(create2, secP224K1FieldElement7.f2675x, secP224K1FieldElement8.f2675x);
        SecP224K1Field.multiplyAddToExt(secP224K1FieldElement8.f2675x, create, createExt);
        SecP224K1Field.reduce(createExt, secP224K1FieldElement8.f2675x);
        SecP224K1FieldElement secP224K1FieldElement9 = new SecP224K1FieldElement(create4);
        if (!isOne) {
            SecP224K1Field.multiply(secP224K1FieldElement9.f2675x, secP224K1FieldElement5.f2675x, secP224K1FieldElement9.f2675x);
        }
        if (!isOne2) {
            SecP224K1Field.multiply(secP224K1FieldElement9.f2675x, secP224K1FieldElement6.f2675x, secP224K1FieldElement9.f2675x);
        }
        return new SecP224K1Point(curve, secP224K1FieldElement7, secP224K1FieldElement8, new ECFieldElement[]{secP224K1FieldElement9}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP224K1FieldElement secP224K1FieldElement = (SecP224K1FieldElement) this.f2628y;
        if (secP224K1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP224K1FieldElement secP224K1FieldElement2 = (SecP224K1FieldElement) this.f2627x;
        SecP224K1FieldElement secP224K1FieldElement3 = (SecP224K1FieldElement) this.f2629zs[0];
        int[] create = Nat224.create();
        SecP224K1Field.square(secP224K1FieldElement.f2675x, create);
        int[] create2 = Nat224.create();
        SecP224K1Field.square(create, create2);
        int[] create3 = Nat224.create();
        SecP224K1Field.square(secP224K1FieldElement2.f2675x, create3);
        SecP224K1Field.reduce32(Nat224.addBothTo(create3, create3, create3), create3);
        SecP224K1Field.multiply(create, secP224K1FieldElement2.f2675x, create);
        SecP224K1Field.reduce32(Nat.shiftUpBits(7, create, 2, 0), create);
        int[] create4 = Nat224.create();
        SecP224K1Field.reduce32(Nat.shiftUpBits(7, create2, 3, 0, create4), create4);
        SecP224K1FieldElement secP224K1FieldElement4 = new SecP224K1FieldElement(create2);
        SecP224K1Field.square(create3, secP224K1FieldElement4.f2675x);
        SecP224K1Field.subtract(secP224K1FieldElement4.f2675x, create, secP224K1FieldElement4.f2675x);
        SecP224K1Field.subtract(secP224K1FieldElement4.f2675x, create, secP224K1FieldElement4.f2675x);
        SecP224K1FieldElement secP224K1FieldElement5 = new SecP224K1FieldElement(create);
        SecP224K1Field.subtract(create, secP224K1FieldElement4.f2675x, secP224K1FieldElement5.f2675x);
        SecP224K1Field.multiply(secP224K1FieldElement5.f2675x, create3, secP224K1FieldElement5.f2675x);
        SecP224K1Field.subtract(secP224K1FieldElement5.f2675x, create4, secP224K1FieldElement5.f2675x);
        SecP224K1FieldElement secP224K1FieldElement6 = new SecP224K1FieldElement(create3);
        SecP224K1Field.twice(secP224K1FieldElement.f2675x, secP224K1FieldElement6.f2675x);
        if (!secP224K1FieldElement3.isOne()) {
            SecP224K1Field.multiply(secP224K1FieldElement6.f2675x, secP224K1FieldElement3.f2675x, secP224K1FieldElement6.f2675x);
        }
        return new SecP224K1Point(curve, secP224K1FieldElement4, secP224K1FieldElement5, new ECFieldElement[]{secP224K1FieldElement6}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        if (this == eCPoint) {
            return threeTimes();
        }
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return twice();
        }
        return this.f2628y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f2628y.isZero()) ? this : twice().add(this);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP224K1Point(this.curve, this.f2627x, this.f2628y.negate(), this.f2629zs, this.withCompression);
    }
}
