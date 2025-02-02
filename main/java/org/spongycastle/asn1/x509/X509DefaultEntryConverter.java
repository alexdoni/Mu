package org.spongycastle.asn1.x509;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERGeneralizedTime;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERUTF8String;

/* loaded from: classes3.dex */
public class X509DefaultEntryConverter extends X509NameEntryConverter {
    @Override // org.spongycastle.asn1.x509.X509NameEntryConverter
    public ASN1Primitive getConvertedValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        if (str.length() != 0 && str.charAt(0) == '#') {
            try {
                return convertHexEncoded(str, 1);
            } catch (IOException unused) {
                throw new RuntimeException("can't recode value for oid " + aSN1ObjectIdentifier.getId());
            }
        }
        if (str.length() != 0 && str.charAt(0) == '\\') {
            str = str.substring(1);
        }
        if (aSN1ObjectIdentifier.equals(X509Name.EmailAddress) || aSN1ObjectIdentifier.equals(X509Name.f2062DC)) {
            return new DERIA5String(str);
        }
        if (aSN1ObjectIdentifier.equals(X509Name.DATE_OF_BIRTH)) {
            return new DERGeneralizedTime(str);
        }
        if (aSN1ObjectIdentifier.equals(X509Name.f2060C) || aSN1ObjectIdentifier.equals(X509Name.f2067SN) || aSN1ObjectIdentifier.equals(X509Name.DN_QUALIFIER) || aSN1ObjectIdentifier.equals(X509Name.TELEPHONE_NUMBER)) {
            return new DERPrintableString(str);
        }
        return new DERUTF8String(str);
    }
}
