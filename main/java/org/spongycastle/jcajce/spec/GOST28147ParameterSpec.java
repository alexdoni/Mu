package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.crypto.engines.GOST28147Engine;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class GOST28147ParameterSpec implements AlgorithmParameterSpec {
    private static Map oidMappings;

    /* renamed from: iv */
    private byte[] f2577iv;
    private byte[] sBox;

    public GOST28147ParameterSpec(byte[] bArr) {
        this.f2577iv = null;
        this.sBox = null;
        byte[] bArr2 = new byte[bArr.length];
        this.sBox = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public GOST28147ParameterSpec(byte[] bArr, byte[] bArr2) {
        this(bArr);
        byte[] bArr3 = new byte[bArr2.length];
        this.f2577iv = bArr3;
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
    }

    public GOST28147ParameterSpec(String str) {
        this.f2577iv = null;
        this.sBox = null;
        this.sBox = GOST28147Engine.getSBox(str);
    }

    public GOST28147ParameterSpec(String str, byte[] bArr) {
        this(str);
        byte[] bArr2 = new byte[bArr.length];
        this.f2577iv = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
    }

    public GOST28147ParameterSpec(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this(getName(aSN1ObjectIdentifier));
        this.f2577iv = Arrays.clone(bArr);
    }

    public byte[] getSbox() {
        return Arrays.clone(this.sBox);
    }

    public byte[] getSBox() {
        return Arrays.clone(this.sBox);
    }

    public byte[] getIV() {
        return Arrays.clone(this.f2577iv);
    }

    static {
        HashMap hashMap = new HashMap();
        oidMappings = hashMap;
        hashMap.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, "E-A");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_B_ParamSet, "E-B");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_C_ParamSet, "E-C");
        oidMappings.put(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_D_ParamSet, "E-D");
    }

    private static String getName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) oidMappings.get(aSN1ObjectIdentifier);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("unknown OID: " + aSN1ObjectIdentifier);
    }
}
