package org.spongycastle.jcajce.provider.asymmetric.p021dh;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.jcajce.spec.UserKeyingMaterialSpec;

/* loaded from: classes3.dex */
public class KeyAgreementSpi extends BaseAgreementSpi {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: g */
    private BigInteger f2550g;

    /* renamed from: p */
    private BigInteger f2551p;
    private BigInteger result;

    /* renamed from: x */
    private BigInteger f2552x;

    public KeyAgreementSpi() {
        super("Diffie-Hellman", null);
    }

    public KeyAgreementSpi(String str, DerivationFunction derivationFunction) {
        super(str, derivationFunction);
    }

    protected byte[] bigIntToBytes(BigInteger bigInteger) {
        int bitLength = (this.f2551p.bitLength() + 7) / 8;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == bitLength) {
            return byteArray;
        }
        if (byteArray[0] == 0 && byteArray.length == bitLength + 1) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }
        byte[] bArr2 = new byte[bitLength];
        System.arraycopy(byteArray, 0, bArr2, bitLength - byteArray.length, byteArray.length);
        return bArr2;
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected Key engineDoPhase(Key key, boolean z) throws InvalidKeyException, IllegalStateException {
        if (this.f2552x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        if (!(key instanceof DHPublicKey)) {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        }
        DHPublicKey dHPublicKey = (DHPublicKey) key;
        if (!dHPublicKey.getParams().getG().equals(this.f2550g) || !dHPublicKey.getParams().getP().equals(this.f2551p)) {
            throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
        }
        BigInteger y = dHPublicKey.getY();
        if (y != null && y.compareTo(TWO) >= 0) {
            BigInteger bigInteger = this.f2551p;
            BigInteger bigInteger2 = ONE;
            if (y.compareTo(bigInteger.subtract(bigInteger2)) < 0) {
                BigInteger modPow = y.modPow(this.f2552x, this.f2551p);
                this.result = modPow;
                if (modPow.compareTo(bigInteger2) == 0) {
                    throw new InvalidKeyException("Shared key can't be 1");
                }
                if (z) {
                    return null;
                }
                return new BCDHPublicKey(this.result, dHPublicKey.getParams());
            }
        }
        throw new InvalidKeyException("Invalid DH PublicKey");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.f2552x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        return super.engineGenerateSecret();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i) throws IllegalStateException, ShortBufferException {
        if (this.f2552x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        return super.engineGenerateSecret(bArr, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public SecretKey engineGenerateSecret(String str) throws NoSuchAlgorithmException {
        if (this.f2552x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        byte[] bigIntToBytes = bigIntToBytes(this.result);
        if (str.equals("TlsPremasterSecret")) {
            return new SecretKeySpec(trimZeroes(bigIntToBytes), str);
        }
        return super.engineGenerateSecret(str);
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
        if (algorithmParameterSpec != null) {
            if (algorithmParameterSpec instanceof DHParameterSpec) {
                DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
                this.f2551p = dHParameterSpec.getP();
                this.f2550g = dHParameterSpec.getG();
            } else if (algorithmParameterSpec instanceof UserKeyingMaterialSpec) {
                this.f2551p = dHPrivateKey.getParams().getP();
                this.f2550g = dHPrivateKey.getParams().getG();
                this.ukmParameters = ((UserKeyingMaterialSpec) algorithmParameterSpec).getUserKeyingMaterial();
            } else {
                throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
            }
        } else {
            this.f2551p = dHPrivateKey.getParams().getP();
            this.f2550g = dHPrivateKey.getParams().getG();
        }
        BigInteger x = dHPrivateKey.getX();
        this.result = x;
        this.f2552x = x;
    }

    @Override // javax.crypto.KeyAgreementSpi
    protected void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
        this.f2551p = dHPrivateKey.getParams().getP();
        this.f2550g = dHPrivateKey.getParams().getG();
        BigInteger x = dHPrivateKey.getX();
        this.result = x;
        this.f2552x = x;
    }

    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    protected byte[] calcSecret() {
        return bigIntToBytes(this.result);
    }

    /* loaded from: classes3.dex */
    public static class DHwithRFC2631KDF extends KeyAgreementSpi {
        public DHwithRFC2631KDF() {
            super("DHwithRFC2631KDF", new DHKEKGenerator(DigestFactory.createSHA1()));
        }
    }
}
