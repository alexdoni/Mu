package io.jsonwebtoken.impl.crypto;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.interfaces.RSAKey;

/* loaded from: classes3.dex */
public class RsaSigner extends RsaProvider implements Signer {
    public RsaSigner(SignatureAlgorithm signatureAlgorithm, Key key) {
        super(signatureAlgorithm, key);
        if ((key instanceof PrivateKey) && (key instanceof RSAKey)) {
            return;
        }
        throw new IllegalArgumentException("RSA signatures must be computed using an RSA PrivateKey.  The specified key of type " + key.getClass().getName() + " is not an RSA PrivateKey.");
    }

    @Override // io.jsonwebtoken.impl.crypto.Signer
    public byte[] sign(byte[] bArr) {
        try {
            return doSign(bArr);
        } catch (InvalidKeyException e) {
            throw new SignatureException("Invalid RSA PrivateKey. " + e.getMessage(), e);
        } catch (java.security.SignatureException e2) {
            throw new SignatureException("Unable to calculate signature using RSA PrivateKey. " + e2.getMessage(), e2);
        }
    }

    protected byte[] doSign(byte[] bArr) throws InvalidKeyException, java.security.SignatureException {
        PrivateKey privateKey = (PrivateKey) this.key;
        Signature createSignatureInstance = createSignatureInstance();
        createSignatureInstance.initSign(privateKey);
        createSignatureInstance.update(bArr);
        return createSignatureInstance.sign();
    }
}
