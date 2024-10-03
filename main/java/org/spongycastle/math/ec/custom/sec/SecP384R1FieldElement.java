package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SecP384R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f2696Q = SecP384R1Curve.f2693q;

    /* renamed from: x */
    protected int[] f2697x;

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP384R1Field";
    }

    public SecP384R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f2696Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        this.f2697x = SecP384R1Field.fromBigInteger(bigInteger);
    }

    public SecP384R1FieldElement() {
        this.f2697x = Nat.create(12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP384R1FieldElement(int[] iArr) {
        this.f2697x = iArr;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat.isZero(12, this.f2697x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat.isOne(12, this.f2697x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat.getBit(this.f2697x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(12, this.f2697x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f2696Q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(12);
        SecP384R1Field.add(this.f2697x, ((SecP384R1FieldElement) eCFieldElement).f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat.create(12);
        SecP384R1Field.addOne(this.f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(12);
        SecP384R1Field.subtract(this.f2697x, ((SecP384R1FieldElement) eCFieldElement).f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(12);
        SecP384R1Field.multiply(this.f2697x, ((SecP384R1FieldElement) eCFieldElement).f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(12);
        Mod.invert(SecP384R1Field.f2695P, ((SecP384R1FieldElement) eCFieldElement).f2697x, create);
        SecP384R1Field.multiply(create, this.f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat.create(12);
        SecP384R1Field.negate(this.f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat.create(12);
        SecP384R1Field.square(this.f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat.create(12);
        Mod.invert(SecP384R1Field.f2695P, this.f2697x, create);
        return new SecP384R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f2697x;
        if (Nat.isZero(12, iArr) || Nat.isOne(12, iArr)) {
            return this;
        }
        int[] create = Nat.create(12);
        int[] create2 = Nat.create(12);
        int[] create3 = Nat.create(12);
        int[] create4 = Nat.create(12);
        SecP384R1Field.square(iArr, create);
        SecP384R1Field.multiply(create, iArr, create);
        SecP384R1Field.squareN(create, 2, create2);
        SecP384R1Field.multiply(create2, create, create2);
        SecP384R1Field.square(create2, create2);
        SecP384R1Field.multiply(create2, iArr, create2);
        SecP384R1Field.squareN(create2, 5, create3);
        SecP384R1Field.multiply(create3, create2, create3);
        SecP384R1Field.squareN(create3, 5, create4);
        SecP384R1Field.multiply(create4, create2, create4);
        SecP384R1Field.squareN(create4, 15, create2);
        SecP384R1Field.multiply(create2, create4, create2);
        SecP384R1Field.squareN(create2, 2, create3);
        SecP384R1Field.multiply(create, create3, create);
        SecP384R1Field.squareN(create3, 28, create3);
        SecP384R1Field.multiply(create2, create3, create2);
        SecP384R1Field.squareN(create2, 60, create3);
        SecP384R1Field.multiply(create3, create2, create3);
        SecP384R1Field.squareN(create3, 120, create2);
        SecP384R1Field.multiply(create2, create3, create2);
        SecP384R1Field.squareN(create2, 15, create2);
        SecP384R1Field.multiply(create2, create4, create2);
        SecP384R1Field.squareN(create2, 33, create2);
        SecP384R1Field.multiply(create2, create, create2);
        SecP384R1Field.squareN(create2, 64, create2);
        SecP384R1Field.multiply(create2, iArr, create2);
        SecP384R1Field.squareN(create2, 30, create);
        SecP384R1Field.square(create, create2);
        if (Nat.m1543eq(12, iArr, create2)) {
            return new SecP384R1FieldElement(create);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP384R1FieldElement) {
            return Nat.m1543eq(12, this.f2697x, ((SecP384R1FieldElement) obj).f2697x);
        }
        return false;
    }

    public int hashCode() {
        return f2696Q.hashCode() ^ Arrays.hashCode(this.f2697x, 0, 12);
    }
}
