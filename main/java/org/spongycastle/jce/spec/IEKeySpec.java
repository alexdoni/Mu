package org.spongycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.IESKey;

/* loaded from: classes3.dex */
public class IEKeySpec implements KeySpec, IESKey {
    private PrivateKey privKey;
    private PublicKey pubKey;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "IES";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    public IEKeySpec(PrivateKey privateKey, PublicKey publicKey) {
        this.privKey = privateKey;
        this.pubKey = publicKey;
    }

    @Override // org.spongycastle.jce.interfaces.IESKey
    public PublicKey getPublic() {
        return this.pubKey;
    }

    @Override // org.spongycastle.jce.interfaces.IESKey
    public PrivateKey getPrivate() {
        return this.privKey;
    }
}
