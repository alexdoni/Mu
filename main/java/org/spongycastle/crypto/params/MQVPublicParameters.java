package org.spongycastle.crypto.params;

import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class MQVPublicParameters implements CipherParameters {
    private ECPublicKeyParameters ephemeralPublicKey;
    private ECPublicKeyParameters staticPublicKey;

    public MQVPublicParameters(ECPublicKeyParameters eCPublicKeyParameters, ECPublicKeyParameters eCPublicKeyParameters2) {
        if (eCPublicKeyParameters == null) {
            throw new NullPointerException("staticPublicKey cannot be null");
        }
        if (eCPublicKeyParameters2 == null) {
            throw new NullPointerException("ephemeralPublicKey cannot be null");
        }
        if (!eCPublicKeyParameters.getParameters().equals(eCPublicKeyParameters2.getParameters())) {
            throw new IllegalArgumentException("Static and ephemeral public keys have different domain parameters");
        }
        this.staticPublicKey = eCPublicKeyParameters;
        this.ephemeralPublicKey = eCPublicKeyParameters2;
    }

    public ECPublicKeyParameters getStaticPublicKey() {
        return this.staticPublicKey;
    }

    public ECPublicKeyParameters getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }
}
