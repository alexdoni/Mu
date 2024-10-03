package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

/* loaded from: classes3.dex */
public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: h */
    public IntegerPolynomial f2832h;
    private NTRUSigningParameters params;

    public NTRUSigningPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f2832h = integerPolynomial;
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(byte[] bArr, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.f2832h = IntegerPolynomial.fromBinary(bArr, nTRUSigningParameters.f2824N, nTRUSigningParameters.f2829q);
        this.params = nTRUSigningParameters;
    }

    public NTRUSigningPublicKeyParameters(InputStream inputStream, NTRUSigningParameters nTRUSigningParameters) throws IOException {
        super(false);
        this.f2832h = IntegerPolynomial.fromBinary(inputStream, nTRUSigningParameters.f2824N, nTRUSigningParameters.f2829q);
        this.params = nTRUSigningParameters;
    }

    public byte[] getEncoded() {
        return this.f2832h.toBinary(this.params.f2829q);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.f2832h;
        int hashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        NTRUSigningParameters nTRUSigningParameters = this.params;
        return hashCode + (nTRUSigningParameters != null ? nTRUSigningParameters.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = (NTRUSigningPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.f2832h;
        if (integerPolynomial == null) {
            if (nTRUSigningPublicKeyParameters.f2832h != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(nTRUSigningPublicKeyParameters.f2832h)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = this.params;
        if (nTRUSigningParameters == null) {
            if (nTRUSigningPublicKeyParameters.params != null) {
                return false;
            }
        } else if (!nTRUSigningParameters.equals(nTRUSigningPublicKeyParameters.params)) {
            return false;
        }
        return true;
    }
}
