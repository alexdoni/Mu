package org.spongycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.rosstandart.RosstandartObjectIdentifiers;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.math.ec.ECConstants;
import org.spongycastle.math.ec.ECCurve;

/* loaded from: classes3.dex */
public class ECGOST3410NamedCurves {
    static final Hashtable names;
    static final Hashtable objIds;
    static final Hashtable params;

    static {
        Hashtable hashtable = new Hashtable();
        objIds = hashtable;
        Hashtable hashtable2 = new Hashtable();
        params = hashtable2;
        Hashtable hashtable3 = new Hashtable();
        names = hashtable3;
        BigInteger bigInteger = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger2 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        ECCurve.C3197Fp c3197Fp = new ECCurve.C3197Fp(bigInteger, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"), bigInteger2, ECConstants.ONE);
        hashtable2.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A, new ECDomainParameters(c3197Fp, c3197Fp.createPoint(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger2));
        BigInteger bigInteger3 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger4 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        ECCurve.C3197Fp c3197Fp2 = new ECCurve.C3197Fp(bigInteger3, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"), bigInteger4, ECConstants.ONE);
        hashtable2.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA, new ECDomainParameters(c3197Fp2, c3197Fp2.createPoint(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger4));
        BigInteger bigInteger5 = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823193");
        BigInteger bigInteger6 = new BigInteger("57896044618658097711785492504343953927102133160255826820068844496087732066703");
        ECCurve.C3197Fp c3197Fp3 = new ECCurve.C3197Fp(bigInteger5, new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564823190"), new BigInteger("28091019353058090096996979000309560759124368558014865957655842872397301267595"), bigInteger6, ECConstants.ONE);
        hashtable2.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B, new ECDomainParameters(c3197Fp3, c3197Fp3.createPoint(new BigInteger("1"), new BigInteger("28792665814854611296992347458380284135028636778229113005756334730996303888124")), bigInteger6));
        BigInteger bigInteger7 = new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502619");
        BigInteger bigInteger8 = new BigInteger("70390085352083305199547718019018437840920882647164081035322601458352298396601");
        ECCurve.C3197Fp c3197Fp4 = new ECCurve.C3197Fp(bigInteger7, new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502616"), new BigInteger("32858"), bigInteger8, ECConstants.ONE);
        hashtable2.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB, new ECDomainParameters(c3197Fp4, c3197Fp4.createPoint(new BigInteger("0"), new BigInteger("29818893917731240733471273240314769927240550812383695689146495261604565990247")), bigInteger8));
        BigInteger bigInteger9 = new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502619");
        BigInteger bigInteger10 = new BigInteger("70390085352083305199547718019018437840920882647164081035322601458352298396601");
        ECCurve.C3197Fp c3197Fp5 = new ECCurve.C3197Fp(bigInteger9, new BigInteger("70390085352083305199547718019018437841079516630045180471284346843705633502616"), new BigInteger("32858"), bigInteger10, ECConstants.ONE);
        hashtable2.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C, new ECDomainParameters(c3197Fp5, c3197Fp5.createPoint(new BigInteger("0"), new BigInteger("29818893917731240733471273240314769927240550812383695689146495261604565990247")), bigInteger10));
        BigInteger bigInteger11 = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639319");
        BigInteger bigInteger12 = new BigInteger("115792089237316195423570985008687907853073762908499243225378155805079068850323");
        ECCurve.C3197Fp c3197Fp6 = new ECCurve.C3197Fp(bigInteger11, new BigInteger("115792089237316195423570985008687907853269984665640564039457584007913129639316"), new BigInteger("166"), bigInteger12, ECConstants.ONE);
        hashtable2.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256_paramSetA, new ECDomainParameters(c3197Fp6, c3197Fp6.createPoint(new BigInteger("1"), new BigInteger("64033881142927202683649881450433473985931760268884941288852745803908878638612")), bigInteger12));
        BigInteger bigInteger13 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC7", 16);
        BigInteger bigInteger14 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF27E69532F48D89116FF22B8D4E0560609B4B38ABFAD2B85DCACDB1411F10B275", 16);
        ECCurve.C3197Fp c3197Fp7 = new ECCurve.C3197Fp(bigInteger13, new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC4", 16), new BigInteger("E8C2505DEDFC86DDC1BD0B2B6667F1DA34B82574761CB0E879BD081CFD0B6265EE3CB090F30D27614CB4574010DA90DD862EF9D4EBEE4761503190785A71C760", 16), bigInteger14, ECConstants.ONE);
        hashtable2.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetA, new ECDomainParameters(c3197Fp7, c3197Fp7.createPoint(new BigInteger("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003"), new BigInteger("7503CFE87A836AE3A61B8816E25450E6CE5E1C93ACF1ABC1778064FDCBEFA921DF1626BE4FD036E93D75E6A50E3A41E98028FE5FC235F5B889A589CB5215F2A4", 16)), bigInteger14));
        BigInteger bigInteger15 = new BigInteger("8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006F", 16);
        BigInteger bigInteger16 = new BigInteger("800000000000000000000000000000000000000000000000000000000000000149A1EC142565A545ACFDB77BD9D40CFA8B996712101BEA0EC6346C54374F25BD", 16);
        ECCurve.C3197Fp c3197Fp8 = new ECCurve.C3197Fp(bigInteger15, new BigInteger("8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006C", 16), new BigInteger("687D1B459DC841457E3E06CF6F5E2517B97C7D614AF138BCBF85DC806C4B289F3E965D2DB1416D217F8B276FAD1AB69C50F78BEE1FA3106EFB8CCBC7C5140116", 16), bigInteger16, ECConstants.ONE);
        hashtable2.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetB, new ECDomainParameters(c3197Fp8, c3197Fp8.createPoint(new BigInteger("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000002"), new BigInteger("1A8F7EDA389B094C2C071E3647A8940F3C123B697578C213BE6DD9E6C8EC7335DCB228FD1EDF4A39152CBCAAF8C0398828041055F94CEEEC7E21340780FE41BD", 16)), bigInteger16));
        BigInteger bigInteger17 = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFDC7", 16);
        BigInteger bigInteger18 = new BigInteger("3FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFC98CDBA46506AB004C33A9FF5147502CC8EDA9E7A769A12694623CEF47F023ED", 16);
        ECCurve.C3197Fp c3197Fp9 = new ECCurve.C3197Fp(bigInteger17, new BigInteger("DC9203E514A721875485A529D2C722FB187BC8980EB866644DE41C68E143064546E861C0E2C9EDD92ADE71F46FCF50FF2AD97F951FDA9F2A2EB6546F39689BD3", 16), new BigInteger("B4C4EE28CEBC6C2C8AC12952CF37F16AC7EFB6A9F69F4B57FFDA2E4F0DE5ADE038CBC2FFF719D2C18DE0284B8BFEF3B52B8CC7A5F5BF0A3C8D2319A5312557E1", 16), bigInteger18, ECConstants.ONE);
        hashtable2.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetC, new ECDomainParameters(c3197Fp9, c3197Fp9.createPoint(new BigInteger("E2E31EDFC23DE7BDEBE241CE593EF5DE2295B7A9CBAEF021D385F7074CEA043AA27272A7AE602BF2A7B9033DB9ED3610C6FB85487EAE97AAC5BC7928C1950148", 16), new BigInteger("F5CE40D95B5EB899ABBCCFF5911CB8577939804D6527378B8C108C3D2090FF9BE18E2D33E3021ED2EF32D85822423B6304F726AA854BAE07D0396E9A9ADDC40F", 16)), bigInteger18));
        hashtable.put("GostR3410-2001-CryptoPro-A", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A);
        hashtable.put("GostR3410-2001-CryptoPro-B", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B);
        hashtable.put("GostR3410-2001-CryptoPro-C", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C);
        hashtable.put("GostR3410-2001-CryptoPro-XchA", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA);
        hashtable.put("GostR3410-2001-CryptoPro-XchB", CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB);
        hashtable.put("Tc26-Gost-3410-12-256-paramSetA", RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256_paramSetA);
        hashtable.put("Tc26-Gost-3410-12-512-paramSetA", RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetA);
        hashtable.put("Tc26-Gost-3410-12-512-paramSetB", RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetB);
        hashtable.put("Tc26-Gost-3410-12-512-paramSetC", RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetC);
        hashtable3.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_A, "GostR3410-2001-CryptoPro-A");
        hashtable3.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_B, "GostR3410-2001-CryptoPro-B");
        hashtable3.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_C, "GostR3410-2001-CryptoPro-C");
        hashtable3.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchA, "GostR3410-2001-CryptoPro-XchA");
        hashtable3.put(CryptoProObjectIdentifiers.gostR3410_2001_CryptoPro_XchB, "GostR3410-2001-CryptoPro-XchB");
        hashtable3.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_256_paramSetA, "Tc26-Gost-3410-12-256-paramSetA");
        hashtable3.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetA, "Tc26-Gost-3410-12-512-paramSetA");
        hashtable3.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetB, "Tc26-Gost-3410-12-512-paramSetB");
        hashtable3.put(RosstandartObjectIdentifiers.id_tc26_gost_3410_12_512_paramSetC, "Tc26-Gost-3410-12-512-paramSetC");
    }

    public static ECDomainParameters getByOID(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ECDomainParameters) params.get(aSN1ObjectIdentifier);
    }

    public static Enumeration getNames() {
        return names.elements();
    }

    public static ECDomainParameters getByName(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) objIds.get(str);
        if (aSN1ObjectIdentifier != null) {
            return (ECDomainParameters) params.get(aSN1ObjectIdentifier);
        }
        return null;
    }

    public static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) names.get(aSN1ObjectIdentifier);
    }

    public static ASN1ObjectIdentifier getOID(String str) {
        return (ASN1ObjectIdentifier) objIds.get(str);
    }
}
