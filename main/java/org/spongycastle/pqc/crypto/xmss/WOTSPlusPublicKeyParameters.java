package org.spongycastle.pqc.crypto.xmss;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class WOTSPlusPublicKeyParameters {
    private final byte[][] publicKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusPublicKeyParameters(WOTSPlusParameters wOTSPlusParameters, byte[][] bArr) {
        if (wOTSPlusParameters == null) {
            throw new NullPointerException("params == null");
        }
        if (bArr == null) {
            throw new NullPointerException("publicKey == null");
        }
        if (XMSSUtil.hasNullPointer(bArr)) {
            throw new NullPointerException("publicKey byte array == null");
        }
        if (bArr.length != wOTSPlusParameters.getLen()) {
            throw new IllegalArgumentException("wrong publicKey size");
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2.length != wOTSPlusParameters.getDigestSize()) {
                throw new IllegalArgumentException("wrong publicKey format");
            }
        }
        this.publicKey = XMSSUtil.cloneArray(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[][] toByteArray() {
        return XMSSUtil.cloneArray(this.publicKey);
    }
}
