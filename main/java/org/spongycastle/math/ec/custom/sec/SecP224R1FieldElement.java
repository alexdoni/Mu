package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat224;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class SecP224R1FieldElement extends ECFieldElement {

    /* renamed from: Q */
    public static final BigInteger f2680Q = SecP224R1Curve.f2676q;

    /* renamed from: x */
    protected int[] f2681x;

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP224R1Field";
    }

    public SecP224R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f2680Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        this.f2681x = SecP224R1Field.fromBigInteger(bigInteger);
    }

    public SecP224R1FieldElement() {
        this.f2681x = Nat224.create();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP224R1FieldElement(int[] iArr) {
        this.f2681x = iArr;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat224.isZero(this.f2681x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat224.isOne(this.f2681x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat224.getBit(this.f2681x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.f2681x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return f2680Q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.add(this.f2681x, ((SecP224R1FieldElement) eCFieldElement).f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat224.create();
        SecP224R1Field.addOne(this.f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.subtract(this.f2681x, ((SecP224R1FieldElement) eCFieldElement).f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224R1Field.multiply(this.f2681x, ((SecP224R1FieldElement) eCFieldElement).f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        Mod.invert(SecP224R1Field.f2678P, ((SecP224R1FieldElement) eCFieldElement).f2681x, create);
        SecP224R1Field.multiply(create, this.f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat224.create();
        SecP224R1Field.negate(this.f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat224.create();
        SecP224R1Field.square(this.f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat224.create();
        Mod.invert(SecP224R1Field.f2678P, this.f2681x, create);
        return new SecP224R1FieldElement(create);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f2681x;
        if (Nat224.isZero(iArr) || Nat224.isOne(iArr)) {
            return this;
        }
        int[] create = Nat224.create();
        SecP224R1Field.negate(iArr, create);
        int[] random = Mod.random(SecP224R1Field.f2678P);
        int[] create2 = Nat224.create();
        if (!isSquare(iArr)) {
            return null;
        }
        while (!trySqrt(create, random, create2)) {
            SecP224R1Field.addOne(random, random);
        }
        SecP224R1Field.square(create2, random);
        if (Nat224.m1547eq(iArr, random)) {
            return new SecP224R1FieldElement(create2);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP224R1FieldElement) {
            return Nat224.m1547eq(this.f2681x, ((SecP224R1FieldElement) obj).f2681x);
        }
        return false;
    }

    public int hashCode() {
        return f2680Q.hashCode() ^ Arrays.hashCode(this.f2681x, 0, 7);
    }

    private static boolean isSquare(int[] iArr) {
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        Nat224.copy(iArr, create);
        for (int i = 0; i < 7; i++) {
            Nat224.copy(create, create2);
            SecP224R1Field.squareN(create, 1 << i, create);
            SecP224R1Field.multiply(create, create2, create);
        }
        SecP224R1Field.squareN(create, 95, create);
        return Nat224.isOne(create);
    }

    /* renamed from: RM */
    private static void m1540RM(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6, int[] iArr7) {
        SecP224R1Field.multiply(iArr5, iArr3, iArr7);
        SecP224R1Field.multiply(iArr7, iArr, iArr7);
        SecP224R1Field.multiply(iArr4, iArr2, iArr6);
        SecP224R1Field.add(iArr6, iArr7, iArr6);
        SecP224R1Field.multiply(iArr4, iArr3, iArr7);
        Nat224.copy(iArr6, iArr4);
        SecP224R1Field.multiply(iArr5, iArr2, iArr5);
        SecP224R1Field.add(iArr5, iArr7, iArr5);
        SecP224R1Field.square(iArr5, iArr6);
        SecP224R1Field.multiply(iArr6, iArr, iArr6);
    }

    /* renamed from: RP */
    private static void m1541RP(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        Nat224.copy(iArr, iArr4);
        int[] create = Nat224.create();
        int[] create2 = Nat224.create();
        for (int i = 0; i < 7; i++) {
            Nat224.copy(iArr2, create);
            Nat224.copy(iArr3, create2);
            int i2 = 1 << i;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    m1542RS(iArr2, iArr3, iArr4, iArr5);
                }
            }
            m1540RM(iArr, create, create2, iArr2, iArr3, iArr4, iArr5);
        }
    }

    /* renamed from: RS */
    private static void m1542RS(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4) {
        SecP224R1Field.multiply(iArr2, iArr, iArr2);
        SecP224R1Field.twice(iArr2, iArr2);
        SecP224R1Field.square(iArr, iArr4);
        SecP224R1Field.add(iArr3, iArr4, iArr);
        SecP224R1Field.multiply(iArr3, iArr4, iArr3);
        SecP224R1Field.reduce32(Nat.shiftUpBits(7, iArr3, 2, 0), iArr3);
    }

    private static boolean trySqrt(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = Nat224.create();
        Nat224.copy(iArr2, create);
        int[] create2 = Nat224.create();
        create2[0] = 1;
        int[] create3 = Nat224.create();
        m1541RP(iArr, create, create2, create3, iArr3);
        int[] create4 = Nat224.create();
        int[] create5 = Nat224.create();
        for (int i = 1; i < 96; i++) {
            Nat224.copy(create, create4);
            Nat224.copy(create2, create5);
            m1542RS(create, create2, create3, iArr3);
            if (Nat224.isZero(create)) {
                Mod.invert(SecP224R1Field.f2678P, create5, iArr3);
                SecP224R1Field.multiply(iArr3, create4, iArr3);
                return true;
            }
        }
        return false;
    }
}
