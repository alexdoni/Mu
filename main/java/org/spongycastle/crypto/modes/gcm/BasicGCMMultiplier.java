package org.spongycastle.crypto.modes.gcm;

/* loaded from: classes3.dex */
public class BasicGCMMultiplier implements GCMMultiplier {

    /* renamed from: H */
    private int[] f2450H;

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void init(byte[] bArr) {
        this.f2450H = GCMUtil.asInts(bArr);
    }

    @Override // org.spongycastle.crypto.modes.gcm.GCMMultiplier
    public void multiplyH(byte[] bArr) {
        int[] asInts = GCMUtil.asInts(bArr);
        GCMUtil.multiply(asInts, this.f2450H);
        GCMUtil.asBytes(asInts, bArr);
    }
}
