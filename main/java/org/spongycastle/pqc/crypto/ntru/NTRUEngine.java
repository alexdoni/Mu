package org.spongycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.TernaryPolynomial;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class NTRUEngine implements AsymmetricBlockCipher {
    private boolean forEncryption;
    private NTRUEncryptionParameters params;
    private NTRUEncryptionPrivateKeyParameters privKey;
    private NTRUEncryptionPublicKeyParameters pubKey;
    private SecureRandom random;

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forEncryption = z;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.pubKey = (NTRUEncryptionPublicKeyParameters) parametersWithRandom.getParameters();
            } else {
                this.random = new SecureRandom();
                this.pubKey = (NTRUEncryptionPublicKeyParameters) cipherParameters;
            }
            this.params = this.pubKey.getParameters();
            return;
        }
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) cipherParameters;
        this.privKey = nTRUEncryptionPrivateKeyParameters;
        this.params = nTRUEncryptionPrivateKeyParameters.getParameters();
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.params.maxMsgLenBytes;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return ((this.params.f2803N * log2(this.params.f2809q)) + 7) / 8;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        if (this.forEncryption) {
            return encrypt(bArr2, this.pubKey);
        }
        return decrypt(bArr2, this.privKey);
    }

    private byte[] encrypt(byte[] bArr, NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters) {
        byte[] bArr2 = bArr;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPublicKeyParameters.f2813h;
        int i = this.params.f2803N;
        int i2 = this.params.f2809q;
        int i3 = this.params.maxMsgLenBytes;
        int i4 = this.params.f2805db;
        int i5 = this.params.bufferLenBits;
        int i6 = this.params.dm0;
        int i7 = this.params.pkLen;
        int i8 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr3 = this.params.oid;
        int length = bArr2.length;
        if (i3 > 255) {
            throw new IllegalArgumentException("llen values bigger than 1 are not supported");
        }
        if (length > i3) {
            throw new DataLengthException("Message too long: " + length + ">" + i3);
        }
        while (true) {
            int i9 = i4 / 8;
            byte[] bArr4 = new byte[i9];
            boolean z2 = z;
            this.random.nextBytes(bArr4);
            int i10 = (i3 + 1) - length;
            int i11 = i8;
            int i12 = i4;
            byte[] bArr5 = new byte[i5 / 8];
            int i13 = i5;
            System.arraycopy(bArr4, 0, bArr5, 0, i9);
            bArr5[i9] = (byte) length;
            int i14 = i9 + 1;
            System.arraycopy(bArr2, 0, bArr5, i14, bArr2.length);
            System.arraycopy(new byte[i10], 0, bArr5, i14 + bArr2.length, i10);
            IntegerPolynomial fromBinary3Sves = IntegerPolynomial.fromBinary3Sves(bArr5, i);
            int i15 = length;
            byte[] bArr6 = bArr3;
            int i16 = i7;
            IntegerPolynomial mult = generateBlindingPoly(buildSData(bArr3, bArr, i15, bArr4, copyOf(integerPolynomial.toBinary(i2), i7 / 8)), bArr5).mult(integerPolynomial, i2);
            IntegerPolynomial integerPolynomial2 = (IntegerPolynomial) mult.clone();
            integerPolynomial2.modPositive(4);
            fromBinary3Sves.add(MGF(integerPolynomial2.toBinary(4), i, i11, z2));
            fromBinary3Sves.mod3();
            if (fromBinary3Sves.count(-1) >= i6 && fromBinary3Sves.count(0) >= i6 && fromBinary3Sves.count(1) >= i6) {
                mult.add(fromBinary3Sves, i2);
                mult.ensurePositive(i2);
                return mult.toBinary(i2);
            }
            z = z2;
            i8 = i11;
            i7 = i16;
            i4 = i12;
            i5 = i13;
            length = i15;
            bArr3 = bArr6;
            bArr2 = bArr;
        }
    }

    private byte[] buildSData(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[bArr.length + i + bArr3.length + bArr4.length];
        System.arraycopy(bArr, 0, bArr5, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr5, bArr.length, bArr2.length);
        System.arraycopy(bArr3, 0, bArr5, bArr.length + bArr2.length, bArr3.length);
        System.arraycopy(bArr4, 0, bArr5, bArr.length + bArr2.length + bArr3.length, bArr4.length);
        return bArr5;
    }

    protected IntegerPolynomial encrypt(IntegerPolynomial integerPolynomial, TernaryPolynomial ternaryPolynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial mult = ternaryPolynomial.mult(integerPolynomial2, this.params.f2809q);
        mult.add(integerPolynomial, this.params.f2809q);
        mult.ensurePositive(this.params.f2809q);
        return mult;
    }

    private Polynomial generateBlindingPoly(byte[] bArr, byte[] bArr2) {
        IndexGenerator indexGenerator = new IndexGenerator(bArr, this.params);
        if (this.params.polyType == 1) {
            return new ProductFormPolynomial(new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr1)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr2)), new SparseTernaryPolynomial(generateBlindingCoeffs(indexGenerator, this.params.dr3)));
        }
        int i = this.params.f2808dr;
        boolean z = this.params.sparse;
        int[] generateBlindingCoeffs = generateBlindingCoeffs(indexGenerator, i);
        if (z) {
            return new SparseTernaryPolynomial(generateBlindingCoeffs);
        }
        return new DenseTernaryPolynomial(generateBlindingCoeffs);
    }

    private int[] generateBlindingCoeffs(IndexGenerator indexGenerator, int i) {
        int[] iArr = new int[this.params.f2803N];
        for (int i2 = -1; i2 <= 1; i2 += 2) {
            int i3 = 0;
            while (i3 < i) {
                int nextIndex = indexGenerator.nextIndex();
                if (iArr[nextIndex] == 0) {
                    iArr[nextIndex] = i2;
                    i3++;
                }
            }
        }
        return iArr;
    }

    private IntegerPolynomial MGF(byte[] bArr, int i, int i2, boolean z) {
        Digest digest = this.params.hashAlg;
        int digestSize = digest.getDigestSize();
        byte[] bArr2 = new byte[i2 * digestSize];
        if (z) {
            bArr = calcHash(digest, bArr);
        }
        int i3 = 0;
        while (i3 < i2) {
            digest.update(bArr, 0, bArr.length);
            putInt(digest, i3);
            System.arraycopy(calcHash(digest), 0, bArr2, i3 * digestSize, digestSize);
            i3++;
        }
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i);
        while (true) {
            int i4 = 0;
            for (int i5 = 0; i5 != bArr2.length; i5++) {
                int i6 = bArr2[i5] & 255;
                if (i6 < 243) {
                    for (int i7 = 0; i7 < 4; i7++) {
                        int i8 = i6 % 3;
                        integerPolynomial.coeffs[i4] = i8 - 1;
                        i4++;
                        if (i4 == i) {
                            return integerPolynomial;
                        }
                        i6 = (i6 - i8) / 3;
                    }
                    integerPolynomial.coeffs[i4] = i6 - 1;
                    i4++;
                    if (i4 == i) {
                        return integerPolynomial;
                    }
                }
            }
            if (i4 >= i) {
                return integerPolynomial;
            }
            digest.update(bArr, 0, bArr.length);
            putInt(digest, i3);
            bArr2 = calcHash(digest);
            i3++;
        }
    }

    private void putInt(Digest digest, int i) {
        digest.update((byte) (i >> 24));
        digest.update((byte) (i >> 16));
        digest.update((byte) (i >> 8));
        digest.update((byte) i);
    }

    private byte[] calcHash(Digest digest) {
        byte[] bArr = new byte[digest.getDigestSize()];
        digest.doFinal(bArr, 0);
        return bArr;
    }

    private byte[] calcHash(Digest digest, byte[] bArr) {
        byte[] bArr2 = new byte[digest.getDigestSize()];
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] decrypt(byte[] bArr, NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters) throws InvalidCipherTextException {
        Polynomial polynomial = nTRUEncryptionPrivateKeyParameters.f2812t;
        IntegerPolynomial integerPolynomial = nTRUEncryptionPrivateKeyParameters.f2810fp;
        IntegerPolynomial integerPolynomial2 = nTRUEncryptionPrivateKeyParameters.f2811h;
        int i = this.params.f2803N;
        int i2 = this.params.f2809q;
        int i3 = this.params.f2805db;
        int i4 = this.params.maxMsgLenBytes;
        int i5 = this.params.dm0;
        int i6 = this.params.pkLen;
        int i7 = this.params.minCallsMask;
        boolean z = this.params.hashSeed;
        byte[] bArr2 = this.params.oid;
        if (i4 > 255) {
            throw new DataLengthException("maxMsgLenBytes values bigger than 255 are not supported");
        }
        int i8 = i3 / 8;
        IntegerPolynomial fromBinary = IntegerPolynomial.fromBinary(bArr, i, i2);
        IntegerPolynomial decrypt = decrypt(fromBinary, polynomial, integerPolynomial);
        if (decrypt.count(-1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal -1");
        }
        if (decrypt.count(0) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 0");
        }
        if (decrypt.count(1) < i5) {
            throw new InvalidCipherTextException("Less than dm0 coefficients equal 1");
        }
        IntegerPolynomial integerPolynomial3 = (IntegerPolynomial) fromBinary.clone();
        integerPolynomial3.sub(decrypt);
        integerPolynomial3.modPositive(i2);
        IntegerPolynomial integerPolynomial4 = (IntegerPolynomial) integerPolynomial3.clone();
        integerPolynomial4.modPositive(4);
        decrypt.sub(MGF(integerPolynomial4.toBinary(4), i, i7, z));
        decrypt.mod3();
        byte[] binary3Sves = decrypt.toBinary3Sves();
        byte[] bArr3 = new byte[i8];
        System.arraycopy(binary3Sves, 0, bArr3, 0, i8);
        int i9 = 255 & binary3Sves[i8];
        if (i9 > i4) {
            throw new InvalidCipherTextException("Message too long: " + i9 + ">" + i4);
        }
        byte[] bArr4 = new byte[i9];
        int i10 = i8 + 1;
        System.arraycopy(binary3Sves, i10, bArr4, 0, i9);
        int i11 = i10 + i9;
        int length = binary3Sves.length - i11;
        byte[] bArr5 = new byte[length];
        System.arraycopy(binary3Sves, i11, bArr5, 0, length);
        if (!Arrays.constantTimeAreEqual(bArr5, new byte[length])) {
            throw new InvalidCipherTextException("The message is not followed by zeroes");
        }
        IntegerPolynomial mult = generateBlindingPoly(buildSData(bArr2, bArr4, i9, bArr3, copyOf(integerPolynomial2.toBinary(i2), i6 / 8)), bArr4).mult(integerPolynomial2);
        mult.modPositive(i2);
        if (mult.equals(integerPolynomial3)) {
            return bArr4;
        }
        throw new InvalidCipherTextException("Invalid message encoding");
    }

    protected IntegerPolynomial decrypt(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2) {
        IntegerPolynomial mult;
        if (this.params.fastFp) {
            mult = polynomial.mult(integerPolynomial, this.params.f2809q);
            mult.mult(3);
            mult.add(integerPolynomial);
        } else {
            mult = polynomial.mult(integerPolynomial, this.params.f2809q);
        }
        mult.center0(this.params.f2809q);
        mult.mod3();
        if (!this.params.fastFp) {
            mult = new DenseTernaryPolynomial(mult).mult(integerPolynomial2, 3);
        }
        mult.center0(3);
        return mult;
    }

    private byte[] copyOf(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (i >= bArr.length) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    private int log2(int i) {
        if (i == 2048) {
            return 11;
        }
        throw new IllegalStateException("log2 not fully implemented");
    }
}
