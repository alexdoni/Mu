package org.spongycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class RainbowParameterSpec implements AlgorithmParameterSpec {
    private static final int[] DEFAULT_VI = {6, 12, 17, 22, 33};

    /* renamed from: vi */
    private int[] f2860vi;

    public RainbowParameterSpec() {
        this.f2860vi = DEFAULT_VI;
    }

    public RainbowParameterSpec(int[] iArr) {
        this.f2860vi = iArr;
        checkParams();
    }

    private void checkParams() {
        int[] iArr;
        int i;
        int[] iArr2 = this.f2860vi;
        if (iArr2 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (iArr2.length > 1) {
            int i2 = 0;
            do {
                iArr = this.f2860vi;
                if (i2 >= iArr.length - 1) {
                    return;
                }
                i = iArr[i2];
                i2++;
            } while (i < iArr[i2]);
            throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
        }
        throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
    }

    public int getNumOfLayers() {
        return this.f2860vi.length - 1;
    }

    public int getDocumentLength() {
        int[] iArr = this.f2860vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int[] getVi() {
        return Arrays.clone(this.f2860vi);
    }
}
