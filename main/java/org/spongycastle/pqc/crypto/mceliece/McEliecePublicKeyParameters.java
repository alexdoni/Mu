package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

/* loaded from: classes3.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g */
    private GF2Matrix f2788g;

    /* renamed from: n */
    private int f2789n;

    /* renamed from: t */
    private int f2790t;

    public McEliecePublicKeyParameters(int i, int i2, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f2789n = i;
        this.f2790t = i2;
        this.f2788g = new GF2Matrix(gF2Matrix);
    }

    public int getN() {
        return this.f2789n;
    }

    public int getT() {
        return this.f2790t;
    }

    public GF2Matrix getG() {
        return this.f2788g;
    }

    public int getK() {
        return this.f2788g.getNumRows();
    }
}
