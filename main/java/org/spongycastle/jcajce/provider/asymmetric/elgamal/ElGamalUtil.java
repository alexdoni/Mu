package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.jce.interfaces.ElGamalPrivateKey;
import org.spongycastle.jce.interfaces.ElGamalPublicKey;

/* loaded from: classes3.dex */
public class ElGamalUtil {
    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof ElGamalPublicKey) {
            ElGamalPublicKey elGamalPublicKey = (ElGamalPublicKey) publicKey;
            return new ElGamalPublicKeyParameters(elGamalPublicKey.getY(), new ElGamalParameters(elGamalPublicKey.getParameters().getP(), elGamalPublicKey.getParameters().getG()));
        }
        if (publicKey instanceof DHPublicKey) {
            DHPublicKey dHPublicKey = (DHPublicKey) publicKey;
            return new ElGamalPublicKeyParameters(dHPublicKey.getY(), new ElGamalParameters(dHPublicKey.getParams().getP(), dHPublicKey.getParams().getG()));
        }
        throw new InvalidKeyException("can't identify public key for El Gamal.");
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof ElGamalPrivateKey) {
            ElGamalPrivateKey elGamalPrivateKey = (ElGamalPrivateKey) privateKey;
            return new ElGamalPrivateKeyParameters(elGamalPrivateKey.getX(), new ElGamalParameters(elGamalPrivateKey.getParameters().getP(), elGamalPrivateKey.getParameters().getG()));
        }
        if (privateKey instanceof DHPrivateKey) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) privateKey;
            return new ElGamalPrivateKeyParameters(dHPrivateKey.getX(), new ElGamalParameters(dHPrivateKey.getParams().getP(), dHPrivateKey.getParams().getG()));
        }
        throw new InvalidKeyException("can't identify private key for El Gamal.");
    }
}
