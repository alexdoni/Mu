package org.spongycastle.pqc.crypto.newhope;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class NHAgreement {
    private NHPrivateKeyParameters privKey;

    public void init(CipherParameters cipherParameters) {
        this.privKey = (NHPrivateKeyParameters) cipherParameters;
    }

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        byte[] bArr = new byte[32];
        NewHope.sharedA(bArr, this.privKey.secData, ((NHPublicKeyParameters) cipherParameters).pubData);
        return bArr;
    }
}
