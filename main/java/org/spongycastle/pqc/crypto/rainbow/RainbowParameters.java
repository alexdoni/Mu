package org.spongycastle.pqc.crypto.rainbow;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;

    /* renamed from: vi */
    private int[] f2841vi;

    public RainbowParameters() {
        int[] iArr = {6, 12, 17, 22, 33};
        this.DEFAULT_VI = iArr;
        this.f2841vi = iArr;
    }

    public RainbowParameters(int[] iArr) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f2841vi = iArr;
        try {
            checkParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkParams() throws Exception {
        int[] iArr;
        int i;
        int[] iArr2 = this.f2841vi;
        if (iArr2 == null) {
            throw new Exception("no layers defined.");
        }
        if (iArr2.length > 1) {
            int i2 = 0;
            do {
                iArr = this.f2841vi;
                if (i2 >= iArr.length - 1) {
                    return;
                }
                i = iArr[i2];
                i2++;
            } while (i < iArr[i2]);
            throw new Exception("v[i] has to be smaller than v[i+1]");
        }
        throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
    }

    public int getNumOfLayers() {
        return this.f2841vi.length - 1;
    }

    public int getDocLength() {
        int[] iArr = this.f2841vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int[] getVi() {
        return this.f2841vi;
    }
}
