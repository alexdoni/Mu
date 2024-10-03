package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

/* loaded from: classes3.dex */
public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {

    /* renamed from: h */
    public IntegerPolynomial f2813h;

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f2813h = integerPolynomial;
    }

    public NTRUEncryptionPublicKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.f2813h = IntegerPolynomial.fromBinary(bArr, nTRUEncryptionParameters.f2803N, nTRUEncryptionParameters.f2809q);
    }

    public NTRUEncryptionPublicKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) throws IOException {
        super(false, nTRUEncryptionParameters);
        this.f2813h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.f2803N, nTRUEncryptionParameters.f2809q);
    }

    public byte[] getEncoded() {
        return this.f2813h.toBinary(this.params.f2809q);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.f2813h;
        return (((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31) + (this.params != null ? this.params.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.f2813h;
        if (integerPolynomial == null) {
            if (nTRUEncryptionPublicKeyParameters.f2813h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUEncryptionPublicKeyParameters.f2813h)) {
            return false;
        }
        if (this.params == null) {
            if (nTRUEncryptionPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!this.params.equals(nTRUEncryptionPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }
}
