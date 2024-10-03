package org.spongycastle.pqc.jcajce.provider.xmss;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.XMSSMTKeyParams;
import org.spongycastle.pqc.asn1.XMSSMTPrivateKey;
import org.spongycastle.pqc.asn1.XMSSPrivateKey;
import org.spongycastle.pqc.crypto.xmss.BDSStateMap;
import org.spongycastle.pqc.crypto.xmss.XMSSMTParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSMTPrivateKeyParameters;
import org.spongycastle.pqc.crypto.xmss.XMSSUtil;
import org.spongycastle.pqc.jcajce.interfaces.XMSSMTKey;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class BCXMSSMTPrivateKey implements PrivateKey, XMSSMTKey {
    private final XMSSMTPrivateKeyParameters keyParams;
    private final ASN1ObjectIdentifier treeDigest;

    @Override // java.security.Key
    public String getAlgorithm() {
        return "XMSSMT";
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    public BCXMSSMTPrivateKey(ASN1ObjectIdentifier aSN1ObjectIdentifier, XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters) {
        this.treeDigest = aSN1ObjectIdentifier;
        this.keyParams = xMSSMTPrivateKeyParameters;
    }

    public BCXMSSMTPrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        XMSSMTKeyParams xMSSMTKeyParams = XMSSMTKeyParams.getInstance(privateKeyInfo.getPrivateKeyAlgorithm().getParameters());
        ASN1ObjectIdentifier algorithm = xMSSMTKeyParams.getTreeDigest().getAlgorithm();
        this.treeDigest = algorithm;
        XMSSPrivateKey xMSSPrivateKey = XMSSPrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
        try {
            XMSSMTPrivateKeyParameters.Builder withRoot = new XMSSMTPrivateKeyParameters.Builder(new XMSSMTParameters(xMSSMTKeyParams.getHeight(), xMSSMTKeyParams.getLayers(), DigestUtil.getDigest(algorithm))).withIndex(xMSSPrivateKey.getIndex()).withSecretKeySeed(xMSSPrivateKey.getSecretKeySeed()).withSecretKeyPRF(xMSSPrivateKey.getSecretKeyPRF()).withPublicSeed(xMSSPrivateKey.getPublicSeed()).withRoot(xMSSPrivateKey.getRoot());
            if (xMSSPrivateKey.getBdsState() != null) {
                withRoot.withBDSState((BDSStateMap) XMSSUtil.deserialize(xMSSPrivateKey.getBdsState()));
            }
            this.keyParams = withRoot.build();
        } catch (ClassNotFoundException e) {
            throw new IOException("ClassNotFoundException processing BDS state: " + e.getMessage());
        }
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.xmss_mt, new XMSSMTKeyParams(this.keyParams.getParameters().getHeight(), this.keyParams.getParameters().getLayers(), new AlgorithmIdentifier(this.treeDigest))), createKeyStructure()).getEncoded();
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CipherParameters getKeyParams() {
        return this.keyParams;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BCXMSSMTPrivateKey)) {
            return false;
        }
        BCXMSSMTPrivateKey bCXMSSMTPrivateKey = (BCXMSSMTPrivateKey) obj;
        return this.treeDigest.equals(bCXMSSMTPrivateKey.treeDigest) && Arrays.areEqual(this.keyParams.toByteArray(), bCXMSSMTPrivateKey.keyParams.toByteArray());
    }

    public int hashCode() {
        return this.treeDigest.hashCode() + (Arrays.hashCode(this.keyParams.toByteArray()) * 37);
    }

    private XMSSMTPrivateKey createKeyStructure() {
        byte[] byteArray = this.keyParams.toByteArray();
        int digestSize = this.keyParams.getParameters().getDigestSize();
        int height = this.keyParams.getParameters().getHeight();
        int i = (height + 7) / 8;
        int bytesToXBigEndian = (int) XMSSUtil.bytesToXBigEndian(byteArray, 0, i);
        if (!XMSSUtil.isIndexValid(height, bytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        int i2 = i + 0;
        byte[] extractBytesAtOffset = XMSSUtil.extractBytesAtOffset(byteArray, i2, digestSize);
        int i3 = i2 + digestSize;
        byte[] extractBytesAtOffset2 = XMSSUtil.extractBytesAtOffset(byteArray, i3, digestSize);
        int i4 = i3 + digestSize;
        byte[] extractBytesAtOffset3 = XMSSUtil.extractBytesAtOffset(byteArray, i4, digestSize);
        int i5 = i4 + digestSize;
        byte[] extractBytesAtOffset4 = XMSSUtil.extractBytesAtOffset(byteArray, i5, digestSize);
        int i6 = i5 + digestSize;
        return new XMSSMTPrivateKey(bytesToXBigEndian, extractBytesAtOffset, extractBytesAtOffset2, extractBytesAtOffset3, extractBytesAtOffset4, XMSSUtil.extractBytesAtOffset(byteArray, i6, byteArray.length - i6));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigest;
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.XMSSMTKey
    public int getHeight() {
        return this.keyParams.getParameters().getHeight();
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.XMSSMTKey
    public int getLayers() {
        return this.keyParams.getParameters().getLayers();
    }

    @Override // org.spongycastle.pqc.jcajce.interfaces.XMSSMTKey
    public String getTreeDigest() {
        return DigestUtil.getXMSSDigestName(this.treeDigest);
    }
}
