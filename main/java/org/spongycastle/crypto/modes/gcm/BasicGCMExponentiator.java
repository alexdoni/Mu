package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class BasicGCMExponentiator implements GCMExponentiator {

    /* renamed from: x */
    private int[] f2449x;

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void init(byte[] bArr) {
        this.f2449x = GCMUtil.asInts(bArr);
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMExponentiator
    public void exponentiateX(long j, byte[] bArr) {
        int[] oneAsInts = GCMUtil.oneAsInts();
        if (j > 0) {
            int[] clone = Arrays.clone(this.f2449x);
            do {
                if ((1 & j) != 0) {
                    GCMUtil.multiply(oneAsInts, clone);
                }
                GCMUtil.multiply(clone, clone);
                j >>>= 1;
            } while (j > 0);
        }
        GCMUtil.asBytes(oneAsInts, bArr);
    }
}
