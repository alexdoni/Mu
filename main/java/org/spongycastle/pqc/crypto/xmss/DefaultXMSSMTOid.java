package org.spongycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* loaded from: classes3.dex */
public final class DefaultXMSSMTOid implements XMSSOid {
    private static final Map<String, DefaultXMSSMTOid> oidLookupTable;
    private final int oid;
    private final String stringRepresentation;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 20, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H20_D2"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 20, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H20_D4"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 40, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H40_D2"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 40, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H40_D4"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 40, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H40_D8"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 60, 8), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H60_D3"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 60, 6), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H60_D6"));
        hashMap.put(createKey(McElieceCCA2KeyGenParameterSpec.SHA256, 32, 16, 67, 60, 12), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-256_W16_H60_D12"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 20, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H20_D2"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 20, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H20_D4"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 40, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H40_D2"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 40, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H40_D4"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 40, 8), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H40_D8"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 60, 3), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H60_D3"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 60, 6), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H60_D6"));
        hashMap.put(createKey("SHA2-512", 64, 16, 131, 60, 12), new DefaultXMSSMTOid(16777217, "XMSSMT_SHA2-512_W16_H60_D12"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 20, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H20_D2"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 20, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H20_D4"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 40, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H40_D2"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 40, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H40_D4"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 40, 8), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H40_D8"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 60, 3), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H60_D3"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 60, 6), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H60_D6"));
        hashMap.put(createKey("SHAKE128", 32, 16, 67, 60, 12), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE128_W16_H60_D12"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 20, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H20_D2"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 20, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H20_D4"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 40, 2), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H40_D2"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 40, 4), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H40_D4"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 40, 8), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H40_D8"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 60, 3), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H60_D3"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 60, 6), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H60_D6"));
        hashMap.put(createKey("SHAKE256", 64, 16, 131, 60, 12), new DefaultXMSSMTOid(16777217, "XMSSMT_SHAKE256_W16_H60_D12"));
        oidLookupTable = Collections.unmodifiableMap(hashMap);
    }

    private DefaultXMSSMTOid(int i, String str) {
        this.oid = i;
        this.stringRepresentation = str;
    }

    public static DefaultXMSSMTOid lookup(String str, int i, int i2, int i3, int i4, int i5) {
        if (str == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return oidLookupTable.get(createKey(str, i, i2, i3, i4, i5));
    }

    private static String createKey(String str, int i, int i2, int i3, int i4, int i5) {
        if (str == null) {
            throw new NullPointerException("algorithmName == null");
        }
        return str + "-" + i + "-" + i2 + "-" + i3 + "-" + i4 + "-" + i5;
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSOid
    public int getOid() {
        return this.oid;
    }

    @Override // org.spongycastle.pqc.crypto.xmss.XMSSOid
    public String toString() {
        return this.stringRepresentation;
    }
}
