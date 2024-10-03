package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

/* loaded from: classes3.dex */
public class NTRUEncryptionPrivateKeyParameters extends NTRUEncryptionKeyParameters {

    /* renamed from: fp */
    public IntegerPolynomial f2810fp;

    /* renamed from: h */
    public IntegerPolynomial f2811h;

    /* renamed from: t */
    public Polynomial f2812t;

    public NTRUEncryptionPrivateKeyParameters(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(true, nTRUEncryptionParameters);
        this.f2811h = integerPolynomial;
        this.f2812t = polynomial;
        this.f2810fp = integerPolynomial2;
    }

    public NTRUEncryptionPrivateKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) throws IOException {
        this(new ByteArrayInputStream(bArr), nTRUEncryptionParameters);
    }

    public NTRUEncryptionPrivateKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) throws IOException {
        super(true, nTRUEncryptionParameters);
        if (nTRUEncryptionParameters.polyType == 1) {
            int i = nTRUEncryptionParameters.f2803N;
            int i2 = nTRUEncryptionParameters.df1;
            int i3 = nTRUEncryptionParameters.df2;
            int i4 = nTRUEncryptionParameters.df3;
            int i5 = nTRUEncryptionParameters.fastFp ? nTRUEncryptionParameters.df3 : nTRUEncryptionParameters.df3 - 1;
            this.f2811h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.f2803N, nTRUEncryptionParameters.f2809q);
            this.f2812t = ProductFormPolynomial.fromBinary(inputStream, i, i2, i3, i4, i5);
        } else {
            this.f2811h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.f2803N, nTRUEncryptionParameters.f2809q);
            IntegerPolynomial fromBinary3Tight = IntegerPolynomial.fromBinary3Tight(inputStream, nTRUEncryptionParameters.f2803N);
            this.f2812t = nTRUEncryptionParameters.sparse ? new SparseTernaryPolynomial(fromBinary3Tight) : new DenseTernaryPolynomial(fromBinary3Tight);
        }
        init();
    }

    private void init() {
        if (this.params.fastFp) {
            IntegerPolynomial integerPolynomial = new IntegerPolynomial(this.params.f2803N);
            this.f2810fp = integerPolynomial;
            integerPolynomial.coeffs[0] = 1;
            return;
        }
        this.f2810fp = this.f2812t.toIntegerPolynomial().invertF3();
    }

    public byte[] getEncoded() {
        byte[] binary3Tight;
        byte[] binary = this.f2811h.toBinary(this.params.f2809q);
        Polynomial polynomial = this.f2812t;
        if (polynomial instanceof ProductFormPolynomial) {
            binary3Tight = ((ProductFormPolynomial) polynomial).toBinary();
        } else {
            binary3Tight = polynomial.toIntegerPolynomial().toBinary3Tight();
        }
        byte[] bArr = new byte[binary.length + binary3Tight.length];
        System.arraycopy(binary, 0, bArr, 0, binary.length);
        System.arraycopy(binary3Tight, 0, bArr, binary.length, binary3Tight.length);
        return bArr;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        int hashCode = ((this.params == null ? 0 : this.params.hashCode()) + 31) * 31;
        Polynomial polynomial = this.f2812t;
        int hashCode2 = (hashCode + (polynomial == null ? 0 : polynomial.hashCode())) * 31;
        IntegerPolynomial integerPolynomial = this.f2811h;
        return hashCode2 + (integerPolynomial != null ? integerPolynomial.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPrivateKeyParameters)) {
            return false;
        }
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) obj;
        if (this.params == null) {
            if (nTRUEncryptionPrivateKeyParameters.params != null) {
                return false;
            }
        } else if (!this.params.equals(nTRUEncryptionPrivateKeyParameters.params)) {
            return false;
        }
        Polynomial polynomial = this.f2812t;
        if (polynomial == null) {
            if (nTRUEncryptionPrivateKeyParameters.f2812t != null) {
                return false;
            }
        } else if (!polynomial.equals(nTRUEncryptionPrivateKeyParameters.f2812t)) {
            return false;
        }
        return this.f2811h.equals(nTRUEncryptionPrivateKeyParameters.f2811h);
    }
}
