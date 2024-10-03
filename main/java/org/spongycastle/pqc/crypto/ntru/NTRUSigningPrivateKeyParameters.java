package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

/* loaded from: classes3.dex */
public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter {
    private List<Basis> bases;
    private NTRUSigningPublicKeyParameters publicKey;

    public NTRUSigningPrivateKeyParameters(byte[] bArr, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) throws IOException {
        this(new ByteArrayInputStream(bArr), nTRUSigningKeyGenerationParameters);
    }

    public NTRUSigningPrivateKeyParameters(InputStream inputStream, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) throws IOException {
        super(true);
        this.bases = new ArrayList();
        int i = 0;
        while (i <= nTRUSigningKeyGenerationParameters.f2814B) {
            add(new Basis(inputStream, nTRUSigningKeyGenerationParameters, i != 0));
            i++;
        }
        this.publicKey = new NTRUSigningPublicKeyParameters(inputStream, nTRUSigningKeyGenerationParameters.getSigningParameters());
    }

    public NTRUSigningPrivateKeyParameters(List<Basis> list, NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters) {
        super(true);
        this.bases = new ArrayList(list);
        this.publicKey = nTRUSigningPublicKeyParameters;
    }

    private void add(Basis basis) {
        this.bases.add(basis);
    }

    public Basis getBasis(int i) {
        return this.bases.get(i);
    }

    public NTRUSigningPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < this.bases.size()) {
            this.bases.get(i).encode(byteArrayOutputStream, i != 0);
            i++;
        }
        byteArrayOutputStream.write(this.publicKey.getEncoded());
        return byteArrayOutputStream.toByteArray();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        List<Basis> list = this.bases;
        if (list == null) {
            return 31;
        }
        int hashCode = 31 + list.hashCode();
        Iterator<Basis> it = this.bases.iterator();
        while (it.hasNext()) {
            hashCode += it.next().hashCode();
        }
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters = (NTRUSigningPrivateKeyParameters) obj;
        List<Basis> list = this.bases;
        if ((list == null) != (nTRUSigningPrivateKeyParameters.bases == null)) {
            return false;
        }
        if (list == null) {
            return true;
        }
        if (list.size() != nTRUSigningPrivateKeyParameters.bases.size()) {
            return false;
        }
        for (int i = 0; i < this.bases.size(); i++) {
            Basis basis = this.bases.get(i);
            Basis basis2 = nTRUSigningPrivateKeyParameters.bases.get(i);
            if (!basis.f2830f.equals(basis2.f2830f) || !basis.fPrime.equals(basis2.fPrime)) {
                return false;
            }
            if ((i != 0 && !basis.f2831h.equals(basis2.f2831h)) || !basis.params.equals(basis2.params)) {
                return false;
            }
        }
        return true;
    }

    /* loaded from: classes3.dex */
    public static class Basis {

        /* renamed from: f */
        public Polynomial f2830f;
        public Polynomial fPrime;

        /* renamed from: h */
        public IntegerPolynomial f2831h;
        NTRUSigningKeyGenerationParameters params;

        /* JADX INFO: Access modifiers changed from: protected */
        public Basis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            this.f2830f = polynomial;
            this.fPrime = polynomial2;
            this.f2831h = integerPolynomial;
            this.params = nTRUSigningKeyGenerationParameters;
        }

        Basis(InputStream inputStream, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters, boolean z) throws IOException {
            int i = nTRUSigningKeyGenerationParameters.f2815N;
            int i2 = nTRUSigningKeyGenerationParameters.f2820q;
            int i3 = nTRUSigningKeyGenerationParameters.f2817d1;
            int i4 = nTRUSigningKeyGenerationParameters.f2818d2;
            int i5 = nTRUSigningKeyGenerationParameters.f2819d3;
            boolean z2 = nTRUSigningKeyGenerationParameters.sparse;
            this.params = nTRUSigningKeyGenerationParameters;
            if (nTRUSigningKeyGenerationParameters.polyType == 1) {
                this.f2830f = ProductFormPolynomial.fromBinary(inputStream, i, i3, i4, i5 + 1, i5);
            } else {
                IntegerPolynomial fromBinary3Tight = IntegerPolynomial.fromBinary3Tight(inputStream, i);
                this.f2830f = z2 ? new SparseTernaryPolynomial(fromBinary3Tight) : new DenseTernaryPolynomial(fromBinary3Tight);
            }
            if (nTRUSigningKeyGenerationParameters.basisType == 0) {
                IntegerPolynomial fromBinary = IntegerPolynomial.fromBinary(inputStream, i, i2);
                for (int i6 = 0; i6 < fromBinary.coeffs.length; i6++) {
                    int[] iArr = fromBinary.coeffs;
                    iArr[i6] = iArr[i6] - (i2 / 2);
                }
                this.fPrime = fromBinary;
            } else if (nTRUSigningKeyGenerationParameters.polyType == 1) {
                this.fPrime = ProductFormPolynomial.fromBinary(inputStream, i, i3, i4, i5 + 1, i5);
            } else {
                this.fPrime = IntegerPolynomial.fromBinary3Tight(inputStream, i);
            }
            if (z) {
                this.f2831h = IntegerPolynomial.fromBinary(inputStream, i, i2);
            }
        }

        void encode(OutputStream outputStream, boolean z) throws IOException {
            int i = this.params.f2820q;
            outputStream.write(getEncoded(this.f2830f));
            if (this.params.basisType == 0) {
                IntegerPolynomial integerPolynomial = this.fPrime.toIntegerPolynomial();
                for (int i2 = 0; i2 < integerPolynomial.coeffs.length; i2++) {
                    int[] iArr = integerPolynomial.coeffs;
                    iArr[i2] = iArr[i2] + (i / 2);
                }
                outputStream.write(integerPolynomial.toBinary(i));
            } else {
                outputStream.write(getEncoded(this.fPrime));
            }
            if (z) {
                outputStream.write(this.f2831h.toBinary(i));
            }
        }

        private byte[] getEncoded(Polynomial polynomial) {
            if (polynomial instanceof ProductFormPolynomial) {
                return ((ProductFormPolynomial) polynomial).toBinary();
            }
            return polynomial.toIntegerPolynomial().toBinary3Tight();
        }

        public int hashCode() {
            Polynomial polynomial = this.f2830f;
            int hashCode = ((polynomial == null ? 0 : polynomial.hashCode()) + 31) * 31;
            Polynomial polynomial2 = this.fPrime;
            int hashCode2 = (hashCode + (polynomial2 == null ? 0 : polynomial2.hashCode())) * 31;
            IntegerPolynomial integerPolynomial = this.f2831h;
            int hashCode3 = (hashCode2 + (integerPolynomial == null ? 0 : integerPolynomial.hashCode())) * 31;
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            return hashCode3 + (nTRUSigningKeyGenerationParameters != null ? nTRUSigningKeyGenerationParameters.hashCode() : 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Basis)) {
                return false;
            }
            Basis basis = (Basis) obj;
            Polynomial polynomial = this.f2830f;
            if (polynomial == null) {
                if (basis.f2830f != null) {
                    return false;
                }
            } else if (!polynomial.equals(basis.f2830f)) {
                return false;
            }
            Polynomial polynomial2 = this.fPrime;
            if (polynomial2 == null) {
                if (basis.fPrime != null) {
                    return false;
                }
            } else if (!polynomial2.equals(basis.fPrime)) {
                return false;
            }
            IntegerPolynomial integerPolynomial = this.f2831h;
            if (integerPolynomial == null) {
                if (basis.f2831h != null) {
                    return false;
                }
            } else if (!integerPolynomial.equals(basis.f2831h)) {
                return false;
            }
            NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = this.params;
            if (nTRUSigningKeyGenerationParameters == null) {
                if (basis.params != null) {
                    return false;
                }
            } else if (!nTRUSigningKeyGenerationParameters.equals(basis.params)) {
                return false;
            }
            return true;
        }
    }
}
