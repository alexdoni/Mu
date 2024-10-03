package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat128;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SecP128R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f2646Q = SecP128R1Curve.f2643q;

    /* renamed from: x */
    protected int[] f2647x;

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP128R1Field";
    }

    public SecP128R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f2646Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        this.f2647x = SecP128R1Field.fromBigInteger(bigInteger);
    }

    public SecP128R1FieldElement() {
        this.f2647x = Nat128.create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP128R1FieldElement(int[] iArr) {
        this.f2647x = iArr;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat128.isZero(this.f2647x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat128.isOne(this.f2647x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat128.getBit(this.f2647x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat128.toBigInteger(this.f2647x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f2646Q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat128.create();
        SecP128R1Field.add(this.f2647x, ((SecP128R1FieldElement) eCFieldElement).f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat128.create();
        SecP128R1Field.addOne(this.f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat128.create();
        SecP128R1Field.subtract(this.f2647x, ((SecP128R1FieldElement) eCFieldElement).f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat128.create();
        SecP128R1Field.multiply(this.f2647x, ((SecP128R1FieldElement) eCFieldElement).f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat128.create();
        Mod.invert(SecP128R1Field.f2645P, ((SecP128R1FieldElement) eCFieldElement).f2647x, create);
        SecP128R1Field.multiply(create, this.f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat128.create();
        SecP128R1Field.negate(this.f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat128.create();
        SecP128R1Field.square(this.f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat128.create();
        Mod.invert(SecP128R1Field.f2645P, this.f2647x, create);
        return new SecP128R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f2647x;
        if (Nat128.isZero(iArr) || Nat128.isOne(iArr)) {
            return this;
        }
        int[] create = Nat128.create();
        SecP128R1Field.square(iArr, create);
        SecP128R1Field.multiply(create, iArr, create);
        int[] create2 = Nat128.create();
        SecP128R1Field.squareN(create, 2, create2);
        SecP128R1Field.multiply(create2, create, create2);
        int[] create3 = Nat128.create();
        SecP128R1Field.squareN(create2, 4, create3);
        SecP128R1Field.multiply(create3, create2, create3);
        SecP128R1Field.squareN(create3, 2, create2);
        SecP128R1Field.multiply(create2, create, create2);
        SecP128R1Field.squareN(create2, 10, create);
        SecP128R1Field.multiply(create, create2, create);
        SecP128R1Field.squareN(create, 10, create3);
        SecP128R1Field.multiply(create3, create2, create3);
        SecP128R1Field.square(create3, create2);
        SecP128R1Field.multiply(create2, iArr, create2);
        SecP128R1Field.squareN(create2, 95, create2);
        SecP128R1Field.square(create2, create3);
        if (Nat128.m1544eq(iArr, create3)) {
            return new SecP128R1FieldElement(create2);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP128R1FieldElement) {
            return Nat128.m1544eq(this.f2647x, ((SecP128R1FieldElement) obj).f2647x);
        }
        return false;
    }

    public int hashCode() {
        return f2646Q.hashCode() ^ Arrays.hashCode(this.f2647x, 0, 4);
    }
}
