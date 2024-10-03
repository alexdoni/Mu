package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat192;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SecP192K1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f2663Q = SecP192K1Curve.f2660q;

    /* renamed from: x */
    protected int[] f2664x;

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP192K1Field";
    }

    public SecP192K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f2663Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        this.f2664x = SecP192K1Field.fromBigInteger(bigInteger);
    }

    public SecP192K1FieldElement() {
        this.f2664x = Nat192.create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP192K1FieldElement(int[] iArr) {
        this.f2664x = iArr;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat192.isZero(this.f2664x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat192.isOne(this.f2664x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat192.getBit(this.f2664x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f2664x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f2663Q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192K1Field.add(this.f2664x, ((SecP192K1FieldElement) eCFieldElement).f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat192.create();
        SecP192K1Field.addOne(this.f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192K1Field.subtract(this.f2664x, ((SecP192K1FieldElement) eCFieldElement).f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192K1Field.multiply(this.f2664x, ((SecP192K1FieldElement) eCFieldElement).f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        Mod.invert(SecP192K1Field.f2661P, ((SecP192K1FieldElement) eCFieldElement).f2664x, create);
        SecP192K1Field.multiply(create, this.f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat192.create();
        SecP192K1Field.negate(this.f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat192.create();
        SecP192K1Field.square(this.f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat192.create();
        Mod.invert(SecP192K1Field.f2661P, this.f2664x, create);
        return new SecP192K1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f2664x;
        if (Nat192.isZero(iArr) || Nat192.isOne(iArr)) {
            return this;
        }
        int[] create = Nat192.create();
        SecP192K1Field.square(iArr, create);
        SecP192K1Field.multiply(create, iArr, create);
        int[] create2 = Nat192.create();
        SecP192K1Field.square(create, create2);
        SecP192K1Field.multiply(create2, iArr, create2);
        int[] create3 = Nat192.create();
        SecP192K1Field.squareN(create2, 3, create3);
        SecP192K1Field.multiply(create3, create2, create3);
        SecP192K1Field.squareN(create3, 2, create3);
        SecP192K1Field.multiply(create3, create, create3);
        SecP192K1Field.squareN(create3, 8, create);
        SecP192K1Field.multiply(create, create3, create);
        SecP192K1Field.squareN(create, 3, create3);
        SecP192K1Field.multiply(create3, create2, create3);
        int[] create4 = Nat192.create();
        SecP192K1Field.squareN(create3, 16, create4);
        SecP192K1Field.multiply(create4, create, create4);
        SecP192K1Field.squareN(create4, 35, create);
        SecP192K1Field.multiply(create, create4, create);
        SecP192K1Field.squareN(create, 70, create4);
        SecP192K1Field.multiply(create4, create, create4);
        SecP192K1Field.squareN(create4, 19, create);
        SecP192K1Field.multiply(create, create3, create);
        SecP192K1Field.squareN(create, 20, create);
        SecP192K1Field.multiply(create, create3, create);
        SecP192K1Field.squareN(create, 4, create);
        SecP192K1Field.multiply(create, create2, create);
        SecP192K1Field.squareN(create, 6, create);
        SecP192K1Field.multiply(create, create2, create);
        SecP192K1Field.square(create, create);
        SecP192K1Field.square(create, create2);
        if (Nat192.m1546eq(iArr, create2)) {
            return new SecP192K1FieldElement(create);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP192K1FieldElement) {
            return Nat192.m1546eq(this.f2664x, ((SecP192K1FieldElement) obj).f2664x);
        }
        return false;
    }

    public int hashCode() {
        return f2663Q.hashCode() ^ Arrays.hashCode(this.f2664x, 0, 6);
    }
}
