package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2;

/* loaded from: classes3.dex */
public class McElieceCCA2KeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";
    private final String digest;
    private int fieldPoly;

    /* renamed from: m */
    private final int f2854m;

    /* renamed from: n */
    private final int f2855n;

    /* renamed from: t */
    private final int f2856t;

    public McElieceCCA2KeyGenParameterSpec() {
        this(11, 50, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int i) {
        this(i, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int i, String str) {
        int i2 = 1;
        if (i < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        int i3 = 0;
        while (i2 < i) {
            i2 <<= 1;
            i3++;
        }
        this.f2856t = (i2 >>> 1) / i3;
        this.f2854m = i3;
        this.f2855n = i2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i3);
        this.digest = str;
    }

    public McElieceCCA2KeyGenParameterSpec(int i, int i2) {
        this(i, i2, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int i, int i2, String str) {
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f2854m = i;
        int i3 = 1 << i;
        this.f2855n = i3;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > i3) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f2856t = i2;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i);
        this.digest = str;
    }

    public McElieceCCA2KeyGenParameterSpec(int i, int i2, int i3) {
        this(i, i2, i3, SHA256);
    }

    public McElieceCCA2KeyGenParameterSpec(int i, int i2, int i3, String str) {
        this.f2854m = i;
        if (i < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int i4 = 1 << i;
        this.f2855n = i4;
        this.f2856t = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i2 > i4) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(i3) == i && PolynomialRingGF2.isIrreducible(i3)) {
            this.fieldPoly = i3;
            this.digest = str;
            return;
        }
        throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
    }

    public int getM() {
        return this.f2854m;
    }

    public int getN() {
        return this.f2855n;
    }

    public int getT() {
        return this.f2856t;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public String getDigest() {
        return this.digest;
    }
}
