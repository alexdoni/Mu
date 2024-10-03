package org.spongycastle.asn1.p020x9;

import com.xsdk.ab_firstbase.statisics.util.Constants;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes3.dex */
public interface X9ObjectIdentifiers {
    public static final ASN1ObjectIdentifier ansi_X9_42;
    public static final ASN1ObjectIdentifier ansi_X9_62;
    public static final ASN1ObjectIdentifier c2onb191v4;
    public static final ASN1ObjectIdentifier c2onb191v5;
    public static final ASN1ObjectIdentifier c2onb239v4;
    public static final ASN1ObjectIdentifier c2onb239v5;
    public static final ASN1ObjectIdentifier c2pnb163v1;
    public static final ASN1ObjectIdentifier c2pnb163v2;
    public static final ASN1ObjectIdentifier c2pnb163v3;
    public static final ASN1ObjectIdentifier c2pnb176w1;
    public static final ASN1ObjectIdentifier c2pnb208w1;
    public static final ASN1ObjectIdentifier c2pnb272w1;
    public static final ASN1ObjectIdentifier c2pnb304w1;
    public static final ASN1ObjectIdentifier c2pnb368w1;
    public static final ASN1ObjectIdentifier c2tnb191v1;
    public static final ASN1ObjectIdentifier c2tnb191v2;
    public static final ASN1ObjectIdentifier c2tnb191v3;
    public static final ASN1ObjectIdentifier c2tnb239v1;
    public static final ASN1ObjectIdentifier c2tnb239v2;
    public static final ASN1ObjectIdentifier c2tnb239v3;
    public static final ASN1ObjectIdentifier c2tnb359v1;
    public static final ASN1ObjectIdentifier c2tnb431r1;
    public static final ASN1ObjectIdentifier cTwoCurve;
    public static final ASN1ObjectIdentifier characteristic_two_field;
    public static final ASN1ObjectIdentifier dhEphem;
    public static final ASN1ObjectIdentifier dhHybrid1;
    public static final ASN1ObjectIdentifier dhHybrid2;
    public static final ASN1ObjectIdentifier dhHybridOneFlow;
    public static final ASN1ObjectIdentifier dhOneFlow;
    public static final ASN1ObjectIdentifier dhSinglePass_cofactorDH_sha1kdf_scheme;
    public static final ASN1ObjectIdentifier dhSinglePass_stdDH_sha1kdf_scheme;
    public static final ASN1ObjectIdentifier dhStatic;
    public static final ASN1ObjectIdentifier dhpublicnumber;
    public static final ASN1ObjectIdentifier ecdsa_with_SHA1;
    public static final ASN1ObjectIdentifier ecdsa_with_SHA2;
    public static final ASN1ObjectIdentifier ecdsa_with_SHA224;
    public static final ASN1ObjectIdentifier ecdsa_with_SHA256;
    public static final ASN1ObjectIdentifier ecdsa_with_SHA384;
    public static final ASN1ObjectIdentifier ecdsa_with_SHA512;
    public static final ASN1ObjectIdentifier ellipticCurve;
    public static final ASN1ObjectIdentifier gnBasis;
    public static final ASN1ObjectIdentifier id_dsa;
    public static final ASN1ObjectIdentifier id_dsa_with_sha1;
    public static final ASN1ObjectIdentifier id_ecPublicKey;
    public static final ASN1ObjectIdentifier id_ecSigType;
    public static final ASN1ObjectIdentifier id_fieldType;
    public static final ASN1ObjectIdentifier id_kdf_kdf2;
    public static final ASN1ObjectIdentifier id_kdf_kdf3;
    public static final ASN1ObjectIdentifier id_publicKeyType;
    public static final ASN1ObjectIdentifier mqv1;
    public static final ASN1ObjectIdentifier mqv2;
    public static final ASN1ObjectIdentifier mqvSinglePass_sha1kdf_scheme;
    public static final ASN1ObjectIdentifier ppBasis;
    public static final ASN1ObjectIdentifier prime192v1;
    public static final ASN1ObjectIdentifier prime192v2;
    public static final ASN1ObjectIdentifier prime192v3;
    public static final ASN1ObjectIdentifier prime239v1;
    public static final ASN1ObjectIdentifier prime239v2;
    public static final ASN1ObjectIdentifier prime239v3;
    public static final ASN1ObjectIdentifier prime256v1;
    public static final ASN1ObjectIdentifier primeCurve;
    public static final ASN1ObjectIdentifier prime_field;
    public static final ASN1ObjectIdentifier tpBasis;
    public static final ASN1ObjectIdentifier x9_42_schemes;
    public static final ASN1ObjectIdentifier x9_44;
    public static final ASN1ObjectIdentifier x9_44_components;
    public static final ASN1ObjectIdentifier x9_63_scheme;

    static {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier("1.2.840.10045");
        ansi_X9_62 = aSN1ObjectIdentifier;
        ASN1ObjectIdentifier branch = aSN1ObjectIdentifier.branch("1");
        id_fieldType = branch;
        prime_field = branch.branch("1");
        ASN1ObjectIdentifier branch2 = branch.branch("2");
        characteristic_two_field = branch2;
        gnBasis = branch2.branch("3.1");
        tpBasis = branch2.branch("3.2");
        ppBasis = branch2.branch("3.3");
        ASN1ObjectIdentifier branch3 = aSN1ObjectIdentifier.branch("4");
        id_ecSigType = branch3;
        ecdsa_with_SHA1 = branch3.branch("1");
        ASN1ObjectIdentifier branch4 = aSN1ObjectIdentifier.branch("2");
        id_publicKeyType = branch4;
        id_ecPublicKey = branch4.branch("1");
        ASN1ObjectIdentifier branch5 = branch3.branch("3");
        ecdsa_with_SHA2 = branch5;
        ecdsa_with_SHA224 = branch5.branch("1");
        ecdsa_with_SHA256 = branch5.branch("2");
        ecdsa_with_SHA384 = branch5.branch("3");
        ecdsa_with_SHA512 = branch5.branch("4");
        ASN1ObjectIdentifier branch6 = aSN1ObjectIdentifier.branch("3");
        ellipticCurve = branch6;
        ASN1ObjectIdentifier branch7 = branch6.branch("0");
        cTwoCurve = branch7;
        c2pnb163v1 = branch7.branch("1");
        c2pnb163v2 = branch7.branch("2");
        c2pnb163v3 = branch7.branch("3");
        c2pnb176w1 = branch7.branch("4");
        c2tnb191v1 = branch7.branch("5");
        c2tnb191v2 = branch7.branch("6");
        c2tnb191v3 = branch7.branch("7");
        c2onb191v4 = branch7.branch(Constants.SDK_LOGIN_TYPE_HUAWEI);
        c2onb191v5 = branch7.branch("9");
        c2pnb208w1 = branch7.branch("10");
        c2tnb239v1 = branch7.branch("11");
        c2tnb239v2 = branch7.branch("12");
        c2tnb239v3 = branch7.branch("13");
        c2onb239v4 = branch7.branch("14");
        c2onb239v5 = branch7.branch("15");
        c2pnb272w1 = branch7.branch("16");
        c2pnb304w1 = branch7.branch("17");
        c2tnb359v1 = branch7.branch("18");
        c2pnb368w1 = branch7.branch("19");
        c2tnb431r1 = branch7.branch("20");
        ASN1ObjectIdentifier branch8 = branch6.branch("1");
        primeCurve = branch8;
        prime192v1 = branch8.branch("1");
        prime192v2 = branch8.branch("2");
        prime192v3 = branch8.branch("3");
        prime239v1 = branch8.branch("4");
        prime239v2 = branch8.branch("5");
        prime239v3 = branch8.branch("6");
        prime256v1 = branch8.branch("7");
        id_dsa = new ASN1ObjectIdentifier("1.2.840.10040.4.1");
        id_dsa_with_sha1 = new ASN1ObjectIdentifier("1.2.840.10040.4.3");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier("1.3.133.16.840.63.0");
        x9_63_scheme = aSN1ObjectIdentifier2;
        dhSinglePass_stdDH_sha1kdf_scheme = aSN1ObjectIdentifier2.branch("2");
        dhSinglePass_cofactorDH_sha1kdf_scheme = aSN1ObjectIdentifier2.branch("3");
        mqvSinglePass_sha1kdf_scheme = aSN1ObjectIdentifier2.branch("16");
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = new ASN1ObjectIdentifier("1.2.840.10046");
        ansi_X9_42 = aSN1ObjectIdentifier3;
        dhpublicnumber = aSN1ObjectIdentifier3.branch("2.1");
        ASN1ObjectIdentifier branch9 = aSN1ObjectIdentifier3.branch("3");
        x9_42_schemes = branch9;
        dhStatic = branch9.branch("1");
        dhEphem = branch9.branch("2");
        dhOneFlow = branch9.branch("3");
        dhHybrid1 = branch9.branch("4");
        dhHybrid2 = branch9.branch("5");
        dhHybridOneFlow = branch9.branch("6");
        mqv2 = branch9.branch("7");
        mqv1 = branch9.branch(Constants.SDK_LOGIN_TYPE_HUAWEI);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = new ASN1ObjectIdentifier("1.3.133.16.840.9.44");
        x9_44 = aSN1ObjectIdentifier4;
        ASN1ObjectIdentifier branch10 = aSN1ObjectIdentifier4.branch("1");
        x9_44_components = branch10;
        id_kdf_kdf2 = branch10.branch("1");
        id_kdf_kdf3 = branch10.branch("2");
    }
}
