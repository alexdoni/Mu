package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.interfaces.GOST3410Params;
import org.spongycastle.jce.interfaces.GOST3410PublicKey;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeySpec;
import org.spongycastle.util.Strings;

/* loaded from: classes3.dex */
public class BCGOST3410PublicKey implements GOST3410PublicKey {
    static final long serialVersionUID = -6251023343619275990L;
    private transient GOST3410Params gost3410Spec;

    /* renamed from: y */
    private BigInteger f2563y;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "GOST3410";
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCGOST3410PublicKey(GOST3410PublicKeySpec gOST3410PublicKeySpec) {
        this.f2563y = gOST3410PublicKeySpec.getY();
        this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(gOST3410PublicKeySpec.getP(), gOST3410PublicKeySpec.getQ(), gOST3410PublicKeySpec.getA()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCGOST3410PublicKey(GOST3410PublicKey gOST3410PublicKey) {
        this.f2563y = gOST3410PublicKey.getY();
        this.gost3410Spec = gOST3410PublicKey.getParameters();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCGOST3410PublicKey(GOST3410PublicKeyParameters gOST3410PublicKeyParameters, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.f2563y = gOST3410PublicKeyParameters.getY();
        this.gost3410Spec = gOST3410ParameterSpec;
    }

    BCGOST3410PublicKey(BigInteger bigInteger, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.f2563y = bigInteger;
        this.gost3410Spec = gOST3410ParameterSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCGOST3410PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = new GOST3410PublicKeyAlgParameters((ASN1Sequence) subjectPublicKeyInfo.getAlgorithmId().getParameters());
        try {
            byte[] octets = ((DEROctetString) subjectPublicKeyInfo.parsePublicKey()).getOctets();
            byte[] bArr = new byte[octets.length];
            for (int i = 0; i != octets.length; i++) {
                bArr[i] = octets[(octets.length - 1) - i];
            }
            this.f2563y = new BigInteger(1, bArr);
            this.gost3410Spec = GOST3410ParameterSpec.fromPublicKeyAlg(gOST3410PublicKeyAlgParameters);
        } catch (IOException unused) {
            throw new IllegalArgumentException("invalid info structure in GOST3410 public key");
        }
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        byte[] bArr;
        SubjectPublicKeyInfo subjectPublicKeyInfo;
        byte[] byteArray = getY().toByteArray();
        if (byteArray[0] == 0) {
            bArr = new byte[byteArray.length - 1];
        } else {
            bArr = new byte[byteArray.length];
        }
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = byteArray[(byteArray.length - 1) - i];
        }
        try {
            GOST3410Params gOST3410Params = this.gost3410Spec;
            if (gOST3410Params instanceof GOST3410ParameterSpec) {
                if (gOST3410Params.getEncryptionParamSetOID() != null) {
                    subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getEncryptionParamSetOID()))), new DEROctetString(bArr));
                } else {
                    subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.gost3410Spec.getPublicKeyParamSetOID()), new ASN1ObjectIdentifier(this.gost3410Spec.getDigestParamSetOID()))), new DEROctetString(bArr));
                }
            } else {
                subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_94), new DEROctetString(bArr));
            }
            return KeyUtil.getEncodedSubjectPublicKeyInfo(subjectPublicKeyInfo);
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // org.spongycastle.jce.interfaces.GOST3410Key
    public GOST3410Params getParameters() {
        return this.gost3410Spec;
    }

    @Override // org.spongycastle.jce.interfaces.GOST3410PublicKey
    public BigInteger getY() {
        return this.f2563y;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GOST3410 Public Key");
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append(lineSeparator);
        stringBuffer.append("            y: ").append(getY().toString(16)).append(lineSeparator);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCGOST3410PublicKey)) {
            return false;
        }
        BCGOST3410PublicKey bCGOST3410PublicKey = (BCGOST3410PublicKey) obj;
        return this.f2563y.equals(bCGOST3410PublicKey.f2563y) && this.gost3410Spec.equals(bCGOST3410PublicKey.gost3410Spec);
    }

    public int hashCode() {
        return this.f2563y.hashCode() ^ this.gost3410Spec.hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.gost3410Spec = new GOST3410ParameterSpec(str, (String) objectInputStream.readObject(), (String) objectInputStream.readObject());
            return;
        }
        this.gost3410Spec = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject()));
        objectInputStream.readObject();
        objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (this.gost3410Spec.getPublicKeyParamSetOID() != null) {
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParamSetOID());
            objectOutputStream.writeObject(this.gost3410Spec.getDigestParamSetOID());
            objectOutputStream.writeObject(this.gost3410Spec.getEncryptionParamSetOID());
        } else {
            objectOutputStream.writeObject(null);
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParameters().getP());
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParameters().getQ());
            objectOutputStream.writeObject(this.gost3410Spec.getPublicKeyParameters().getA());
            objectOutputStream.writeObject(this.gost3410Spec.getDigestParamSetOID());
            objectOutputStream.writeObject(this.gost3410Spec.getEncryptionParamSetOID());
        }
    }
}
