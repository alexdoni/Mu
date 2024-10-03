package org.spongycastle.jcajce.provider.asymmetric.dsa;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class DSASigner extends SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private Digest digest;
    private SecureRandom random;
    private DSA signer;

    protected DSASigner(Digest digest, DSA dsa) {
        this.digest = digest;
        this.signer = dsa;
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        AsymmetricKeyParameter generatePublicKeyParameter = DSAUtil.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, generatePublicKeyParameter);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters generatePrivateKeyParameter = DSAUtil.generatePrivateKeyParameter(privateKey);
        if (this.random != null) {
            generatePrivateKeyParameter = new ParametersWithRandom(generatePrivateKeyParameter, this.random);
        }
        this.digest.reset();
        this.signer.init(true, generatePrivateKeyParameter);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) throws SignatureException {
        this.digest.update(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.digest.update(bArr, i, i2);
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() throws SignatureException {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            BigInteger[] generateSignature = this.signer.generateSignature(bArr);
            return derEncode(generateSignature[0], generateSignature[1]);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        try {
            BigInteger[] derDecode = derDecode(bArr);
            return this.signer.verifySignature(bArr2, derDecode[0], derDecode[1]);
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

    private byte[] derEncode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
        return new DERSequence(new ASN1Integer[]{new ASN1Integer(bigInteger), new ASN1Integer(bigInteger2)}).getEncoded(ASN1Encoding.DER);
    }

    private BigInteger[] derDecode(byte[] bArr) throws IOException {
        ASN1Sequence aSN1Sequence = (ASN1Sequence) ASN1Primitive.fromByteArray(bArr);
        if (aSN1Sequence.size() != 2) {
            throw new IOException("malformed signature");
        }
        if (Arrays.areEqual(bArr, aSN1Sequence.getEncoded(ASN1Encoding.DER))) {
            return new BigInteger[]{((ASN1Integer) aSN1Sequence.getObjectAt(0)).getValue(), ((ASN1Integer) aSN1Sequence.getObjectAt(1)).getValue()};
        }
        throw new IOException("malformed signature");
    }

    /* loaded from: classes3.dex */
    public static class stdDSA extends DSASigner {
        public stdDSA() {
            super(DigestFactory.createSHA1(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSA extends DSASigner {
        public detDSA() {
            super(DigestFactory.createSHA1(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA1())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsa224 extends DSASigner {
        public dsa224() {
            super(DigestFactory.createSHA224(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSA224 extends DSASigner {
        public detDSA224() {
            super(DigestFactory.createSHA224(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA224())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsa256 extends DSASigner {
        public dsa256() {
            super(DigestFactory.createSHA256(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSA256 extends DSASigner {
        public detDSA256() {
            super(DigestFactory.createSHA256(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA256())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsa384 extends DSASigner {
        public dsa384() {
            super(DigestFactory.createSHA384(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSA384 extends DSASigner {
        public detDSA384() {
            super(DigestFactory.createSHA384(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA384())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsa512 extends DSASigner {
        public dsa512() {
            super(DigestFactory.createSHA512(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSA512 extends DSASigner {
        public detDSA512() {
            super(DigestFactory.createSHA512(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA512())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsaSha3_224 extends DSASigner {
        public dsaSha3_224() {
            super(DigestFactory.createSHA3_224(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSASha3_224 extends DSASigner {
        public detDSASha3_224() {
            super(DigestFactory.createSHA3_224(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_224())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsaSha3_256 extends DSASigner {
        public dsaSha3_256() {
            super(DigestFactory.createSHA3_256(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSASha3_256 extends DSASigner {
        public detDSASha3_256() {
            super(DigestFactory.createSHA3_256(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_256())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsaSha3_384 extends DSASigner {
        public dsaSha3_384() {
            super(DigestFactory.createSHA3_384(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSASha3_384 extends DSASigner {
        public detDSASha3_384() {
            super(DigestFactory.createSHA3_384(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_384())));
        }
    }

    /* loaded from: classes3.dex */
    public static class dsaSha3_512 extends DSASigner {
        public dsaSha3_512() {
            super(DigestFactory.createSHA3_512(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }

    /* loaded from: classes3.dex */
    public static class detDSASha3_512 extends DSASigner {
        public detDSASha3_512() {
            super(DigestFactory.createSHA3_512(), new org.spongycastle.crypto.signers.DSASigner(new HMacDSAKCalculator(DigestFactory.createSHA3_512())));
        }
    }

    /* loaded from: classes3.dex */
    public static class noneDSA extends DSASigner {
        public noneDSA() {
            super(new NullDigest(), new org.spongycastle.crypto.signers.DSASigner());
        }
    }
}
