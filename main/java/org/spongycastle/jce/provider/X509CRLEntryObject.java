package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.CRLReason;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.TBSCertList;
import org.spongycastle.asn1.x509.X509Extension;
import org.spongycastle.util.Strings;

/* loaded from: classes3.dex */
public class X509CRLEntryObject extends X509CRLEntry {

    /* renamed from: c */
    private TBSCertList.CRLEntry f2589c;
    private X500Name certificateIssuer;
    private int hashValue;
    private boolean isHashValueSet;

    public X509CRLEntryObject(TBSCertList.CRLEntry cRLEntry) {
        this.f2589c = cRLEntry;
        this.certificateIssuer = null;
    }

    public X509CRLEntryObject(TBSCertList.CRLEntry cRLEntry, boolean z, X500Name x500Name) {
        this.f2589c = cRLEntry;
        this.certificateIssuer = loadCertificateIssuer(z, x500Name);
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return (criticalExtensionOIDs == null || criticalExtensionOIDs.isEmpty()) ? false : true;
    }

    private X500Name loadCertificateIssuer(boolean z, X500Name x500Name) {
        if (!z) {
            return null;
        }
        Extension extension = getExtension(Extension.certificateIssuer);
        if (extension == null) {
            return x500Name;
        }
        try {
            GeneralName[] names = GeneralNames.getInstance(extension.getParsedValue()).getNames();
            for (int i = 0; i < names.length; i++) {
                if (names[i].getTagNo() == 4) {
                    return X500Name.getInstance(names[i].getName());
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    @Override // java.security.cert.X509CRLEntry
    public X500Principal getCertificateIssuer() {
        if (this.certificateIssuer == null) {
            return null;
        }
        try {
            return new X500Principal(this.certificateIssuer.getEncoded());
        } catch (IOException unused) {
            return null;
        }
    }

    private Set getExtensionOIDs(boolean z) {
        Extensions extensions = this.f2589c.getExtensions();
        if (extensions == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Enumeration oids = extensions.oids();
        while (oids.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
            if (z == extensions.getExtension(aSN1ObjectIdentifier).isCritical()) {
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    private Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.f2589c.getExtensions();
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        Extension extension = getExtension(new ASN1ObjectIdentifier(str));
        if (extension == null) {
            return null;
        }
        try {
            return extension.getExtnValue().getEncoded();
        } catch (Exception e) {
            throw new RuntimeException("error encoding " + e.toString());
        }
    }

    @Override // java.security.cert.X509CRLEntry
    public int hashCode() {
        if (!this.isHashValueSet) {
            this.hashValue = super.hashCode();
            this.isHashValueSet = true;
        }
        return this.hashValue;
    }

    @Override // java.security.cert.X509CRLEntry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof X509CRLEntryObject) {
            return this.f2589c.equals(((X509CRLEntryObject) obj).f2589c);
        }
        return super.equals(this);
    }

    @Override // java.security.cert.X509CRLEntry
    public byte[] getEncoded() throws CRLException {
        try {
            return this.f2589c.getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    @Override // java.security.cert.X509CRLEntry
    public BigInteger getSerialNumber() {
        return this.f2589c.getUserCertificate().getValue();
    }

    @Override // java.security.cert.X509CRLEntry
    public Date getRevocationDate() {
        return this.f2589c.getRevocationDate().getDate();
    }

    @Override // java.security.cert.X509CRLEntry
    public boolean hasExtensions() {
        return this.f2589c.getExtensions() != null;
    }

    @Override // java.security.cert.X509CRLEntry
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("      userCertificate: ");
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append(getSerialNumber()).append(lineSeparator);
        stringBuffer.append("       revocationDate: ").append(getRevocationDate()).append(lineSeparator);
        stringBuffer.append("       certificateIssuer: ").append(getCertificateIssuer()).append(lineSeparator);
        Extensions extensions = this.f2589c.getExtensions();
        if (extensions != null) {
            Enumeration oids = extensions.oids();
            if (oids.hasMoreElements()) {
                stringBuffer.append("   crlEntryExtensions:").append(lineSeparator);
                while (oids.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                    Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                    if (extension.getExtnValue() != null) {
                        ASN1InputStream aSN1InputStream = new ASN1InputStream(extension.getExtnValue().getOctets());
                        stringBuffer.append("                       critical(").append(extension.isCritical()).append(") ");
                        try {
                            if (aSN1ObjectIdentifier.equals(X509Extension.reasonCode)) {
                                stringBuffer.append(CRLReason.getInstance(ASN1Enumerated.getInstance(aSN1InputStream.readObject()))).append(lineSeparator);
                            } else if (aSN1ObjectIdentifier.equals(X509Extension.certificateIssuer)) {
                                stringBuffer.append("Certificate issuer: ").append(GeneralNames.getInstance(aSN1InputStream.readObject())).append(lineSeparator);
                            } else {
                                stringBuffer.append(aSN1ObjectIdentifier.getId());
                                stringBuffer.append(" value = ").append(ASN1Dump.dumpAsString(aSN1InputStream.readObject())).append(lineSeparator);
                            }
                        } catch (Exception unused) {
                            stringBuffer.append(aSN1ObjectIdentifier.getId());
                            stringBuffer.append(" value = *****").append(lineSeparator);
                        }
                    } else {
                        stringBuffer.append(lineSeparator);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
