package org.spongycastle.asn1.bsi;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes3.dex */
public interface BSIObjectIdentifiers {
    public static final ASN1ObjectIdentifier bsi_de;
    public static final ASN1ObjectIdentifier ecdsa_plain_RIPEMD160;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA1;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA224;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA256;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA384;
    public static final ASN1ObjectIdentifier ecdsa_plain_SHA512;
    public static final ASN1ObjectIdentifier ecdsa_plain_signatures;
    public static final ASN1ObjectIdentifier id_ecc;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("0.4.0.127.0.7");
        bsi_de = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("1.1");
        id_ecc = branch;
        ASN1ObjectIdentifier branch2 = branch.branch("4.1");
        ecdsa_plain_signatures = branch2;
        ecdsa_plain_SHA1 = branch2.branch("1");
        ecdsa_plain_SHA224 = branch2.branch("2");
        ecdsa_plain_SHA256 = branch2.branch("3");
        ecdsa_plain_SHA384 = branch2.branch("4");
        ecdsa_plain_SHA512 = branch2.branch("5");
        ecdsa_plain_RIPEMD160 = branch2.branch("6");
    }
}
