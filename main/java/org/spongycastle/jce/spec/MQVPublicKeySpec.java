package org.spongycastle.jce.spec;

import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.spongycastle.jce.interfaces.MQVPublicKey;

/* loaded from: classes3.dex */
public class MQVPublicKeySpec implements KeySpec, MQVPublicKey {
    private PublicKey ephemeralKey;
    private PublicKey staticKey;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "ECMQV";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return null;
    }

    @Override // java.security.Key
    public String getFormat() {
        return null;
    }

    public MQVPublicKeySpec(PublicKey publicKey, PublicKey publicKey2) {
        this.staticKey = publicKey;
        this.ephemeralKey = publicKey2;
    }

    @Override // org.spongycastle.jce.interfaces.MQVPublicKey
    public PublicKey getStaticKey() {
        return this.staticKey;
    }

    @Override // org.spongycastle.jce.interfaces.MQVPublicKey
    public PublicKey getEphemeralKey() {
        return this.ephemeralKey;
    }
}
