package org.spongycastle.pqc.crypto.ntru;

import java.nio.ByteBuffer;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;

/* loaded from: classes3.dex */
public class NTRUSigner {
    private Digest hashAlg;
    private NTRUSigningParameters params;
    private NTRUSigningPrivateKeyParameters signingKeyPair;
    private NTRUSigningPublicKeyParameters verificationKey;

    public NTRUSigner(NTRUSigningParameters nTRUSigningParameters) {
        this.params = nTRUSigningParameters;
    }

    public void init(boolean z, CipherParameters cipherParameters) {
        if (z) {
            this.signingKeyPair = (NTRUSigningPrivateKeyParameters) cipherParameters;
        } else {
            this.verificationKey = (NTRUSigningPublicKeyParameters) cipherParameters;
        }
        Digest digest = this.params.hashAlg;
        this.hashAlg = digest;
        digest.reset();
    }

    public void update(byte b) {
        Digest digest = this.hashAlg;
        if (digest == null) {
            throw new IllegalStateException("Call initSign or initVerify first!");
        }
        digest.update(b);
    }

    public void update(byte[] bArr, int i, int i2) {
        Digest digest = this.hashAlg;
        if (digest == null) {
            throw new IllegalStateException("Call initSign or initVerify first!");
        }
        digest.update(bArr, i, i2);
    }

    public byte[] generateSignature() {
        Digest digest = this.hashAlg;
        if (digest == null || this.signingKeyPair == null) {
            throw new IllegalStateException("Call initSign first!");
        }
        byte[] bArr = new byte[digest.getDigestSize()];
        this.hashAlg.doFinal(bArr, 0);
        return signHash(bArr, this.signingKeyPair);
    }

    private byte[] signHash(byte[] bArr, NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters) {
        IntegerPolynomial createMsgRep;
        IntegerPolynomial sign;
        NTRUSigningPublicKeyParameters publicKey = nTRUSigningPrivateKeyParameters.getPublicKey();
        int i = 0;
        do {
            i++;
            if (i > this.params.signFailTolerance) {
                throw new IllegalStateException("Signing failed: too many retries (max=" + this.params.signFailTolerance + ")");
            }
            createMsgRep = createMsgRep(bArr, i);
            sign = sign(createMsgRep, nTRUSigningPrivateKeyParameters);
        } while (!verify(createMsgRep, sign, publicKey.f2832h));
        byte[] binary = sign.toBinary(this.params.f2829q);
        ByteBuffer allocate = ByteBuffer.allocate(binary.length + 4);
        allocate.put(binary);
        allocate.putInt(i);
        return allocate.array();
    }

    private IntegerPolynomial sign(IntegerPolynomial integerPolynomial, NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters) {
        int i = this.params.f2824N;
        int i2 = this.params.f2829q;
        NTRUSigningPublicKeyParameters publicKey = nTRUSigningPrivateKeyParameters.getPublicKey();
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(i);
        for (int i3 = this.params.f2823B; i3 >= 1; i3--) {
            Polynomial polynomial = nTRUSigningPrivateKeyParameters.getBasis(i3).f2830f;
            Polynomial polynomial2 = nTRUSigningPrivateKeyParameters.getBasis(i3).fPrime;
            IntegerPolynomial mult = polynomial.mult(integerPolynomial);
            mult.div(i2);
            IntegerPolynomial mult2 = polynomial2.mult(mult);
            IntegerPolynomial mult3 = polynomial2.mult(integerPolynomial);
            mult3.div(i2);
            mult2.sub(polynomial.mult(mult3));
            integerPolynomial2.add(mult2);
            IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) nTRUSigningPrivateKeyParameters.getBasis(i3).f2831h.clone();
            if (i3 > 1) {
                integerPolynomial3.sub(nTRUSigningPrivateKeyParameters.getBasis(i3 - 1).f2831h);
            } else {
                integerPolynomial3.sub(publicKey.f2832h);
            }
            integerPolynomial = mult2.mult(integerPolynomial3, i2);
        }
        Polynomial polynomial3 = nTRUSigningPrivateKeyParameters.getBasis(0).f2830f;
        Polynomial polynomial4 = nTRUSigningPrivateKeyParameters.getBasis(0).fPrime;
        IntegerPolynomial mult4 = polynomial3.mult(integerPolynomial);
        mult4.div(i2);
        IntegerPolynomial mult5 = polynomial4.mult(mult4);
        IntegerPolynomial mult6 = polynomial4.mult(integerPolynomial);
        mult6.div(i2);
        mult5.sub(polynomial3.mult(mult6));
        integerPolynomial2.add(mult5);
        integerPolynomial2.modPositive(i2);
        return integerPolynomial2;
    }

    public boolean verifySignature(byte[] bArr) {
        Digest digest = this.hashAlg;
        if (digest == null || this.verificationKey == null) {
            throw new IllegalStateException("Call initVerify first!");
        }
        byte[] bArr2 = new byte[digest.getDigestSize()];
        this.hashAlg.doFinal(bArr2, 0);
        return verifyHash(bArr2, bArr, this.verificationKey);
    }

    private boolean verifyHash(byte[] bArr, byte[] bArr2, NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr2);
        byte[] bArr3 = new byte[bArr2.length - 4];
        wrap.get(bArr3);
        return verify(createMsgRep(bArr, wrap.getInt()), IntegerPolynomial.fromBinary(bArr3, this.params.f2824N, this.params.f2829q), nTRUSigningPublicKeyParameters.f2832h);
    }

    private boolean verify(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3) {
        int i = this.params.f2829q;
        double d = this.params.normBoundSq;
        double d2 = this.params.betaSq;
        IntegerPolynomial mult = integerPolynomial3.mult(integerPolynomial2, i);
        mult.sub(integerPolynomial);
        return ((double) ((long) (((double) integerPolynomial2.centeredNormSq(i)) + (d2 * ((double) mult.centeredNormSq(i)))))) <= d;
    }

    protected IntegerPolynomial createMsgRep(byte[] bArr, int i) {
        int i2 = this.params.f2824N;
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(this.params.f2829q);
        int i3 = (numberOfLeadingZeros + 7) / 8;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i2);
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 4);
        allocate.put(bArr);
        allocate.putInt(i);
        NTRUSignerPrng nTRUSignerPrng = new NTRUSignerPrng(allocate.array(), this.params.hashAlg);
        for (int i4 = 0; i4 < i2; i4++) {
            byte[] nextBytes = nTRUSignerPrng.nextBytes(i3);
            int i5 = (i3 * 8) - numberOfLeadingZeros;
            nextBytes[nextBytes.length - 1] = (byte) ((nextBytes[nextBytes.length - 1] >> i5) << i5);
            ByteBuffer allocate2 = ByteBuffer.allocate(4);
            allocate2.put(nextBytes);
            allocate2.rewind();
            integerPolynomial.coeffs[i4] = Integer.reverseBytes(allocate2.getInt());
        }
        return integerPolynomial;
    }
}
