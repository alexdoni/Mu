package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import org.spongycastle.math.ec.ECFieldElement;

/* loaded from: classes3.dex */
public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;

    /* renamed from: x */
    protected ECFieldElement f2627x;

    /* renamed from: y */
    protected ECFieldElement f2628y;

    /* renamed from: zs */
    protected ECFieldElement[] f2629zs;

    public abstract ECPoint add(ECPoint eCPoint);

    protected abstract ECPoint detach();

    protected abstract boolean getCompressionYTilde();

    public abstract ECPoint negate();

    protected abstract boolean satisfiesCurveEquation();

    public abstract ECPoint subtract(ECPoint eCPoint);

    public abstract ECPoint twice();

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 1 && coordinateSystem != 2) {
            if (coordinateSystem == 3) {
                return new ECFieldElement[]{fromBigInteger, fromBigInteger, fromBigInteger};
            }
            if (coordinateSystem == 4) {
                return new ECFieldElement[]{fromBigInteger, eCCurve.getA()};
            }
            if (coordinateSystem != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{fromBigInteger};
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.f2627x = eCFieldElement;
        this.f2628y = eCFieldElement2;
        this.f2629zs = eCFieldElementArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean satisfiesCofactor() {
        BigInteger cofactor = this.curve.getCofactor();
        return cofactor == null || cofactor.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, cofactor).isInfinity();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    protected int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public ECFieldElement getXCoord() {
        return this.f2627x;
    }

    public ECFieldElement getYCoord() {
        return this.f2628y;
    }

    public ECFieldElement getZCoord(int i) {
        if (i >= 0) {
            ECFieldElement[] eCFieldElementArr = this.f2629zs;
            if (i < eCFieldElementArr.length) {
                return eCFieldElementArr[i];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.f2629zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    public final ECFieldElement getRawXCoord() {
        return this.f2627x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.f2628y;
    }

    protected final ECFieldElement[] getRawZCoords() {
        return this.f2629zs;
    }

    protected void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        return curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.f2629zs[0].isOne();
    }

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        return zCoord.isOne() ? this : normalize(zCoord.invert());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 1) {
            if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                ECFieldElement square = eCFieldElement.square();
                return createScaledPoint(square, square.multiply(eCFieldElement));
            }
            if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    protected ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2), this.withCompression);
    }

    public boolean isInfinity() {
        if (this.f2627x != null && this.f2628y != null) {
            ECFieldElement[] eCFieldElementArr = this.f2629zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public boolean isValid() {
        return isInfinity() || getCurve() == null || (satisfiesCurveEquation() && satisfiesCofactor());
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPoint2;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve = getCurve();
        ECCurve curve2 = eCPoint.getCurve();
        boolean z = curve == null;
        boolean z2 = curve2 == null;
        boolean isInfinity = isInfinity();
        boolean isInfinity2 = eCPoint.isInfinity();
        if (isInfinity || isInfinity2) {
            if (isInfinity && isInfinity2) {
                return z || z2 || curve.equals(curve2);
            }
            return false;
        }
        if (!z || !z2) {
            if (!z) {
                if (z2) {
                    eCPoint2 = normalize();
                } else {
                    if (!curve.equals(curve2)) {
                        return false;
                    }
                    ECPoint[] eCPointArr = {this, curve.importPoint(eCPoint)};
                    curve.normalizeAll(eCPointArr);
                    eCPoint2 = eCPointArr[0];
                    eCPoint = eCPointArr[1];
                }
                return eCPoint2.getXCoord().equals(eCPoint.getXCoord()) && eCPoint2.getYCoord().equals(eCPoint.getYCoord());
            }
            eCPoint = eCPoint.normalize();
        }
        eCPoint2 = this;
        if (eCPoint2.getXCoord().equals(eCPoint.getXCoord())) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return equals((ECPoint) obj);
        }
        return false;
    }

    public int hashCode() {
        ECCurve curve = getCurve();
        int i = curve == null ? 0 : ~curve.hashCode();
        if (isInfinity()) {
            return i;
        }
        ECPoint normalize = normalize();
        return (i ^ (normalize.getXCoord().hashCode() * 17)) ^ (normalize.getYCoord().hashCode() * 257);
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer("(");
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(getRawYCoord());
        for (int i = 0; i < this.f2629zs.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(this.f2629zs[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normalize = normalize();
        byte[] encoded = normalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[encoded.length + 1];
            bArr[0] = (byte) (normalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = normalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[encoded.length + encoded2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public ECPoint timesPow2(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPoint = this;
        while (true) {
            i--;
            if (i < 0) {
                return eCPoint;
            }
            eCPoint = eCPoint.twice();
        }
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    /* loaded from: classes3.dex */
    public static abstract class AbstractFp extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.f2627x;
            ECFieldElement eCFieldElement2 = this.f2628y;
            ECFieldElement a2 = this.curve.getA();
            ECFieldElement b = this.curve.getB();
            ECFieldElement square = eCFieldElement2.square();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 0) {
                if (curveCoordinateSystem == 1) {
                    ECFieldElement eCFieldElement3 = this.f2629zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement square2 = eCFieldElement3.square();
                        ECFieldElement multiply = eCFieldElement3.multiply(square2);
                        square = square.multiply(eCFieldElement3);
                        a2 = a2.multiply(square2);
                        b = b.multiply(multiply);
                    }
                } else if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                    ECFieldElement eCFieldElement4 = this.f2629zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement square3 = eCFieldElement4.square();
                        ECFieldElement square4 = square3.square();
                        ECFieldElement multiply2 = square3.multiply(square4);
                        a2 = a2.multiply(square4);
                        b = b.multiply(multiply2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return square.equals(eCFieldElement.square().add(a2).multiply(eCFieldElement).add(b));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }
    }

    /* renamed from: org.spongycastle.math.ec.ECPoint$Fp */
    /* loaded from: classes3.dex */
    public static class C3199Fp extends AbstractFp {
        public C3199Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public C3199Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.withCompression = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C3199Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected ECPoint detach() {
            return new C3199Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int i) {
            if (i == 1 && 4 == getCurveCoordinateSystem()) {
                return getJacobianModifiedW();
            }
            return super.getZCoord(i);
        }

        /* JADX WARN: Code restructure failed: missing block: B:58:0x0123, code lost:
        
            if (r1 == r6) goto L59;
         */
        @Override // org.spongycastle.math.ec.ECPoint
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public org.spongycastle.math.ec.ECPoint add(org.spongycastle.math.ec.ECPoint r17) {
            /*
                Method dump skipped, instructions count: 543
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.ECPoint.C3199Fp.add(org.spongycastle.math.ec.ECPoint):org.spongycastle.math.ec.ECPoint");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement four;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement2 = this.f2628y;
            if (eCFieldElement2.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement3 = this.f2627x;
            if (coordinateSystem == 0) {
                ECFieldElement divide = three(eCFieldElement3.square()).add(getCurve().getA()).divide(two(eCFieldElement2));
                ECFieldElement subtract = divide.square().subtract(two(eCFieldElement3));
                return new C3199Fp(curve, subtract, divide.multiply(eCFieldElement3.subtract(subtract)).subtract(eCFieldElement2), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement4 = this.f2629zs[0];
                boolean isOne = eCFieldElement4.isOne();
                ECFieldElement a2 = curve.getA();
                if (!a2.isZero() && !isOne) {
                    a2 = a2.multiply(eCFieldElement4.square());
                }
                ECFieldElement add = a2.add(three(eCFieldElement3.square()));
                ECFieldElement multiply = isOne ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement4);
                ECFieldElement square = isOne ? eCFieldElement2.square() : multiply.multiply(eCFieldElement2);
                ECFieldElement four2 = four(eCFieldElement3.multiply(square));
                ECFieldElement subtract2 = add.square().subtract(two(four2));
                ECFieldElement two = two(multiply);
                ECFieldElement multiply2 = subtract2.multiply(two);
                ECFieldElement two2 = two(square);
                return new C3199Fp(curve, multiply2, four2.subtract(subtract2).multiply(add).subtract(two(two2.square())), new ECFieldElement[]{two(isOne ? two(two2) : two.square()).multiply(multiply)}, this.withCompression);
            }
            if (coordinateSystem != 2) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(true);
                }
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement5 = this.f2629zs[0];
            boolean isOne2 = eCFieldElement5.isOne();
            ECFieldElement square2 = eCFieldElement2.square();
            ECFieldElement square3 = square2.square();
            ECFieldElement a3 = curve.getA();
            ECFieldElement negate = a3.negate();
            if (negate.toBigInteger().equals(BigInteger.valueOf(3L))) {
                ECFieldElement square4 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
                eCFieldElement = three(eCFieldElement3.add(square4).multiply(eCFieldElement3.subtract(square4)));
                four = four(square2.multiply(eCFieldElement3));
            } else {
                ECFieldElement three = three(eCFieldElement3.square());
                if (isOne2) {
                    eCFieldElement = three.add(a3);
                } else if (a3.isZero()) {
                    eCFieldElement = three;
                } else {
                    ECFieldElement square5 = eCFieldElement5.square().square();
                    if (negate.bitLength() < a3.bitLength()) {
                        eCFieldElement = three.subtract(square5.multiply(negate));
                    } else {
                        eCFieldElement = three.add(square5.multiply(a3));
                    }
                }
                four = four(eCFieldElement3.multiply(square2));
            }
            ECFieldElement subtract3 = eCFieldElement.square().subtract(two(four));
            ECFieldElement subtract4 = four.subtract(subtract3).multiply(eCFieldElement).subtract(eight(square3));
            ECFieldElement two3 = two(eCFieldElement2);
            if (!isOne2) {
                two3 = two3.multiply(eCFieldElement5);
            }
            return new C3199Fp(curve, subtract3, subtract4, new ECFieldElement[]{two3}, this.withCompression);
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
            ECFieldElement eCFieldElement = this.f2628y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(false).add(eCPoint);
                }
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = this.f2627x;
            ECFieldElement eCFieldElement3 = eCPoint.f2627x;
            ECFieldElement eCFieldElement4 = eCPoint.f2628y;
            ECFieldElement subtract = eCFieldElement3.subtract(eCFieldElement2);
            ECFieldElement subtract2 = eCFieldElement4.subtract(eCFieldElement);
            if (subtract.isZero()) {
                return subtract2.isZero() ? threeTimes() : this;
            }
            ECFieldElement square = subtract.square();
            ECFieldElement subtract3 = square.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(subtract2.square());
            if (subtract3.isZero()) {
                return curve.getInfinity();
            }
            ECFieldElement invert = subtract3.multiply(subtract).invert();
            ECFieldElement multiply = subtract3.multiply(invert).multiply(subtract2);
            ECFieldElement subtract4 = two(eCFieldElement).multiply(square).multiply(subtract).multiply(invert).subtract(multiply);
            ECFieldElement add = subtract4.subtract(multiply).multiply(multiply.add(subtract4)).add(eCFieldElement3);
            return new C3199Fp(curve, add, eCFieldElement2.subtract(add).multiply(subtract4).subtract(eCFieldElement), this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f2628y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(false).add(this);
                }
                return twice().add(this);
            }
            ECFieldElement eCFieldElement2 = this.f2627x;
            ECFieldElement two = two(eCFieldElement);
            ECFieldElement square = two.square();
            ECFieldElement add = three(eCFieldElement2.square()).add(getCurve().getA());
            ECFieldElement subtract = three(eCFieldElement2).multiply(square).subtract(add.square());
            if (subtract.isZero()) {
                return getCurve().getInfinity();
            }
            ECFieldElement invert = subtract.multiply(two).invert();
            ECFieldElement multiply = subtract.multiply(invert).multiply(add);
            ECFieldElement subtract2 = square.square().multiply(invert).subtract(multiply);
            ECFieldElement add2 = subtract2.subtract(multiply).multiply(multiply.add(subtract2)).add(eCFieldElement2);
            return new C3199Fp(curve, add2, eCFieldElement2.subtract(add2).multiply(subtract2).subtract(eCFieldElement), this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint timesPow2(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            }
            if (i == 0 || isInfinity()) {
                return this;
            }
            if (i == 1) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f2628y;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement a2 = curve.getA();
            ECFieldElement eCFieldElement2 = this.f2627x;
            ECFieldElement fromBigInteger = this.f2629zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.f2629zs[0];
            if (!fromBigInteger.isOne() && coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    ECFieldElement square = fromBigInteger.square();
                    eCFieldElement2 = eCFieldElement2.multiply(fromBigInteger);
                    eCFieldElement = eCFieldElement.multiply(square);
                    a2 = calculateJacobianModifiedW(fromBigInteger, square);
                } else if (coordinateSystem == 2) {
                    a2 = calculateJacobianModifiedW(fromBigInteger, null);
                } else if (coordinateSystem == 4) {
                    a2 = getJacobianModifiedW();
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            int i2 = 0;
            ECFieldElement eCFieldElement3 = a2;
            ECFieldElement eCFieldElement4 = eCFieldElement;
            ECFieldElement eCFieldElement5 = eCFieldElement2;
            ECFieldElement eCFieldElement6 = eCFieldElement3;
            while (i2 < i) {
                if (eCFieldElement4.isZero()) {
                    return curve.getInfinity();
                }
                ECFieldElement three = three(eCFieldElement5.square());
                ECFieldElement two = two(eCFieldElement4);
                ECFieldElement multiply = two.multiply(eCFieldElement4);
                ECFieldElement two2 = two(eCFieldElement5.multiply(multiply));
                ECFieldElement two3 = two(multiply.square());
                if (!eCFieldElement6.isZero()) {
                    three = three.add(eCFieldElement6);
                    eCFieldElement6 = two(two3.multiply(eCFieldElement6));
                }
                ECFieldElement subtract = three.square().subtract(two(two2));
                eCFieldElement4 = three.multiply(two2.subtract(subtract)).subtract(two3);
                fromBigInteger = fromBigInteger.isOne() ? two : two.multiply(fromBigInteger);
                i2++;
                eCFieldElement5 = subtract;
            }
            if (coordinateSystem == 0) {
                ECFieldElement invert = fromBigInteger.invert();
                ECFieldElement square2 = invert.square();
                return new C3199Fp(curve, eCFieldElement5.multiply(square2), eCFieldElement4.multiply(square2.multiply(invert)), this.withCompression);
            }
            if (coordinateSystem == 1) {
                return new C3199Fp(curve, eCFieldElement5.multiply(fromBigInteger), eCFieldElement4, new ECFieldElement[]{fromBigInteger.multiply(fromBigInteger.square())}, this.withCompression);
            }
            if (coordinateSystem == 2) {
                return new C3199Fp(curve, eCFieldElement5, eCFieldElement4, new ECFieldElement[]{fromBigInteger}, this.withCompression);
            }
            if (coordinateSystem == 4) {
                return new C3199Fp(curve, eCFieldElement5, eCFieldElement4, new ECFieldElement[]{fromBigInteger, eCFieldElement6}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        protected ECFieldElement two(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }

        protected ECFieldElement three(ECFieldElement eCFieldElement) {
            return two(eCFieldElement).add(eCFieldElement);
        }

        protected ECFieldElement four(ECFieldElement eCFieldElement) {
            return two(two(eCFieldElement));
        }

        protected ECFieldElement eight(ECFieldElement eCFieldElement) {
            return four(two(eCFieldElement));
        }

        protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
            return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            if (curve.getCoordinateSystem() != 0) {
                return new C3199Fp(curve, this.f2627x, this.f2628y.negate(), this.f2629zs, this.withCompression);
            }
            return new C3199Fp(curve, this.f2627x, this.f2628y.negate(), this.withCompression);
        }

        protected ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a2 = getCurve().getA();
            if (a2.isZero() || eCFieldElement.isOne()) {
                return a2;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement square = eCFieldElement2.square();
            ECFieldElement negate = a2.negate();
            if (negate.bitLength() < a2.bitLength()) {
                return square.multiply(negate).negate();
            }
            return square.multiply(a2);
        }

        protected ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.f2629zs[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement[] eCFieldElementArr = this.f2629zs;
            ECFieldElement calculateJacobianModifiedW = calculateJacobianModifiedW(this.f2629zs[0], null);
            eCFieldElementArr[1] = calculateJacobianModifiedW;
            return calculateJacobianModifiedW;
        }

        protected C3199Fp twiceJacobianModified(boolean z) {
            ECFieldElement eCFieldElement = this.f2627x;
            ECFieldElement eCFieldElement2 = this.f2628y;
            ECFieldElement eCFieldElement3 = this.f2629zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement add = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement two = two(eCFieldElement2);
            ECFieldElement multiply = two.multiply(eCFieldElement2);
            ECFieldElement two2 = two(eCFieldElement.multiply(multiply));
            ECFieldElement subtract = add.square().subtract(two(two2));
            ECFieldElement two3 = two(multiply.square());
            ECFieldElement subtract2 = add.multiply(two2.subtract(subtract)).subtract(two3);
            ECFieldElement two4 = z ? two(two3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                two = two.multiply(eCFieldElement3);
            }
            return new C3199Fp(getCurve(), subtract, subtract2, new ECFieldElement[]{two, two4}, this.withCompression);
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class AbstractF2m extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement multiplyPlusProduct;
            ECFieldElement squarePlusProduct;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f2627x;
            ECFieldElement a2 = curve.getA();
            ECFieldElement b = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement2 = this.f2629zs[0];
                boolean isOne = eCFieldElement2.isOne();
                if (eCFieldElement.isZero()) {
                    ECFieldElement square = this.f2628y.square();
                    if (!isOne) {
                        b = b.multiply(eCFieldElement2.square());
                    }
                    return square.equals(b);
                }
                ECFieldElement eCFieldElement3 = this.f2628y;
                ECFieldElement square2 = eCFieldElement.square();
                if (isOne) {
                    multiplyPlusProduct = eCFieldElement3.square().add(eCFieldElement3).add(a2);
                    squarePlusProduct = square2.square().add(b);
                } else {
                    ECFieldElement square3 = eCFieldElement2.square();
                    ECFieldElement square4 = square3.square();
                    multiplyPlusProduct = eCFieldElement3.add(eCFieldElement2).multiplyPlusProduct(eCFieldElement3, a2, square3);
                    squarePlusProduct = square2.squarePlusProduct(b, square4);
                }
                return multiplyPlusProduct.multiply(square2).equals(squarePlusProduct);
            }
            ECFieldElement eCFieldElement4 = this.f2628y;
            ECFieldElement multiply = eCFieldElement4.add(eCFieldElement).multiply(eCFieldElement4);
            if (coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    ECFieldElement eCFieldElement5 = this.f2629zs[0];
                    if (!eCFieldElement5.isOne()) {
                        ECFieldElement multiply2 = eCFieldElement5.multiply(eCFieldElement5.square());
                        multiply = multiply.multiply(eCFieldElement5);
                        a2 = a2.multiply(eCFieldElement5);
                        b = b.multiply(multiply2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return multiply.equals(eCFieldElement.add(a2).multiply(eCFieldElement.square()).add(b));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords(), this.withCompression);
            }
            if (curveCoordinateSystem == 6) {
                ECFieldElement rawXCoord2 = getRawXCoord();
                ECFieldElement rawYCoord = getRawYCoord();
                ECFieldElement eCFieldElement2 = getRawZCoords()[0];
                ECFieldElement multiply = rawXCoord2.multiply(eCFieldElement.square());
                return getCurve().createRawPoint(multiply, rawYCoord.add(rawXCoord2).add(multiply), new ECFieldElement[]{eCFieldElement2.multiply(eCFieldElement)}, this.withCompression);
            }
            return super.scaleX(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords(), this.withCompression);
            }
            return super.scaleY(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f2627x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.f2628y.square(), new ECFieldElement[]{this.f2629zs[0].square()}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.f2628y.square(), this.withCompression);
        }

        public AbstractF2m tauPow(int i) {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f2627x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i), this.f2628y.squarePow(i), new ECFieldElement[]{this.f2629zs[0].squarePow(i)}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i), this.f2628y.squarePow(i), this.withCompression);
        }
    }

    /* loaded from: classes3.dex */
    public static class F2m extends AbstractF2m {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (eCFieldElement != null) {
                ECFieldElement.F2m.checkFieldElements(this.f2627x, this.f2628y);
                if (eCCurve != null) {
                    ECFieldElement.F2m.checkFieldElements(this.f2627x, this.curve.getA());
                }
            }
            this.withCompression = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) {
                ECFieldElement eCFieldElement = this.f2627x;
                ECFieldElement eCFieldElement2 = this.f2628y;
                if (isInfinity() || eCFieldElement.isZero()) {
                    return eCFieldElement2;
                }
                ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
                if (6 != curveCoordinateSystem) {
                    return multiply;
                }
                ECFieldElement eCFieldElement3 = this.f2629zs[0];
                return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
            }
            return this.f2628y;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) {
                return rawYCoord.testBitZero() != rawXCoord.testBitZero();
            }
            return rawYCoord.divide(rawXCoord).testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElement;
            ECFieldElement eCFieldElement2;
            ECFieldElement eCFieldElement3;
            ECFieldElement eCFieldElement4;
            ECFieldElement eCFieldElement5;
            ECFieldElement eCFieldElement6;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement7 = this.f2627x;
            ECFieldElement eCFieldElement8 = eCPoint.f2627x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement9 = this.f2628y;
                ECFieldElement eCFieldElement10 = eCPoint.f2628y;
                ECFieldElement add = eCFieldElement7.add(eCFieldElement8);
                ECFieldElement add2 = eCFieldElement9.add(eCFieldElement10);
                if (add.isZero()) {
                    if (add2.isZero()) {
                        return twice();
                    }
                    return curve.getInfinity();
                }
                ECFieldElement divide = add2.divide(add);
                ECFieldElement add3 = divide.square().add(divide).add(add).add(curve.getA());
                return new F2m(curve, add3, divide.multiply(eCFieldElement7.add(add3)).add(add3).add(eCFieldElement9), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement11 = this.f2628y;
                ECFieldElement eCFieldElement12 = this.f2629zs[0];
                ECFieldElement eCFieldElement13 = eCPoint.f2628y;
                ECFieldElement eCFieldElement14 = eCPoint.f2629zs[0];
                boolean isOne = eCFieldElement14.isOne();
                ECFieldElement add4 = eCFieldElement12.multiply(eCFieldElement13).add(isOne ? eCFieldElement11 : eCFieldElement11.multiply(eCFieldElement14));
                ECFieldElement add5 = eCFieldElement12.multiply(eCFieldElement8).add(isOne ? eCFieldElement7 : eCFieldElement7.multiply(eCFieldElement14));
                if (add5.isZero()) {
                    if (add4.isZero()) {
                        return twice();
                    }
                    return curve.getInfinity();
                }
                ECFieldElement square = add5.square();
                ECFieldElement multiply = square.multiply(add5);
                if (!isOne) {
                    eCFieldElement12 = eCFieldElement12.multiply(eCFieldElement14);
                }
                ECFieldElement add6 = add4.add(add5);
                ECFieldElement add7 = add6.multiplyPlusProduct(add4, square, curve.getA()).multiply(eCFieldElement12).add(multiply);
                ECFieldElement multiply2 = add5.multiply(add7);
                if (!isOne) {
                    square = square.multiply(eCFieldElement14);
                }
                return new F2m(curve, multiply2, add4.multiplyPlusProduct(eCFieldElement7, add5, eCFieldElement11).multiplyPlusProduct(square, add6, add7), new ECFieldElement[]{multiply.multiply(eCFieldElement12)}, this.withCompression);
            }
            if (coordinateSystem == 6) {
                if (eCFieldElement7.isZero()) {
                    if (eCFieldElement8.isZero()) {
                        return curve.getInfinity();
                    }
                    return eCPoint.add(this);
                }
                ECFieldElement eCFieldElement15 = this.f2628y;
                ECFieldElement eCFieldElement16 = this.f2629zs[0];
                ECFieldElement eCFieldElement17 = eCPoint.f2628y;
                ECFieldElement eCFieldElement18 = eCPoint.f2629zs[0];
                boolean isOne2 = eCFieldElement16.isOne();
                if (isOne2) {
                    eCFieldElement = eCFieldElement8;
                    eCFieldElement2 = eCFieldElement17;
                } else {
                    eCFieldElement = eCFieldElement8.multiply(eCFieldElement16);
                    eCFieldElement2 = eCFieldElement17.multiply(eCFieldElement16);
                }
                boolean isOne3 = eCFieldElement18.isOne();
                if (isOne3) {
                    eCFieldElement3 = eCFieldElement15;
                } else {
                    eCFieldElement7 = eCFieldElement7.multiply(eCFieldElement18);
                    eCFieldElement3 = eCFieldElement15.multiply(eCFieldElement18);
                }
                ECFieldElement add8 = eCFieldElement3.add(eCFieldElement2);
                ECFieldElement add9 = eCFieldElement7.add(eCFieldElement);
                if (add9.isZero()) {
                    if (add8.isZero()) {
                        return twice();
                    }
                    return curve.getInfinity();
                }
                if (eCFieldElement8.isZero()) {
                    ECPoint normalize = normalize();
                    ECFieldElement xCoord = normalize.getXCoord();
                    ECFieldElement yCoord = normalize.getYCoord();
                    ECFieldElement divide2 = yCoord.add(eCFieldElement17).divide(xCoord);
                    eCFieldElement4 = divide2.square().add(divide2).add(xCoord).add(curve.getA());
                    if (eCFieldElement4.isZero()) {
                        return new F2m(curve, eCFieldElement4, curve.getB().sqrt(), this.withCompression);
                    }
                    eCFieldElement6 = divide2.multiply(xCoord.add(eCFieldElement4)).add(eCFieldElement4).add(yCoord).divide(eCFieldElement4).add(eCFieldElement4);
                    eCFieldElement5 = curve.fromBigInteger(ECConstants.ONE);
                } else {
                    ECFieldElement square2 = add9.square();
                    ECFieldElement multiply3 = add8.multiply(eCFieldElement7);
                    ECFieldElement multiply4 = add8.multiply(eCFieldElement);
                    ECFieldElement multiply5 = multiply3.multiply(multiply4);
                    if (multiply5.isZero()) {
                        return new F2m(curve, multiply5, curve.getB().sqrt(), this.withCompression);
                    }
                    ECFieldElement multiply6 = add8.multiply(square2);
                    ECFieldElement multiply7 = !isOne3 ? multiply6.multiply(eCFieldElement18) : multiply6;
                    ECFieldElement squarePlusProduct = multiply4.add(square2).squarePlusProduct(multiply7, eCFieldElement15.add(eCFieldElement16));
                    if (!isOne2) {
                        multiply7 = multiply7.multiply(eCFieldElement16);
                    }
                    eCFieldElement4 = multiply5;
                    eCFieldElement5 = multiply7;
                    eCFieldElement6 = squarePlusProduct;
                }
                return new F2m(curve, eCFieldElement4, eCFieldElement6, new ECFieldElement[]{eCFieldElement5}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement add;
            ECFieldElement squarePlusProduct;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f2627x;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement add2 = this.f2628y.divide(eCFieldElement).add(eCFieldElement);
                ECFieldElement add3 = add2.square().add(add2).add(curve.getA());
                return new F2m(curve, add3, eCFieldElement.squarePlusProduct(add3, add2.addOne()), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement2 = this.f2628y;
                ECFieldElement eCFieldElement3 = this.f2629zs[0];
                boolean isOne = eCFieldElement3.isOne();
                ECFieldElement multiply = isOne ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
                if (!isOne) {
                    eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement3);
                }
                ECFieldElement square = eCFieldElement.square();
                ECFieldElement add4 = square.add(eCFieldElement2);
                ECFieldElement square2 = multiply.square();
                ECFieldElement add5 = add4.add(multiply);
                ECFieldElement multiplyPlusProduct = add5.multiplyPlusProduct(add4, square2, curve.getA());
                return new F2m(curve, multiply.multiply(multiplyPlusProduct), square.square().multiplyPlusProduct(multiply, multiplyPlusProduct, add5), new ECFieldElement[]{multiply.multiply(square2)}, this.withCompression);
            }
            if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement4 = this.f2628y;
                ECFieldElement eCFieldElement5 = this.f2629zs[0];
                boolean isOne2 = eCFieldElement5.isOne();
                ECFieldElement multiply2 = isOne2 ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement5);
                ECFieldElement square3 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
                ECFieldElement a2 = curve.getA();
                ECFieldElement multiply3 = isOne2 ? a2 : a2.multiply(square3);
                ECFieldElement add6 = eCFieldElement4.square().add(multiply2).add(multiply3);
                if (add6.isZero()) {
                    return new F2m(curve, add6, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement square4 = add6.square();
                ECFieldElement multiply4 = isOne2 ? add6 : add6.multiply(square3);
                ECFieldElement b = curve.getB();
                if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                    ECFieldElement square5 = eCFieldElement4.add(eCFieldElement).square();
                    if (b.isOne()) {
                        squarePlusProduct = multiply3.add(square3).square();
                    } else {
                        squarePlusProduct = multiply3.squarePlusProduct(b, square3.square());
                    }
                    add = square5.add(add6).add(square3).multiply(square5).add(squarePlusProduct).add(square4);
                    if (a2.isZero()) {
                        add = add.add(multiply4);
                    } else if (!a2.isOne()) {
                        add = add.add(a2.addOne().multiply(multiply4));
                    }
                } else {
                    if (!isOne2) {
                        eCFieldElement = eCFieldElement.multiply(eCFieldElement5);
                    }
                    add = eCFieldElement.squarePlusProduct(add6, multiply2).add(square4).add(multiply4);
                }
                return new F2m(curve, square4, add, new ECFieldElement[]{multiply4}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f2627x;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            if (curve.getCoordinateSystem() == 6) {
                ECFieldElement eCFieldElement2 = eCPoint.f2627x;
                ECFieldElement eCFieldElement3 = eCPoint.f2629zs[0];
                if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                    return twice().add(eCPoint);
                }
                ECFieldElement eCFieldElement4 = this.f2628y;
                ECFieldElement eCFieldElement5 = this.f2629zs[0];
                ECFieldElement eCFieldElement6 = eCPoint.f2628y;
                ECFieldElement square = eCFieldElement.square();
                ECFieldElement square2 = eCFieldElement4.square();
                ECFieldElement square3 = eCFieldElement5.square();
                ECFieldElement add = curve.getA().multiply(square3).add(square2).add(eCFieldElement4.multiply(eCFieldElement5));
                ECFieldElement addOne = eCFieldElement6.addOne();
                ECFieldElement multiplyPlusProduct = curve.getA().add(addOne).multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
                ECFieldElement multiply = eCFieldElement2.multiply(square3);
                ECFieldElement square4 = multiply.add(add).square();
                if (square4.isZero()) {
                    if (multiplyPlusProduct.isZero()) {
                        return eCPoint.twice();
                    }
                    return curve.getInfinity();
                }
                if (multiplyPlusProduct.isZero()) {
                    return new F2m(curve, multiplyPlusProduct, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
                ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
                return new F2m(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3}, this.withCompression);
            }
            return twice().add(eCPoint);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f2627x;
            if (eCFieldElement.isZero()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 0) {
                return new F2m(this.curve, eCFieldElement, this.f2628y.add(eCFieldElement), this.withCompression);
            }
            if (curveCoordinateSystem == 1) {
                return new F2m(this.curve, eCFieldElement, this.f2628y.add(eCFieldElement), new ECFieldElement[]{this.f2629zs[0]}, this.withCompression);
            }
            if (curveCoordinateSystem == 5) {
                return new F2m(this.curve, eCFieldElement, this.f2628y.addOne(), this.withCompression);
            }
            if (curveCoordinateSystem == 6) {
                ECFieldElement eCFieldElement2 = this.f2628y;
                ECFieldElement eCFieldElement3 = this.f2629zs[0];
                return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }
}
