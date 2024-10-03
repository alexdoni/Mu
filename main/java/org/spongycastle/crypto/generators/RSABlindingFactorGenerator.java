package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

/* loaded from: classes3.dex */
public class RSABlindingFactorGenerator {
    private RSAKeyParameters key;
    private SecureRandom random;
    private static BigInteger ZERO = BigInteger.valueOf(0);
    private static BigInteger ONE = BigInteger.valueOf(1);

    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (RSAKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            this.key = (RSAKeyParameters) cipherParameters;
            this.random = new SecureRandom();
        }
        if (this.key instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("generator requires RSA public key");
        }
    }

    public BigInteger generateBlindingFactor() {
        RSAKeyParameters rSAKeyParameters = this.key;
        if (rSAKeyParameters == null) {
            throw new IllegalStateException("generator not initialised");
        }
        BigInteger modulus = rSAKeyParameters.getModulus();
        int bitLength = modulus.bitLength() - 1;
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.random);
            BigInteger gcd = bigInteger.gcd(modulus);
            if (!bigInteger.equals(ZERO) && !bigInteger.equals(ONE) && gcd.equals(ONE)) {
                return bigInteger;
            }
        }
    }
}
