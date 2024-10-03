package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes3.dex */
public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n */
    private int f2759n;

    /* renamed from: t */
    private int f2760t;

    public McElieceCCA2PublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix, String str) {
        super(false, str);
        this.f2759n = i;
        this.f2760t = i2;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public int getN() {
        return this.f2759n;
    }

    public int getT() {
        return this.f2760t;
    }

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }
}
