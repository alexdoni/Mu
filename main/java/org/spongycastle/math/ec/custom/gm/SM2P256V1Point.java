package org.spongycastle.math.ec.custom.gm;

import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* loaded from: classes3.dex */
public class SM2P256V1Point extends ECPoint.AbstractFp {
    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    @Override // org.spongycastle.math.ec.ECPoint
    protected ECPoint detach() {
        return new SM2P256V1Point(null, getAffineXCoord(), getAffineYCoord());
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
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement) this.f2627x;
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement) this.f2628y;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement) eCPoint.getXCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement4 = (SM2P256V1FieldElement) eCPoint.getYCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement5 = (SM2P256V1FieldElement) this.f2629zs[0];
        SM2P256V1FieldElement sM2P256V1FieldElement6 = (SM2P256V1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat256.createExt();
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        boolean isOne = sM2P256V1FieldElement5.isOne();
        if (isOne) {
            iArr = sM2P256V1FieldElement3.f2642x;
            iArr2 = sM2P256V1FieldElement4.f2642x;
        } else {
            SM2P256V1Field.square(sM2P256V1FieldElement5.f2642x, create2);
            SM2P256V1Field.multiply(create2, sM2P256V1FieldElement3.f2642x, create);
            SM2P256V1Field.multiply(create2, sM2P256V1FieldElement5.f2642x, create2);
            SM2P256V1Field.multiply(create2, sM2P256V1FieldElement4.f2642x, create2);
            iArr = create;
            iArr2 = create2;
        }
        boolean isOne2 = sM2P256V1FieldElement6.isOne();
        if (isOne2) {
            iArr3 = sM2P256V1FieldElement.f2642x;
            iArr4 = sM2P256V1FieldElement2.f2642x;
        } else {
            SM2P256V1Field.square(sM2P256V1FieldElement6.f2642x, create3);
            SM2P256V1Field.multiply(create3, sM2P256V1FieldElement.f2642x, createExt);
            SM2P256V1Field.multiply(create3, sM2P256V1FieldElement6.f2642x, create3);
            SM2P256V1Field.multiply(create3, sM2P256V1FieldElement2.f2642x, create3);
            iArr3 = createExt;
            iArr4 = create3;
        }
        int[] create4 = Nat256.create();
        SM2P256V1Field.subtract(iArr3, iArr, create4);
        SM2P256V1Field.subtract(iArr4, iArr2, create);
        if (Nat256.isZero(create4)) {
            if (Nat256.isZero(create)) {
                return twice();
            }
            return curve.getInfinity();
        }
        SM2P256V1Field.square(create4, create2);
        int[] create5 = Nat256.create();
        SM2P256V1Field.multiply(create2, create4, create5);
        SM2P256V1Field.multiply(create2, iArr3, create2);
        SM2P256V1Field.negate(create5, create5);
        Nat256.mul(iArr4, create5, createExt);
        SM2P256V1Field.reduce32(Nat256.addBothTo(create2, create2, create5), create5);
        SM2P256V1FieldElement sM2P256V1FieldElement7 = new SM2P256V1FieldElement(create3);
        SM2P256V1Field.square(create, sM2P256V1FieldElement7.f2642x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement7.f2642x, create5, sM2P256V1FieldElement7.f2642x);
        SM2P256V1FieldElement sM2P256V1FieldElement8 = new SM2P256V1FieldElement(create5);
        SM2P256V1Field.subtract(create2, sM2P256V1FieldElement7.f2642x, sM2P256V1FieldElement8.f2642x);
        SM2P256V1Field.multiplyAddToExt(sM2P256V1FieldElement8.f2642x, create, createExt);
        SM2P256V1Field.reduce(createExt, sM2P256V1FieldElement8.f2642x);
        SM2P256V1FieldElement sM2P256V1FieldElement9 = new SM2P256V1FieldElement(create4);
        if (!isOne) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.f2642x, sM2P256V1FieldElement5.f2642x, sM2P256V1FieldElement9.f2642x);
        }
        if (!isOne2) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.f2642x, sM2P256V1FieldElement6.f2642x, sM2P256V1FieldElement9.f2642x);
        }
        return new SM2P256V1Point(curve, sM2P256V1FieldElement7, sM2P256V1FieldElement8, new ECFieldElement[]{sM2P256V1FieldElement9}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement) this.f2628y;
        if (sM2P256V1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement) this.f2627x;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement) this.f2629zs[0];
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        SM2P256V1Field.square(sM2P256V1FieldElement.f2642x, create3);
        int[] create4 = Nat256.create();
        SM2P256V1Field.square(create3, create4);
        boolean isOne = sM2P256V1FieldElement3.isOne();
        int[] iArr = sM2P256V1FieldElement3.f2642x;
        if (!isOne) {
            SM2P256V1Field.square(sM2P256V1FieldElement3.f2642x, create2);
            iArr = create2;
        }
        SM2P256V1Field.subtract(sM2P256V1FieldElement2.f2642x, iArr, create);
        SM2P256V1Field.add(sM2P256V1FieldElement2.f2642x, iArr, create2);
        SM2P256V1Field.multiply(create2, create, create2);
        SM2P256V1Field.reduce32(Nat256.addBothTo(create2, create2, create2), create2);
        SM2P256V1Field.multiply(create3, sM2P256V1FieldElement2.f2642x, create3);
        SM2P256V1Field.reduce32(Nat.shiftUpBits(8, create3, 2, 0), create3);
        SM2P256V1Field.reduce32(Nat.shiftUpBits(8, create4, 3, 0, create), create);
        SM2P256V1FieldElement sM2P256V1FieldElement4 = new SM2P256V1FieldElement(create4);
        SM2P256V1Field.square(create2, sM2P256V1FieldElement4.f2642x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.f2642x, create3, sM2P256V1FieldElement4.f2642x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.f2642x, create3, sM2P256V1FieldElement4.f2642x);
        SM2P256V1FieldElement sM2P256V1FieldElement5 = new SM2P256V1FieldElement(create3);
        SM2P256V1Field.subtract(create3, sM2P256V1FieldElement4.f2642x, sM2P256V1FieldElement5.f2642x);
        SM2P256V1Field.multiply(sM2P256V1FieldElement5.f2642x, create2, sM2P256V1FieldElement5.f2642x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement5.f2642x, create, sM2P256V1FieldElement5.f2642x);
        SM2P256V1FieldElement sM2P256V1FieldElement6 = new SM2P256V1FieldElement(create2);
        SM2P256V1Field.twice(sM2P256V1FieldElement.f2642x, sM2P256V1FieldElement6.f2642x);
        if (!isOne) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement6.f2642x, sM2P256V1FieldElement3.f2642x, sM2P256V1FieldElement6.f2642x);
        }
        return new SM2P256V1Point(curve, sM2P256V1FieldElement4, sM2P256V1FieldElement5, new ECFieldElement[]{sM2P256V1FieldElement6}, this.withCompression);
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
        return isInfinity() ? this : new SM2P256V1Point(this.curve, this.f2627x, this.f2628y.negate(), this.f2629zs, this.withCompression);
    }
}
