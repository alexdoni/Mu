package org.spongycastle.jcajce.provider.asymmetric.dstu;

import com.google.common.base.Ascii;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.DSTU4145Signer;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jce.interfaces.ECKey;

/* loaded from: classes3.dex */
public class SignatureSpi extends java.security.SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private static byte[] DEFAULT_SBOX = {10, 9, Ascii.f546CR, 6, Ascii.f556SO, 11, 4, 5, Ascii.f555SI, 1, 3, Ascii.f548FF, 7, 0, 8, 2, 8, 0, Ascii.f548FF, 4, 9, 6, 7, 11, 2, 3, 1, Ascii.f555SI, 5, Ascii.f556SO, 10, Ascii.f546CR, Ascii.f555SI, 6, 5, 8, Ascii.f556SO, 11, 10, 4, Ascii.f548FF, 0, 3, 7, 2, 9, 1, Ascii.f546CR, 3, 8, Ascii.f546CR, 9, 6, 11, Ascii.f555SI, 0, 2, 5, Ascii.f548FF, 10, 4, Ascii.f556SO, 1, 7, Ascii.f555SI, 8, Ascii.f556SO, 9, 7, 2, 0, Ascii.f546CR, Ascii.f548FF, 6, 1, 5, 11, 4, 3, 10, 2, 8, 9, 7, 5, Ascii.f555SI, 0, 11, Ascii.f548FF, 1, Ascii.f546CR, Ascii.f556SO, 10, 3, 6, 4, 3, 8, 11, 5, 6, 4, Ascii.f556SO, 10, 2, Ascii.f548FF, 1, 7, 9, Ascii.f555SI, Ascii.f546CR, 0, 1, 2, 3, Ascii.f556SO, 6, Ascii.f546CR, 11, 8, Ascii.f555SI, 10, Ascii.f548FF, 5, 7, 9, 0, 4};
    private Digest digest;
    private DSA signer = new DSTU4145Signer();

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        CipherParameters generatePublicKeyParameter;
        if (publicKey instanceof BCDSTU4145PublicKey) {
            generatePublicKeyParameter = ((BCDSTU4145PublicKey) publicKey).engineGetKeyParameters();
        } else {
            generatePublicKeyParameter = ECUtil.generatePublicKeyParameter(publicKey);
        }
        this.digest = new GOST3411Digest(expandSbox(((BCDSTU4145PublicKey) publicKey).getSbox()));
        this.signer.init(false, generatePublicKeyParameter);
    }

    byte[] expandSbox(byte[] bArr) {
        byte[] bArr2 = new byte[128];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr2[i2] = (byte) ((bArr[i] >> 4) & 15);
            bArr2[i2 + 1] = (byte) (bArr[i] & Ascii.f555SI);
        }
        return bArr2;
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        AsymmetricKeyParameter generatePrivateKeyParameter = privateKey instanceof ECKey ? ECUtil.generatePrivateKeyParameter(privateKey) : null;
        this.digest = new GOST3411Digest(DEFAULT_SBOX);
        if (this.appRandom != null) {
            this.signer.init(true, new ParametersWithRandom(generatePrivateKeyParameter, this.appRandom));
        } else {
            this.signer.init(true, generatePrivateKeyParameter);
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) throws SignatureException {
        this.digest.update(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.digest.update(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public byte[] engineSign() throws SignatureException {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            BigInteger[] generateSignature = this.signer.generateSignature(bArr);
            byte[] byteArray = generateSignature[0].toByteArray();
            byte[] byteArray2 = generateSignature[1].toByteArray();
            int length = (byteArray.length > byteArray2.length ? byteArray.length : byteArray2.length) * 2;
            byte[] bArr2 = new byte[length];
            System.arraycopy(byteArray2, 0, bArr2, (length / 2) - byteArray2.length, byteArray2.length);
            System.arraycopy(byteArray, 0, bArr2, length - byteArray.length, byteArray.length);
            return new DEROctetString(bArr2).getEncoded();
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.security.SignatureSpi
    public boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        try {
            byte[] octets = ((ASN1OctetString) ASN1OctetString.fromByteArray(bArr)).getOctets();
            byte[] bArr3 = new byte[octets.length / 2];
            byte[] bArr4 = new byte[octets.length / 2];
            System.arraycopy(octets, 0, bArr4, 0, octets.length / 2);
            System.arraycopy(octets, octets.length / 2, bArr3, 0, octets.length / 2);
            BigInteger bigInteger = new BigInteger(1, bArr4);
            return this.signer.verifySignature(bArr2, new BigInteger[]{new BigInteger(1, bArr3), bigInteger}[0], bigInteger);
        } catch (Exception unused) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }
}
