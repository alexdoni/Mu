package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;

/* loaded from: classes3.dex */
public interface CRMFObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_ct_encKeyWithID;
    public static final ASN1ObjectIdentifier id_pkip;
    public static final ASN1ObjectIdentifier id_pkix;
    public static final ASN1ObjectIdentifier id_regCtrl;
    public static final ASN1ObjectIdentifier id_regCtrl_authenticator;
    public static final ASN1ObjectIdentifier id_regCtrl_pkiArchiveOptions;
    public static final ASN1ObjectIdentifier id_regCtrl_pkiPublicationInfo;
    public static final ASN1ObjectIdentifier id_regCtrl_regToken;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.3.6.1.5.5.7");
        id_pkix = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("5");
        id_pkip = branch;
        ASN1ObjectIdentifier branch2 = branch.branch("1");
        id_regCtrl = branch2;
        id_regCtrl_regToken = branch2.branch("1");
        id_regCtrl_authenticator = branch2.branch("2");
        id_regCtrl_pkiPublicationInfo = branch2.branch("3");
        id_regCtrl_pkiArchiveOptions = branch2.branch("4");
        id_ct_encKeyWithID = PKCSObjectIdentifiers.id_ct.branch("21");
    }
}
