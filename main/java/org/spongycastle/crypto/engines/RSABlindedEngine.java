package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes3.dex */
public class RSABlindedEngine implements AsymmetricBlockCipher {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private RSACoreEngine core = new RSACoreEngine();
    private RSAKeyParameters key;
    private SecureRandom random;

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.core.init(z, cipherParameters);
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (RSAKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            this.key = (RSAKeyParameters) cipherParameters;
            this.random = new SecureRandom();
        }
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.core.getInputBlockSize();
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.core.getOutputBlockSize();
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) {
        BigInteger processBlock;
        if (this.key == null) {
            throw new IllegalStateException("RSA engine not initialised");
        }
        BigInteger convertInput = this.core.convertInput(bArr, i, i2);
        RSAKeyParameters rSAKeyParameters = this.key;
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters) rSAKeyParameters;
            BigInteger publicExponent = rSAPrivateCrtKeyParameters.getPublicExponent();
            if (publicExponent != null) {
                BigInteger modulus = rSAPrivateCrtKeyParameters.getModulus();
                BigInteger bigInteger = ONE;
                BigInteger createRandomInRange = BigIntegers.createRandomInRange(bigInteger, modulus.subtract(bigInteger), this.random);
                processBlock = this.core.processBlock(createRandomInRange.modPow(publicExponent, modulus).multiply(convertInput).mod(modulus)).multiply(createRandomInRange.modInverse(modulus)).mod(modulus);
                if (!convertInput.equals(processBlock.modPow(publicExponent, modulus))) {
                    throw new IllegalStateException("RSA engine faulty decryption/signing detected");
                }
            } else {
                processBlock = this.core.processBlock(convertInput);
            }
        } else {
            processBlock = this.core.processBlock(convertInput);
        }
        return this.core.convertOutput(processBlock);
    }
}
