package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.prng.DigestRandomGenerator;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.ByteUtils;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.IntegerFunctions;

/* loaded from: classes3.dex */
public class McElieceKobaraImaiCipher implements MessageEncryptor {
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.3";
    public static final byte[] PUBLIC_CONSTANT = "a predetermined public constant".getBytes();
    private boolean forEncryption;

    /* renamed from: k */
    private int f2772k;
    McElieceCCA2KeyParameters key;
    private Digest messDigest;

    /* renamed from: n */
    private int f2773n;

    /* renamed from: sr */
    private SecureRandom f2774sr;

    /* renamed from: t */
    private int f2775t;

    @Override // org.spongycastle.pqc.crypto.MessageEncryptor
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.f2774sr = parametersWithRandom.getRandom();
                McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters = (McElieceCCA2PublicKeyParameters) parametersWithRandom.getParameters();
                this.key = mcElieceCCA2PublicKeyParameters;
                initCipherEncrypt(mcElieceCCA2PublicKeyParameters);
                return;
            }
            this.f2774sr = new SecureRandom();
            McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters2 = (McElieceCCA2PublicKeyParameters) cipherParameters;
            this.key = mcElieceCCA2PublicKeyParameters2;
            initCipherEncrypt(mcElieceCCA2PublicKeyParameters2);
            return;
        }
        McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters = (McElieceCCA2PrivateKeyParameters) cipherParameters;
        this.key = mcElieceCCA2PrivateKeyParameters;
        initCipherDecrypt(mcElieceCCA2PrivateKeyParameters);
    }

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) mcElieceCCA2KeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this.messDigest = Utils.getDigest(mcElieceCCA2PublicKeyParameters.getDigest());
        this.f2773n = mcElieceCCA2PublicKeyParameters.getN();
        this.f2772k = mcElieceCCA2PublicKeyParameters.getK();
        this.f2775t = mcElieceCCA2PublicKeyParameters.getT();
    }

    private void initCipherDecrypt(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.messDigest = Utils.getDigest(mcElieceCCA2PrivateKeyParameters.getDigest());
        this.f2773n = mcElieceCCA2PrivateKeyParameters.getN();
        this.f2772k = mcElieceCCA2PrivateKeyParameters.getK();
        this.f2775t = mcElieceCCA2PrivateKeyParameters.getT();
    }

    @Override // org.spongycastle.pqc.crypto.MessageEncryptor
    public byte[] messageEncrypt(byte[] bArr) {
        if (!this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int digestSize = this.messDigest.getDigestSize();
        int i = this.f2772k >> 3;
        int bitLength = (IntegerFunctions.binomial(this.f2773n, this.f2775t).bitLength() - 1) >> 3;
        byte[] bArr2 = PUBLIC_CONSTANT;
        int length = ((i + bitLength) - digestSize) - bArr2.length;
        if (bArr.length > length) {
            length = bArr.length;
        }
        int length2 = bArr2.length + length;
        int i2 = ((length2 + digestSize) - i) - bitLength;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, length, bArr2.length);
        byte[] bArr4 = new byte[digestSize];
        this.f2774sr.nextBytes(bArr4);
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(bArr4);
        byte[] bArr5 = new byte[length2];
        digestRandomGenerator.nextBytes(bArr5);
        for (int i3 = length2 - 1; i3 >= 0; i3--) {
            bArr5[i3] = (byte) (bArr5[i3] ^ bArr3[i3]);
        }
        byte[] bArr6 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(bArr5, 0, length2);
        this.messDigest.doFinal(bArr6, 0);
        for (int i4 = digestSize - 1; i4 >= 0; i4--) {
            bArr6[i4] = (byte) (bArr6[i4] ^ bArr4[i4]);
        }
        byte[] concatenate = ByteUtils.concatenate(bArr6, bArr5);
        byte[] bArr7 = new byte[0];
        if (i2 > 0) {
            bArr7 = new byte[i2];
            System.arraycopy(concatenate, 0, bArr7, 0, i2);
        }
        byte[] bArr8 = new byte[bitLength];
        System.arraycopy(concatenate, i2, bArr8, 0, bitLength);
        byte[] bArr9 = new byte[i];
        System.arraycopy(concatenate, bitLength + i2, bArr9, 0, i);
        byte[] encoded = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters) this.key, GF2Vector.OS2VP(this.f2772k, bArr9), Conversions.encode(this.f2773n, this.f2775t, bArr8)).getEncoded();
        return i2 > 0 ? ByteUtils.concatenate(bArr7, encoded) : encoded;
    }

    @Override // org.spongycastle.pqc.crypto.MessageEncryptor
    public byte[] messageDecrypt(byte[] bArr) throws InvalidCipherTextException {
        byte[] bArr2;
        if (this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int i = this.f2773n >> 3;
        if (bArr.length < i) {
            throw new InvalidCipherTextException("Bad Padding: Ciphertext too short.");
        }
        int digestSize = this.messDigest.getDigestSize();
        int i2 = this.f2772k >> 3;
        int length = bArr.length - i;
        if (length > 0) {
            byte[][] split = ByteUtils.split(bArr, length);
            bArr2 = split[0];
            bArr = split[1];
        } else {
            bArr2 = new byte[0];
        }
        GF2Vector[] decryptionPrimitive = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters) this.key, GF2Vector.OS2VP(this.f2773n, bArr));
        byte[] encoded = decryptionPrimitive[0].getEncoded();
        GF2Vector gF2Vector = decryptionPrimitive[1];
        if (encoded.length > i2) {
            encoded = ByteUtils.subArray(encoded, 0, i2);
        }
        byte[] concatenate = ByteUtils.concatenate(ByteUtils.concatenate(bArr2, Conversions.decode(this.f2773n, this.f2775t, gF2Vector)), encoded);
        int length2 = concatenate.length - digestSize;
        byte[][] split2 = ByteUtils.split(concatenate, digestSize);
        byte[] bArr3 = split2[0];
        byte[] bArr4 = split2[1];
        byte[] bArr5 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(bArr4, 0, bArr4.length);
        this.messDigest.doFinal(bArr5, 0);
        for (int i3 = digestSize - 1; i3 >= 0; i3--) {
            bArr5[i3] = (byte) (bArr5[i3] ^ bArr3[i3]);
        }
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(bArr5);
        byte[] bArr6 = new byte[length2];
        digestRandomGenerator.nextBytes(bArr6);
        for (int i4 = length2 - 1; i4 >= 0; i4--) {
            bArr6[i4] = (byte) (bArr6[i4] ^ bArr4[i4]);
        }
        byte[] bArr7 = PUBLIC_CONSTANT;
        byte[][] split3 = ByteUtils.split(bArr6, length2 - bArr7.length);
        byte[] bArr8 = split3[0];
        if (ByteUtils.equals(split3[1], bArr7)) {
            return bArr8;
        }
        throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
    }
}
