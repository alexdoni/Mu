package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

/* loaded from: classes3.dex */
class RSACoreEngine {
    private boolean forEncryption;
    private RSAKeyParameters key;

    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            this.key = (RSAKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters();
        } else {
            this.key = (RSAKeyParameters) cipherParameters;
        }
        this.forEncryption = z;
    }

    public int getInputBlockSize() {
        int bitLength = this.key.getModulus().bitLength();
        if (this.forEncryption) {
            return ((bitLength + 7) / 8) - 1;
        }
        return (bitLength + 7) / 8;
    }

    public int getOutputBlockSize() {
        int bitLength = this.key.getModulus().bitLength();
        if (this.forEncryption) {
            return (bitLength + 7) / 8;
        }
        return ((bitLength + 7) / 8) - 1;
    }

    public BigInteger convertInput(byte[] bArr, int i, int i2) {
        if (i2 > getInputBlockSize() + 1) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        if (i2 == getInputBlockSize() + 1 && !this.forEncryption) {
            throw new DataLengthException("input too large for RSA cipher.");
        }
        if (i != 0 || i2 != bArr.length) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            bArr = bArr2;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.compareTo(this.key.getModulus()) < 0) {
            return bigInteger;
        }
        throw new DataLengthException("input too large for RSA cipher.");
    }

    public byte[] convertOutput(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (this.forEncryption) {
            if (byteArray[0] == 0 && byteArray.length > getOutputBlockSize()) {
                int length = byteArray.length - 1;
                byte[] bArr = new byte[length];
                System.arraycopy(byteArray, 1, bArr, 0, length);
                return bArr;
            }
            if (byteArray.length < getOutputBlockSize()) {
                int outputBlockSize = getOutputBlockSize();
                byte[] bArr2 = new byte[outputBlockSize];
                System.arraycopy(byteArray, 0, bArr2, outputBlockSize - byteArray.length, byteArray.length);
                return bArr2;
            }
        } else if (byteArray[0] == 0) {
            int length2 = byteArray.length - 1;
            byte[] bArr3 = new byte[length2];
            System.arraycopy(byteArray, 1, bArr3, 0, length2);
            return bArr3;
        }
        return byteArray;
    }

    public BigInteger processBlock(BigInteger bigInteger) {
        RSAKeyParameters rSAKeyParameters = this.key;
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters) rSAKeyParameters;
            BigInteger p = rSAPrivateCrtKeyParameters.getP();
            BigInteger q = rSAPrivateCrtKeyParameters.getQ();
            BigInteger dp = rSAPrivateCrtKeyParameters.getDP();
            BigInteger dq = rSAPrivateCrtKeyParameters.getDQ();
            BigInteger qInv = rSAPrivateCrtKeyParameters.getQInv();
            BigInteger modPow = bigInteger.remainder(p).modPow(dp, p);
            BigInteger modPow2 = bigInteger.remainder(q).modPow(dq, q);
            return modPow.subtract(modPow2).multiply(qInv).mod(p).multiply(q).add(modPow2);
        }
        return bigInteger.modPow(rSAKeyParameters.getExponent(), this.key.getModulus());
    }
}
