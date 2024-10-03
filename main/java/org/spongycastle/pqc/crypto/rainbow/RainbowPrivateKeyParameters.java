package org.spongycastle.pqc.crypto.rainbow;

/* loaded from: classes3.dex */
public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    private short[][] A1inv;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f2842b1;

    /* renamed from: b2 */
    private short[] f2843b2;
    private Layer[] layers;

    /* renamed from: vi */
    private int[] f2844vi;

    public RainbowPrivateKeyParameters(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, Layer[] layerArr) {
        super(true, iArr[iArr.length - 1] - iArr[0]);
        this.A1inv = sArr;
        this.f2842b1 = sArr2;
        this.A2inv = sArr3;
        this.f2843b2 = sArr4;
        this.f2844vi = iArr;
        this.layers = layerArr;
    }

    public short[] getB1() {
        return this.f2842b1;
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[] getB2() {
        return this.f2843b2;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.f2844vi;
    }
}
