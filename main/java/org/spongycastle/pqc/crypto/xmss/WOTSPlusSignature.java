package org.spongycastle.pqc.crypto.xmss;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class WOTSPlusSignature {
    private byte[][] signature;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusSignature(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        if (wOTSPlusParameters == null) {
            throw new NullPointerException("params == null");
        }
        if (bArr == null) {
            throw new NullPointerException("signature == null");
        }
        if (XMSSUtil.hasNullPointer(bArr)) {
            throw new NullPointerException("signature byte array == null");
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            throw new IllegalArgumentException("wrong signature size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getDigestSize()) {
                throw new IllegalArgumentException("wrong signature format");
            }
        }
        this.signature = XMSSUtil.cloneArray(bArr);
    }

    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.signature);
    }
}
