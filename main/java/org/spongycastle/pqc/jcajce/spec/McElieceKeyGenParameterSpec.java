package org.spongycastle.pqc.jcajce.spec;

import java.security.InvalidParameterException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* loaded from: classes3.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m */
    private int f2857m;

    /* renamed from: n */
    private int f2858n;

    /* renamed from: t */
    private int f2859t;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public McElieceKeyGenParameterSpec(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f2857m = 0;
        this.f2858n = 1;
        while (true) {
            int i2 = this.f2858n;
            if (i2 < i) {
                this.f2858n = i2 << 1;
                this.f2857m++;
            } else {
                int i3 = i2 >>> 1;
                this.f2859t = i3;
                int i4 = this.f2857m;
                this.f2859t = i3 / i4;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i4);
                return;
            }
        }
    }

    public McElieceKeyGenParameterSpec(int i, int i2) throws InvalidParameterException {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f2857m = i;
        int i3 = 1 << i;
        this.f2858n = i3;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > i3) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f2859t = i2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i);
    }

    public McElieceKeyGenParameterSpec(int i, int i2, int i3) {
        this.f2857m = i;
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int i4 = 1 << i;
        this.f2858n = i4;
        this.f2859t = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > i4) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(i3) == i && PolynomialRingGF2.isIrreducible(i3)) {
            this.fieldPoly = i3;
            return;
        }
        throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
    }

    public int getM() {
        return this.f2857m;
    }

    public int getN() {
        return this.f2858n;
    }

    public int getT() {
        return this.f2859t;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }
}
