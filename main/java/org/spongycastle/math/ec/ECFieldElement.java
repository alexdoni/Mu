package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Random;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public abstract class ECFieldElement implements ECConstants {
    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public abstract BigInteger toBigInteger();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int i) {
        ECFieldElement eCFieldElement = this;
        for (int i2 = 0; i2 < i; i2++) {
            eCFieldElement = eCFieldElement.square();
        }
        return eCFieldElement;
    }

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public String toString() {
        return toBigInteger().toString(16);
    }

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }

    /* renamed from: org.spongycastle.math.ec.ECFieldElement$Fp */
    /* loaded from: classes3.dex */
    public static class C3198Fp extends ECFieldElement {

        /* renamed from: q */
        BigInteger f2624q;

        /* renamed from: r */
        BigInteger f2625r;

        /* renamed from: x */
        BigInteger f2626x;

        @Override // org.spongycastle.math.ec.ECFieldElement
        public String getFieldName() {
            return "Fp";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static BigInteger calculateResidue(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength < 96 || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return ONE.shiftLeft(bitLength).subtract(bigInteger);
        }

        public C3198Fp(BigInteger bigInteger, BigInteger bigInteger2) {
            this(bigInteger, calculateResidue(bigInteger), bigInteger2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C3198Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            if (bigInteger3 == null || bigInteger3.signum() < 0 || bigInteger3.compareTo(bigInteger) >= 0) {
                throw new IllegalArgumentException("x value invalid in Fp field element");
            }
            this.f2624q = bigInteger;
            this.f2625r = bigInteger2;
            this.f2626x = bigInteger3;
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f2626x;
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public int getFieldSize() {
            return this.f2624q.bitLength();
        }

        public BigInteger getQ() {
            return this.f2624q;
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new C3198Fp(this.f2624q, this.f2625r, modAdd(this.f2626x, eCFieldElement.toBigInteger()));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement addOne() {
            BigInteger add = this.f2626x.add(ECConstants.ONE);
            if (add.compareTo(this.f2624q) == 0) {
                add = ECConstants.ZERO;
            }
            return new C3198Fp(this.f2624q, this.f2625r, add);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new C3198Fp(this.f2624q, this.f2625r, modSubtract(this.f2626x, eCFieldElement.toBigInteger()));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new C3198Fp(this.f2624q, this.f2625r, modMult(this.f2626x, eCFieldElement.toBigInteger()));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f2626x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C3198Fp(this.f2624q, this.f2625r, modReduce(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f2626x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C3198Fp(this.f2624q, this.f2625r, modReduce(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new C3198Fp(this.f2624q, this.f2625r, modMult(this.f2626x, modInverse(eCFieldElement.toBigInteger())));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement negate() {
            if (this.f2626x.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f2624q;
            return new C3198Fp(bigInteger, this.f2625r, bigInteger.subtract(this.f2626x));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement square() {
            BigInteger bigInteger = this.f2624q;
            BigInteger bigInteger2 = this.f2625r;
            BigInteger bigInteger3 = this.f2626x;
            return new C3198Fp(bigInteger, bigInteger2, modMult(bigInteger3, bigInteger3));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f2626x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C3198Fp(this.f2624q, this.f2625r, modReduce(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f2626x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C3198Fp(this.f2624q, this.f2625r, modReduce(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement invert() {
            return new C3198Fp(this.f2624q, this.f2625r, modInverse(this.f2626x));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f2624q.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f2624q.testBit(1)) {
                BigInteger add = this.f2624q.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f2624q;
                return checkSqrt(new C3198Fp(bigInteger, this.f2625r, this.f2626x.modPow(add, bigInteger)));
            }
            if (this.f2624q.testBit(2)) {
                BigInteger modPow = this.f2626x.modPow(this.f2624q.shiftRight(3), this.f2624q);
                BigInteger modMult = modMult(modPow, this.f2626x);
                if (modMult(modMult, modPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new C3198Fp(this.f2624q, this.f2625r, modMult));
                }
                return checkSqrt(new C3198Fp(this.f2624q, this.f2625r, modMult(modMult, ECConstants.TWO.modPow(this.f2624q.shiftRight(2), this.f2624q))));
            }
            BigInteger shiftRight = this.f2624q.shiftRight(1);
            if (!this.f2626x.modPow(shiftRight, this.f2624q).equals(ECConstants.ONE)) {
                return null;
            }
            BigInteger bigInteger2 = this.f2626x;
            BigInteger modDouble = modDouble(modDouble(bigInteger2));
            BigInteger add2 = shiftRight.add(ECConstants.ONE);
            BigInteger subtract = this.f2624q.subtract(ECConstants.ONE);
            Random random = new Random();
            while (true) {
                BigInteger bigInteger3 = new BigInteger(this.f2624q.bitLength(), random);
                if (bigInteger3.compareTo(this.f2624q) < 0 && modReduce(bigInteger3.multiply(bigInteger3).subtract(modDouble)).modPow(shiftRight, this.f2624q).equals(subtract)) {
                    BigInteger[] lucasSequence = lucasSequence(bigInteger3, bigInteger2, add2);
                    BigInteger bigInteger4 = lucasSequence[0];
                    BigInteger bigInteger5 = lucasSequence[1];
                    if (modMult(bigInteger5, bigInteger5).equals(modDouble)) {
                        return new C3198Fp(this.f2624q, this.f2625r, modHalfAbs(bigInteger5));
                    }
                    if (!bigInteger4.equals(ECConstants.ONE) && !bigInteger4.equals(subtract)) {
                        return null;
                    }
                }
            }
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.ONE;
            BigInteger bigInteger5 = ECConstants.TWO;
            BigInteger bigInteger6 = ECConstants.ONE;
            BigInteger bigInteger7 = ECConstants.ONE;
            BigInteger bigInteger8 = bigInteger;
            for (int i = bitLength - 1; i >= lowestSetBit + 1; i--) {
                bigInteger6 = modMult(bigInteger6, bigInteger7);
                if (bigInteger3.testBit(i)) {
                    bigInteger7 = modMult(bigInteger6, bigInteger2);
                    bigInteger4 = modMult(bigInteger4, bigInteger8);
                    bigInteger5 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger8 = modReduce(bigInteger8.multiply(bigInteger8).subtract(bigInteger7.shiftLeft(1)));
                } else {
                    bigInteger4 = modReduce(bigInteger4.multiply(bigInteger5).subtract(bigInteger6));
                    BigInteger modReduce = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger5 = modReduce(bigInteger5.multiply(bigInteger5).subtract(bigInteger6.shiftLeft(1)));
                    bigInteger8 = modReduce;
                    bigInteger7 = bigInteger6;
                }
            }
            BigInteger modMult = modMult(bigInteger6, bigInteger7);
            BigInteger modMult2 = modMult(modMult, bigInteger2);
            BigInteger modReduce2 = modReduce(bigInteger4.multiply(bigInteger5).subtract(modMult));
            BigInteger modReduce3 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(modMult)));
            BigInteger modMult3 = modMult(modMult, modMult2);
            for (int i2 = 1; i2 <= lowestSetBit; i2++) {
                modReduce2 = modMult(modReduce2, modReduce3);
                modReduce3 = modReduce(modReduce3.multiply(modReduce3).subtract(modMult3.shiftLeft(1)));
                modMult3 = modMult(modMult3, modMult3);
            }
            return new BigInteger[]{modReduce2, modReduce3};
        }

        protected BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            return add.compareTo(this.f2624q) >= 0 ? add.subtract(this.f2624q) : add;
        }

        protected BigInteger modDouble(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            return shiftLeft.compareTo(this.f2624q) >= 0 ? shiftLeft.subtract(this.f2624q) : shiftLeft;
        }

        protected BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f2624q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f2624q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modInverse(BigInteger bigInteger) {
            int fieldSize = getFieldSize();
            int i = (fieldSize + 31) >> 5;
            int[] fromBigInteger = Nat.fromBigInteger(fieldSize, this.f2624q);
            int[] fromBigInteger2 = Nat.fromBigInteger(fieldSize, bigInteger);
            int[] create = Nat.create(i);
            Mod.invert(fromBigInteger, fromBigInteger2, create);
            return Nat.toBigInteger(i, create);
        }

        protected BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return modReduce(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger modReduce(BigInteger bigInteger) {
            if (this.f2625r != null) {
                boolean z = bigInteger.signum() < 0;
                if (z) {
                    bigInteger = bigInteger.abs();
                }
                int bitLength = this.f2624q.bitLength();
                boolean equals = this.f2625r.equals(ECConstants.ONE);
                while (bigInteger.bitLength() > bitLength + 1) {
                    BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                    BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                    if (!equals) {
                        shiftRight = shiftRight.multiply(this.f2625r);
                    }
                    bigInteger = shiftRight.add(subtract);
                }
                while (bigInteger.compareTo(this.f2624q) >= 0) {
                    bigInteger = bigInteger.subtract(this.f2624q);
                }
                return (!z || bigInteger.signum() == 0) ? bigInteger : this.f2624q.subtract(bigInteger);
            }
            return bigInteger.mod(this.f2624q);
        }

        protected BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger subtract = bigInteger.subtract(bigInteger2);
            return subtract.signum() < 0 ? subtract.add(this.f2624q) : subtract;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C3198Fp)) {
                return false;
            }
            C3198Fp c3198Fp = (C3198Fp) obj;
            return this.f2624q.equals(c3198Fp.f2624q) && this.f2626x.equals(c3198Fp.f2626x);
        }

        public int hashCode() {
            return this.f2624q.hashCode() ^ this.f2626x.hashCode();
        }
    }

    /* loaded from: classes3.dex */
    public static class F2m extends ECFieldElement {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        /* renamed from: ks */
        private int[] f2621ks;

        /* renamed from: m */
        private int f2622m;
        private int representation;

        /* renamed from: x */
        private LongArray f2623x;

        @Override // org.spongycastle.math.ec.ECFieldElement
        public String getFieldName() {
            return "F2m";
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement negate() {
            return this;
        }

        public F2m(int i, int i2, int i3, int i4, BigInteger bigInteger) {
            if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > i) {
                throw new IllegalArgumentException("x value invalid in F2m field element");
            }
            if (i3 == 0 && i4 == 0) {
                this.representation = 2;
                this.f2621ks = new int[]{i2};
            } else {
                if (i3 >= i4) {
                    throw new IllegalArgumentException("k2 must be smaller than k3");
                }
                if (i3 <= 0) {
                    throw new IllegalArgumentException("k2 must be larger than 0");
                }
                this.representation = 3;
                this.f2621ks = new int[]{i2, i3, i4};
            }
            this.f2622m = i;
            this.f2623x = new LongArray(bigInteger);
        }

        public F2m(int i, int i2, BigInteger bigInteger) {
            this(i, i2, 0, 0, bigInteger);
        }

        private F2m(int i, int[] iArr, LongArray longArray) {
            this.f2622m = i;
            this.representation = iArr.length == 1 ? 2 : 3;
            this.f2621ks = iArr;
            this.f2623x = longArray;
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public int bitLength() {
            return this.f2623x.degree();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public boolean isOne() {
            return this.f2623x.isOne();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public boolean isZero() {
            return this.f2623x.isZero();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public boolean testBitZero() {
            return this.f2623x.testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f2623x.toBigInteger();
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public int getFieldSize() {
            return this.f2622m;
        }

        public static void checkFieldElements(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            if (!(eCFieldElement instanceof F2m) || !(eCFieldElement2 instanceof F2m)) {
                throw new IllegalArgumentException("Field elements are not both instances of ECFieldElement.F2m");
            }
            F2m f2m = (F2m) eCFieldElement;
            F2m f2m2 = (F2m) eCFieldElement2;
            if (f2m.representation != f2m2.representation) {
                throw new IllegalArgumentException("One of the F2m field elements has incorrect representation");
            }
            if (f2m.f2622m != f2m2.f2622m || !Arrays.areEqual(f2m.f2621ks, f2m2.f2621ks)) {
                throw new IllegalArgumentException("Field elements are not elements of the same field F2m");
            }
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f2623x.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f2623x, 0);
            return new F2m(this.f2622m, this.f2621ks, longArray);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement addOne() {
            return new F2m(this.f2622m, this.f2621ks, this.f2623x.addOne());
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int i = this.f2622m;
            int[] iArr = this.f2621ks;
            return new F2m(i, iArr, this.f2623x.modMultiply(((F2m) eCFieldElement).f2623x, i, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f2623x;
            LongArray longArray2 = ((F2m) eCFieldElement).f2623x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f2623x;
            LongArray longArray4 = ((F2m) eCFieldElement3).f2623x;
            LongArray multiply = longArray.multiply(longArray2, this.f2622m, this.f2621ks);
            LongArray multiply2 = longArray3.multiply(longArray4, this.f2622m, this.f2621ks);
            if (multiply == longArray || multiply == longArray2) {
                multiply = (LongArray) multiply.clone();
            }
            multiply.addShiftedByWords(multiply2, 0);
            multiply.reduce(this.f2622m, this.f2621ks);
            return new F2m(this.f2622m, this.f2621ks, multiply);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement square() {
            int i = this.f2622m;
            int[] iArr = this.f2621ks;
            return new F2m(i, iArr, this.f2623x.modSquare(i, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f2623x;
            LongArray longArray2 = ((F2m) eCFieldElement).f2623x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f2623x;
            LongArray square = longArray.square(this.f2622m, this.f2621ks);
            LongArray multiply = longArray2.multiply(longArray3, this.f2622m, this.f2621ks);
            if (square == longArray) {
                square = (LongArray) square.clone();
            }
            square.addShiftedByWords(multiply, 0);
            square.reduce(this.f2622m, this.f2621ks);
            return new F2m(this.f2622m, this.f2621ks, square);
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement squarePow(int i) {
            if (i < 1) {
                return this;
            }
            int i2 = this.f2622m;
            int[] iArr = this.f2621ks;
            return new F2m(i2, iArr, this.f2623x.modSquareN(i, i2, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement invert() {
            int i = this.f2622m;
            int[] iArr = this.f2621ks;
            return new F2m(i, iArr, this.f2623x.modInverse(i, iArr));
        }

        @Override // org.spongycastle.math.ec.ECFieldElement
        public ECFieldElement sqrt() {
            return (this.f2623x.isZero() || this.f2623x.isOne()) ? this : squarePow(this.f2622m - 1);
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int getM() {
            return this.f2622m;
        }

        public int getK1() {
            return this.f2621ks[0];
        }

        public int getK2() {
            int[] iArr = this.f2621ks;
            if (iArr.length >= 2) {
                return iArr[1];
            }
            return 0;
        }

        public int getK3() {
            int[] iArr = this.f2621ks;
            if (iArr.length >= 3) {
                return iArr[2];
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof F2m)) {
                return false;
            }
            F2m f2m = (F2m) obj;
            return this.f2622m == f2m.f2622m && this.representation == f2m.representation && Arrays.areEqual(this.f2621ks, f2m.f2621ks) && this.f2623x.equals(f2m.f2623x);
        }

        public int hashCode() {
            return (this.f2623x.hashCode() ^ this.f2622m) ^ Arrays.hashCode(this.f2621ks);
        }
    }
}
